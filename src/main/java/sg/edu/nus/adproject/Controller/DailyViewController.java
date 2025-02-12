package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.adproject.Model.DailyView;
import sg.edu.nus.adproject.Service.DailyViewService;
import sg.edu.nus.adproject.dtos.ChartDTO;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DailyViewController {

    @Autowired
    private DailyViewService dailyViewService;
    private Object formattedMonths;

    @GetMapping("/getAllDailyViews")
    public List<DailyView> getAllDailyViews() {
        return dailyViewService.getAllDailyViews();

    }
    @GetMapping("/getLastSevenMonthsData/{year}/{month}")
    public ResponseEntity<Map<String, Object>> getLastSevenMonthsData(@PathVariable int year, @PathVariable int month) {
        YearMonth yearMonth = YearMonth.of(year, month);

        // Get data from service
        ChartDTO chartDTO = dailyViewService.pastSevenMonthsDailyView(yearMonth);

        // Extract last seven months (current month + last six months)
        List<YearMonth> lastSevenMonths = chartDTO.getLastSevenMonths();

        // Format months as MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        List<String> formattedMonths = lastSevenMonths.stream()
                .map(yearMonthObj -> yearMonthObj.format(formatter))
                .collect(Collectors.toList());

        // Prepare series data
        List<Map<String, Object>> series = new ArrayList<>();

        series.add(Map.of(
                "name", "Logins",
                "data", lastSevenMonths.stream()
                        .map(chartDTO.getLoginCountsInLastSevenMonths()::get) // Get values using YearMonth keys
                        .collect(Collectors.toList())
        ));

        series.add(Map.of(
                "name", "Searches",
                "data", lastSevenMonths.stream()
                        .map(chartDTO.getSearchCountsInLastSevenMonths()::get) // Get values using YearMonth keys
                        .collect(Collectors.toList())
        ));

        // Construct final response
        Map<String, Object> response = new HashMap<>();
        response.put("ok", true);
        response.put("categories", formattedMonths);
        response.put("series", series);

        return ResponseEntity.ok(response);
    }

    /**
     * Fetch today's, yesterday's, and past 7 days' login counts.
     */
    @GetMapping("/loginStats")
    public ResponseEntity<Map<String, Object>> getDailyLoginStats() {

        try {
            Map<String, Object> trafficData = dailyViewService.getDailyLoginStats();
            return ResponseEntity.ok(trafficData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "ok", false,
                    "statusText", "Network response error"
            ));
        }
//        return ResponseEntity.ok(dailyViewService.getDailyLoginStats());
    }
}
