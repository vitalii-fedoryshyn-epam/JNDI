package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vf.common.ActionResult;
import com.vf.common.ActionService;
import com.vf.common.ActionStatus;
import com.vf.common.CallbackClientListener;
import com.vf.common.EmptyActionResult;
import com.vf.common.IntegerActionResult;
import com.vf.common.StringActionResult;

public class Server extends UnicastRemoteObject implements ActionService {

	protected Server() throws RemoteException {
		super();
		random = new Random();
		executor = Executors.newFixedThreadPool(NTHREDS);
	}

	public static void main(String[] args) {
		try {
			Server server = new Server();
			Naming.rebind("Server", server);
			System.out.println("Server started!");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	private static final int NTHREDS = 10;
	private static int MIN_VALUE = 1000;
	private static int MAX_VALUE = 10000;

	private Random random;
	private ExecutorService executor;

	public void shutdown() {
		executor.shutdown();
	}

	@Override
	public void doSomeWork(final CallbackClientListener client, final int sequenceNumber) throws RemoteException {
		System.out.println("Received request: 'doSomeWork'. Sequence number: " + sequenceNumber + ". Client: " + client);
		System.out.println();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					ActionResult actionResult = null;
					// Heavy work. Need to sleep a little bit.
					Thread.sleep(getRandomInt());
					// Start work
					try {
						actionResult = new EmptyActionResult(ActionStatus.SUCCESS, "doSomeWork", sequenceNumber, null);
					} catch (Exception e) {
						// handle more
					}
					// End work
					client.notify(actionResult);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void doSomeWorkWithInteger(final CallbackClientListener client, final int sequenceNumber) throws RemoteException {
		System.out.println("Received request: 'doSomeWorkWithInteger'. Sequence number: " + sequenceNumber + ". Client: " + client);
		System.out.println();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					ActionResult actionResult = null;
					// Heavy work. Need to sleep a little bit.
					Thread.sleep(getRandomInt());
					// Start work
					try {
						// handle more
						Integer.valueOf("asdasd");
					} catch (Exception e) {
						actionResult = new IntegerActionResult(ActionStatus.FAILURE, "doSomeWorkWithInteger", sequenceNumber, e, null);
					}
					// End work
					client.notify(actionResult);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void getString(final CallbackClientListener client, final int sequenceNumber) throws RemoteException {
		System.out.println("Received request: 'getString'. Sequence number: " + sequenceNumber + ". Client: " + client);
		System.out.println();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					ActionResult actionResult = null;
					// Heavy work. Need to sleep a little bit.
					Thread.sleep(getRandomInt());
					// Start work
					try {
						String s = "sssssss data: " + new Date();
						actionResult = new StringActionResult(ActionStatus.SUCCESS, "getString", sequenceNumber, null, s);
					} catch (Exception e) {
						// handle more
					}
					// End work
					client.notify(actionResult);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private int getRandomInt() {
		return MIN_VALUE + random.nextInt(MAX_VALUE - MIN_VALUE);
	}

}
