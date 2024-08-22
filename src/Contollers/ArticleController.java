package Contollers;

import Models.ArticleModel;
import Models.TypeArticleModel;
import Views.AddNewArticles;
import Views.ArticlesViews;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;





public class ArticleController {
    private ArticlesViews v ;
    private ArticleModel m ;
    private ArrayList <ArticleModel> articles ;
    private int xPanel,yPanel;
    public ArticleController(ArticlesViews v, ArticleModel m) {
        this.v = v;
        this.m = m;
        articles = new ArrayList<>();
        v.setActionClose(e->{
             System.exit(0);
        });
        v.setActionAjouter(e->{
              new AddArticleControllers(new AddNewArticles(v,true), m);
               
           
        });
       /* 
        v.setActionTextRecherche( new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
               String rechercheValue  = v.getTextRecherch();
               if (rechercheValue == null || rechercheValue.isBlank()){
                   v.remplirTable(articles);
               }else {
                   List <ArticleModel> articleFiltrer ;
                        articleFiltrer  = articles.stream().filter(article->article.getDisiniationArt().contains(rechercheValue) || article.getTypeArt().getLibbleTypeArt().contains(rechercheValue)).
                        toList();
                        v.remplirTable(articleFiltrer);
               }
               
            }
            
        });*/
        v.setKeyBoardTextRecherche(new KeyAdapter () {
            @Override
            public void keyTyped(KeyEvent e) {
                String rechercheValue  = v.getTextRecherch();
               if (rechercheValue == null || rechercheValue.isBlank()){
                   v.remplirTable(articles);
               }else {
                   List <ArticleModel> articleFiltrer ;
                        articleFiltrer  = articles.stream().filter(article->article.getDisiniationArt().contains(rechercheValue) || article.getTypeArt().getLibbleTypeArt().contains(rechercheValue)).
                        toList();
                        v.remplirTable(articleFiltrer);
               }
            }
        
        });
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
        
        
        try {
            getDataFromModelToView();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        v.setVisible(true);
    }
    private void getDataFromModelToView () throws SQLException{
        ResultSet rs = m.display_allArticles();
        while (rs.next()){
            articles.add(new ArticleModel (rs.getString("code_art"),rs.getString(2),rs.getInt(3),rs.getDouble("prix"),new TypeArticleModel (rs.getString("libble_type"),null,0)));
        }
        rs.close();
        v.remplirTable(articles);
    }
    
    
    
    
}