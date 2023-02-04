package com.example.ex4;

import com.example.ex4.beans.Label;
import com.example.ex4.beans.UsersBean;
import com.example.ex4.repo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * create some beans witn various scopes using QUALIFIERS (method names)
 */
@Configuration
public class BeanConfiguration {

    /* BEAN using ctor - session scope */
    @Bean
    @ApplicationScope
    public UsersBean ActiveUsersBean() {
        UsersBean lst = new UsersBean();
        return lst;
    }

    /* singleton scope */
    @Bean
    @Scope("singleton")
    public Label autowiredFieldSingletonScope() {
        Label l =  new Label();
        l.setLabel("I'm SINGLETON Label bean");
        return l;
    }

    /* application scope */
    @Bean
    @ApplicationScope
    public Label autowiredFieldApplicationScope() {
        Label l =  new Label();
        l.setLabel("I'm APPLICATION Label bean");
        return l;
    }

    /* request scope */
    @Bean
    @RequestScope
    public Label autowiredFieldRequestScope() {
        Label l =  new Label();
        l.setLabel("I'm REQUEST Label bean");
        return l;
    }

    /* session scope */
    @Bean
    @SessionScope
    //@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Label sessionScope () {
        Label l =  new Label();
        l.setLabel("I'm SESSION Label bean");
        return l;
    }
    @Bean
    @SessionScope
    public User sessionUser () {
        return new User();
    }
}
