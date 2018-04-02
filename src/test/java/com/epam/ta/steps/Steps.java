package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Steps {
    private WebDriver driver;

    private final Logger logger = LogManager.getRootLogger();

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void loginGithub(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean isLoggedIn(String username) {
        LoginPage loginPage = new LoginPage(driver);
        String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
        logger.info("Actual username: " + actualUsername);
        return actualUsername.equals(username);
    }

    public boolean logoutGitHub() {
        MainPage mainPage = new MainPage(driver);
        mainPage.logOut();
        return driver.getCurrentUrl().equals("https://github.com/");
    }

    public boolean createNewRepository(String repositoryName, String repositoryDescription) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
        return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
    }

    public boolean currentRepositoryIsEmpty() {
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.isCurrentRepositoryEmpty();
    }

    public ProfilePage openProfilePage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openProfilePage();
        return new ProfilePage(driver);
    }


    public MainPage openMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        return new MainPage(driver);
    }

    public String findAllRepository() {
        ProfilePage profilePage = new ProfilePage(driver);
        String str = profilePage.getNameAllRepositories();
        return str;
    }

    public int getCounts() {
        ProfilePage profilePage = new ProfilePage(driver);
        return profilePage.getIntCounter();
    }


    public boolean deleteRepository(int count) {
        boolean result = true;
        for (int i = 0; i < count; i++) {
            result = clickSettingsAndDeleteRepository();
        }
        return result;
    }

    public boolean clickSettingsAndDeleteRepository() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        String project = profilePage.deleteRepository();
        return testDeleteProject(project);
    }

    public boolean testDeleteProject(String name) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        return profilePage.projectIsDelete(name);
    }


}
