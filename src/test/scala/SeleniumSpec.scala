import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.scalatest.flatspec.AnyFlatSpec

class SeleniumSpec extends AnyFlatSpec {

  "Selenium" should "run Chrome" in {
    System.setProperty("webdriver.chrome.driver", sys.env("CHROMEDRIVER"))

    val chromeOptions = new ChromeOptions
    chromeOptions.setBinary(sys.env("CHROME_BINARY"))
    chromeOptions.addArguments("--headless")
    chromeOptions.addArguments("--disable-dev-shm-usage") // overcome limited resource problems
    chromeOptions.addArguments("--no-sandbox")

    val driver = new ChromeDriver(chromeOptions)
    driver.get("https://www.google.com")
    Thread.sleep(3000)
    val isPageLoaded = driver.getPageSource.contains("<title>Google</title>")
    driver.quit()
    assert(isPageLoaded)
  }
}
