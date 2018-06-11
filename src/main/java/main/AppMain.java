package main;

import mapper.CoordinatesAdder;
import mapper.CsvPollutionMapper;
import mapper.StationCsvMapper;
import models.StationEntity;
import repo.StationsRepository;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class AppMain {

    private Logger logger = logger = Logger.getLogger(AppMain.class.getName());

    public static void main(String[] args){
        StationCsvMapper stationCsvMapper = new StationCsvMapper("stations-data-csv.csv");
        List<StationEntity> stations = stationCsvMapper.getPreparedStationList();
        CoordinatesAdder coordinatesAdder = new CoordinatesAdder();
        List<StationEntity> stationsWithCoordinates = coordinatesAdder.addCoordinates(stations);
        StationsRepository repository = new StationsRepository();
        repository.addAllStations(stationsWithCoordinates);


        CsvPollutionMapper pollutionMapper = new CsvPollutionMapper();
        for(File csvFile : pollutionMapper.getAllMeasurementFilesFromFolder()){
            repository.addMeasurementsList(pollutionMapper.getFromCsvPreparedPollutionEntitiesForEachDay(csvFile.getName()));
        }


    }


}
