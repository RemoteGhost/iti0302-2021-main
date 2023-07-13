package ee.bookefy.controllers.theory.skateboards;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/skateboards")
@RestController
public class SkateboardsApi {

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
    // Fred has a Skateboard shop in Telliskivi.
    // ---
    // Hi. I'm Fred the hipster. I studied law and music, but now I'm selling and making skateboards. Wild life!
    // Our business has grown and I need some help automating it.
    // Currently our inventory is managed by pen and paper. You need to make it better.
    // This is what I need:
    // - an overview of the skateboards we sell
    // - I want to know which ones are in stock and which ones are new (vs used)
    // - I want to order by the price or by the name alphabetically
    // and a
    // - page for each skateboard where I can see it's info
    // - button to add a new skateboard
    // - button to update existing skateboard
    // - button to delete skateboard

    //todo A first things first, please add necessary annotations to this class

    //todo B "an overview of the skateboards we sell"
    // create a method to query skateboards (plural)

    private List<Skateboard> skateboards = new ArrayList<>();

    public List<Skateboard> getSkateboards(@RequestParam(required = false) String inStock,
                                           @RequestParam(required = false) String condition,
                                           @RequestParam(required = false) String sortType,
                                           @RequestParam(required = false) String sortOrder) {
        List<Skateboard> fittingSkateboards = skateboards.stream()
                .filter(skateboard -> inStock == null || skateboard.getInStock().equals(inStock)) // "in stock", "out of stock"
                .filter(skateboard -> condition == null || skateboard.getCondition().equals(condition)) // "new", "used", "broken"
                .sorted(Comparator.comparingLong(skateboard -> {
                    if (sortType == null) {
                        return 0;
                    }
                    long orderByReverseOrder = 1;
                    if (sortOrder != null && sortOrder.equals("descending")) {
                        orderByReverseOrder = -1;
                    }
                    if (sortType.equals("price")) {
                        return Long.parseLong(skateboard.getPrice()) * orderByReverseOrder;
                    }
                    return 0;
                })).collect(Collectors.toList());

        if (sortType != null && sortType.equals("name")) {
            if (sortOrder != null && sortOrder.equals("descending")) {
                return fittingSkateboards.stream().sorted(Comparator.comparing(Skateboard::getName).reversed())
                        .collect(Collectors.toList());
            } else { // sortOrder for sortType "name" is null or explicitly "ascending", so this is the default
                return fittingSkateboards.stream().sorted(Comparator.comparing(Skateboard::getName))
                        .collect(Collectors.toList());
            }
        }
        return fittingSkateboards;
    }

    //todo C "page for each skateboard where I can see it's info"
    // create a method to query a single skateboard

    @GetMapping("/{id}")
    public Skateboard getSkateboard(@PathVariable Long id) {
        for (Skateboard skateboard : skateboards) {
            if (skateboard.getId().equals(id)) {
                return skateboard;
            }
        }
        return null;
    }

    //todo D "button to add a new skateboard"
    // create a method to save a new skateboard

    @PostMapping
    public Skateboard addNewSkateboard(@RequestBody Skateboard skateboard) {
        skateboards.add(skateboard);
        return skateboard;
    }

    //todo E "button to update existing skateboard"
    // create a method to update a skateboard

    @PutMapping
    public void updateSkateboard(@RequestBody Skateboard skateboard) {
        for (int i = 0; i < skateboards.size(); i++) {
            if (skateboards.get(i).getId().equals(skateboard.getId())) {
                skateboards.set(i, skateboard);
                return;
            }
        }
    }

    //todo F "button to delete skateboard"
    // create a method to delete a skateboard

    @DeleteMapping("/{id}")
    public void deleteSkateboard(@PathVariable Long id) {
        for (int i = 0; i < skateboards.size(); i++) {
            if (skateboards.get(i).getId().equals(id)) {
                skateboards.remove(i);
                return;
            }
        }
    }

    //todo G, H "I want to know which ones are in stock and which ones are new (vs used)"
    // G modify correct method to filter whether the skateboard is in stock or out of stock
    // H modify correct method to filter by condition (new, used, broken)
    // make sure existing functionality doesn't break

    //todo I-J "I want to order by the price or by the name alphabetically"
    // I modify correct method to provide sorting by price and name
    // J modify correct method to support sorting in ascending and descending order
    // in addition write some examples for how you will sort using your api (provide urls)

}
