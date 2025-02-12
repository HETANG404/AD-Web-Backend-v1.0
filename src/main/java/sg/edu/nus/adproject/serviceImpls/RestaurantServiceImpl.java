package sg.edu.nus.adproject.serviceImpls;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.Favourite;
import sg.edu.nus.adproject.Model.Restaurant;
import sg.edu.nus.adproject.Repository.FavouriteRepository;
import sg.edu.nus.adproject.Repository.RestaurantRepository;
import sg.edu.nus.adproject.Service.RestaurantService;

import java.util.*;
import java.util.stream.Collectors;



@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll(); // Call to JpaRepository's findAll
    }

    // Top 20 Restaurants
//    @Override
//    public List<Restaurant> getTop20Restaurants() {
//        List<Restaurant> restaurants = restaurantRepository.findAll(); // Fetch restaurants
//
//        // Sorting using a comparator based on rating
//        return restaurants.stream()
//                .sorted(Comparator.comparing(Restaurant::getUserRatingsTotal).reversed()) // Descending order // Change getUserRatingsTotal to restaurantID_ from favourite
//                .limit(20)
//                .collect(Collectors.toList());
//    }

    //Top 5 restaurants from Favourite
    @Autowired
    private FavouriteRepository restaurantFavouriteRepository;

    @Override
    public List<Restaurant> getTopfiveRestaurants() {
        List<Favourite> favourites = restaurantFavouriteRepository.findAll();

        List<Restaurant> allRestaurants = new ArrayList<>();

        for (Favourite favourite : favourites) {
            allRestaurants.add(favourite.getRestaurant());
        }

        Map<Long, Integer> hashRestaurant = new HashMap<>();

        for (Restaurant restaurant : allRestaurants) {
            if (!hashRestaurant.containsKey(restaurant.getId())) {
                hashRestaurant.put(restaurant.getId(), 1);
            } else {
                hashRestaurant.put(restaurant.getId(), hashRestaurant.get(restaurant.getId()) + 1);
            }
        }

        // Sort by frequency (highest first) and get top 5 IDs
        List<Long> top5RestaurantIds = hashRestaurant.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Sort by count descending
                .limit(5) // Get top 5
                .map(Map.Entry::getKey) // Extract restaurant IDs
                .toList();

        List<Restaurant> top5Restaurants = restaurantRepository.findAllById(top5RestaurantIds);

        return top5Restaurants;

    }
}
