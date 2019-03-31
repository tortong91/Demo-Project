package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbInputfood")
public class Inputfood extends Model {
    @Id
    private String id_ifoods;
    private String name_ifoods,date_ifoods,picture;
    private double amount_ifoods,price_ifoods;
    @ManyToOne
    private Foods inputfoods;
    @OneToMany(mappedBy = "inputfood", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<Recipe> recipeList = new ArrayList<Recipe>();


    public Inputfood() {
    }

    public Inputfood(String id_ifoods, String name_ifoods, String date_ifoods, String picture, double amount_ifoods, double price_ifoods, Foods inputfoods) {
        this.id_ifoods = id_ifoods;
        this.name_ifoods = name_ifoods;
        this.date_ifoods = date_ifoods;
        this.picture = picture;
        this.amount_ifoods = amount_ifoods;
        this.price_ifoods = price_ifoods;
        this.inputfoods = inputfoods;
    }

    public String getId_ifoods() {
        return id_ifoods;
    }

    public void setId_ifoods(String id_ifoods) {
        this.id_ifoods = id_ifoods;
    }

    public String getName_ifoods() {
        return name_ifoods;
    }

    public void setName_ifoods(String name_ifoods) {
        this.name_ifoods = name_ifoods;
    }

    public String getDate_ifoods() {
        return date_ifoods;
    }

    public void setDate_ifoods(String date_ifoods) {
        this.date_ifoods = date_ifoods;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getAmount_ifoods() {
        return amount_ifoods;
    }

    public void setAmount_ifoods(double amount_ifoods) {
        this.amount_ifoods = amount_ifoods;
    }

    public double getPrice_ifoods() {
        return price_ifoods;
    }

    public void setPrice_ifoods(double price_ifoods) {
        this.price_ifoods = price_ifoods;
    }

    public Foods getInputfoods() {
        return inputfoods;
    }

    public void setInputfoods(Foods inputfoods) {
        this.inputfoods = inputfoods;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,Inputfood> finder=new Finder<String, Inputfood>(String.class,Inputfood.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<Inputfood> showlist(){
        return finder.all();
    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(Inputfood data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit( Inputfood data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(Inputfood data){
        data.delete();
    }
}
