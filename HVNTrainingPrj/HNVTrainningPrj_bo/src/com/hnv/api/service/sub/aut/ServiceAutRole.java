package com.hnv.api.service.sub.aut;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefDBExt;
import com.hnv.api.def.DefJS;
import com.hnv.api.def.DefRight;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.sub.APIAuth;
import com.hnv.common.tool.ToolDBLock;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDatatable;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutAuthorization;
import com.hnv.db.aut.TaAutRole;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.sys.TaSysLock;


/**
 * ----- ServiceAutRole by H&V
 * ----- Copyright 2017------------
 */
public class ServiceAutRole implements IService {

	//--------------------------------Service Definition----------------------------------
	public static final String SV_MODULE 					= "EC_V3".toLowerCase();

	public static final String SV_CLASS 					= "ServiceAutRole".toLowerCase();	

	public static final String SV_GET 			= "SVGet".toLowerCase();	
	public static final String SV_LST 			= "SVLst".toLowerCase();
	public static final String SV_LST_DYN		= "SVLstDyn".toLowerCase();

	public static final String SV_NEW 			= "SVNew".toLowerCase();	
	public static final String SV_MOD 			= "SVMod".toLowerCase();	
	public static final String SV_DEL 			= "SVDel".toLowerCase();

	public static final String SV_LCK_REQ 		= "SVLckReq".toLowerCase(); //req or refresh	
	public static final String SV_LCK_SAV 		= "SVLckSav".toLowerCase(); //save and continue
	public static final String SV_LCK_END 		= "SVLckEnd".toLowerCase();
	public static final String SV_LCK_DEL 		= "SVLckDel".toLowerCase();

	//-----------------------------------------------------------------------------------------------
	//-------------------------Default Constructor - Required -------------------------------------
	public ServiceAutRole(){
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
	}

	//-----------------------------------------------------------------------------------------------

	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		String 		sv 		= API.reqSVFunctName(json);
		TaAutUser 	user	= (TaAutUser) json.get("userInfo");

		try {
			if(sv.equals(SV_GET)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doGet(user, json, response);
			} else if(sv.equals(SV_LST)			&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){ 
				doLst(user, json, response);
			} else  if(sv.equals(SV_LST_DYN)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doListDyn(user, json, response);
			}  else if(sv.equals(SV_NEW)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doNew(user, json, response);
			} else if(sv.equals(SV_MOD)			&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doMod(user, json, response);
			} else  if(sv.equals(SV_DEL)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doDel(user, json, response);
			}else if(sv.equals(SV_LCK_REQ)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckReq(user, json, response);
			} else if(sv.equals(SV_LCK_SAV)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckSav(user, json, response);
			} else if(sv.equals(SV_LCK_END)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckEnd(user, json, response);
			} else if(sv.equals(SV_LCK_DEL)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckDel(user, json, response);

			} else {
				API.doResponse(response, DefAPI.API_MSG_ERR_UNKNOW);
			}

		}catch(Exception e){
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
		}
	}

	//---------------------------------------------------------------------------------------------------------

	private static final int WORK_GET = 1;
	private static final int WORK_LST = 2;
	private static final int WORK_NEW = 3;
	private static final int WORK_MOD = 4;
	private static final int WORK_DEL = 5;

	private static boolean canWorkWithObj ( TaAutUser user, int typWork, Object...params){		
		if (params==null|| params.length==0 || user==null) return false;
		//int userType	= (int) user.req(TaAutUser.ATT_I_TYPE);
		//if (userType==TYPE_ADM_ALL) return true;

		switch(typWork){
		case WORK_LST : 
		case WORK_MOD : 
		case WORK_GET : return true;
		case WORK_DEL : return true;


		case WORK_NEW : 
			//check something with params
			return true;

		}

		return false;

	}


	private void doListDyn(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception {

		Object[]  			dataTableOption = ToolDatatable.reqDataTableOption (json, null);
		List<String>		searchKey		= (List<String>)dataTableOption[0];	
		Integer				stat			= ToolData.reqInt	(json, "stat"	, null);
		Integer				typ				= ToolData.reqInt	(json, "type"	, null);
		Set<Integer>		typMulti		= ToolData.reqSetInt(json, "typMulti", null);


		Criterion 			cri 			= reqRestriction(user, searchKey);				

		List<TaAutRole> 	list 			= reqListDyn(dataTableOption, cri);

		if (list==null ){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		} 


		Integer iTotalRecords 				= reqNbListDyn().intValue();				
		Integer iTotalDisplayRecords 		= reqNbListDyn(cri).intValue();

		API.doResponse(response,ToolJSON.reqJSonString(		
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"iTotalRecords"				, iTotalRecords,
				"iTotalDisplayRecords"		, iTotalDisplayRecords,
				"aaData"					, list
				));
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doGet(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {	
		Integer 			objId	= ToolData.reqInt (json, "id"		, -1	);				
		Boolean				forced	= ToolData.reqBool (json, "forced"	, false	);

		TaAutRole 			ent 	= TaAutRole.DAO.reqEntityByRef(objId, forced);

		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, true, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, true, DefJS.SV_CODE, DefAPI.SV_CODE_ERR_RIGHT));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doLst(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		int 				idUser =  (int)user.reqRef();
		List<TaAutRole> 	list 	= doLst(user, json); //and other params if necessary
		if (list==null || list.size()==0){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, true, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, true, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, list 
				));
	}

	private static Criterion reqCriterion(Object...params) throws Exception{
		if (params==null || params.length==0) return null;

		Criterion cri = Restrictions.gt("I_ID", 0);		

		if (params!=null && params.length>0){
			//int type 	= (int) params[0];
			//cri 		= Restrictions.and(cri, Restrictions.eqOrIsNull(TaAutRole.ATT_I_TYPE, type));
		}			

		if (params!=null && params.length>1){
			//do something
		}

		return cri;
	}

	private static List<TaAutRole> doLst(TaAutUser user, JSONObject json, Object...params) throws Exception {		
		List<TaAutRole> list =   TaAutRole.DAO.reqList();
		return list;
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doNew(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doNew --------------");

		TaAutRole 			ent		= reqNew		(user, json);
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, true, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO					
					));
		}
		API.doResponse(response,ToolJSON.reqJSonString(
				DefJS.SESS_STAT		, true, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent
				));				
	}


	private static TaAutRole reqNew(TaAutUser user, JSONObject json) throws Exception {
		JSONObject				obj		= ToolData.reqJson(json, "obj", null);

		if (!canWorkWithObj(user, WORK_NEW, obj)){ //other param after obj...
			return null;
		}

		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaAutRole.class);
		TaAutRole  			ent	 	= new TaAutRole(attr);
		
		TaAutRole.DAO.doPersist(ent);

		return ent;
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doMod(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doMod --------------");
		TaAutRole	ent		= reqMod(user, json); 								
		if (ent == null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, true, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
		} else {
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, true,	DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, ent));
		}				

	}

	private static TaAutRole reqMod(TaAutUser user, JSONObject json) throws Exception {
		JSONObject			obj		= ToolData.reqJson (json, "obj"	, null);
		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaAutRole.class);
		int					entId 	= ToolData.reqInt(obj, "id", null);
		int					entTyp	= DefDBExt.ID_TA_AUT_ROLE;
		TaAutRole  			ent	 	= TaAutRole.DAO.reqEntityByRef(entId);
		
		if (ent==null){
			return null;
		}

		if (!canWorkWithObj(user, WORK_MOD, ent)){ //other param after obj...
			return null;
		}
		
		String rightsOld = ent.reqStr(TaAutRole.ATT_T_AUT_RIGHT);
		
		TaAutRole.DAO.doMerge(ent, attr);
		
		//---refresh all 
		String rightsNew = ent.reqStr(TaAutRole.ATT_T_AUT_RIGHT);
		if (!rightsNew.equals(rightsOld)) {
			List<TaAutAuthorization> lst = TaAutAuthorization.DAO.reqList(Restrictions.eq(TaAutAuthorization.ATT_I_AUT_ROLE, entId));
			for (TaAutAuthorization a : lst) {
				a.reqSet(TaAutAuthorization.ATT_T_AUT_RIGHT, rightsNew);
			}
			TaAutAuthorization.DAO.doMerge(lst);
		}
		
		return ent;
	}	

	//---------------------------------------------------------------------------------------------------------
	private static void doDel(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {

		JSONObject 		obj 	= ToolData.reqJson(json, "lock", null); 
		TaSysLock 		lock 	= ToolDBLock.reqLock(obj, user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		if (lock==null || lock.reqStatus() == 0){
			API.doResponse(response,ToolJSON.reqJSonString(						
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO,
					DefJS.RES_DATA		, lock
					));	
			return;
		}

		boolean					ok		= canDel(user, json);
		if (!ok){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
		} else {
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES));
		}

		ToolDBLock.canDeleteLock(lock);
	}

	private static boolean canDel(TaAutUser user, JSONObject json) throws Exception {
		Integer 	objId	= ToolData.reqInt (json, "id"		, -1	);	

		TaAutRole  	ent	 	= TaAutRole.DAO.reqEntityByRef(objId);
		if (ent==null){
			return false;
		}

		if (!canWorkWithObj(user, WORK_DEL, ent)){ //other param after ent...
			return false;
		}		

		Session sess = TaAutRole.DAO.reqSessionCurrent();
		try {
			//remove all other object connecting to this ent first-------
			List<TaAutAuthorization> lA = TaAutAuthorization.DAO.reqList(sess, Restrictions.eq(TaAutAuthorization.ATT_I_AUT_ROLE, objId));
			if (lA!=null && lA.size()>0) TaAutAuthorization.DAO.doRemove(sess, lA);

			TaAutRole.DAO.doRemove(sess, ent);

			TaAutRole.DAO.doSessionCommit(sess);
		}catch(Exception e) {
			TaAutRole.DAO.doSessionRollback(sess);
		}

		return true;
	}

	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private void doLckReq(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckReq --------------");		

		JSONObject 		obj 	= ToolData.reqJson(json, DefJS.REQ_DATA, null); 
		TaSysLock 		lock 	= ToolDBLock.reqLock(obj, user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		if (lock==null || lock.reqStatus() != TaSysLock.LOCK_STAT_ACTIVE){
			API.doResponse(response,ToolJSON.reqJSonString(						
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO,
					DefJS.RES_DATA		, lock
					));	
		}else{
			API.doResponse(response,ToolJSON.reqJSonString(						
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, lock
					));	
		}			
	}
	private void doLckDel(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckDel --------------");

		boolean isDeleted = ToolDBLock.canDeleteLock(json);			
		API.doResponse(response,ToolJSON.reqJSonString(		
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, isDeleted
				));			

	}

	private void doLckSav(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckSav --------------");	
		boolean isLocked 	= ToolDBLock.canExistLock(json);
		if(!isLocked){
			API.doResponse(response,ToolJSON.reqJSonString(	
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO
					));
		}

		TaAutRole  		ent	 	=  reqMod(user, json); 								
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
		} else {				
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));	
		}
	}


	//user when modify object with lock
	private void doLckEnd(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckEnd --------------");	
		boolean isLocked 	= ToolDBLock.canExistLock(json);
		if(!isLocked){
			API.doResponse(response,ToolJSON.reqJSonString(	
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO
					));
			return;
		}


		TaAutRole  		ent	 	=  reqMod(user, json); 					
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
		} else {
			ToolDBLock.canDeleteLock(json);
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));	
		}	
	}


	//---------------------------------------------------------------------------------

	
	private static Criterion reqRestriction(TaAutUser user, List<String> searchKey) throws Exception {
		Criterion cri = Restrictions.gt(TaAutRole.ATT_I_ID, 0);
		for (String s : searchKey){		
			cri = 	Restrictions.and(	cri, 
										Restrictions.ilike(TaAutRole.ATT_T_NAME	, s)
							);
		}		
		return cri;
	}

	private static List<TaAutRole> reqListDyn(Object[] dataTableOption, Criterion 	cri) throws Exception {		
		int 		begin 		= (int)	dataTableOption[1];
		int 		number 		= (int)	dataTableOption[2]; 
		int 		sortCol 	= (int)	dataTableOption[3]; 
		int 		sortTyp 	= (int)	dataTableOption[4];	

		List<TaAutRole> list 	= new ArrayList<TaAutRole>();		

		Order order = null;			
		String colName = TaAutRole.ATT_T_NAME;

		switch(sortCol){
		//case 0: colName = TaAutRole.ATT_I_ID; break;
		case 1: colName = TaAutRole.ATT_I_ID; break;
		case 2: colName = TaAutRole.ATT_T_NAME; break;					
		}

		if (colName!=null){
			switch(sortTyp){
			case 0: order = Order.asc(colName); break;
			case 1: order = Order.desc(colName); break;								
			}
		}

		if (order==null)
			list	= TaAutRole.DAO.reqList(begin, number, cri);
		else
			list	= TaAutRole.DAO.reqList(begin, number, order, cri);			
		return list;
	}

	private static Number reqNbListDyn() throws Exception {						
		return TaAutRole.DAO.reqCount();		
	}

	private static Number reqNbListDyn(Criterion cri) throws Exception {			
		return TaAutRole.DAO.reqCount(cri);
	}
}
