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

            Recipe.add(data);
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
