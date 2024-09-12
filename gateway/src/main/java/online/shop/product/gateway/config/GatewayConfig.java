package online.shop.product.gateway.config;

import online.shop.product.gateway.filter.AuthFilter;
import online.shop.product.gateway.model.InputLogin;
import online.shop.product.gateway.properties.BackendProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayConfig {

    @Autowired
    BackendProperties backendProperties;

    @Autowired
    AuthFilter authFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //PRODUCT
                .route(backendProperties.getProduct().getName(), r -> r.path(backendProperties.getProduct().getPath().getInquirylist())
                        .and().method(HttpMethod.GET)
                        .filters(f -> f.filters(authFilter))
                        .uri(backendProperties.getProduct().getUri()))
                .route(backendProperties.getProduct().getName(), r -> r.path(backendProperties.getProduct().getPath().getInquirydetail())
                        .and().method(HttpMethod.GET)
                        .filters(f -> f.filters(authFilter))
                        .uri(backendProperties.getProduct().getUri()))

                //USER
                .route(backendProperties.getUser().getName(), r -> r.path(backendProperties.getUser().getPath().getInquirylist())
                        .and().method(HttpMethod.GET)
                        .filters(f -> f.filters(authFilter))
                        .uri(backendProperties.getUser().getUri()))
                .route(backendProperties.getUser().getName(), r -> r.path(backendProperties.getUser().getPath().getAddcart())
                        .and().method(HttpMethod.POST)
//                        .and().readBody(Product.class, s -> true).filters(f -> f.filters(authFilter))
                        .uri(backendProperties.getUser().getUri()))

                //LOGIN
                .route(backendProperties.getUser().getName(),r -> r.path(backendProperties.getUser().getPath().getLogin())
                        .and().readBody(InputLogin.class, s -> true)
                        .and().method(HttpMethod.POST)
                        .uri(backendProperties.getUser().getUri()))
                .build();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
