package com.example.datasources.conf;
 
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
 
 
@Configuration
@MapperScan(basePackages = "com.example.datasources.mapper.students",sqlSessionFactoryRef = "studentDataSourceFactory")
public class MysqlDataSourceConfig {
    @Primary
    @Bean(name="studentDataSource")
    @ConfigurationProperties("spring.datasource.druid.student")
    public DataSource studentDataSource(){return DruidDataSourceBuilder.create().build();}
 
    @Primary
    @Bean(name="studentDataSourceFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("studentDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/students/*.xml"));
        return sessionFactoryBean.getObject();
    }
    @Primary
    @Bean(name = "studentTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("studentDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "studentSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("studentDataSourceFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}