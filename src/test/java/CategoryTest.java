import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CategoryTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void category_instantiatesCorrectly_true(){
        Category testCategory = new Category("Home Maintenance");
        assertEquals(true, testCategory instanceof Category);
    }

    @Test
    public void getName_categoryInstantiatesWithName_HomeMaintencance(){
        Category testCategory = new Category("Home Maintenance");
        assertEquals("Home Maintenance", testCategory.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsSame_true(){
        Category testCategory = new Category("Home Maintenance");
        Category anotherCategory = new Category("Home Maintenance");
        assertTrue(testCategory.equals(anotherCategory));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Category(){
        Category testCategory = new Category("Home Maintenance");
        testCategory.save();
        assertTrue(Category.all().get(0).equals(testCategory));
    }

    @Test
    public void all_returnsAllInstancesOfCategory_true(){
        Category firstCategory = new Category("Home Maintenance");
        firstCategory.save();
        Category secondCategory = new Category("Plumbing");
        secondCategory.save();
        assertEquals(true, Category.all().get(0).equals(firstCategory));
        assertEquals(true, Category.all().get(1).equals(secondCategory));
    }

    @Test
    public void save_assignsIdToObject(){
        Category firstCategory = new Category("Home Maintenance");
        firstCategory.save();
        Category savedCategory = Category.all().get(0);
        assertEquals(firstCategory.getId(), savedCategory.getId());
    }

    @Test
    public void find_returnsCategoryWithSameId_secondCategory(){
        Category firstCategory = new Category("Home Maintenance");
        firstCategory.save();
        Category secondCategory = new Category("Plumbing");
        secondCategory.save();
        assertEquals(Category.find(secondCategory.getId()), secondCategory);
    }

    @Test
    public void getUsers_retrievesAllUsersFromDataBase_usersList() {
        Category testCategory = new Category("Home Maintenance");
        testCategory.save();
        User firstUser = new User("Henry", "John", "henry@mail.com", "password", "location", "avatar", testCategory.getId());
        firstUser.save();
        User secondUser = new User("Tim", "ken", "tim@mail.com", "word", "place", "propic", testCategory.getId());
        secondUser.save();
        User[] users = new User[] { firstUser, secondUser};
        assertTrue(testCategory.getUsers().containsAll(Arrays.asList(users)));
    }
}
