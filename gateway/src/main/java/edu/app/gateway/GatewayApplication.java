package edu.app.gateway;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class GatewayApplication {
	public static class Statist implements Job{

		@Override
		public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
			System.out.println("Thực hiện tác vụ định kỳ với Quartz...");
		}
	}

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(GatewayApplication.class, args);
//		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//		// Tạo một Job (công việc) đơn giản
//		JobDetail job = JobBuilder.newJob(Statist.class)
//				.withIdentity("Statist", "group1")
//				.build();
//		// Tạo một Trigger (kích hoạt) để chạy công việc mỗi 10 giây
//		Trigger trigger = TriggerBuilder.newTrigger()
//				.withIdentity("myTrigger", "group1")
//				.startNow()
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//						.withIntervalInSeconds(10)
//						.repeatForever())
//				.build();
//
//		// Bắt đầu lên lịch Job
//		scheduler.scheduleJob(job, trigger);
//		scheduler.start();
	}

}
