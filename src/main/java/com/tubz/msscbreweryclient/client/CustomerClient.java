package com.tubz.msscbreweryclient.client;

import com.tubz.msscbreweryclient.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 * Customer client class.
 */
@Component
public class CustomerClient {
    public static final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    @Value("${tubz.brewery.apihost}")
    private String apihost;
    private final RestTemplate restTemplate;

    /**
     * Constructor.
     *
     * @param restTemplateBuilder restTemplateBuilder.
     */
    public CustomerClient(final RestTemplateBuilder restTemplateBuilder) {
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
     * Client call to get customer by id.
     *
     * @param customerId customer id to match with.
     * @return customer info.
     */
    public CustomerDto getCustomerById(final UUID customerId) {
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + UUID.randomUUID(), CustomerDto.class);
    }

    /**
     * Client call to save new customer info.
     *
     * @param customerDto customer info to save.
     * @return URI to the saved customer resource.
     */
    public URI saveNewCustomer(final CustomerDto customerDto) {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }

    /**
     * Client call to update customer info.
     *
     * @param customerDto customer info to update.
     * @return URI to the saved customer resource.
     */
    public void updateCustomer(final UUID customerId, final CustomerDto customerDto) {
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + customerId, customerDto);
    }

    /**
     * Client call to delete customer info.
     *
     * @param customerId customer id to match with.
     */
    public void deleteCustomer(final UUID customerId) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + customerId);
    }
}
