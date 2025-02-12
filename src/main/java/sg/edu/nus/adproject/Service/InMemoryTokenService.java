package sg.edu.nus.adproject.Service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryTokenService {
    private final Map<String, String> resetTokens = new ConcurrentHashMap<>();
    private final Map<String, Long> tokenExpiry = new ConcurrentHashMap<>();
    private static final long EXPIRY_TIME = 15 * 60 * 1000; // 15 minutes

    // Generate and store reset token temporarily
    public String generateResetToken(String email) {
        String token = UUID.randomUUID().toString();
        resetTokens.put(token, email);
        tokenExpiry.put(token, System.currentTimeMillis() + EXPIRY_TIME);
        return token;
    }

    // Validate token and get email
    public String validateResetToken(String token) {
        if (!resetTokens.containsKey(token) || tokenExpiry.get(token) < System.currentTimeMillis()) {
            return null; // Invalid or expired token
        }
        return resetTokens.get(token);
    }

    // Remove token after reset
    public void removeToken(String token) {
        resetTokens.remove(token);
        tokenExpiry.remove(token);
    }
}