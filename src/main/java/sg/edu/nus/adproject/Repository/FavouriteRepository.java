package sg.edu.nus.adproject.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.adproject.Model.Favourite;

public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
}
