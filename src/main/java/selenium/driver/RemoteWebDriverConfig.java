package selenium.driver;

import org.apache.http.util.TextUtils;

import selenium.configurations.TypedProperties;

public class RemoteWebDriverConfig {
        private final TypedProperties typedProperties = new TypedProperties("/driver_config.properties");

        String getPropertyString(String name) {
                String value = "";
                value = System.getenv(name.toUpperCase().replace(".", "_"));

                if (TextUtils.isEmpty(value)) {
                        value = System.getProperty(name);
                }

                if (TextUtils.isEmpty(value)) {
                        value = typedProperties.getValue(name);
                }

                if (TextUtils.isEmpty(value)) {
                        value = "";
                }

                return value;
        }

        int getPropertyInt(String name) {
                String value = "";
                value = this.getPropertyString(name);

                int valueInt = 0;
                
                try {
                        valueInt = Integer.parseInt(value);
                } catch (Exception Ex) {}

                return valueInt;
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
                return this.getPropertyString("browser.proxy");
        }
}
