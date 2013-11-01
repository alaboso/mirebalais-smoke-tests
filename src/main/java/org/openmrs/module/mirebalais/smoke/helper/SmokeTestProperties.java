package org.openmrs.module.mirebalais.smoke.helper;

public class SmokeTestProperties {

    public String getWebAppUrl() {
        return envOrDefault("WEBAPP_URL", "http://localhost:8080/openmrs");
    }

    public String getDatabaseUrl() {
        return envOrDefault("DATABASE_URL", "jdbc:mysql://localhost:3306/bamboo66");
    }

    public String getDatabaseUsername() {
        return envOrDefault("DATABASE_USERNAME", "root");
    }

    public String getDatabasePassword() {
        return envOrDefault("DATABASE_PASSWORD", "sa05ntk");
    }

    public String getDatabaseDriverClass() {
        return "com.mysql.jdbc.Driver";
    }

    private String envOrDefault(String environmentVariable, String defaultValue) {
        return System.getenv(environmentVariable) != null ? System.getenv(environmentVariable) : defaultValue;
    }
}
