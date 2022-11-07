package com.demo.productmanagement.config;


import com.demo.productmanagement.dao.CategoryMapper;
import com.demo.productmanagement.dao.ImageMapper;
import com.demo.productmanagement.dao.ProductCategoryMapper;
import com.demo.productmanagement.dao.ProductMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.util.Objects;

@Configuration
@Profile({"dev","default"})
public class DevMyBatisConfig implements EnvironmentAware {

    Environment environment;
    // Tạo bean dataSource
    @Bean
    public DriverManagerDataSource dataSource() throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        Properties properties = new Properties();
//        InputStream user_props = this.getClass()
//                .getResourceAsStream("/application-dev.properties");
//        properties.load(user_props);
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setDriverClassName(
                Objects.requireNonNull(environment.getProperty("spring.datasource.driver-class-name"))
        );
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    // đọc thông tin file cấu hình MyBatis
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        Resource resource = new ClassPathResource("SqlMapConfig.xml");
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory;
    }

    // scan tất cả những mapper
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.example.user_management.user.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }

    @Bean (name = "productMapper")
    public ProductMapper productMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(ProductMapper.class);
    }

    @Bean (name = "productCategoryMapper")
    public ProductCategoryMapper productCategoryMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(ProductCategoryMapper.class);
    }
    @Bean (name = "imageMapper")
    public ImageMapper imageMapperMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(ImageMapper.class);
    }

    @Bean (name = "categoryMapper")
    public CategoryMapper categoryMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(CategoryMapper.class);
    }
}
