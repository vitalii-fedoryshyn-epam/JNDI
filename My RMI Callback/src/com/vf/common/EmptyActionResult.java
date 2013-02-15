package com.vf.common;

public class EmptyActionResult extends AbstractActionResult<Object> {

	public EmptyActionResult(ActionStatus actionStatus, String actionName, int sequenceNumber, Exception exception) {
		super(actionStatus, actionName, sequenceNumber, exception, null);
	}

	@Override
	public Class getResultClass() {
		return null;
	}

}