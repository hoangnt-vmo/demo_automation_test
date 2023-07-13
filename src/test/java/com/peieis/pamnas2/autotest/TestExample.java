package com.peieis.pamnas2.autotest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class TestExample {
    WebDriver driver;

    @Test
    public void Test01() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000L));
        driver.manage().window().maximize();
        driver.navigate().to("https://dev.pamnas2.jp/login");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("form_item_loginId")));
        System.out.println("Test " + driver.getTitle());
        WebElement userName = driver.findElement(By.id("form_item_loginId"));
        WebElement password = driver.findElement(By.id("form_item_password"));
        WebElement login = driver.findElement(xpath("//button[@type='submit']"));
        userName.sendKeys("admin");
        password.sendKeys("admin");
        login.click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/dashboard/new-receive']")));
        WebElement newReceive = driver.findElement(By.xpath("//a[@href='/dashboard/new-receive']"));

        newReceive.click();
        wait.until(ExpectedConditions.urlContains("dashboard/new-receive"));
        WebElement labelPage = driver.findElement(By.xpath("//span[@class='ant-page-header-heading-title']/span[1]"));
        WebElement labelCallerPhone = driver.findElement(By.xpath("//label[@for='form_item_callerPhone']"));
        WebElement inputCallerPhone = driver.findElement(By.id("form_item_callerPhone"));
        WebElement labelClientSelect = driver.findElement(By.xpath("//span[@class='hyperlink']"));


        WebElement btnCreateNotice = driver.findElement(By.xpath("//div[@class='ant-page-header-heading']//button[2]"));


        Assert.assertTrue(labelPage.getText().equalsIgnoreCase("新規受付"));
        Assert.assertTrue(labelCallerPhone.getText().equalsIgnoreCase("発信者電話番号"));
        Assert.assertTrue(inputCallerPhone.getAttribute("value").equalsIgnoreCase("00000000000000000000"));
        Assert.assertTrue(labelClientSelect.getText().equalsIgnoreCase("クライアント/ライン"));
        Assert.assertTrue(btnCreateNotice.getText().contains("案件作成"));
        Assert.assertFalse(btnCreateNotice.isEnabled());
        labelClientSelect.click();
        WebElement ntmSelect = driver.findElement(By.xpath("//div[contains(@class,'ant-col ant-col-6')]//div[2]"));
        ntmSelect.click();
        WebElement btnSelectClient = driver.findElement(By.xpath("//button[contains(@class,'ant-btn ant-btn-primary custom-btn select-btn')]"));
        Thread.sleep(200);
        btnSelectClient.click();
        Assert.assertTrue(btnCreateNotice.isEnabled());
        inputCallerPhone.clear();
        inputCallerPhone.sendKeys("555555");
        btnCreateNotice.click();
        driver.findElement(By.xpath("//div[@class='custom-btn-confirm']/button[1]")).click();

        wait.until(ExpectedConditions.urlContains("nsf/notice-vehicle"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("form_item_clientName")));
        Thread.sleep(2000);
        WebElement itemClientName = driver.findElement(By.id("form_item_clientName"));
        WebElement receivedPhone = driver.findElement(By.id("locationInformation_receivedPhone"));
        Assert.assertTrue(itemClientName.getAttribute("value").equalsIgnoreCase("【NFM】日新火災海上"));
        Assert.assertTrue(receivedPhone.getAttribute("value").equalsIgnoreCase("44444"));
    }

}
