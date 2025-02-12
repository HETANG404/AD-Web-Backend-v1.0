package sg.edu.nus.adproject.Service;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import sg.edu.nus.adproject.Model.DailyView;
//import sg.edu.nus.adproject.Model.TrafficResponse;
//import sg.edu.nus.adproject.Repository.DailyViewRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class TrafficService {
//    private final DailyViewRepository dailyViewRepository;
//
//    @Autowired
//    public TrafficService(DailyViewRepository dailyViewRepository) {
//        this.dailyViewRepository = dailyViewRepository;
//    }
//
//    public TrafficResponse getTrafficData() {
//        List<DailyView> last7Days = dailyViewRepository.findTop7ByOrderByDateDesc();
//        //dailyViewRepository.findTop7ByOrderByDateDesc();
//
//        // Extract traffic numbers from last 7 days
//        List<Integer> trafficData7Days = last7Days.stream()
//                .map(DailyView::getLogin_Count) // Get the login count for each day
//                .collect(Collectors.toList());
//
//    }
//}

////        int todayTraffic = trafficData7Days.isEmpty() ? 0 : trafficData7Days.get(0); // Today's traffic
////        int yesterdayTraffic = trafficData7Days.size() > 1 ? trafficData7Days.get(1) : 0; // Yesterday's traffic
////
////        return new TrafficResponse(todayTraffic, yesterdayTraffic, trafficData7Days);
////    }
////}
