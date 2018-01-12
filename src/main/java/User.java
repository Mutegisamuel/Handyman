import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class User{
    private Timestamp createdOn;
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
            String sql = "INSERT INTO users (firstName, lastName, address, password, location, avatar, categoryId, createdOn) VALUES (:firstName, :lastName, :address, :password, :location, :avatar, :categoryId, now())";
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

    public Timestamp getCreationDate(){
        return createdOn;
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

    public void updateFirstName(String firstName){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE users SET firstName = :firstName WHERE id = :id";
            con.createQuery(sql)
            .addParameter("firstName", firstName)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public void updateLastName(String lastName){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE users SET lastName = :lastName WHERE id = :id";
            con.createQuery(sql)
            .addParameter("lastName", lastName)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public void updateAddress(String address){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE users SET address = :address WHERE id = :id";
            con.createQuery(sql)
            .addParameter("address", address)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public void updateLocation(String location){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE users SET location = :location WHERE id = :id";
            con.createQuery(sql)
            .addParameter("location", location)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public void updateAvatar(String avatar){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE users SET avatar = :avatar WHERE id = :id";
            con.createQuery(sql)
            .addParameter("avatar", avatar)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public void updatePassword(String password){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE users SET password = :password WHERE id = :id";
            con.createQuery(sql)
            .addParameter("password", password)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public void updateCategoryId(int categoryId){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE users SET categoryId = :categoryId WHERE id = :id";
            con.createQuery(sql)
            .addParameter("categoryId", categoryId)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM users WHERE id = :id;";
            con.createQuery(sql)
            .addParameter("id", id)
            .executeUpdate();
        }
    }
}
