package com.pieis.pamnas2.seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAuto {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Document\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.navigate().to("https://dev.pamnas2.jp/login");
        Thread.sleep(2000);
        System.out.println("Test " + driver.getTitle());
        WebElement userName = driver.findElement(By.id("form_item_loginId"));
        WebElement password = driver.findElement(By.id("form_item_password"));
//        WebElement login = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement login = driver.findElement(By.tagName("//label[@for='submit']"));
        userName.sendKeys("admin");
        password.sendKeys("admin");
        login.click();
        Thread.sleep(2000);
        WebElement newReceive = driver.findElement(By.xpath("//a[@href='/dashboard/new-receive']"));
        newReceive.click();
    }
}
