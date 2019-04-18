package controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import model.Booking;
import model.DbConnector;
import model.UserOperation;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static spark.Spark.*;

public class ReservationController {
    public static void main(String arg[])
    {
        UserOperation up=new UserOperation();
        up.login("rahul@gmail.com","rahul");
        staticFiles.location("/public");
        port(8080);

        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(ReservationController.class, "/");

        get("/login", (request, response) -> {

            StringWriter writer = new StringWriter();

            try {
                Template formTemplate = configuration.getTemplate("freemarker/login.ftl");
                Map<String,String>map=new HashMap<>();
                map.put("login_error","");
                formTemplate.process(map, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
        Spark.post("/login", (request, response) -> {
            StringWriter writer = new StringWriter();

            try {
                String email = request.queryParams("email") != null ? request.queryParams("email") : "";
                String password = request.queryParams("password") != null ? request.queryParams("password") : "";

                Template resultTemplate = configuration.getTemplate("freemarker/login.ftl");


        //        Map<String, Object> map = new HashMap<>();
          //      map.put("username", name);
            //    map.put("password", email);
                   UserOperation userOperation=new UserOperation();
                   String name=userOperation.login(email,password);
                   if (name.length()==0) {
                       Map<String, String> map = new HashMap<>();
                       map.put("email", "");
                       map.put("password","");
                       map.put("login_error", "Invalid Login");
                       resultTemplate.process(map, writer);
                   }
                   else {
                          response.cookie("email",email);
                       response.redirect("/welcome");
                   }


            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
        get("/signup", (request, response) -> {

            StringWriter writer = new StringWriter();

            try {
                Template formTemplate = configuration.getTemplate("freemarker/signup.ftl");

                formTemplate.process(null, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });

        Spark.get("/welcome" ,(request, response) ->
        {
            StringWriter writer=new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("freemarker/welcome.ftl");

                formTemplate.process(null, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }
            return  writer;
        });
        Spark.post("/signup", (request, response) -> {
            StringWriter writer = new StringWriter();
            String name;
            String email;
            String password;
            try {
                if (request.queryParams("name").length()!=0 && request.queryParams("email").length()!=0 && request.queryParams("password").length() !=0 )
                {
                    name=request.queryParams("name");
                    email=request.queryParams("email");
                    password=request.queryParams("password");
                    UserOperation userOperation=new UserOperation();
                    userOperation.signup(email,name,password);
                    response.redirect("/login");
                }
                else
                    response.redirect("/signup");


            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
        Spark.post("/welcome", (request, response) -> {
            StringWriter writer = new StringWriter();
            String source;
            String destination;
            String trainno;
            String date;
            try {
                if (request.queryParams("source").length()!=0 && request.queryParams("destination").length()!=0 && request.queryParams("trainno").length() !=0 && request.queryParams("date").length() !=0  )
                {
                    source=request.queryParams("source");
                    destination=request.queryParams("destination");
                    trainno=request.queryParams("trainno");
                    date=request.queryParams("date");
                    Booking booking=new Booking();
                    if(booking.availability_check(source,destination,trainno,date))
                    {
                        int id = (int)(Math.random()*((100000-1)+1))+1;
                        String email=request.cookie("email");
                        System.err.println("HDHFGD");
                        booking.bookingconfirm(email,String.valueOf(id),source,destination,trainno,"confirm",date);
                        response.redirect("/confirmation");
                    }
                    else {
                        response.redirect("/welcome");
                    }
                }
               else
                   response.redirect("/welcome");


            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
        Spark.get("/confirmation", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("freemarker/confirmation.ftl");

                formTemplate.process(null, writer);

            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
    }
}
