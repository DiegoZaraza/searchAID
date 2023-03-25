package org.searchaid.pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class AidsPage extends BasePage{
    @FindBy(xpath = "//ul[@id=\"primary-menu\"]//a[@data-level=\"1\"]")
    List<WebElement> optionsMenu;
    public AidsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnMenu(){
        Random random = new Random();
        int option = (random.nextInt(optionsMenu.size() - 1) + 1) - 1;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(optionsMenu.get(option), "");
    }
}
