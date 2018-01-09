import java.util.List;
import java.util.ArrayList;

public class Category{
    private String name;
    private static List<Category> instances = new ArrayList<Category>();
    private int mId;

    public Category(String name){
        this.name = name;
        instances.add(this);
        mId = instances.size();
    }

    public String getName(){
        return name;
    }

    public static List<Category>all(){
        return instances;
    }

    public static void clear(){
        instances.clear();
    }

    public int getId(){
        return mId;
    }

    public static Category find(int id) {
        return instances.get(id - 1);
    }
}
