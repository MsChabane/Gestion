
package Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandModel {
    private int numCommand;
    private float montantCom ;
    private Date dateCommand ;
    private ClientModel clientCom ;

   
      public CommandModel(int numCommand, float montantCom, Date dateCommand, ClientModel clientCom) {
        this.numCommand = numCommand;
        this.montantCom = montantCom;
        this.dateCommand = dateCommand;
        this.clientCom = clientCom;
    }

    public int getNumCommand() {
        return numCommand;
    }

    public void setNumCommand(int numCommand) {
        this.numCommand = numCommand;
    }

    public float getMontantCom() {
        return montantCom;
    }

    public void setMontantCom(float montantCom) {
        this.montantCom = montantCom;
    }

    public Date getDateCommand() {
        return dateCommand;
    }

    public void setDateCommand(Date dateCommand) {
        this.dateCommand = dateCommand;
    }

    public ClientModel getClientCom() {
        return clientCom;
    }

    public void setClientCom(ClientModel clientCom) {
        this.clientCom = clientCom;
    }
    
      
    
    public static void faitCommand (CommandModel cm) throws SQLException{
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("insert into commande  values (?,?,?,?)");
       pre.setInt(1,cm.getNumCommand());
       pre.setFloat(2,cm.getMontantCom());
       pre.setDate(3,cm.getDateCommand());
       pre.setString (4,cm.getClientCom().getIdClient());
       pre.executeUpdate();
    }

     public static void modifierCommand (CommandModel cm) throws SQLException{
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("update commande set  montant = ? , date_comm = ? where  num_comm = ? ;");
       pre.setFloat(1,cm.getMontantCom());
       pre.setDate(2,cm.getDateCommand());
       pre.setInt(4,cm.getNumCommand());
       pre.executeUpdate();
    }
     
    public static void calculeMountant (CommandModel cm ) throws SQLException{
        PreparedStatement pre = Connexion.connecter().prepareStatement("update commande set montant = (select sum(prix*quentite_art) from contient ,article  where num_comm = ? and article.code_art = contient.code_art) where num_comm =?; ");
        pre.setInt(1,cm.getNumCommand());
        pre.setInt(2,cm.getNumCommand());
        pre.executeUpdate();
    } 
     
  
    
     public static  ResultSet display_all_Command () throws SQLException{ 
       ResultSet res = Connexion.connecter().prepareStatement("select num_comm,montant,date_comm,client_comm,nom ,prenom,num_ccp from client_s,commande where client_comm = id_client; ").executeQuery();
       return res;
   }
    
}
