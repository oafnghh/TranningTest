package com.hnv.api.service.sub.nso;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDatatable;
import com.hnv.common.tool.ToolDate;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.tool.ToolSet;
import com.hnv.common.tool.ToolString;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.nso.TaNsoPost;
import com.hnv.db.nso.ViNsoPost;
import com.hnv.db.sys.TaSysLock;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;

/**
 * ----- ServiceNsoPost by H&V
 * ----- Copyright 2017------------
 */
public class ServiceNsoPost implements IService {
	private static	String 			filePath	= null; 
	private	static	String 			urlPath		= null; 

	//--------------------------------Service Definition----------------------------------
	public static final String SV_MODULE 				= "EC_V3".toLowerCase();
	public static final String SV_CLASS 				= "ServiceNsoPost".toLowerCase();	

	public static final String SV_GET 					= "SVGet".toLowerCase();	
	public static final String SV_GET_SIMPLE			= "SVGetSimple".toLowerCase();	

	public static final String SV_LST 					= "SVLst".toLowerCase();
	public static final String SV_LST_DYN				= "SVLstDyn".toLowerCase(); 

	public static final String SV_UPDATE_STATUS 		= "SVUpdateStat".toLowerCase();

	public static final String SV_GET_FILE 				= "SVGetFile".toLowerCase();	

	public static final String SV_NEW 					= "SVNew".toLowerCase();	
	public static final String SV_MOD 					= "SVMod".toLowerCase();	
	public static final String SV_DEL 					= "SVDel".toLowerCase();
	

	public static final String SV_MOD_TRANSL			= "SVModTransl".toLowerCase();	

	public static final String SV_LCK_REQ 				= "SVLckReq".toLowerCase(); //req or refresh	
	public static final String SV_LCK_SAV 				= "SVLckSav".toLowerCase(); //save and continue
	public static final String SV_LCK_END 				= "SVLckEnd".toLowerCase();
	public static final String SV_LCK_DEL 				= "SVLckDel".toLowerCase();

	public static final String SV_LST_NEWS		        = "SVLstNews".toLowerCase();
	public static final String SV_LST_SEARCH		    = "SVLstSearch".toLowerCase();
	public static final String SV_LST_TOP_VIEW		    = "SVLstTopView".toLowerCase();


	private static Set<String> filter = new HashSet<String>();

	//-----------------------------------------------------------------------------------------------
	//-------------------------Default Constructor - Required -------------------------------------
	public ServiceNsoPost(){
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
		
		if (filePath	==null) filePath		= API.reqContextParameter("NSO_POST_PATH_FILE");
		if (urlPath		==null) urlPath			= API.reqContextParameter("NSO_POST_PATH_URL");	
	}

	//-----------------------------------------------------------------------------------------------

	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		String 		sv 		= API.reqSVFunctName(json);
		TaAutUser 	user	= (TaAutUser) json.get("userInfo");

		try {
			//---------------------------------------------------------------------------------
			//---------------------------------------------------------------------------------
			//---------------------------------------------------------------------------------
			if(sv.equals(SV_GET) 						&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doGet(user, json, response);
			} else if(sv.equals(SV_GET_SIMPLE) 			&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doGetSimple(user, json, response);		

			} else if(sv.equals(SV_LST)					&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLst(user, json, response);
			} else if(sv.equals(SV_LST_DYN)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLstDyn(user, json, response);
			
				
//			} else if(sv.equals(SV_GET_FILE)			&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_G)){
			} else if(sv.equals(SV_GET_FILE)			&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doGetFile(user, json, response);	

//			} else if(sv.equals(SV_NEW)					&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_N)){
			} else if(sv.equals(SV_NEW)					&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doNew(user, json, response);
			} else if(sv.equals(SV_MOD)					&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doMod(user, json, response);
			} else  if(sv.equals(SV_DEL)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doDel(user, json, response);

//			} else if(sv.equals(SV_MOD_TRANSL)			&& APIAuth.canAuthorize(user, APIRightDef.RIGHT_NSO_AREA_M)){
//				doModTransl(user, json, response);

//			}else  if(sv.equals(SV_UPDATE_STATUS)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
			}else  if(sv.equals(SV_UPDATE_STATUS)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doUpdateStatus(user, json, response);

//			} else if(sv.equals(SV_LCK_REQ)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
			} else if(sv.equals(SV_LCK_REQ)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckReq(user, json, response);
//			} else if(sv.equals(SV_LCK_SAV)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
			} else if(sv.equals(SV_LCK_SAV)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckSav(user, json, response);
//			} else if(sv.equals(SV_LCK_END)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
			} else if(sv.equals(SV_LCK_END)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckEnd(user, json, response);
//			} else if(sv.equals(SV_LCK_DEL)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_NSO_POST_M)){
			} else if(sv.equals(SV_LCK_DEL)				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckDel(user, json, response);		

			
			}else if(sv.equals(SV_LST_NEWS)				&&  APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLstNews(user, json, response);

			} else if(sv.equals(SV_LST_SEARCH)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)) {
				doLstSearch(user, json, response);
				
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
//			TaNsoPost post 	= (TaNsoPost) params[0];
//			Integer   uId 	= post.reqInt(post, TaNsoPost.ATT_I_AUT_USER_NEW);
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
	private static void doGet(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {	
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Integer 			objId		= ToolData.reqInt	(json, "id"		, -1	);				
		Boolean				forced		= ToolData.reqBool	(json, "forced"	, false	);
		Boolean				forManager	= ToolData.reqBool	(json, "forManager"	, false	);

		TaNsoPost 			ent 		= reqGet(objId, forced, forManager);

		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_ERR_RIGHT));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));



	}

	private static CacheData<TaNsoPost> 		cache_entity= new CacheData<TaNsoPost>		(500, DefTime.TIME_SLEEP_24_00_00_000 );
	public static TaNsoPost reqGet(Integer objId, Boolean forced, boolean forManager) throws Exception{
		String 			key		= objId+"";
		TaNsoPost 		ent 	= cache_entity.reqData(key);	
		if (forced || ent ==null) {
			ent 	= TaNsoPost.DAO.reqEntityByRef(objId, forced);
			if (ent!=null) {
				cache_entity.reqPut(key, ent);
			}
		}else {				
			ToolLogServer.doLogInf("---reqNsoAreaGet use cache-----");
			cache_entity.reqCheckIfOld(key); //cache in 20 hour
		}


		//---do build something other of ent like details....
		if (ent!=null){			
			if(!forManager && !((Integer)ent.req(TaNsoPost.ATT_I_STATUS)).equals(TaNsoPost.POST_STATUS_VALIDATED))	return null;			
			ent.doBuildAll(forced, forManager);
//			ent.doBuildViewCount(true, objId);
		}

		return ent;
	}



	private static void doGetSimple(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {	
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Integer 			objId		= ToolData.reqInt	(json, "id"			, -1	);		
		TaNsoPost 			ent 		= reqGetSimple(objId);

		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_ERR_RIGHT));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		
				filter_EntSimple,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));
	}
	private static Set<String> filter_EntSimple = new HashSet<String>();
	static {
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_02);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_03);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_04);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_05);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_06);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_07);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_08);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_09);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_CONTENT_10);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_STATUS);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_TYPE_01);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_AUT_USER_NEW);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_D_DATE_NEW);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_D_DATE_MOD);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_PROPERTY_01);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_PROPERTY_02);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_PROPERTY_03);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_PROPERTY_04);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_TYPE_01);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_TYPE_02);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_PARENT);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_T_COMMENT);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_D_DATE_NEW);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_D_DATE_MOD);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_NB_RESP);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_ENTITY_TYPE);
		filter_EntSimple.add(TaNsoPost.class.getSimpleName()+"."+TaNsoPost.ATT_I_ENTITY_ID);
	}

	public static TaNsoPost reqGetSimple(Integer objId) throws Exception{
		String 			key		= objId+"";
		TaNsoPost 		ent 	= cache_entity.reqData(key);	
		if (ent ==null) {
			ent 	= TaNsoPost.DAO.reqEntityByRef(objId, false);
		}
		return ent;
	}
	//---------------------------------------------------------------------------------------------------------
	private static void doGetFile(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {	
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");
		JSONObject		obj			= ToolData.reqJson (json, "obj"		, null);
		Integer 			objId	= ToolData.reqInt	(json, "id"		, -1	);				
		Boolean				forced	= ToolData.reqBool	(json, "forced"	, true	);

		TaNsoPost 		ent 	= reqGetFile(obj, forced);

		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_ERR_RIGHT));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));
	}

	private static TaNsoPost reqGetFile(JSONObject obj, Boolean forced) throws Exception{
		//		TaNsoPost 		ent 	= TaNsoPost.DAO.reqEntityByRef(objId, false);

		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaNsoPost.class);
		TaNsoPost 		ent 		= new TaNsoPost(attr);

		//---do build something other of ent like details....
		ent.doBuildDocuments(forced);

		return ent;
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doLst(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		List<TaNsoPost> 	list = reqLst(user, json); //and other params if necessary
		if (list==null || list.size()==0){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, list 
				));				
	}



	private static List<TaNsoPost> reqLst(TaAutUser user, JSONObject json) throws Exception {
		Integer 			objMan		= ToolData.reqInt	(json, "manId"	, null	);
		Integer 			objTyp		= ToolData.reqInt	(json, "typ"		, null	);
		Boolean 			objContBuild= ToolData.reqBool	(json, "withBuild"	, false	);

		//other params here

		if (!canWorkWithObj(user, WORK_LST, objTyp)){ //other param after objTyp...
			return null;
		}

		Criterion 			cri		= reqCriterion (objTyp); //and other params	
		List<TaNsoPost> 	list 	= null;
		if (cri==null) 
			list =   TaNsoPost.DAO.reqList();
		else
			list =   TaNsoPost.DAO.reqList(cri);


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
			//cri 		= Restrictions.and(cri, Restrictions.eqOrIsNull(TaNsoPost.ATT_I_TYPE, type));
		}			

		if (params!=null && params.length>1){
			//do something
		}

		return cri;
	}

	
	private static void doLstSearch(TaAutUser user,  JSONObject json, HttpServletResponse response)	throws Exception {

		String				searchkey		= ToolData.reqStr	(json, "searchkey"	, "%").toLowerCase();// Integer.parseInt(request.getParameter("typ01")); 	
		Integer				nbLineMax		= ToolData.reqInt	(json, "nbLine"		, 10 );

		Integer 		    type			= ToolData.reqInt	(json, "type"		, null	);
		String				typeMultiStr	= ToolData.reqStr	(json, "typMulti"	, null);
		Set<Integer>		typeMulti		= typeMultiStr!=null?ToolSet.reqSetInt(typeMultiStr):null;
		
		if (!canWorkWithObj(user, WORK_LST, type)){ //other param after objTyp...
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		Criterion cri	= null;
		
		cri 			= Restrictions.ilike(TaNsoPost.ATT_T_TITLE							, "%"+searchkey+"%");

		if (typeMulti!=null) {
			cri				= Restrictions.and(cri, Restrictions.in(TaNsoPost.ATT_I_TYPE_01	, typeMulti));
		}else if (type!=null){
			cri				= Restrictions.and(cri, Restrictions.eq(TaNsoPost.ATT_I_TYPE_01	, type));
		}


		List<TaNsoPost> 	postLst		= TaNsoPost.DAO.reqList(0, nbLineMax, Order.asc(TaNsoPost.ATT_T_TITLE), cri);

		API.doResponse(response, ToolJSON.reqJSonStringWithNull(
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES, 
				DefJS.RES_DATA		, postLst));
		
	}
	//---------------------------------------------------------------------------------------------------------		

	private static Long countAllEle = null;
	private static void doLstDyn(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception {	
		Object[]  			dataTableOption = ToolDatatable.reqDataTableOption (json, null);
		List<String>		searchKey		= (List<String>)dataTableOption[0];	
		Integer				stat			= ToolData.reqInt	(json, "stat"	, null);
		Integer				typ				= ToolData.reqInt	(json, "type"	, null);
		Set<Integer>		typMulti		= ToolData.reqSetInt(json, "typMulti", null);
		
		Set<Integer>	objTypMult			= new HashSet<Integer>() ;
		if(typ == null && typMulti  == null) {
			objTypMult.add(TaNsoPost.POST_TYPE_BLOG);
			objTypMult.add(TaNsoPost.POST_TYPE_OFFER);
		
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

		List<TaNsoPost> list 		= reqListDyn(dataTableOption, cri);		
		if (list==null ){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		} else {
			for(TaNsoPost p : list) {
				p.doBuildUserLogin(true);
			}
		}

		if (countAllEle==null)
			countAllEle = ((long)reqNbNsoPostListDyn());

		Integer iTotalRecords 			= (countAllEle.intValue());				
		Integer iTotalDisplayRecords 	= reqNbNsoPostListDyn(cri).intValue();


		API.doResponse(response,ToolJSON.reqJSonString(		filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,					
				"iTotalRecords"				, iTotalRecords,
				"iTotalDisplayRecords"		, iTotalDisplayRecords,
				"aaData"					, list
				));

	}

	

	private static Criterion reqRestriction(List<String> searchKey, Integer stat, Set<Integer> types) throws Exception {		

		Criterion cri = null;
		if(stat != null){
			cri = Restrictions.eq(TaNsoPost.ATT_I_STATUS, stat);
		}

		for (String s : searchKey){
			if (cri==null)
				cri = 	Restrictions.ilike(TaNsoPost.ATT_T_TITLE		, s);

			else
				cri = 	Restrictions.and(	cri, 
						Restrictions.ilike(TaNsoPost.ATT_T_TITLE		, s)
						);
		}

		if(types!=null) {
			cri = Restrictions.and(	cri, 
					Restrictions.in(TaNsoPost.ATT_I_TYPE_01 , types)
					);
		}

		return cri;
	}

	private static List<TaNsoPost> reqListDyn(Object[] dataTableOption, Criterion 	cri) throws Exception {		
		int 		begin 		= (int)	dataTableOption[1];
		int 		number 		= (int)	dataTableOption[2]; 
		int 		sortCol 	= (int)	dataTableOption[3]; 
		int 		sortTyp 	= (int)	dataTableOption[4];	

		List<TaNsoPost> list 	= new ArrayList<TaNsoPost>();		

		Order 	order 	= null;			
		String 	colName = null;

		switch(sortCol){
		case 0: colName = TaNsoPost.ATT_I_ENTITY_ID; break;		
		case 1: colName = TaNsoPost.ATT_T_TITLE; break;					
		}

		if (colName!=null){
			switch(sortTyp){
			case 0: order = Order.asc (colName); break;
			case 1: order = Order.desc(colName); break;								
			}
		}

		if (order==null)
			list	= TaNsoPost.DAO.reqList(begin, number, cri);
		else
			list	= TaNsoPost.DAO.reqList(begin, number, order, cri);			

		return list;
	}


	private static Number reqNbNsoPostListDyn() throws Exception {		
		return TaNsoPost.DAO.reqCount();		
	}

	private static Number reqNbNsoPostListDyn(Criterion cri) throws Exception {			
		return TaNsoPost.DAO.reqCount(cri);
	}

	private static Number reqNbNsoPostUserListDyn(String sql) throws Exception {			
		return TaNsoPost.DAO.reqCount(sql);
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doNew(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		TaNsoPost 			ent		= reqNew		(user, json);
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO					
					));
			return;
		}

		TaSysLock 	lock 	= ToolDBLock.reqLock (json, "lock", DefDB.DB_LOCK_NEW, DefDBExt.ID_TA_NSO_POST, (Integer)ent.req(TaNsoPost.ATT_I_ID), user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent,
				"lock"				, lock
				));
	}

	private static TaNsoPost reqNew(TaAutUser user, JSONObject json) throws Exception {
		JSONObject		obj			= ToolData.reqJson (json, "obj", null);

		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaNsoPost.class);	

		Integer			userId		= user.reqId();
		attr.put(TaNsoPost.ATT_I_AUT_USER_NEW, userId);
		//cho nay khong duoc bo, phai dam bao post duoc gui boi ai do dang nhap, neu la visitor thi la id cua visitor


		String ref = "PN_" + ToolDate.reqString(new Date(), "yyMMddHHmm");
		attr.put(TaNsoPost.ATT_D_DATE_NEW	, new Date());
		attr.put(TaNsoPost.ATT_D_DATE_MOD	, new Date());
		attr.put(TaNsoPost.ATT_T_REF		, ref);


		TaNsoPost  		ent	 		= new TaNsoPost(attr);
		Integer 		type		= ent.reqInt(TaNsoPost.ATT_I_TYPE_01);
		Integer			status		= ent.reqInt(TaNsoPost.ATT_I_STATUS);
		
		if (type==null)	ent.reqSet(TaNsoPost.ATT_I_TYPE_01	, TaNsoPost.POST_TYPE_BLOG);
		if (status==null)ent.reqSet(TaNsoPost.ATT_I_STATUS	, TaNsoPost.POST_STATUS_DRAFT);

		if (!canWorkWithObj(user, WORK_NEW, ent)){ //other param after obj...
			return null;
		}

		String title 	= (String) ent.req(TaNsoPost.ATT_T_TITLE);
		String prop04 	= ToolString.reqCovertStringToURL(title);
		ent.reqSet(TaNsoPost.ATT_T_PROPERTY_04, prop04);

		TaNsoPost.DAO.doPersist(ent);

		//reqSavePostDetail(user, ent, obj);
		int				entId 		= (int) ent.req(TaNsoPost.ATT_I_ID);
		int				entTyp		= DefDBExt.ID_TA_NSO_POST;
		
		//Update attach files
		//--this list can contain: stat_new, stat_duplicated, stat_deleted, stat_validated
		JSONArray		docs		= (JSONArray) obj.get("files");
		ent.reqSet(TaNsoPost.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(user, entTyp, entId, docs, DefAPI.SV_MODE_NEW));

		return ent;
	}
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private static void doLstNews(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		ResultPagination  	res = reqLstNews(user, json); //and other params if necessary

		if (res.reqList()==null || res.reqList().size()==0){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, res 
				));	
	}

	//--------------------------------PARTNER MORAL-------------------------------------------------------------------------
	private static final int LEVEL_BLOG_CATECORY = 1;
	private static ResultPagination reqLstNews(TaAutUser user, JSONObject json) throws Exception {
		//		Integer 		objMan		= ToolData.reqInt	(json, "manId"		, null	);
		//		Boolean 		objContBuild= ToolData.reqBool	(json, "withBuild"	, false	);
		Integer 		type		= ToolData.reqInt	(json, "type"		, null	);
		Integer			status		= TaNsoPost.POST_STATUS_VALIDATED;
		Integer 		style		= ToolData.reqInt	(json, "style"		, null	);
		Integer 		begin		= ToolData.reqInt	(json, "begin"		, 0	);
		Integer 		number		= ToolData.reqInt	(json, "number"		, 20);
		Integer 		total		= ToolData.reqInt	(json, "total"		, 0	);
		String 		   	typMulti    = ToolData.reqStr	(json, "typMulti"    , null);
		String 			sCatIds		= ToolData.reqStr	(json, "catIds"		, null);
		String 			langId 		= ToolData.reqStr	(json, "langId"		, null); // format (1,2,3)
		Boolean			withTransl	= ToolData.reqBool	(json, "withTransl"  , true);
		String 			searchkey   = ToolData.reqStr	(json, "searchkey"	, "%"); // format (1,2,3)

		//other params here

		if (!canWorkWithObj(user, WORK_LST, type)){ //other param after objTyp...
			return null;
		}

		String keyWord 	= typMulti + "_" +  type + "_" + status + "_" + style +"_" + begin + "_" + number + "_" + total + "_" + langId+ "_" + withTransl + "_" + searchkey;
		ResultPagination rs =	cache_rs.reqData(keyWord);

		if(rs==null) {
			ToolLogServer.doLogInf("---reqGetLstGrid build vi-----");
			//Set<Integer> catIds				= ToolSet.reqSetInt(sCatIds);
			Set<Integer> catIds				= null;
			List<ViNsoPost> 	list 		= ViNsoPost.reqLstPost(begin, number, type, typMulti, status, style, catIds, langId, searchkey);
			if (total == 0 )	total		= (Integer)ViNsoPost.reqLstPostCount(type, typMulti, status, null, catIds, langId, searchkey);
			rs								= new ResultPagination(list, total, begin, number);
			
//			if (withTransl)		ViNsoPost.doBuildTranslations(list);
			
			cache_rs.reqPut(keyWord, rs);	
		} else {
			ToolLogServer.doLogInf("---reqNsoAreaLstGrid use cache-----");
			cache_rs.reqCheckIfOld(keyWord); //cache in 2 hour
		}

		return rs;
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doMod(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doMod --------------");

		TaNsoPost  		ent	 	=  reqMod(user, json); 								
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

	private static TaNsoPost reqMod(TaAutUser user, JSONObject json) throws Exception {
		JSONObject			obj		= ToolData.reqJson (json, "obj"	, null);
		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaNsoPost.class);
		
		int				entId 		= ToolData.reqInt(obj, "id", null);
		int				entTyp		= DefDBExt.ID_TA_NSO_POST;
		TaNsoPost 		ent 		= TaNsoPost.DAO.reqEntityByRef(entId);
		if (ent==null){
			return null;
		}

		if (!canWorkWithObj(user, WORK_MOD, ent)){ //other param after obj...
			return null;
		}

		attr.remove(TaNsoPost.ATT_D_DATE_NEW);
		attr.remove(TaNsoPost.ATT_I_AUT_USER_NEW);
		attr.put(TaNsoPost.ATT_D_DATE_MOD		, new Date());	
		attr.put(TaNsoPost.ATT_I_AUT_USER_MOD	, user.reqId());

		Integer typPost = (Integer) ent.req(TaNsoPost.ATT_I_TYPE_01);
		switch(typPost) {
		case TaNsoPost.POST_TYPE_BLOG : 	
			String title 	= (String) attr.get(TaNsoPost.ATT_T_TITLE);
			String prop04 	= ToolString.reqCovertStringToURL(title);
			attr.put(TaNsoPost.ATT_T_PROPERTY_04, prop04);
			break;
		default: break;
		}

		TaNsoPost.DAO.doMerge(ent, attr);

		//merge other list from obj --------------
		JSONArray 		docs 	= (JSONArray) obj.get("files");
		ent.reqSet(TaNsoPost.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(user, entTyp, entId, docs, DefAPI.SV_MODE_MOD));

		JSONArray		cats	= (JSONArray) obj.get("cats");
		ent.reqSet(TaNsoPost.ATT_O_CATS		, TaTpyCategoryEntity.reqListMod(entTyp, entId, cats));

		return ent;
	}

//	private static void doModTransl(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
//		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doMod --------------");
//		Integer  		entId	 	=  reqModTransl(user, request);
//
//		if (entId==null){
//			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
//		} else {
//			cache_entity.doDel(entId+"");
//			API.doResponse(response,ToolJSON.reqJSonString(
//					DefJS.SESS_STAT		, 1, 
//					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
//					DefJS.RES_DATA		, entId
//					));	
//		}		
//	}

//	private static Integer reqModTransl(TaAutUser user, HttpServletRequest request) throws Exception {
//		JSONObject 	obj 		= ToolData.reqJson (json, "obj");
//		Object 		translId 	= obj.get("translId"); //id of translation
//		Integer		objLang		= Integer.parseInt((String) obj.get("typ02")); //lang option
//		Integer		parId		= Integer.parseInt((String) obj.get("parId")); //lang option
//
//		TaTpyTranslation transl	= null;
//		if (translId!=null) {		
//			transl = TaTpyTranslation.DAO.reqEntityByRef(Integer.parseInt(translId.toString()));
//			if (transl!=null) {
//				Integer		parIdEnt		= transl.reqInt(transl, TaTpyTranslation.ATT_I_ENTITY_ID);
//				Integer		parTyEnt		= transl.reqInt(transl, TaTpyTranslation.ATT_I_ENTITY_TYPE);
//				if (!parIdEnt.equals(parId) || !parTyEnt.equals(DBConfig.ID_TA_NSO_POST)) transl = null;
//			}
//		}
//
//		if (transl==null) {
//			transl = new TaTpyTranslation(DBConfig.ID_TA_NSO_POST, parId, 0, null, objLang, obj.toJSONString());
//			TaTpyTranslation.DAO.doPersist(transl);
//		}else {
//			transl.reqSet(TaTpyTranslation.ATT_T_CONTENT, obj.toJSONString());
//			TaTpyTranslation.DAO.doMerge(transl);
//		}
//		return parId;
//	}

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

	private static boolean canDel(TaAutUser user, JSONObject json) throws Exception {
		Integer 	entId	= ToolData.reqInt	(json, "id", null	);	
		Integer		entTyp	= DefDBExt.ID_TA_NSO_POST;
		TaNsoPost  	ent	 	= TaNsoPost.DAO.reqEntityByRef(entId);
		if (ent==null){
			return false;
		}

		Integer stat = ent.reqInt(ent, TaNsoPost.ATT_I_STATUS);
		if (stat.equals(TaNsoPost.POST_STATUS_DELETED)) {
			if (!canWorkWithObj(user, WORK_DEL, ent)){ //other param after ent...
				return false;
			}	
			
			
			//remove all other object connecting to this ent first-------
			
			
			
//			if 3 tables are in 3 different db, we need user 3 session
//			Session sessSub 	= TaTpyDocument	.DAO.reqSessionCurrent();
			Session sessMain 	= TaNsoPost		.DAO.reqSessionCurrent();
			try {
				TaTpyCategoryEntity	.doListDel(sessMain, entTyp, entId);
				TaTpyDocument		.doListDel(sessMain, entTyp, entId);
//				TaTpyDocument		.doListDel(sessSub, entTyp, entId);
				
				TaNsoPost.DAO		.doRemove (sessMain, ent);
				
				TaNsoPost			.DAO.doSessionCommit(sessMain);
//				TaTpyDocument		.DAO.doSessionCommit(sessSub);
			}catch(Exception e){
				e.printStackTrace();
				TaNsoPost			.DAO.doSessionRollback(sessMain);
//				TaTpyDocument		.DAO.doSessionRollback(sessSub);
			}
			
		} else {
			//Set status = POST_STATUS_DELETED
			ent.reqSet(TaNsoPost.ATT_I_STATUS, TaNsoPost.POST_STATUS_DELETED);
			TaNsoPost.DAO.doMerge(ent);	
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
		
		TaNsoPost  		ent	 	=  reqMod(user, json); 								
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
		
		
		TaNsoPost ent = reqMod(user, json);						
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
	//--cache for UI public
	private static CacheData<ResultPagination>		cache_rs 	= new CacheData<ResultPagination>(100, DefTime.TIME_SLEEP_01_00_00_000);
	//-----------------------------------------------------------------------------------
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

		TaNsoPost p = TaNsoPost.DAO.reqEntityByRef(postId);
		p.reqSet(TaNsoPost.ATT_I_STATUS, stat);
		p.reqSet(TaNsoPost.ATT_D_DATE_MOD, new Date());
		TaNsoPost.DAO.doMerge(p);
		//		p.doBuildAll(true);
		p.doBuildDocuments(true);

		API.doResponse(response,ToolJSON.reqJSonString(
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, p
				));
	}

	//---------------------------------------------------------------------------------------------------------
	//--cache for UI BLOG public
	//------------function get list post by entType and entId-----------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	
	//----------------------------------------------------
//	private static void doLstTopView(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
//		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
//		Integer 					nbMax		= ToolData.reqInt	(json, "nbMax"		, 10 );
//		List<ViSysStatisticCount>	lstVi		= ViSysStatisticCount.reqList(DBConfig.ID_TA_NSO_POST, nbMax);
//
//		String keyWord 			= "TOPVIEW_"+nbMax;
//		List<TaNsoPost> res 	= cache_manager.reqData(keyWord);
//
//		if(res == null) {
//			res		= reqLstTopView(lstVi); //and other params if necessary
//			if (res==null){
//				API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
//				return;
//			}
//			cache_manager	.doPut					(keyWord, res);
//		}else {
//			ToolLogServer.doLogInf("---reqNsoAreaLstSearchManage user cache-----");
//			cache_manager.doCheckIfOld(keyWord, DefTime.TIME_SLEEP_02_00_00_000); //cache in 2 hour
//		}
//
//		if (res==null || res.size()==0){
//			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
//			return;
//		}
//
//		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
//				DefJS.SESS_STAT		, 1, 
//				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
//				DefJS.RES_DATA		, res 
//				));				
//	}
//
//	private static List<TaNsoPost> reqLstTopView(List<ViSysStatisticCount>	lstVi) throws Exception {
//		Set<Integer> 		ids 	= ToolSet.reqSetInt(lstVi, ViSysStatisticCount.ATT_I_ENT_ID);
//		List<TaNsoPost> 	list 	= TaNsoPost.DAO.reqList_In(TaNsoPost.ATT_I_ID, ids);
//		if (list!=null) {
//			TaNsoPost		.doBuildListTranslations	(list);
//			TaTpyDocument			.doBuildTpyDocuments	(list, DBConfig.ID_TA_NSO_POST, null, null, TaNsoPost.ATT_O_DOCUMENTS, false);
//		}
//		return list;
//	}

	//--cache for UI public
	private static CacheData<List<TaNsoPost>> 	cacheEnt 		= new CacheData<List<TaNsoPost>>();
	private static CacheData<ResultPagination>		cacheEnt_rs 	= new CacheData<ResultPagination>(100, DefTime.TIME_SLEEP_02_00_00_000);
	//------------function get list post by entType and entId-----------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private static void doGetPostLstByEnt(TaAutUser user,  JSONObject json, HttpServletResponse response)	throws Exception {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGetPostLstByEntId --------------");

		ResultPagination  	res = reqListPage(user, json); //and other params if necessary
		if (res.reqList()==null || res.reqList().size()==0){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString(filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES, 
				DefJS.RES_DATA				, res));
	}

	public static ResultPagination reqListPage(TaAutUser user, JSONObject json) throws Exception {
		Integer			entTyp		= ToolData.reqInt	(json, "entTyp"		, 0);	
		Integer  		entId 		= ToolData.reqInt	(json, "entId"		, 0);
		Integer 		begin		= ToolData.reqInt	(json, "begin"		, 0	);
		Integer 		number		= ToolData.reqInt	(json, "number"		, 10);
		Integer 		total		= ToolData.reqInt	(json, "total"		, 0	);
		Boolean 		forced		= ToolData.reqBool	(json, "forced"		, false	);
		Integer			typ		    = ToolData.reqInt	(json, "type"		, 1);	
		Integer			nbLevel		= ToolData.reqInt	(json, "nbLevel"	, 1);	
		Boolean			reBuild		= ToolData.reqBool	(json, "reBuild"	, false);

		//other params here

		if (!canWorkWithObj(user, WORK_LST, null)){ //other param after objTyp...
			return null;
		}

		String keyWord 	= entTyp + "_" + entId + "_" +"_" + begin + "_" + number + "_" + total + "_" + typ;
		ResultPagination rs =	cacheEnt_rs.reqData(keyWord);

		if(reBuild)	rs = null;

		if(rs==null) {
			ToolLogServer.doLogInf("---reqGetPostByEntId build post-----");
			Criterion criEntTyp 	= Restrictions.eq(TaNsoPost.ATT_I_ENTITY_TYPE	, entTyp);
			Criterion criEnt 		= Restrictions.eq(TaNsoPost.ATT_I_ENTITY_ID		, entId);

			//only get post with status != draft or != not_validated
			//				cri				= Restrictions.and(cri, Restrictions.or(Restrictions.ne(TaNsoPost.ATT_I_STATUS	, TaNsoPost.POST_STATUS_DRAFT),
			//																		Restrictions.ne(TaNsoPost.ATT_I_STATUS	, TaNsoPost.POST_STATUS_NOT_VALIDATED)));
			Criterion criCond		= Restrictions.and( Restrictions.eq(TaNsoPost.ATT_I_STATUS		, TaNsoPost.POST_STATUS_VALIDATED), 
					Restrictions.eq(TaNsoPost.ATT_I_TYPE_01		, typ));

			Criterion criParent		= Restrictions.isNull(TaNsoPost.ATT_I_PARENT);
			Criterion criLst 		= Restrictions.and(criEntTyp, criEnt, criCond, criParent);

			List<TaNsoPost> list	= TaNsoPost.DAO.reqList(begin, number, Order.desc(TaNsoPost.ATT_I_ID), criLst);		


			List<TaNsoPost> listAll	= new ArrayList<TaNsoPost>(); 
			if(list != null && list.size() > 0 && nbLevel > 1) {
				Criterion 		criLev 	= Restrictions.and(criEntTyp, criCond);
				listAll					= reqBuildChildPost(list, criLev, forced);
			}
			listAll.addAll(list);

			//AutTool.doBuildAutUsers	    (listAll, TaNsoPost.ATT_I_AUT_USER, TaNsoPost.ATT_O_USER, forced, true);	

			//PRJ not user for moment Doc
			//TaTpyDocument.doBuildTpyDocuments	(listAll, DBConfig.ID_TA_NSO_POST, null, null, TaNsoPost.ATT_O_DOCUMENTS, forced);

			if (total == 0 )	total		= TaNsoPost.DAO.reqCount(criLst).intValue();
			rs								= new ResultPagination(list, total, begin, number);
			cacheEnt_rs.reqPut(keyWord, rs);			
		} else {
			ToolLogServer.doLogInf("---reqGetPostByEntId use cache-----");
			cacheEnt_rs.reqCheckIfOld(keyWord); //cache in 2 hour
		}

		return rs;

	}

	private static List<TaNsoPost> reqBuildChildPost(List<TaNsoPost> list, Criterion criLev, boolean forced) throws Exception {
		Set<Integer> setLev = ToolSet.reqSetInt(list, TaNsoPost.ATT_I_ID);
		if(setLev!=null && setLev.size()>0) {
			List<TaNsoPost> childs = TaNsoPost.DAO.reqList_In(TaNsoPost.ATT_I_PARENT, setLev, criLev);
			if(childs != null && childs.size() > 0) {
				Map<Integer, List<TaNsoPost>> mapChild = new HashMap<Integer, List<TaNsoPost>>();

				for(TaNsoPost post: childs) {
					Integer parentID = (Integer) post.req(TaNsoPost.ATT_I_PARENT);
					if(parentID != null) {
						if(mapChild.containsKey(parentID)) {
							mapChild.get(parentID).add(post);
						}else {
							List<TaNsoPost> ls = new ArrayList<TaNsoPost>();
							ls.add(post);
							mapChild.put(parentID, ls);
						}
					}
				}

				for(TaNsoPost p: list) {
					Integer postID = (Integer) p.req(TaNsoPost.ATT_I_ID);
					p.reqSet(TaNsoPost.ATT_O_CHILDS, mapChild.get(postID));
				}
			}
			return childs;
		}
		return new ArrayList<TaNsoPost>();
	}

}
