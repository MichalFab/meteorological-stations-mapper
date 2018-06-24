package mapper;

import main.AppMain;
import models.StationDailyData;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvPollutionMapper {

    private Logger logger = Logger.getLogger(AppMain.class.getName());
    private boolean hasStationCodes = false;


    public List<StationDailyData> getFromCsvPreparedPollutionEntitiesForEachDay(String fileName) {
        logger.info("Extracting data from file: " + fileName);
        List<StationDailyData> stationsMeasurement = new ArrayList<>();
        List<String> stationCodes = new ArrayList<>();
        try {
            String fileLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/measurements/".concat(fileName))));
            LocalDate dateOfMeasurement = generateFirstDateOfMeasurement(fileName);
            String typeOfPollution = generateTypeOfPollution(fileName);
            while ((fileLine = reader.readLine()) != null) {
                String[] measurement = fileLine.split(";");
                if (!hasStationCodes) {
                    stationCodes = Arrays.asList(measurement);
                    hasStationCodes = true;
                    continue;
                }
                if (measurement.length != stationCodes.size()) {
                    throw new WrongMeasurementCsvFormatException();
                }
                for (int i = 0; i < stationCodes.size(); i++) {
                    stationsMeasurement.add(new StationDailyData().builder()
                            .stationCode(stationCodes.get(i))
                            .pollutionValue(parseToDouble(measurement[i]))
                            .typeOfPollution(typeOfPollution)
                            .date(dateOfMeasurement)
                            .build());
                }
                dateOfMeasurement = dateOfMeasurement.plusDays(1);
            }
            hasStationCodes = false;
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Not found file for given location");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Wrong file");
        } catch (WrongMeasurementCsvFormatException e) {
            logger.warning("Number of stations codes are not the same as number of measurement!");
        }
        return stationsMeasurement;
    }

    private String generateTypeOfPollution(String fileName) {
        String[] splittedFilename = fileName.split("-");
        if (splittedFilename.length < 2) {
            throw new RuntimeException("Wrong measurement filename");
        }
        return splittedFilename[1];
    }

    private double parseToDouble(String meas) {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number numberMes = null;
        try {
            numberMes = format.parse(meas);
        } catch (ParseException e) {
            logger.warning("Exception while parsing measurement data to Double!");
        }
        return Objects.requireNonNull(numberMes).doubleValue();
    }

    private LocalDate generateFirstDateOfMeasurement(String fileName) {
        String yearOfMeasurements = fileName
                .split("-")[0]
                .concat("-01-01");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        return LocalDate.parse(yearOfMeasurements, formatter).plusDays(1);
    }

    public File[] getAllMeasurementFilesFromFolder() {
        File locationFolder = new File("C:\\Users\\Michał\\IdeaProjects\\meteorological-stations-mapper\\src\\main\\resources\\measurements");
        return locationFolder.listFiles();
    }

}
