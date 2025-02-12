package sg.edu.nus.adproject.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.adproject.Model.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
}
