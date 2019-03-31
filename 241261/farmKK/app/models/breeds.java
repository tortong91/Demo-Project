package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tbBreeds")
public class breeds extends Model {
    @Id

    private String id;
    private String name;
    private String picture;
    @OneToMany(mappedBy = "breed", cascade = CascadeType.ALL)//สั่งให้เปลี่ยนข้อมูลให้สัมพันธ์กัน
    private List<Cows> cowList = new ArrayList<Cows>();

    public breeds() {

    }

    public breeds(String id, String name, String picture) {
        this.id=id;
        this.name = name;
        this.picture = picture;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
       this.id=id;
    }

    public void  setnewid(String id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public void setCowList(List<Cows> cowList) {
        this.cowList = cowList;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,breeds> finder=new Finder<String, breeds>(String.class,breeds.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<breeds> showlist(){
        return finder.all();

    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void add(breeds data){
        data.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(breeds data)
    {
        data.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delete(breeds data){
        data.delete();
    }


}
