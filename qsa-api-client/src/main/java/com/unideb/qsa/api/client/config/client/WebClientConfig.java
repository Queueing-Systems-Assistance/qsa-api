package com.unideb.qsa.api.client.config.client;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

/**
 * Configuration for {@link WebClient}.
 */
@Configuration
public class WebClientConfig {

    @Value("${client.config.timeout}")
    private int webClientTimeoutMillis;
    @Value("${client.config.read-timeout}")
    private int webclientReadTimeoutMillis;
    @Value("${client.config.write-timeout}")
    private int webclientWriteTimeoutMillis;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                        .clientConnector(new ReactorClientHttpConnector(HttpClient.from(createTcpClient())))
                        .build();
    }

    private TcpClient createTcpClient() {
        return TcpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webClientTimeoutMillis)
                        .doOnConnected(connection -> {
                            connection.addHandlerLast(new ReadTimeoutHandler(webclientReadTimeoutMillis, TimeUnit.MILLISECONDS));
                            connection.addHandlerLast(new WriteTimeoutHandler(webclientWriteTimeoutMillis, TimeUnit.MILLISECONDS));
                        });
    }

}
