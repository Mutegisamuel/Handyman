import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest{
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
    public void all_returnsAllInstancesOfCategory_true(){
        Category firstCategory = new Category("Home Maintenance");
        Category secondCategory = new Category("Plumbing");
        assertEquals(true, Category.all().contains(firstCategory));
        assertEquals(true, Category.all().contains(secondCategory));
    }

    @Test
    public void clear_emptiesAllCategoriesFromList_0(){
        Category testCategory = new Category("Home Maintenance");
        Category.clear();
        assertEquals(Category.all().size(), 0);
    }

    @Test
    public void getId_categoriesInstantiateWithAnId_1(){
        Category testCategory = new Category("Home Maintenance");
        assertEquals(1, testCategory.getId());
    }

    @Test
    public void find_returnsCategoryWithSameId_secondCategory(){
        Category.clear();
        Category firstCategory = new Category("Hom Maintenance");
        Category secondCategory = new Category("Plumbing");
        assertEquals(Category.find(secondCategory.getId()), secondCategory);
    }
}
