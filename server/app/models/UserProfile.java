package models;


import io.ebean.*;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserProfile extends Model {
    @Id
    @GeneratedValue
    private int id;

    @Constraints.Required
    private String username;
    @Constraints.Required
    private String password;
    private String fname;
<<<<<<< HEAD

   @ManyToMany(mappedBy = "followers")
    private List<Tag> interests=new ArrayList<>();

    private String lname;
    private String accessLevel;

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fname='" + fname + '\'' +
                ", interests=" + interests +
                ", lname='" + lname + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", date_created=" + date_created +
                '}';
    }

=======
    private String lname;
    private String accessLevel;
    public List<Tag> interests=new ArrayList<>();

>>>>>>> 8cb45f9c2bf50553c3da44a35df96da6e45cbfb2
    public List<Tag> getInterests() {
        return interests;
    }

    public void setInterests(Tag interest) {
        this.interests.add(interest);
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

<<<<<<< HEAD


=======
>>>>>>> 8cb45f9c2bf50553c3da44a35df96da6e45cbfb2
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }

    @Constraints.Required
    @Column(columnDefinition = "datetime")
    public Timestamp date_created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final Finder<Long, UserProfile> find = new Finder<>(UserProfile.class);
}
