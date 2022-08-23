package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Artem", "Lebedev",
              "Artemka@mail.ru", new Car("BMW", "M3")));
      userService.add(new User("Anna", "Ozhigova",
              "AnnaOzhohova@gmail.com", new Car("MINI", "Cooper")));
      userService.add(new User("Dmitriy", "Salikhov",
              "SalikhovDima@gmail.com", new Car("KIA", "Rio X")));
      userService.add(new User("Pavel", "Anoshkin",
              "Anoshkin@yandex.ru", new Car("Lada", "Granta")));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      User user = userService.getUserByCar("MINI", "Cooper");
      System.out.println(user);

      context.close();
   }
}
