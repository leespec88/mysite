package com.lee2015.mysite.guestbook.action;

import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action =null;
		
		if("insert".equals(actionName)){
			
			action = new InsertAction();
			
		}else if("delete".equals(actionName)){
			
			action = new DeleteAction();
		
		}else if("deleteform".equals(actionName)){
			
			action = new DeleteFormAction();
		
		}else{
			action = new IndexAction();
		}
				
		return action;
	}

}
