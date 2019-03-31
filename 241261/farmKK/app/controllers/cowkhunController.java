package controllers;

import models.Cows;
import models.UserKK;
import models.breeds;
import models.sellsCow;
import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;
import views.html.details.detailsCow;
import views.html.sells.sellCow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controllers.allController.main1;

public class cowkhunController extends Controller {

    public static String picPath = Play.application().configuration().getString("path_formCow");


    //ข้อมูลวัคซีน
    public static Form<Cows> myForms = Form.form(Cows.class);
    public static Cows data1;
    public static List<Cows> cowList = new ArrayList<Cows>();
    public static List<breeds> breedList = new ArrayList<breeds>();

    public static Result showcow() {
        cowList = Cows.showlist();
        return main1(showCow.render(cowList));
    }

    public static Result add() {
        breedList = breeds.showlist();
        myForms = Form.form(Cows.class);
        return main1(form_Cow.render(myForms, breedList));
    }

    public static Result input() {
        Form<Cows> myForm = myForms.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Errorcow", "ข้อมูลซ้ำกันในระบบ");
            return main1(form_Cow.render(myForm, breedList));
        } else {

            if (picture != null) {
                contentType = picture.getContentType();
                File file = picture.getFile();
                fileName = picture.getFilename();
                if (contentType.startsWith("images")) {
                    return main1(form_Cow.render(myForm, breedList));
                }
                data1 = myForm.get();
                fileName = data1.getCow_id() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                data1.setPicture(fileName);
                Cows.add(data1);

            }
            return showcow();

        }
    }

    public static Result edit(String id) {
        Form<Cows> newformpro = myForms.bindFromRequest();
        data1 = Cows.finder.byId(id);
        session("cowId", data1.getCow_id());
        if (data1 == null) {
            return showcow();
        } else {
            breedList = breeds.showlist();
            myForms = Form.form(Cows.class).fill(data1);
            return main1(editCow.render(myForms, breedList));
        }
    }

    public static Result update() {
        Form<Cows> formupdate = myForms.bindFromRequest();
        data1 = Cows.finder.byId(session("cowId"));
        if (formupdate.hasErrors()) {
            return main1(editCow.render(formupdate, breedList));
        } else {
            Cows cowdata = new Cows(data1.getBreed(), "sale",formupdate.get().getPrice(), session("cowId"), formupdate.get().getSex(), formupdate.get().getColor(), formupdate.get().getDate(), formupdate.get().getAge(), formupdate.get().getPicture(), formupdate.get().getWeight(), formupdate.get().getHeight());
            Cows.edit(cowdata);
            return showcow();
        }
    }

    //ลบ
    public static Result delete(String id) {
        data1 = Cows.finder.byId(id);
        if (data1 != null) {
            data1.delete();
        }
        return showcow();
    }

    public static Result detailsCow(String id) {
        int i;
        for (i = 0; i < cowList.size(); i++) {
            if (cowList.get(i).getCow_id().equals(id)) {
                break;
            }
        }
        return ok(detailsCow.render(cowList.get(i)));
    }
//    public static Result two(){
//        DynamicForm myForms = Form.form().bindFromRequest();
//        String se = myForms.get("search"); //"sh" สีเขียวคือชื่อที่ตั้งตรง inputtext
//
//        cowList = Cows.finder.where().like("cow_id",'%'+se+'%').findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
//        return main1(showCow.render(cowList));
//    }

    public static Result showcow1() {
        cowList = Cows.showlist();
        return main1(showCow1.render(cowList));
    }

    public static Result edit1(String id) {
        Form<Cows> newformpro = myForms.bindFromRequest();
        data1 = Cows.finder.byId(id);
        session("cowId", data1.getCow_id());
        if (data1 == null) {
            return showcow1();
        } else {
            breedList = breeds.showlist();
            myForms = Form.form(Cows.class).fill(data1);
            return main1(formCow1.render(myForms, breedList));
        }
    }

    public static Result update1() {
        Form<Cows> formupdate = myForms.bindFromRequest();
        data1 = Cows.finder.byId(session("cowId"));
        if (formupdate.hasErrors()) {
            return main1(formCow1.render(formupdate, breedList));
        } else {
            Cows cowdata = new Cows(data1.getBreed(), "sale",formupdate.get().getPrice(), session("cowId"), formupdate.get().getSex(), formupdate.get().getColor(), formupdate.get().getDate(), formupdate.get().getAge(), formupdate.get().getPicture(), formupdate.get().getWeight(), formupdate.get().getHeight());
            Cows.edit(cowdata);
            return showcow1();
        }
    }

//    public static play.mvc.Result check (String id ){
//        cowList = Cows.brandList(id);
//        if(cowList != null){
//            return main1(showCow.render(cowList));
//        }else {
//            cowList = Cows.showlist();
//            return main1(showCow.render(cowList));
//        }
//    }
}
