package pl.sztyro.pracamagisterska.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sztyro.pracamagisterska.model.BiometryChunk;
import pl.sztyro.pracamagisterska.model.User;

import java.util.List;

public interface BiometryChunkRepository extends CrudRepository<BiometryChunk, Long> {
    @Query("from BiometryChunk where pair = :pair and appUser = :user")
    BiometryChunk getByPairAndUser(String pair, User user);

    @Query("from  BiometryChunk where appUser = :user")
    List<BiometryChunk> getUserChunks(User user);
}
