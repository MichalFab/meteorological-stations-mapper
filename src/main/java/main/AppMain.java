package main;

import mapper.GoogleLocationManager;
import mapper.StationCsvMapper;
import repo.StationsRepository;

import java.util.logging.Logger;

public class AppMain {

    private Logger logger = logger = Logger.getLogger(AppMain.class.getName());

    public static void main(String[] args) {
        StationCsvMapper stationCsvMapper = new StationCsvMapper("C:\\Users\\Michał\\Desktop\\magister\\stationmapper\\src\\main\\resources\\stations-data\\stations-data-csv.csv");
        StationsRepository repository = new StationsRepository();
        repository.openCurrentSessionwithTransaction();
        repository.addAllStations(stationCsvMapper.getPreparedStationList());
        repository.closeCurrentSessionwithTransaction();
        GoogleLocationManager locationManager = new GoogleLocationManager();
        System.out.println(locationManager.getCoordinatesForLocation("Kotarbinskiego%203", "Myslowice").toString());
    }


}
