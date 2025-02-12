package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.adproject.Model.User;
import sg.edu.nus.adproject.Service.MailService;
import sg.edu.nus.adproject.Service.PasswordResetService;
import sg.edu.nus.adproject.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/getAllUsers")
    public List<User> getActiveUsers()
    {
        return userService.getActiveUsers();
    }

    @GetMapping("/getAllUsers2")
    public List<User> getActiveUsers2()
    {
        return userService.getAllUsers();
    }



    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId)
    {
        User user = userService.getUserById(userId);

        if(user == null)
        {
            Map<String, Object> feedbacks = new HashMap<>();
            feedbacks.put("status", 404);
            feedbacks.put("message", "User not found");

            return ResponseEntity.status(404).body(feedbacks);
        }
        return ResponseEntity.status(200).body(user);
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<?> deleteUserById(@PathVariable Long userId)
//    {
//        boolean successDeletion = userService.deleteUserById(userId);
//
//        if(successDeletion)
//        {
//            Map<String, Object> feedbacks = new HashMap<>();
//            feedbacks.put("status", 200);
//            feedbacks.put("message", "User deleted successfully");
//            return ResponseEntity.status(200).body(feedbacks);
//        }
//        return ResponseEntity.status(404).body("User not found");
//    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        boolean successDeletion = userService.deleteUserById(userId);

        if (successDeletion) {
            Map<String, Object> feedbacks = new HashMap<>();
            feedbacks.put("status", 200);
            feedbacks.put("message", "User cancelled successfully");
            return ResponseEntity.status(200).body(feedbacks);
        }
        return ResponseEntity.status(404).body("User not found");
    }


    //This is for single column update of the user
    //Can change according to biz logic
    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable Long userId ,@RequestBody Map<String, Object> userUpdateBody)
    {
        User user = userService.getUserById(userId);

        if(user == null)
        {
            return ResponseEntity.status(404).body("User not found for update");
        }

        String newUserName = userUpdateBody.get("username").toString();
        user.setUsername(newUserName);
        userService.saveUser(user);

        Map<String, Object> feedbacks = new HashMap<>();
        feedbacks.put("status", 200);
        feedbacks.put("message", "User updated successfully");

        return ResponseEntity.status(200).body(feedbacks);
    }

    @GetMapping("/forgot-password/{userId}")
    public ResponseEntity<String> requestPasswordReset(@PathVariable long userId) {

        User user = userService.getUserById(userId);

        if(user == null)
        {
            return ResponseEntity.status(404).body("User not found");
        }

        String email = user.getUsername();
        passwordResetService.sendResetPasswordEmail(email);
        return ResponseEntity.ok("Password reset link sent to your email.");
    }


//    @GetMapping("/forgot-password/{userId}")
//    public ResponseEntity<String> requestPasswordReset(@PathVariable long userId) {
//
//        User user = userService.getUserById(userId);
//
//        if(user == null)
//        {
//            return ResponseEntity.status(404).body("User not found");
//        }
//
//        String email = user.getUsername();
//        passwordResetService.sendResetPasswordEmail(email);
//        return ResponseEntity.ok("Password reset link sent to your email.");
//    }

    @PutMapping("/UpdatePassword")
    public ResponseEntity<String> updateUserPassword(@RequestParam Long userId, @RequestParam String newPassword) {
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        user.setPassword(newPassword);
        userService.saveUser(user);

        return ResponseEntity.ok("Password updated successfully");
    }




    @Autowired
    private MailService mailService; // Inject the mail service

    // Reset Password: Directly update the user's password and send an email with the new password
    @PostMapping("/reset-password/{userId}")
    public ResponseEntity<?> resetPassword(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        // Generate a random password (you can change the logic to make it more secure)
        String newPassword = generateRandomPassword();
        user.setPassword(newPassword);
        userService.saveUser(user);

        // Send the new password to the user's email
        String email = user.getEmail(); // Use email field instead of username
        String subject = "Your Password Has Been Reset";
        String body = "Dear " + user.getUsername() + ",\n\nYour password has been successfully reset. Your new password is: " + newPassword + "\n\nPlease login and change your password to something more secure.";

        // Send email
        mailService.sendEmail(email, subject, body);

        // Return response
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "Password has been reset successfully. Check your email for the new password.");

        return ResponseEntity.status(200).body(response);
    }

    // Generate a random password
    private String generateRandomPassword() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
}
