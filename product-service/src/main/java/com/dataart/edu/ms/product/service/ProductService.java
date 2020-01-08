package com.dataart.edu.ms.product.service;

import com.dataart.edu.ms.product.domain.Product;
import com.dataart.edu.ms.product.repository.ProductRepository;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.utils.IDGenerator;
import com.dataart.edu.ms.domain.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventProcessor eventProcessor;

    public void createProduct(Product product) {
        if (StringUtils.isEmpty(product.getId())) {
            product.setId(IDGenerator.nextId());
        }
        productRepository.save(product);
        eventProcessor.processEntity(EventType.PRODUCT_CREATED.type, "product created " + product.getId(), product);
        LOG.error("product created");
    }

    public void establishProduct(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, IDGenerator.nextId(),
                    EventType.PRODUCT_ESTABLISH_STARTED.type, "establish product started", productId);
        }
    }

    public List<Product> list() {
        return this.productRepository.findAll();
    }
}
