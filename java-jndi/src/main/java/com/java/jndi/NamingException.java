package com.java.jndi;

import javax.naming.*;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-10-31 19:32
 * @ModificationHistory who      when       What
 **/
public class NamingException extends Throwable {

    public static void main(String[] args) {
        Context ctx = null;
        try {
            ctx = new InitialContext();

//            ctx.bind("jdbc/billingDB","nihao");

//            NamingEnumeration<NameClassPair>  list= ctx.list("somename");

            Object obj = ctx.lookup("java:comp/env/ejb/SimpleConverter");


//            ctx.bind("jdbc/pool/fastCoffeeDB", cpds);

        } catch (AuthenticationException e) {
            // attempt to reacquire the authentication information

        } catch (javax.naming.NamingException e) {
            e.printStackTrace();
        }

    }
}
