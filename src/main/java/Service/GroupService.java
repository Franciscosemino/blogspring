package Service;

import DAO.GroupDAO;
import DAO.UserDAO;
import Domain.Group;
import Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupDAO groupdao;
    Group group = null;
    Long gid = null;

    public GroupService() {}

    public Boolean newGroup(String groupname, User user){
        try{
            group = new Group(groupname);
            group.addUser(user);
            user.addGroup(group);
            gid = group.getId();
            groupdao.save(group);
            return true;
        } catch(Exception e){
            System.out.println(e.toString()+"\n"+e.getMessage()+"\n"+e.getCause());
            return false;
        }

    }

    public Group findById(Long gid){
        group = groupdao.findByIdGroup(gid);
        return group;
    }
    public Boolean suscribeGroup(String groupname, User user){
        try{
            Group g = groupdao.findByName(groupname);
            if (g!=null){
                g.addUser(user);
                user.addGroup(g);
                groupdao.save(g);
                return true;
            } else {return false;}

        } catch (Exception e){

            return false;
        }
    }

    public Group getGroup() {
        return group;
    }
}
