package sg.edu.nus.adproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.nus.adproject.Model.Feedback;
import sg.edu.nus.adproject.Model.User;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);

    //.... Fetch the most recent feedback based on time_ new
    Feedback findTopByOrderByTimeDesc();
    //...
    List <Feedback> findTop50ByOrderByTimeDesc();

    // Retrieve feedback for a specific user (last 50 entries)
    List<Feedback> findTop50ByUserIdOrderByTimeDesc(Long userId);
}