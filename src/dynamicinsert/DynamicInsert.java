
package dynamicinsert;

import dynamicinsert.db.ConexionDB;
import dynamicinsert.db.Consultas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author faka
 */
public class DynamicInsert {

    public static Connection conexion;
    public static PreparedStatement stmt;
    ArrayList<String> x1;
    ArrayList<String> x2;
    Consultas s;
    public static void main(String[] args) {
        conexion= ConexionDB.GetConnection();
        
        if(conexion==null)
        {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos!");
        }
        DynamicInsert s = new DynamicInsert();
        
    }

    DynamicInsert()
    {
            s = new Consultas();
            String[] cn = {"Nombre","Ciudad","Eda","Fecha"};
            x1 = new ArrayList<>();
            x1.addAll(Arrays.asList(cn));
            x2 = new ArrayList<>();
            for(int z = 0;z<x1.size();z++)
            {
                       try{
                            Properties p = new Properties();
                            p.load(new FileInputStream("/home/faka/NetBeansProjects/DynamicInsert/src/dynamicinsert/user.ini"));
                                                       
                            x2.add(z,p.getProperty(x1.get(z)));
                            
                          }
                       catch (Exception e) {
                            System.out.println(e);
                            }
            }//System.out.println(s.GenStatement("prueba", x1));
          try {
            
            s.Insert("dyn", x1, x2);
        } catch (SQLException ex) {
            Logger.getLogger(DynamicInsert.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
