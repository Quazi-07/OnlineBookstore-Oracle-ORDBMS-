#!/usr/bin/perl
use CGI;
$query   = new CGI;
$subject_name = $query->param( "subject_name" );
$cust_name = $query->param( "cust_name" );
$title = $query->param( "title" );
$quantity = $query->param( "quantity" );
$act = $query->param( "act" );

if ( $act eq "purchase" ) {
print ( "Content-type: text/html\n\n" );

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
$username = $query->url_param("cust_name");
  $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom purchase '$username' ";

  my @titles = $query->param( 'title' );
      foreach my $title ( @titles ) {
        $cmd .= "'" . $title . "' ";
      }

my @quantity = $query->param( 'quantity' );
foreach my $quantity ( @quantity ) {
    $cmd .=$quantity." ";
}

   # print ( $cmd . "\n\n" );
#$cmd .="'".$quantity."'";
#$username = $query->url_param("cust_name");

print $cmd;

system( $cmd );



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
