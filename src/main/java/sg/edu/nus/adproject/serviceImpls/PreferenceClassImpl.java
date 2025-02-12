package sg.edu.nus.adproject.serviceImpls;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.Preference;
import sg.edu.nus.adproject.Repository.PreferenceRepository;
import sg.edu.nus.adproject.Service.PreferenceService;

import java.util.List;

@Service
@Transactional
public class PreferenceClassImpl implements PreferenceService {

    @Autowired
    private PreferenceRepository preferenceRepository;

     public List<Preference> getAllPreferences() {

         return preferenceRepository.findAll();
     }
}
