package io.lishman.bootdemo.testing.annotations;

import io.lishman.bootdemo.service.UserService;
import io.lishman.bootdemo.testing.config.RepositoryMocks;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringJUnitConfig(classes = {
        UserService.class,
        RepositoryMocks.class
})
public @interface ServiceIntegrationTest { }
