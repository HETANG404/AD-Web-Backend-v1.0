package sg.edu.nus.adproject.Service;





import sg.edu.nus.adproject.Model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public boolean deleteUserById(Long id);
    public void saveUser(User user);
    List<User> getActiveUsers();  // 只返回 isCancelled = false 的用户

}
