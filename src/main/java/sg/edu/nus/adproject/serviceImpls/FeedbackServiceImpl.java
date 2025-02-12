package sg.edu.nus.adproject.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.Feedback;
import sg.edu.nus.adproject.Model.User;
import sg.edu.nus.adproject.Repository.FeedbackRepository;
import sg.edu.nus.adproject.Repository.UserRepository;
import sg.edu.nus.adproject.Service.FeedbackService;
import sg.edu.nus.adproject.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class FeedbackServiceImpl implements FeedbackService { // ✅ Correctly implements FeedbackService

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findTop50ByOrderByTimeDesc();
    }

    @Override
    public List<Feedback> getFeedbacksByUserId(long userId) {  // ✅ Correctly overriding
        return feedbackRepository.findTop50ByUserIdOrderByTimeDesc(userId);
    }

    // Retrieve the most recent feedback_new
    public Feedback getLatestFeedback() {
        return feedbackRepository.findTopByOrderByTimeDesc();
    }

    @Override
    public void deleteFeedbackById(Long feedbackId) {
        // 检查反馈是否存在
        Optional<Feedback> feedback = feedbackRepository.findById(feedbackId);
        if (feedback.isPresent()) {
            feedbackRepository.deleteById(feedbackId); // 从数据库中删除反馈
        } else {
            throw new ResourceNotFoundException("Feedback with id " + feedbackId + " not found.");
        }
    }
}