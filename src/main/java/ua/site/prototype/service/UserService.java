package ua.site.prototype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.site.prototype.entities.Folder;
import ua.site.prototype.entities.UserEntity;
import ua.site.prototype.repositories.UserRepository;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity findFirstByName(String name) {

        UserEntity userEntity = userRepository.findAllByName(name).get(0);
        userEntity.sortFolders();

        return userEntity;
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity testUser() {

        return new UserEntity("Alex");
    }


    public void uploadImages(String name, String[] imagesBase64) {
        UserEntity userEntity = findFirstByName(name);

        Arrays.stream(imagesBase64)
                .map(userEntity::createPhoto)
                .forEach(userEntity::addPhoto);

        save(userEntity);
    }

    public Boolean isCurrentDate(UserEntity userEntity) {
        String currentDate = userEntity.getCurrentDate();

        return userEntity.getDates().stream()
                .map(Folder::getDate)
                .anyMatch(date -> date.equals(currentDate));
    }
}
