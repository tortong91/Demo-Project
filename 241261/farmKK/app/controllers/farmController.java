package controllers;

import models.farm;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.edit_Farm;
import views.html.form_Farm;
import views.html.show_Farm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controllers.allController.main1;

public class farmController extends Controller {
    public static String picPath = Play.application().configuration().getString("path_farm");

    //database sql ข้อมูลฟาร์ม
    public static Form<farm> myFormsFarm = Form.form(farm.class);
    public static farm dataFarm;
    public static List<farm> farmList = new ArrayList<farm>();
    public static Result showfarm() {
        farmList = farm.showlist();
        return main1(show_Farm.render(farmList));
    }

    public static Result addfarm() {
        myFormsFarm = play.data.Form.form(farm.class);
        return main1(form_Farm.render(myFormsFarm));
    }

    public static Result inputfarm() {
        Form<farm> myForm = myFormsFarm.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            return main1(form_Farm.render(myForm));
        } else {
            if (picture != null) {
                contentType = picture.getContentType();
                File file = picture.getFile();
                fileName = picture.getFilename();
                if (contentType.startsWith("images")) {
                    return main1(form_Farm.render(myForm));
                }
                dataFarm = myForm.get();
                fileName = dataFarm.getFarm_id() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                dataFarm.setPicture(fileName);
                farmList.add(dataFarm);
                farm.add(dataFarm);

            }
            return showfarm();

        }
    }

    public static Result editfarm(String id) {
        play.data.Form<farm> newformpro = myFormsFarm.bindFromRequest();
        dataFarm = farm.finder.byId(id);
        session("FarmId",dataFarm.getFarm_id());
        if (dataFarm == null) {
            return showfarm();
        } else {
            myFormsFarm = play.data.Form.form(farm.class).fill(dataFarm);
            return main1(edit_Farm.render(myFormsFarm));
        }
    }

    public static Result updatefarm() {
        play.data.Form<farm> formupdate = myFormsFarm.bindFromRequest();
        if (formupdate.hasErrors()) {
            return main1(edit_Farm.render(formupdate));
        } else {
            farm ffarm = new farm(session("FarmId"),formupdate.get().getFram_name(),formupdate.get().getFarm_address(),formupdate.get().getFarm_tel(),formupdate.get().getDate_farm(),formupdate.get().getFarm_details(),formupdate.get().getDate_farm());
            farm.edit(ffarm);
            return showfarm();
        }
    }

    //ลบ
    public static Result deletfarm(String id) {
        dataFarm = farm.finder.byId(id);
        if (dataFarm != null) {
            dataFarm .delete();
        }
        return showfarm();
    }


}
