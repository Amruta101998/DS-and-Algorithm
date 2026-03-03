// src/main/java/SearchResultsPage.java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
    private WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getNumberOfResults() {
        // Example: Extract the number of search results
        WebElement resultsStats = driver.findElement(By.id("result-stats"));
        String statsText = resultsStats.getText();
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d+");
        java.util.regex.Matcher matcher = pattern.matcher(statsText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        } else {
            return 0;
        }
    }
}
