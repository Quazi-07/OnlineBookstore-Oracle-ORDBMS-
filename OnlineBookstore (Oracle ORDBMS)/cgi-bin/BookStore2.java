// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import java.lang.Object;
import java.lang.StringBuilder;


class  BookStore2 {
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

        if ( args[0].equals( "insert" ) ) {

            for ( int i=4;  i < args.length;  i++ ){

                cmdS = "insert /*+ ignore_row_on_dupkey_index(subject_table2(subject_name)) */ into subject_table2 ";
                cmdS += "values(subject_id.NEXTVAL, '" + args[i].trim( ) +"')";
                System.out.println( cmdS );
                stmt.execute( cmdS );
            }

            cmd  = "insert /*+ ignore_row_on_dupkey_index(book_table(title)) */ into book_table ";
            cmd += "values ('" + args[1].trim( );
            cmd += "','"+args[2].trim( );
            cmd += "', " + args[3].trim( );
            cmd += " , subject_type(";
            //System.out.println( cmd );
            //stmt.execute( cmd );


            //cmdBS = "select s.subject_id,regexp_replace( s.subject_id,',[^[:digit:]]*$') from subject_table2 s where (" ;
            cmdBS = "select s.subject_id from subject_table2 s where (" ;
             if ( args.length == 0 ) { cmd += "subject_name=' ')";}
             else {
             for ( int i=4; i < args.length; i++){

                        if (i == 4 ){ cmdBS += " s.subject_name ='"+ args[i].trim( );}

                        else { cmdBS += "' or  s.subject_name ='"+ args[i].trim( );}}//}

            cmdBS += "')";
             System.out.println( cmdBS );
             rset = stmt1.executeQuery(cmdBS);
             int s = 0;
             // StringBuilder strItems = new StringBuilder();
            while(rset.next()){//{
                if (s==0){
                    cmd += "subject_t2(" + rset.getString(1)+")";
                  s+=1;
                }
                else
                  {
                      cmd += ",subject_t2(" + rset.getString(1)+")";
                }

            }

             rset.close();
            cmd += ")) ";
            System.out.println( cmd );
            stmt.execute( cmd );


             }}
        else if ( args[0].equals( "List All Data" ) ) {
            cmd  = "select distinct b.title from book_table b ";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){

                System.out.print(" <input type='radio'/><font color='0B6A34'>"+"Books Title: </font>"+ "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlink2.cgi?title=" );
                System.out.print( rset.getString(1)+"'>"+rset.getString(1) +"</a><br/><br/>" );
            }
            rset.close( );

            cmd  = "select distinct s.subject_name from subject_table2 s ";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){

                System.out.print(" <input type='radio'/><font color='0B6A34'>"+"Subject Name: </font>"+ "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlinkSub2.cgi?subject_name=" );
                System.out.print( rset.getString(1)+"'>"+rset.getString(1)+"</a> <br/><br/>" );
            }
            rset.close( );

            cmd  = "select distinct c.cust_name from cust_table c ";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){

                System.out.print(" <input type='radio'/><font color='0B6A34'>"+"Customer Name: </font>"+ "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlinkCust.cgi?cust_name=" );
                System.out.print( rset.getString(1)+"'>"+rset.getString(1)+"</a> <br/><br/>" );
            }
            rset.close( );
}

        else if ( args[0].equals( "List All Books" ) ) {
            cmd  = "select distinct b.title from book_table b ";
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
            System.out.print( "<input type='submit' name='act' value='search' /> </form><br /><br/>");
            rset.close( );
           }

            else if ( args[0].equals("Master Clear"))  {

                 cmd  = "delete from book_table bt";
                 System.out.println( cmd );
                 stmt.execute( cmd );


                cmdS = "delete from subject_table2 st";
                System.out.println( cmdS );
                stmt.execute( cmdS );


                cmdBS = "delete from cust_table ct";
                System.out.println( cmdBS );
                stmt.execute( cmdBS );
            }

        else if ( args[0].equals( "Display Customer Data" ) ) {
            cmd  = "select distinct c.cust_name from cust_table c";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){
                System.out.print("<input type='checkbox' /><font color='0B6A34'>"+"Customer Name: "+"</font>" + "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlinkCust.cgi?cust_name=" );
                System.out.print( rset.getString(1)+"'>" + rset.getString(1)+"</a>" +"<br /><br/>" );
            }
            // Close the ResultSet and Statement.
            System.out.print( "<input type='submit' name='act' value='search' /> </form><br /><br/>");
            rset.close( );
        }

        stmt.close( );

        // Close the Connection.
        conn.close( );
    }
}
