import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

		public class oracle_data_population
		{
		  public static void main(String[] args) 
		  {
			  
		                                                      
		    Connection con;
		    Statement stmt;
		    ResultSet rs;
		   try 
		    {                                                                        
		      // Load the driver
		      Class.forName("oracle.jdbc.driver.OracleDriver");                             
		      System.out.println("**** Loaded the JDBC driver");

		      // Create the connection using the IBM Data Server Driver for JDBC and SQLJ
		      con = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:xe","system","admin@1234");     
		      
		      // Commit changes manually
		      con.setAutoCommit(false);
		      System.out.println("**** Created a JDBC connection to the data source");

		      // Create the Statement
		      stmt = con.createStatement();                                           
		      System.out.println("**** Created JDBC Statement object");

		      // Execute a query and generate a ResultSet instance
		      Random rand = new Random();
		      
		      // FOR INSERTING DATA IN TO CUSTOMERS TABLE
		     
		      
		     /*	for(int i=1;i<=10000;i++)
		      	{
		    	  int s = (rand.nextInt(1000)+1)*10000;
		    	  //System.out.println("insert into db2admin.customers(c_name,c_salary) values('cust"+i+"',"+s+")");
		    	  stmt.executeUpdate("insert into customers(c_id,c_name,c_salary) values(" + i + ",'cust" + i + "',"+s+")"); 
		    	  System.out.println(i);
		        }*/
		      
		     
		      
// FOR INSERTING DATA IN TO ITEMS TABLE		      
		      
		      rs = stmt.executeQuery("select * from customers");
		     
			
		      
		      ArrayList<Integer> ids = new ArrayList<Integer>();
		      
		      while (rs.next()) {
		        ids.add(Integer.parseInt(rs.getString(1)));
		        //System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
		      }
		      
		      
		      for(int i=1;i<=50000000;i++)
		      {
		    	  int c = (rand.nextInt(1000)+1)*10000;
		    	  int idi = rand.nextInt(1000);
		    	  stmt.executeUpdate("insert into items(i_id,i_name,i_cost,c_id) values("+i+",'item"+i+"',"+c+","+"(select c_id from customers where c_id = "+ids.get(idi)+"))");
		    	  
		    	  System.out.println(i);
		    	  if(i%100000==0)
		    	  {
				      con.commit();
		    	  }
		      }
		      
		      stmt.close();
		      con.commit();
		      con.close(); 
		    }
		    
		    
		    catch (ClassNotFoundException e)
		    {
		      System.err.println("Could not load JDBC driver");
		      System.out.println("Exception: " + e);
		      e.printStackTrace();
		    }

		    catch(SQLException ex)                                                    
		    {
		    	System.err.println("SQLException information");
		    	
		    	while(ex!=null) {
		        System.err.println ("Error msg: " + ex.getMessage());
		        System.err.println ("SQLSTATE: " + ex.getSQLState());
		        System.err.println ("Error code: " + ex.getErrorCode());
		        ex.printStackTrace();
		        ex = ex.getNextException(); 
		      }
		    }
		  }  
 }    

