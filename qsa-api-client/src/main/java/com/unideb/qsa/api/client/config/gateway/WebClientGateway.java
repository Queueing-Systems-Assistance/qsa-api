package com.unideb.qsa.api.client.config.gateway;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import com.unideb.qsa.api.client.config.request.QsaApiRequest;
import com.unideb.qsa.api.domain.api.exception.ApiInternalException;
import com.unideb.qsa.api.domain.api.exception.GatewayException;
import com.unideb.qsa.api.domain.api.response.ErrorResponse;

/**
 * Abstract implementation of a generic {@link Gateway} with a {@link RestTemplate} call.
 * @param <RESPONSE> Type of the response body.
 */
public class WebClientGateway<RESPONSE> implements Gateway<RESPONSE> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebClientGateway.class);
    private static final String FAILED_CALL_EXCEPTION_MESSAGE = "Failed to call {}: {}";
    private static final String CALL_MESSAGE = "Calling to call {}: {}";

    private final WebClient webClient;
    private final GatewayConfiguration<RESPONSE, ? extends ApiInternalException> gatewayConfiguration;

    public WebClientGateway(WebClient webClient, GatewayConfiguration<RESPONSE, ? extends ApiInternalException> gatewayConfiguration) {
        this.webClient = webClient;
        this.gatewayConfiguration = gatewayConfiguration;
    }

    @Override
    public <REQUEST> RESPONSE call(QsaApiRequest<REQUEST> request) {
        LOGGER.info(CALL_MESSAGE, gatewayConfiguration.getTargetApp(), getComponents(request));
        Map<String, String> mdc = MDC.getCopyOfContextMap();
        return createWebClientRequest(request)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> handleError(clientResponse.statusCode(), clientResponse))
                .bodyToMono(gatewayConfiguration.getResponseType())
                .doOnError(throwable -> {
                    MDC.setContextMap(mdc);
                    LOGGER.warn(FAILED_CALL_EXCEPTION_MESSAGE, gatewayConfiguration.getTargetApp(), getComponents(request));
                })
                .block();
    }

    private Mono<Throwable> handleError(HttpStatus httpStatus, ClientResponse clientResponse) {
        return clientResponse.bodyToMono(ErrorResponse.class)
                             .switchIfEmpty(Mono.error(new GatewayException()))
                             .flatMap(errorResponse -> Mono.error(gatewayConfiguration.getExceptionProvider(httpStatus).apply(errorResponse)));
    }

    private <REQUEST> WebClient.RequestBodySpec createWebClientRequest(QsaApiRequest<REQUEST> request) {
        var webClientRequest = webClient
                .method(gatewayConfiguration.getHttpMethod())
                .uri(request.getUri())
                .headers(httpHeaders -> httpHeaders.addAll(request.getHeaders()));
        Optional.ofNullable(request.getRequest()).ifPresent(webClientRequest::bodyValue);
        return webClientRequest;
    }

    private <REQUEST> String getComponents(QsaApiRequest<REQUEST> request) {
        return gatewayConfiguration.getRequestComponentsMessageResolver().resolve(request);
    }
}

