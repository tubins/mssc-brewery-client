package com.tubz.msscbreweryclient.client;

import com.tubz.msscbreweryclient.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    private BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerDto dto = breweryClient.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void saveNewBeer() {
        BeerDto toSave = BeerDto
                .builder()
                .id(UUID.randomUUID())
                .beerName("Corona").build();
        URI uri = breweryClient.saveNewBeer(toSave);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void updateBeer() {
        BeerDto toUpdate = BeerDto
                .builder()
                .id(UUID.randomUUID())
                .beerName("Corona").build();
        breweryClient.updateBeer(UUID.randomUUID(), toUpdate);
    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }
}