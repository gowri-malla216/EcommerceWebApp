package com.example.api_gateway.Filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/api/auth/register",
            "/api/auth/login",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isOpenApiRequest = req -> openApiEndpoints.contains(req.getURI().getPath());
}
