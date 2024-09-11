package online.shop.product.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "backend")
@Configuration("BackendProperties")
@Data
public class BackendProperties {
    private Backend<Product> product;
    private Backend<User> user;

    @Data
    public static class Product{
        private String inquirylist;
        private String inquirydetail;
    }
    @Data
    public static class User{
        private String login;
        private String inquirylist;
        private String addcart;
    }

    @Data
    public static class Backend<T>{
        private String name;
        private String uri;
        private T path;

    }

}
