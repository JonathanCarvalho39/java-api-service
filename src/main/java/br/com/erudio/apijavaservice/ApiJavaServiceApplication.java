package br.com.erudio.apijavaservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ApiService HelpDasck", version = "1", description = "Api desenvolvida para estudos"))
public class ApiJavaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiJavaServiceApplication.class, args);
    }

}
