package pl.sztyro.pracamagisterska.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.sztyro.pracamagisterska.model.User;
import pl.sztyro.pracamagisterska.model.Word;

public interface WordRepository extends CrudRepository<Word, String> {
}
