package com.example.demo;

import java.util.Map;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@RestController
class HelloRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloRestController.class);

    private String getHostAddress() {
        String hostaddress = "";
        try {
          hostaddress =  InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
          LOGGER.error("Error while fetching hostaddress:", e);
        }
        return hostaddress;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello (@RequestHeader Map<String, String> header){
        String hostName = getHostAddress();
        printAllHeaders(header);return
        ResponseEntity.ok("<h2>Hello World! : "+hostName+"</h2>");
    }

    private void printAllHeaders(Map<String, String> headers) {
        headers.forEach((key, value) -> {
            LOGGER.info(String.format("Header '%s' = %s", key, value));
        });
    }
}
