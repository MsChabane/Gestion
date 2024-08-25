
package Contollers;

import Models.ArticleModel;
import Models.TypeArticleModel;
import Views.AddNewArticlesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class AddArticleControllers {
    private AddNewArticlesView v ;// v is Add New Articles View 
    private ArticleModel m ;
    private ArrayList <TypeArticleModel> typeArticles ;
    private int xPanel,yPanel;
    
    public AddArticleControllers(AddNewArticlesView v, ArticleModel m) {
        this.v = v;// v is the Add New Articles View 
        this.m = m;
        typeArticles = new ArrayList<>();// pour remplire les items de comboBox
        
       
        v.setMouseListnerForHeaderPanel(new MouseAdapter (){
            @Override
            public void mousePressed(MouseEvent e) {
                xPanel = e.getX();// xPanel is the loction of mouse curser in the headerPanel of the Articles Frame (sur l'axe ox)
                yPanel = e.getY();// yPanel is the loction of mouse curser in the headerPanel of the Articles Frame (sur l'axe oy)
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
               v.setLocation(xScrean - xPanel, yScean - yPanel);// v it's the frame
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
            JOptionPane.showMessageDialog(v, "NO SELECTED ITEM ,CONNEXION PROBLRM","SYSTEM",JOptionPane.ERROR_MESSAGE);
        }
         v.setActionClose(e->{
             v.dispose ();
        });
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    } 
    private boolean valide() { 
            String dg = v.getTxtDsg();
            String quan = v.getTxtQua();
            String p = v.getTxtPrc();
            int i = v.getComItem();
            if (i<0){
                JOptionPane.showMessageDialog(v, "SELECT ONE ITEM ","COMBO BOX",JOptionPane.WARNING_MESSAGE);
                return false;
            } 
            if (dg == null || dg.isBlank()){
                JOptionPane.showMessageDialog(v, "ENTER THE DESIGNATION OF THIS ARTICLE ","DESIGNATION",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (quan == null || quan.isBlank()){
               JOptionPane.showMessageDialog(v, "ENTER THE QUANTITY OF THIS ARTICLE ","QUANTITY",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (p == null || p.isBlank()){
                JOptionPane.showMessageDialog(v, "ENTER THE PRICE OF THIS ARTICLE ","PRICE",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (!quan.matches("[0-9]+")){
                JOptionPane.showMessageDialog(v, "INVALID VALUE OF QUANTITY ","CORRECT QUANTITY",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (!p.matches("(\\d+).(\\d*)")){
                JOptionPane.showMessageDialog(v, "INVALID VALUE OF PRICE ","CORRECT PRICE",JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(v, "ARTICLE ADDED SUCCESSFULLY ","DONE",JOptionPane.INFORMATION_MESSAGE);
            v.getPerent().addOneRowToArticleTab(m);
            ArticleController.articles.add(m);
            v.clearContent();
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(v, "NO ADDED ARTICLE , CONNEXION PROBLRM","SYSTEM",JOptionPane.ERROR_MESSAGE);
        }  
            }
            
}
