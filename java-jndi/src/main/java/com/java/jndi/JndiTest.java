package com.java.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * @Project: jdk
 * @description:   孙康
 * @author: sunkang
 * @create: 2018-10-31 17:02
 * @ModificationHistory who      when       What
 **/
public class JndiTest {

    public static void main(String[] args)  {
        // using the LDAP service provider
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");

        //the file system service provider
        Hashtable<String, Object> env1 = new Hashtable<String, Object>();
        env1.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.fscontext.RefFSContextFactory");

        //Supply the Information Needed by the Initial Context
        env.put(Context.PROVIDER_URL, "ldap://ldap.openldap.org:80");
//        env.put(Context.SECURITY_PRINCIPAL, "joeuser");
//        env.put(Context.SECURITY_CREDENTIALS, "joepassword");

        //    env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=JNDITutorial");

        //Creating the Initial Context
        try {
            Context ctx = new InitialContext(env);
            Object obj = ctx.lookup("somename");
        } catch (NamingException e) {
            e.printStackTrace();
        }

//        directory operations
      //  DirContext ctx = new InitialDirContext(env);


    }
}
