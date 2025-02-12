package sg.edu.nus.adproject.Service;



import sg.edu.nus.adproject.Model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();

    List<Restaurant> getTopfiveRestaurants();
}