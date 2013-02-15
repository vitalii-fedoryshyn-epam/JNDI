import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class Client {

	public static void main(String[] args) {
		main2();
	}

	public static void main1() {
		System.out.println("main 1");
		try {
			Registry registry = LocateRegistry.getRegistry();
			ScriptServer scriptServer = (ScriptServer) registry.lookup("Server");

			System.out.println("get: " + scriptServer.calculate("get"));
			System.out.println("add1: " + scriptServer.calculate("add1", Double.valueOf(123.321)));
			System.out.println("add2: " + scriptServer.calculate("add2", Double.valueOf(2.2), Double.valueOf(4.4)));
			Thread.sleep(10000);
		} catch (Exception e) {
			System.err.println("Client exception:");
			e.printStackTrace();
		}
	}

	public static void main2() {
		System.out.println("main 2");
		try {
			Hashtable<String, Object> env = new Hashtable<String, Object>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
			env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

			DirContext dirContext = new InitialDirContext(env);
			ScriptServer scriptServer = (ScriptServer) dirContext.lookup("Server");

			System.out.println("get: " + scriptServer.calculate("get"));
			System.out.println("add1: " + scriptServer.calculate("add1", Double.valueOf(123.321)));
			System.out.println("add2: " + scriptServer.calculate("add2", Double.valueOf(2.2), Double.valueOf(4.4)));
			Thread.sleep(10000);
		} catch (Exception e) {
			System.err.println("Client exception:");
			e.printStackTrace();
		}
	}

}
