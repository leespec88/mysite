package com.lee2015.mysite.board.action;

import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		
		if("writeform".equals(actionName)){
			action = new WriteFormAction();
		}else if("write".equals(actionName)){
			action = new WriteAction();
		}else if("viewform".equals(actionName)){
			action = new ViewFoamAction();
		}else if("modifyform".equals(actionName)){
			action = new ModifyFormAction();
		}else if("modify".equals(actionName)){
			action = new ModifyAction();
		}else if("delete".equals(actionName)){
			action = new DeleteAction();
		}else if("search".equals(actionName)){
			action = new SearchAction();
		}else if("commentAdd".equals(actionName)){
			action = new CommentAddAction();
		}else if("commentDelete".equals(actionName)){
			action = new CommentDeleteAction();
		}else{
			action = new IndexAction();
		}
		
		return action;
	}

}
