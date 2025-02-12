//package sg.edu.nus.adproject.Service;
//
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////import sg.edu.nus.adproject.Model.MonthlyStats;
////import sg.edu.nus.adproject.Repository.MonthlyStatsRepository;
////
////import java.time.YearMonth;
////import java.time.format.DateTimeFormatter;
////import java.util.ArrayList;
////import java.util.List;
////
////@Service
////public class StatsService {
////    @Autowired
////    private MonthlyStatsRepository monthlyStatsRepository;
////
////    public List<MonthlyStats> getStatsForLastSixMonths(String selectedMonthStr) {
////        // 1. Parse "09/2023" into a YearMonth
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
////        YearMonth selectedMonth = YearMonth.parse(selectedMonthStr, formatter);
////
////        // 2. Generate the list of 6 months (including the selected month)
////        List<YearMonth> months = new ArrayList<>();
////        for (int i = 5; i >= 0; i--) {
////            months.add(selectedMonth.minusMonths(i));
////        }
////
////        // 3. Query the database for each month.
////        //    If no record exists, create a default with 0s.
////        List<MonthlyStats> results = new ArrayList<>();
////        for (YearMonth ym : months) {
////            String mmYYYY = ym.format(formatter);  // e.g. "04/2023"
////            MonthlyStats stats = monthlyStatsRepository.findByMonthString(mmYYYY);
////
////            if (stats == null) {
////                // If you prefer returning no data, you could skip,
////                // but here's how you'd return 0 for missing months.
////                stats = new MonthlyStats();
////                stats.setMonthString(mmYYYY);
////                stats.setLogins(0);
////                stats.setSearches(0);
////            }
////            results.add(stats);
////        }
////
////        return results;
////    }
////
////}
////....................
////    @Autowired
////    private MonthlyStatsRepository statsRepository;
////
////    public List<MonthlyStats> getStatsForLastSixMonths(String selectedMonthStr) {
////        // Parse the incoming "09/2023" into a YearMonth
////        // e.g. "MM/yyyy"
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
////        YearMonth selectedMonth = YearMonth.parse(selectedMonthStr, formatter);
////
////        // Generate the last 6 months including the selectedMonth
////        // e.g., if selected is 09/2023, we want [04/2023, 05/2023, 06/2023, 07/2023, 08/2023, 09/2023]
////        List<YearMonth> months = new ArrayList<>();
////        for (int i = 5; i >= 0; i--) {
////            YearMonth m = selectedMonth.minusMonths(i);
////            months.add(m);
////        }
////
////        // For each YearMonth, convert back to "MM/yyyy" to query
////        List<MonthlyStats> results = new ArrayList<>();
////        for (YearMonth ym : months) {
////            String mmYYYY = ym.format(formatter); // back to "MM/yyyy"
////            MonthlyStats stats = statsRepository.findByMonthString(mmYYYY);
////
////            // If no record found, you might want to create a default with zeros
////            if (stats == null) {
////                stats = new MonthlyStats();
////                stats.setMonthString(mmYYYY);
////                stats.setLogins(0);
////                stats.setSearches(0);
////            }
////            results.add(stats);
////        }
////
////        return results;
////    }
////}
