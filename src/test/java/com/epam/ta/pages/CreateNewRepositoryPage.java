package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.ta.utils.Utils;

public class CreateNewRepositoryPage extends AbstractPage {
    private final String BASE_URL = "http://www.github.com/new";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(id = "repository_name")
    private WebElement inputRepositoryName;

    @FindBy(id = "repository_description")
    private WebElement inputRepositoryDescription;

    @FindBy(xpath = ".//*[@id='new_repository']/div[3]/button")
    private WebElement butttonCreate;

    @FindBy(linkText = "Import code")
    private WebElement labelEmptyRepoSetupOption;

    @FindBy(xpath = "//a[@data-pjax='#js-repo-pjax-container']")
    private WebElement linkCurrentRepository;

    @FindBy(xpath = ".//*[@id='user-links']/li[3]/details/ul/li[3]/a")
    private WebElement linkYourProfile;

    @FindBy(xpath = ".//*[@id='user-links']/li[3]/details/summary/span")
    private WebElement flag;


    public CreateNewRepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isCurrentRepositoryEmpty() {
        return labelEmptyRepoSetupOption.isDisplayed();
    }

    public String createNewRepository(String repositoryName, String repositoryDescription) {
        String repositoryFullName = repositoryName + Utils.getRandomString(6);
        inputRepositoryName.sendKeys(repositoryFullName);
        inputRepositoryDescription.sendKeys(repositoryDescription);
        butttonCreate.click();
        return repositoryFullName;
    }


    public String getCurrentRepositoryName() {
        return linkCurrentRepository.getText();
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }


    public void openProfilePage() {
        flag.click();
        linkYourProfile.click();
    }


}