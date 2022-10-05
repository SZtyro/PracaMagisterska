package pl.sztyro.pracamagisterska.rest;

import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BaseController {

    @PostMapping("/biometry")
    void checkBehavior(@RequestBody Object array){
        System.out.println(array.toString());
    }
}
