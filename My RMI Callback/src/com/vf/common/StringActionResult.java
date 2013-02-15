package com.vf.common;

public class StringActionResult extends AbstractActionResult<String> {

	public StringActionResult(ActionStatus actionStatus, String actionName, int sequenceNumber, Exception exception, String result) {
		super(actionStatus, actionName, sequenceNumber, exception, result);
	}

	@Override
	public Class getResultClass() {
		return String.class;
	}

}
