package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.adproject.Model.Admin;
import sg.edu.nus.adproject.Service.AdminService;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllAdmins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    /**
     * Login API (Stores user session)
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request, HttpSession session) {
        String username = request.get("username");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if (username == null || username.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Username is required");
            return ResponseEntity.status(400).body(response);
        }

        if (password == null || password.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Password is required");
            return ResponseEntity.status(400).body(response);
        }


        boolean userExists = adminService.userExists(username);
        boolean authenticated = adminService.authenticate(username, password);


        if (!authenticated || !userExists) {
            if (authenticated) {
                response.put("success", false);
                response.put("message", "Incorrect password!");  // 统一错误信息
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else if (userExists) {
                response.put("success", false);
                response.put("message", "Incorrect password!");  // 统一错误信息
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }


        // Login successful
        session.setAttribute("loggedInUser", username);
        response.put("success", true);
        response.put("redirectUrl", "/"); // Redirect URL after successful login
        return ResponseEntity.ok(response);
    }

    /**
     * Logout API
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        session.invalidate();// Invalidate session on logout

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Logged out successfully.");
        return ResponseEntity.ok(response);
    }
}
//......
