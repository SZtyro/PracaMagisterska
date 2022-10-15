package pl.sztyro.pracamagisterska.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sztyro.pracamagisterska.model.BiometryChunk;
import pl.sztyro.pracamagisterska.model.MembershipFunction;
import pl.sztyro.pracamagisterska.model.User;
import pl.sztyro.pracamagisterska.repository.BiometryChunkRepository;
import pl.sztyro.pracamagisterska.repository.MembershipFunctionRepository;

import java.util.List;

@Service
public class BiometryChunkService {

    public BiometryChunkRepository biometryChunkRepository;
    public MembershipFunctionRepository membershipFunctionRepository;

    @Autowired
    public BiometryChunkService(
            BiometryChunkRepository biometryChunkRepository,
            MembershipFunctionRepository membershipFunctionRepository) {
        this.biometryChunkRepository = biometryChunkRepository;
        this.membershipFunctionRepository = membershipFunctionRepository;
    }

    public BiometryChunk getByPairAndUser(String pair, User user){
        return biometryChunkRepository.getByPairAndUser(pair,user);
    }

    public void saveChunk(BiometryChunk biometryChunk){
        biometryChunkRepository.save(biometryChunk);
    }

    public void deleteFunction(MembershipFunction membershipFunction){
        this.membershipFunctionRepository.delete(membershipFunction);
    }

    public List<BiometryChunk> getUserChunks(User user){
        return this.biometryChunkRepository.getUserChunks(user);
    }

}
