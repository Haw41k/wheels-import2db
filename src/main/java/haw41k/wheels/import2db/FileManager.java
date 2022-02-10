package haw41k.wheels.import2db;

import haw41k.wheels.import2db.adapters.FileAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileManager {
    private Logger logger = LogManager.getLogger(this.getClass());

    private String filePath;
    private FileAdapter fileAdapter;

    private List<Wheel> wheels;
    private Set<String> models;
    private Set<String> colors;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileAdapter(FileAdapter fileAdapter) {
        this.fileAdapter = fileAdapter;
    }

    public boolean readFile() {
        if (filePath == null) {
            logger.error("File path needed");
            return false;
        }
        if (fileAdapter == null) {
            logger.error("File Adapter needed");
            return false;
        }

        logger.info("start reading file brand: " + fileAdapter.getBrandName());

        try (InputStream file = new FileInputStream(filePath + fileAdapter.getBrandName() + ".xls")) {
            wheels = new LinkedList<>();
            models = new LinkedHashSet<>();
            colors = new TreeSet<>();


            Workbook wb = WorkbookFactory.create(file);
            Sheet sheet = wb.getSheetAt(0);
            Row row;
            Wheel wheel;

            for (int i = fileAdapter.getIndexFirstRow(); i < sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (
                        row.getCell(fileAdapter.getIndexModel()) == null ||
                        row.getCell(fileAdapter.getIndexModel()).toString().equals("")
                ) continue;

                try {
                    wheel = new Wheel();

                    wheel.setModel(
                            row.getCell(fileAdapter.getIndexModel()).getStringCellValue());

                    wheel.setSizeDiameter(
                            (byte) row.getCell(fileAdapter.getIndexSizeDiameter()).getNumericCellValue());
                    wheel.setSizeWidth(
                            (float) row.getCell(fileAdapter.getIndexSizeWidth()).getNumericCellValue());

                    wheel.setPcdCount(
                            (byte) row.getCell(fileAdapter.getIndexPcdCount()).getNumericCellValue());
                    wheel.setPcdDiameter(
                            (float) row.getCell(fileAdapter.getIndexPcdDiameter()).getNumericCellValue());

                    wheel.setEt(
                            (float) row.getCell(fileAdapter.getIndexEt()).getNumericCellValue());
                    wheel.setDia(
                            (float) row.getCell(fileAdapter.getIndexDia()).getNumericCellValue());

                    wheel.setColor(
                            row.getCell(fileAdapter.getIndexColor()).getStringCellValue().trim());
                    wheel.setFactoryCode(
                            (int) Float.parseFloat(row.getCell(fileAdapter.getIndexFactoryCode()).toString()));

                } catch (IllegalStateException e) {
                    logger.warn("Not valid cell(s) in row " + i + 1);
                    continue;
                }

                wheels.add(wheel);
                models.add(wheel.getModel());
                colors.add(wheel.getColor());

            }
            logger.info("Complete read file brand: " + fileAdapter.getBrandName());
            logger.info("found " + wheels.size() + " wheels");
            logger.info("found " + models.size() + " models");
            logger.info("found " + colors.size() + " colors");

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("IO Error");
            e.printStackTrace();
        }

        return true;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public Set<String> getModels() {
        return models;
    }

    public Set<String> getColors() {
        return colors;
    }
}
