package com.fiap.l7.productservice.infrastructure.batch;


import com.fiap.l7.productservice.domain.entity.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Configuration
@EnableBatchProcessing
public class ProductBatchConfig {

    String filePath = "products.csv";

    @Bean
    public Job processProducts(JobRepository jobRepository, Step step){
        return new JobBuilder("processProducts",jobRepository)
                .incrementer(new RunIdIncrementer()) //pra melhorar a performance
                .start(step)
                .build();
    }

    @Bean
    Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Product> reader, ItemProcessor<Product, Product> processor, ItemWriter<Product> writer){
        return new StepBuilder("step", jobRepository)
                .<Product,Product>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer).build();
    }

    @Bean
    ItemReader<Product> reader(){
        return new FlatFileItemReaderBuilder<Product>()
                .name("ItemReader")
                .resource(new ClassPathResource(filePath))
                .delimited()
                .names("name","description","price","stockQuantity")
                .fieldSetMapper(fieldSet -> {
                    Product product = new Product();
                    product.setName(fieldSet.readString("name"));
                    product.setDescription(fieldSet.readString("description"));
                    product.setPrice(fieldSet.readDouble("price"));
                    product.setStockQuantity(fieldSet.readInt("stockQuantity"));
                    product.setCreatedAt(LocalDateTime.now());
                    return product;
                }).linesToSkip(1)
                .build();
    }

    @Bean
    ItemWriter<Product> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Product>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .dataSource(dataSource)
                //.sql("INSERT INTO product (name, description, price, stock_quantity, created_at) " +
                  //      "VALUES (:name, :description, :price, :stockQuantity, :createdAt)")
                .sql("MERGE INTO product (name, description, price, stock_quantity, created_at) " +
                       "KEY(name) VALUES (:name, :description, :price, :stockQuantity, :createdAt)")
                .build();
    }

    @Bean
    ItemProcessor<Product, Product> processor(){
        return new ProductProcessor();
    }

}