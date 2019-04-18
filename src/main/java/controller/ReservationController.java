package controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ReservationController {
    public static void main(String arg[])
    {
        staticFiles.location("/public");
        port(8080);

        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(ReservationController.class, "/");

        get("/", (request, response) -> {

            StringWriter writer = new StringWriter();

            try {
                Template formTemplate = configuration.getTemplate("freemarker/login.ftl");

                formTemplate.process(null, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
        Spark.post("/welcome", (request, response) -> {
            StringWriter writer = new StringWriter();

            try {
                String name = request.queryParams("username") != null ? request.queryParams("username") : "anonymous";
                String email = request.queryParams("password") != null ? request.queryParams("password") : "unknown";

                Template resultTemplate = configuration.getTemplate("freemarker/welcome.ftl");

                Map<String, Object> map = new HashMap<>();
                map.put("username", name);
                map.put("password", email);

                resultTemplate.process(map, writer);
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
                    response.redirect("/");
                }
               else
                   response.redirect("/signup");


            } catch (Exception e) {
                Spark.halt(500);
            }

            return writer;
        });
        Spark.post("/confirmation", (request, response) -> {
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
