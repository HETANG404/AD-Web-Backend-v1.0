package sg.edu.nus.adproject.Service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.User;
import sg.edu.nus.adproject.Repository.UserRepository;


@Service
public class PasswordResetService {
    private final UserRepository userRepository;
    private final InMemoryTokenService tokenService;
    private final JavaMailSender mailSender;

    public PasswordResetService(UserRepository userRepository, InMemoryTokenService tokenService, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.mailSender = mailSender;
    }

    public void sendResetPasswordEmail(String email) {
        User user = userRepository.findByUsername(email);
        String resetToken = tokenService.generateResetToken(email);
        String resetLink = "http://localhost:8080/auth/reset-password/" + resetToken;

        // Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Click the link to reset your password: " + resetLink);
        mailSender.send(message);
    }
}