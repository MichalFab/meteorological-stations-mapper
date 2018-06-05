package mapper;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import models.Coordinates;
import org.json.JSONObject;
import java.io.IOException;

import java.net.URI;

import java.util.logging.Logger;


public class GoogleLocationManager {

    private final String GOOGLE_API_URL = "https://maps.googleapis.com";
    private final String URL_PATH = "/maps/api/geocode/json?key=AIzaSyCwXbXj-N_QT7SvZE8bgNKsay7aJjTwr_4&address=";
    private Logger logger;

    public GoogleLocationManager() {
        this.logger = Logger.getLogger(GoogleLocationManager.class.getName());
    }


    public Coordinates getCoordinatesForLocation(String street, String city){
        logger.info("Getting coordinates from json");
        String locationDataString = getStringLocationData(street.concat(",%20").concat(city));
        System.out.println(locationDataString);
        JSONObject json = new JSONObject(locationDataString);
        JSONObject location = json.getJSONObject("location");
        Double latitude = location.getDouble("lat");
        Double longitude = location.getDouble("lng");

        return Coordinates.builder().latitude(latitude).longitude(longitude).build();
    }



    private String getStringLocationData(String location) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GOOGLE_API_URL.concat(URL_PATH).concat(location).concat("&sensor=false")))
                .build();
        HttpResponse<String> response =
                null;
        try {
            response = client.send(request, HttpResponse.BodyHandler.asString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());
return "";
    }
//        try {
//            URL googleUrl = new URL(GOOGLE_API_URL.concat(URL_PATH).concat(location).concat("&sensor=false"));
//            logger.info("GETTING DATA FROM " + googleUrl);
//            URLConnection connection = googleUrl.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//            StringBuilder locationJson = new StringBuilder();
//            while (in.readLine() != null) {
//                System.out.println(in.readLine());
////                locationJson.append(in.readLine());
//            }
//            return locationJson.toString();
//        } catch (MalformedURLException e) {
//            logger.warning("Malformed URL has occurred");
//            e.printStackTrace();
//        } catch (IOException e) {
//            logger.warning("Open connection problem");
//            e.printStackTrace();
//        }
//        return "";
//    }

}
