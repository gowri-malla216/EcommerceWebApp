package com.example.api_gateway.Filter;

import com.example.api_gateway.Helper.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;


@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtHelper jwtHelper;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain)->{
            if(!routeValidator.isOpenApiRequest.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Authorization header not present");
                }
                String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if(token!=null && token.startsWith("Bearer ")){
                    token = token.substring(7);
                    try{
                        if(!jwtHelper.validateToken(token))
                            throw new RuntimeException("Invalid token");
                    }
                    catch(Exception e){
                        throw new RuntimeException("Invalid token");
                    }
                }
            }
            return chain.filter(exchange);
        });
    }

    

    public static class Config{
    }
}
