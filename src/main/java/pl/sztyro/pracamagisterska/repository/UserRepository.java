package pl.sztyro.pracamagisterska.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sztyro.pracamagisterska.model.User;

public interface UserRepository extends CrudRepository<User, String> {
}
