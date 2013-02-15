import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockListenerClient implements StockListener {

	private List<StockInfo> stockInfos = new ArrayList<StockInfo>();

	public static void main(String[] args) {
		try {
			StockListener client = new StockListenerClient();
			System.out.println("Exporting the client");
			UnicastRemoteObject.exportObject(client, 0);
			String serverURL = "rmi://127.0.0.1:1099/StockWatcherServerRMI";
			StockWatcher server = (StockWatcher) Naming.lookup(serverURL);
			server.subscribe(client);
			Thread.sleep(args.length == 0 ? 24000: Integer.parseInt(args[0]));
			server.unsubscribe(client);
			UnicastRemoteObject.unexportObject(client, true);
		} catch (Exception e) {
			System.out.println("Exception occured " + "while adding attraction: " + "\n" + e.getMessage());
		}
	}

	@Override
	public void addStockInfo(StockInfo stockInfo) throws RemoteException {
		stockInfos.add(stockInfo);
		printStockInfos("addStockInfo");
	}

	@Override
	public void updateStockInfos(List<StockInfo> stockInfos) throws RemoteException {
		this.stockInfos = stockInfos;
		printStockInfos("updateStockInfos");
	}

	private void printStockInfos(String action) {
		System.out.println("----------------------------------------------------");
		System.out.println("Action: " + action + ", Date: " + new Date());
		for (StockInfo stockInfo : stockInfos) {
			System.out.println(stockInfo);
		}
	}

}
