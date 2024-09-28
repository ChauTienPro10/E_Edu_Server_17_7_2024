package com.edu.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.RequestId;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public class RegisterTest {
    public static void main(String[] args) {
        // Cài đặt đường dẫn WebDriver cho Microsoft Edge
        System.setProperty("webdriver.edge.driver", "C:\\Users\\chaud\\Videos\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        EdgeDriver driver = new EdgeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Kích hoạt Network để bắt sự kiện phản hồi
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));


        // Lắng nghe sự kiện phản hồi nhận được
        devTools.addListener(Network.responseReceived(), response -> {


            System.out.println("URL: " + response.getResponse().getUrl());
            System.out.println("Status: " + response.getResponse().getStatus());
            System.out.println("Response code: " + response.getResponse().getStatusText());

            // Giả sử nội dung phản hồi là JSON

        });

        try {
            // Mở trang login của bạn
            driver.get("http://localhost:3000/signup");

            try {
                Thread.sleep(3000);  // Chờ 3 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Tương tác với các trường đăng nhập
            WebElement nameField = driver.findElement(By.id("namebox"));
            nameField.sendKeys("tran huu tuan");
            WebElement mailField = driver.findElement(By.id("emailbox"));
            mailField.sendKeys("tuan@gmail.com");
            WebElement telField = driver.findElement(By.id("telbox"));
            telField.sendKeys("0812788212");
            WebElement passwordField = driver.findElement(By.id("pass"));
            passwordField.sendKeys("22012003");
            WebElement passAgainField = driver.findElement(By.id("passagainbox"));
            passAgainField.sendKeys("22012003");
            passwordField.submit();
//            try {
//                Thread.sleep(3000);  // Chờ 10 giây
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            WebElement loginF = driver.findElement(By.id("loginFalure"));
//            loginF.click();



        } finally {
            // Đóng trình duyệt nếu cần thiết
            // driver.quit();
        }
    }
}

