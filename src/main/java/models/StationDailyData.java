package models;

import javax.persistence.*;

@Entity
@Table(name = "station_daily_data")
public class StationDailyData {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;

    @Column
    private String date;

    @Column
    private Double pollutionValue;


}
