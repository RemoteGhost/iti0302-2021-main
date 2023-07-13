package ee.bookefy.controllers.theory.coins;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("coins")
@RestController
@NoArgsConstructor
public class CoinsApi {

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
    // Chris is a coin collector. (Numismatic is the official term)
    // ---
    // I have 1 000 different coins from the Ancient Greek to the Modern Estonian euros.
    // I used to travel a lot with my coin collection. Do you know how many times have I had an exhibition in Telliskivi?
    // I used to travel, not anymore. Have you heard of corona?
    // Anyways I want to develop a web page for my coins so myself and my friends can view my collection wherever they are.
    // I need to have like a list view with many coins. If I click on a single coin, I get to a detail page.
    // I want to add new coins, update existing ones and occasionally delete some.
    // There should be some filtering, by period and region.
    // And sorting, by value and dateAdded. By default it can sort with latest coins first.
    //
    //todo A first things first, please add necessary annotations to this class

    //todo B "I need to have like a list view with many coins"
    // create a method to query coins (plural)
    private final List<Coin> coins = new ArrayList<>();
    @GetMapping
    // Teen kõik tegevused siit peafunktsioonist
    public List<Coin> getCoins(@RequestParam(required = false) String period,
                                      @RequestParam(required = false) String region,
                                      @RequestParam(required = false) String sorting,
                                      @RequestParam(required = false) String order) {
        // filtreerib ära by period and region - G, H
        return coins.stream().filter(coin -> period == null || coin.getPeriod().equals(period))
                .filter(coin -> region == null || coin.getRegion().equals(region))
                .sorted(Comparator.comparingLong(coin -> {
                    // if sorting is not needed
                    if (sorting == null) {
                        return 0;
                    }
                    // latest coins
                    long orderByReverseOrder;
                    if (order != null && order.equals("desc")) orderByReverseOrder = -1;
                    else orderByReverseOrder = 1;

                    // sort by value and date - I, J
                    if (sorting.equals("value")) {
                        return Long.parseLong(coin.getValue()) * orderByReverseOrder;
                    }
                    if (sorting.equals("date")) {
                        return Long.parseLong(coin.getDateAdded()) * orderByReverseOrder;
                    }
                    // if sorting is not needed
                    return 0;
                })).collect(Collectors.toList());
    }

    //todo C "If I click on a single coin, I get to a detail page."
    // create a method to query a single coin
    @GetMapping("{id}")
    public Coin getCoin(@PathVariable Long id) {
        Optional<Coin> coinStream = coins.stream().filter(coin -> coin.getId().equals(id)).findAny();
        return coinStream.get();
    }

    //todo D "I want to add new coins"
    // create a method to save a new coin
    @PostMapping
    public void addNewCoin(@RequestBody Coin coin) {
        coins.add(coin);
    }

    //todo E "update existing ones"
    // create a method to update a coin
    @PutMapping
    public void updateCoin(@RequestBody Coin coin) {
        // go through coins and update the selected coin
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getId().equals(coin.getId())) {
                coins.set(i, coin);
                return;
            }
        }
    }

    //todo F "occasionally delete some"
    // create a method to delete a blog
    @DeleteMapping("{id}")
    public void deleteCoin(@PathVariable Long id) {
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getId().equals(id)) {
                coins.remove(i);
                return;
            }
        }
    }

    //todo G, H "There should be some filtering, by period and region"
    // G modify correct method to filter by period (ancient times, 18th century, 19th century)
    // H modify correct method to filter by region (americas, europe)
    // make sure existing functionality doesn't break

    //todo I-J "And sorting, by value and date added. By default it can sort with latest coins first."
    // I modify correct method to provide sorting by value and date added
    // J modify correct method to support sorting in ascending and descending order
    // in addition write some examples for how you will sort using your api (provide urls)

}
