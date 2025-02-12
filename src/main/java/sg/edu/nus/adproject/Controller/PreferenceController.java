package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.adproject.Model.Preference;
import sg.edu.nus.adproject.Service.PreferenceService;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    @Autowired
    private PreferenceService preferenceService;

    @GetMapping("/getAllPreferences")
    public List<Preference> getAllPreferences() {
        return preferenceService.getAllPreferences();
    }
}
