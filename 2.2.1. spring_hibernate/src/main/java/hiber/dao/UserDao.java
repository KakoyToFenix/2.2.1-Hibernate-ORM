package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user, Car car);
   List<User> listUsers();
   User getUsersByCarModelAndSeries(String model, String series);
}
