/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taptotrip_java;

import entities.Attraction;
import entities.Voyage;
import java.sql.SQLException;
import services.AttractionService;
import services.VoyageService;

/**
 *
 * @author DELL
 */
public class TapToTrip_Java {

        
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        
        Attraction A1 = new Attraction("Algerie","France","Attraction12 Paris");
        AttractionService as = new AttractionService();
        
        //Ajouter Attraction
        //as.ajouterAttraction(A1);
        
        //Afficher Liste des Attractions
        //System.out.println(as.afficherAttractions());
        
        //Modifier Attraction
        //as.modifierAttraction(A1,12);
        
        //Supprimer Attraction
        //as.supprimerAttraction(12);
        
        Voyage V1 = new Voyage("Maldives","3Jours","Programme de voyage ...","","hotel 3*","9000 dt",6);
        VoyageService vs = new VoyageService();
        
        
        //Ajouter Voyage organisé
        //vs.ajouterVoyage(V1);
        
        //Afficher Liste des Voyages
        System.out.println(vs.afficherVoyages());
        
        //Modifier Attraction
        //vs.modifierVoyage(V1,7);
        
        //Supprimer Attraction
        //vs.supprimerVoyage(8);
        
    }
        
    
    
}
