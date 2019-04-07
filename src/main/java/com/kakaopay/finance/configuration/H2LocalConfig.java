package com.kakaopay.finance.configuration;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class H2LocalConfig {

    @Value("spring.datasource.url")
    static String url;

    @Value("spring.datasource.username")
    static String username;

    @Value("spring.datasource.password")
    static String password;

    public static EntityManagerFactory getEntityManagerFactory(){
        return Persistence.createEntityManagerFactory(
                EntityManagerUrlBuilder
                        .builder()
                        .url(url)
                        .username(username)
                        .password(password)
                        .build()
                        .getPersistenceUnitName()
        );
    }

    @Builder
    static class EntityManagerUrlBuilder {
        private String url;
        private String username;
        private String password;

        public String getPersistenceUnitName(){
            return url + ";user=" + username + ";password=" + password;
        }
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory(
//            "objectdb://localhost/myDbFile.odb;user=admin;password=admin");
    }
}