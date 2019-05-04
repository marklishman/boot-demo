package io.lishman.bootdemo.repository;

import io.lishman.bootdemo.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@Sql("/user-data.sql")
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("Given users exist in the database, then all these users are returned")
    void userList() {

        final List<User> users = repository.findAll();

        System.out.println(users);

        assertThat(users, hasSize(5));
    }

    @Test
    @DisplayName("Given a user exists in the database, then this user is returned")
    void userById() {

        final Optional<User> userOpt = repository.findById(3L);

        final User user = userOpt.get();

        assertThat(user.getLastName(), is(equalTo("Bauch")));
    }
}

