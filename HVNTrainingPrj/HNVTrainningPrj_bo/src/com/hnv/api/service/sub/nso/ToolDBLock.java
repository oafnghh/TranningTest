package com.hnv.api.service.sub.nso;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefDB;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDate;
import com.hnv.data.json.JSONObject;
import com.hnv.db.sys.TaSysLock;

public class ToolDBLock {

	public static final String  STR_DELIM		= " | ";
	public static final String  LAB_OBJ_TYP 	= "objectType";
	public static final String  LAB_OBJ_KEY 	= "objectKey";
	public static final String  LAB_LCK_ID 		= "lock_id";

	private static long TIME_RELEASE 		= 1000 * 60 * 15; //15 minutes
	private static long TIME_DELETE  		= 1000 * 60 * 30; //30 minutes

	
	public static boolean canDeleteLock(JSONObject json) {
		Integer idLock    = ToolData.reqInt(json, LAB_LCK_ID, null);
		if (idLock==null) return false;
		return canDeleteLock(idLock);
	}
	
	public static boolean canDeleteLock(int id) {
		try {
			if (id<=0) return false;
			TaSysLock taSysLock = TaSysLock.DAO.reqEntityByRef(id);
			return canDeleteLock(taSysLock);				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
	public static boolean canDeleteLock(int objType, int objectKey, int userId, String val01, String val02) {
		try {
			if (objType<=0 || objectKey<=0 || userId<=0 ) return false;
			List<TaSysLock> lst = TaSysLock.DAO.reqList(
					Restrictions.eq(TaSysLock.ATT_I_VAL_01		,	objType), 
					Restrictions.eq(TaSysLock.ATT_I_VAL_02		, 	objectKey),
					Restrictions.eq(TaSysLock.ATT_I_AUT_USER	, 	userId));
		
			TaSysLock taSysLock =  null;
			if (lst!=null && lst.size()>0) taSysLock = lst.get(0);
			return canDeleteLock(taSysLock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static  boolean canDeleteLock(TaSysLock taSysLock) {
		if(taSysLock == null) return false;
		Session sess 	= null;
		try {
			sess = TaSysLock.DAO.reqSessionCurrent();
			TaSysLock.DAO.doRemove(sess, taSysLock);	
			TaSysLock.DAO.doSessionCommit(sess);
			return true;
		} catch (Exception e) {
			if (sess!=null) TaSysLock.DAO.doSessionRollback(sess);
			e.printStackTrace();
		}
		return false;
	}
	
	public static  boolean canRemoveLockByCheckTime(TaSysLock taSysLock) throws Exception {
		if (taSysLock==null) return false;
		Session sess 	= null;
		
		sess = TaSysLock.DAO.reqSessionCurrent();
		
		long threadHold = TIME_DELETE;
		Date dateMod = (Date) taSysLock.req(TaSysLock.ATT_D_DATE_02);
		if (dateMod==null) {
			dateMod			= (Date) taSysLock.req(TaSysLock.ATT_D_DATE_01);
			threadHold 		= TIME_RELEASE;
		}
		if (dateMod==null){
			TaSysLock.DAO.doRemove(sess, taSysLock);	
			TaSysLock.DAO.doSessionCommit(sess);
			return true;
		}

		Long now		= new Date().getTime();
		long dtLock 	= dateMod.getTime();
	
		if (now-dtLock > threadHold){
			TaSysLock.DAO.doRemove(sess, taSysLock);	
			TaSysLock.DAO.doSessionCommit(sess);
			return true;
		}				
		return false;
	}

	public static  boolean canRemoveLockByCheckTime(TaSysLock taSysLock, long timeThreadHold) throws Exception {
		if (taSysLock==null) return false;

		if (timeThreadHold<=0) timeThreadHold = 1000;
		
		Session sess 	= null;
		sess = TaSysLock.DAO.reqSessionCurrent();
		
		Date dateMod = (Date) taSysLock.req(TaSysLock.ATT_D_DATE_02);
		if (dateMod==null) {
			dateMod = (Date) taSysLock.req(TaSysLock.ATT_D_DATE_01);
		}
		if (dateMod==null){
			TaSysLock.DAO.doRemove(sess, taSysLock);	
			TaSysLock.DAO.doSessionCommit(sess);
			return true;
		}

		Long now		= new Date().getTime();
		long dtLock 	= dateMod.getTime();

		if (now-dtLock> timeThreadHold){
			TaSysLock.DAO.doRemove(sess, taSysLock);	
			TaSysLock.DAO.doSessionCommit(sess);
			return true;
		}				
		return false;
	}

	//-------------------------------------------------------------------------------------
	public static boolean canExistLock(JSONObject json) {
		Integer idLock    = ToolData.reqInt(json, "lock_id", null);
		return canExistLock(idLock);
	}
	
    public static boolean canExistLock(Integer lockId) {
    	if (lockId==null) return false;
        Session sess 	= null;
		try {
			sess = TaSysLock.DAO.reqSessionCurrent();
			
			List<TaSysLock> lst = TaSysLock.DAO.reqList(
					sess, 
					Restrictions.eq(TaSysLock.ATT_I_ID		,	lockId));
		
			if (lst != null && lst.size() > 0) return true;
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }

    public static boolean canExistLock(Integer objType, Integer objKey, Integer userId) {
    	Session sess 	= null;
		try {
			sess = TaSysLock.DAO.reqSessionCurrent();
			
			if (userId != null) {
				List<TaSysLock> lst = TaSysLock.DAO.reqList(
						sess, 
						Restrictions.eq(TaSysLock.ATT_I_VAL_01		,	objType),
						Restrictions.eq(TaSysLock.ATT_I_VAL_02		,	objKey),
						Restrictions.eq(TaSysLock.ATT_I_AUT_USER	,	userId));
				
	            if (lst != null && lst.size() > 0) return true;
	            return false;
	        } else {
	        	List<TaSysLock> lst = TaSysLock.DAO.reqList(
						sess, 
						Restrictions.eq(TaSysLock.ATT_I_VAL_01		,	objType),
						Restrictions.eq(TaSysLock.ATT_I_VAL_02		,	objKey));
	        	
	            if (lst != null && lst.size() > 0) return true;
	            return false;
	        }
				        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }

	public static boolean canExistLockWithOtherUser(int objType, int objKey, int userIdWantLock) {
		Session sess 	= null;
		
		try {
			sess = TaSysLock.DAO.reqSessionCurrent();
			
			TaSysLock taSysLock = null;
		
			List<TaSysLock> lst = TaSysLock.DAO.reqList(
					sess, 
					Restrictions.eq(TaSysLock.ATT_I_VAL_01		,	objType),
					Restrictions.eq(TaSysLock.ATT_I_VAL_02		,	objKey));
			
            if (lst != null && lst.size() > 0) taSysLock =  lst.get(0);
            
			if (taSysLock != null) {
				int uId = (int)taSysLock.req(TaSysLock.ATT_I_AUT_USER);
				if (uId == userIdWantLock) {
					doRefreshLock(taSysLock);
					return false;
				} else {
					if (canRemoveLockByCheckTime(taSysLock)) {
						return false;
					} else
						return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	//---------------------------------------------------------------------------------------------	
	public static TaSysLock reqLock( JSONObject json, String attrName, int status, Integer entityType, Integer entityId, Integer userId, String val01, String val02) throws Exception {
		TaSysLock 	lock 	= null;
		Long 		dolock 	= (Long) json.get(attrName);
		if(dolock!=null && dolock == status){
			lock 	= reqLock(entityType, entityId, userId, val01, val02);
		}
		return lock;
    }
	
	public static TaSysLock reqLock(JSONObject lockJson, Integer userId, String val01, String val02) throws Exception {
		if (userId == null) return null;
		if (lockJson==null) return null;
        int objectType	= Integer.parseInt(lockJson.get(LAB_OBJ_TYP).toString());
        int objectKey	= Integer.parseInt(lockJson.get(LAB_OBJ_KEY).toString());
        return reqLock(objectType, objectKey, userId, val01, val02);
    }
	
    public static TaSysLock reqLock(Integer objType, Integer objKey, Integer userId, String val01, String val02) throws Exception {
        if (userId == null) return null;
        try {
        	List<TaSysLock> lst 		= TaSysLock.DAO.reqList(
    				Restrictions.eq(TaSysLock.ATT_I_VAL_02	, objKey),
    				Restrictions.eq(TaSysLock.ATT_I_VAL_01	, objType));
        	
			TaSysLock taSysLock =  null;
			if (lst!=null && lst.size()>0) taSysLock = lst.get(0);

            int lockStat =  DefDB.DB_LOCK_STAT_OK;

            if (taSysLock == null) {
                taSysLock				= new TaSysLock(null, userId,  new Date(), null, lockStat, objType, objKey, val01, val02);
                TaSysLock.DAO.doPersist(taSysLock);
            } else {
            	int uId = (int)taSysLock.req(TaSysLock.ATT_I_AUT_USER);
                if (uId == userId) {
                    doRefreshLock(taSysLock);
                } else {
                    if (canRemoveLockByCheckTime(taSysLock)) {
                    	taSysLock				= new TaSysLock(null, userId,  new Date(), null, lockStat, objType, objKey, val01, val02);
                    	TaSysLock.DAO.doPersist(taSysLock);
                    } else {
                        taSysLock.reqSet(TaSysLock.ATT_I_STATUS, DefDB.DB_LOCK_STAT_KO);
                    }
                }
            }

//            int lockId = (int)taSysLock.req(TaSysLock.ATT_I_ID);
            doRemoveLockExpirated();
            return taSysLock;
        } catch (Exception e) {
        	e.printStackTrace();
        }

		//----something wrong here
        TaSysLock lck		= new TaSysLock(null, userId,  null, null, DefDB.DB_LOCK_STAT_KO, objType, objKey, val01, val02);
        return lck;
    }

    private static void doRemoveLockExpirated() throws Exception {
		//TODO Auto-generated method stub
		//do nothing because the check reqLock corvers all cases
    	
    	Date now  = new Date();
    	
    	List<TaSysLock> lst 		= TaSysLock.DAO.reqList(
				Restrictions.ne(TaSysLock.ATT_D_DATE_02	, null),
				Restrictions.lt(TaSysLock.ATT_D_DATE_02	, new Date(ToolDate.reqSecond(now) + TIME_DELETE)));
    	TaSysLock.DAO.doRemove(lst);

		
    	lst 		= TaSysLock.DAO.reqList(
				Restrictions.ne(TaSysLock.ATT_D_DATE_01	, null),
				Restrictions.lt(TaSysLock.ATT_D_DATE_01	,  new Date(ToolDate.reqSecond(now) + TIME_RELEASE)));
    	TaSysLock.DAO.doRemove(lst);
	}

	private static void doRefreshLock(TaSysLock taSysLock) throws Exception {
		if (taSysLock == null) return;
		
		Date now  = new Date();
		taSysLock.reqSet(TaSysLock.ATT_I_STATUS, TaSysLock.LOCK_STAT_ACTIVE);
		taSysLock.reqSet(TaSysLock.ATT_D_DATE_02, now);

		TaSysLock.DAO.doMerge(taSysLock);
	}

	//-------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------

	public static void doSetTime (long timeRelease, long timeDel) {
		TIME_RELEASE = timeRelease;
		TIME_DELETE  = timeDel;
	}

	public static long reqLockTimeDelete() {
		// TODO Auto-generated method stub
		return TIME_DELETE;
	}

	public static long reqLockTimeRelease() {
		// TODO Auto-generated method stub
		return TIME_RELEASE;
	}

}
