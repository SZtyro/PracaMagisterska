package pl.sztyro.pracamagisterska.service;

import org.springframework.stereotype.Service;
import pl.sztyro.pracamagisterska.model.Word;
import pl.sztyro.pracamagisterska.repository.WordRepository;

@Service
public class WordService {

    WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Iterable<Word> getAllWords(){
        return this.wordRepository.findAll();
    }

}
