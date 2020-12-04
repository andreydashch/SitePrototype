package ua.site.prototype.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.site.prototype.entities.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findAllByName(String name);

    UserEntity save(UserEntity userEntity);
}
