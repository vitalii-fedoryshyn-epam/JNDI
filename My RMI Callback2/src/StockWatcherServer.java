import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StockWatcherServer extends UnicastRemoteObject implements StockWatcher {

	public static void main(String args[]) {
		try {
			StockWatcher stockWatcher = new StockWatcherServer();
			Naming.rebind("StockWatcherServerRMI", stockWatcher);
			System.out.println("Server started!");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	private static final long serialVersionUID = 1L;

	private List<StockListener> stockListeners = new ArrayList<StockListener>();
	private List<StockInfo> stockInfos = new ArrayList<StockInfo>();

	protected StockWatcherServer() throws RemoteException {
		super();
		stockInfos.add(new StockInfo("IBM", 213.87));
		stockInfos.add(new StockInfo("AMD", 111.11));
		stockInfos.add(new StockInfo("Intel", 413.31));
		new Thread(new WatchingThread()).start();
	}

	@Override
	public void subscribe(StockListener o) throws RemoteException {
		stockListeners.add(o);
	}

	@Override
	public void unsubscribe(StockListener o) throws RemoteException {
		System.out.println("Before unsubscribe" + stockListeners.size());
		stockListeners.remove(o);
		System.out.println("After unsubscribe" + stockListeners.size());
	}

	private class WatchingThread implements Runnable {

		private Random random = new Random();

		private static final double MIN_RANGE = 100d;
		private static final double MAX_RANGE = 999.99d;

		private double getRandomDouble() {
			return MIN_RANGE + (MAX_RANGE - MIN_RANGE) * random.nextDouble();
		}

		@Override
		public void run() {
			try {
				int i = 0;
				while (true) {
					++i;
					Thread.sleep(2000);
					if (i % 10 == 0) {
						StockInfo stockInfo = new StockInfo("xxx_" + new Date(), 413.31);
						stockInfos.add(stockInfo);
						for (StockListener stockListener : stockListeners) {
							stockListener.addStockInfo(stockInfo);
						}
					} else {
						stockInfos.get(1).setPrice(getRandomDouble());
						stockInfos.get(2).setPrice(getRandomDouble());
						for (StockListener stockListener : stockListeners) {
							stockListener.updateStockInfos(stockInfos);
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

}
