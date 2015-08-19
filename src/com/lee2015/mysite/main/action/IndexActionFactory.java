package com.lee2015.mysite.main.action;

import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.action.ActionFactory;

public class IndexActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		return new IndexAction();
	}

}
