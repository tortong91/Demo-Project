package controllers;

import models.Foods;
import models.Inputfood;
import models.Recipe;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;
import views.html.Recipe.showRecipe;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.allController.data;
import static controllers.allController.main1;

public class inputfoodsController extends Controller {
    //inputfoods
    public static String picPath = Play.application().configuration().getString("path_form");
    public static List<Inputfood> listinputfoods = new ArrayList<Inputfood>();
    public static List<Foods> foodsList = new ArrayList<Foods>();
    public static List<Recipe> recipeList = new ArrayList<Recipe>();
    public static Form<Inputfood> formsinputfoods = Form.form(Inputfood.class);
    public static Inputfood inputfoods;

    public static Result showinputfoods() {
        listinputfoods = Inputfood.showlist();
        formsinputfoods = Form.form(Inputfood.class);
        foodsList = Foods.showlist();
        recipeList = Recipe.list();

        return main1(showinputfoods.render(listinputfoods,formsinputfoods,foodsList,recipeList));
    }

    public static Result showinputDatafoods() {
        listinputfoods = Inputfood.showlist();
        foodsList = Foods.showlist();


        return main1(showDatainputfoods.render(listinputfoods,foodsList));
    }
    public static Result inputfoodseach(){
        listinputfoods = Inputfood.finder.where().orderBy().desc("date").findList();//title คือชื่อฟิลด์ที่ต้องการค้นหา
        formsinputfoods = Form.form(Inputfood.class);
        foodsList = Foods.showlist();
        recipeList = Recipe.list();

        return main1(showinputfoods.render(listinputfoods,formsinputfoods,foodsList,recipeList));
    }
    //แผนการฉีดวัคซีน

//    public static Result addinputfoods() {
//        foodsList = Foods.showlist();
//        recipeList = Recipe.list();
//        formsinputfoods = play.data.Form.form(Inputfood.class);
//        return main1(formInputfoods.render(formsinputfoods, foodsList,recipeList));
//    }


    public static Result inputmyfoods() {
        Form<Inputfood> myForms = formsinputfoods.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForms.hasErrors()) {
            flash("Errorinputfood", "ข้อมูลมีการซ้ำกันในระบบ");
            return main1(formInputfoods.render(myForms, foodsList, recipeList));

        } else {

            inputfoods = myForms.get();
            Inputfood food = Inputfood.findFood(inputfoods.getInputfoods().getId());
            if (food != null) {
                flash("data", "อาหารที่บันทึก มีในสต๊อกแล้วหากท่านต้องการจะนำเข้าเพิ่ม ให้ทำการคลิกที่ +รับอาหารเพิ่ม  ");
                return inputfoodseach();
            } else {

                Inputfood chk1;
                chk1 = Inputfood.finder.byId(inputfoods.getId_ifoods());
                if (chk1 != null) {
                    flash("Errorinputfood", "ข้อมูล Primarykey ซ้ำกันในระบบ");
                    flash("data", "ข้อมูล โคขุน ที่ท่านเลือกมีการจัดสูตรไว้ก่อนหน้านี้แล้ว");

                    return inputmyfoods();
                } else {
                    if (picture != null) {
                        contentType = picture.getContentType();
                        File file = picture.getFile();
                        fileName = picture.getFilename();
                        if (contentType.startsWith("images")) {
                            return main1(formInputfoods.render(myForms, foodsList, recipeList));
                        }
                        inputfoods = myForms.get();

                        fileName = inputfoods.getId_ifoods() + fileName.substring(fileName.lastIndexOf("."));
                        file.renameTo(new File(picPath, fileName));
                        inputfoods.setPicture(fileName);
                        int total = myForms.get().getAmountKg() * myForms.get().getAmount_ifoods();
                        int totalPrie = myForms.get().getPrice_ifoods() * myForms.get().getPrice_ifoods();
                        inputfoods.setTotal(total);
                        inputfoods.setTotalPrice(totalPrie);
                        inputfoods.setDate(new Date());
                        Inputfood.add(inputfoods);

                    }
                    return inputfoodseach();
                }
            }
        }
    }
    public static Result seacth() {
        listinputfoods = Inputfood.finder.where().ne("name_ifoods", "กำลังตั้งชื่อ").findList();
        formsinputfoods = Form.form(Inputfood.class);
        foodsList = Foods.showlist();
        recipeList = Recipe.list();

        return main1(showinputfoods.render(listinputfoods,formsinputfoods,foodsList,recipeList));


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
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        if (update.hasErrors()) {

            return main1(editInputfoods.render(update, foodsList));
        } else {
            inputfoods=update.get();
            if (picture != null) {
                String fileName = picture.getFilename();
                String extension = fileName.substring(fileName.indexOf("."));
                String realName = inputfoods.getId_ifoods() + extension;
                File file = picture.getFile();
                File temp = new File("public/images/Pic/" + realName);
                if (temp.exists()) {
                    temp.delete();
                }
                file.renameTo(new File("public/images/Pic/" + realName));
                inputfoods.setPicture(realName);
            }
            inputfoods = update.get();
            int total = update.get().getAmountKg() * update.get().getAmount_ifoods();
            int totalPrie = update.get().getPrice_ifoods() * update.get().getPrice_ifoods();
            inputfoods.setTotal(total);
            inputfoods.setTotalPrice(totalPrie);
            inputfoods.setDate(new Date());
            Inputfood.edit(inputfoods);
            return inputfoodseach();
        }
    }

    //ลบ
    public static Result deleteinputfoods(String id) {
        inputfoods = Inputfood.finder.byId(id);
        if (inputfoods != null) {
            File temp = new File("public/images/Pic/" + inputfoods.getPicture());
            temp.delete();
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
