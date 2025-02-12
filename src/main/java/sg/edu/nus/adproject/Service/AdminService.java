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

//    @Autowired
//    private AdminRepository adminRepository;
//
//    /**
//     * Authenticate admin user without password encryption
//     */
//    public boolean authenticate(String username, String password) {
//        Optional<Admin> admin = adminRepository.findByUsername(username);
//
//        // Compare password directly (NOT recommended for production)
//        return admin.isPresent() && admin.get().getPassword().equals(password);
//    }
//
//
//    public List<Admin> getAllAdmins(){
//        return adminRepository.findAll();
//
//    }
//}
