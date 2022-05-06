package com.tubz.msscbreweryclient.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateCustomizer Class.
 */
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    private final Integer maxTotalConnections;
    private final Integer defaultMaxTotalConnetions;
    private final Integer connectionRequestTimeout;
    private final Integer socketTimeout;

    /**
     * All args constructor.
     *
     * @param maxTotalConnections       max total connections.
     * @param defaultMaxTotalConnetions default max total connections.
     * @param connectionRequestTimeout  connection request timeout.
     * @param socketTimeout             socket timeout.
     */
    public BlockingRestTemplateCustomizer(@Value("${tubz.maxtotalconnections}") Integer maxTotalConnections,
                                          @Value("${tubz.defaultmaxtotalconnections}") Integer defaultMaxTotalConnetions,
                                          @Value("${tubz.connectionrequesttimeout}") Integer connectionRequestTimeout,
                                          @Value("${tubz.sockettimeout}") Integer socketTimeout) {
        this.maxTotalConnections = maxTotalConnections;
        this.defaultMaxTotalConnetions = defaultMaxTotalConnetions;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.socketTimeout = socketTimeout;
    }

    /**
     * Setup ClientHttpRequestFactory.
     *
     * @return instance of ClientHttpRequestFactory.
     */
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(defaultMaxTotalConnetions);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void customize(final RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
