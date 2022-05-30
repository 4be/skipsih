package com.sekoding.example.demo.repository;

import com.sekoding.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByIsaktif(Boolean bool);

    Optional<User> findByUsernameOrEmail(String username, String email);

    User findUserByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findUserByNik(String nik);

    List<User> findUserByNikManager(String nikManager);

}
