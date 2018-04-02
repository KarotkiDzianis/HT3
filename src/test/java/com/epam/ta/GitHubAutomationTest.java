package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.*;
import com.epam.ta.steps.Steps;


public class GitHubAutomationTest {
    private Steps steps;
    private final String USERNAME = "testautomationuser";
    private final String PASSWORD = "Time4Death!";

    @BeforeClass(description = "Init browser")
    public void initBrauser() {
        steps = new Steps();
        steps.initBrowser();
    }

    @BeforeMethod(description = "Log in to GitHub")
    public void setUp() throws Exception {
        steps.loginGithub(USERNAME, PASSWORD);

    }

    @Test
    public void oneCanCreateProject() {
        Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
        Assert.assertTrue(steps.currentRepositoryIsEmpty());
    }

    @Test(description = "Login to Github")
    public void oneCanLoginGithub() {
        Assert.assertTrue(steps.isLoggedIn(USERNAME));
    }

    @Test(description = "FindRepositoryWithWords")
    public void FindRepositoryWithWords() {
        steps.openProfilePage();
        Assert.assertTrue(steps.findAllRepository().contains("testRepo"));
    }

    @Test(description = "CounterIsWork")
    public void equalsCountsOfRepositories() {
        steps.openProfilePage();
        int beforeCreateCount = steps.getCounts();
        steps.openMainPage();
        steps.createNewRepository("TT", "auto-generated test repo");
        steps.openProfilePage();
        int afterCreateCount = steps.getCounts();
        Assert.assertTrue(afterCreateCount > beforeCreateCount);
    }

    @Test(description = "Delete repositories which are located on top of the list ")
    public void DeleteLastAddRepository() {
        Assert.assertTrue(steps.deleteRepository(1));
    }

    @AfterMethod(description = "Log out")
    public void tearDown() throws Exception {
        steps.logoutGitHub();
    }


    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();
    }

}
