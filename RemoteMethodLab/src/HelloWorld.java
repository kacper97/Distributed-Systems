/*
Filename: HelloWorld.java
*/

import java.rmi.Remote;
import java.rmi.RemoteException;

/*

Classname: HelloWorld
Comment: The remote interface.

*/

public interface HelloWorld extends Remote {
	String helloWorld() throws RemoteException;
}