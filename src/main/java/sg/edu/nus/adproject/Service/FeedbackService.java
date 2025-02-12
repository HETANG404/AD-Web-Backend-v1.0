package sg.edu.nus.adproject.Service;

import sg.edu.nus.adproject.Model.Feedback;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getAllFeedbacks();

    List<Feedback>getFeedbacksByUserId(long userId);
    //To fetch the latest feedback
    Feedback getLatestFeedback();

//    Feedback addFeedback(long userId, String context);

    void deleteFeedbackById(Long feedbackId); // 删除反馈的方法

}