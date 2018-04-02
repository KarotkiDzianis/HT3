package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProfilePage extends AbstractPage {

    private String name;

    private final String BASE_URL = "https://github.com/testautomationuser";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = ".//*[@id='js-pjax-container']/div/div[2]/div[2]/nav/a[2]")
    private WebElement linkAllRepositories;

    @FindBy(className = "Counter")
    private WebElement counter;

    @FindBy(id = "user-repositories-list")
    private WebElement userRepositoriesList;

    @FindBy(xpath = ".//*[@id='js-repo-pjax-container']/div[1]/nav/a[4]")
    private WebElement linkSettings;

    @FindBy(xpath = "//*[@id='your-repos-filter']")
    private WebElement reposFilter;

    @FindBy(xpath = "//*[@id='user-repositories-list']/div[2]/h4")
    private WebElement text;

    @FindBy(xpath = "//*[@id='user-repositories-list']/ul/li[1]/div[1]/h3/a")
    private WebElement linkFirstRep;


    public ProfilePage(WebDriver driver, String name) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.name = name;
    }

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public String getNameAllRepositories() {
        linkAllRepositories.click();
        String str = userRepositoriesList.getText();
        return str.toString();
    }

    public void viewAllRepositories() {
        linkAllRepositories.click();
    }

    public int getIntCounter() {
        String str = counter.getText();
        int count = Integer.parseInt(str);
        return count;
    }


    public String getName() {
        return name;
    }


    public String deleteRepository() {
        viewAllRepositories();
        ProfilePage profilePage = clickRepository(linkFirstRep);
        String project = profilePage.getName();
        ProjectSettingsPage projectSettingsPage = profilePage.getSettings();
        projectSettingsPage.deleteRepo();
        return project;
    }

    private ProfilePage clickRepository(WebElement element) {
        String str = element.getText();
        element.click();
        return new ProfilePage(driver, str);
    }

    public ProjectSettingsPage getSettings() {
        linkSettings.click();
        return new ProjectSettingsPage(driver, name);
    }

    public boolean projectIsDelete(String name) {
        viewAllRepositories();
        reposFilter.sendKeys(name);
        if (text.getText().contains(" doesn’t have any repositories that match.")) {
            return true;
        } else
            return false;
    }

}
