package com.cafe24.mvc.action.guestbook;

import com.cafe24.mvc.action.AbstractActionFactory;
import com.cafe24.mvc.action.Action;

public class GuestbookActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("list".equals(actionName)) {
			action = new ListAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteformAction();
		} else if ("delete".equals(actionName)) {
			System.out.println(actionName);
			action = new DeleteAction();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
