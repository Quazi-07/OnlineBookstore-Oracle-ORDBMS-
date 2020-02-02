#!/usr/bin/perl
use CGI;
$query   = new CGI;
$act     = $query->param('act');
$radio = $query->param( "radio" );
$search = $query->param( "search" );
$cust_name = $query->param( "cust_name" );
$test = $query->param("test");

if ( $test eq "Submit"){
    $username = $query->url_param("cust_name");
    print ( "Content-type: text/html\n\n" );
    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStoreCust2 "."'".$username."'";
    print($cmd);
system($cmd);
 #print($cmd);
}

if ( $act eq "Submit" ) {
    print ( "Content-type: text/html\n\n");
# Use "here-doc" syntax.
    print <<EndofHTML;
  <html>
   <body background = "http://undcemcs02.und.edu/~mdsaifur.rahman.1/bg/bground.png">
    <table width="100%" height="80%">
     <tr>
      <td align="center">
       <font size="+0"><b>
EndofHTML

  # Compose a Java command.


if ( $radio eq "List All Books" ) {
    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStoreCust 'List All Books'";
    # print ( $cmd . "\n\n" );
    system( $cmd );
}
    elsif ( $radio eq "List All Subjects" ) {

        $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStoreCust 'List All Subjects'";

        system( $cmd );
    }
    elsif ( $radio eq "Display Customer Data" ) {
        $cmd  = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStoreCust 'Display Customer Data' ";
        $username = $query->url_param("cust_name");
        $cmd .= "'".$username."'";
        print ( $cmd . "\n\n" );
        system( $cmd );
    }

    elsif ( $radio eq "search" ) {
        $cmd  = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStoreCust 'search' '";
        $cmd .= $search . "' ";
        $username = $query->url_param("cust_name");
        $cmd .= "'".$username."'";
        print ( $cmd . "\n\n" );
        system( $cmd );
    }

    print <<EndofHTML;
        <form>
         <input type="button" value=" Back " onclick="history.go(-1);return false;" />
        </form>
       </b></font>
      </td>
     </tr>
    </table>
   </body>
  </html>
EndofHTML
}
elsif ( $act eq "HTML " ) {
    print ( "Content-type: text/plain\n\n" );
    $cmd  = "/usr/bin/lynx -dump -source " . $ENV{HTTP_REFERER};
    $cmd .= "; echo \n\n\n\n";
    system( $cmd );
}
elsif ( $act eq "CGI " ) {
  # Print plain text.
    print ( "Content-type: text/plain\n\n" );
    system( "/bin/cat BookStore.cgi; echo \n\n\n\n" );
}
elsif ( $act eq "Perl " ) {
    print ( "Content-type: text/plain\n\n" );
    system( "/bin/cat BookStore.pl; echo \n\n\n\n" );
}
elsif ( $act eq "Java " ) {
    print ( "Content-type: text/plain\n\n" );
    system( "/bin/cat BookStore.java; echo \n\n\n\n" );
}
else {
    print( "Content-type: text/html\n\n" );
    print( "No such option: <em>$act</em>" );
}
