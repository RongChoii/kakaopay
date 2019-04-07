package com.kakaopay.finance.configuration;

import lombok.Builder;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class H2LocalConfig {

//    @Value("spring.datasource.url") //@@@@@@@@@@@
    static String url = "jdbc:h2:file:C:/Users/rong/eclipse-workspace/financeDB;AUTO_SERVER=TRUE;";
//    static String url = "jdbc:h2:tcp://localhost:8080/financeDB;AUTO_SERVER=TRUE";
//    static String url = "jdbc:h2:mem:financeDB;DB_CLOSE_ON_EXIT=FALSE;";

//    @Value("spring.datasource.username")
    static String username="kakao";

//    @Value("spring.datasource.password")
    static String password="0000";

    public static EntityManagerFactory getEntityManagerFactory(){

        System.out.println("@@@@ H2LocalConfig : getEntityMangerFactory : " + url + "/" + username + "/" + password);

        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", password);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                url, properties);

        return emf;

//        return Persistence.createEntityManagerFactory(
//                EntityManagerUrlBuilder
//                        .builder()
//                        .url(url)
//                        .username(username)
//                        .password(password)
//                        .build()
//                        .getPersistenceUnitName()
//        );
    }

    @Builder
    static class EntityManagerUrlBuilder {
        private String url;
        private String username;
        private String password;

        public String getPersistenceUnitName(){
            System.out.println("@@@@ H2LocalConfig : EntityManagerUrlNuilder");

            return url + ";user=" + username + ";password=" + password;
        }
    }
}