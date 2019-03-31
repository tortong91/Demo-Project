package controllers;

import models.Vacs;
import models.breeds;
import models.cooperatives;
import play.Play;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;
import views.html.details.detailVac;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controllers.allController.main1;


public class vacController extends Controller {
    public static String picPath = Play.application().configuration().getString("path_Vac");


    //ข้อมูลวัคซีน
    public static Form<Vacs> myForms = Form.form(Vacs.class);
    public static Vacs data1;
    public static List<Vacs> vacList = new ArrayList<Vacs>();

    public static Result showvac() {
        vacList = Vacs.showlist();
        myForms = Form.form(Vacs.class);
        return main1(showVac.render(vacList,myForms));
    }

//    public static Result add() {
//        myForms = Form.form(Vacs.class);
//        return main1(formVac.render(myForms));
//    }

    public static Result input() {
        Form<Vacs> myForm = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Errorvac", "ข้อมูล Primarykey ซ้ำกันในระบบ");
            return main1(formVac.render(myForm));
        } else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return main1(formVac.render(myForm));
                    }
                    data1 = myForm.get();
                    fileName = data1.getId() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(picPath, fileName));
                    data1.setPicture(fileName);
                    vacList.add(data1);
                    Vacs.add(data1);

                }
                return showvac();
            }
        }


    public static Result edit(String id) {
        play.data.Form<Vacs> newformpro = myForms.bindFromRequest();
        data1 = Vacs.finder.byId(id);
        session("vacId",data1.getId());
        if (data1 == null) {
            return showvac();
        } else {
            myForms = play.data.Form.form(Vacs.class).fill(data1);
            return main1(editVac.render(myForms));
        }
    }

    public static Result update() {
        play.data.Form<Vacs> formupdate = myForms.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(editVac.render(formupdate));
        } else {
            Vacs dataEdit = new Vacs(session("vacId"),formupdate.get().getPr(),formupdate.get().getName(),formupdate.get().getPicture());
            Vacs.edit(dataEdit);
            return showvac();
        }
    }

    //ลบ
    public static Result delete(String id) {
        data1 = Vacs.finder.byId(id);
        if (data1 != null) {
            data1.delete();
        }
        return showvac();
    }



    @Entity
    @Table(name="tbFarm")
    public static class addFarm extends Model {
        @Id
        private String farm_id;
        private String fram_name,farm_address,farm_details,farm_tel,picture;

        public addFarm() {
        }

        public addFarm(String farm_id, String fram_name, String farm_address, String farm_details, String farm_tel, String picture) {
            this.farm_id = farm_id;
            this.fram_name = fram_name;
            this.farm_address = farm_address;
            this.farm_details = farm_details;
            this.farm_tel = farm_tel;
            this.picture = picture;
        }

        public String getFarm_id() {
            return farm_id;
        }

        public void setFarm_id(String farm_id) {
            this.farm_id = farm_id;
        }

        public String getFram_name() {
            return fram_name;
        }

        public void setFram_name(String fram_name) {
            this.fram_name = fram_name;
        }

        public String getFarm_address() {
            return farm_address;
        }

        public void setFarm_address(String farm_address) {
            this.farm_address = farm_address;
        }

        public String getFarm_details() {
            return farm_details;
        }

        public void setFarm_details(String farm_details) {
            this.farm_details = farm_details;
        }

        public String getFarm_tel() {
            return farm_tel;
        }

        public void setFarm_tel(String farm_tel) {
            this.farm_tel = farm_tel;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
        public static Finder<String, addFarm> finder=new Finder<String, addFarm>(String.class, addFarm.class);
        //ใน<คือ modei > เอาข้อมูลในฐานข้อมูลมาเก็บใน showlist
        public static List<addFarm> showlist(){
            return finder.all();

        }
        //เป็นเอาข้อมูลบันทึกลงฐานข้อมูล
        public static void add(addFarm dataFarm){
            dataFarm.save();
        }
        //เป็นแก้ไขข้อมูลฐานข้อมูล
        public static void edit(addFarm dataFarm)
        {
            dataFarm.update();
        }



        //เป็นลบข้อมูลฐานข้อมูล
        public static void delet(addFarm dataFarm){
            dataFarm.delete();
        }
    }
}
