import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class UserTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void user_instantiatesCorrectly_true(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar");
        assertEquals(true, testUser instanceof User);
    }

    @Test
    public void getdetails_userInstanttiatesWithAllNecessaryDetails(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar");
        assertEquals("Henry", testUser.getFirstName());
        assertEquals("John", testUser.getLastname());
        assertEquals("henry@mail.com", testUser.getAddress());
        assertEquals("password", testUser.getPassword());
        assertEquals("location", testUser.getLocation());
        assertEquals("avatar", testUser.getAvatar());
    }
    @Test
    public void equals_returnsTrueIfEmailAndPassWordAreTheSame_true(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar");
        User anotherUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar");
        assertTrue(testUser.equals(anotherUser));
    }

    @Test
    public void save_insertsObjectIntoDatabase_User(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar");
        testUser.save();
        assertTrue(User.all().get(0).equals(testUser));
    }

    @Test
    public void all_returnsAllInstancesOfUser_true(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar");
        testUser.save();
        User secondUser = new User("Tim", "ken", "tim@mail.com", "word", "place", "propic");
        secondUser.save();
        assertEquals(true, User.all().get(0).equals(testUser));
        assertEquals(true, User.all().get(1).equals(secondUser));
    }

    @Test
    public void save_assignsIdToObject(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar");
        testUser.save();
        User savedUser = User.all().get(0);
        assertEquals(testUser.getId(), savedUser.getId());
    }

    @Test
    public void find_returnUserWithSameId_secondUser(){
        User testUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar");
        testUser.save();
        User secondUser = new User("Tim", "ken", "tim@mail.com", "word", "place", "propic");
        secondUser.save();
        assertEquals(User.find(secondUser.getId()), secondUser);
    }
}