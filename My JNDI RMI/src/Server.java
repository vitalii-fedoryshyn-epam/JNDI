import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Server implements ScriptServer {

	public static final String FILE_NAME = "test.js";

	public static void main(String[] args) {
		try {
			ScriptServer scriptServer = new Server();
			ScriptServer stub = (ScriptServer) UnicastRemoteObject.exportObject(scriptServer, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Server", stub);
			System.out.println("ScriptServer bound");
		} catch (Exception e) {
			System.err.println("ScriptServer exception:");
			e.printStackTrace();
		}
	}

	private ScriptEngine engine;

	public Server() {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName("JavaScript");

		// evaluate script
		try {
			engine.eval(new java.io.FileReader(FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Double calculate(String function, Double... params) throws RemoteException {
		Double result = null;
		try {
			Invocable inv = (Invocable) engine;
			result = (Double) inv.invokeFunction(function, (Object[]) params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
