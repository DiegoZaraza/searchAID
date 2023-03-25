package org.searchaid.pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IndexPage extends BasePage {
    @FindBy(className = "gLFyf")
    WebElement textBoxSearch;

    @FindBy(xpath = "//div[@class = \"eKjLze\"]//a[contains(@href, \"aidforaids.org\")]")
    List<WebElement> textSearchResult;

    public IndexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setTextBoxSearch(String text) {
        sendKeys(textBoxSearch, text, "");
        sendEnter(textBoxSearch);
    }

    public int results() {
        return textSearchResult.size();
    }

    public void getLinks(int i){
        click(textSearchResult.get(i), "");
    }
}
