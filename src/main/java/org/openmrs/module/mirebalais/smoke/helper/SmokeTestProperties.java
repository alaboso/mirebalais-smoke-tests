package org.openmrs.module.mirebalais.smoke.helper;

public class SmokeTestProperties {

    public static final int IMPLICIT_WAIT_TIME = 10;

    private String webAppUrl = null;

    private String databaseUrl = null;

    private String databaseUsername = null;

    private String databasePassword = null;

    public String getWebAppUrl() {
        if (webAppUrl == null) {
            webAppUrl = envOrDefault("WEBAPP_URL", "http://localhost:8080/openmrs");
        }

        return webAppUrl;
    }

    public String getDatabaseUrl() {
        if (databaseUrl == null) {
            databaseUrl = envOrDefault("DATABASE_URL", "jdbc:mysql://localhost:3306/openmrs");
        }

        return databaseUrl;
    }

    public String getDatabaseUsername() {
        if (databaseUsername == null) {
            databaseUsername = envOrDefault("DATABASE_USERNAME", "openmrs");
        }

        return databaseUsername;
    }

    public String getDatabasePassword() {
        if (databasePassword == null) {
            databasePassword = envOrDefault("DATABASE_PASSWORD", "openmrs");
        }

        return databasePassword;
    }

    public String getDatabaseDriverClass() {
        return "com.mysql.jdbc.Driver";
    }

    private String envOrDefault(String environmentVariable, String defaultValue) {
        return System.getenv(environmentVariable) != null ? System.getenv(environmentVariable) : defaultValue;
    }
}
