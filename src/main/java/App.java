import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App{
    public static void main(String[] args){
        ProcessBuilder process = new ProcessBuilder();
         Integer port;
         if (process.environment().get("PORT") != null) {
             port = Integer.parseInt(process.environment().get("PORT"));
         } else {
             port = 4567;
         }
        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/login", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/login.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/login", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String address = request.queryParams("address");
            String password = request.queryParams("password");
            String location = request.queryParams("location");
            String avatar = request.queryParams("avatar");
            int categoryId = Integer.parseInt(request.queryParams("categoryId"));
            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            User newUser = new User(firstName, lastName, address, password, location, avatar, categoryId);
            if(User.clientExists(newUser)){
                String clientInDb = "Welcome to the app";
                model.put("clientInDb", clientInDb);
            }else{
                String clientNotInDb = "You are not in our registry - please sign up!";
                model.put("clientNotInDb", clientNotInDb);
            }
            //User newUser = User.find(Integer.parseInt(request.params(":id")));
            model.put("template", "templates/login-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // get("/categories", (request, response) -> {
        //     Map<String, Object> model = new HashMap<String, Object>();
        //     model.put("template", "templates/category-form.vtl");
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());
        //
        // post("/categories", (request, response) -> {
        //     Map<String, Object> model = new HashMap<String, Object>();
        //     String name = request.queryParams("name");
        //     Category newCategory = new Category(name);
        //     newCategory.save();
        //     model.put("template", "templates/signin.vtl");
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());

        get("/signin", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("categories", Category.all());
            model.put("template", "templates/signin.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/signin", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Category category = Category.find(Integer.parseInt(request.queryParams("categoryId")));
            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String address = request.queryParams("address");
            String password = request.queryParams("password");
            String location = request.queryParams("location");
            String avatar = request.queryParams("avatar");
            int categoryId = Integer.parseInt(request.queryParams("categoryId"));

            User newUser = new User(firstName, lastName, address, password, location, avatar, category.getId());
            newUser.save();
            model.put("template", "templates/signin-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/homepage", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("users", User.all());
            model.put("categories", Category.all());
            model.put("template", "templates/main.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/homepage/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Category category = Category.find(Integer.parseInt(request.params(":id")));
            model.put("category", category);
            model.put("template", "templates/category.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/homepage/:category_id/users/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Category category = Category.find(Integer.parseInt(request.params(":category_id")));
            User user = User.find(Integer.parseInt(request.params(":id")));
            model.put("category", category);
            model.put("categories", Category.all());
            model.put("user", user);
            model.put("template", "templates/user.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/homepage/:category_id/users/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            User user = User.find(Integer.parseInt(request.params("id")));
            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            String address = request.queryParams("address");
            String password = request.queryParams("password");
            String avatar = request.queryParams("avatar");
            String location = request.queryParams("location");
            int categoryId = Integer.parseInt(request.queryParams("categoryId"));
            Category category = Category.find(user.getCategoryId());
            user.updateFirstName(firstName);
            user.updateLastName(lastName);
            user.updateAddress(address);
            user.updateLocation(location);
            user.updatePassword(password);
            user.updateCategoryId(categoryId);
            //String url = String.format("/homepage/%d/users/%d", category.getId(), user.getId());
            //response.redirect(url);
            model.put("template", "templates/homepage.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/homepage/:category_id/users/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            User user = User.find(Integer.parseInt(request.params("id")));
            Category category = Category.find(user.getCategoryId());
            user.delete();
            model.put("category", category);
            model.put("template", "templates/homepage.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
