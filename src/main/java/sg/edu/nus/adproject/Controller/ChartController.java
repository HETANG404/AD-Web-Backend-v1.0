package sg.edu.nus.adproject.Controller;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import sg.edu.nus.adproject.Service.DailyViewService;
//
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/api")
//public class ChartController {
//    // Inject the StatsService so it’s never null
//    @Autowired
//    private DailyViewService dailyViewService;
//
//    @GetMapping("/chart")
//    public Map<String, Object> getChartData(@RequestParam(name="date", required=false) String selectedMonthStr) {
//
//
//        // 1. Get the last 6 months from the DB using the injected service
//        List<MonthlyStats> sixMonthsData = statsService.getStatsForLastSixMonths(selectedMonthStr);
//
//        // 2. Prepare collections for categories, logins, and searchings
//        List<String> categories = new ArrayList<>();
//        List<Integer> loginsData = new ArrayList<>();
//        List<Integer> searchesData = new ArrayList<>();
//
//        // Populate each list from the retrieved MonthlyStats
//        for (MonthlyStats ms : sixMonthsData) {
//            categories.add(ms.getMonthString());  // e.g. "09/2023"
//            loginsData.add(ms.getLogins());       // e.g. 5
//            searchesData.add(ms.getSearches());   // e.g. 120
//        }
//
//        // 3. Create the "series" objects expected by your frontend
//        Map<String, Object> loginsSeries = new HashMap<>();
//        loginsSeries.put("name", "Logins");
//        loginsSeries.put("data", loginsData);
//
//        Map<String, Object> searchesSeries = new HashMap<>();
//        searchesSeries.put("name", "Searchings");
//        searchesSeries.put("data", searchesData);
//
//        List<Map<String, Object>> seriesList = new ArrayList<>();
//        seriesList.add(loginsSeries);
//        seriesList.add(searchesSeries);
//
//        // 4. Final response structure
//        Map<String, Object> response = new HashMap<>();
//        response.put("categories", categories);
//        response.put("series", seriesList);
//
//        // Add your custom fields
//        response.put("ok", true);
//        response.put("statusText", "Network response was not ok");
//
//        return response;
//    }
//}

//...................
    // Example: GET /api/chart?month=09/2023
//    @GetMapping("/chart")
//    public Map<String, Object> getChartData(@RequestParam("month") String selectedMonthStr) {
        // 1. Get the last 6 months from the DB
//        StatsService statsService = null;
//        List<MonthlyStats> sixMonthsData = statsService.getStatsForLastSixMonths(selectedMonthStr);

        // 2. Build the response structure
        //    categories -> [ "xx/2023", "xx/2023", ... ]
        //    series ->
        //       [
        //         { name: "Logins", data: [1,2,3,...] },
        //         { name: "Searchings", data: [100,200,300,...] }
        //       ]

        // categories
//        List<String> categories = new ArrayList<>();
        // data arrays
//        List<Integer> loginsData = new ArrayList<>();
//        List<Integer> searchesData = new ArrayList<>();
//
//        for (MonthlyStats ms : sixMonthsData) {
//            categories.add(ms.getMonthString());
//            loginsData.add(ms.getLogins());
//            searchesData.add(ms.getSearches());
//        }

        // build "series"
//        Map<String, Object> loginsSeries = new HashMap<>();
//        loginsSeries.put("name", "Logins");
//        loginsSeries.put("data", loginsData);
//
//        Map<String, Object> searchesSeries = new HashMap<>();
//        searchesSeries.put("name", "Searchings");
//        searchesSeries.put("data", searchesData);
//
//        List<Map<String, Object>> seriesList = new ArrayList<>();
//        seriesList.add(loginsSeries);
//        seriesList.add(searchesSeries);

        // final response map
//        Map<String, Object> response = new HashMap<>();
//        response.put("categories", categories);
//        response.put("series", seriesList);
//
//        return response;
//    }
//}
//

        // You can now use 'selectedMonth' (e.g., "09/2023")
        // to filter data or do other logic.

        // For demonstration, we'll return some simple mock data
//        Map<String, Object> response = new HashMap<>();

        // categories could be dynamic based on selectedMonth,
        // but here's a static example:
//        response.put("categories", Arrays.asList(
//                "08/2023", "09/2023", "10/2023",
//                "11/2023", "12/2023", "01/2024", "02/2024"
//        ));

        // Build "series" array
//        List<Map<String, Object>> seriesList = new ArrayList<>();
//
//        Map<String, Object> logins = new HashMap<>();
//        logins.put("name", "Logins");
//        logins.put("data", Arrays.asList(1, 1, 1, 1, 1, 1, 1));
//
//        Map<String, Object> searchings = new HashMap<>();
//        searchings.put("name", "Searchings");
//        searchings.put("data", Arrays.asList(420, 430, 440, 450, 460, 470, 480));
//
//        seriesList.add(logins);
//        seriesList.add(searchings);
//        response.put("series", seriesList);
//
//        return response;
//    }
//
//
//
//    }




//    public ChartData getChartData() {
//        ChartData chartData = new ChartData();
//
//        // Set categories
//        chartData.setCategories(Arrays.asList(
//                "08/2023", "09/2023", "10/2023",
//                "11/2023", "12/2023", "01/2024", "02/2024"
//        ));
//
//        // Create first series
//        ChartData.Series logins = new ChartData.Series();
//        logins.setName("Logins");
//        logins.setData(Arrays.asList(1, 1, 1, 1, 1, 1, 1));
//
//        // Create second series
//        ChartData.Series searchings = new ChartData.Series();
//        searchings.setName("Searchings");
//        searchings.setData(Arrays.asList(420, 430, 440, 450, 460, 470, 480));
//
//        // Set series
//        chartData.setSeries(Arrays.asList(logins, searchings));
//
//        return chartData;
//    }
//}

