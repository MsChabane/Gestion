/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Start;

import Models.ArticleModel;
import Models.ClientModel;
import Models.Genre;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;





public class Main {
    public static void main(String[] args) {
        try {
            ClientModel clientModel = new ClientModel("", "", "", "", new Date(2004-1900,03,01), Genre.FEMALE);
            ClientModel.generatIdClient(clientModel);
            System.out.println(clientModel.getIdClient());
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
