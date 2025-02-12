package sg.edu.nus.adproject.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.adproject.Model.QueryLog;

public interface QueryLogRepository extends JpaRepository<QueryLog, Long> {
}
