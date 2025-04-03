package com.niko.sbc.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class SampleGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        return 100;
    }

    private final Logger log = LoggerFactory.getLogger(SampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Ejecutando el filtro antes del Request PRE");

        ServerWebExchange finalExchange = exchange.mutate().request(req -> req.headers(h -> h.add("token", "abcdefg"))).build();

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Ejecutando el filtro después del Request POST");
            log.info("token: {}", finalExchange.getRequest().getHeaders().getFirst("token"));

            Optional.ofNullable(finalExchange.getRequest().getHeaders().getFirst("token")).ifPresent(token -> {
                log.info("Token: {}", token);
                finalExchange.getResponse().getHeaders().add("token", token);
            });

            finalExchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "red").build());
            finalExchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }
}
