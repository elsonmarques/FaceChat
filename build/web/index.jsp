<%-- 
    Document   : index
    Created on : 14/01/2013, 21:55:58
    Author     : elsonms
--%>
<%@page import="java.io.IOException"%>
<%@page import="org.jivesoftware.smack.XMPPException"%>
<%@page import="pacote.FBConsoleChatApp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%!
 public String run() {
      String retorno = "";
      String username = "eduardo.pacheco.587";
      String password = "par0la";
      FBConsoleChatApp app = new FBConsoleChatApp();
      try {
         app.connect();
         if (!app.login(username, password)) {
            //System.out.println("Access Denied...");
            System.exit(-2);
            retorno = "Não Conectou";
         }
         retorno = "Conectou ";
        
         app.showMenu();
         app.getFriends();
         String data = null;
         /*
         menu:
         while(true) {
       //     if (!Character.isDigit(data.charAt(0))) {
               //System.out.println("Invalid input.Only 1-3 is allowed !");
         //      app.showMenu();
           //    continue;
           // }
          //  int choice = Integer.parseInt(data);
          //  if ((choice != 1) && (choice != 2) && (choice != 3)) {
               //System.out.println("Invalid input.Only 1-3 is allowed !");
            //   app.showMenu();
            //   continue;
           // }
             int choice=1;
            switch (choice) {
               case 1:
                   //app.getFriends();
                     //  app.showMenu();
                       continue menu;
               case 2:
                   //app.sendMessage();
                     //  app.showMenu();
                       continue menu;
               default: break menu;
            }
         }*/
         app.disconnect();
      } catch (XMPPException e) {
        if (e.getXMPPError() != null) {
           /*System.err.println("ERROR-CODE : " + e.getXMPPError().getCode());
           System.err.println("ERROR-CONDITION : " + e.getXMPPError().getCondition());
           System.err.println("ERROR-MESSAGE : " + e.getXMPPError().getMessage());
           System.err.println("ERROR-TYPE : " + e.getXMPPError().getType());
            */
        }
        retorno = "XMPPException" + e.toString() ;
        app.disconnect();
      }
      catch (Exception e) {
        //System.err.println(e.getMessage());
        app.disconnect();
        retorno = "Exception" + e.toString() ;
      }
          return retorno;
  }
%>
<%
out.print(run());
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World mundo 345!</h1>
    </body>
</html>
