package classicmodels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ClassicModels {

    public static void main(String[] args)  {
        
        //Create Try/Catch
        
        try {
            //Create connection to Database
             Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");
            
             //Create a new SQL statement using the connection
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT productName FROM products WHERE productLine = 'Classic Cars';");
        
                while (rs.next()){
            System.out.println(rs.getString("ProductName"));
                    
                }
                
              
                System.out.println("Enter 1 to see the inventory of classic cars, 2 to see ships or 3 for motorcycles");
                
                Scanner scanner = new Scanner(System.in);
                
                String userInput = scanner.next();
                
                String sql = "";
                
                 switch(userInput){
                case "1":
                    sql = "SELECT * FROM Products WHERE productLine = 'Classic Cars'";
                    break;
                case "2":
                    sql = "SELECT * FROM Products WHERE productLine = 'Ships'";
                    break;
                case "3":
                    sql = "SELECT * FROM Products WHERE productLine = 'Motorcycles'";
                    break;
                default:
                    System.out.println("You did not enter a valid choice for the product line.");
                    break;
            }//end switch
                 
                 rs = stmt.executeQuery(sql);
                 
                  while (rs.next()){
            System.out.println(rs.getString("productName") + " " + ("ProductLine"));
                  }
                  
                  stmt.executeUpdate("INSERT INTO PRODUCTS \n" +  "(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP)\n" +
        "VALUES \n" +
        "(\"S72_3213\",\"USS Monitor\", \"Ships\", \"1:72\", \"Second Gear Diecast\", \"Ironclad warship with her steam engine and revolving turret\", 780, 35.00, 55.00);\n");
                  
                   rs = stmt.executeQuery("SELECT * FROM products WHERE productCode = 'S72_3213'");
                   while(rs.next() ) {
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " "  + rs.getString(1));
                   }            
                stmt.executeUpdate("UPDATE products SET productScale ='1:10' WHERE productCode = 'S72_3213'");
                
                stmt.executeUpdate("DELETE FROM products WHERE products = 'S72_3212'");
               
                rs = stmt.executeQuery("SELECT * FROM products WHERE productCode = 'S72_3213'");
                 while(rs.next() ) {
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " "  + (1));
                 }
           
        }    
         catch (Exception e){
         System.out.println(e);
         }
    }
}
        //
    
    

