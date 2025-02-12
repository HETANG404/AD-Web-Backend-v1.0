package sg.edu.nus.adproject.serviceImpls;



import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.Favourite;
import sg.edu.nus.adproject.Repository.FavouriteRepository;
import sg.edu.nus.adproject.Service.FavouriteService;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    private FavouriteRepository favouriteRepository;

    public List<Favourite> getAllFavourites()
    {

        return favouriteRepository.findAll();
    }
}
