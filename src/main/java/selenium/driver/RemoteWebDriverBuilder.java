package selenium.driver;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.net.URL;
import java.net.MalformedURLException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RemoteWebDriverBuilder {

    private String name;
    private final RemoteWebDriverConfig webDriverConfig;
    private String userAgent;
    private boolean disableCookies;

    public RemoteWebDriverBuilder(RemoteWebDriverConfig webDriverConfig) {
        this.webDriverConfig = webDriverConfig;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void userAgent(final UserAgents userAgent) {
        this.userAgent = userAgent.getValue();
    }

    public void disableCookies(boolean cookies) {
        this.disableCookies = cookies;
    }


    public WebDriver toWebDriver() throws MalformedURLException {
        DesiredCapabilitiesFactory desiredCapabilitiesFactory = new DesiredCapabilitiesFactory();
        DesiredCapabilities capabilities = desiredCapabilitiesFactory.initRemoteDesiredCapabilities(webDriverConfig, userAgent, disableCookies);
        String browser = webDriverConfig.getBrowserName();

        System.out.println("remote webdriver: " + webDriverConfig.getRemoteWebDriver());

        switch (browser) {
            case "chrome":
                final RemoteWebDriver chromeDriver = new RemoteWebDriver(new URL(webDriverConfig.getRemoteWebDriver()), capabilities);
                chromeDriver.manage().window().setSize(new Dimension(1024, 720));
                return chromeDriver;
            case "edge":
                final RemoteWebDriver edgeDriver = new RemoteWebDriver(new URL(webDriverConfig.getRemoteWebDriver()), capabilities);
                return edgeDriver;
            case "internetexplorer":
                final InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver(capabilities);
                return internetExplorerDriver;
            case "opera":
                final OperaDriver operaDriver = new OperaDriver(capabilities);
                return operaDriver;
            case "phantomjs":
                final PhantomJSDriver phantomJsWebDriver = new PhantomJSDriver(capabilities);
                phantomJsWebDriver.manage().timeouts().implicitlyWait(webDriverConfig.getImplicitlyWait(), SECONDS);
                phantomJsWebDriver.manage().timeouts().setScriptTimeout(webDriverConfig.getDomMaxScriptRunTime(), SECONDS);
                return phantomJsWebDriver;
            default:
                final RemoteWebDriver firefoxDriver = new RemoteWebDriver(new URL(webDriverConfig.getRemoteWebDriver()), capabilities);
                return firefoxDriver;
        }
    }
}
