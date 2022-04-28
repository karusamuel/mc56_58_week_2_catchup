

import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;

import static  spark.Spark.*;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/",(request,response)->{

            return modelAndView(new HashMap<>(),"index.hbs");
        },new HandlebarsTemplateEngine());
    }
}
