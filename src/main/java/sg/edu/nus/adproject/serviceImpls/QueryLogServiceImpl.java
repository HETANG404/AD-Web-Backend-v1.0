package sg.edu.nus.adproject.serviceImpls;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.QueryLog;
import sg.edu.nus.adproject.Repository.QueryLogRepository;
import sg.edu.nus.adproject.Service.QueryLogService;

import java.util.List;

@Service
public class QueryLogServiceImpl implements QueryLogService {

    @Autowired
    private QueryLogRepository queryLogRepository;

    public List<QueryLog> getAllQueryLogs()
    {

        return queryLogRepository.findAll();
    }
}
