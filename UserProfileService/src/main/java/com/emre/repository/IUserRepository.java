package com.emre.repository;

import com.emre.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User,String> {

    Optional<User> findByAuthId(Long authId);

    List<User> findByFavoriteCategory(String categoryId);
}
