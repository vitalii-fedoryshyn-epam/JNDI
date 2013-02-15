package com.vf.common;

import java.rmi.RemoteException;

public interface ActionService extends java.rmi.Remote {

	void doSomeWork(CallbackClientListener client, final int sequenceNumber) throws RemoteException;

	void doSomeWorkWithInteger(CallbackClientListener client, final int sequenceNumber) throws RemoteException;

	void getString(CallbackClientListener client, final int sequenceNumber) throws RemoteException;

}
