package com.fiap.l7.productservice;

import com.fiap.l7.productservice.infrastructure.batch.ProductBatchConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBatchTest
@SpringBootTest(classes = {ProductBatchConfig.class})
@SpringJUnitConfig(classes = {ProductBatchConfig.class})
public class ProductBatchConfigTest {
 // Não consegui fazer e não tinha nenhuma refeeência de testes no repositório do curso

}
