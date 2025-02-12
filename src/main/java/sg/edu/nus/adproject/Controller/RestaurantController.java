package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.adproject.Model.Restaurant;
import sg.edu.nus.adproject.Service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/getAll")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

//    @GetMapping("/getTop20")
//    public List<Restaurant> getTop20Restaurants () {
//        return restaurantService.getTop20Restaurants();
//    }
    @GetMapping("/getTop5")
    public List<Restaurant> getTop5Restaurants () {
        return restaurantService.getTopfiveRestaurants();
    }
}
