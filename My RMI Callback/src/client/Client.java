package client;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import com.vf.common.ActionResult;
import com.vf.common.ActionService;
import com.vf.common.ActionStatus;
import com.vf.common.CallbackClientListener;

public class Client implements CallbackClientListener {

	private int requests = 0;

	public synchronized int getRequests() {
		return requests;
	}

	public synchronized void setRequests(int requests) {
		this.requests = requests;
	}

	public synchronized void incrementRequests() {
		++requests;
	}

	public synchronized void decrementRequests() {
		--requests;
	}

	public static void main(String args[]) {
		try {
			Client client = new Client();
			System.out.println("Exporting the client");
			UnicastRemoteObject.exportObject(client, 0);
			String serverURL = "rmi://127.0.0.1:1099/Server";
			ActionService server = (ActionService) Naming.lookup(serverURL);

			// Execute 4 actions on server
			int i = 0;
			server.doSomeWork(client, ++i);
			client.incrementRequests();

			server.doSomeWorkWithInteger(client, ++i);
			client.incrementRequests();

			server.getString(client, ++i);
			client.incrementRequests();

			server.getString(client, ++i);
			client.incrementRequests();

			while (true) {
				System.out.println("Waiting for response...");
				Thread.sleep(2000);
				if (client.getRequests() == 0) {
					UnicastRemoteObject.unexportObject(client, true);
					System.out.println("All responses processed...");
					Thread.sleep(10000);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured " + "while adding attraction: " + "\n" + e.getMessage());
		}
	}

	@Override
	public void notify(ActionResult actionResult) {
		synchronized (this) {
			System.out.println("------------------------------");
			System.out.println("Receive response: " + actionResult.getActionName() + ". Sequence number: " + actionResult.getSequenceNumber());
			System.out.println("Result: " + actionResult.getActionStatus());
			if (ActionStatus.FAILURE.equals(actionResult.getActionStatus())) {
				System.out.println("Exception: " + actionResult.getThrownException());
			} else {
				if (actionResult.getClass() != null) {
					System.out.println("Result class: " + actionResult.getResultClass());
					System.out.println("Result object: " + actionResult.getResultObject());
				} else {
					System.out.println("Result class: void method");
					System.out.println("Result object: void method");
				}
			}
			System.out.println("------------------------------");
		}
		// can perform some other actions with result
		// and finally
		decrementRequests();
	}
}
