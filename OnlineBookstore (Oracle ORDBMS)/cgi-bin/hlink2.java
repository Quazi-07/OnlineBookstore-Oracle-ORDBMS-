// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;


class  hlink2 {
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


        cmd  = "select bt.ISBN,bt.title,bt.price,s.subject_name  from book_table bt, table(bt.subject) ns, subject_table2 s ";
        cmd += "where ns.subject_id = s.subject_id and bt.title= '"+args[0].trim( )+"'";
        System.out.println( cmd+"<br/><br/>" );
        rset = stmt.executeQuery( cmd );
        while ( rset.next( ) ){

            System.out.print( " <font color='0B6A34'>ISBN: </font>"+ rset.getString(1)+"<br/> <font color='0B6A34'>Title: </font>"+rset.getString(2)+"<br/> <font color='0B6A34'>Price: </font>"+rset.getString(3)+"<br><font color='0B6A34'\
>Subject Name: </font>"+"<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlinkSub2.cgi?subject_name=" );
            System.out.print( rset.getString(4)+" '>" + rset.getString(4)+"</a>" +"<br /><br/>" );
            //System.out.print(rset.getString(4)+" '>"+rset.getString(4)+"</a>"+"<br/><br/>" );

        }
        rset.close( );


        stmt.close( );

        // Close the Connection.
        conn.close( );
    }
}
