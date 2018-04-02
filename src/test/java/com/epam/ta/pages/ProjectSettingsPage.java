package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProjectSettingsPage extends AbstractPage {

    private String name;

    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div[1]/div/div[2]/div/div[10]/ul/li[4]/button")
    private WebElement buttonDelete;

    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div[1]/div/div[2]/div/div[10]/ul/li[4]/div/div/div/div/div[2]/form/p/input")
    private WebElement form;

    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div[1]/div/div[2]/div/div[10]/ul/li[4]/div/div/div/div/div[2]/form/button")
    private WebElement linkDeleteThisRepository;


    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div[1]/div/div[2]/div/div[10]/ul/li[4]/div/div/div/div/div[2]/p[1]/strong[2]")
    private WebElement projectName;


    public ProjectSettingsPage(WebDriver driver, String name) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.name = name;
    }

    public ProjectSettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void openPage() {

    }

    public void deleteRepo() {
        buttonDelete.click();
        while (!form.isEnabled()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        form.clear();
        form.sendKeys(name);
        linkDeleteThisRepository.click();
    }
}



















