package com.team.mybook.data.repository;

import com.team.mybook.data.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
