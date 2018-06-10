package models;

public class Coordinates {
    private Double longitude;
    private Double latitude;

    public Coordinates(Builder builder) {
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public static class Builder {
        private Double longitude;
        private Double latitude;

        public Builder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Coordinates build() {
            return new Coordinates(this);
        }
    }


}
