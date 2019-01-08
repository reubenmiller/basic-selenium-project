package selenium;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;

import selenium.configurations.TestConfig;
import selenium.driver.RemoteWebDriverConfig;
import selenium.utils.RemoteWebDriverProvider;
import selenium.utils.annotations.DisableCookies;
import selenium.utils.annotations.RepeatRule;
import selenium.utils.annotations.UserAgent;
import selenium.utils.annotations.browser.Browser;
import selenium.utils.annotations.browser.BrowserDimension;
import selenium.utils.annotations.browser.Browsers;


public abstract class RemoteSeleniumTestWrapper {

	// Config
	protected static final TestConfig testConfig = new TestConfig();
	private final RemoteWebDriverConfig webDriverConfig = new RemoteWebDriverConfig();
	protected final RemoteWebDriverProvider webDriverProvider = new RemoteWebDriverProvider(this.webDriverConfig);

	@Rule
	public RepeatRule repeatRule = new RepeatRule();

	protected WebDriver getDriver() {
        try {
            return this.webDriverProvider.getDriver();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return null;
        }
	}

	/**
	 * test class annotations
	 */
	@Before
	public void setUserAgent(){
		UserAgent userAgent = this.getClass().getAnnotation(UserAgent.class);
		if (userAgent != null) {
			webDriverProvider.useUserAgent(userAgent.value());
		}
	}

	@Before
	public void disableCookies(){
		DisableCookies cookies = this.getClass().getAnnotation(DisableCookies.class);
		if (cookies != null) {
			webDriverProvider.disableCookies(true);
		}
	}

	@Before
	public void browser() throws Exception {
		Browser browser = this.getClass().getAnnotation(Browser.class);
		if (browser != null){
			if (browser.require().length > 0 && browser.skip().length == 0){
				String browsers = concatinateBrowsers(browser.require());
				assumeTrue("only execute test against " + browsers, browsers.contains(testConfig.getBrowser()));
			}

			if (browser.skip().length > 0 && browser.require().length == 0){
				String browsers = concatinateBrowsers(browser.skip());
				assumeFalse("skip test against " + browsers, browsers.contains(testConfig.getBrowser()));
			}
		}
	}

	private String concatinateBrowsers(Browsers[] browsers){
		String concatinatedBrowsers = "";
		for(Browsers browser : browsers) concatinatedBrowsers += browser.getValue() + " & ";
		return concatinatedBrowsers.substring(0,concatinatedBrowsers.lastIndexOf("&"));
	}

	@Before
	public void browserDimension() throws MalformedURLException {
		BrowserDimension browserDimension = this.getClass().getAnnotation(BrowserDimension.class);
		if (browserDimension != null) {
			getDriver().manage().window().setSize(browserDimension.value().dimension);
		}
	}

	@After
	public void closeBrowser() throws MalformedURLException {
		getDriver().quit();
	}


}
