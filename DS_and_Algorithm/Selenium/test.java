import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class SeleniumExample {

    public static void main(String[] args) {
        // Setup WebDriver
        Systm.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChrmDriver();

        // Configure WebDriver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Configure WebDriver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Navigate to a webpage
        driver.get("https://example.com");

        // Wait until an element is visible and interact with it
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement someElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someId")));
        someElement.click();

        // Fill a form
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("email")).sendKeys("john.doe@example.com");

        // Select from a dropdown
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 1");

        // Check a checkbox
        WebElement checkbox = driver.findElement(By.id("checkbox"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        // Handle a table
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tableId']/tbody/tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.println(cell.getText());
            }
        }

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        // File upload
        WebElement uploadElement = driver.findElement(By.id("upload"));
        uploadElement.sendKeys("path/to/file");

        // Wait for and click a button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        submitButton.click();

        // Switch to a new window
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Handle an alert
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // Assertions (using a simple example, replace with a proper testing framework for real projects)
        String expectedTitle = "Expected Title";
        String actualTitle = driver.getTitle();
        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Title matches!");
        } else {
            System.out.println("Title does not match!");
        }

        // More interactions as needed...
        
        // Finally, close the browser
        driver.quit();
    }

    @Test
public void login() {
System.setProperty("webdriver.chrome.driver", "path of driver");
WebDriver driver=new ChromeDriver();
driver.manage().window().maximize();
driver.get("https://www.browserstack.com/users/sign_in");
WebElement username=driver.findElement(By.id("user_email_Login"));
WebElement password=driver.findElement(By.id("user_password"));
WebElement login=driver.findElement(By.name("commit"));
username.sendKeys("abc@gmail.com");
password.sendKeys("your_password");
login.click();
String actualUrl="https://live.browserstack.com/dashboard";
String expectedUrl= driver.getCurrentUrl();
Assert.assertEquals(expectedUrl,actualUrl);
}
@Tag("UnitTests")
class ArchitectureTest {

  @Test
  void anyMatchesX86() {
    assertThat(ANY.is(X86)).isTrue();
  }

  @Test
  void anyMatchesX64() {
    assertThat(ANY.is(X64)).isTrue();
  }

  @Test
  void anyMatchesARM() {
    assertThat(ANY.is(ARM)).isTrue();
  }

  @Test
  void anyMatchesANY() {
    assertThat(ANY.is(ANY)).isTrue();
  }

  @Test
  void currentArchitecture() {
    Architecture current = Architecture.getCurrent();
    assertThat(current).isNotNull();
    assertThat(current.is(ANY)).isFalse();
  }

  @Test
  void determineArchI386() {
    assertThat(Architecture.extractFromSysProperty("i386").is(X86)).isTrue();
  }

  @Test
  void determineArchIA32() {
    assertThat(Architecture.extractFromSysProperty("ia32").is(X86)).isTrue();
  }

  @Test
  void determineArchI686() {
    assertThat(Architecture.extractFromSysProperty("i686").is(X86)).isTrue();
  }

  @Test
  void determineArchI486() {
    assertThat(Architecture.extractFromSysProperty("i486").is(X86)).isTrue();
  }

  @Test
  void determineArchI86() {
    assertThat(Architecture.extractFromSysProperty("i86").is(X86)).isTrue();
  }

  @Test
  void determineArchPentium() {
    assertThat(Architecture.extractFromSysProperty("pentium").is(X86)).isTrue();
  }

  @Test
  void determineArchPentiumPro() {
    assertThat(Architecture.extractFromSysProperty("pentium_pro").is(X86)).isTrue();
  }

  @Test
  void determineArchPentiumProMmx() {
    assertThat(Architecture.extractFromSysProperty("pentium_pro+mmx").is(X86)).isTrue();
  }

  @Test
  void determineArchPentiumMmx() {
    assertThat(Architecture.extractFromSysProperty("pentium+mmx").is(X86)).isTrue();
  }

  @Test
  void determineArchAMD64() {
    assertThat(Architecture.extractFromSysProperty("amd64").is(X64)).isTrue();
  }

  @Test
  void determineArchIA64() {
    assertThat(Architecture.extractFromSysProperty("ia64").is(X64)).isTrue();
  }

  @Test
  void determineArchARM() {
    assertThat(Architecture.extractFromSysProperty("arm").is(ARM)).isTrue();
  }

  @Test
  void determineArchEmpty() {
    assertThatExceptionOfType(UnsupportedOperationException.class)
        .isThrownBy(() -> Architecture.extractFromSysProperty(""))
        .withMessageContaining("Unknown architecture");
  }

  @Test
  void determineArchBogus() {
    assertThatExceptionOfType(UnsupportedOperationException.class)
        .isThrownBy(() -> Architecture.extractFromSysProperty("hoobaflooba"))
        .withMessageContaining("Unknown architecture");
  }

  @Test
  void determineArchMixedCasing() {
    assertThat(Architecture.extractFromSysProperty("AmD64").is(X64)).isTrue();
  }

  @Test
  void dataModelIs32Or64BitOnCurrentArchitecture() {
    int model = Architecture.getCurrent().getDataModel();
    assertThat(model == 32 || model == 64).isTrue();
  }

  @Test
  void x86DataModelIs32Bit() {
    assertThat(X86.getDataModel()).isEqualTo(32);
  }

  @Test
  void x64DataModelIs64Bit() {
    assertThat(X64.getDataModel()).isEqualTo(64);
  }

  @Test
  void armDataModelIs64Bit() {
    assertThat(ARM.getDataModel()).isEqualTo(64);
  }

  @Test
  void anyDataModelIs64Bit() {
    assertThat(ANY.getDataModel()).isEqualTo(64);
  }
  private static final boolean UNSELECTED = false;
  private static final boolean SELECTED = true;

  @Test
  void testShouldBeAbleToSelectAnEnabledUnselectedCheckbox() {
    driver.get(pages.formPage);
    assertCanSelect(enabledUnselectedCheckbox());
  }

  @Test
  void testShouldBeAbleToSelectAnEnabledUnselectedRadioButton() {
    driver.get(pages.formPage);
    assertCanSelect(enabledUnselectedRadioButton());
  }

  @Test
  void testShouldNotBeAbleToSelectADisabledCheckbox() {
    driver.get(pages.formPage);
    assertCannotSelect(disabledUnselectedCheckbox());
  }

  @Test
  void testShouldNotBeAbleToSelectADisabledCheckboxDisabledWithRandomString() {
    driver.get(pages.formPage);
    assertCannotSelect(randomlyDisabledSelectedCheckbox());
  }

  @Test
  void testShouldNotBeAbleToSelectADisabledRadioButton() {
    driver.get(pages.formPage);
    assertCannotSelect(disabledUnselectedRadioButton());
  }

  @Test
  void testShouldNotBeAbleToSelectADisabledRadioButtonDisabledWithRandomString() {
    driver.get(pages.formPage);
    assertCannotSelect(randomlyDisabledUnselectedRadioButton());
  }

  @Test
  void testSelectingRadioButtonShouldUnselectItsSibling() {
    driver.get(pages.formPage);

    WebElement originallySelected = enabledSelectedRadioButton();
    assertSelected(originallySelected);

    WebElement toSelect = enabledUnselectedRadioButton();
    assertNotSelected(toSelect);

    toSelect.click();
    assertNotSelected(originallySelected);
    assertSelected(toSelect);
  }

  @Test
  void testShouldBeAbleToToggleAnEnabledUnselectedCheckbox() {
    driver.get(pages.formPage);
    assertCanToggle(enabledUnselectedCheckbox());
  }

  @Test
  void testShouldBeAbleToToggleAnEnabledSelectedCheckbox() {
    driver.get(pages.formPage);
    assertCanToggle(enabledSelectedCheckbox());
  }

  @Test
  void testClickingOnASelectedRadioButtonShouldLeaveItSelected() {
    driver.get(pages.formPage);

    WebElement button = enabledSelectedRadioButton();
    assertThat(button.isSelected()).isTrue();

    button.click();

    assertThat(button.isSelected()).isTrue();
  }

  @Test
  void testShouldBeAbleToToggleEnabledMultiSelectOption() {
    driver.get(pages.formPage);
    assertCanToggle(selectedMultipleSelectOption());
  }

  @Test
  void testShouldBeAbleToToggleSelectableCheckboxByClickingOnIt() {
    driver.get(pages.formPage);

    WebElement checkbox = enabledUnselectedCheckbox();
    assertNotSelected(checkbox);

    checkbox.click();
    assertSelected(checkbox);

    checkbox.click();
    assertNotSelected(checkbox);
  }

  @Test
  void testShouldBeAbleToSelectSelectableRadioButtonByClickingOnIt() {
    driver.get(pages.formPage);

    WebElement radioButton = enabledUnselectedRadioButton();
    assertNotSelected(radioButton);

    radioButton.click();
    assertSelected(radioButton);

    radioButton.click();
    assertSelected(radioButton);
  }

  @Test
  void testClickingDisabledSelectedCheckboxShouldBeNoop() {
    driver.get(pages.formPage);
    assertClickingPreservesCurrentlySelectedStatus(randomlyDisabledSelectedCheckbox());
  }

  @Test
  void testClickingDisabledUnselectedCheckboxShouldBeNoop() {
    driver.get(pages.formPage);
    assertClickingPreservesCurrentlySelectedStatus(disabledUnselectedCheckbox());
  }

  @Test
  void testClickingDisabledSelectedRadioButtonShouldBeNoop() {
    driver.get(pages.formPage);
    assertClickingPreservesCurrentlySelectedStatus(disabledSelectedRadioButton());
  }

  @Test
  void testClickingDisabledUnselectedRadioButtonShouldBeNoop() {
    driver.get(pages.formPage);
    assertClickingPreservesCurrentlySelectedStatus(disabledUnselectedRadioButton());
  }

  private void assertNotSelected(WebElement element) {
    assertSelected(element, UNSELECTED);
  }

  private void assertSelected(WebElement element) {
    assertSelected(element, SELECTED);
  }

  private void assertSelected(WebElement element, boolean isSelected) {
    wait.until(ExpectedConditions.elementSelectionStateToBe(element, isSelected));
    assertThat(element.isSelected())
        .describedAs(
            "Expected element %s to be %s",
            describe(element), selectedToString(isSelected), selectedToString(!isSelected))
        .isEqualTo(isSelected);
  }

  private void assertCannotSelect(WebElement element) {
    boolean previous = element.isSelected();
    element.click();
    assertThat(element.isSelected()).isEqualTo(previous);
  }

  private void assertCanSelect(WebElement element) {
    assertNotSelected(element);

    element.click();
    assertSelected(element);
  }

  private void assertClickingPreservesCurrentlySelectedStatus(WebElement element) {
    boolean currentSelectedStatus = element.isSelected();
    element.click();
    assertSelected(element, currentSelectedStatus);
  }

  private static String selectedToString(boolean isSelected) {
    return isSelected ? "[selected]" : "[not selected]";
  }

  private static String describe(WebElement element) {
    return element.getAttribute("id");
  }

  private void assertCanToggle(WebElement element) {
    final boolean originalState = element.isSelected();

    assertSelected(element, originalState);

    assertTogglingSwapsSelectedStateFrom(element, originalState);
    assertTogglingSwapsSelectedStateFrom(element, !originalState);
  }

  private void assertTogglingSwapsSelectedStateFrom(WebElement element, boolean originalState) {
    element.click();
    boolean isNowSelected = element.isSelected();
    assertThat(isNowSelected)
        .describedAs(
            "Expected element %s to have been toggled to %s",
            describe(element), selectedToString(!originalState))
        .isEqualTo(!(originalState));
    assertSelected(element, !originalState);
  }

  // TODO: Test disabled multi-selects
  // TODO: Test selecting options

  private WebElement enabledUnselectedCheckbox() {
    return driver.findElement(By.id("checky"));
  }

  private WebElement enabledSelectedCheckbox() {
    return driver.findElement(By.id("checkedchecky"));
  }

  private WebElement disabledUnselectedCheckbox() {
    return driver.findElement(By.id("disabledchecky"));
  }

  private WebElement randomlyDisabledSelectedCheckbox() {
    return driver.findElement(By.id("randomly_disabled_checky"));
  }

  private WebElement enabledUnselectedRadioButton() {
    return driver.findElement(By.id("peas"));
  }

  private WebElement enabledSelectedRadioButton() {
    return driver.findElement(By.id("cheese_and_peas"));
  }

  private WebElement disabledSelectedRadioButton() {
    return driver.findElement(By.id("lone_disabled_selected_radio"));
  }

  private WebElement disabledUnselectedRadioButton() {
    return driver.findElement(By.id("nothing"));
  }

  private WebElement randomlyDisabledUnselectedRadioButton() {
    return driver.findElement(By.id("randomly_disabled_nothing"));
  }

  private WebElement selectedMultipleSelectOption() {
    WebElement select = driver.findElement(By.name("multi"));
    return select.findElements(By.tagName("option")).get(0);
  }
  @Test
  void testSameElementLookedUpDifferentWaysShouldBeEqual() {
    driver.get(pages.simpleTestPage);

    WebElement body = driver.findElement(By.tagName("body"));
    WebElement xbody = driver.findElements(By.xpath("//body")).get(0);

    assertThat(xbody).isEqualTo(body);
  }

  @Test
  void testDifferentElementsShouldNotBeEqual() {
    driver.get(pages.simpleTestPage);

    List<WebElement> ps = driver.findElements(By.tagName("p"));

    assertThat(ps.get(0).equals(ps.get(1))).isFalse();
  }

  @Test
  void testSameElementLookedUpDifferentWaysUsingFindElementShouldHaveSameHashCode() {
    driver.get(pages.simpleTestPage);
    WebElement body = driver.findElement(By.tagName("body"));
    WebElement xbody = driver.findElement(By.xpath("//body"));

    assertThat(xbody.hashCode()).isEqualTo(body.hashCode());
  }

  @Test
  void testSameElementLookedUpDifferentWaysUsingFindElementsShouldHaveSameHashCode() {
    driver.get(pages.simpleTestPage);
    List<WebElement> body = driver.findElements(By.tagName("body"));
    List<WebElement> xbody = driver.findElements(By.xpath("//body"));

    assertThat(xbody.get(0).hashCode()).isEqualTo(body.get(0).hashCode());
  }

  @SwitchToTopAfterTest
  @Test
  @NotYetImplemented(SAFARI)
  public void testAnElementFoundInViaJsShouldHaveSameId() {
    driver.get(pages.missedJsReferencePage);

    driver.switchTo().frame("inner");
    WebElement first = driver.findElement(By.id("oneline"));

    WebElement element =
        (WebElement)
            ((JavascriptExecutor) driver)
                .executeScript("return document.getElementById('oneline');");

    checkIdEqualityIfRemote(first, element);
  }

  private void checkIdEqualityIfRemote(WebElement first, WebElement second) {
    String firstId = getId(unwrapIfNecessary(first));
    String secondId = getId(unwrapIfNecessary(second));

    assertThat(secondId).isEqualTo(firstId);
  }

  private String getId(WebElement element) {
    if (!(element instanceof RemoteWebElement)) {
      System.err.println("Skipping remote element equality test - not a remote web driver");
      return null;
    }

    return ((RemoteWebElement) element).getId();
  }

  private WebElement unwrapIfNecessary(WebElement element) {
    if (element instanceof WrapsElement) {
      return ((WrapsElement) element).getWrappedElement();
    }
    return element;
  }
  // Note: If this test ever fixed/enabled on Firefox, check if it also needs @NoDriverAfterTest OR
  // if @NoDriverAfterTest can be removed from some other tests in this class.
  @Test
  @NotYetImplemented(SAFARI)
  public void testPageLoadTimeoutCanBeChanged() {
    testPageLoadTimeoutIsEnforced(2);
    testPageLoadTimeoutIsEnforced(3);
  }

  @Test
  @NotYetImplemented(SAFARI)
  public void testCanHandleSequentialPageLoadTimeouts() {
    long pageLoadTimeout = 2;
    long pageLoadTimeBuffer = 10;
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
    assertPageLoadTimeoutIsEnforced(pageLoadTimeout, pageLoadTimeBuffer);
    assertPageLoadTimeoutIsEnforced(pageLoadTimeout, pageLoadTimeBuffer);
  }

  @Test
  void testShouldTimeoutIfAPageTakesTooLongToLoad() {
    try {
      testPageLoadTimeoutIsEnforced(2);
    } finally {
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
    }

    // Load another page after get() timed out but before test HTTP server served previous page.
    driver.get(pages.xhtmlTestPage);
    wait.until(titleIs("XHTML Test Page"));
  }

  @Test
  @Ignore(value = SAFARI, reason = "Flaky")
  public void testShouldTimeoutIfAPageTakesTooLongToLoadAfterClick() {
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));

    driver.get(appServer.whereIs("page_with_link_to_slow_loading_page.html"));
    WebElement link = wait.until(visibilityOfElementLocated(By.id("link-to-slow-loading-page")));

    long start = System.currentTimeMillis();
    try {
      link.click();
      fail("I should have timed out");
    } catch (RuntimeException e) {
      long end = System.currentTimeMillis();

      assertThat(e).isInstanceOf(TimeoutException.class);

      int duration = (int) (end - start);
      assertThat(duration).isGreaterThan(2000);
      assertThat(duration).isLessThan(5000);
    } finally {
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
    }

    // Load another page after get() timed out but before test HTTP server served previous page.
    driver.get(pages.xhtmlTestPage);
    wait.until(titleIs("XHTML Test Page"));
  }

  @Test
  @Ignore(value = CHROME, reason = "Flaky")
  @Ignore(value = EDGE, reason = "Flaky")
  @NeedsFreshDriver
  public void testShouldTimeoutIfAPageTakesTooLongToRefresh() {
    // Get the sleeping servlet with a pause of 5 seconds
    String slowPage = appServer.whereIs("sleep?time=5");

    driver.get(slowPage);

    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));

    long start = System.currentTimeMillis();
    try {
      driver.navigate().refresh();
      fail("I should have timed out");
    } catch (RuntimeException e) {
      long end = System.currentTimeMillis();

      assertThat(e).isInstanceOf(TimeoutException.class);

      int duration = (int) (end - start);
      assertThat(duration).isGreaterThanOrEqualTo(2000);
      assertThat(duration).isLessThan(4000);
    } finally {
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
    }

    // Load another page after get() timed out but before test HTTP server served previous page.
    driver.get(pages.xhtmlTestPage);
    wait.until(titleIs("XHTML Test Page"));
  }

  @Test
  @NotYetImplemented(CHROME)
  @NotYetImplemented(EDGE)
  @NotYetImplemented(value = SAFARI)
  public void testShouldNotStopLoadingPageAfterTimeout() {
    try {
      testPageLoadTimeoutIsEnforced(1);
    } finally {
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
    }

    new WebDriverWait(driver, Duration.ofSeconds(30))
        .ignoring(StaleElementReferenceException.class)
        .until(elementTextToEqual(By.tagName("body"), "Slept for 11s"));
  }

  /**
   * Sets given pageLoadTimeout to the {@link #driver} and asserts that attempt to navigate to a
   * page that takes much longer (10 seconds longer) to load results in a TimeoutException.
   *
   * <p>Side effects: 1) {@link #driver} is configured to use given pageLoadTimeout, 2) test HTTP
   * server still didn't serve the page to browser (some browsers may still be waiting for the page
   * to load despite the fact that driver responded with the timeout).
   */
  private void testPageLoadTimeoutIsEnforced(long webDriverPageLoadTimeout) {
    // Test page will load this many seconds longer than WD pageLoadTimeout.
    long pageLoadTimeBuffer = 10;
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(webDriverPageLoadTimeout));
    assertPageLoadTimeoutIsEnforced(webDriverPageLoadTimeout, pageLoadTimeBuffer);
  }

  private void assertPageLoadTimeoutIsEnforced(
      long webDriverPageLoadTimeout, long pageLoadTimeBuffer) {
    long start = System.currentTimeMillis();
    try {
      driver.get(
          appServer.whereIs("sleep?time=" + (webDriverPageLoadTimeout + pageLoadTimeBuffer)));
      fail("I should have timed out after " + webDriverPageLoadTimeout + " seconds");
    } catch (RuntimeException e) {
      long end = System.currentTimeMillis();

      assertThat(e).isInstanceOf(TimeoutException.class);

      long duration = end - start;
      assertThat(duration).isGreaterThanOrEqualTo(webDriverPageLoadTimeout * 1000);
      assertThat(duration).isLessThan((webDriverPageLoadTimeout + pageLoadTimeBuffer) * 1000);
    }
  }
}
