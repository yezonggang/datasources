package com.example.datasources.conf;
 
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
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


/**
 * @author y25958
 */
@Configuration
@Slf4j
@MapperScan(basePackages = "com.example.datasources.mapper.teachers",sqlSessionFactoryRef = "teacherDataSourceFactory")
public class MppDataSourceConfigByDruid {


    @Primary
    @Bean(name="teacherDataSource")
    @ConfigurationProperties("spring.datasource.druid.teacher")
    public DataSource teacherDataSource(){return DruidDataSourceBuilder.create().build();}
 
    @Primary
    @Bean(name="teacherDataSourceFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("teacherDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/teachers/*.xml"));
        return sessionFactoryBean.getObject();
    }
    @Bean(name = "teacherTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("teacherDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "teacherSqlSessionTemplate" )
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("teacherDataSourceFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

 
}