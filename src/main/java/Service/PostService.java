package Service;

import DAO.PostDAO;
import Domain.Group;
import Domain.Post;
import Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    @Autowired
    PostDAO postdao;
    Post post = null;
    Long postid = null;
    

    public PostService() {}

    public Post findById(Long pid){
        post = postdao.findByIdPost(pid);
        return post;
    }

    public Boolean newPost(String title, String text, ArrayList<String> taglist, User user, Group group) {
        try {
            post = new Post(title, text, taglist, user, group);
            postid = post.getId();
            postdao.save(post);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean removePost(String titletoremove){
        try{
            postdao.deletePostByTitle(titletoremove);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ArrayList<Post> searchByTag(String tag) {
        ArrayList<Post> poststoreturn = new ArrayList<Post>();
        for (Post post : postdao.getPostList()) {
            List<String> taglist = post.taglist;
            for (String tagfromlist : taglist){
                if (tag.equals(tagfromlist)){
                    poststoreturn.add(post);
                }
            }

        }
        return poststoreturn;
    }

    public ArrayList<Post> searchByText(String text){
        ArrayList<Post> poststoreturn = new ArrayList<Post>();
        for(Post post : postdao.getPostList()){
            if (post.getText().contains(text)) {
                poststoreturn.add(post);
            }
        }
        return poststoreturn;
    }

    public ArrayList<Post> searchByOwner(String name){
        ArrayList<Post> poststoreturn = new ArrayList<Post>();
        for (Post post : postdao.getPostList()){
            String ownersname = post.getOwner().getName();
            if (ownersname.equals(name)) {
                poststoreturn.add(post);
            }
        }
        return poststoreturn;
    }

    public ArrayList<Post> searchBetweenDates(Date from, Date to){
        ArrayList<Post> foundedpost = new ArrayList<Post>();
        for(Post post : postdao.getPostList()){
            if(post.getDate().after(from) && post.getDate().before(to)){
                foundedpost.add(post);
            }
        }
        return foundedpost;
    }

    public void orderNewToOld(){
        Collections.sort(postdao.getPostList(), new Comparator<Post>() {
            @Override
            public int compare(Post post, Post t1) {
                return t1.getDate().compareTo(post.getDate());
            }
        });

    }
    public void orderOldToNew(){
        Collections.sort(postdao.getPostList(), new Comparator<Post>() {
            @Override
            public int compare(Post post, Post t1) {
                return post.getDate().compareTo(t1.getDate());
            }
        });
    }
    public void orderAtoZ(){
        Collections.sort(postdao.getPostList(), new Comparator<Post>() {
            @Override
            public int compare(Post post, Post t1) {
                return post.getTitle().compareTo(t1.getTitle());
            }
        });
    }
    public void orderZtoA(){
        Collections.sort(postdao.getPostList(), new Comparator<Post>() {
            @Override
            public int compare(Post post, Post t1) {
                return t1.getTitle().compareTo(post.getTitle());
            }
        });
    }

    public Post getPost() {
        return post;
    }

}
