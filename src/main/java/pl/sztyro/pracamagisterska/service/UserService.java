package pl.sztyro.pracamagisterska.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sztyro.pracamagisterska.model.User;
import pl.sztyro.pracamagisterska.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    public UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getCurrentUserByEmail(String email){
        return userRepository.findById(email);
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }


}
