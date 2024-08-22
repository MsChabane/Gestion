
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddresseModel {
    private int addId;
    private String ville;
    private String cite;
    private String rue;
    private String codePostal;

    public AddresseModel(int addId, String ville, String cite, String rue, String codePostal) {
        this.addId = addId;
        this.ville = ville;
        this.cite = cite;
        this.rue = rue;
        this.codePostal = codePostal;
        //addId++;
    }

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    public static void ajouterAdd (AddresseModel addm) throws SQLException{
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("insert into address  values (?,?,?,?,?)");
       pre.setInt(1,addm.getAddId());
       pre.setString(2,addm.getVille());
       pre.setString(3,addm.getCite());
       pre.setString(4,addm.getRue());
       pre.setString(5,addm.getCodePostal());
       pre.executeLargeUpdate();
    }
    
    public static void modifierAdd (AddresseModel newAdd) throws SQLException{
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("update address set  ville = ? , cite = ? , rue = ? ,code_postal = ?  where  id_add = ? ;");
       pre.setString(1,newAdd.getVille());
       pre.setString(2,newAdd.getCite());
       pre.setString(3,newAdd.getRue());
       pre.setString(4,newAdd.getCodePostal());
       pre.setInt(5,newAdd.getAddId());
       pre.executeUpdate();    
    }
    
    public static void supprimerAdd (){
        
    }
    public static  ResultSet display_all_Add () throws SQLException{ 
       ResultSet res = Connexion.connecter().prepareStatement("select * from address ").executeQuery();
       return res;
   } 
}
