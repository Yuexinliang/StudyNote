package com.jr.config;

import com.jr.entity.Emp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * className SpriongConfig
 * packageName com.jr.config
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/07 22:37
 */

@Configuration
public class SpringBeanConfig {

    @Bean(name = "emp")
    @Scope("prototype")
    public Emp createEmp() {
        return new Emp();
    }
}
