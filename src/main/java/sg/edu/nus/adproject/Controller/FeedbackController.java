package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.adproject.Model.Feedback;
import sg.edu.nus.adproject.Service.FeedbackService;
import sg.edu.nus.adproject.exception.ResourceNotFoundException;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * ✅ Get latest 50 feedbacks for a specific user, sorted by date
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Map<String, String>>> getFeedbacksByUserId(@PathVariable("userId") long userId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByUserId(userId);

        // Convert to formatted response
        List<Map<String, String>> formattedFeedbacks = feedbacks.stream()
                .map(fb -> Map.of(
                        "time", fb.getTime().toString(),
                        "context", fb.getContext(),
                        "username", fb.getUser().getUsername()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(formattedFeedbacks);
    }

    /**
     * ✅ Get latest 50 feedbacks (all users), sorted by date
     */
    @GetMapping("/getAllFeedbacks")
    public ResponseEntity<List<Map<String, String>>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();

        // Sort feedbacks first by date (descending), then by time (descending within the same day)
        List<Map<String, String>> formattedFeedbacks = feedbacks.stream()
                .sorted(Comparator.comparing(Feedback::getTime).reversed()) // Sort by date-time descending
                .limit(50) // Limit to top 50 results
                .map(fb -> Map.of(
                        "time", fb.getTime().toString(),
                        "context", fb.getContext(),
                        "username", fb.getUser().getUsername(),
                        "userid", String.valueOf(fb.getUser().getId()),
                        "feedbackId", String.valueOf(fb.getId())
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(formattedFeedbacks);
    }
    /**
     * ✅ Get the latest feedback entry based on time
     */
    @GetMapping("/latest")
    public ResponseEntity<Map<String, String>> getLatestFeedback() {
        Feedback feedback = feedbackService.getLatestFeedback();

        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        // Format response
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a"); // Format to "09:30 am"

        Map<String, String> formattedFeedback = Map.of(
                "time", feedback.getTime() != null ? feedback.getTime().toLocalTime().format(timeFormatter) : "N/A",
                "content", feedback.getContext() != null ? feedback.getContext() : "No content",
                "userid", (feedback.getUser() != null && feedback.getUser().getId() != null) ? String.valueOf(feedback.getUser().getId()) : "N/A",
                "username", feedback.getUser() != null ? feedback.getUser().getUsername() : "Anonymous"
        );

        return ResponseEntity.ok(formattedFeedback);
    }

    /**
     * ✅ 根据 feedbackId 删除反馈
     */
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("feedbackId") Long feedbackId) {
        try {
            feedbackService.deleteFeedbackById(feedbackId); // 调用服务层删除方法
            return ResponseEntity.noContent().build(); // 返回204 No Content
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build(); // 如果没有找到该反馈，返回404 Not Found
        }
    }

}


