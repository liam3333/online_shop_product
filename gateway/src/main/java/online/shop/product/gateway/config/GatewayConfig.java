package online.shop.product.gateway.config;

import online.shop.product.gateway.filter.AuthFilter;
import online.shop.product.gateway.model.ProducList;
import online.shop.product.gateway.model.Product;
import online.shop.product.gateway.properties.BackendProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.WebFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    BackendProperties backendProperties;

    @Autowired
    AuthFilter authFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
                // adding 2 rotes to first microservice as we need to log request body if method is POST
        return builder.routes()
                .route(backendProperties.getProduct().getName(), r -> r.path(backendProperties.getProduct().getPath().getInquirylist())
                        .and().method("GET")
                        .and().readBody(ProducList.class, s -> true).filters(f -> f.filters(authFilter))
                        .uri(backendProperties.getProduct().getUri()))
                .route(backendProperties.getProduct().getName(), r -> r.path(backendProperties.getProduct().getPath().getInquirtdetail())
                        .and().method("GET")
                        .and().readBody(Product.class, s -> true).filters(f -> f.filters(authFilter))
                        .uri(backendProperties.getProduct().getUri()))

                .route(backendProperties.getUser().getName(), r -> r.path(backendProperties.getUser().getPath().getInquirylist())
                        .and().method("GET")
                        .and().readBody(ProducList.class, s -> true).filters(f -> f.filters(authFilter))
                        .uri(backendProperties.getUser().getUri()))
                .route(backendProperties.getUser().getName(), r -> r.path(backendProperties.getUser().getPath().getAddcart())
                        .and().method("GET")
                        .and().readBody(Product.class, s -> true).filters(f -> f.filters(authFilter))
                        .uri(backendProperties.getUser().getUri()))

                .route(backendProperties.getUser().getName(),r -> r.path(backendProperties.getUser().getPath().getLogin())
                        .uri(backendProperties.getUser().getUri()))
                .build();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
