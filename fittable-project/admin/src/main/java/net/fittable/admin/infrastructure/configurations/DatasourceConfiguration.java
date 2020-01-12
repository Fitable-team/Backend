package net.fittable.admin.infrastructure.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "net.fittable.admin.infrastructure.repositories", entityManagerFactoryRef = "entityManagerFactoryBean")
@EnableJpaAuditing
public class DatasourceConfiguration {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Autowired
    private EntityManagerFactoryBuilder builder;

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        Map<String, Object> properties = this.hibernateProperties
                .determineHibernateProperties(this.jpaProperties.getProperties(), new HibernateSettings());

        return this.builder
                .dataSource(this.dataSource)
                .properties(properties)
                .packages("net.fittable.domain")
                .persistenceUnit("default")
                .build();
    }
}
