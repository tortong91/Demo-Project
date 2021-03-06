package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Random;


@Entity
@Table(name="db_coo")
public class cooperatives extends Model {
    @Id

    private String id;
    private String name,address,tel,picture;

    public cooperatives() {
       setId();
    }

    public cooperatives(String id, String name, String address, String tel, String picture) {
        if(id==null){
            setId();
        }else{
            setnewid(id);
        }

        this.name = name;
        this.address = address;
        this.tel = tel;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        int i ;
        Random random = new Random();
        i = random.nextInt(100000)+1;
        id = "Ctive-" + Integer.toString(i);
    }
  public void setnewid(String id) {
 this.id=id  ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    //เป็นการหาเอาข้อมูลมาเก็บไว้ใน ชื่อ (finder)
    public static Finder<String,cooperatives> finder=new Finder<String, cooperatives>(String.class,cooperatives.class);
    //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
    public static List<cooperatives> showlist(){
        return finder.all();
    }
    //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
    public static void adddata(cooperatives data1){
        data1.save();
    }
    //เป็นแก้ไขข้อมูลฐานข้อมูล
    public static void edit(cooperatives data1)
    {
        data1.update();
    }
    //เป็นลบข้อมูลฐานข้อมูล
    public static void delet(cooperatives data1){
        data1.delete();
    }
}
