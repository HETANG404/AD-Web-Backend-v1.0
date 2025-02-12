package sg.edu.nus.adproject.dtos;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartDTO {
    private Map<YearMonth, Integer> loginCountsInLastSevenMonths;
    private Map<YearMonth, Integer> searchCountsInLastSevenMonths;
    private List<YearMonth> lastSevenMonths;

    public ChartDTO() {
        loginCountsInLastSevenMonths = new HashMap<>();
        searchCountsInLastSevenMonths = new HashMap<>();
        lastSevenMonths = new ArrayList<>();
    }

    public Map<YearMonth, Integer> getLoginCountsInLastSevenMonths() {
        return loginCountsInLastSevenMonths;
    }

    public void setLoginCountsInLastSevenMonths(Map<YearMonth, Integer> loginCountsInLastSevenMonths) {
        this.loginCountsInLastSevenMonths = loginCountsInLastSevenMonths;
    }

    public Map<YearMonth, Integer> getSearchCountsInLastSevenMonths() {
        return searchCountsInLastSevenMonths;
    }

    public void setSearchCountsInLastSevenMonths(Map<YearMonth, Integer> searchCountsInLastSevenMonths) {
        this.searchCountsInLastSevenMonths = searchCountsInLastSevenMonths;
    }

    public List<YearMonth> getLastSevenMonths() {
        return lastSevenMonths;
    }

    public void setLastSevenMonths(List<YearMonth> lastSevenMonths) {
        this.lastSevenMonths = lastSevenMonths;
    }
}
