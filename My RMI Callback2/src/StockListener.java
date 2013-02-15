import java.rmi.RemoteException;
import java.util.List;

public interface StockListener extends java.rmi.Remote {

	void addStockInfo(StockInfo stockInfo) throws RemoteException;

	void updateStockInfos(List<StockInfo> stockInfos) throws RemoteException;

}
