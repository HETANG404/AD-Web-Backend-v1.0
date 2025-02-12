package sg.edu.nus.adproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sg.edu.nus.adproject.Model.DailyView;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyViewRepository extends JpaRepository<DailyView, Long> {
    // Fetch today's data
    DailyView findByDate(LocalDate date);

    // Fetch yesterday's data
    @Query("SELECT d FROM DailyView d WHERE d.date = :date")
    DailyView findYesterdayData(LocalDate date);

    // Fetch the last 7 days data
    @Query("SELECT d FROM DailyView d WHERE d.date BETWEEN :startDate AND :endDate ORDER BY d.date ASC")
    List<DailyView> findLast7DaysData(LocalDate startDate, LocalDate endDate);

    //Fetch data for a specific month
    @Query("SELECT d FROM DailyView d WHERE YEAR(d.date) = YEAR(:date) AND MONTH(d.date) = MONTH(:date)")
    List<DailyView> findByMonth(LocalDate date);
}
