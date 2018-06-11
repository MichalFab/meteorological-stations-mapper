package mapper;

public class WrongMeasurementCsvFormatException extends Exception {

    public WrongMeasurementCsvFormatException() {
        super("Number of measurements must be the same as headers (stations codes)");
    }
}
