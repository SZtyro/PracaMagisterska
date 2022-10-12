package pl.sztyro.pracamagisterska.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.sztyro.pracamagisterska.model.BiometryChunk;
import pl.sztyro.pracamagisterska.model.MembershipFunction;
import pl.sztyro.pracamagisterska.model.User;

public interface MembershipFunctionRepository extends CrudRepository<MembershipFunction, Long> {
}
