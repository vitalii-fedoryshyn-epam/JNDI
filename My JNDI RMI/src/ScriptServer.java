import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ScriptServer extends Remote {

	Double calculate(String function, Double... params) throws RemoteException;

}
