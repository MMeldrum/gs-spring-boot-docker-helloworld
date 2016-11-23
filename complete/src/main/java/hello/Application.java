package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    public String home(@RequestParam(value = "ip", defaultValue = "192.168.99.100") String ip,
                       @RequestParam(value = "port", defaultValue = "8081") String port) {

        RestTemplate restTemplate = new RestTemplate();
        Time time = restTemplate.getForObject("http://" + ip + ":" + port + "/time", Time.class);
        log.info(time.toString());

        return "Hello Docker World.  The time is " + time.getTime();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
