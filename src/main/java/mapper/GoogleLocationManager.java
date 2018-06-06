package mapper;

import models.Coordinates;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Logger;


public class GoogleLocationManager {

    private final String GOOGLE_API_URL = "https://maps.googleapis.com";
    private final String URL_PATH = "/maps/api/geocode/json?key=AIzaSyCwXbXj-N_QT7SvZE8bgNKsay7aJjTwr_4&address=";
    private final String LOCATION_NOT_FOUND = "not_found";
    private Logger logger;

    public GoogleLocationManager() {
        this.logger = Logger.getLogger(GoogleLocationManager.class.getName());
    }


    public Coordinates getCoordinatesForLocation(String street, String city) {

        String locationDataString = "";
        logger.info("Getting coordinates from json : " + "street: " + street);
        try {
            String encodedStreetCity = URLEncoder.encode(street.concat(",").concat(city));
            locationDataString = getStringLocationData(encodedStreetCity);
        } catch (LocationNotFoundException e) {
            logger.warning("Problem with getting location data : " + e.getMessage());
        }

        return fetchLocationFromJson(locationDataString);
    }


    private String getStringLocationData(String location) throws LocationNotFoundException {

        StringBuilder preparedData = new StringBuilder();
        try {
            URL url = new URL(GOOGLE_API_URL.concat(URL_PATH).concat(location).concat("&sensor=false"));
            URLConnection uc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc
                    .getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                preparedData.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException exc) {
            logger.warning("Malformed URL! " + exc.getMessage());
        } catch (IOException exc) {
            logger.warning("IOException while reading from Google API URL: " + exc.getMessage());
        }

        String preparedDataString = preparedData.toString();
        if (preparedDataString.isEmpty()) {
            throw new LocationNotFoundException();
        } else {
            return preparedDataString;
        }
    }

    private Coordinates fetchLocationFromJson(String locationDataString) {
        JSONObject json = new JSONObject(locationDataString);
        JSONArray result = json.getJSONArray("results");
        JSONObject firstResult = result.getJSONObject(0);
        JSONObject geometry = firstResult.getJSONObject("geometry");
        JSONObject location = geometry.getJSONObject("location");
        return Coordinates.builder()
                .latitude(location.getDouble("lat"))
                .longitude(location.getDouble("lng")).build();
    }
}
