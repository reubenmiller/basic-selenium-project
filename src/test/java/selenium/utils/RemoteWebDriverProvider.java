package selenium.utils;

import java.net.MalformedURLException;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import selenium.driver.UserAgents;
import selenium.driver.RemoteWebDriverBuilder;
import selenium.driver.RemoteWebDriverConfig;

public class RemoteWebDriverProvider extends TestWatcher {
    private final RemoteWebDriverBuilder webDriverBuilder;
    private WebDriver driver;

    public RemoteWebDriverProvider(final RemoteWebDriverConfig webDriverConfig) {
        this.webDriverBuilder = new RemoteWebDriverBuilder(webDriverConfig);
    }

    public WebDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            driver = webDriverBuilder.toWebDriver();
        }
        return driver;
    }

    public void useUserAgent(UserAgents userAgent) {
        webDriverBuilder.userAgent(userAgent);
    }

    public void disableCookies(boolean cookies) {
        webDriverBuilder.disableCookies(cookies);
    }

    public boolean existsDriver() {
        return driver != null;
    }

    @Override
    protected void starting(final Description description) {
        String methodName = description.getClassName() + "." + description.getMethodName();
        this.webDriverBuilder.setName(methodName);
    }

    @Override
    protected void finished(final Description description) {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
