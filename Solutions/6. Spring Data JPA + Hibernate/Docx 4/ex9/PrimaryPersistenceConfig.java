package com.cognizant.ems.primary.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.Map;

// Docx 4 - Hands on 9: primary datasource (employees/departments). Marked @Primary so that plain
// @Transactional / @Autowired DataSource usage elsewhere in the app defaults to this one.
@Configuration
@EnableJpaRepositories(
        basePackages = "com.cognizant.ems.primary.repository",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryPersistenceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("app.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource primaryDataSource() {
        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        // spring.jpa.hibernate.ddl-auto is normally translated to hibernate.hbm2ddl.auto by
        // Spring Boot's own auto-configured entityManagerFactory bean; since that bean is backed
        // off in favor of this manually-built one, the translation has to happen here instead.
        return builder
                .dataSource(primaryDataSource())
                .packages("com.cognizant.ems.primary.model")
                .properties(Map.of("hibernate.hbm2ddl.auto", "update"))
                .persistenceUnit("primary")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
