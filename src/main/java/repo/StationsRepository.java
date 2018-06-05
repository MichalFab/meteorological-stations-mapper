package repo;

import models.StationEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationsRepository implements StationsRepositoryI<StationEntity, Integer> {
    private Session currentSession;
    private Transaction currentTransaction;
    private Logger logger;

    public StationsRepository() {
        this.logger = Logger.getLogger(StationsRepository.class.getName());
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().addAnnotatedClass(StationEntity.class).configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void addAllStations(List<StationEntity> stations) {
        try {
            stations.forEach(station -> getCurrentSession().save(station));
            logger.log(Level.INFO, "Successfully inserted " + stations.size() + "records");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Inserting error");
            e.printStackTrace();
        }
    }

    @Override
    public List<StationEntity> fetchAll() {
        Optional<List> allStations = Optional.ofNullable(getCurrentSession()
                .createQuery("from station_data").list());
        if (allStations.isPresent()) {
            return (List<StationEntity>) allStations.get();
        }
        return List.of(new StationEntity());
    }

    @Override
    public void persist(StationEntity entity) {

    }
}
