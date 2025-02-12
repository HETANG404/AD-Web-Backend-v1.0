package sg.edu.nus.adproject.utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DateGenerator {


    public static List<LocalDate> getDatesForMonth(int year, int month) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int daysInMonth = firstDay.lengthOfMonth();

        for (int day = 1; day <= daysInMonth; day++) {
            dates.add(LocalDate.of(year, month, day));
        }

        return dates;
    }


    public static List<YearMonth> getPastSixMonths(YearMonth yearMonth) {
        List<YearMonth> pastMonths = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            pastMonths.add(yearMonth.minusMonths(i));
        }

        return pastMonths;
    }
    public static List<YearMonth> getPastSevenMonths(YearMonth userInputMonth) {
        List<YearMonth> lastSevenMonths = new ArrayList<>();

        // Add current month first
        lastSevenMonths.add(userInputMonth);

        // Add previous six months
        for (int i = 1; i <= 6; i++) {
            lastSevenMonths.add(userInputMonth.minusMonths(i));
        }

        return lastSevenMonths;
    }
}

//        public static List<LocalDate> getAllDaysFromLastSixMonths()
//        {
//            List<LocalDate> dates = new ArrayList<>();
//            List<YearMonth> pastSixMonths = getPastSixMonths();
//
//            for (YearMonth eachYearMonth : pastSixMonths) {
//                int year = eachYearMonth.getYear();
//                int month = eachYearMonth.getMonthValue();
//
//                List<LocalDate> allDaysFromCurrentMonth = getDatesForMonth(year, month);
//                dates.addAll(allDaysFromCurrentMonth);
//            }
//
//            return dates;
//        }


