package pl.sztyro.pracamagisterska.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sztyro.pracamagisterska.model.Word;
import pl.sztyro.pracamagisterska.service.WordService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/word")
public class WordController {

    @Autowired
    WordService wordService;

    @GetMapping
    public Iterable<Word> getAll(){
        List<Word> list = new ArrayList<>();
        for (Word word : wordService.getAllWords()) {
            list.add(word);
        }
        Collections.shuffle(list);
        return list;
    }
}
