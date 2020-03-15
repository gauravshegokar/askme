package models;

import java.sql.Timestamp;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.validation.*;
@Entity
public class User extends Model {
    @Id
    @GeneratedValue
    private int id;

    @Constraints.Required
    private String username;
    @Constraints.Required
    private String password;

    private String fname;
    private String lname;

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

    public static final Finder<Long, User> find = new Finder<>(User.class);

}
