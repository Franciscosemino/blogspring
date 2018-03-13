package Controller;

import Domain.Post;
import Domain.User;
import Service.GroupService;
import Service.PostService;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("")
public class MainController {
    @Autowired
    private UserService userservice = new UserService();
    @Autowired
    private GroupService groupservice = new GroupService();
    @Autowired
    private PostService postservice = new PostService();


    @RequestMapping(method = RequestMethod.POST,value = "/user/add")
    public ResponseEntity<?> addNewUser (@RequestParam String name
            , @RequestParam String email) {
        Boolean created = userservice.newUser(name,email);
        if (created){
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity( HttpStatus.CREATED);
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

    @RequestMapping(method = RequestMethod.POST, value = "/post/add")
    public  ResponseEntity<?> newPost(@RequestParam("uid") Long uid, @RequestParam
            String title,@RequestParam String text,@RequestParam String tags, @RequestParam Long gid){
        ArrayList<String> taglist = new ArrayList<String >(Arrays.asList(tags.split(",")));
        Boolean newpost = postservice.newPost(title,text, taglist,userservice.findById(uid),
                groupservice.findById(gid));
        if (newpost){
            return new ResponseEntity<String>("new post in /post/"+postservice.getPost().getId(),
                    HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("Can't Created Post, title already exit",HttpStatus.BAD_REQUEST);
        }


    }

    @RequestMapping(method = RequestMethod.GET, value = "/post/{pid}")
    public  ResponseEntity<?> showPost(@PathVariable("pid") Long pid){
        Post post = postservice.findById(pid);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
}
