package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbFood")
public class foodsAnimal extends Model {
@Id

private String id;
    private String Animalfoods_name,Animalfoods_unit,Picture,details,amountKg;
    private double Animalfoods_amount,Animalfoods_price;

    @OneToMany(mappedBy = "foods", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<Recipe> recipeList = new ArrayList<Recipe>();

    public foodsAnimal(String id, String animalfoods_name, String animalfoods_unit, String picture, String details, String amountKg, double animalfoods_amount, double animalfoods_price) {
        this.id = id;
        Animalfoods_name = animalfoods_name;
        Animalfoods_unit = animalfoods_unit;
        Picture = picture;
        this.details = details;
        this.amountKg = amountKg;
        Animalfoods_amount = animalfoods_amount;
        Animalfoods_price = animalfoods_price;
    }

    public String getAmountKg() {
        return amountKg;
    }

    public void setAmountKg(String amountKg) {
        this.amountKg = amountKg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnimalfoods_name() {
        return Animalfoods_name;
    }

    public void setAnimalfoods_name(String animalfoods_name) {
        Animalfoods_name = animalfoods_name;
    }

    public String getAnimalfoods_unit() {
        return Animalfoods_unit;
    }

    public void setAnimalfoods_unit(String animalfoods_unit) {
        Animalfoods_unit = animalfoods_unit;
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

    public double getAnimalfoods_amount() {
        return Animalfoods_amount;
    }

    public void setAnimalfoods_amount(double animalfoods_amount) {
        Animalfoods_amount = animalfoods_amount;
    }

    public double getAnimalfoods_price() {
        return Animalfoods_price;
    }

    public void setAnimalfoods_price(double animalfoods_price) {
        Animalfoods_price = animalfoods_price;
    }



    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,foodsAnimal> finder=new Finder<String, foodsAnimal>(String.class,foodsAnimal.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<foodsAnimal> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(foodsAnimal datafoods){
        datafoods.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit1(foodsAnimal datafoods)
    {
        datafoods.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delet(foodsAnimal datafoods){
        datafoods.delete();
    }
}
