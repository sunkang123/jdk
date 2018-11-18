package com.java.demo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import static java.util.concurrent.TimeUnit.*;

interface RemoteOperations extends Remote {
    String remoteOperation() throws RemoteException;
}

public  class RMITest implements RemoteOperations {
    private static final String REMOTE_NAME = RemoteOperations.class.getName();
    private static final RemoteOperations classVariable = new RMITest();

    private static boolean holdStrongReference = false;
    private static boolean invokeGarbageCollector = true;
    private static int delay = 0;

    public static void main( String[] args) throws Exception {
        args = new String []{"-nogc","-hold"};
        for (final String arg : args) {
            if ("-gc".equals(arg)) {
                invokeGarbageCollector = true;
            } else if ("-nogc".equals(arg)) {
                invokeGarbageCollector = false;
            } else if ("-hold".equals(arg)) {
                holdStrongReference = true;
            } else if ("-release".equals(arg)) {
                holdStrongReference = false;
            } else if (arg.startsWith("-delay")) {
                delay = Integer.parseInt(arg.substring("-delay".length()));
            } else {
                System.err.println("usage: javac RMITest.java && java RMITest [-gc] [-nogc] [-hold] [-release] [-delay<seconds>]");
                System.exit(1);
            }
        }
        server();

        if (invokeGarbageCollector) {
            System.gc();
        }
        if (delay > 0) {
            System.out.println("delaying " + delay + " seconds");
            final long milliseconds = MILLISECONDS.convert(delay, SECONDS);
            Thread.sleep(milliseconds);
        }
        client();
        System.exit(0); // stop RMI server thread
    }

    @Override
    public String remoteOperation() {
        return "foo";
    }

    private static void server() throws Exception {
        // This reference is eligible for GC after this method returns
         RemoteOperations methodVariable = new RMITest();
         RemoteOperations toBeStubbed = holdStrongReference ? classVariable : methodVariable;
         Remote remote = UnicastRemoteObject.exportObject(toBeStubbed, 0);
         Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.bind(REMOTE_NAME, remote);
    }

    private static void client() throws Exception {
         Registry registry = LocateRegistry.getRegistry();
         Remote remote = registry.lookup(REMOTE_NAME);
         RemoteOperations stub = RemoteOperations.class.cast(remote);
         String message = stub.remoteOperation();
        System.out.println("received: " + message);
    }
}