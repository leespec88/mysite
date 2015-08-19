package com.lee2015.mysite.user.action;

import com.lee2015.mysite.main.action.IndexAction;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.action.ActionFactory;

public class UserActionFactroy extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		
		if("loginform".equals(actionName)){
				action = new LoginFormAction();
		}else if("joinform".equals(actionName)){
			action = new JoinFormAction();
		}else if("join".equals(actionName)){
			action = new JoinAction();
		}else if("joinsuccess".equals(actionName)){
			action = new JoinSuccessAction();
		}else if("loginform".equals(actionName)){
				action = new LoginFormAction();
		}else if("login".equals(actionName)){
			action = new LoginAction();
		}else if("logout".equals(actionName)){
			action = new LogoutAction();
		}else if("pwcheck".equals(actionName)){
			action = new PwCheckAction();
		}else if("docheck".equals(actionName)){
			action = new DoCheckAction();
		}else if("modifyform".equals(actionName)){
			action = new ModifyForm();
		}else if("modify".equals(actionName)){
			action = new ModifyAction();
		}else if("checkemail".equals(actionName)){
			action = new CheckEmailAction();
		}else{
			action = new IndexAction();
		}
		
		return action;
	}

}
