package com.dataart.edu.ms.business;

import com.dataart.edu.ms.business.domain.BusinessArea;
import com.dataart.edu.ms.business.repository.BusinessAreaRepository;
import com.dataart.edu.ms.utils.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class BusinessAreaApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BusinessAreaRepository businessAreaRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void createNewBusinessRepositoryTest() {
        BusinessArea businessArea = new BusinessArea(IDGenerator.nextId(), "business1", "test businessArea entity");
        entityManager.persist(businessArea);
        entityManager.flush();
        Optional<BusinessArea> businessFromDb = businessAreaRepository.findById(businessArea.getId());
        assertThat(businessFromDb.isPresent()).isTrue();
        if (businessFromDb.isEmpty()) {
            return;
        }
        BusinessArea found = businessFromDb.get();
        assertThat(found.getName()).isEqualTo("business1");
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

}
