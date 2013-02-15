import java.rmi.RemoteException;

public interface StockWatcher extends java.rmi.Remote {
	void subscribe(StockListener o) throws RemoteException;

	void unsubscribe(StockListener o) throws RemoteException;
}
