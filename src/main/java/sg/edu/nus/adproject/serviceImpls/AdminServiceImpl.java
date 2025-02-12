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
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            // 比对密码，这里可以进行加密处理（使用 BCrypt 等）
            return admin.getPassword().equals(password); // 简单比对（实际应用应加密）
        }
        return false;
    }

    @Override
    public boolean userExists(String username) {
        return adminRepository.findByUsername(username).isPresent(); // 检查管理员是否存在
    }




}

