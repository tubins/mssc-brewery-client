package com.tubz.msscbreweryclient.client;

import com.tubz.msscbreweryclient.model.BeerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 * Vrewery client class.
 */
@Component
public class BreweryClient {
    public static final String BEER_PATH_V1 = "/api/v1/beer/";
    @Value("${tubz.brewery.apihost}")
    private String apihost;
    private final RestTemplate restTemplate;

    /**
     * Constructor.
     *
     * @param restTemplateBuilder restTemplateBuilder.
     */
    public BreweryClient(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Sets api host.
     *
     * @param apihost
     */
    public void setApihost(final String apihost) {
        this.apihost = apihost;
    }

    /**
     * Client call to get beer by id.
     *
     * @param beerId beer id to match with.
     * @return beer info.
     */
    public BeerDto getBeerById(final UUID beerId) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + UUID.randomUUID(), BeerDto.class);
    }

    /**
     * Client call to save new beer info.
     *
     * @param beerDto beer info to save.
     * @return URI to the saved beer resource.
     */
    public URI saveNewBeer(final BeerDto beerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    /**
     * Client call to update beer info.
     *
     * @param beerDto beer info to update.
     * @return URI to the saved beer resource.
     */
    public void updateBeer(final UUID beerId, final BeerDto beerDto) {
        restTemplate.put(apihost + BEER_PATH_V1 + beerId, beerDto);
    }

    /**
     * Client call to delete beer info.
     *
     * @param beerId beer id to match with.
     */
    public void deleteBeer(final UUID beerId) {
        restTemplate.delete(apihost + BEER_PATH_V1 + beerId);
    }
}
