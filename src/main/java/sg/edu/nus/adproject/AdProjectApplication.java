package sg.edu.nus.adproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@ComponentScan(basePackages = "sg.edu.nus.adproject") //
public class AdProjectApplication {

    public static void main(String[] args) {

//        PrintWriter writer = new PrintWriter(System.out);
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//
//        String formattedDateTime = now.format(formatter);
//        writer.format("Formatted Date-Time: %s%n",formattedDateTime);
//        writer.flush();

        SpringApplication.run(AdProjectApplication.class, args);
    }

}
