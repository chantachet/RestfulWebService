package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TaskDetail extends Model {

    @Id
    public Long id;
    public String title;
    public String status;

    @ManyToOne
    Task task;


    public static Finder<Integer,TaskDetail>find
            =new Finder<Integer, TaskDetail>(Integer.class,TaskDetail.class);


}
