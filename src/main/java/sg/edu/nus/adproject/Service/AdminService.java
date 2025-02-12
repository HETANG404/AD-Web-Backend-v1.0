package sg.edu.nus.adproject.Service;



import org.springframework.beans.factory.annotation.Autowired;
import sg.edu.nus.adproject.Model.Admin;
import sg.edu.nus.adproject.Repository.AdminRepository;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Admin> getAllAdmins(); // Fetch all admins

    Optional<Admin> getAdminByUsername(String username); // Get admin by username

    boolean authenticate(String username, String password); // Authenticate user

    boolean userExists(String username);


//    public List<Admin> getAllAdmins();
}
