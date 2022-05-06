package com.tubz.msscbreweryclient.client;

import com.tubz.msscbreweryclient.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    private CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerDto customerById = customerClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerById);
    }

    @Test
    void saveNewCustomer() {
        CustomerDto toSave = CustomerDto.builder().build();
        URI uri = customerClient.saveNewCustomer(toSave);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void updateCustomer() {
        CustomerDto toUpdate = CustomerDto.builder().build();
        customerClient.updateCustomer(UUID.randomUUID(), toUpdate);
    }

    @Test
    void deleteCustomer() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}