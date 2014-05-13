
package dynamicinsert.db;

import dynamicinsert.DynamicInsert;
import static dynamicinsert.db.Consultas.isValidDateStr;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 *
 * @author faka
 */
public class Consultas {
    
    public void Insert(String table,ArrayList x1, ArrayList x2) throws SQLException{
        
      if(DynamicInsert.conexion!=null){
            DynamicInsert.stmt = DynamicInsert.conexion.prepareStatement(this.GenStatement(table, x1,x2));
            for(int i = 0; i<x2.size();i++)
            {
            DynamicInsert.stmt.setString(i+1, x2.get(i).toString());
            }
            DynamicInsert.stmt.executeUpdate();
            DynamicInsert.stmt.close();
      }
      else{
          JOptionPane.showMessageDialog(null, "ERROR EN LA DB");
      }
    } 

public String GenStatement(String t, ArrayList x1, ArrayList x2)
{
    String sql = "insert into "+t+" (";
    for(int i = 0 ; i<x1.size() ; i++ )
    {
            sql = sql + x1.get(i).toString() + ",";
    }
    sql = sql.substring(0, sql.length()-1);
    sql = sql + ") values (";
    for(int i = 0 ; i<x1.size() ; i++ )
    {
        if(isValidDateStr(x2.get(i).toString(),"yyyy-MM-dd"))
            sql = sql + "str_to_date(?" + ",'%Y-%m-%d'),";
        else
            sql = sql + "?,";
    }
    sql = sql.substring(0, sql.length()-1);
    sql = sql + ")";
    return sql;
}
        
public static boolean isValidDateStr(String date, String format) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      sdf.setLenient(false);
      sdf.parse(date);
    }
    catch (ParseException | IllegalArgumentException e) {
      return false;
    }
    return true;
    }

}



/*
 *         if(isValidDateStr(x1.get(i).toString(),"yyyy-MM-dd"))
            sql = sql + "str_to_date(" + x1.get(i).toString() + ",%Y/%m/%d),";
 * 
 */