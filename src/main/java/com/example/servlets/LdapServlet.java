package com.example.servlets;
import javax.naming.Context;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;


@SuppressWarnings("serial")
//@WebServlet("add")
public class LdapServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get Login Form inputs
        String user = req.getParameter("username");
		String pass = req.getParameter("password");
        System.out.println("\n\nINPUTS\n Username:\t " + user + "\n password:\t " + pass);

        // Authenticate
        // if( ldap(user, pass) ){
        if( user.equals("u$ern@me") && pass.equals("pa33word") ){
            System.out.print("CORRECT CREDENTIALS !!!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        } else {
            System.out.print("WRONG CREDENTIALS ;( noooo!!!!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
            dispatcher.forward(req, resp);
        }
    }


    // LDAP Authenticate : True/False
    private boolean ldap(String username, String password){
        try{
            String ldapURL = "ldap://localhost:389";
            String userBase = "o=SSC,dc=adnane,dc=org";
            // String userSearchFilter = "(sAMAccountName={0})";

            // Set up the LDAP environment
            java.util.Hashtable<String, String> env = new java.util.Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, ldapURL);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + "," + userBase);
            env.put(Context.SECURITY_CREDENTIALS, password);

            // Attempt to create an initial DirContext
            DirContext ctx = new InitialDirContext(env);
            ctx.close();

            return true; // Authentication successful

        }catch(NamingException e){

            e.printStackTrace();
            return false; // Authentication failed

        }
    }


}