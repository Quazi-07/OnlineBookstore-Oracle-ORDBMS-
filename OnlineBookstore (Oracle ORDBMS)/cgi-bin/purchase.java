// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;


class  purchase {
    public static void  main( String args[ ] ) throws SQLException {
        String user     = "C##mdsaifur.rahman.1";
        String password = "rahman9451";
        String database = "65.52.222.73:1521/cdb1";

        // Open an OracleDataSource and get a connection.
        OracleDataSource ods = new OracleDataSource( );
        ods.setURL     ( "jdbc:oracle:thin:@" + database );
        ods.setUser    ( user );
        ods.setPassword( password );
        Connection conn = ods.getConnection( );


        String     cmd="";
        Statement  stmt = conn.createStatement( );
        ResultSet  rset;



                                        cmd = "update table(select c.cust_order from cust_table c where c.cust_name = '"+args[0].trim()+"') nt set nt.title = '"+args[1]+"', nt.quantity ="+args[2];
        //for ( int i=1;  i < args.length;  i++ ){
        //cmd = "update table(select c.cust_order from cust_table c where c.cust_name = '"+args[0].trim()+"') nt ";
        // for ( int i=1;  i < args.length;  i++ ){
        //            cmd +="set nt.title='"+args[i]+"',nt.quantity="+args[i+2]+")<br/>";
        // }
         // System.out.println( cmd+"<br/>" );
         //stmt.execute( cmd );
        //      }

        //for ( int i=1;  i < args.length;  i++ ){
        //              cmd = "insert /*+ ignore_row_on_dupkey_index(cust_table(cust_name)) */  into cust_table ";
        //cmd += "values((select c.cust_id from cust_table c where c.cust_name='"+args[0]+"'),'"+args[0].trim()+"',order_type(";
        //      for ( int i=1;  i < args.length;  i++ ){
        //  cmd +="order_t('"+args[i]+"',"+args[i+2]+")))";
        //      for ( int i=1;  i < args.length;  i++ ){
        //cmd = "insert into table(select c.cust_order from cust_table c where c.cust_name = '"+args[0].trim()+"') ";
        //cmd += "values(";
        //cmd +="'"+args[i]+"',"+args[i+2]+")";

          System.out.println( cmd+"<br/><br/>" );
          stmt.execute( cmd );

          //        }
            //}

        stmt.close( );

        // Close the Connection.
        conn.close( );
    }
    
}

