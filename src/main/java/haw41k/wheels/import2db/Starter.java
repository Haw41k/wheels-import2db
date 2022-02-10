package haw41k.wheels.import2db;

import haw41k.wheels.import2db.adapters.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class Starter {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger("Log");
        logger.info("start");

        List<FileAdapter> fileAdapters = new LinkedList<>();
        fileAdapters.add(new FileAdapterAlcasta());
        fileAdapters.add(new FileAdapterXrace());
        fileAdapters.add(new FileAdapterYokatta());
        fileAdapters.add(new FileAdapterHarp());
        fileAdapters.add(new FileAdapterBuffalo());

        try {
            FileManager fm = new FileManager();
            DBManager db = new DBManager(Config.JDBC_URL);

            db.initDb();

            for (FileAdapter fa : fileAdapters){
                fm.setFilePath(Config.PATH_IMPORT_FILES);
                fm.setFileAdapter(fa);

                if (fm.readFile()) {

                    db.addBrand(fa.getBrandName());

                    db.addModels(fm.getModels(), fa.getBrandName());
                    db.addColors(fm.getColors(), fa.getBrandName());

                    db.addWheelParams(fm.getWheels());
                }
            }
        } catch (SQLException e) {
            logger.throwing(e);
        }

        logger.info("successful");

    }
}
