// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;


class  loginCust {
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


        String     cmd;
        Statement  stmt = conn.createStatement( );
        ResultSet  rset;

        if ( args[0].equals("login")){
        cmd = "select distinct c.cust_name from cust_table c ";
        cmd += "where c.cust_name='"+args[1].trim()+"'";
        System.out.print(cmd);
        rset = stmt.executeQuery(cmd);
        while(rset.next()){
            System.out.print("<br/><br/>Hello,"+rset.getString(1)+"! ");
            System.out.print("Welcome to Saif's Book Store 2nd version :) <br/>");
            System.out.print("<form method='post' action='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/BookStoreCust.cgi?cust_name="+rset.getString(1)+"'>");
            System.out.print("<input type='submit' name ='test' value='Submit'></form>");
        }
        rset.close();}
        else if(args[0].equals("register")){
            cmd = "insert into cust_table ";
            cmd += "values( cust_id.nextval,'"+args[1].trim()+"',order_type(order_t(null,null)))";
            System.out.print(cmd);
            System.out.print("<br/><br/>Thank you. Your registration is successful :)");
            stmt.executeUpdate(cmd);
}
        stmt.close();
        //      else{
        //  System.out.print("<br/>Register your name.");}
        conn.close( );
    }
}

