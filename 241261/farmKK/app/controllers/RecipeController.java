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
import java.util.List;

public class RecipeController extends Controller {

    //Join Table
    public static List<Inputfood> foodsAnimalList = new ArrayList<Inputfood>();
    public static List<Recipe> RecipeList = new ArrayList<Recipe>();
    public static Form<Recipe> picture = Form.form(Recipe.class);
    public static Form<Recipe> recipeForm = Form.form(Recipe.class);
    public static Recipe data;



    public static play.mvc.Result showRecipe() {
        RecipeList = Recipe.list();
        return ok(show_Recipe.render(RecipeList));
    }

    public static play.mvc.Result add() {
        foodsAnimalList = Inputfood.showlist();
        recipeForm = Form.form(Recipe.class);
        return ok(form_RecipeFoods.render(recipeForm,foodsAnimalList));
    }

    public static play.mvc.Result input() {
        Form<Recipe> newadd = recipeForm.bindFromRequest();
        if (newadd.hasErrors()) {
            return ok(form_RecipeFoods.render(newadd, foodsAnimalList));
        } else {
                 data = newadd.get();
                Recipe.add(data);
                return add();
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
