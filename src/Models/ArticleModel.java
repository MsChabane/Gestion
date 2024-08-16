package Models;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ArticleModel {
    private String codeArt;
    private String disiniationArt ;
    private int quantiteArt ;
    private double prixArt;
    private TypeArticleModel typeArt ;

    public String getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }

    public String getDisiniationArt() {
        return disiniationArt;
    }

    public void setDisiniationArt(String disiniationArt) {
        this.disiniationArt = disiniationArt;
    }

    public int getQuantiteArt() {
        return quantiteArt;
    }

    public void setQuantiteArt(int quantiteArt) {
        this.quantiteArt = quantiteArt;
    }

    public double getPrixArt() {
        return prixArt;
    }

    public void setPrixArt(double prixArt) {
        this.prixArt = prixArt;
    }

    public TypeArticleModel getTypeArt() {
        return typeArt;
    }

    public void setTypeArt(TypeArticleModel typeArt) {
        this.typeArt = typeArt;
    }

    public ArticleModel(String codeArt, String disiniationArt, int quantiteArt, double prixArt, TypeArticleModel typeArt) {
        this.codeArt = codeArt;
        this.disiniationArt = disiniationArt;
        this.quantiteArt = quantiteArt;
        this.prixArt = prixArt;
        this.typeArt = typeArt;
    }

    public ArticleModel() {
    }
    
   public static void ajouterArticle (ArticleModel artM ) throws SQLException{
       ArticleModel.generatId (artM);
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("insert into article  values (?,?,?,?,?)");
       pre.setString(1,artM.getCodeArt());
       pre.setString(2,artM.getDisiniationArt());
       pre.setInt(3,artM.getQuantiteArt());
       pre.setInt(4,artM.getTypeArt().getIdArt());
       pre.setDouble(5,artM.getPrixArt());
        pre.executeUpdate();
   }  
    
    public static void modifierArticle (ArticleModel artM ) throws SQLException{   
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("update article set disiniation = ? , quantite = ? , prix = ? where code_art = ? ;");
       pre.setString(1,artM.getDisiniationArt());
       pre.setInt(2,artM.getQuantiteArt());
       pre.setDouble(3,artM.getPrixArt());
       pre.setString(4,artM.getCodeArt());
       pre.executeUpdate();    
   } 

   // mnimo+ 3 car ordre    
    private  static void  generatId (ArticleModel artM) throws SQLException{
          /* if
          we have a mnimo like  "VEG" ==> the code article will be like "VEG_000"
          so we will change a little bit in the method genererId of class Article Model
          the change is  to make the mnino 4 caracters if its not ;
          
           */
          String id = artM.typeArt.getMnimoTypeArt();//
          //check if the if is of 4 caracters here if not try to fill the space use the caracter _ ;
          id = id.trim();
        id = (id.length()==4) ? id:
                (id.length()==3)?id.concat("_"):
                (id.length()==2)?id.concat("__"):id.concat("___");
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("select max(substring(code_art,5)) from article where code_art like ? ");
       pre.setString(1,id.concat("%"));
        ResultSet rs = pre.executeQuery();
        if (rs.next() && rs.getString(1) != null){
           String v = rs.getString(1);// eg : "001"
           int num = Integer.parseInt(v);
           num ++;
           String v2 = String.format("%03d",num);
           id=id.concat(v2);  
        }
        else id = id.concat("000"); 
        artM.setCodeArt(id);
     }  

    public ArticleModel(String codeArt, TypeArticleModel typeArt) {
        this.codeArt = codeArt;
        this.typeArt = typeArt;
    }
    
    public static ResultSet display_allArticles () throws SQLException{
       Connection con = Connexion.connecter();
       Statement stm = con.createStatement();
       ResultSet rs = stm.executeQuery("select code_art , disiniation ,quantite , libble_type ,prix from article , type_art  where article.type_art = type_art.id_type    ");
        /*Connection con = Connexion.connecter();
        PreparedStatement pre =con.prepareStatement("select code_art , disiniation ,quantite , mnimo_type from article , type_art  where article.type_art = type_art.id_type ; ");
        ResultSet rs = pre.executeQuery();*/
       /* ArrayList  <ArticleModel>  rl = new ArrayList <> ();
        while (rs.next()){
            rl.add(new ArticleModel (rs.getString("code_art"),rs.getString(2),rs.getInt(3),rs.getDouble("prix"),new TypeArticleModel (rs.getString("libble_type"),null,0)));
        }
        return rl;*/
       return rs;
    }
    
    public  static void dic_quantite  (int quantite_vent  , String code_art ) throws SQLException{
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("update article  set quantite = quantite - ?  where code_art = ? ;");
       pre.setInt(1,quantite_vent);
       pre.setString(2,code_art);
       pre.executeUpdate();  
    }
    
    public static ResultSet display_existArticle  () throws SQLException{
       Connection con = Connexion.connecter();
       Statement stm = con.createStatement();
       ResultSet rs = stm.executeQuery("select code_art , disiniation ,quantite ,prix from article where quantite > 0 ");
       return rs;
    }
    
    
    @Override
    public String toString() {
        return "ArticleModel{" + "codeArt=" + codeArt + ", disiniationArt=" + disiniationArt + ", quantiteArt=" + quantiteArt + ", prixArt=" + prixArt + ", typeArt=" + typeArt.getLibbleTypeArt() + '}';
    }
    
}