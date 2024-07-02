package com.hnv.api.service.sub;

import java.util.Set;

import com.hnv.api.def.DefRight;
import com.hnv.common.tool.ToolSet;
import com.hnv.db.aut.TaAutUser;

public class APIAuth{	
	
	public static boolean canAuthorizeWithOneFrom (TaAutUser user, Integer...rights){
		if (rights==null ) return true;
		return user.canHaveOneRight(rights);
	}
	
	public static boolean canAuthorize (TaAutUser user, Integer... rCodes){
		if (rCodes!=null && rCodes[0]==DefRight.RIGHT_ ) return true;
		return user.canHaveRight(rCodes);		
	}
	
	
	public static boolean canAuthorize (TaAutUser user, String rCodes){
		if (rCodes==null) return true;
		Set<Integer> rCs = ToolSet.reqSetInt(rCodes);
		if (rCs.size()==0) return true;
		
		return user.canHaveRight(rCs);		
	}

}
