package com.epam.ta.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://github.com/";

    @FindBy(xpath = "//a[contains(@aria-label, 'Create new')]")
    private WebElement buttonCreateNew;

    @FindBy(xpath = ".//*[@id='your_repos']/div/div[1]/a")
    private WebElement linkNewRepository;

    @FindBy(xpath = ".//*[@id='user-links']/li[3]/details/ul/li[3]/a")
    private WebElement linkYourProfile;

    @FindBy(xpath = ".//*[@id='user-links']/li[3]/details/summary/span")
    private WebElement flag;


    @FindBy(xpath = ".//*[@id='user-links']/li[3]/details/ul/li[9]/form/button")
    private WebElement buttonSingOut;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnCreateNewRepositoryButton() {
        linkNewRepository.click();
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }


    public void openProfilePage() {
        flag.click();
        linkYourProfile.click();
    }


    public void logOut() {
        openPage();
        flag.click();
        buttonSingOut.click();
    }

}