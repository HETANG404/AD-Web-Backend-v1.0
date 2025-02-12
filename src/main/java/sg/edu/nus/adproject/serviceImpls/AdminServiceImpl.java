package sg.edu.nus.adproject.serviceImpls;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.Admin;
import sg.edu.nus.adproject.Repository.AdminRepository;
import sg.edu.nus.adproject.Service.AdminService;

import java.util.List;
import java.util.Optional;

@Service

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {

        return adminRepository.findAll();
    }
    /**
     * Get admin by username
     */
    @Override
    public Optional<Admin> getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    /**
     * Authenticate admin user
     */
    @Override
    public boolean authenticate(String username, String password) {
        Optional<Admin> admin = adminRepository.findByUsername(username);

        // Compare password directly (NOT recommended for production)
        return admin.isPresent() && admin.get().getPassword().equals(password);
    }

    @Override
    public boolean userExists(String username) {
        Optional<Admin> user = adminRepository.findByUsername(username);
//        System.out.println("Checking if user exists: " + username + " -> " + user.isPresent());
        return user.isPresent();
    }




}

