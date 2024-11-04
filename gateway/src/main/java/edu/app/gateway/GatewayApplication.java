package edu.app.gateway;

import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class GatewayApplication {
    public static class Statist implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("Thực hiện tác vụ định kỳ với Quartz...");
        }
    }

    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
