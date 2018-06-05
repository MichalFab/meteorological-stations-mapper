package repo;

import java.io.Serializable;
import java.util.List;

public interface StationsRepositoryI<T, Id> extends Serializable {
    public void persist(T entity);
    public void addAllStations(List<T> stations);
    public List<T> fetchAll();

}
