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
                } else if (TextUtils.isEmpty(value)) {
                        value = typedProperties.getValue(name);
                } else {
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
                return this.getPropertyString("browser.name");
        }

        int getImplicitlyWait() {
                return this.getPropertyInt("implicitly_wait");
        }

        int getDomMaxScriptRunTime() {
                return this.getPropertyInt("dom.max_script_run_time");
        }

        String getRemoteWebDriver() {
                return this.getPropertyString("remote_webdriver");
        }

        String getBrowserProxy() {
                return this.getPropertyString("browser.proxy");
        }
}
