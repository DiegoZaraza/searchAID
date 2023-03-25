package org.searchaid.steps;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.searchaid.pageObject.AidsPage;
import org.searchaid.pageObject.BasePage;
import org.searchaid.pageObject.IndexPage;
import org.searchaid.utilities.PropertiesRead;

public class StepDefs {
    private static final String PAGE = PropertiesRead.readFromFrameworkConfig("URL");
    private WebDriver webDriver;
    private SoftAssertions softAssertions;
    private Faker faker;
    private IndexPage indexPage;
    private AidsPage aidsPage;
    private int size;


    @Before
    public void setup() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        webDriver = WebDriverManager.chromedriver().create();
        indexPage = new IndexPage(webDriver);
        aidsPage = new AidsPage(webDriver);
        softAssertions = new SoftAssertions();
        faker = new Faker();
        webDriver.manage().window().maximize();
    }
    @Given("I enter on the google page")
    public void iEnterOnTheGooglePage() {
        BasePage.setImplicitlyWait();
        System.out.println(PAGE);
        webDriver.get(PAGE);
    }

    @When("I search {string}")
    public void iSearch(String arg0) {
        indexPage.setTextBoxSearch(arg0);
    }
    @Then("I validate the result is greater than zero with the domain")
    public void iValidateTheResultIsGreaterThanZeroWithTheDomain() {
        size = indexPage.results();
        softAssertions.assertThat(size > 0);
    }
    @Then("I validate the title and enter on menu options")
    public void iValidateTheTitleAndEnterOnMenuOptions() {
        for(int i = 0; i < size; i++){
            indexPage.getLinks(i);
            softAssertions.assertThat(webDriver.getTitle().length() > 0);
            aidsPage.clickOnMenu();
            webDriver.navigate().back();
            webDriver.navigate().back();
        }
    }
    @After
    public void end() {
        softAssertions.assertAll();
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
