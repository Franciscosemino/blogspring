package Domain;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Posts")
public class Post implements Serializable {
    @Id
    @Column(name = "pid", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false, unique = true)
    String title;

    @Column(name = "text",nullable = false)
    String text;

    @Transient
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    Date date ;

    public ArrayList<String> taglist;

    @ManyToOne(optional = false)
    @JoinColumn(name="uid")
    User owner;

    @ManyToOne(optional = false)
    @JoinColumn(name="gid")
    Group group;


    public Post(){}

    public Post(String title, String text, ArrayList<String> taglist, User owner, Group group){
        this.title = title;
        this.text = text;
        this.taglist = taglist;
        this.owner = owner;
        try{
            this.date = sdf.parse(sdf.format(new Date()));
        } catch (Exception e) {

        }
        this.group = group;

    }

    public void setDate(Date date) {

        this.date = date;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaglist(ArrayList<String> taglist) {
        this.taglist = taglist;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {

        return title;
    }

    public String getText() {
        return text;
    }

    public User getOwner() {

        return owner;
    }

    public Group getGroup() {

        return group;
    }

    public Long getId() {
        return id;
    }

    public List<String> getTaglist() {

        return taglist;
    }

    public String postToString(){
        String tags = "";
        if (!taglist.isEmpty()) {
            for (String tag : taglist) {
                tags = tag + ", ";
            }
            tags = tags.substring(0, tags.length() - 2);
        }
        return "Title:\t" + this.title +"\nText:\n" + this.text +"\nOwner: \t" + this.owner.getName() + "\nTags:\n"
                + tags;
    }

}
