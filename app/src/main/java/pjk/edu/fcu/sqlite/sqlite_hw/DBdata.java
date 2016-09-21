package pjk.edu.fcu.sqlite.sqlite_hw;

import java.io.Serializable;

/**
 * Created by kiam on 08/05/2016.
 */
public class DBdata implements Serializable {
    private String id,name,calories;

    public DBdata(String id,String name, String calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
