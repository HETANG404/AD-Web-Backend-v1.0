package sg.edu.nus.adproject.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.DailyView;
import sg.edu.nus.adproject.Repository.DailyViewRepository;
import sg.edu.nus.adproject.Service.DailyViewService;
import sg.edu.nus.adproject.dtos.ChartDTO;
import sg.edu.nus.adproject.utils.DateGenerator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DailyViewServiceImpl implements DailyViewService {

    @Autowired
    DailyViewRepository dailyViewRepository;


    @Override
    public List<DailyView> getAllDailyViews() {
        return dailyViewRepository.findAll();
    }

    @Override
    public ChartDTO pastSevenMonthsDailyView(YearMonth userInputMonth) {

        Map<YearMonth, Integer> loginCountsInLastSevenMonths = new HashMap<>();
        Map<YearMonth, Integer> searchCountsInLastSevenMonths = new HashMap<>();

        // Get current month + last six months
        List<YearMonth> lastSevenMonths = DateGenerator.getPastSevenMonths(userInputMonth);

        for (YearMonth yearMonth : lastSevenMonths) {
            int year = yearMonth.getYear();
            int month = yearMonth.getMonthValue();

            int totalLoginCount = 0;
            int totalSearchCount = 0;

            List<LocalDate> allDaysInCurrentMonth = DateGenerator.getDatesForMonth(year, month);

            for (LocalDate day : allDaysInCurrentMonth) {
                DailyView dailyView = dailyViewRepository.findByDate(day);

                if (dailyView != null) {
                    totalLoginCount += dailyView.getLoginCount();
                    totalSearchCount += dailyView.getSearchCount();
                }
            }

            loginCountsInLastSevenMonths.put(yearMonth, totalLoginCount);
            searchCountsInLastSevenMonths.put(yearMonth, totalSearchCount);
        }

        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setLoginCountsInLastSevenMonths(loginCountsInLastSevenMonths);
        chartDTO.setSearchCountsInLastSevenMonths(searchCountsInLastSevenMonths);
        chartDTO.setLastSevenMonths(lastSevenMonths);

        return chartDTO;
    }



    @Override
    public Map<String, Object> getDailyLoginStats() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate sevenDaysAgo = today.minusDays(6); // Last 7 days including today
        List<DailyView> dailyViews = dailyViewRepository.findByMonth(today);


        // Fetch today's and yesterday's login count
        DailyView todayData = dailyViewRepository.findByDate(today);
        DailyView yesterdayData = dailyViewRepository.findByDate(yesterday);

        int todayLogin = (todayData != null) ? todayData.getLoginCount() : 0;
        int yesterdayLogin = (yesterdayData != null) ? yesterdayData.getLoginCount() : 0;

        // Fetch login counts for the last 7 days
        List<DailyView> last7DaysData = dailyViewRepository.findLast7DaysData(sevenDaysAgo, today);
        List<Integer> past7DaysLogin = last7DaysData.stream()
                .map(DailyView::getLoginCount)
                .collect(Collectors.toList());

        // Construct response
        Map<String, Object> response = new HashMap<>();
        response.put("todayLogin", todayLogin);
        response.put("yesterdayLogin", yesterdayLogin);
        response.put("past7DaysLogin", past7DaysLogin);

        return response;
    }
}

