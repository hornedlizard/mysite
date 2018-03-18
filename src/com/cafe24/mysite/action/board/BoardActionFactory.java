package com.cafe24.mysite.action.board;

import com.cafe24.mvc.action.AbstractActionFactory;
import com.cafe24.mvc.action.Action;
import com.cafe24.mysite.action.board.ListAction;

public class BoardActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("list".equals(actionName)) {
			action = new ListAction();
		} else if ("writeform".equals(actionName)) {
			action = new WriteformAction();
		} else if ("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("view".equals(actionName)) {
			action = new ViewAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if ("update".equals(actionName)) {
			action = new UpdateAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
