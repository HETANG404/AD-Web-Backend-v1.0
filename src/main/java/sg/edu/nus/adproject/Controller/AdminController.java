package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
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

        // Check if the username exists
        if (!adminService.userExists(username)) {
            response.put("success", false);
            response.put("message", "Username does not exist!");
            return ResponseEntity.status(401).body(response);
        }

        // Check if the password is correct
        if (!adminService.authenticate(username, password)) {
            response.put("success", false);
            response.put("message", "Incorrect password!");
            return ResponseEntity.status(401).body(response);
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

/**
 * Check if the admin is logged in
 */
//@GetMapping("/status")
//public ResponseEntity<Map<String, Object>> checkLoginStatus(HttpSession session) {
//    Map<String, Object> response = new HashMap<>();
//
//    if (session.getAttribute("loggedInUser") != null) {
//        response.put("loggedIn", true);
//        response.put("username", session.getAttribute("loggedInUser"));
//    } else {
//        response.put("loggedIn", false);
//        response.put("message", "User not logged in.");
//    }
//
//    return ResponseEntity.ok(response);
//}


//...................
    /**
     * Login API
     */
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
//        String username = request.get("username");
//        String password = request.get("password");
//
//        boolean authenticated = adminService.authenticate(username, password);
//
//        Map<String, Object> response = new HashMap<>();
//        if (authenticated) {
//            response.put("success", true);
//            response.put("redirectUrl", "/");
//            return ResponseEntity.ok(response);
//        } else {
//            response.put("success", false);
//            response.put("message", "Invalid username or password.");
//            return ResponseEntity.status(401).body(response);
//        }
//    }
//
//    /**
//     * Logout API
//     */
//    @PostMapping("/logout")
//    public ResponseEntity<Map<String, Object>> logout() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("success", true);
//        response.put("message", "Logged out successfully.");
//        return ResponseEntity.ok(response);
//    }
//}

//    @PostMapping("/login")
//    public ResponseEntity<Admin> login(@RequestBody Map<String, String> credentials) {
//        String username = credentials.get("username");
//        String password = credentials.get("password");
//
//        Admin admin = adminService.authenticate
//
//    }

