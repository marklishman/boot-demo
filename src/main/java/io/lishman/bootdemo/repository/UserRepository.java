package io.lishman.bootdemo.repository;


import io.lishman.bootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLastNameContainingIgnoreCaseOrderByLastNameDesc(final String nameContains);


}
