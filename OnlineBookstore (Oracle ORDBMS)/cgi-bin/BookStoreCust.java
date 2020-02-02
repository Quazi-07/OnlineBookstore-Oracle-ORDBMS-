// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import java.lang.Object;
import java.lang.StringBuilder;


class  BookStoreCust {
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


        String     cmd, cmdS, cmdBS, cmdBS1, cmdNT;
        Statement  stmt1 = conn.createStatement( );

        Statement  stmt = conn.createStatement( );
        ResultSet  rset,rset2;
        //ResultSet rset1;


        if ( args[0].equals( "List All Books" ) ) {
            cmd  = "select distinct b.title from book_table b ";
            //cmd  = "where  ";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){

                System.out.print(" <input type='radio' name='title' value='"+rset.getString(1)+"'/><font color='0B6A34'>"+"Books Title:"+ "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlink2.cgi?title=" );
                System.out.print( rset.getString(1)+"'>"+rset.getString(1) +"</a> </font><br/><br/>" );
                //System.out.print( "<br/> <font color='#3366CC'>"+"Price: $</font>"+rset.getString(2)+ "</form><br /><br/>" );
            }
            rset.close( );}
        else if ( args[0].equals( "List All Subjects" ) ) {
            cmd  = "select distinct s.subject_name from subject_table2 s";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){
                System.out.print("<input type='checkbox'/><font color='0B6A34'>"+"Subject Name: "+"</font>" + "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlinkSub2.cgi?subject_name=" );
                System.out.print( rset.getString(1)+" '>" + rset.getString(1)+"</a>" +"<br /><br/>" );
            }
            // Close the ResultSet and Statement.
            //System.out.print( "<input type='submit' name='act' value='search' /> </form><br /><br/>");
            rset.close( );
        }

        else if ( args[0].equals( "Display Customer Data" ) ) {
            cmd  = "select distinct c.cust_name from cust_table c ";
            cmd  += "where c.cust_name='"+args[1]+"'";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){
                System.out.print("<input type='checkbox' /><font color='0B6A34'>"+"Customer Name: "+"</font>" + "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlinkCust.cgi?cust_name=" );
                System.out.print( rset.getString(1)+"'>" + rset.getString(1)+"</a>" +"<br /><br/>" );
            }
            // Close the ResultSet and Statement.
            //System.out.print( "<input type='submit' name='act' value='search' /> </form><br /><br/>");
            rset.close( );
        }
        else if ( args[0].equals("search"))  {
            //cmd  = "select b.title from book_table b where ";
            //cmd += "regexp_like(upper(b.title),upper('"+args[1].trim( )+"'))";
            if (args[1].equals("")){
                cmd  = "select b.title from book_table b,subject_table2 s,table(b.subject) nt where ";
                cmd += "nt.subject_id=s.subject_id group by b.title order by count(nt.subject_id) desc";
}
            else {
            cmd  = "select b.title from book_table b,subject_table2 s,table(b.subject) nt where ";
            cmd += "nt.subject_id=s.subject_id and regexp_like(upper(s.subject_name),upper('"+args[1].trim( )+"'))group by b.title order by count(nt.subject_id) desc";
            }
 System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){
                // System.out.print( "<form method ='post' action='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/purchase.cgi'/><input type='checkbox'   name='title' value ='"+rset.getString(1)+"' /><font color='0B6A34'>"+"Book T\
itle: </font>"+"<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlink2.cgi?title=");
                //              System.out.print( rset.getString(1)+" '>" + rset.getString(1)+"</a><br/>" );
                System.out.print("<form method='post' action='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/purchase.cgi?cust_name="+args[2].trim()+"'>"+"<input type='checkbox'   name='title' value ='"+rset.getString(1)+"'/><font\
 color='0B6A34'>"+"Book Title: "+"</font>" + "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlink2.cgi?title=" );
                System.out.print( rset.getString(1)+"'>"+rset.getString(1)+"</a>" +"<br/>" );
                System.out.print("<font color='#3366CC'>"+"Book Quantity: </font>"+"<input type='text' name='quantity'><br/>");
                // + rset.getString(1)+ "</font>" );
            }
            System.out.print( "<br/><br/><input type='submit' name='act' value='purchase' /> </form><br /><br/>");
            // Close the ResultSet and Statement.
            rset.close( );}



        stmt.close( );

        // Close the Connection.
        conn.close( );
    }
}
