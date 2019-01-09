package selenium.driver;

import org.apache.http.util.TextUtils;

import selenium.configurations.TypedProperties;

public class RemoteWebDriverConfig {
        private final TypedProperties typedProperties = new TypedProperties("/driver_config.properties");

        String getPropertyString(String name, String defaultValue) {
                String value = "";
                value = System.getenv(name.toUpperCase().replace(".", "_"));

                if (TextUtils.isEmpty(value)) {
                        value = System.getProperty(name);
                }

                if (TextUtils.isEmpty(value)) {
                        value = typedProperties.getValue(name);
                }

                if (TextUtils.isEmpty(value)) {
                        value = defaultValue;
                }

                return value;
        }

        int getPropertyInt(String name, int defaultValue) {
                String value = "";
                value = this.getPropertyString(name, "");

                int valueInt = defaultValue;
                
                try {
                        valueInt = Integer.parseInt(value);
                } catch (Exception Ex) {}

                return valueInt;
        }

        String getBrowserName() {
                return this.getPropertyString("browser.name", "");
        }

        int getImplicitlyWait() {
                return this.getPropertyInt("implicitly_wait", 0);
        }

        int getDomMaxScriptRunTime() {
                return this.getPropertyInt("dom.max_script_run_time", 0);
        }

        String getRemoteWebDriver() {
                return this.getPropertyString("remote_webdriver", "");
        }

        String getBrowserProxy() {
                return this.getPropertyString("browser.proxy", "");
        }
}
