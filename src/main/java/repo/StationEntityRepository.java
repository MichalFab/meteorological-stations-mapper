//package repo;
//
//import models.StationEntity;
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import utils.HibernateUtils;
//
//import javax.persistence.Query;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class StationEntityRepository {
//
//    private Session session;
//    private Transaction transaction;
//    private Logger logger;
//
//    public StationEntityRepository() {
//        logger = Logger.getLogger(StationEntityRepository.class.getName());
//    }
//
//    public void saveStations(List<StationEntity> stations) {
//        session = HibernateUtils.getSessionFactory().openSession();
//        transaction = session.beginTransaction();
//        try {
//            transaction.rollback();
//            transaction.begin();
//            stations.forEach(station -> session.save(station));
//
//            logger.log(Level.INFO, "Successfully inserted " + stations.size() + "records");
//
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//                logger.log(Level.WARNING, "Transaction Rollback");
//            }
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
////                session.close();
//            }
//        }
//    }
//
//    public List<StationEntity> getAllStations() {
//        session = HibernateUtils.getSessionFactory().getCurrentSession();
//        transaction = session.beginTransaction();
//        List<StationEntity> allStations = null;
//        try {
//            transaction.rollback();
//            transaction.begin();
//            allStations = (List<StationEntity>) session.createQuery("from station_data").list();
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//                logger.log(Level.WARNING, "Transaction Rollback");
//            }
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//
//            session.close();
//            HibernateUtils.shutdown();
//            return allStations;
//
//        }
//    }
//}
