package sg.edu.nus.adproject.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.adproject.Model.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {


}
