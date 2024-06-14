package br.com.erudio.apijavaservice.config;

import br.com.erudio.apijavaservice.services.DBService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @PostConstruct
    public void instaciaDB(){
        this.dbService.instaciaDB();
    }
}
