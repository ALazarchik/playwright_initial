package constants;

import static services.EnvironmentReaderService.getProperty;

public enum UserData {                // we can store user data in properties files

    FIRST_NAME(getProperty("firstName")),
    LAST_NAME(getProperty("lastName")),
    EMAIL(getProperty("emailAddress")),
    PASSWORD(getProperty("password")),
    PHONE_NUMBER(getProperty("phoneNumber"));

    private String value;

    UserData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
