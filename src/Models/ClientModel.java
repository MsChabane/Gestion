
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;


public class ClientModel {
    private String idClient ,nom , prenom,email,numCCP;
    private Date dateInscription ;
    private Genre g;

    public ClientModel( String nom, String prenom, String email, String numCCP, Date dateInscription, Genre g) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numCCP = numCCP;
        this.dateInscription = dateInscription;
        this.g = g;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumCCP() {
        return numCCP;
    }

    public void setNumCCP(String numCCP) {
        this.numCCP = numCCP;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Genre getG() {
        return g;
    }

    public void setG(Genre g) {
        this.g = g;
    }
    
    // date d'inscription + 1 car genre + 3 car ordre --> eg : 01042024F009  14102024M001
    private static  void generatIdClient (ClientModel cl) throws SQLException{
        
        SimpleDateFormat f = new SimpleDateFormat("ddMMyyyy");
        Connection con = Connexion.connecter();
        String id ;
        /*
         cl.getDateInscription() = f.format (dateInscription);
         if (cl.g.name().compareTo("FEMALE")== 0)
         {
            cl.setIdClient(idClient.concat("F"));
         }
         else 
         { 
             idClient = idClient.concat("M");
         }
         if (f.format (dateInscription).compareTo(f.format(now))==0)
         {
            Statement stm =con.createStatement();
             ResultSet rs = stm.executeQuery("select Count(date_insc) from client_s where dateInscription = ?;");
                  if (){
                                    
                                }
         }
               
           idClient = idClient.concat(String.format("%03d",0)); */
      
        id = f.format(cl.getDateInscription())+ cl.getG().name().charAt(0);
        PreparedStatement pre =con.PreparedStatement("select Count(date_insc) from client_s where dateInscription = ?;");
        pre.setDate(1,cl.dateInscription);
        ResultSet rs = pre.executeQuery();
      
      
      
      
    }

    @Override
    public String toString() {
        return "ClientModel{" + "idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", numCCP=" + numCCP + ", dateInscription=" + dateInscription + ", g=" + g + '}';
    }
    
    
    
    public static void ajouterClient (ClientModel clM ) throws SQLException {
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("insert into client_s  values (?,?,?,?,?,?,?)");
      // pre.setString(1,clM.generatIdClient(0));
       pre.setString(2,clM.getNom());
       pre.setString(3,clM.getPrenom());
       pre.setString(4,clM.getEmail());
       pre.setString(5,clM.getNumCCP());
       pre.setDate(5,new Date (System.currentTimeMillis()));
       if (clM.getG().name().compareTo("MALE")>=0) {
           pre.setString (5,Genre.MALE.name());
        } else {
           pre.setString (5,Genre.FEMALE.name());
        }
        pre.executeUpdate();
    }
    
    public static void modifierClient (ClientModel newCl) throws SQLException{
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("update article set nom = ? , prenom = ? , email= ? ,numCCP = ? ,dateInscription = ? ,g = ? where  idClient = ? ;");
       pre.setString(1,newCl.getNom());
       pre.setString(2,newCl.getPrenom());
       pre.setString(3,newCl.getEmail());
       pre.setString(4,newCl.getNumCCP());
       pre.setDate(5,newCl.getDateInscription());
       pre.setString(6,newCl.getG().name());
       pre.executeUpdate();    
    }
    
    public static void supprimerClient () throws SQLException{
       Connection con = Connexion.connecter();
       PreparedStatement pre =con.prepareStatement("");
       
    } 
    
    public static void rechercheClient (){
        
    }
}
