package sg.edu.nus.adproject.serviceImpls;




import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.User;
import sg.edu.nus.adproject.Repository.UserRepository;
import sg.edu.nus.adproject.Service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

//    @Override
//    public boolean deleteUserById(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent()) {
//            userRepository.delete(user.get());
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setIsCancelled(true);  // Set isCancelled to true for soft delete
            userRepository.save(existingUser);  // Save the updated user
            return true;
        }
        return false;
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getActiveUsers() {
        return userRepository.findByIsCancelledFalse();
    }

}
