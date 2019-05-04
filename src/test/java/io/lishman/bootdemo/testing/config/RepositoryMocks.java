package io.lishman.bootdemo.testing.config;

import io.lishman.bootdemo.repository.UserRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class RepositoryMocks {

    @MockBean
    private UserRepository userRepository;
}
