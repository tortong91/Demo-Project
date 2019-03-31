package controllers;

import models.Foods;
import models.Inputfood;
import models.Recipe;
import models.foodsAnimal;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import views.html.form_RecipeFoods;
import views.html.show_Recipe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.allController.main1;

public class RecipeController extends Controller {

    //Join Table
    public static List<Inputfood> foodsAnimalList = new ArrayList<Inputfood>();
    public static List<Recipe> RecipeList = new ArrayList<Recipe>();
    public static Form<Recipe> picture = Form.form(Recipe.class);
    public static Form<Recipe> recipeForm = Form.form(Recipe.class);
    public static Recipe data;
    public static Inputfood inputfood;
    public static Inputfood inputfood1;
    public static Inputfood inputfood2;
    public static Inputfood inputfood3;


    public static play.mvc.Result showRecipe() {
        RecipeList = Recipe.list();
        recipeForm = Form.form(Recipe.class);
        foodsAnimalList = Inputfood.showlist();
        return main1(show_Recipe.render(RecipeList,recipeForm, foodsAnimalList));
    }

//    public static play.mvc.Result add() {
//        foodsAnimalList = Inputfood.showlist();
//        recipeForm = Form.form(Recipe.class);
//        return main1(form_RecipeFoods.render(recipeForm, foodsAnimalList));
//    }

    public static play.mvc.Result input() {
        Form<Recipe> newadd = recipeForm.bindFromRequest();
        if (newadd.hasErrors()) {
            return main1(form_RecipeFoods.render(newadd, foodsAnimalList));
        } else {
            data = newadd.get();
            data.setDateRecipe(new Date());
            int am1 = newadd.get().getAm1();
            int am2 = newadd.get().getAm2();
            int am3 = newadd.get().getAm3();
            int am4 = newadd.get().getAm4();
                inputfood = Inputfood.finder.byId(newadd.get().getInputfood().getId_ifoods());
                if (inputfood != null) {
                    int update = inputfood.getTotal() - am1;
                    if  (update<0){
                        flash("more","จำนวนเกินที่มีอยู่ในสต๊อก");
                    }else{
                        inputfood.setTotal(update);
                        Inputfood.edit(inputfood);
                        Recipe.add(data);
                    }
                }
                inputfood1 = Inputfood.finder.byId(newadd.get().getInputfood1().getId_ifoods());
                if (inputfood1!= null){
                    int update = inputfood1.getTotal()  - am2;
                    if  (update<0){
                        flash("more","จำนวนเกินที่มีอยู่ในสต๊อก");
                    }else{
                        inputfood1.setTotal(update);
                        Inputfood.edit(inputfood1);
                        Recipe.add(data);
                    }

                }
                inputfood2 = Inputfood.finder.byId(newadd.get().getInputfood2().getId_ifoods());
                if (inputfood2 != null) {
                    int update = inputfood2.getTotal() - am3;
                    if  (update<0){
                        flash("more","จำนวนเกินที่มีอยู่ในสต๊อก");
                    }else{
                        inputfood2.setTotal(update);
                        Inputfood.edit(inputfood2);
                        Recipe.add(data);
                    }

                }
                inputfood3 = Inputfood.finder.byId(newadd.get().getInputfood3().getId_ifoods());
                if (inputfood3 != null){
                    int update = inputfood3.getTotal()  - am4;
                    if  (update<0){
                        flash("more","จำนวนเกินที่มีอยู่ในสต๊อก");
                    }else{
                        inputfood3.setTotal(update);
                        Inputfood.edit(inputfood3);
                        Recipe.add(data);
                    }


            }

            return showRecipe();
        }
    }


    //ลบ
    public static play.mvc.Result delete(String id) {
        data = Recipe.finder.byId(id);
        if (data != null) {
            Recipe.delete(data);

        }
        RecipeList = Recipe.list();
        return showRecipe();
    }


}
