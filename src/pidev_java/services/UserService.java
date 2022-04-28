/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import pidev_java.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pidev_java.entities.role;
import utils.MyDB;

/**
 *
 * @author hp
 */
public class UserService implements IService<User> {

    Connection con;
    Statement stm;

    public UserService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public boolean ajouteruser(User u) {
        boolean done = false;

        String testS = "SELECT * FROM user WHERE email=" + "\"" + u.getEmail() + "\"";
        try {
            Statement stS = con.createStatement();
            ResultSet rstS = stS.executeQuery(testS);
            if (rstS.next()) {
                System.out.println("Utilisateur existe deja");
                done = false;
            } else {
                String req = "INSERT INTO `user`(`email`, `nom`, `prenom`, `numtel`, `password`, `confirm_password`,`roles`) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pstm = con.prepareStatement(req);
                pstm.setString(1, u.getEmail());
                pstm.setString(2, u.getNom());
                pstm.setString(3, u.getPrenom());
                pstm.setString(4, u.getNumtel());
                pstm.setString(5, u.getPassword());
                pstm.setString(6, u.getConfirm_password());
                pstm.setString(7, role.CLIENT.name());
                pstm.executeUpdate();
                System.out.println("User ajouté avec succes !");
                done = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return done;
    }

    @Override
    public List<User> afficheruser() {

        List<User> Users = new ArrayList();

        try {
            Statement stm = con.createStatement();
            String querry = "SELECT * FROM user";

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setNumtel(rs.getString("numtel"));
                u.setPassword(rs.getString("password"));
                u.setConfirm_password((rs.getString("confirm_password")));
                u.setRole(role.valueOf(rs.getString("roles")));
                u.setState(rs.getInt("state"));

                Users.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Users;

    }
    
    
    public List<User> SearchUserByName(String nom) {

        List<User> Users = new ArrayList();

        try {
            Statement stm = con.createStatement();
            String querry = "SELECT * FROM user WHERE nom LIKE '%"+nom+"%'";

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setNumtel(rs.getString("numtel"));
                u.setPassword(rs.getString("password"));
                u.setConfirm_password((rs.getString("confirm_password")));
                u.setRole(role.valueOf(rs.getString("roles")));
                u.setState(rs.getInt("state"));

                Users.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return Users;

    }

    @Override
    public boolean modifieruser(User u) {
        boolean done = false;
        try {
            String req = "UPDATE `user` SET "
                    + " `state`=" + u.getState()+ ","
                    + "`email`='" + u.getEmail() + "',"
                    + "`nom`='" + u.getNom() + "',"
                    + "`prenom`='" + u.getPrenom() + "',"
                    + "`numtel`='" + u.getNumtel() + "',"
                    + "`roles`='" + u.getRole() + "',"
                    + "`password`='" + u.getPassword() + "',"
                    + "`confirm_password`='" + u.getConfirm_password() + "'"
                    + " WHERE id=" + u.getId();
            System.out.println(req);
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("User modifié avec succes !");
            done= true;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return done ;
    }

    @Override
    public void Desactiveruser(int id) {
        try {
            String req = "update user SET state = 1 where id=" + id;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("user locked !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void Activeruser(int id) {
        try {
            String req = "update user SET state = 0 where id=" + id;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("user unlocked !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public User getUser(String email) {
        User u = new User();

        String req = "SELECT * FROM user WHERE email=" + "\"" + email + "\"";

        try {

            if (req != null) {
                Statement st = con.createStatement();
                ResultSet rst = st.executeQuery(req);
                if (rst.next()) {

                    u.setId(rst.getInt("id"));
                    u.setNom(rst.getString("nom"));
                    u.setPrenom(rst.getString("prenom"));
                    u.setEmail(rst.getString("email"));
                    u.setNumtel(rst.getString("numtel"));
                    u.setPassword(rst.getString("password"));
                    u.setConfirm_password((rst.getString("confirm_password")));
                    u.setRole(role.valueOf(rst.getString("roles")));
                    u.setState(rst.getInt("state"));

                } else {
                    System.out.println("Utilisateur n'existe pas");
                }

            }

        } catch (SQLException ex) {
            System.out.println("Connexion à la base de données impossible , " + ex.getMessage());
        }
        System.out.println(u);
        return (u);
    }

}
