package models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "station_daily_data")
public class StationDailyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDate date;

    @Column
    private Double pollutionValue;

    @Column
    private String stationCode;

    public Builder builder() {
        return new Builder();
    }

    public StationDailyData() {
    }

    public StationDailyData(Builder builder) {
        this.date = builder.date;
        this.pollutionValue = builder.pollutionValue;
        this.stationCode = builder.stationCode;
    }

    public static class Builder{
        private LocalDate date;
        private Double pollutionValue;
        private String stationCode;

        public Builder date(LocalDate date){
            this.date = date;
            return this;
        }
        public Builder pollutionValue(Double pollutionValue){
            this.pollutionValue = pollutionValue;
            return this;
        }
        public Builder stationCode(String stationCode){
            this.stationCode = stationCode;
            return this;
        }
        public StationDailyData build(){
            return new StationDailyData(this);
        }
    }


}
