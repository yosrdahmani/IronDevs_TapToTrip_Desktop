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
public interface VService <V> {
    
    public void ajouterVoyage(V v);
    
    public List<V> afficherVoyages();
    
    public void modifierVoyage(V v, int id);
    
    public void supprimerVoyage(int id) throws SQLException;
    
}
