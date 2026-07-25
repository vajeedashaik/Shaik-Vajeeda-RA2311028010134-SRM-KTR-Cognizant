package com.cognizant.ems.primary.config;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Employee.department is FetchType.LAZY (see Employee.java); without this module Jackson tries to
// serialize the Hibernate proxy's own internal fields and fails with InvalidDefinitionException.
// FORCE_LAZY_LOADING makes Jackson initialize the proxy (relying on open-in-view to still have a
// session available) instead of silently writing null.
@Configuration
public class JacksonConfig {

    @Bean
    public Hibernate6Module hibernate6Module() {
        Hibernate6Module module = new Hibernate6Module();
        module.enable(Hibernate6Module.Feature.FORCE_LAZY_LOADING);
        return module;
    }
}
