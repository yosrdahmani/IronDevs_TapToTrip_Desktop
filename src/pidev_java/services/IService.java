/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;


import java.util.List;

/**
 *
 * @author DELL
 */
public interface IService <U> {
    
    public boolean ajouteruser(U u);
    public List<U> afficheruser();
    public boolean  modifieruser(U u);
    public void Desactiveruser(int id );
    
}
