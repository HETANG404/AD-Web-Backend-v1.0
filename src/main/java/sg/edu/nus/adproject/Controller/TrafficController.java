package sg.edu.nus.adproject.Controller;


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import sg.edu.nus.adproject.Model.TrafficResponse;
//import sg.edu.nus.adproject.Service.TrafficService;
//
//@RestController
//public class TrafficController {
//
//    @Autowired
//    private TrafficService trafficService;
//
//    @GetMapping("/api/daily")
//    public TrafficResponse getDailyTraffic() {
//        // This will return JSON automatically, thanks to Spring Boot’s Jackson integration
//        return trafficService.getDailyTraffic();
//    }
//}


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import sg.edu.nus.adproject.Model.TrafficResponse;
//import sg.edu.nus.adproject.Service.TrafficService;
//
//@RestController
//@RequestMapping("/api")
//public class TrafficController {
//    private final TrafficService trafficService;
//
//    @Autowired
//    public TrafficController(TrafficService trafficService) {
//        this.trafficService = trafficService;
//    }
//
//    @GetMapping("/daily")
//    public ResponseEntity<TrafficResponse> fetchTrafficData() {
//        TrafficResponse response = trafficService.getTrafficData();
//        return ResponseEntity.ok(response);
//    }
//}
