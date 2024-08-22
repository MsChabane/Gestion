
package Contollers;

import Models.ArticleModel;
import Models.TypeArticleModel;
import Views.AddNewArticles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddArticleControllers {
    private AddNewArticles v ;
    private ArticleModel m ;
    private ArrayList <TypeArticleModel> typeArticles ;
    private int xPanel,yPanel;
    
    public AddArticleControllers(AddNewArticles v, ArticleModel m) {
        this.v = v;
        this.m = m;
        typeArticles = new ArrayList<>();
        
        
        v.setMouseListnerForHeaderPanel(new MouseAdapter (){
            @Override
            public void mousePressed(MouseEvent e) {
                xPanel = e.getX();
                yPanel = e.getY();
                v.setOpacity(0.5f);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                 v.setOpacity(1f); 
            }
            
            
        });
        
        v.setMouseMotionListnerForHeaderPanel(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
               int xScrean,yScean;
               xScrean = e.getXOnScreen();
               yScean = e.getYOnScreen();
               v.setLocation(xScrean - xPanel, yScean - yPanel);
            }
            
        });
        v.setActionAdd(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
               if (valide()){
                   ajouter ();
               }
            }

      
        });
        try {
            getComItemFromView();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         v.setActionClose(e->{
             v.dispose ();
        });
        v.setVisible(true);
    } 
    private boolean valide() {
            String dg = v.getTxtDsg();
            String quan = v.getTxtQua();
            String p = v.getTxtPrc();
            int i = v.getComItem();
            if (i<0){
                System.out.println("Select an Item");
                return false;
            } 
            if (dg == null || dg.isBlank()){
                System.out.println("Write smth in dg");
                return false;
            }
            if (quan == null || quan.isBlank()){
                System.out.println("Write smth in quan");
                return false;
            }
            if (p == null || p.isBlank()){
                System.out.println("Write smth in price");
                return false;
            }
            if (!quan.matches("[0-9]+")){
                 System.out.println("Write a Integer number in quan");
                return false;
            }
            if (!p.matches("(\\d+).(\\d*)")){
                 System.out.println("Write a Float number in price");
                return false;
            } 
            m.setTypeArt(typeArticles.get(i));m.setDisiniationArt(dg);m.setQuantiteArt(Integer.parseInt(quan));m.setPrixArt(Double.parseDouble(p));
        return true ;
        
            }
    private void getComItemFromView () throws SQLException{
        ResultSet rs = TypeArticleModel.display_all_Type();
        while (rs.next()){
           typeArticles.add(new TypeArticleModel (rs.getString("libble_type"),rs.getString("mnimo_type"),rs.getInt("id_type")));
        }
        rs.close();
        v.remplireComboItems(typeArticles);
    }

    private void ajouter() {
        try {
            m.ajouterArticle(m);
            System.out.println("Ajouter avec succe ");
            v.getPerent().addOneRowToArticleTab(m);
            v.clearContent();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
            }
            
}
