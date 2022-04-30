

import Models.Task;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static  spark.Spark.*;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public/");


        get("/",(request,response)->{

           Map<String,Object> data =  new HashMap<>();
            List<Task> list = request.session().attribute("Tasks");


            boolean moreThanFive = false;

            if( list!=null && list.size()>4){
                moreThanFive = true;
                list = list.subList(0,3);
            }

            data.put("Tasks",list);
            data.put("moreThanFive",moreThanFive);
            return modelAndView(data,"index.hbs");
        },new HandlebarsTemplateEngine());



        post("/",(request,response)-> {
            Map<String,Object> data = new HashMap<>();

            String tittle = request.queryParams("title");
            String date = request.queryParams("date");
            String desc = request.queryParams("desc");

            List<Task> list = request.session().attribute("Tasks");
            Task task = new Task(tittle,date,desc);
            TaskApp taskApp = new TaskApp();

            taskApp.addTask(list,task);

            List<Task> updatedList = taskApp.getAllTask();
            request.session().attribute("Tasks",updatedList);

            boolean moreThanFive = false;
            if(updatedList.size()>4){
                moreThanFive = true;
                updatedList = updatedList.subList(0,3);
            }

            data.put("Tasks",updatedList);
            data.put("moreThanFive",moreThanFive);




         return modelAndView(data,"index.hbs");
        },new HandlebarsTemplateEngine());


        get("/task/:id",(request,response)->{
            Map<String,Object> data  = new HashMap<>();

            int id = Integer.parseInt(request.params(":id"));
            List<Task> list = request.session().attribute("Tasks");
            Task task = list.get(id);
            data.put("Task",task);

            return modelAndView(data,"task.hbs");
        },new HandlebarsTemplateEngine());


        get("/task",(request,response)->{
           Map<String,Object> data =  new HashMap<>();
           List<Task> list = request.session().attribute("Tasks");
           data.put("Tasks",list);
            return modelAndView(data,"all-tasks.hbs");
        },new HandlebarsTemplateEngine());


        post("/task/edit/:id",(request,response)->{
            Map<String,Object> data =  new HashMap<>();

            List<Task> list = request.session().attribute("Tasks");
            int id = Integer.parseInt(request.params(":id"));

            TaskApp app = new TaskApp();
            String delete = request.queryParams("delete");
//            String complete = request.queryParams("complete");

            if(delete==null){
                System.out.println("complete pressed");
                app.markAsComplete(list,id);
            }else {
                System.out.println("delete pressed");
                app.removeTask(list,id);
            }

            request.session().attribute("Tasks",app.getAllTask());
            data.put("Tasks",app.getAllTask());


//             redirect.get("/task/edit/:id","/");

            return modelAndView(data,"task.hbs");
        },new HandlebarsTemplateEngine());



    }
}
