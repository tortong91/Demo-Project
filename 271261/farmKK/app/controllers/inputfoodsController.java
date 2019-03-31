package controllers;

import models.Foods;
import models.Inputfood;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.Recipe.showRecipe;
import views.html.editInputfoods;
import views.html.formInputfoods;
import views.html.show_Recipe;
import views.html.showinputfoods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controllers.allController.main1;

public class inputfoodsController extends Controller {
    //inputfoods
    public static String picPath = Play.application().configuration().getString("path_form");
    public static List<Inputfood> listinputfoods = new ArrayList<Inputfood>();
    public static List<Foods> foodsList = new ArrayList<Foods>();
    public static Form<Inputfood> formsinputfoods = Form.form(Inputfood.class);
    public static Inputfood inputfoods;

    public static Result showinputfoods() {
        listinputfoods = Inputfood.showlist();
        return main1(showinputfoods.render(listinputfoods));
    }

    public static Result addinputfoods() {
        foodsList = Foods.showlist();
        formsinputfoods = play.data.Form.form(Inputfood.class);
        return main1(formInputfoods.render(formsinputfoods, foodsList));
    }


    public static Result inputmyfoods() {
        Form<Inputfood> myForms = formsinputfoods.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForms.hasErrors()) {
            flash("Error", "ข้อมูลมีการซ้ำกันในระบบ");
            return main1(formInputfoods.render(myForms, foodsList));

        } else {
            if (picture != null) {
                contentType = picture.getContentType();
                File file = picture.getFile();
                fileName = picture.getFilename();
                if (contentType.startsWith("images")) {
                    return main1(formInputfoods.render(myForms, foodsList));
                }
                inputfoods = myForms.get();

                fileName = inputfoods.getId_ifoods() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(picPath, fileName));
                inputfoods.setPicture(fileName);
                Inputfood.add(inputfoods);

            }
            return showinputfoods();
        }
    }

    public static Result seacth() {
        listinputfoods = Inputfood.finder.where().ne("name_ifoods", "กำลังตั้งชื่อ").findList();
        return main1(showinputfoods.render(listinputfoods));
    }


    public static Result editinputfoods(String id) {
        play.data.Form<Inputfood> newform =formsinputfoods.bindFromRequest();
        inputfoods = Inputfood.finder.byId(id);
        if (inputfoods == null) {
            return showinputfoods();
        } else {
            foodsList=Foods.showlist();
            formsinputfoods = Form.form(Inputfood.class).fill(inputfoods);
            return main1(editInputfoods.render(formsinputfoods, foodsList));
        }
    }

    public static Result updateinputfoods() {
        play.data.Form<Inputfood> update = formsinputfoods.bindFromRequest();
        if (update.hasErrors()) {
            return main1(editInputfoods.render(update, foodsList));
        } else {
            inputfoods = update.get();
            Inputfood.edit(inputfoods);
            return showinputfoods();
        }
    }

    //ลบ
    public static Result deleteinputfoods(String id) {
        inputfoods = Inputfood.finder.byId(id);
        if (inputfoods != null) {
            inputfoods.delete();
        }
        return showinputfoods();

    }


//รายละเอียด

/*
    public static Result details(String id) {
        int i;
        for (i = 0; i < product3List.size(); i++){
            if (product3List.get(i).getId().equals(id)) {
                break;
            }
        }
        return ok(information_cooparetive.render(product3List.get(i));
    }
    /*public static Result ishow2() {

        return ok(Detailss.render();
    }*/
    /*-----ข้อมูลอาหารสัตว์*/

    ///////////////สูตรอาหาร//////////////////
    public static List<Inputfood> recipefoodsList = new ArrayList<Inputfood>();
    public static Form<Inputfood> recipefoodsForm = Form.form(Inputfood.class);
    public static Inputfood recipedata;
    public static Result showrecipe(){
        recipefoodsList=Inputfood.showlist();
        recipefoodsForm = Form.form(Inputfood.class);
        return main1(showRecipe.render(recipefoodsList,recipefoodsForm));
    }
    public static play.mvc.Result inputrecipe() {

        Form<Inputfood> myForm = recipefoodsForm.bindFromRequest();
        if (myForm.hasErrors()) {
            return main1(showRecipe.render(recipefoodsList,myForm));

        } else {
            recipedata = myForm.get();
            Inputfood.add(recipedata);
        }
        return showrecipe();

    }

    /////////////////////////////////

}
