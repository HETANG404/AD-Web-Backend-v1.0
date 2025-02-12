package sg.edu.nus.adproject.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import sg.edu.nus.adproject.Model.User;
import sg.edu.nus.adproject.Repository.UserRepository;
import sg.edu.nus.adproject.Service.InMemoryTokenService;
import sg.edu.nus.adproject.Service.PasswordResetService;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class PasswordResetController {
    private final PasswordResetService passwordResetService;
    private final InMemoryTokenService tokenService;
    private final UserRepository userRepository;

    public PasswordResetController(PasswordResetService passwordResetService, InMemoryTokenService tokenService, UserRepository userRepository) {
        this.passwordResetService = passwordResetService;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @GetMapping("/reset-password/{token}")
    public RedirectView resetPassword(@PathVariable String token) {

        String email = tokenService.validateResetToken(token);

        if (email == null) {
            return new RedirectView("/error?message=Invalid or expired token");
        }

        User user = userRepository.findByUsername(email);
        if (user == null) {
            return new RedirectView("/error?message=User not found");
        }
        return new RedirectView("http://localhost:5173");
    }


}