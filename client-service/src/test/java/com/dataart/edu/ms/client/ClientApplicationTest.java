package com.dataart.edu.ms.client;

import com.dataart.edu.ms.client.domain.Client;
import com.dataart.edu.ms.client.repository.ClientRepository;
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
public class ClientApplicationTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void createNewClientRepositoryTest() {
        Client client = new Client(IDGenerator.nextId(), "Lvivska Kopalnya Kavy", IDGenerator.nextId(), IDGenerator.nextId());
        entityManager.persist(client);
        entityManager.flush();
        Client found = clientRepository.findById(client.getId()).get();
        assertThat(found.getName()).isEqualTo("business1");
    }

}
