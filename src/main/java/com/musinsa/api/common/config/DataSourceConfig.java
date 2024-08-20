package com.musinsa.api.common.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.musinsa.api.common.mapper", sqlSessionTemplateRef = "sqlSession")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties searchDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("dataSource")
    @ConfigurationProperties("spring.datasource.config")
    public HikariDataSource dataSource() {
        return searchDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext,
            @Qualifier("dataSource") DataSource searchDataSource) throws Exception {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(searchDataSource);
        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
        sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));

        return sessionFactoryBean.getObject();
    }

    @Bean("sqlSession")
    public SqlSessionTemplate sqlSession(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
