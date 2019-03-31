package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbRecipe")
public class Recipe extends Model {
    @Id
    private String rid;

    @GeneratedValue
    private  int number;

    private int amountCow;
    private int am1,am2,am3, am4,total;
    private String nameRecipe,dateTimeRecipe;
    @Formats.DateTime(pattern="dd/MM/yyyy")
    private  Date dateRecipe=new Date();

    @ManyToOne()
    private Foods foods;
    @ManyToOne()
    private Inputfood inputfood;
    @ManyToOne()
    private Inputfood inputfood1;
    @ManyToOne()
    private Inputfood inputfood2;
    @ManyToOne()
    private Inputfood inputfood3;

    public Recipe() {
setRid();
    }


    public Recipe(String rid, String nameRecipe, Date dateRecipe, String dateTimeRecipe,int total, int amountCow, int am1, int am2, int am3,int am4, Foods foods) {
        if (rid==null){
            setRid();
        }else{
            setnewrid(rid);
        }
        this.nameRecipe = nameRecipe;
        this.dateRecipe = dateRecipe;
        this.dateTimeRecipe = dateTimeRecipe;
        this.amountCow = amountCow;
        this.am1 = am1;
        this.am2 = am2;
        this.am3 = am3;
        this.am4 = am4;
        this.total=total;
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



    public String getNameRecipe() {
        return nameRecipe;
    }

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
    }

    public Date getDateRecipe() {
        return dateRecipe;
    }

    public void setDateRecipe(Date dateRecipe) {
        this.dateRecipe = dateRecipe;
    }

    public String getDateTimeRecipe() {
        return dateTimeRecipe;
    }

    public void setDateTimeRecipe(String dateTimeRecipe) {
        this.dateTimeRecipe = dateTimeRecipe;
    }

    public int getAmountCow() {
        return amountCow;
    }

    public void setAmountCow(int amountCow) {
        this.amountCow = amountCow;
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

    public Inputfood getInputfood1() {
        return inputfood1;
    }

    public void setInputfood1(Inputfood inputfood1) {
        this.inputfood1 = inputfood1;
    }

    public Inputfood getInputfood2() {
        return inputfood2;
    }

    public void setInputfood2(Inputfood inputfood2) {
        this.inputfood2 = inputfood2;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAm1() {
        return am1;
    }

    public void setAm1(int am1) {
        this.am1 = am1;
    }

    public int getAm2() {
        return am2;
    }

    public void setAm2(int am2) {
        this.am2 = am2;
    }

    public int getAm3() {
        return am3;
    }

    public void setAm3(int am3) {
        this.am3 = am3;
    }

    public int getAm4() {
        return am4;
    }

    public void setAm4(int am4) {
        this.am4 = am4;
    }

    public Inputfood getInputfood3() {
        return inputfood3;
    }

    public void setInputfood3(Inputfood inputfood3) {
        this.inputfood3 = inputfood3;
    }

    public int getTotal() {
        return total=am1+am2+am3+am4;
    }

    public void setTotal(int total) {
        this.total = total;
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
