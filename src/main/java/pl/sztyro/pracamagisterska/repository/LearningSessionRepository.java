package pl.sztyro.pracamagisterska.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sztyro.pracamagisterska.model.LearningSession;

public interface LearningSessionRepository extends CrudRepository<LearningSession, Long> {
}
