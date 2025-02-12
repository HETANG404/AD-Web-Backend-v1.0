package sg.edu.nus.adproject.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.DailyView;
import sg.edu.nus.adproject.Repository.DailyViewRepository;
import sg.edu.nus.adproject.dtos.ChartDTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public interface DailyViewService {

  public List<DailyView> getAllDailyViews();// Get all daily views

  Map<String, Object> getDailyLoginStats(); // Get daily login statistics


//  Map<String, Object> getChartData(String selectedMonth); // Get chart data for a specific month

//  public ChartDTO pastSixMonthsDailyView(YearMonth userInputMonth);

  public ChartDTO pastSevenMonthsDailyView(YearMonth userInputMonth) ;

}