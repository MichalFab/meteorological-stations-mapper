package models;

import javax.persistence.*;

@Entity(name = "station_data")
@Table(name = "station_data")
public class StationEntity {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;

    @Column
    private String state;

    @Column
    private String oldCode;

    @Column
    private String code;

    @Column
    private String stationName;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    public void setState(String state) {
        this.state = state;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
