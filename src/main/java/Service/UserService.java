package Service;

import DAO.UserDAO;
import Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class UserService {
    @Autowired
    private UserDAO userdao;
    private User user = null;
    private Long uid = null;


    public Boolean newUser(String name, String email) {
        try {
            this.user = new User(name, email);
            uid=user.getId();
            System.out.println(user);
            userdao.save(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public User userIsRegister(String name) throws SQLException{
        try{
            user = userdao.findByName(name);
            return user;
        } catch (Exception e){
            throw new SQLException();
        }
    }

    public User findById(Long uid) {
        user = userdao.findByIdUser(uid);
        return user;

    }
    public User getUser() {
        return user;
    }

}
