package Contollers;

import Models.ArticleModel;
import Views.EditArticleView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EditArticleController {
    private EditArticleView v;// v is Edit Article View 
    private ArticleModel m;
    private int xPanel,yPanel;
   
    public EditArticleController(EditArticleView v, ArticleModel m) {
        this.v = v;
        this.m = m;
        
        
        
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
        v.setActionEdit(e->{
            if (valide()){
                try {
                    modifier ();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(v, "NO MODIFICATION IN DATA BASE ,CONNEXION PROBLRM","SYSTEM",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        v.setActionClose(e->{
            v.dispose();
        });
        v.setLabContent(m);
       v.setLocationRelativeTo(null);
       v.setVisible(true);
    }
     private boolean valide() {
            String dg = v.getTxtDsg();
            String quan = v.getTxtQua();
            String p = v.getTxtPrc();
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
            int quantite = Integer.parseInt(quan);
            double prix = Double.parseDouble(p);
            if (dg.equals(m.getDisiniationArt()) && quantite == m.getQuantiteArt() && prix == m.getPrixArt()){
                JOptionPane.showMessageDialog(v, "NO APPEAR CHANGRS","EDIT",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            m.setDisiniationArt(dg);m.setQuantiteArt(quantite);m.setPrixArt(prix);
            return true ;
        
            }

    private void modifier() throws SQLException{
        m.modifierArticle(m);
        JOptionPane.showMessageDialog(v, "ARTICLE EdITED SUCCESSFULLY ","DONE",JOptionPane.INFORMATION_MESSAGE);
        int secetedI = v.getArtVPerent().getSelectedIndexFromTable();
        ArticleController.articles.set(secetedI, m);
        v.getArtVPerent().editOneRowOfArticleTab(secetedI, m);
        v.dispose();
    }
     
     
}
