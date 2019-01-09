package selenium.driver;

import selenium.configurations.TypedProperties;

public class RemoteWebDriverConfig {
        private final TypedProperties typedProperties = new TypedProperties("/driver_config.properties");

        String getPropertyValue(String name) {
                String value = System.getenv(name.toUpperCase().replace(".", "_"));

                if (!value.matches(".+")) {
                        value = System.getProperty(name);
                } else if (!value.matches(".+")) {
                        value = typedProperties.getValue(name);
                }

                return value;
        }

        String getBrowserName() {
                return typedProperties.getValue("browser.name");
        }

        int getImplicitlyWait() {
                return typedProperties.getInt("implicitly_wait");
        }

        int getDomMaxScriptRunTime() {
                return typedProperties.getInt("dom.max_script_run_time");
        }

        String getRemoteWebDriver() {
                return typedProperties.getValue("remote_webdriver");
        }

        String getBrowserProxy() {
                return this.getPropertyValue("browser.proxy");
        }
}
