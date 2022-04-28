/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Entities.Categorie;
import Entities.CompteBancaire;
import Entities.Transport;
import Services.BilletService;
import Services.CategorieService;
import Services.CompteBancaireService;
import Services.TransportService;
import util.MyDB;

/**
 *
 * @author lenovo
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Transport t1 = new Transport("test","test1","test2",20,19,1,20);
        
        TransportService ts = new TransportService();
//                         
//        System.out.println(ps.afficher());
//        
//        Categorie c = new Categorie(19,"dog","dog");
//        
        CategorieService cs = new CategorieService();
        
//        cs.modifier(c);

            //cs.chercherNomCategorie("yosr");
            //System.out.println(cs.chercherNomCategorie("Automatique"));
            
            //System.out.println(ts.afficher());
            
            //BilletService bs = new BilletService ();
            
            //System.out.println(bs.getDateBilletByUser(2));
            
//            CompteBancaireService cbs = new CompteBancaireService();
//            
//            System.out.println(cbs.getSoldeByUser(2));

            System.out.println(ts.getPrixById(70));
            
            CompteBancaireService cbs = new CompteBancaireService();
            
            CompteBancaire cb = new CompteBancaire();
            
            cb = new CompteBancaire(1,1400);
            
            cbs.modifier(cb);
            
    }
}
