package com.vf.common;

public interface ActionResult {

	ActionStatus getActionStatus();

	String getActionName();
	
	int getSequenceNumber();

	Object getResultObject();

	Class getResultClass();

	Exception getThrownException();

}
