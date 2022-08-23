package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, String series) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "select id from Car where model = :modelParam and series = :seriesParam");
        query.setParameter("modelParam", model);
        query.setParameter("seriesParam", series);
        Integer carId = (Integer) query.getSingleResult();
        Car car = session.get(Car.class, carId);
        return car.getUser();
    }

}
