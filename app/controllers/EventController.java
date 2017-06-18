package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Task;
import models.TaskDetail;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

import static play.libs.Json.toJson;

public class EventController extends Controller {

    public Result index() {
        return ok(index.render(""));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result addTask(){
        JsonNode json = request().body().asJson();

       Task task = Json.fromJson(json,Task.class);
        if(task.toString().equals("")){
            return badRequest("Missing parameter");
        }

        task.save();
        return ok("true");
    }

    public Result listTask(){
        List<Task> tasks = new Model.Finder(String.class,Task.class).all();
        return ok(toJson(tasks));
    }

    public Result listTaskDetail(){
        List<TaskDetail> tasks = new Model.Finder(String.class,TaskDetail.class).all();
        return ok(toJson(tasks));
    }

    public Result getTask(int id){
        Task task = Task.find.byId(id);
        if(task == null) {
            return notFound("Task not found!");
        }

        return ok(toJson(task));
    }

    public Result getDetail(int id){
        TaskDetail tasks = TaskDetail.find.byId(id);
        if(tasks == null){
            return notFound("Task not found!");
        }

        return ok(toJson(tasks));

    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result addTaskDetail(int id){
        Task task = Task.find.byId(id);

        if(task == null){
            return notFound("Task not found");
        }

        JsonNode json = request().body().asJson();
        TaskDetail tasks = Json.fromJson(json,TaskDetail.class);
        task.details.add(tasks);
        task.save();
        return ok("true");


    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateTaskDetail(int id){
        TaskDetail tasks = TaskDetail.find.byId(id);

        if(tasks == null){
            return notFound("Task not found");
        }

        JsonNode json = request().body().asJson();
        TaskDetail tasksToBe = Json.fromJson(json,TaskDetail.class);
        tasks = tasksToBe;
        tasks.update();
        return  ok("true");

    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateTask(int id){
        Task task = Task.find.byId(id);

        if(task == null ){
            return notFound("Task not found");
        }

        JsonNode json = request().body().asJson();
        Task taskToBe = Json.fromJson(json,Task.class);
        task = taskToBe;
        task.update();
        return ok("true");
    }

    public Result deleteTask(int id){
        Task task = Task.find.ref(id);

        if(task == null){
            return notFound("Task not found");
        }

        task.delete();
        return ok("true");
    }

    public Result deleteTaskDetail(int id){
        TaskDetail tasks = TaskDetail.find.byId(id);
        if(tasks == null){
            return notFound("Task not found");
        }

        tasks.delete();
        return ok("true");

    }






}
