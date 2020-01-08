package com.dataart.edu.ms.product;

import com.dataart.edu.ms.product.domain.Product;
import com.dataart.edu.ms.product.repository.ProductRepository;
import com.dataart.edu.ms.utils.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ProductApplicationTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void createNewProduct() {
        Product product = new Product(IDGenerator.nextId(), "product1", "business1", "client1");
        entityManager.persist(product);
        entityManager.flush();
        Product found = productRepository.findById(product.getId()).get();
        assertThat(found.getName()).isEqualTo("product1");
    }

}
