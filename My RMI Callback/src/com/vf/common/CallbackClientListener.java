package com.vf.common;

import java.rmi.RemoteException;

public interface CallbackClientListener extends java.rmi.Remote {

	void notify(ActionResult actionResult) throws RemoteException;

}
