package Main;

import DAO.*;
import Domain.*;
import Service.GroupService;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages={"Service","DAO","Domains","Controller"})
@EnableJpaRepositories("DAO")
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {User.class, Group.class,Post.class})
public class Application /*implements CommandLineRunner*/ {
    /*@Autowired
    UserService userservice;
    @Autowired
    GroupService groupservice;
*/
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    /*@Override
    public void run(String... args){
        userservice.newUser("sda1","asd1");
        Boolean b = groupservice.newGroup("sd1",userservice.getUser());
        if (b) { System.out.println("true");}else{System.out.println("false");}
        userservice.newUser("fran1","hola1");
        groupservice.suscribeGroup("sd1",userservice.getUser());
    }*/
}