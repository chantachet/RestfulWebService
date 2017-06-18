package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
import java.util.List;


@Entity
public class Task extends Model {

    @Id
    public Long id;
    public String subject;

    @OneToMany(cascade = CascadeType.ALL)
    public List<TaskDetail> details;

    public static Finder<Integer,Task>find
            = new Finder<Integer, Task>(Integer.class,Task.class);

}
