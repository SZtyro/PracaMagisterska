package pl.sztyro.pracamagisterska.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sztyro.pracamagisterska.model.LearningSession;
import pl.sztyro.pracamagisterska.model.User;
import pl.sztyro.pracamagisterska.repository.LearningSessionRepository;
import pl.sztyro.pracamagisterska.repository.UserRepository;
import pl.sztyro.pracamagisterska.service.AuthService;

import java.security.Principal;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/learning")
public class LearningController {


    LearningSessionRepository learningSessionRepository;
    UserRepository userRepository;

    @Autowired
    public LearningController(LearningSessionRepository learningSessionRepository, UserRepository userRepository) {
        this.learningSessionRepository = learningSessionRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    void saveLearningSession(@RequestBody String obj, Principal principal){
        JSONArray arr = new JSONArray(obj);
        LearningSession entity = new LearningSession();
        String mail = AuthService.getPrincipalMail(principal);
        User user;
        Optional<User> u = this.userRepository.findById(mail);
        if(u.isPresent()){
            user = u.get();
        }else{
            user = new User(mail);
            userRepository.save(user);

        }
        entity.setResults(obj);
        entity.setUser(user);
        learningSessionRepository.save(entity);
    }
}
