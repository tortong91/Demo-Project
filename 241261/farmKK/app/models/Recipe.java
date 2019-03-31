package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbRecipe")
public class Recipe extends Model {
    @Id
    private String rid;
    private String recipe1,recipe2;

    @ManyToOne()
    private Foods foods;
    @ManyToOne()
    private Inputfood inputfood;

    public Recipe() {
setRid();
    }

    public Recipe(String rid, String recipe1, String recipe2, Foods foods) {
        if (rid==null){
            setRid();
        }else{
            setnewrid(rid);
        }
        this.rid = rid;
        this.recipe1 = recipe1;
        this.recipe2 = recipe2;
        this.foods = foods;
    }

    public String getRid() {
        return rid;
    }

    public void setRid() {
        int i ;
        Random random = new Random();
        i = random.nextInt(100000)+1;
        rid = "Ctive-" + Integer.toString(i);
    }
public void setnewrid(String rid) {
        this.rid = rid;
    }

    public String getRecipe1() {
        return recipe1;
    }

    public void setRecipe1(String recipe1) {
        this.recipe1 = recipe1;
    }

    public String getRecipe2() {
        return recipe2;
    }

    public void setRecipe2(String recipe2) {
        this.recipe2 = recipe2;
    }

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }

    public Inputfood getInputfood() {
        return inputfood;
    }

    public void setInputfood(Inputfood inputfood) {
        this.inputfood = inputfood;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Model.Finder<String,Recipe> finder=new Model.Finder<String, Recipe>(String.class,Recipe.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Recipe> list(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Recipe data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(Recipe data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Recipe data){
        data.delete();
    }


}
