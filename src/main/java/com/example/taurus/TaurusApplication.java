package com.example.taurus;

import com.example.taurus.repository.base.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
//@EnableCaching
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.example.taurus.repository",repositoryBaseClass = BaseRepositoryImpl.class)
public class TaurusApplication {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(TaurusApplication.class, args);
        String serverPort=context.getEnvironment().getProperty("server.port");
        log.info("Taurus started at http://localhost:"+serverPort);
    }

}
