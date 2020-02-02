#!/usr/bin/perl
use CGI;
$query   = new CGI;
$cust_name = $query->param( "cust_name" );
$act = $query->param( "act" );
$radio = $query->param( "radio" );
$test = $query->param( "test" );

#if ( $test eq "Submit"){
#$username = $query->url_param("cust_name");
#print ( "Content-type: text/html\n\n" );
#print($username);
#}
if ( $act eq "Submit" ) {
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

if ( $radio eq "login" ) {
$cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom loginCust 'login' '$cust_name'";
   # print ( $cmd . "\n\n" );
#$cmd .= $u_name . "' '" .$log_password ."'";
    system( $cmd );
}

    elsif ( $radio eq "register" ) {
        $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom loginCust 'register' '$cust_name'";
   # print ( $cmd . "\n\n" );
#$cmd .= $u_name . "' '" .$log_password ."'";
        system( $cmd );
    }

    else {
        print( "Content-type: text/html\n\n" );
        print( "No such option: <em>$act</em>" );
    }

}
if ( $test eq "Submit"){
    $username = $query->url_param("cust_name");
    print ( "Content-type: text/html\n\n" );
    print($username);
}
