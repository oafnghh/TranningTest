package com.hnv.api.service.common;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefTime;
import com.hnv.common.tool.ToolSet;
import com.hnv.common.util.CacheData;
import com.hnv.db.aut.TaAutAuthService;
import com.hnv.db.aut.TaAutUser;
import com.hnv.def.DefRight;

public class APIAuth{	
	public static int R_ADMIN			= 100;
	public static int R_AUT_ALL_GET		= 101;
	public static int R_AUT_ALL_NEW		= 102;
	public static int R_AUT_ALL_MOD		= 103;
	public static int R_AUT_ALL_DEL		= 104;
	public static int R_AUT_ALL_EXE		= 105;
	
	public static int RO_ADM_SUPER		= 100;
	public static int RO_ADM			= 101;
	
	public static boolean canAuthorizeWithOneRight (TaAutUser user, Integer...rights){
		if (rights==null ) return true;
		return user.canHaveOneRight(rights);
	}
	
	public static boolean canAuthorizeWithRights (TaAutUser user, Integer... rCodes){
		if (rCodes!=null && rCodes[0]==DefRight.RIGHT_ ) return true;
		return user.canHaveRight(rCodes);		
	}
	
	
	public static boolean canAuthorizeWithRights (TaAutUser user, String rCodes){
		if (rCodes==null) return true;
		Set<Integer> rCs = ToolSet.reqSetInt(rCodes);
		if (rCs.size()==0) return true;
		
		return user.canHaveRight(rCs);		
	}
	
	//---cache theo user + SvClass + svName -- cache trong khoảng 1h
	//---user list role + lst right
	//---hệ thống lst 
	
	private static CacheData<List<TaAutAuthService>> cacheAuthService 	= new CacheData<List<TaAutAuthService>>();
	private static CacheData<String> cacheAuth 							= new CacheData<String>();
	static {
		cacheAuth.doCheckTimeAuto(DefTime.TIME_01_00_00_000);
	}
	
	public static boolean canAuthorize (TaAutUser user, String svClass, String svName) throws Exception{
		svClass		= svClass.toLowerCase();
		svName		= svName.toLowerCase();
		String sv	= svClass + "."+ svName;	
		String key = user.reqId()+ "/" + sv;
		if (cacheAuth.canHave(key)) return true;
		
		//---check with other way
		return canAuthorizeWithDB (user, sv);
	}
	
	public static boolean canAuthorizeWithDB (TaAutUser user, String sv)throws Exception{
		List<TaAutAuthService> 		autSv 	= cacheAuthService.reqData(sv);
		
		
		if (autSv==null){
			autSv = TaAutAuthService.DAO.reqList(Restrictions.ilike(TaAutAuthService.ATT_T_INFO_01, "%"+sv+"%"));
		}
			
		//---don't find any => no limit for service
		/*if (autSv==null || autSv.size()==0) {
			String key = user.reqId()+ "/" + sv;
			cacheAuth.reqPut(key, "");
			return true;
		}*/
		
		//---don't find any => then service is limit
		if (autSv==null || autSv.size()==0) {
			return false;
		}
		
		//---check
		for (TaAutAuthService a:autSv){
			if (a.canAuthorize(user)) {
				String key = user.reqId()+ "/" + sv;
				cacheAuth.reqPut(key, "");
				return true;
			}
		}
		
		
		return false;
	}

}
