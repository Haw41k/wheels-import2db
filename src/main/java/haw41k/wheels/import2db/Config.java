package haw41k.wheels.import2db;

public class Config {
    private static final String USER_DIRECTORY = System.getProperty("user.home");
    public static final String PATH_IMPORT_FILES = USER_DIRECTORY + "\\Desktop\\YST WHEELS\\";
    public static final String JDBC_URL = "jdbc:h2:" + PATH_IMPORT_FILES + "\\exportedDB\\yst_db";
}
