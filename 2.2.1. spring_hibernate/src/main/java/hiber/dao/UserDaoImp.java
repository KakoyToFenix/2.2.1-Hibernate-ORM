package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      sessionFactory.getCurrentSession().save(user);
      user.setCar(car);
      sessionFactory.getCurrentSession().saveOrUpdate(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getUsersByCarModelAndSeries(String model, String series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT user FROM User user JOIN user.car car WHERE car.model = :model AND car.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }

}
