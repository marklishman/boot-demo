package io.lishman.bootdemo.service;

import io.lishman.bootdemo.entity.User;
import io.lishman.bootdemo.exception.UserResourceNotFoundException;
import io.lishman.bootdemo.repository.UserRepository;
import io.lishman.bootdemo.testing.annotations.ServiceIntegrationTest;
import io.lishman.bootdemo.testing.fixtures.UserFixture;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.lishman.bootdemo.testing.matchers.UserMatcher.matchesUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class UserServiceTest {

    private static final long USER_ID = 10L;

    @Nested
    @DisplayName("getAllUsers() method")
    @SpringJUnitConfig(classes = UserService.class)
    class GetAllUsers {

        @Autowired
        private UserService userService;

        @MockBean
        private UserRepository userRepository;

        @Test
        @DisplayName("Given there are no users, then an empty list is returned")
        void noUsersInList() {
            final List<User> actual = userService.getAllUsers();
            assertThat(actual, hasSize(0));
        }

        @Test
        @DisplayName("Given there is one user, then a list with one is returned")
        void singleUserInList() {
            final List<User> userEntities = Collections.singletonList(UserFixture.leanneGraham());
            given(userRepository.findAll()).willReturn(userEntities);

            final List<User> actualUsers = userService.getAllUsers();

            assertThat(actualUsers, hasSize(1));
            assertThat(actualUsers, Matchers.contains(matchesUser(UserFixture.leanneGraham())));
        }

        @Test
        @DisplayName("Given there are several users, then a list with all of them is returned")
        void usersInList() {
            final List<User> userEntities = List.of(UserFixture.leanneGraham(), UserFixture.nicholasRunolfsdottir());
            given(userRepository.findAll()).willReturn(userEntities);

            final List<User> actualUsers = userService.getAllUsers();

            assertThat(actualUsers, hasSize(2));
            assertThat(actualUsers, Matchers.contains(
                    matchesUser(UserFixture.leanneGraham()),
                    matchesUser(UserFixture.nicholasRunolfsdottir()))
            );
        }
    }

    @Nested
    @DisplayName("getAllUserById(Long) method")
    @ServiceIntegrationTest
    class GetUserBy {

        @Autowired
        private UserService userService;

        @Test
        @DisplayName("Given there is no user with the id, then an exception is thrown")
        void userNotFoundById(@Autowired final UserRepository userRepository) {
            given(userRepository.findById(USER_ID)).willReturn(Optional.empty());

            UserResourceNotFoundException thrown =
                    assertThrows(UserResourceNotFoundException.class,
                            () -> userService.getUserById(USER_ID),
                            "Expected getUserById() to throw, but it didn't");

            assertThat(thrown.getMessage(), is(equalTo(String.format("User %s not found", USER_ID))));
        }

        @Test
        @DisplayName("Given there is a user with the id, then this user is returned")
        void userFoundById(@Autowired final UserRepository userRepository) {
            given(userRepository.findById(USER_ID)).willReturn(Optional.of(UserFixture.nicholasRunolfsdottir()));

            final User actualUser = userService.getUserById(USER_ID);

            assertThat(actualUser, matchesUser(UserFixture.nicholasRunolfsdottir()));
        }
    }
}
