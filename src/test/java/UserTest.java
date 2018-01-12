import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class UserTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void user_instantiatesCorrectly_true(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        assertEquals(true, testUser instanceof User);
    }

    @Test
    public void getdetails_userInstanttiatesWithAllNecessaryDetails(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        assertEquals("Henry", testUser.getFirstName());
        assertEquals("John", testUser.getLastname());
        assertEquals("henry@mail.com", testUser.getAddress());
        assertEquals("password", testUser.getPassword());
        assertEquals("location", testUser.getLocation());
        assertEquals("avatar", testUser.getAvatar());
        assertEquals(1, testUser.getCategoryId());
    }
    @Test
    public void equals_returnsTrueIfEmailAndPassWordAreTheSame_true(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        User anotherUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        assertTrue(testUser.equals(anotherUser));
    }

    @Test
    public void save_insertsObjectIntoDatabase_User(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        testUser.save();
        assertTrue(User.all().get(0).equals(testUser));
    }

    @Test
    public void all_returnsAllInstancesOfUser_true(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        testUser.save();
        User secondUser = new User("Tim", "ken", "tim@mail.com", "word", "place", "propic", 1);
        secondUser.save();
        assertEquals(true, User.all().get(0).equals(testUser));
        assertEquals(true, User.all().get(1).equals(secondUser));
    }

    @Test
    public void save_assignsIdToObject(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        testUser.save();
        User savedUser = User.all().get(0);
        assertEquals(testUser.getId(), savedUser.getId());
    }

    @Test
    public void find_returnUserWithSameId_secondUser(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        testUser.save();
        User secondUser = new User("Tim", "ken", "tim@mail.com", "word", "place", "propic", 1);
        secondUser.save();
        assertEquals(User.find(secondUser.getId()), secondUser);
    }

    @Test
    public void save_savesCategoryIntoDB_true() {
        Category testCategory = new Category("Home Maintenance");
        testCategory.save();
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", testCategory.getId());
        testUser.save();
        User savedUser = User.find(testUser.getId());
        assertEquals(savedUser.getCategoryId(), testCategory.getId());
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        testUser.save();
        Timestamp savedUserCreationDate = User.find(testUser.getId()).getCreationDate();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(),savedUserCreationDate.getDay());
    }

    @Test
    public void update_updatesTheUserDetails_true(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        testUser.save();
        testUser.updateFirstName("Tim");
        testUser.updateLastName("ken");
        testUser.updateAddress("tim@mail.com");
        testUser.updateLocation("newLocation");
        testUser.updateAvatar("newProfPic");
        testUser.updateCategoryId(3);
        assertEquals("Tim", User.find(testUser.getId()).getFirstName());
        assertEquals("ken", User.find(testUser.getId()).getLastname());
        assertEquals("tim@mail.com", User.find(testUser.getId()).getAddress());
        assertEquals("newProfPic", User.find(testUser.getId()).getAvatar());
        assertEquals("newLocation", User.find(testUser.getId()).getLocation());
        assertEquals(3, User.find(testUser.getId()).getCategoryId());
    }

    @Test
    public void delete_deleteUser_true(){
        User myUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", 1);
        myUser.save();
        int myUserId = myUser.getId();
        myUser.delete();
        assertEquals(null, User.find(myUserId));
    }

    
}
