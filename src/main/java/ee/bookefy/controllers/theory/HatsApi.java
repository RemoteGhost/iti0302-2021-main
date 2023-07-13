package ee.bookefy.controllers.theory;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hats")
@RequiredArgsConstructor
public class HatsApi {
    private Hat hat1 = new Hat(0L, "blue", "nike", "123", "casual", "M");
    private Hat hat2 = new Hat(1L, "red", "adidas", "312", "gangseter", "S");
    private Hat hat3 = new Hat(2L, "green", "wands", "112", "fancy", "XL");
    private List<Hat> hats = Arrays.asList(hat1, hat2, hat3);
    //todo Welcome to the theory!
    // To start put these classes into my.project.controller.theory so you can check these using swagger or browser
    // Each team member has to do only 1 assignment and commit/push it to your repository.
    // (So 2 people - 2 assignments, 3 people - 3 assignments, 4 people - 4 assignments).
    // Make sure to commit under your user otherwise the points won't count. Each team member has to score at least 50%.
    // Don't add unnecessary code (no need for services or database).
    // We are doing mock-api design. I am grading urls and structure of the methods.
    // It should still work, i.e I can access this api from swagger or browser.
    // A good source for learning about proper API design is https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-design

    //todo The Story
    // Mad Hatter. Another Telliskivi butique. Hat know-how link (:  https://www.youtube.com/watch?v=6lYuL_kz9Ak
    // ---
    // Hey, I am Max Hatter. I'm from the states (US). I played basketball in my youth and I fell in love with hats.
    // I came to Estonia few years ago and started a business selling hats. I have a busy shop in Telliskivi region.
    // However during winter-time our sales are slow, so I am thinking of expanding our online presence.
    // I think we need to do something on the web. Like a shop or gallery or both. Connect it to tik-tok, instagram, facebook.
    // Do the online thing. Can you help?
    // I guess I need like a landing page where you can see many hats.
    // And each hat has some info, so once you click on it, it displays it.
    // And then there are buttons for saving and updating when I have new hats or some info was wrong.
    // Oh, and some way to remove hats.
    // For landing page it is important that the hats can be filtered by style and colour.
    // Also I'd like to order them by size and price.

    //todo A first things first, please add necessary annotations to this class

    //todo B "I guess I need like a landing page where you can see many hats"
    // create a method to query hats (plural)
    //todo G, H "For landing page it is important that the hats can be filtered by style and colour."
    // G modify correct method to filter by hat style (59fifty, 9twenty, cap, etc)
    // G modify correct method to filter by hat colour (red, blue, etc)
    // make sure existing functionality doesn't break

    //todo I-J "Also I'd like to order them [the hats] by size and price."
    // I modify correct method to provide sorting by size and price
    // J modify correct method to support sorting in ascending and descending order
    // in addition write some examples for how you will sort using your api (provide urls)
    @GetMapping()
    public List<Hat> getHats(@RequestParam(required = false) String style,
                             @RequestParam(required = false) String colour,
                             @RequestParam(required = false) String sort,
                             @RequestParam(required = false) String order){
        System.out.println("waats giug on my duuds");
        return hats.stream().filter(hat -> style == null || hat.getStyle().equals(style))
                .filter(hat -> colour == null || hat.getStyle().equals(colour))
                .sorted(Comparator.comparingLong(hat -> {
                    if (sort == null) return 0;
                    long orderBy = (order != null && order.equals("desc")) ? -1 : 1;
                    if (sort.equals("size")) {
                        return Long.parseLong(hat.getSize()) * orderBy;
                    }
                    if (sort.equals("price")) {
                        return Long.parseLong(hat.getPrice()) * orderBy;
                    }
                    return 0;

                })).collect(Collectors.toList());
    }
    //todo C "And each hat has some info, so once you click on it, it displays it"
    // create a method to query a single hat
    @GetMapping("/{id}")
    public Hat getHatById(@PathVariable Long id) {
        Optional<Hat> theHat = hats.stream().filter(hat -> Objects.equals(hat.getId(), id)).findFirst();
            return theHat.get();
    }
    //todo D "And then there are buttons for saving [..] when I have new hats [..]"
    // create a method to save a new hat
    @PostMapping
    public Hat addHat(@RequestBody Hat hat) {
        hats.add(hat);
        return hat;
    }
    //todo E "And then there are buttons for [..] updating when [..] some info was wrong"
    // create a method to update a hat
    @PutMapping
    public Hat updateHat(@RequestBody Hat hat) {
        Optional<Hat> theHat = hats.stream().filter(listhat -> listhat.getId().equals(hat.getId())).findFirst();
        hats.remove(theHat.get());
        hats.add(hat);
        return hat;

    }
    //todo F "Oh, and some way to remove hats."
    // create a method to delete a hat
    @DeleteMapping("/{id}")
    public void removeHat(@PathVariable Long id) {
        Optional<Hat> theHat = hats.stream().filter(listhat -> listhat.getId().equals(id)).findFirst();
        hats.remove(theHat.get());
    }

    //todo G, H "For landing page it is important that the hats can be filtered by style and colour."
    // G modify correct method to filter by hat style (59fifty, 9twenty, cap, etc)
    // G modify correct method to filter by hat colour (red, blue, etc)
    // make sure existing functionality doesn't break

    //todo I-J "Also I'd like to order them [the hats] by size and price."
    // I modify correct method to provide sorting by size and price
    // J modify correct method to support sorting in ascending and descending order
    // in addition write some examples for how you will sort using your api (provide urls)
}
