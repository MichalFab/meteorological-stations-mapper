package repo;

import models.StationDailyData;
import models.StationEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Function;

public class StationsRepository {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("stationsApp");
    private EntityManager entityManager = factory.createEntityManager();


    private <T> T withinTransaction(Function<EntityManager, T> code) {
        T result;
        try {
            entityManager.getTransaction().begin();
            result = code.apply(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        return result;
    }

    public void addStationEntity(StationEntity stationEntity) {
        withinTransaction(entityManager -> {
            entityManager.persist(stationEntity);
            return stationEntity;
        });
    }

    public void addAllStations(List<StationEntity> stations) {
        withinTransaction(entityManager1 -> {
            stations.forEach(station -> entityManager.persist(station));
            return stations;
        });
    }

    public void addMeasurementsList(List<StationDailyData> stationDailyData) {
        withinTransaction(entityManager1 -> {
            stationDailyData.forEach(stationDailyData1 -> entityManager.persist(stationDailyData1));
            return stationDailyData;
        });
    }

    public List<StationEntity> fetchAll() {
        return withinTransaction(entityManager -> entityManager
                .createQuery("SELECT s FROM StationEntity s", StationEntity.class)
                .getResultList());
    }
}
