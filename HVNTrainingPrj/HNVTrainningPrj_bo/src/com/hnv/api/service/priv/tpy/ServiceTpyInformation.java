package com.hnv.api.service.priv.tpy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefDB;
import com.hnv.api.def.DefJS;
import com.hnv.api.def.DefTime;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.common.APIAuth;
import com.hnv.api.service.common.ResultPagination;
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolDBLock;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDatatable;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.EntityAbstract;
import com.hnv.db.aut.TaAutRole;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.aut.vi.ViAutUserDyn;
import com.hnv.db.nso.TaNsoPost;
import com.hnv.db.sys.TaSysLock;
import com.hnv.db.tpy.TaTpyCategory;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.db.tpy.TaTpyInformation;
import com.hnv.db.tpy.vi.ViTpyCategoryDyn;
import com.hnv.db.tpy.vi.ViTpyInformationDyn;
import com.hnv.def.DefDBExt;
import com.hnv.def.DefRight;


/**
 * ----- ServiceTpyInformation by H&V
 * ----- Copyright 2017------------
 */
public class ServiceTpyInformation implements IService {
	private static	String 			filePath	= null; 
	private	static	String 			urlPath		= null; 

	//--------------------------------Service Definition----------------------------------
	public static final String SV_MODULE 		= "EC_V3".toLowerCase();

	public static final String SV_CLASS 		= "ServiceTpyInformation".toLowerCase();	

	public static final String SV_GET 			= "SVGet"		.toLowerCase();	
	public static final String SV_LST 			= "SVLst"		.toLowerCase();
	public static final String SV_LST_SEARCH	= "SVLstSearch"	.toLowerCase(); 
	public static final String SV_LST_DYN		= "SVLstDyn"	.toLowerCase();
	public static final String SV_LST_PAGE		= "SVLstPage"	.toLowerCase();

	public static final String SV_NEW 			= "SVNew".toLowerCase();	
	public static final String SV_MOD 			= "SVMod".toLowerCase();	
	public static final String SV_DEL 			= "SVDel".toLowerCase();
	
	public static final String SV_DUPLICATE 	= "SVDuplicate".toLowerCase();
	
	public static final String SV_LCK_REQ 		= "SVLckReq".toLowerCase(); //req or refresh	
	public static final String SV_LCK_SAV 		= "SVLckSav".toLowerCase(); //save and continue
	public static final String SV_LCK_END 		= "SVLckEnd".toLowerCase();
	public static final String SV_LCK_DEL 		= "SVLckDel".toLowerCase();

	public static final String SV_LST_CAT 		= "SVLstCat".toLowerCase();
	public static final String SV_LST_GROUP  	= "SVLstGroup".toLowerCase();
	
	public static final Integer	ENT_TYP			= DefDBExt.ID_TA_TPY_INFORMATION;
	//-----------------------------------------------------------------------------------------------
	//-------------------------Default Constructor - Required -------------------------------------
	public ServiceTpyInformation(){
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
		if(filePath == null) {
			filePath 	= API.reqContextParameter("PATH_FILE");
		}
		if (urlPath==null) {
			urlPath 	= API.reqContextParameter("PATH_URL");
		}
	}

	//-----------------------------------------------------------------------------------------------
	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		String 		sv 		= API.reqSVFunctName(json);
		TaAutUser 	user	= (TaAutUser) json.get("userInfo");
		try {
			if(sv.equals(SV_GET) 				&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_GET)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doGet(user, json, response);
			} else if(sv.equals(SV_LST)			&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_GET)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doLst(user, json, response);
			} else if(sv.equals(SV_LST_SEARCH)	&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_GET)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doLstSearch(user, json, response);
			} else if(sv.equals(SV_LST_DYN)		&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_GET)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doLstDyn(user,  json, response);
			} else if(sv.equals(SV_NEW)			&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_NEW)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doNew(user, json, response);
			} else if(sv.equals(SV_MOD)			&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doMod(user, json, response);
			} else  if(sv.equals(SV_DEL)		&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_DEL)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doDel(user, json, response);
			} else if(sv.equals(SV_LCK_REQ)		&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doLckReq(user, json, response);
			} else if(sv.equals(SV_LCK_SAV)		&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doLckSav(user, json, response);
			} else if(sv.equals(SV_LCK_END)		&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doLckEnd(user, json, response);
			} else if(sv.equals(SV_LCK_DEL)		&& (APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD)	||	APIAuth.canAuthorize(user, SV_CLASS, sv))){
				doLckDel(user, json, response);		
			
			} else {
				API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			}
		} catch (Exception e) {
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			e.printStackTrace();
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
			//check something with params
			return true;
		case WORK_UPL : 
			//check something with params
			return true;
		}
		return false;
	}

	
	
	private static CacheData<Map<Integer, TaTpyInformation>> 	cache_lstGrp 	= new CacheData<Map<Integer, TaTpyInformation>>(100, DefTime.TIME_05_00_00_000);	
	private static CacheData<ResultPagination>				cache_pagina 	= new CacheData<ResultPagination>	(100, DefTime.TIME_02_00_00_000);

	//---------------------------------------------------------------------------------------------------------
	private static CacheData<TaTpyInformation> 				cache_ent 		= new CacheData<TaTpyInformation>();		
	private static void doGet(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {	
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");
//
		Integer 			objId		= ToolData.reqInt	(json, "id"				, -1	);				
		Boolean				forced		= ToolData.reqBool	(json, "forced"			, true	);	

		TaTpyInformation 		ent 	= reqGet(objId, forced);

		if (ent==null){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)){
			API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));
	}
	
	private static TaTpyInformation reqGet(Integer id, Boolean forced) throws Exception{
		String key 	= id + "" ;

		TaTpyInformation 		ent 	= cache_ent.reqData(key);

		if (forced || ent == null) {
			ent 	= TaTpyInformation.DAO.reqEntityByRef(id);
			if (ent!=null) {
				//---do something and put to cache
				cache_ent.reqPut(key, ent);
			}
		}else {				
			ToolLogServer.doLogInf("---reqNsoAreaGet use cache-----");
			cache_ent.reqCheckIfOld(key); //cache in 20 hour
		}
		if(ent==null||forced) {
			ent = TaTpyInformation.DAO.reqEntityByID(id);
			
			if(ent==null) return ent;
			
			cache_ent.reqPut(key, ent);
		} else {
			cache_ent.reqCheckIfOld(key);
		}
		//---do build something other of ent like details....
		return ent;
	}
	//--------------------------------------------------------------------------------------------------------------
	private static CacheData<List<TaTpyInformation>> 			cache_lst 		= new CacheData<List<TaTpyInformation>>();	
	private static void doLst(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {	
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Set<Integer>		ids			= ToolData.reqSetInt(json, "ids"			, null	);	
		Set<String>			codes		= ToolData.reqSetStr(json, "codes"			, null	);	
		Boolean				forced		= ToolData.reqBool	(json, "forced"			, false	);
		

		List<TaTpyInformation>		ent 	= reqLst(ids, codes, forced);

		if (ent==null){
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)){
			API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));

	}
	
	private static List<TaTpyInformation>	 reqLst(Set<Integer> ids, Set<String> codes , Boolean forced ) throws Exception{
		String key 	= ids + "";

		List<TaTpyInformation> lst 	= cache_lst.reqData(key);

		if(lst==null||forced) {
			if (ids!=null) 
				lst = TaTpyInformation.DAO.reqList_In(TaTpyInformation.ATT_I_ID, ids);
			else if (codes!=null)
				lst = TaTpyInformation.DAO.reqList_In(TaTpyInformation.ATT_T_INFO_01, codes);
			else return null;
			
			if (lst!=null &&  lst.size()>0) cache_lst.reqPut(key, lst);
		} else {
			cache_lst.reqCheckIfOld(key);
		}
		//---do build something other of ent like details....
		return lst;
	}
	
	//---------------------------------------------------------------------------------------------------------
	private static CacheData<List<ViTpyInformationDyn>> cache_VI = new CacheData<List<ViTpyInformationDyn>>(100, DefTime.TIME_01_00_00_000);	
	private static void  doLstSearch(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		//----sử dụng ViTpyInformationDyn để đơn giản dữ liệu lấy từ CSDL
		
		
		
		String				searchkey	= ToolData.reqStr	(json, "searchkey"	, "%");// Integer.parseInt(request.getParameter("typ01")); 	
		Integer 			typ01 		= ToolData.reqInt 	(json, "typ01"		, null);
		Integer 			inf01 		= ToolData.reqInt 	(json, "inf01"		, null);
		Integer 			inf02 		= ToolData.reqInt 	(json, "inf02"		, null);
		Integer				nbLineMax	= ToolData.reqInt	(json, "nbLine"		, 10 );
		
		String 				keyWord 	= searchkey + "_" + typ01  ;
		List<ViTpyInformationDyn> list		= cache_VI.reqData(keyWord);
		
		if (list==null){
			Criterion cri	= null;
			cri 			= Restrictions.like(ViTpyInformationDyn.ATT_T_INFO_01 , "%"+searchkey+"%");
			cri				= Restrictions.and(cri, 
					Restrictions.eq(ViTpyInformationDyn.ATT_T_INFO_01		, inf01),
					Restrictions.eq(ViTpyInformationDyn.ATT_T_INFO_02		, inf02)
					
					);
			
			list = ViTpyInformationDyn.DAO.reqList(0, nbLineMax, Order.asc(ViTpyInformationDyn.ATT_T_INFO_01), cri);
			cache_VI.reqPut(keyWord, list);	
		} else {
			cache_VI.reqCheckIfOld(keyWord); //cache in 1 hour
		}
		
		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, list
				));	
	}

	private static Hashtable<String,Integer> mapCol = new Hashtable<String, Integer>(){
		{
			put("action", -1);
			put("id"	, 0 );
			put("dt01"  , 1);
            put("inf01" , 2);
		}
	};
	
	private static void doLstDyn(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception {	
		Integer 			typ01 			= ToolData.reqInt (json, "typ01"		, null);
		Integer 			entTyp 			= ToolData.reqInt (json, "entTyp"		, null);
		Integer 			entId 			= ToolData.reqInt (json, "entId"		, null);
		Object[]  			dataTableOption = ToolDatatable.reqDataTableOption (json, mapCol);
		Set<String>		    searchKey		= (Set<String>)dataTableOption[0];	

		if(typ01 == null) {
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		
		if (!canWorkWithObj(user, WORK_LST, null, null)){ //other param after objTyp...
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}

		Criterion 	cri 				= reqRestriction(typ01,entTyp,entId, searchKey);				

		List<ViTpyInformationDyn> list 	= reqListDyn(dataTableOption, cri);		
		if (list==null ){
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		} 

		Integer iTotalRecords 			= reqNbListDyn().intValue();				
		Integer iTotalDisplayRecords 	= reqNbListDyn(cri).intValue();

	
		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"iTotalRecords"				, iTotalRecords,
				"iTotalDisplayRecords"		, iTotalDisplayRecords,
				"aaData"					, list
				));

	}
	
	private static Criterion reqRestriction(int typ01,Integer entTyp,Integer entId, Set<String> searchKey ) throws Exception {		

		Criterion cri = Restrictions.eq(ViTpyInformationDyn.ATT_I_TYPE_01, typ01);
		if(entTyp != null) {
			cri = Restrictions.and(cri,Restrictions.eq(ViTpyInformationDyn.ATT_I_ENTITY_TYPE, entTyp));
		}
		if(entId != null) {
			cri = Restrictions.and(cri,Restrictions.eq(ViTpyInformationDyn.ATT_I_ENTITY_ID, entId));
		}
		for (String s : searchKey){
			if (cri==null)
				cri = 	Restrictions.or(Restrictions.ilike(ViTpyInformationDyn.ATT_T_INFO_01	, s), 
										Restrictions.ilike(ViTpyInformationDyn.ATT_T_INFO_02	, s));

			else
				cri = 	Restrictions.and(cri, 
						Restrictions.or(Restrictions.ilike(ViTpyInformationDyn.ATT_T_INFO_01		, s), 
										Restrictions.ilike(ViTpyInformationDyn.ATT_T_INFO_02		, s))
						);
		}


		return cri;
	}

	private static List<ViTpyInformationDyn> reqListDyn(Object[] dataTableOption, Criterion 	cri) throws Exception {		
		int 		begin 		= (int)	dataTableOption[1];
		int 		number 		= (int)	dataTableOption[2]; 
		int 		sortCol 	= (int)	dataTableOption[3]; 
		int 		sortTyp 	= (int)	dataTableOption[4];	

		List<ViTpyInformationDyn> list 	= new ArrayList<ViTpyInformationDyn>();		

		Order 	order 	= null;			
		String 	colName = ViTpyInformationDyn.ATT_D_DATE_01;

		switch(sortCol){
		case 0: colName = ViTpyInformationDyn.ATT_I_ID; break;		
		case 1: colName = ViTpyInformationDyn.ATT_D_DATE_01; break;	
		case 2: colName = ViTpyInformationDyn.ATT_T_INFO_01; break;	
		}

		if (colName!=null){
			switch(sortTyp){
			case 0: order = Order.asc(colName); break;
			case 1: order = Order.desc(colName); break;	
			}
		}

		if (order==null)
			list	= ViTpyInformationDyn.DAO.reqList(begin, number, cri);
		else
			list	= ViTpyInformationDyn.DAO.reqList(begin, number, order, cri);			

		return list;
	}

	private static Number reqNbListDyn() throws Exception {						
		return ViTpyInformationDyn.DAO.reqCount();		
	}
	
	private static Number reqNbListDyn(Criterion cri) throws Exception {			
		return ViTpyInformationDyn.DAO.reqCount(cri);
	}
	
	//---------------------------------------------------------------------------------------------------------
	private static void doNew(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doNew --------------");
		JSONObject			obj		= ToolData.reqJson (json, "obj", null);
		
		Boolean 	existTpyInf 	= doCheckCode(obj);
		if(existTpyInf){
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			return;
		}
		TaTpyInformation 			ent		= reqNew		(user, json);
		
		if (ent==null){
			API.doResponse(response, ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO					
					));
			return;
		}
		
		TaSysLock 	lock 	= ToolDBLock.reqLock (json, "lock", DefDB.DB_LOCK_NEW, ENT_TYP, (Integer)ent.req(TaNsoPost.ATT_I_ID), user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent,
				"lock"				, lock
				));
		
	}
	private static Boolean doCheckCode(JSONObject obj) throws Exception {
		// TODO Auto-generated method stub
		String numver = obj.get("inf01").toString().trim();
		if(numver.equals("") || numver == null)	return false;
		TaTpyInformation cat 	= TaTpyInformation.DAO.reqEntityByValue(TaTpyInformation.ATT_T_INFO_01, numver);
		if(cat != null)
			return true;
		else
			return false;
	}
	private static TaTpyInformation reqNew(TaAutUser user,  JSONObject json) throws Exception {
		JSONObject				obj		= ToolData.reqJson (json, "obj", null);

		if (!canWorkWithObj(user, WORK_NEW, obj)){ //other param after obj...
			return null;
		}

		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaTpyInformation.class);

		attr.put(TaTpyInformation.ATT_I_ENTITY_TYPE, 0);
		attr.put(TaTpyInformation.ATT_I_ENTITY_ID, 0);
		attr.put(TaTpyInformation.ATT_I_TYPE_01, 1);

		TaTpyInformation  		ent	 	= new TaTpyInformation(attr);
		TaTpyInformation.DAO.doPersist(ent);

		return ent;
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doMod(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doMod --------------");
		
		TaTpyInformation  		ent	 		= reqMod(user, json); 
		
		if (ent==null){
			API.doResponse(response,DefAPI.API_MSG_KO);
		} else {
			API.doResponse(response, ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));	
		}		
	}

	private static TaTpyInformation reqMod(TaAutUser user,  JSONObject json) throws Exception {
		JSONObject			obj		= ToolData.reqJson (json, "obj", null);
		int					entId 	= ToolData.reqInt(obj, "id", null);
		TaTpyInformation  		ent	 	= TaTpyInformation.DAO.reqEntityByRef(entId);
		if (ent==null){
			return null;
		}

		if (!canWorkWithObj(user, WORK_MOD, ent)){ //other param after obj...
			return null;
		}

		Map<String, Object> attr 		= API.reqMapParamsByClass(obj, TaTpyInformation.class);
		String 				newCode 	= ToolData.reqStr  (obj	, "inf01", null);
		if(!ent.req(TaTpyInformation.ATT_T_INFO_01).equals(newCode)){
			Boolean existCode = doCheckCode(obj);
			if(existCode){
				attr.remove(TaTpyInformation.ATT_T_INFO_01);
			}
		}

		TaTpyInformation.DAO.doMerge(ent, attr);

		//---------------------------------------------------------
		String key 	= entId + "_" + true + "_" + true + "_" + false;
		cache_ent.reqPut(key, ent);
		
		return ent;
	}	

	//---------------------------------------------------------------------------------------------------------
	private static void doDel(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doDel --------------");

		//--chk lock-----------------------------------------------------------------------------------------------------------------
		int				entId 	= ToolData.reqInt(json, "id", null);
		TaSysLock 		lock 	= ToolDBLock.reqLock(ENT_TYP, entId, user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);

		if ((Integer)lock.req(TaSysLock.ATT_I_STATUS) == 0){
			API.doResponse(response, ToolJSON.reqJSonString(						
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO,
					DefJS.RES_DATA		, lock
					));	
			return;
		}
		//----------------------------------------------------------------------------------------------------------------------
		boolean		ok		= canTpyInformationDel(user, json); 				
		if (!ok){
			API.doResponse(response,DefAPI.API_MSG_KO);
		} else {
			API.doResponse(response, ToolJSON.reqJSonString(DefJS.SESS_STAT, 1,	DefJS.SV_CODE, DefAPI.SV_CODE_API_YES));
		}		
	}
	private static boolean canTpyInformationDel(TaAutUser user,  JSONObject json) throws Exception {
		Integer 		entId	= ToolData.reqInt	(json, "id"		, -1	);	
		TaTpyInformation  	ent	 	= TaTpyInformation.DAO.reqEntityByRef(entId);
		if (ent==null){
			return false;
		}

		if (!canWorkWithObj(user, WORK_DEL, ent)){ //other param after ent...
			return false;
		}		

		//remove all other object connecting to this ent first-------
		Session sess  	= TaTpyInformation	.DAO.reqSessionCurrent();
		try {

			TaTpyInformation		.DAO.doRemove (sess, ent);

			cache_ent.reqDel(entId+"");
			TaTpyInformation			.DAO.doSessionCommit(sess);
		}catch(Exception e){
			TaTpyInformation		.DAO.doSessionRollback(sess);
			e.printStackTrace();
		}
		return true;
	}

	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private void doLckReq(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckReq --------------");		
		Integer 		entId	= ToolData.reqInt	(json, "id", null);	
		TaSysLock 		lock 	= ToolDBLock.reqLock(ENT_TYP, entId, user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		if (lock==null || lock.reqStatus() == 0){
			API.doResponse(response, ToolJSON.reqJSonString(						
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO,
					DefJS.RES_DATA		, lock
					));	
		}else{
			API.doResponse(response, ToolJSON.reqJSonString(						
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, lock
					));	
		}			

	}

	private void doLckDel(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckDel --------------");
		boolean isDeleted = ToolDBLock.canDeleteLock(json);		
		if (isDeleted)
			API.doResponse(response, ToolJSON.reqJSonString(		
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES
				));		
		else 
			API.doResponse(response, ToolJSON.reqJSonString(		
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO
					));
	}
	
	private void doLckSav(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckSav --------------");	

		//--chk lock-----------------------------------------------------------------------------------------------------------------
		boolean isLocked 	= ToolDBLock.canExistLock(json);
		if(!isLocked){
			API.doResponse(response, DefAPI.API_MSG_ERR_LOCK);
			return;
		}

		TaTpyInformation  		ent	 	=  reqMod(user, json); 								
		if (ent==null){
			API.doResponse(response,DefAPI.API_MSG_KO);
		} else {
			API.doResponse(response, ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));	
		}
	}

	//user when modify object with lock
	private void doLckEnd(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckEnd --------------");	

		//--chk lock-----------------------------------------------------------------------------------------------------------------
		boolean isLocked 	= ToolDBLock.canExistLock(json);
		if(!isLocked){
			API.doResponse(response, DefAPI.API_MSG_ERR_LOCK);
			return;
		}
		
		TaTpyInformation  		ent	 		= reqMod(user, json); 						
		if (ent==null){
			API.doResponse(response,DefAPI.API_MSG_KO);
		} else {
			ToolDBLock.canDeleteLock(json);
			API.doResponse(response, ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));	
		}				
	}
}
