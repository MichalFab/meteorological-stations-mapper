package main;

import mapper.CoordinatesAdder;
import mapper.StationCsvMapper;
import models.StationEntity;
import repo.StationsRepository;
import java.util.List;
import java.util.logging.Logger;

public class AppMain {

    private Logger logger = logger = Logger.getLogger(AppMain.class.getName());

    public static void main(String[] args){
        StationCsvMapper stationCsvMapper = new StationCsvMapper("C:\\Users\\Michał\\IdeaProjects\\meteorological-stations-mapper\\src\\main\\resources\\stations-data\\stations-data-csv.csv");
        List<StationEntity> stations = stationCsvMapper.getPreparedStationList();
        CoordinatesAdder coordinatesAdder = new CoordinatesAdder();
        List<StationEntity> stationsWithCoordinates = coordinatesAdder.addCoordinates(stations);
        StationsRepository repository = new StationsRepository();
        repository.openCurrentSessionwithTransaction();
        repository.addAllStations(stationsWithCoordinates);
        repository.closeCurrentSessionwithTransaction();
    }


}
