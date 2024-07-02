package com.hnv.api.service.sub.tpy;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
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
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolDBLock;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.nso.TaNsoPost;
import com.hnv.db.sys.TaSysLock;
import com.hnv.db.tpy.TaTpyCategory;
import com.hnv.db.tpy.TaTpyDocument;


/**
 * ----- ServiceTpyCategory by H&V
 * ----- Copyright 2017------------
 */
public class ServiceTpyCategory implements IService {
	private static	String 			filePath	= null; 
	private	static	String 			urlPath		= null; 

	//--------------------------------Service Definition----------------------------------
	public static final String SV_MODULE 					= "EC_V3".toLowerCase();

	public static final String SV_CLASS 					= "ServiceTpyCategory".toLowerCase();	

	public static final String SV_DO_GET 		= "SVGet".toLowerCase();	
	public static final String SV_DO_LST 		= "SVLst".toLowerCase();

	public static final String SV_DO_NEW 		= "SVNew".toLowerCase();	
	public static final String SV_DO_MOD 		= "SVMod".toLowerCase();	
	public static final String SV_DO_DEL 		= "SVDel".toLowerCase();
	
	public static final String SV_DO_DUPLICATE 	= "SVDuplicate".toLowerCase();
	
	public static final String SV_DO_LCK_REQ 	= "SVLckReq".toLowerCase(); //req or refresh	
	public static final String SV_DO_LCK_SAV 	= "SVLckSav".toLowerCase(); //save and continue
	public static final String SV_DO_LCK_END 	= "SVLckEnd".toLowerCase();
	public static final String SV_DO_LCK_DEL 	= "SVLckDel".toLowerCase();

	public static final String SV_DO_LST_CAT 	= "SVLstCat".toLowerCase();
	public static final String SV_DO_LST_GROUP  = "SVLstGroup".toLowerCase();
	//-----------------------------------------------------------------------------------------------
	//-------------------------Default Constructor - Required -------------------------------------
	public ServiceTpyCategory(){
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
			if(sv.equals(SV_DO_GET) 				&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doGet(user, json, response);
			} else if(sv.equals(SV_DO_LST)			&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLst(user, json, response);

			} else if(sv.equals(SV_DO_NEW)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doNew(user, json, response);
			} else if(sv.equals(SV_DO_MOD)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doMod(user, json, response);
			} else  if(sv.equals(SV_DO_DEL)		&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doDel(user, json, response);
			
			} else if(sv.equals(SV_DO_DUPLICATE)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doDuplicate(user, json, response);		
				
			} else if(sv.equals(SV_DO_LCK_REQ)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckReq(user, json, response);
			} else if(sv.equals(SV_DO_LCK_SAV)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckSav(user, json, response);
			} else if(sv.equals(SV_DO_LCK_END)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckEnd(user, json, response);
			} else if(sv.equals(SV_DO_LCK_DEL)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLckDel(user, json, response);		
			} else if(sv.equals(SV_DO_LST_CAT)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLstCat(user, json, response);		
				
			} else if(sv.equals(SV_DO_LST_GROUP)	&& APIAuth.canAuthorize(user, DefRight.RIGHT_)){
				doLstGroup(user, json, response);		
			
					
			} else {
				API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			}	
		} catch (Exception e) {
			try {
				API.doResponse(response, DefAPI.API_MSG_ERR_API);
				e.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	//---------------------------------------------------------------------------------------------------------
	private static void doGet(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {	
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Integer 			objId	= ToolData.reqInt	(json, "id"		, -1	);				
		Boolean				forced	= ToolData.reqBool	(json, "forced"	, false	);

		TaTpyCategory 		ent 	= reqTpyCategoryGet(objId, forced);

		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)){
			API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));



	}
	private static TaTpyCategory reqTpyCategoryGet(Integer matId, Boolean forced) throws Exception{
		TaTpyCategory 		ent 	= TaTpyCategory.DAO.reqEntityByRef(matId, forced);

		ent.doBuildDocuments(forced);
		
		Integer pId = ent.reqInt(TaTpyCategory.ATT_I_PARENT);
		if (pId!=null) {
			TaTpyCategory par = TaTpyCategory.DAO.reqEntityByRef(pId, forced);
			if (par!=null) ent.reqSet(TaTpyCategory.ATT_O_PARENT_NAME,  par.reqStr( TaTpyCategory.ATT_T_NAME));
		}

		//---do build something other of ent like details....
		return ent;
	}
	//---------------------------------------------------------------------------------------------------------
	private static void doLst(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
		Integer 				manId 		= ToolData.reqInt 	(json, "manId"		, null);
		Integer 				parType 	= ToolData.reqInt 	(json, "parType"	, null);
		Boolean 				treeBuild 	= ToolData.reqBool 	(json, "treeType"	, false);
		Boolean 				withAvatar 	= ToolData.reqBool 	(json, "withAvatar"	, false);
		Boolean 				isCommon 	= ToolData.reqBool 	(json, "isCommon"	, false);
		if (isCommon)			manId		= 1;

		if (parType ==null) {
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		String keyWord 	= manId + "_" + parType + "_" + treeBuild + "_" + isCommon ;
		ResultPagination rs =	cache_rs.reqData(keyWord);

		if(rs==null) {
			List  	list 		= TaTpyCategory.reqListByType(parType, manId!=null?manId:user.reqPerManagerId());
			if(list==null|| list.size()==0){
				API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
				return;
			}else {
				if (withAvatar) 
					TaTpyDocument.doBuildTpyDocuments(list, parType, null, null, TaTpyCategory.ATT_O_DOCUMENTS, false);
				
				if (treeBuild) 
					list = ToolDBEntity.reqTreeStruct(list, TaTpyCategory.ATT_I_ID, TaTpyCategory.ATT_I_PARENT, TaTpyCategory.ATT_O_CHILDREN);
			}

			rs		= new ResultPagination(list, 0,0,0);
			cache_rs.reqPut(keyWord, rs);			
		} else {
			cache_rs.reqCheckIfOld(keyWord); //cache in 2 hour
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, rs.reqList()
				));	

	}

	private static CacheData<Map<Integer, TaTpyCategory>> cache = new CacheData<Map<Integer, TaTpyCategory>>(100, DefTime.TIME_SLEEP_05_00_00_000);	
	private static CacheData<ResultPagination>		cache_rs 	= new CacheData<ResultPagination>	(100, DefTime.TIME_SLEEP_02_00_00_000);

	private static void doLstGroup(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
		Integer 						objParTyp	= ToolData.reqInt	(json, "parType", null);
		Integer							manId		= user.reqPerManagerId();

		String							key			= objParTyp+"-"+manId;
		Map<Integer, TaTpyCategory> 	map 		= cache.reqData(key);

		if (map ==null) {
			map 		= TaTpyCategory.reqMapByType(objParTyp, manId);
			cache.reqPut(key, map);
		}else {
			cache.reqCheckIfOld(key);
		}

		if (map==null || map.size()==0){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, map 
				));				
	}

	private static void doDuplicate(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		JSONObject			obj			= ToolData.reqJson (json, "obj", null);
		JSONObject			catLstDup	= (JSONObject) obj.get("cats");
		Integer				socId		= Integer.parseInt((String) obj.get("socId"));

		if (!canWorkWithObj(user, WORK_NEW, obj)){ //other param after obj...
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}

		Map<Integer, TaTpyCategory> 	catLst 		= TaTpyCategory.DAO.reqMap(Restrictions.eq(TaTpyCategory.ATT_I_PER_MANAGER, user.reqPerManagerId()));
		List<TaTpyCategory>				listNewCat	= new ArrayList<TaTpyCategory>();
		if (catLstDup!=null&& catLstDup.size()>0) {

			for (Object o : catLstDup.entrySet()) {
				Map.Entry<Object, Object> 	entry 	= (Entry<Object, Object>) o;
				Integer						idCat	= Integer.parseInt(entry.getKey().toString());
				Integer 					check 	= Integer.parseInt(entry.getValue().toString());
				if(check == 1) {
					//---check dependency
					TaTpyCategory	catNew 			= catLst.get(idCat);
					Boolean 		existTpyCat 	= do_check_code_tpy_cat_duplicate(catNew, socId);
					if(!existTpyCat) {
						catNew.reqSet(TaTpyCategory.ATT_I_ID, null);
						catNew.reqSet(TaTpyCategory.ATT_I_PER_MANAGER, socId);
						listNewCat.add(catNew);
					}
				}			
			}

			//--persist the new relationships
			TaTpyCategory.DAO.doPersist(listNewCat);
		}

		if (listNewCat.size() == 0){
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO					
					));
		}else{				
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES
					));				
		}		
	}

	private static List<TaTpyCategory> reqTpyCategoryLst(TaAutUser user,  JSONObject json) throws Exception {
		Integer 			objMan		= user.reqPerManagerId();//ToolData.reqInt	(json, "manId"	, null	);
		Integer 			objTyp		= ToolData.reqInt	(json, "typ"			, null	);
		Boolean 			objContBuild= ToolData.reqBool	(json, "withBuild"	, false	);
		Integer 			objParTyp	= ToolData.reqInt	(json, "parType"		, null	);

		//other params here

		if (!canWorkWithObj(user, WORK_LST, objTyp, objMan)){ //other param after objTyp...
			return null;
		}

		Criterion 			cri		= reqCriterion (objTyp, objMan, objParTyp); //and other params	
		List<TaTpyCategory> list 	= null;
		if (cri==null) 
			list =   TaTpyCategory.DAO.reqList();
		else
			list =   TaTpyCategory.DAO.reqList(cri);

		//do something else with request
		if (objContBuild){

		}

		return list;
	}

	private static Criterion reqCriterion(Object...params) throws Exception{
		if (params==null || params.length==0) return null;

		Criterion cri = Restrictions.gt("I_ID", 0);	

		if (params!=null && params.length>0){
			//Integer type 	= (Integer) params[0];
			//cri 		= Restrictions.and(cri, Restrictions.eqOrIsNull(TaTpyCategory.ATT_I_TYPE, type));			
		}			

		if (params!=null && params.length>1){
			//do something
			Integer manId	= (Integer) params[1];
			cri 			= Restrictions.and(cri, Restrictions.eqOrIsNull(TaTpyCategory.ATT_I_PER_MANAGER, manId));
		}

		if (params!=null && params.length>2){
			//do something
			Integer parType	= (Integer) params[2];
			cri 			= Restrictions.and(cri, Restrictions.eqOrIsNull(TaTpyCategory.ATT_I_TYPE_00, parType));
		}
		return cri;
	}
	//---------------------------------------------------------------------------------------------------------
	private static void doNew(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doNew --------------");
		//--- in simple case, obj has only header , no details ----------------------
		//Map<String, Object> attr 	= API.reqMapParamsByClass(request, TaTpyCategory.class);
		//TaTpyCategory  ent	 = new TaTpyCategory(attr);
		//TaTpyCategory.DAO.doPersist(ent);
		//----------------------------------------------------------------------------------------------------------------------
		JSONObject			obj		= ToolData.reqJson (json, "obj", null);
		Boolean 	existTpyCat 	= doCheckCode(obj);
		if(existTpyCat){
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			return;
		}
		TaTpyCategory 			ent		= reqTpyCategoryNew		(user, json);
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO					
					));
		}else{				
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));				
		}
		
		TaSysLock 	lock 	= ToolDBLock.reqLock (json, "lock", DefDB.DB_LOCK_NEW, DefDBExt.ID_TA_TPY_CATEGORY, (Integer)ent.req(TaNsoPost.ATT_I_ID), user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent,
				"lock"						, lock
				));
		
	}


	private static Boolean doCheckCode(JSONObject obj) throws Exception {
		// TODO Auto-generated method stub
		String code = obj.get("code").toString().trim();
		if(code.equals("") || code == null)	return false;
		TaTpyCategory cat 	= TaTpyCategory.DAO.reqEntityByValue(TaTpyCategory.ATT_T_CODE, code);
		if(cat != null)
			return true;
		else
			return false;
	}

	private static Boolean do_check_code_tpy_cat_duplicate(TaTpyCategory cat, Integer manId) throws Exception {
		// TODO Auto-generated method stub
		String code = (String) cat.req(TaTpyCategory.ATT_T_CODE);
		List<TaTpyCategory> catLst 	= TaTpyCategory.DAO.reqList(Restrictions.eq(TaTpyCategory.ATT_T_CODE, code), Restrictions.eq(TaTpyCategory.ATT_I_PER_MANAGER, manId));
		if(catLst != null && catLst.size() > 0)
			return true;
		else
			return false;
	}

	private static TaTpyCategory reqTpyCategoryNew(TaAutUser user,  JSONObject json) throws Exception {
		JSONObject				obj		= ToolData.reqJson (json, "obj", null);

		if (!canWorkWithObj(user, WORK_NEW, obj)){ //other param after obj...
			return null;
		}

		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaTpyCategory.class);
		String codeTpy 				= attr.get(TaTpyCategory.ATT_T_CODE).toString().trim();

		int 				manId 	=  (int)user.reqPerManagerId();

		attr.put(TaTpyCategory.ATT_I_PER_MANAGER, manId);
//		if((Integer)attr.get(TaTpyCategory.ATT_I_TYPE_00) == DefDBExt.ID_TA_MAT_MATERIAL && (codeTpy.equals("") || codeTpy == null)){
//			Integer num = reqNumero();
//			String code = "CAT" + String.format("%06d", num);
//			attr.put(TaTpyCategory.ATT_T_CODE, code);
//		}

		TaTpyCategory  		ent	 	= new TaTpyCategory(attr);
		TaTpyCategory.DAO.doPersist(ent);

		int 					entId		= (int)ent.reqRef();
		int 					entTyp		= DefDBExt.ID_TA_TPY_CATEGORY;
		
		//--update documents tpy market ids------------------------
		JSONArray				docs		= (JSONArray) obj.get("files");	
		ent.reqSet(TaNsoPost.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(user, entTyp, entId, docs, DefAPI.SV_MODE_NEW));


		//--get parentName----------------------------------------
		Integer pId = ent.reqInt(TaTpyCategory.ATT_I_PARENT);
		if (pId!=null) {
			TaTpyCategory par = TaTpyCategory.DAO.reqEntityByRef(pId, false);
			if (par!=null) ent.reqSet(TaTpyCategory.ATT_O_PARENT_NAME,  par.reqStr( TaTpyCategory.ATT_T_NAME));
		}
		
		return ent;
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doMod(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doMod --------------");
		JSONObject			obj			= ToolData.reqJson (json, "obj", null);
		TaTpyCategory  		ent	 		= reqTpyCategoryMod(user, json); 								
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
		} else {
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));	
		}		
	}

	private static TaTpyCategory reqTpyCategoryMod(TaAutUser user,  JSONObject json) throws Exception {
		JSONObject			obj		= ToolData.reqJson (json, "obj", null);

		int 				entId	= Integer.parseInt(obj.get("id").toString());	
		int 				entTyp	= DefDBExt.ID_TA_TPY_CATEGORY;
		TaTpyCategory  		ent	 	= TaTpyCategory.DAO.reqEntityByRef(entId);
		if (ent==null){
			return null;
		}

		if (!canWorkWithObj(user, WORK_MOD, ent)){ //other param after obj...
			return null;
		}

		Map<String, Object> attr 		= API.reqMapParamsByClass(obj, TaTpyCategory.class);

		String 				newCode 	= ToolData.reqStr  (obj	, "code", null);
		if(!ent.req(TaTpyCategory.ATT_T_CODE).equals(newCode)){
			Boolean existCode = doCheckCode(obj);
			if(existCode){
				attr.remove(TaTpyCategory.ATT_T_CODE);
			}
		}

		TaTpyCategory.DAO.doMerge(ent, attr);

	
		//--------mod documents-------------------
		JSONArray				docs		= (JSONArray) obj.get("files");	
		ent.reqSet(TaNsoPost.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(user, entTyp, entId, docs, DefAPI.SV_MODE_NEW));

		//--get parentName----------------------------------------
		Integer pId = ent.reqInt(TaTpyCategory.ATT_I_PARENT);
		if (pId!=null) {
			TaTpyCategory par = TaTpyCategory.DAO.reqEntityByRef(pId, false);
			if (par!=null) ent.reqSet(TaTpyCategory.ATT_O_PARENT_NAME,  par.reqStr( TaTpyCategory.ATT_T_NAME));
		}
		return ent;
	}	

	//---------------------------------------------------------------------------------------------------------
	private static void doDel(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doDel --------------");

		//--chk lock-----------------------------------------------------------------------------------------------------------------
		JSONObject 		obj 	= ToolData.reqJson(json, "lock", null); 
		TaSysLock 		lock 	= ToolDBLock.reqLock(obj, user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);

		if ((Integer)lock.req(TaSysLock.ATT_I_STATUS) == 0){
			API.doResponse(response,ToolJSON.reqJSonString(						
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO,
					DefJS.RES_DATA		, lock
					));	
			return;
		}
		//----------------------------------------------------------------------------------------------------------------------
		boolean		ok		= canTpyCategoryDel(user, json); 				
		if (!ok){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
		} else {
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1,	DefJS.SV_CODE, DefAPI.SV_CODE_API_YES));
		}		
	}
	private static boolean canTpyCategoryDel(TaAutUser user,  JSONObject json) throws Exception {
		Integer 		entId	= ToolData.reqInt	(json, "id"		, -1	);	
		Integer			entTyp	= DefDBExt.ID_TA_TPY_CATEGORY;
		TaTpyCategory  	ent	 	= TaTpyCategory.DAO.reqEntityByRef(entId);
		if (ent==null){
			return false;
		}

		if (!canWorkWithObj(user, WORK_DEL, ent)){ //other param after ent...
			return false;
		}		

		//remove all other object connecting to this ent first-------
//		Session sessSub 	= TaTpyDocument	.DAO.reqSessionCurrent();
		Session sessMain 	= TaTpyCategory	.DAO.reqSessionCurrent();
		try {
			TaTpyDocument		.doListDel(sessMain, entTyp, entId);
//			TaTpyDocument		.doListDel(sessSub, entTyp, entId);
			
			TaTpyCategory		.DAO.doRemove (sessMain, ent);
			
			TaTpyCategory		.DAO.doSessionCommit(sessMain);
//			TaTpyDocument		.DAO.doSessionCommit(sessSub);
		}catch(Exception e){
			TaTpyCategory		.DAO.doSessionRollback(sessMain);
//			TaTpyDocument		.DAO.doSessionRollback(sessSub);
			e.printStackTrace();
		}
		return true;
	}

	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private void doLckReq(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLckReq --------------");		
		JSONObject 		obj 	= ToolData.reqJson(json, DefJS.REQ_DATA, null); 
		TaSysLock 		lock 	= ToolDBLock.reqLock(obj, user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		if (lock==null || lock.reqStatus() == 0){
			API.doResponse(response,ToolJSON.reqJSonString(						
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO,
					DefJS.RES_DATA		, lock
					));	
		}else{
			API.doResponse(response,ToolJSON.reqJSonString(						
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, lock
					));	
		}			

	}

	private void doLckDel(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLckDel --------------");
		boolean isDeleted = ToolDBLock.canDeleteLock(json);					
		API.doResponse(response,ToolJSON.reqJSonString(		
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, isDeleted
				));	

	}
	
	private void doLckSav(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLckSav --------------");	

		//--chk lock-----------------------------------------------------------------------------------------------------------------
		boolean isLocked 	= ToolDBLock.canExistLock(json);
		if(!isLocked){
			API.doResponse(response,ToolJSON.reqJSonString(	
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO
					));
		}

		TaTpyCategory  		ent	 	=  reqTpyCategoryMod(user, json); 								
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
		} else {
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));	
		}
	}

	//user when modify object with lock
	private void doLckEnd(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLckEnd --------------");	

		//--chk lock-----------------------------------------------------------------------------------------------------------------
		boolean isLocked 	= ToolDBLock.canExistLock(json);
		if(!isLocked){
			API.doResponse(response,ToolJSON.reqJSonString(	
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO
					));
		}
		
		TaTpyCategory  		ent	 		= reqTpyCategoryMod(user, json); 						
		if (ent==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
		} else {
			ToolDBLock.canDeleteLock(json);
			API.doResponse(response,ToolJSON.reqJSonString(
					DefJS.SESS_STAT				, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, ent
					));	
		}				
	}

	

	//---------------------------------------------------------------------------------------------------------
	private static void doLstCat(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
		Integer parTyp = ToolData.reqInt (json, "parTyp", null);

		List<TaTpyCategory> list = TaTpyCategory.reqListByType(parTyp, user.reqPerManagerId()); 
		if (list==null){
			API.doResponse(response,ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
			return;
		}
		API.doResponse(response,ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, list 
				));				
	}



	//---------------------------------------------------------------------------------

}
