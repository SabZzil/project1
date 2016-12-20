package org.sabzzil;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.sabzzil.interceptor.AuthInterceptor;
import org.sabzzil.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan(value = {"org.sabzzil.mapper"})
public class Project1Application {

	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AuthInterceptor authInterceptor;
	
	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		
		Resource[] res = new PathMatchingResourcePatternResolver().
							getResources("classpath:mappers/*Mapper.xml");
		
		sessionFactory.setMapperLocations(res);
				
		return sessionFactory.getObject();
	}

	@Bean
	public String uploadPath() throws Exception {
		String str = "C:\\Users\\SabZzil\\Desktop\\WEB\\upload";
		return str;
	}
	
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/loginPost");
                registry.addInterceptor(authInterceptor)
                .addPathPatterns("/board/write")
                .addPathPatterns("/board/modifyA")
                .addPathPatterns("/board/delete");
                
            }
        };
    }
}
