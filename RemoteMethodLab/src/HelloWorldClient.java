/*
Filename: HelloWorldClient.java
*/

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

/*
Classname: HelloWorldClient
Comment: The RMI client.
*/

public class HelloWorldClient {

static String message = "blank";
static int x;
static int y;

// The HelloWorld object "obj" is the identifier that is
// used to refer to the remote object that implements
// the HelloWorld interface.

static HelloWorld obj = null;

public static void main(String args[])
{
	try {
		obj = (HelloWorld)Naming.lookup("//"
				+ "localhost"
				+ "/HelloWorld");
		message = obj.helloWorld();
		System.out.println("Please enter two random numbers you want to add up");
		Scanner sc = new Scanner(System.in);
	     int x = sc.nextInt();
	     int y = sc.nextInt();
		
		
		int sum = obj.addNums(x, y);
		System.out.println("Message from the RMI-server was: \""
				+ message + "\n");
		System.out.println(sum);
	}
	catch (Exception e) {
		System.out.println("HelloWorldClient exception: "
				+ e.getMessage());
		e.printStackTrace();
	}
}
}