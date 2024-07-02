package com.hnv.api.service.sub.aut;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefDB;
import com.hnv.api.def.DefDBExt;
import com.hnv.api.def.DefJS;
import com.hnv.api.def.DefRight;
import com.hnv.api.def.DefTime;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.sub.APIAuth;
import com.hnv.api.service.sub.ResultPagination;
import com.hnv.common.tool.ToolDBLock;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDatatable;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.tool.ToolSet;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutAuthorization;
import com.hnv.db.aut.TaAutRole;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.per.TaPerPerson;
import com.hnv.db.sys.TaSysLock;
import com.hnv.db.tpy.TaTpyDocument;

/**
 * ----- ServiceNsoPost by H&V
 * ----- Copyright 2017------------
 */
public class ServiceAutUser implements IService {
	//--------------------------------Service Definition----------------------------------
	public static final String SV_MODULE 				= "EC_V3".toLowerCase();

	public static final String SV_CLASS 				= "ServiceAutUser".toLowerCase();	

	public static final String SV_GET 					= "SVGet".toLowerCase();	
	public static final String SV_LST 					= "SVLst".toLowerCase();
	public static final String SV_LST_DYN				= "SVLstDyn".toLowerCase(); 

	

	public static final String SV_NEW 					= "SVNew".toLowerCase();	
	public static final String SV_MOD 					= "SVMod".toLowerCase();	
	public static final String SV_DEL 					= "SVDel".toLowerCase();
	
	public static final String SV_LCK_REQ 				= "SVLckReq".toLowerCase(); //req or refresh	
	public static final String SV_LCK_SAV 				= "SVLckSav".toLowerCase(); //save and continue
	public static final String SV_LCK_END 				= "SVLckEnd".toLowerCase();
	public static final String SV_LCK_DEL 				= "SVLckDel".toLowerCase();
	
	public static final String SV_UPDATE_STATUS 		= "SVUpdateStat".toLowerCase();
	public static final String SV_GET_FILE 				= "SVGetFile".toLowerCase();	
	public static final String SV_MOD_TRANSL			= "SVModTransl".toLowerCase();	


	//-----------------------------------------------------------------------------------------------
	//-------------------------Default Constructor - Required -------------------------------------
	public ServiceAutUser(){
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
	}

	//-----------------------------------------------------------------------------------------------

	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		String 		sv 		= API.reqSVFunctName(json);
		TaAutUser 	user	= (TaAutUser) json.get("userInfo");
		
		try {
			//---------------------------------------------------------------------------------

			if(sv.equals(SV_GET) 						&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doGet(user,  json, response);

			} else if(sv.equals(SV_LST)					&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLst(user,  json, response);
			} else if(sv.equals(SV_LST_DYN)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLstDyn(user,  json, response);

			} else if(sv.equals(SV_NEW)					&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_N)){
				doNew(user,  json, response);
			} else if(sv.equals(SV_MOD)					&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doMod(user,  json, response);
			} else  if(sv.equals(SV_DEL)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doDel(user,  json, response);

			}else  if(sv.equals(SV_UPDATE_STATUS)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
				doUpdateStatus(user,  json, response);

			} else if(sv.equals(SV_LCK_REQ)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
				doLckReq(user,  json, response);
			} else if(sv.equals(SV_LCK_SAV)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
				doLckSav(user,  json, response);
			} else if(sv.equals(SV_LCK_END)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
				doLckEnd(user,  json, response);
			} else if(sv.equals(SV_LCK_DEL)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
				doLckDel(user,  json, response);		

			
			} else {
				API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_ERR_RIGHT));
			}	


		}catch(Exception e){
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_ERR_API
					));
			e.printStackTrace();
			return;
		}			
	}
	//---------------------------------------------------------------------------------------------------------

	private static final int WORK_GET 	= 1;
	private static final int WORK_LST 	= 2;
	private static final int WORK_NEW 	= 3;
	private static final int WORK_MOD 	= 4;
	private static final int WORK_DEL 	= 5;
	private static final int WORK_UPL 	= 10; //upload

	private static boolean canWorkWithObj ( TaAutUser user, int typWork, Object...params){
		if (user.canBeSuperAdmin()) return true;
		
		switch(typWork){
		case WORK_GET : 
			//check something with params
			return true;
		case WORK_LST : 
			//check something with params
			return true;
		case WORK_NEW : 
			//check something with params
			return true;
		case WORK_MOD : 
			//check something with params
			return true;	
		case WORK_DEL : 
			if (params==null || params.length<=0) return false; 
//			TaAutUser post 	= (TaAutUser) params[0];
//			Integer   uId 	= post.reqInt(post, TaAutUser.ATT_I_AUT_USER_NEW);
//			if (uId!=null && uId.equals(user.reqUserId())) return true;
//			return false;
			
			return true;
		case WORK_UPL : 
			//check something with params
			return true;
		}
		return false;
	}
	//---------------------------------------------------------------------------------------------------------
	private static Set<String> filter = new HashSet<String>();
	static {
		filter.add(TaAutUser.class.getSimpleName()+"."+TaAutUser.ATT_T_PASS_01);
		filter.add(TaAutUser.class.getSimpleName()+"."+TaAutUser.ATT_T_PASS_02);
	}
	//---------------------------------------------------------------------------------------------------------
	private static void doGet(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {	
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Integer 			objId		= ToolData.reqInt	(json, "id"			, -1	);				
		Boolean				forced		= ToolData.reqBool	(json, "forced"		, false	);
//		Boolean				forManager	= ToolData.reqBool	(json, "forManager"	, false	);

		TaAutUser 			ent 		= reqGet(objId, forced);

		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_ERR_RIGHT));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(
				filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));



	}
	
	private static CacheData<TaAutUser> 		cache_entity= new CacheData<TaAutUser>		(500, DefTime.TIME_SLEEP_24_00_00_000 );
	public static TaAutUser reqGet(Integer objId, Boolean forced) throws Exception{
		String 			key		= objId+"";
		TaAutUser 		ent 	= cache_entity.reqData(key);	
		
		if (forced || ent == null) {
			ent 	= TaAutUser.DAO.reqEntityByRef(objId);
			if (ent!=null) {
				cache_entity.reqPut(key, ent);
			}
		}else {				
			ToolLogServer.doLogInf("---reqNsoAreaGet use cache-----");
			cache_entity.reqCheckIfOld(key); //cache in 20 hour
		}


		//---do build something other of ent like details....
		if (ent!=null){		
			ent.doBuildDocuments(forced);
			ent.doBuildPerson(forced);
			ent.doBuildSuperior(forced);
			ent.doBuilRoleRighs(forced);
		}

		return ent;
	}
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private static void doLst(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		List<TaAutUser> 	list = reqLst(user, json); //and other params if necessary
		if (list==null || list.size()==0){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		
				filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, list 
				));				
	}

	private static List<TaAutUser> reqLst(TaAutUser user,  JSONObject json) throws Exception {
		Integer 			objMan		= ToolData.reqInt	(json, "manId"	, null	);
		Integer 			objTyp		= ToolData.reqInt	(json, "typ"		, null	);
		Boolean 			objContBuild= ToolData.reqBool	(json, "withBuild"	, false	);

		//other params here

		if (!canWorkWithObj(user, WORK_LST, objTyp)){ //other param after objTyp...
			return null;
		}

		Criterion 			cri		= reqCriterion (objTyp); //and other params	
		List<TaAutUser> 	list 	= null;
		if (cri==null) 
			list =   TaAutUser.DAO.reqList();
		else
			list =   TaAutUser.DAO.reqList(cri);


		//do something else with request
		if (objContBuild){

		}

		return list;
	}

	private static Criterion reqCriterion(Object...params) throws Exception{
		if (params==null || params.length==0) return null;

		Criterion cri = Restrictions.gt("I_ID", 0);	

		if (params!=null && params.length>0){
			//int type 	= (int) params[0];
			//cri 		= Restrictions.and(cri, Restrictions.eqOrIsNull(TaAutUser.ATT_I_TYPE, type));
		}			

		if (params!=null && params.length>1){
			//do something
		}

		return cri;
	}

	
	//---------------------------------------------------------------------------------------------------------		

	private static Long countAllEle = null;
	private static void doLstDyn(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception {	
		Object[]  			dataTableOption = ToolDatatable.reqDataTableOption (json, null);
		List<String>		searchKey		= (List<String>)dataTableOption[0];	
		Integer				stat			= ToolData.reqInt	(json, "stat"	, null);
		Integer				typ				= ToolData.reqInt	(json, "type"	, null);
		Set<Integer>		typMulti		= ToolData.reqSetInt(json, "typMulti", null);
		
		Set<Integer>		objTypMult		= new HashSet<Integer>() ;
		if(typ == null && typMulti  == null) {
//			objTypMult.add(TaAutUser.POST_TYPE_BLOG);
		}else if (typMulti  != null) {
			objTypMult = typMulti;
		}else if(typ!=null ){
			objTypMult.add(typ);
		}

		if (!canWorkWithObj(user, WORK_LST, null, stat)){ //other param after objTyp...
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		Criterion 	cri 			= reqRestriction(searchKey, stat, objTypMult);				

		List<TaAutUser> list 		= reqListDyn(dataTableOption, cri);		
		if (list==null ){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		} else {
//			for(TaAutUser u : list) {
//				p.doBuildUserLogin(true);
//			}
		}

		if (countAllEle==null)
			countAllEle = ((long)reqNbNsoPostListDyn());

		Integer iTotalRecords 			= (countAllEle.intValue());				
		Integer iTotalDisplayRecords 	= reqNbNsoPostListDyn(cri).intValue();


		API.doResponse(response,ToolJSON.reqJSonString(		
				filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"iTotalRecords"				, iTotalRecords,
				"iTotalDisplayRecords"		, iTotalDisplayRecords,
				"aaData"					, list
				));

	}

	private static Criterion reqRestriction(List<String> searchKey, Integer stat, Set<Integer> types) throws Exception {		
		Criterion cri = null;
		if(stat != null){
			cri = Restrictions.eq(TaAutUser.ATT_I_STATUS, stat);
		}

		for (String s : searchKey){
			if (cri==null)
				cri = 	Restrictions.or(Restrictions.ilike(TaAutUser.ATT_T_LOGIN_01, s), Restrictions.ilike(TaAutUser.ATT_T_LOGIN_02, s));

			else
				cri = 	Restrictions.and(	cri, 
						Restrictions.or(Restrictions.ilike(TaAutUser.ATT_T_LOGIN_01, s), Restrictions.ilike(TaAutUser.ATT_T_LOGIN_02, s))
						);
		}

		if(types!=null) {
			cri = Restrictions.and(	cri, 
					Restrictions.in(TaAutUser.ATT_I_TYPE_01 , types)
					);
		}

		return cri;
	}

	private static List<TaAutUser> reqListDyn(Object[] dataTableOption, Criterion 	cri) throws Exception {		
		int 		begin 		= (int)	dataTableOption[1];
		int 		number 		= (int)	dataTableOption[2]; 
		int 		sortCol 	= (int)	dataTableOption[3]; 
		int 		sortTyp 	= (int)	dataTableOption[4];	

		List<TaAutUser> list 	= new ArrayList<TaAutUser>();		

		Order 	order 	= null;			
		String 	colName = null;

		switch(sortCol){
		case 0: colName = TaAutUser.ATT_I_ID; break;		
		case 1: colName = TaAutUser.ATT_T_LOGIN_01; break;			
		case 2: colName = TaAutUser.ATT_T_INFO_01; break;		
		}

		if (colName!=null){
			switch(sortTyp){
			case 0: order = Order.asc (colName); break;
			case 1: order = Order.desc(colName); break;								
			}
		}

		if (order==null)
			list	= TaAutUser.DAO.reqList(begin, number, cri);
		else
			list	= TaAutUser.DAO.reqList(begin, number, order, cri);			

		return list;
	}

	private static Number reqNbNsoPostListDyn() throws Exception {						
		return TaAutUser.DAO.reqCount();		
	}
	
	private static Number reqNbNsoPostListDyn(Criterion cri) throws Exception {			
		return TaAutUser.DAO.reqCount(cri);
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doNew(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		TaAutUser 			ent		= reqNew		(user, json);
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO					
					));
			return;
		}

		TaSysLock 	lock 	= ToolDBLock.reqLock (json, "lock", DefDB.DB_LOCK_NEW, DefDBExt.ID_TA_, (Integer)ent.req(TaAutUser.ATT_I_ID), user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent,
				"lock"				, lock
				));
	}

	private static TaAutUser reqNew(TaAutUser user,  JSONObject json) throws Exception {
		Integer			userId		= user.reqId();
		JSONObject		obj			= ToolData.reqJson 		 (json	, "obj"			, null);
//		Integer 		perType		= ToolData.reqInt 		 (obj	, "perType"		, 1);
		
		
		JSONObject			per		= (JSONObject) obj.get("per");		
		if (per!=null){	
			per.put("typ01", TaPerPerson.TYP_01_NATURAL);
			
			//check mail first //	//---send email for validate
			Map<String, Object> 	attrPer		= API.reqMapParamsByClass(per, TaPerPerson.class);				
			TaPerPerson				perEnt	 	= new TaPerPerson(attrPer);
			perEnt.reqSet(TaPerPerson.ATT_I_ID			, null);
			perEnt.reqSet(TaPerPerson.ATT_I_STATUS		, TaPerPerson.STAT_ACTIVE_LV_01);
			perEnt.reqSet(TaPerPerson.ATT_D_DATE_01		, new Date());
			perEnt.reqSet(TaPerPerson.ATT_I_AUT_USER_01	, user.reqId());
			
			TaPerPerson.DAO.doPersist(perEnt);
			//--------------------------------------------------------------------------------------------
			Map<String, Object> attrUsr = API.reqMapParamsByClass(obj	, TaAutUser.class);
			attrUsr.put(TaAutUser.ATT_D_DATE_01	, new Date());
			attrUsr.put(TaAutUser.ATT_I_AUT_USER_01, userId);
			attrUsr.put(TaAutUser.ATT_D_DATE_02	, null);
			
			TaAutUser ent				= new TaAutUser(attrUsr);	
			ent.reqSet(TaAutUser.ATT_I_PER_PERSON, perEnt.reqId());
			ent.reqSet(TaAutUser.ATT_O_PER_PERSON, perEnt);
			
			TaAutUser.DAO.doPersist(ent);
			
			//----set date validation if user is visistor.....
			int 		entId		= perEnt.reqId();
			int 		entTyp		= DefDBExt.ID_TA_PER_PERSON;
			JSONArray 	docs 		= (JSONArray) per.get("files");
			perEnt.reqSet(TaPerPerson.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(ent, entTyp, entId, docs, DefAPI.SV_MODE_MOD));
			
						entId		= ent.reqId();
						entTyp		= DefDBExt.ID_TA_AUT_USER;
						docs 		= (JSONArray) obj.get("files");
			ent.reqSet(TaAutUser.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(ent, entTyp, entId, docs, DefAPI.SV_MODE_MOD));
			
			
			//---persist authorization
			Set<Integer> 				roles 		= ToolSet.reqSetInt(obj.get("roles").toString());
			List<TaAutRole> 			lstRoles 	= TaAutRole.DAO.reqList_In(TaAutRole.ATT_I_ID, roles);
			List<TaAutAuthorization> 	lstAuth 	= new ArrayList<TaAutAuthorization>();
			for (TaAutRole r: lstRoles) {
				TaAutAuthorization auth = new TaAutAuthorization(entId, r.reqId(), r.reqStr(TaAutRole.ATT_T_AUT_RIGHT));
			}
			TaAutAuthorization.DAO.doPersist(lstAuth);
			
			return ent;
		}

		

		return null;
	}
	
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private static void doMod(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doMod --------------");

		TaAutUser  		ent	 	=  reqMod(user, json); 								
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

	private static TaAutUser reqMod(TaAutUser user,  JSONObject json) throws Exception {
		JSONObject			obj		= ToolData.reqJson 	(json	, "obj"	, null);
		int 				objId 	= ToolData.reqInt	(obj	, "id"	, -1);
		TaAutUser 			ent 	= TaAutUser.DAO.reqEntityByRef(objId);
		
		if (ent==null){
			Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaAutUser.class);			
			attr.remove(TaAutUser.ATT_I_ID);
			attr.remove(TaAutUser.ATT_T_LOGIN_01);
			attr.remove(TaAutUser.ATT_T_LOGIN_02);
			attr.remove(TaAutUser.ATT_I_PER_PERSON);
			attr.remove(TaAutUser.ATT_I_AUT_USER_01);
			attr.remove(TaAutUser.ATT_D_DATE_01);
			
			attr.put(TaAutUser.ATT_D_DATE_02	, new Date());
			attr.put(TaAutUser.ATT_I_AUT_USER_02, user.reqId());
			
			TaAutUser.DAO.doMerge(ent, attr);	
			
			//---merge authorization
			String					strRoles	= obj.get("roles").toString();
			TaAutAuthorization.reqListCheck(objId, strRoles);
			
			//---merger person
			JSONObject				per			= (JSONObject) obj.get("per");	
			Map<String, Object> 	attrPer		= API.reqMapParamsByClass(per, TaPerPerson.class);				
			attr.remove(TaAutUser.ATT_I_ID);
			attr.remove(TaAutUser.ATT_D_DATE_01);
			attr.remove(TaAutUser.ATT_I_AUT_USER_01);
			
			TaPerPerson				perEnt	 	= new TaPerPerson(attrPer);
			TaPerPerson.DAO.doPersist(perEnt);
			
			//merge files for user
			JSONArray				docs		= (JSONArray) obj.get("files");	
			int 					entId		= ent.reqId();
			int 					entTyp		= DefDBExt.ID_TA_AUT_USER;
			ent.reqSet(TaAutUser.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(ent, entTyp, entId, docs, DefAPI.SV_MODE_MOD));
			
			//merge files for person
									docs		= (JSONArray) per.get("files");	
									entId		= ent.reqId();
									entTyp		= DefDBExt.ID_TA_PER_PERSON;
			ent.reqSet(TaAutUser.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(ent, entTyp, entId, docs, DefAPI.SV_MODE_MOD));
		}

		return ent;
	}


	//---------------------------------------------------------------------------------------------------------
	private static void doDel(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doDel --------------");

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

	private static boolean canDel(TaAutUser user,  JSONObject json) throws Exception {
		Integer 	entId	= ToolData.reqInt	(json, "id", null	);	
		Integer		entTyp	= DefDBExt.ID_TA_NSO_POST;
		TaAutUser  	ent	 	= TaAutUser.DAO.reqEntityByRef(entId);
		if (ent==null){
			return false;
		}

		Integer stat = ent.reqInt(ent, TaAutUser.ATT_I_STATUS);
		if (stat.equals(TaAutUser.STAT_DELETED)) {
			if (!canWorkWithObj(user, WORK_DEL, ent)){ //other param after ent...
				return false;
			}	
			//remove all other object connecting to this ent first-------

//			Session sessSub 	= TaTpyDocument	.DAO.reqSessionCurrent();
			Session sessMain 	= TaAutUser		.DAO.reqSessionCurrent();
			try {
				TaTpyDocument		.doListDel	(sessMain, entTyp, entId);
//				TaTpyDocument		.doListDel(sessSub, entTyp, entId);
				
				TaAutAuthorization	.doListDel	(sessMain, entId);
				
				TaAutUser.DAO		.doRemove 	(sessMain, ent);
				
				TaAutUser			.DAO.doSessionCommit(sessMain);
//				TaTpyDocument		.DAO.doSessionCommit(sessSub);
			}catch(Exception e){
				e.printStackTrace();
				TaAutUser			.DAO.doSessionRollback(sessMain);
//				TaTpyDocument		.DAO.doSessionRollback(sessSub);
			}		
		} else {
			//Set status = -1
			ent.reqSet(TaAutUser.ATT_I_STATUS, TaAutUser.STAT_DELETED);
			TaAutUser.DAO.doMerge(ent);	
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
		if (lock==null || lock.reqStatus() == 0){
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
		
		TaAutUser  		ent	 	=  reqMod(user, json); 								
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
		}
		
		
		TaAutUser ent = reqMod(user, json);						
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
	//---------------------------------------------------------------------------------------------------------

	private static void doUpdateStatus(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception {
		int 			stat	= ToolData.reqInt	(json, "stat", -1);
		int				postId	= ToolData.reqInt	(json, "postId", -1);

		if(stat == -1 || postId == -1) {
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO					
					));
			return;
		}

		TaAutUser p = TaAutUser.DAO.reqEntityByRef(postId);
		p.reqSet(TaAutUser.ATT_I_STATUS, stat);
		p.reqSet(TaAutUser.ATT_D_DATE_02, new Date());
		TaAutUser.DAO.doMerge(p);
		//		p.doBuildAll(true);
		p.doBuildDocuments(true);

		API.doResponse(response,ToolJSON.reqJSonString(
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, p
				));
	}

	

	//--cache for UI public
	private static CacheData<ResultPagination>		cacheEnt_rs 	= new CacheData<ResultPagination>(100, DefTime.TIME_SLEEP_02_00_00_000);
	//------------function get list post by entType and entId-----------------------------------------------
	//---------------------------------------------------------------------------------------------------------


}
