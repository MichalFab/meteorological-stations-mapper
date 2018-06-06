package mapper;

import models.Coordinates;
import models.StationEntity;

import java.util.List;

public class CoordinatesAdder {


    public List<StationEntity> addCoordinates(List<StationEntity> stationsWithoutCoordinates) {
        GoogleLocationManager googleLocationManager = new GoogleLocationManager();
        for (StationEntity station : stationsWithoutCoordinates) {
            Coordinates coordinates = googleLocationManager.getCoordinatesForLocation(station.getAddress(), station.getCity());
            station.setLatitude(coordinates.getLatitude());
            station.setLongitude(coordinates.getLongitude());
        }
        return stationsWithoutCoordinates;
    }


}
