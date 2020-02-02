// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;


class  hlinkCust {
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


        String     cmd,cmdT;
        Statement  stmt = conn.createStatement( );
        Statement  stmt2 = conn.createStatement( );
        ResultSet  rset,rset2;


        cmd  = "select c.cust_id,c.cust_name,(select s.title from table(c.cust_order) s) as purchased_book,(select s.quantity from table(c.cust_order) s) as book_quantity from cust_table c";
        cmd += " where c.cust_name = '"+args[0].trim( )+"'";
        System.out.println( cmd+"<br/><br/>" );
        rset = stmt.executeQuery( cmd );
        while ( rset.next( ) ){

            System.out.print( " <font color='0B6A34'> Customer ID: </font>"+ rset.getString(1)+"<br/> <font color='0B6A34'>Customer Name: </font>"+rset.getString(2)+"<br/> <font color='0B6A34'>Purchased Book: </font>"+"<a href='http://u\
ndcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlink2.cgi?title=" );
            System.out.print( rset.getString(3)+"'>"+rset.getString(3) +"</a><br/>" );
            System.out.print("<font color='0B6A34'> Book Quantity: </font>"+rset.getString(4) +"</a><br/>" );
            //System.out.print( rset.getString(3)+"'>"+rset.getString(3) +"</a><br/>";

            //}
        // rset.close( );


        //stmt.close( );

        //      cmdT = "select (b.price*(select nt.quantity from table(c.cust_order) nt where nt.title='Python: Programming in Context')) as total_amount from cust_table c, book_table b";
        //      cmdT += " where b.title='Python: Programming in Context' and c.cust_name='"+args[0].trim()+"'" ;

        cmdT = "select (b.price*(select nt.quantity from table(c.cust_order) nt where nt.title='"+rset.getString(3)+"')) as total_amount from cust_table c, book_table b";
        cmdT += " where b.title='"+rset.getString(3)+"' and c.cust_name='"+args[0].trim()+"'" ;

        //cmdT += ar
        //cmdTotal  = "select c.cust_id,c.cust_name,(select s.title from table(c.cust_order) s) as purchased_book,(select s.quantity from table(c.cust_order) s) as book_quantity from cust_table c";
        //cmdTotal += " where c.cust_name = '"+args[0].trim( )+"'";
        //System.out.println( cmdT+"<br/><br/>" );
        rset2 = stmt2.executeQuery( cmdT );
        while ( rset2.next( ) ){
            System.out.print("<font color='0B6A34'> Total amount: </font>"+rset2.getString(1) +"</a><br/><br/>" );

        }
        rset2.close( );
        stmt2.close();

        }

        // Close the Connection.
        conn.close( );
    }
}
