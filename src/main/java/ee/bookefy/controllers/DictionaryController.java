package ee.bookefy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/definition")
@RequiredArgsConstructor
public class DictionaryController {

    @GetMapping()
    @RequestMapping
    public Object getMeaning(@RequestParam String word) {
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
        RestTemplate template = new RestTemplate();
        return template.getForObject(url, String.class);
    }
}
