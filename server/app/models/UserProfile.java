package models;


import io.ebean.*;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private String lname;
    private String accessLevel;
    public List<Tag> interests=new ArrayList<>();

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
