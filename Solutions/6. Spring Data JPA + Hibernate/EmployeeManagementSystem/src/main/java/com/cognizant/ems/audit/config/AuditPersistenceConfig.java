package com.cognizant.ems.audit.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.Map;

// Docx 4 - Hands on 9: second, independent datasource (audit trail), separate from the
// primary employee/department datasource - demonstrates managing multiple datasources
@Configuration
@EnableJpaRepositories(
        basePackages = "com.cognizant.ems.audit.repository",
        entityManagerFactoryRef = "auditEntityManagerFactory",
        transactionManagerRef = "auditTransactionManager"
)
public class AuditPersistenceConfig {

    @Bean
    @ConfigurationProperties("app.datasource.audit")
    public DataSourceProperties auditDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource auditDataSource() {
        return auditDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean auditEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        // see PrimaryPersistenceConfig.primaryEntityManagerFactory for why ddl-auto is set here directly
        return builder
                .dataSource(auditDataSource())
                .packages("com.cognizant.ems.audit.model")
                .properties(Map.of("hibernate.hbm2ddl.auto", "update"))
                .persistenceUnit("audit")
                .build();
    }

    @Bean
    public PlatformTransactionManager auditTransactionManager(
            @Qualifier("auditEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
