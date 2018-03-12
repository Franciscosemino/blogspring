package Controller;

import Domain.User;
import Service.GroupService;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("")
public class MainController {
    @Autowired
    private UserService userservice = new UserService();
    @Autowired
    private GroupService groupservice = new GroupService();


    @RequestMapping(method = RequestMethod.POST,value = "/user/add")
    public ResponseEntity<?> addNewUser (@RequestParam String name
            , @RequestParam String email) {
        Boolean created = userservice.newUser(name,email);
        if (created){
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/login")
    public ResponseEntity<?> login(@RequestParam String name){
        try{
            User user = userservice.userIsRegister(name);
            return new ResponseEntity<String>("redirect:/user/"+user.getId(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("redirect:/user/login", HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{uid}")
    public  ResponseEntity<?> showUser(@PathVariable("uid") Long uid){
        User user = userservice.findById(uid);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/user/{uid}/group/add")
    public ResponseEntity<?> addGroup(@PathVariable("uid") Long uid, @RequestParam("name") String name){
        User user = null;
        try{
            user = userservice.findById(uid);
        } catch (Exception e){
            return new ResponseEntity<String>("redirect:/user/login",HttpStatus.BAD_REQUEST);
        }
        try {
            if(groupservice.newGroup(name,user)){
                return new ResponseEntity<String>("Group created", HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Group with this name already Exist", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception j){
            return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/{uid}/group/suscribe")
    public  ResponseEntity<?> suscribeGroup(@PathVariable("uid") Long uid,@RequestParam("name") String name){
        User user= null;
        try{
            user = userservice.findById(uid);
        } catch (Exception e){
            return new ResponseEntity<String>("redirect:/user/login",HttpStatus.BAD_REQUEST);
        }
        try {
            if(groupservice.suscribeGroup(name,user)){
                return new ResponseEntity<String>("Suscribe to"+name+"correctly", HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Group with this name no Exist", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception j){
            return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
