package com.demo.userService.repository;

import com.demo.userService.entity.UserDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDemo, Long> {
}
