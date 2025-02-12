package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.adproject.Model.QueryLog;
import sg.edu.nus.adproject.Service.QueryLogService;

import java.util.List;

@RestController
@RequestMapping("/api/queryLog")
public class QueryLogController {

    @Autowired
    private QueryLogService queryLogService;

    @GetMapping("/getAllQueryLogs")
    public List<QueryLog> getAllQueryLogs(){
        return queryLogService.getAllQueryLogs();
    }
}
