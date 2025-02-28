package online.shop.product.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import online.shop.product.gateway.model.HeaderRequestDto;
import online.shop.product.gateway.util.JWTUtil;
import online.shop.product.gateway.validator.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RefreshScope
@Slf4j
public class AuthFilter implements GatewayFilter {

    @Autowired
    RouteValidator routeValidator;

    @Autowired
    private JWTUtil jwtUtil;

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Value("${authentication.enabled}")
    private boolean authEnabled;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(!authEnabled) {
            log.info("Authentication is disabled. To enable it, make \"authentication.enabled\" property as true");
            return chain.filter(exchange);
        }
        String token ="";
        ServerHttpRequest request = exchange.getRequest();

        if(routeValidator.isSecured.test(request)) {
            log.debug("validating authentication token");
            if(!request.getHeaders().containsKey("Authorization")) {
                log.debug("in error");
                return this.onError(exchange,"Credentials missing",HttpStatus.UNAUTHORIZED);
            }

            token = Objects.requireNonNull(request.getHeaders().get("Authorization")).toString().split(" ")[1];
            if(jwtUtil.isInvalid(token)) {
                return this.onError(exchange,"Auth header invalid",HttpStatus.UNAUTHORIZED);
            }
            else {
                log.debug("Authentication is successful");
            }

            this.populateRequestWithHeaders(exchange,token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getALlClaims(token);
        String userId = (String) claims.get("userId");
        Integer roleId = null;

        // Handle potential type issues
        Object roleIdObject = claims.get("roleId");
        if (roleIdObject instanceof Integer) {
            roleId = (Integer) roleIdObject;
        } else if (roleIdObject instanceof String) {
            try {
                roleId = Integer.parseInt((String) roleIdObject);
            } catch (NumberFormatException e) {
                // Handle parsing error if roleId is not a valid integer
                System.err.println("Invalid roleId format: " + e.getMessage());
            }
        }
        HeaderRequestDto headerRequestDto = new HeaderRequestDto(userId,roleId);
        try {
            exchange.getRequest()
                    .mutate()
                    .header("USER-DETAILS", objectMapper.writeValueAsString(headerRequestDto))
                    .build();
        }catch (Exception e){
            log.info("Failed Mapping Json to String");
        }
    }
}
