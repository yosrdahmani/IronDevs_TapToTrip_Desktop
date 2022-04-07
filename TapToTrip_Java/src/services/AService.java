/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface AService <A> {
    
    public void ajouterAttraction(A a);
    
    public List<A> afficherAttractions();
    
    public void modifierAttraction(A a, int id);
    
    public void supprimerAttraction(int id) throws SQLException;
}
