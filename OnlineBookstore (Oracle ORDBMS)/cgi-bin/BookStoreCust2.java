public class BookStoreCust2{
    public static void main(String args[] ){
        System.out.print("Content-type: text/html\n\n" );
        System.out.print("<html>"+
"<center>"+
"<form method=\"post\" action=\"http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/BookStoreCust.cgi?cust_name="+args[0]+"\" target=\"view\">"+
 "<table width=\"90%\" bgcolor=\"058989\" cellspacing=\"3\" cellpadding=\"10\" border=\"0\" class=\"shadow\">"+
  "<tr bgcolor=\"EDFCFC\">"+
   "<td><font size=\"+1\">"+
   "<center><br />"+
    "<font face=\"Verdana, Arial, Helvetica\" size=\"-1\">"+
     "<h1>Saif's Bookstore2!</h1>"+

" <br /> <br />"+

 "<input type=\"radio\"  name=\"radio\"    value=\"List All Books\" checked=\"checked\"/> List All Books"+
 "<input type=\"radio\"   name=\"radio\"    value=\"List All Subjects\" /> List All Subjects"+
"<input type=\"radio\"   name=\"radio\"    value=\"Display Customer Data\" /> Display Customer Data <br/>"+
  "<input type=\"radio\"   name=\"radio\"    value=\"search\" /> Search <br />"+
  "Books Title:<input type=\"text\" name=\"search\" placeholder =\"Search here..\" size=\"24\" /> <br /> <br />"+
  "<input type=\"submit\" name=\"act\"   value=\"Submit\" />"+
  "<input type=\"submit\"   name=\"act\"    value=\"HTML source\" />"+
  "<input type=\"submit\"   name=\"act\"    value=\"CGI source\" />"+
  "<input type=\"submit\"   name=\"act\"    value=\"Perl source\" />"+
  "<input type=\"submit\"   name=\"act\"    value=\"Java source\" />"+
  "<input type=\"reset\"                  value=\"Reset\" />"+
   "</center></font>"+
   "</td>"+
  "</tr>"+
  "<tr bgcolor=\"EDFCFC\">"+
   "<td>"+
    "<iframe frameborder=\"0\" width=\"100%\" height=\"700\" src=\"http://undcemcs02.und.edu/~mdsaifur.rahman.1/bg/bground.png\" name=\"view\">"+
      "<div  name =\"view\">"+
    "<iframe frameborder=\"0\" width=\"100%\" height=\"300\"  name=\"view\">"+
    "</iframe>"+
    "</div>"+
    "</iframe>"+
   "</td>"+
  "</tr>"+
 "</table><br />"+
"</form>"+
"</center>"+
"</html>"
 );
}

}
