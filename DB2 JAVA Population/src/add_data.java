import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

		public class add_data
		{
		  public static void main(String[] args) 
		  {
			  
		    String urlPrefix = "jdbc:db2:";
		    String url="//localhost:50000/SAMPLE";
		    String user="db2admin";
		    String password="admin@1234";                                                         
		    Connection con;
		    Statement stmt;
		    ResultSet rs;
		 
		    
		    url = urlPrefix + url;
		    
		    try 
		    {                                                                        
		      // Load the driver
		      Class.forName("com.ibm.db2.jcc.DB2Driver");                             
		      System.out.println("**** Loaded the JDBC driver");

		      // Create the connection using the IBM Data Server Driver for JDBC and SQLJ
		      con = DriverManager.getConnection (url, user, password);     
		      
		      // Commit changes manually
		      con.setAutoCommit(false);
		      System.out.println("**** Created a JDBC connection to the data source");

		      // Create the Statement
		      stmt = con.createStatement();                                           
		      System.out.println("**** Created JDBC Statement object");

		      // Execute a query and generate a ResultSet instance
		      Random rand = new Random();
		      
// FOR INSERTING DATA IN TO CUSTOMERS TABLE
		     
		      /*
		     	for(int i=1;i<=10000;i++)
		      	{
		    	  int s = (rand.nextInt(1000)+1)*10000;
		    	  //System.out.println("insert into db2admin.customers(c_name,c_salary) values('cust"+i+"',"+s+")");
		    	  stmt.executeUpdate("insert into db2admin.customers(c_name,c_salary) values('cust"+i+"',"+s+")"); 
		    	  System.out.println(i);
		        }
		      
		     */
		      
// FOR INSERTING DATA IN TO ITEMS TABLE		      
		      
		      rs = stmt.executeQuery("select * from db2admin.customers");
		     
			
		      
		      ArrayList<Integer> ids = new ArrayList<Integer>();
		      
		      while (rs.next()) {
		        ids.add(Integer.parseInt(rs.getString(1)));
		        //System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
		      }
		      
		      
		      for(int i=4000001;i<=10000000;i++)
		      {
		    	  int c = (rand.nextInt(1000)+1)*10000;
		    	  int idi = rand.nextInt(1000);
		    	  stmt.executeUpdate("insert into db2admin.items(i_name,i_cost,c_id) values('item"+i+"',"+c+","+"(select c_id from db2admin.customers where c_id = "+ids.get(idi)+"))");
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

