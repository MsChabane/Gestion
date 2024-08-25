/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Start;


import Contollers.ArticleController;
import Models.ArticleModel;
import Views.ArticlesViews;







public class Main {
    public static void main(String[] args) {
      ArticleController c = new ArticleController (new ArticlesViews (),new ArticleModel());
      
    /*double x = Double.parseDouble("12,8");
        //System.out.println(".".matches("(\\d+).(\\d*)"));
        System.out.println(x);*/
    }
}
