package io.lishman.bootdemo.testing.config;

import io.lishman.bootdemo.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class ServiceMocks {

    @MockBean
    private UserService userService;
}
