package com.vf.common;

import java.io.Serializable;

abstract class AbstractActionResult<T> implements ActionResult, Serializable {

	ActionStatus actionStatus;
	String actionName;
	int sequenceNumber;
	Exception exception;
	T result;

	protected AbstractActionResult(ActionStatus actionStatus, String actionName, int sequenceNumber, Exception exception, T result) {
		if (actionStatus == null) {
			throw new IllegalArgumentException("Action status can not be null");
		}
		if (actionName == null) {
			throw new IllegalArgumentException("Action name can not be null");
		}
		if (ActionStatus.SUCCESS.equals(actionStatus) && exception != null) {
			throw new IllegalArgumentException("Exception can not be specified for success request");
		}
		if (ActionStatus.FAILURE.equals(actionStatus) && exception == null) {
			throw new IllegalArgumentException("Exception can not be null for failure request");
		}
		this.actionStatus = actionStatus;
		this.actionName = actionName;
		this.sequenceNumber = sequenceNumber;
		this.exception = exception;
	}

	@Override
	public ActionStatus getActionStatus() {
		return actionStatus;
	}

	@Override
	public String getActionName() {
		return actionName;
	}

	@Override
	public Exception getThrownException() {
		return exception;
	}

	@Override
	public T getResultObject() {
		return result;
	}

	@Override
	public int getSequenceNumber() {
		return sequenceNumber;
	}

}
