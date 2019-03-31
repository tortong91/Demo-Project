package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="tbFoods")
public class Foods extends Model {
    @Id
    private String id;
    private String Animalfoods_name,Picture,details;
    @OneToMany(mappedBy = "foods", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<Recipe> recipeList = new ArrayList<Recipe>();
    private List<Inputfood> inputList = new ArrayList<Inputfood>();

    public Foods() {
    }

    public Foods(String id, String picture, String details,String Animalfoods_name) {
        this.id = id;
        Picture = picture;
        this.details = details;
        this.Animalfoods_name = Animalfoods_name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAnimalfoods_name() {
        return Animalfoods_name;
    }

    public void setAnimalfoods_name(String animalfoods_name) {
        Animalfoods_name = animalfoods_name;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void setInputList(List<Inputfood> inputList) {
        this.inputList = inputList;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,Foods> finder=new Finder<String, Foods>(String.class,Foods.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Foods> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Foods datafoods){
        datafoods.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit1(Foods datafoods)
    {
        datafoods.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delet(Foods datafoods){
        datafoods.delete();
    }
}
