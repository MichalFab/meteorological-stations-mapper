package mapper;

public class LocationNotFoundException extends Exception {


    public LocationNotFoundException() {

        super("Cannot find location for given data");
    }
}
