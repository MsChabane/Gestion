
package Models;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class TypeArticleModel {
   private String libbleTypeArt ,mnimoTypeArt;
   private int idArt;

    public String getLibbleTypeArt() {
        return libbleTypeArt;
    }

    public void setLibbleTypeArt(String libbleTypeArt) {
        this.libbleTypeArt = libbleTypeArt;
    }

    public String getMnimoTypeArt() {
        return mnimoTypeArt;
    }

    public void setMnimoTypeArt(String mnimoTypeArt) {
      
        this.mnimoTypeArt = mnimoTypeArt;
    }

    public int getIdArt() {
        return idArt;
    }

    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }

    public TypeArticleModel(String libbleTypeArt, String mnimoTypeArt, int idArt) {
        this.libbleTypeArt = libbleTypeArt;
        this.mnimoTypeArt = mnimoTypeArt;// 1 or 2 or 3 or 4 caracters
        this.idArt = idArt;
    }
    
   public static  void add (TypeArticleModel type) throws SQLException{
       // the id_type is autoIncrement so we dont have to spacify it in the query;
       PreparedStatement prp = Connexion.connecter().prepareStatement("insert into type_art (libble_type,mnimo_type) values (?,?)");
       prp.setString(1,type.getLibbleTypeArt());
       prp.setString(2, type.getMnimoTypeArt());
       prp.executeUpdate();
   }
   public static  void modify (TypeArticleModel type) throws SQLException{
       // dont forget : we dont have to change the "mnimonique" attribute because we use it into the id of article 
       PreparedStatement prp = Connexion.connecter().prepareStatement("update type_art set libble_type= ? where id_type = ?");
       prp.setString(1,type.getLibbleTypeArt());
       prp.setInt(2, type.getIdArt());
       prp.executeUpdate();
   }
   public static  ResultSet display_all_Type () throws SQLException{ 
       ResultSet res = Connexion.connecter().prepareStatement("select * from type_art").executeQuery();
       return res;
   }

    @Override
    public String toString() {
        return "TypeArticleModel{" + "libbleTypeArt=" + libbleTypeArt + ", mnimoTypeArt=" + mnimoTypeArt + ", idArt=" + idArt + '}';
    }
   
   
}
