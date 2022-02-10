package haw41k.wheels.import2db;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class DBManager {

    private Connection jdbc;

    public DBManager(String jdbcUrl) throws SQLException {
        this.jdbc = DriverManager.getConnection(jdbcUrl);
    }

    public void initDb() throws SQLException {
        Statement stmt = jdbc.createStatement();

        String query =
                "DROP TABLE IF EXISTS\n" +
                        "Wheel_Params,\n" +
                        "Brands,\n" +
                        "Colors,\n" +
                        "Models;\n" +
                        "\n" +
                        "CREATE TABLE\n" +
                        "Brands(\n" +
                        "\tid INT PRIMARY KEY AUTO_INCREMENT,\n" +
                        "\tname VARCHAR(255)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE\n" +
                        "Models(\n" +
                        "\tid INT PRIMARY KEY AUTO_INCREMENT,\n" +
                        "\tbrand_id INT,\n" +
                        "\tmodel VARCHAR(255),\n" +
                        "\t\n" +
                        "\tFOREIGN KEY (brand_id)  REFERENCES Brands (id) ON DELETE CASCADE\n" +
                        ");\n" +
                        "CREATE TABLE\n" +
                        "Colors(\n" +
                        "\tid INT PRIMARY KEY AUTO_INCREMENT,\n" +
                        "\tbrand_id INT,\n" +
                        "\tcolor VARCHAR(255),\n" +
                        "\tdescription VARCHAR(255),\n" +
                        "\t\n" +
                        "\tFOREIGN KEY (brand_id)  REFERENCES Brands (id) ON DELETE CASCADE\n" +
                        ");\n" +
                        "CREATE TABLE\n" +
                        "Wheel_Params(\n" +
                        "\tid INT PRIMARY KEY AUTO_INCREMENT,\n" +
                        "\tmodel_id INT,\n" +
                        "\tsize_diameter TINYINT,\n" +
                        "\tsize_width REAL,\n" +
                        "\tpcd_count TINYINT,\n" +
                        "\tpcd_diameter REAL,\n" +
                        "\tet REAL,\n" +
                        "\tdia REAL,\n" +
                        "\tcolor_id INT,\n" +
                        "\tfactory_code INT,\n" +
                        "\n" +
                        "\tFOREIGN KEY (Model_id)  REFERENCES Models (id) ON DELETE CASCADE\n" +
                        ");";

        stmt.execute(query);
        stmt.close();

    }

    public void addColors(Set<String> colors, String brandName) throws SQLException {
        String query =
                "INSERT INTO Colors (brand_id, color)" +
                "VALUES ((SELECT id FROM Brands WHERE name = ? LIMIT 1), ?);";

        PreparedStatement stmt = jdbc.prepareStatement(query);

        for (String color : colors) {
            stmt.setString(1, brandName);
            stmt.setString(2, color);
            stmt.execute();
        }
        stmt.close();
    }

    public void addModels(Set<String> models, String brandName) throws SQLException {
        String query =
                "INSERT INTO Models (brand_id, model)" +
                "VALUES ((SELECT id FROM Brands WHERE name = ? LIMIT 1), ?);";

        PreparedStatement stmt = jdbc.prepareStatement(query);

        for (String model : models) {
            stmt.setString(1, brandName);
            stmt.setString(2, model);
            stmt.execute();
        }
        stmt.close();
    }

    public void addBrand(String brand) throws SQLException {
        String query = "INSERT INTO Brands (name) VALUES (?);";

        PreparedStatement stmt = jdbc.prepareStatement(query);
        stmt.setString(1, brand);
        stmt.execute();
        stmt.close();
    }

    public void addWheelParams(List<Wheel> wheels) throws SQLException {
        String query =
                "INSERT INTO " +
                        "Wheel_Params (" +
                        "model_id, size_diameter, size_width, pcd_count, pcd_diameter, et, dia, color_id, factory_code" +
                        ") VALUES (" +
                        "(SELECT id FROM Models WHERE model = ? LIMIT 1), " +
                        "?, ?, ?, ?, ?, ?, " +
                        "(SELECT id FROM Colors WHERE color = ? LIMIT 1), ?" +
                        ");";

        PreparedStatement stmt = jdbc.prepareStatement(query);

        for (Wheel w : wheels) {
            stmt.setString(1, w.getModel());

            stmt.setByte(2, w.getSizeDiameter());
            stmt.setFloat(3, w.getSizeWidth());

            stmt.setByte(4, w.getPcdCount());
            stmt.setFloat(5, w.getPcdDiameter());

            stmt.setFloat(6, w.getEt());
            stmt.setFloat(7, w.getDia());

            stmt.setString(8, w.getColor());
            stmt.setInt(9, w.getFactoryCode());

            stmt.execute();

        }
        stmt.close();
    }

}