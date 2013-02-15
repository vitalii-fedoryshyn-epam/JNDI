package com.vf.common;

public class IntegerActionResult extends AbstractActionResult<Integer> {

	public IntegerActionResult(ActionStatus actionStatus, String actionName, int sequenceNumber, Exception exception, Integer result) {
		super(actionStatus, actionName, sequenceNumber, exception, result);
	}

	@Override
	public Class getResultClass() {
		return Integer.class;
	}

}