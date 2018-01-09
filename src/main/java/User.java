import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class User{
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String location;
    private String avatar;
    private String category;
    private int id;
    private int categoryId;

    public User(String firstName, String lastName, String address, String password, String location, String avatar, int categoryId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.location = location;
        this.avatar = avatar;
        this.categoryId = categoryId;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO users (firstName, lastName, address, password, location, avatar, categoryId) VALUES (:firstName, :lastName, :address, :password, :location, :avatar, :categoryId)";
            this.id = (int) con.createQuery(sql, true)
            .addParameter("firstName", this.firstName)
            .addParameter("lastName", this.lastName)
            .addParameter("address", this.address)
            .addParameter("password", this.password)
            .addParameter("location", this.location)
            .addParameter("avatar", this.avatar)
            .addParameter("categoryId", this.categoryId)
            .executeUpdate()
            .getKey();
        }
    }

    @Override
    public boolean equals(Object otherUser){
        if(!(otherUser instanceof User)){
            return false;
        }else{
            User newUser = (User) otherUser;
            return this.getAddress().equals(newUser.getAddress());
        }
    }

    public static List<User> all(){
        String sql = "SELECT * FROM users";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastname(){
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public String getPassword(){
        return password;
    }

    public String getLocation(){
        return location;
    }

    public String getAvatar(){
        return avatar;
    }

    public String getCategory(){
        return category;
    }

    public int getId(){
        return id;
    }

    public int getCategoryId(){
        return categoryId;
    }

    public static User find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM users where id=:id";
            User user = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(User.class);
            return user;
        }
    }
}
