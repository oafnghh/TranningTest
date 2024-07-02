package com.hnv.api.service.publ.tpy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefJS;
import com.hnv.api.def.DefTime;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.common.ResultPagination;
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONObject;
import com.hnv.db.EntityAbstract;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.mat.TaMatMaterial;
import com.hnv.db.tpy.TaTpyInformation;
import com.hnv.db.tpy.TaTpyCategory;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.db.tpy.vi.ViTpyInformationDyn;
import com.hnv.def.DefDBExt;

/**
 * ----- ServiceTpyInformation by H&V ----- Copyright 2017------------
 */
public class ServiceTpyInformationPubl implements IService {
	private static String filePath = null;
	private static String urlPath  = null;

	// --------------------------------Service
	// Definition----------------------------------
	public static final String  SV_MODULE     = "EC_V3".toLowerCase();

	public static final String  SV_CLASS      = "ServiceTpyInformationPubl".toLowerCase();

	public static final String  SV_GET        = "SVGet".toLowerCase();
	public static final String  SV_LST        = "SVLst".toLowerCase();
	
	public static final String  SV_LST_SEARCH = "SVLstSearch".toLowerCase();
	public static final String  SV_LST_PAGE   = "SVLstPage".toLowerCase();

	public static final String  SV_LST_CAT    = "SVLstCat".toLowerCase();
	public static final String  SV_LST_GROUP  = "SVLstGroup".toLowerCase();
	
	public static final String  SV_GET_LAST   = "SVGetLast".toLowerCase();
	public static final String  SV_GET_INF    = "SVGetInf".toLowerCase();
	public static final String  SV_GET_WELCOME    = "SVGetWelcome".toLowerCase();

	public static final Integer ENT_TYP       = DefDBExt.ID_TA_TPY_CATEGORY;

	// -----------------------------------------------------------------------------------------------
	// -------------------------Default Constructor - Required
	// -------------------------------------
	public ServiceTpyInformationPubl() {
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
		if (filePath == null) {
			filePath = API.reqContextParameter("PATH_FILE");
		}
		if (urlPath == null) {
			urlPath = API.reqContextParameter("PATH_URL");
		}
	}

	// -----------------------------------------------------------------------------------------------
	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		String    sv   = API.reqSVFunctName(json);
		TaAutUser user = (TaAutUser) json.get("userInfo");
		try {
			if (sv.equals(SV_GET) ) {
				doGet(user, json, response);
			} else if (sv.equals(SV_LST) ) {
				doLst(user, json, response);
			} else if(sv.equals(SV_LST_PAGE)	){
				doLstPage(user,  json, response);
				
			} else if (sv.equals(SV_LST_SEARCH) ) {
				doLstSearch(user, json, response);

			} else if (sv.equals(SV_LST_CAT)) {
				doLstCat(user, json, response);

			} else if (sv.equals(SV_GET_LAST) ) {
				doGetLast(user, json, response);
			} else if (sv.equals(SV_LST_GROUP) ) {
				doLstGroup(user, json, response);

			} else if (sv.equals(SV_GET_INF) ) {
				doGetInf(user, json, response);

			} else if (sv.equals(SV_GET_WELCOME) ) {
				doGetWelcome(user, json, response);

			} else {
				API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			}
		} catch (Exception e) {
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			e.printStackTrace();
		}
	}
	// ---------------------------------------------------------------------------------------------------------

	private static final int WORK_GET = 1;
	private static final int WORK_LST = 2;
	private static final int WORK_NEW = 3;
	private static final int WORK_MOD = 4;
	private static final int WORK_DEL = 5;
	private static final int WORK_UPL = 10; // upload

	private static boolean canWorkWithObj(TaAutUser user, int typWork, Object... params) {
		switch (typWork) {
		case WORK_GET:
			// check something with params
			return true;
		case WORK_LST:
			// check something with params
			return true;
		case WORK_NEW:
			// check something with params
			return true;
		case WORK_MOD:
			// check something with params
			return true;
		case WORK_DEL:
			// check something with params
			return true;
		case WORK_UPL:
			// check something with params
			return true;
		}
		return false;
	}

	private static CacheData<Map<Integer, TaTpyInformation>> cache_lstGrp = new CacheData<Map<Integer, TaTpyInformation>>(100,
			DefTime.TIME_05_00_00_000);
	private static CacheData<ResultPagination>            cache_pagina = new CacheData<ResultPagination>(100,
			DefTime.TIME_02_00_00_000);

	// ---------------------------------------------------------------------------------------------------------
	private static CacheData<TaTpyInformation> cache_ent = new CacheData<TaTpyInformation>(100, DefTime.TIME_01_00_00_000, DefTime.TIME_00_30_00_000);

	private static void doGet(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Integer       objId   	= ToolData.reqInt (json, "id"		, -1);
		Boolean       forced  	= ToolData.reqBool(json, "forced"	, false);
		Boolean       wAvar   	= ToolData.reqBool(json, "wAvatar"	, false);
		Boolean       wChild  	= ToolData.reqBool(json, "wChild"	, false);
		Boolean       wParent 	= ToolData.reqBool(json, "wParent"	, false);
		Boolean       wInfo		= ToolData.reqBool(json, "wInfo"	, false);

		TaTpyInformation ent     	= reqGet(objId, forced, wAvar, wParent, wChild, wInfo);

		if (ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)) {
			API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, ent));
	}

	private static TaTpyInformation reqGet(Integer id, Boolean forced, Boolean wAvatar, Boolean wParent, Boolean wChild, Boolean wInfo)	throws Exception {
		String        key = id + "_" + wAvatar + "_" + wParent + "_" + wChild + "_" +wInfo;

		TaTpyInformation ent = cache_ent.reqData(key);

		if (ent == null || forced) {
			ent = TaTpyInformation.DAO.reqEntityByID(id);

			if (ent == null)
				return ent;

//			if (wAvatar)
//				ent.doBuildDocuments(false);
//			if (wParent)
//				ent.doBuildParent();
//			if (wChild)
//				ent.doBuildChildren();
//
//			if (wInfo)
//				ent.doBuildInfo();

			cache_ent.reqPut(key, ent);
		} else {
			cache_ent.reqCheckIfOld(key);
		}
		// ---do build something other of ent like details....
		return ent;
	}

	// --------------------------------------------------------------------------------------------------------------
	private static CacheData<List<TaTpyInformation>> cache_lst = new CacheData<List<TaTpyInformation>>();

	private static void doLst(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Set<Integer>        ids     = ToolData.reqSetInt(json, "ids", null);
		Set<String>         codes   = ToolData.reqSetStr(json, "codes", null);
		
		Boolean             forced  = ToolData.reqBool(json, "forced", false);
		Boolean             wAvar   = ToolData.reqBool(json, "wAvatar", false);
		Boolean             wChild  = ToolData.reqBool(json, "wChild", false);
		Boolean             wParent = ToolData.reqBool(json, "wParent", false);

		List<TaTpyInformation> ent     = reqLst(ids, codes, forced, wAvar, wParent, wChild);

		if (ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)) {
			API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, ent));

	}

	private static List<TaTpyInformation> reqLst(
			Set<Integer> ids, Set<String> codes, Boolean forced, Boolean wAvatar, Boolean wParent, Boolean wChild
	) throws Exception {
		String              key = ids + "_" + codes + "_" + wAvatar + "_" + wParent + "_" + wChild;

		List<TaTpyInformation> lst = cache_lst.reqData(key);

		if (lst == null || forced) {
			if (ids != null)
				lst = TaTpyInformation.DAO.reqList_In(TaTpyInformation.ATT_I_ID, ids);
//			else
//				lst = TaTpyInformation.DAO.reqList_In(TaTpyInformation.ATT_T_CODE, codes);

//			if (wAvatar)
//				TaTpyInformation.doBuildDocuments(lst);
//			if (wParent)
//				TaTpyInformation.doBuildParent(lst);
//			if (wChild)
//				TaTpyInformation.doBuildChildren(lst);

			if (lst != null && lst.size() > 0)
				cache_lst.reqPut(key, lst);
		} else {
			cache_lst.reqCheckIfOld(key);
		}
		// ---do build something other of ent like details....
		return lst;
	}

	// ---------------------------------------------------------------------------------------------------------
	private static void doLstTreeStruct(TaAutUser user, JSONObject json, HttpServletResponse response)
			throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
		Integer manId      = ToolData.reqInt(json, "manId", null);
		Integer typ01      = ToolData.reqInt(json, "typ01", null);

		Boolean treeBuild  = ToolData.reqBool(json, "treeType", false);
		Boolean withAvatar = ToolData.reqBool(json, "withAvatar", false);
		Boolean isCommon   = ToolData.reqBool(json, "isCommon", false);
		if (isCommon)
			manId = 1;

		if (typ01 == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		String           keyWord = manId + "_" + typ01 + "_" + treeBuild + "_" + isCommon;
		ResultPagination rs      = cache_pagina.reqData(keyWord);

		if (rs == null) {
//			List list = TaTpyInformation.reqListByType(typ01, manId != null ? manId : user.reqPerManagerId());
//			if (list == null || list.size() == 0) {
//				API.doResponse(response, DefAPI.API_MSG_KO);
//				return;
//			} else {
//				if (withAvatar)
//					TaTpyDocument.doBuildTpyDocuments(list, typ01, null, null, TaTpyInformation.ATT_O_DOCUMENTS, false);
//
//				if (treeBuild)
//					list = ToolDBEntity.reqTreeStruct(list, TaTpyInformation.ATT_I_ID, TaTpyInformation.ATT_I_PARENT,
//							TaTpyInformation.ATT_O_CHILDREN);
//			}

//			rs = new ResultPagination(list, 0, 0, 0);
			cache_pagina.reqPut(keyWord, rs);
		} else {
			cache_pagina.reqCheckIfOld(keyWord); // cache in 2 hour
		}

		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, rs.reqList()));

	}

	// ---------------------------------------------------------------------------------------------------------
	private static CacheData<List<ViTpyInformationDyn>> cache_VI = new CacheData<List<ViTpyInformationDyn>>(100,
			DefTime.TIME_01_00_00_000);

	private static void doLstSearch(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ----sử dụng ViTpyInformation để đơn giản dữ liệu lấy từ CSDL

//		Integer 			manId 		= ToolData.reqInt 	(json, "manId"		, null);
		String                 searchkey = ToolData.reqStr(json, "searchkey", "%");// Integer.parseInt(request.getParameter("typ01"));
		Integer                typ01     = ToolData.reqInt(json, "typ01", 100);
		Integer                typ02     = ToolData.reqInt(json, "typ02", 1);
		Integer                nbLineMax = ToolData.reqInt(json, "nbLine", 10);

		String                 keyWord   = searchkey + "_" + typ01 + "_" + typ02;
		List<ViTpyInformationDyn> list      = cache_VI.reqData(keyWord);

		if (list == null) {
			Criterion cri = null;
//			cri  = Restrictions.like(TaTpyInformation.ATT_T_NAME, "%" + searchkey + "%");
			cri  = Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_TYPE_01, typ01),
					Restrictions.eq(TaTpyInformation.ATT_I_TYPE_02, typ02)

//					Restrictions.eq(TaTpyInformation.ATT_I_PER_MANAGER	, manId)
			);

//			list = ViTpyInformationDyn.DAO.reqList(0, nbLineMax, Order.asc(ViTpyInformationDyn.ATT_T_NAME), cri);
			cache_VI.reqPut(keyWord, list);
		} else {
			cache_VI.reqCheckIfOld(keyWord); // cache in 1 hour
		}

		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, list));
	}

	private static Hashtable<String, Integer> mapCol = new Hashtable<String, Integer>() {
		{
			put("action", -1);
			put("id", 0);
			put("name", 1);
			put("code", 2);
		}
	};


	private static Criterion reqRestriction(int typ01, Set<String> searchKey) throws Exception {

		Criterion cri = Restrictions.eq(ViTpyInformationDyn.ATT_I_TYPE_01, typ01);

//		for (String s : searchKey) {
//			if (cri == null)
//				cri = Restrictions.or(Restrictions.ilike(ViTpyInformationDyn.ATT_T_NAME, s),
//						Restrictions.ilike(ViTpyInformationDyn.ATT_T_CODE, s));
//
//			else
//				cri = Restrictions.and(cri, Restrictions.or(Restrictions.ilike(ViTpyInformationDyn.ATT_T_NAME, s),
//						Restrictions.ilike(ViTpyInformationDyn.ATT_T_CODE, s)));
//		}

		return cri;
	}


	private static void doBuildParentName(List<ViTpyInformationDyn> lst) throws Exception {
		HashSet<Integer>       set    = new HashSet<Integer>();
		List<ViTpyInformationDyn> lstChk = new ArrayList<ViTpyInformationDyn>();
		for (ViTpyInformationDyn o : lst) {
//			Integer pId = o.reqInt(ViTpyInformationDyn.ATT_I_PARENT);
//			if (pId != null) {
//				set.add(pId);
//				lstChk.add(o);
//			}
		}
		if (set.size() > 0) {
			List<ViTpyInformationDyn>             par = ViTpyInformationDyn.DAO.reqList_In(ViTpyInformationDyn.ATT_I_ID, set);
			Hashtable<Integer, EntityAbstract> tab = ToolDBEntity.reqTabKeyInt(par, ViTpyInformationDyn.ATT_I_ID);
			for (ViTpyInformationDyn o : lstChk) {
//				Integer pId = o.reqInt(ViTpyInformationDyn.ATT_I_PARENT);
//				if (pId != null) {
//					ViTpyInformationDyn p = (ViTpyInformationDyn) tab.get(pId);
//					if (p != null)
//						o.reqSet(ViTpyInformationDyn.ATT_O_PARENT_NAME, p.req(ViTpyInformationDyn.ATT_T_NAME));
//				}
			}
		}
	}
	// ---------------------------------------------------------------------------------------------------------

	private static void doLstGroup(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
		Integer                     objParTyp = ToolData.reqInt(json, "typ01", null);
		Integer                     manId     = user.reqPerManagerId();

		String                      key       = objParTyp + "-" + manId;
		Map<Integer, TaTpyInformation> map       = cache_lstGrp.reqData(key);

		if (map == null) {
//			map = TaTpyInformation.reqMapByType(objParTyp, manId);
			cache_lstGrp.reqPut(key, map);
		} else {
			cache_lstGrp.reqCheckIfOld(key);
		}

		if (map == null || map.size() == 0) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, map));
	}

	private static List<TaTpyInformation> reqLst(TaAutUser user, JSONObject json) throws Exception {
		Integer manId        = user.reqPerManagerId();                    // ToolData.reqInt (json, "manId" , null );

		Boolean objContBuild = ToolData.reqBool(json, "withBuild", false);

		Integer typ01        = ToolData.reqInt(json, "typ01", null);
		Integer typ02        = ToolData.reqInt(json, "typ02", null);

		// other params here

		if (!canWorkWithObj(user, WORK_LST, manId, typ01)) { // other param after objTyp...
			return null;
		}

		Criterion           cri  = reqCriterion(manId, typ01, typ02); // and other params
		List<TaTpyInformation> list = null;
		if (cri == null)
			list = TaTpyInformation.DAO.reqList();
		else
			list = TaTpyInformation.DAO.reqList(cri);

		// do something else with request
		if (objContBuild) {

		}

		return list;
	}

	private static Criterion reqCriterion(Object... params) throws Exception {
		if (params == null || params.length == 0)
			return null;

		Criterion cri = Restrictions.gt("I_ID", 0);

		if (params != null && params.length > 0) {
			// Integer type = (Integer) params[0];
			// cri = Restrictions.and(cri, Restrictions.eqOrIsNull(TaTpyInformation.ATT_I_TYPE,
			// type));
		}

		if (params != null && params.length > 1) {
			// do something
			Integer manId = (Integer) params[1];
//			cri = Restrictions.and(cri, Restrictions.eqOrIsNull(TaTpyInformation.ATT_I_PER_MANAGER, manId));
		}

		if (params != null && params.length > 2) {
			// do something
			Integer typ = (Integer) params[2];
			cri = Restrictions.and(cri, Restrictions.eqOrIsNull(TaTpyInformation.ATT_I_TYPE_01, typ));
		}

		if (params != null && params.length > 3) {
			// do something
			Integer typ = (Integer) params[3];
			cri = Restrictions.and(cri, Restrictions.eqOrIsNull(TaTpyInformation.ATT_I_TYPE_02, typ));
		}
		return cri;
	}

	// ---------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------
	private static void doLstCat(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
		Integer             parTyp 		= ToolData.reqInt	(json, "parType"	, null);
		Boolean             withCount 	= ToolData.reqBool	(json, "withCount"	, null);
		Integer             manId  		= user != null ? user.reqPerManagerId() : null;

//		List<TaTpyInformation> list   		= TaTpyInformation.reqListByType(parTyp, manId);
//		
//		if (list == null) {
//			API.doResponse(response, DefAPI.API_MSG_KO);
//			return;
//		}
		
		if(withCount != null) {
//			TaTpyInformation.doBuildCount(list, DefDBExt.ID_TA_MAT_MATERIAL, null, null);
		}
		
//		API.doResponse(response, ToolJSON.reqJSonString( // filter,
//				DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, list));
	}

	// ---------------------------------------------------------------------------------
	private static void doLstPage(TaAutUser user,  JSONObject json, HttpServletResponse response)	throws Exception {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGetPostLstByEntId --------------");

		ResultPagination  	res = reqListPage(user, json); //and other params if necessary
		if (res==null){
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString(
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES, 
				DefJS.RES_DATA				, res));
	}

	private static CacheData<ResultPagination>		cacheEnt_rs 	= new CacheData<ResultPagination>(100, DefTime.TIME_02_00_00_000);
	public static ResultPagination reqListPage(TaAutUser user, JSONObject json) throws Exception {
		Integer 			begin		= ToolData.reqInt		(json, "begin"		, 0	);
		Integer 			number		= ToolData.reqInt		(json, "number"		, 10); 
		Integer 			total		= ToolData.reqInt		(json, "total"		, 0	);
//		Boolean 			withAvatar	= ToolData.reqBool		(json, "withAvatar"	, false	);
	
		Set<String> 		searchKey	= ToolData.reqSetStr	(json, "sKey"		, null);
		Integer				typ01		= ToolData.reqInt		(json, "typ01"		, null);
		Integer				entTyp		= ToolData.reqInt		(json, "entTyp"		, null);
		Integer				entId		= ToolData.reqInt		(json, "entId"		, null);
//		Integer				typ04		= ToolData.reqInt		(json, "typ04"		, null);
//		Integer				typ05		= ToolData.reqInt		(json, "typ05"		, null);
//		Integer				stat01		= ToolData.reqInt		(json, "stat01"		, null);
//		Integer				stat02		= ToolData.reqInt		(json, "stat02"		, null);
//		Integer				stat03		= ToolData.reqInt		(json, "stat03"		, null);
//		Integer				stat04		= ToolData.reqInt		(json, "stat04"		, null);
//		Integer				stat05		= ToolData.reqInt		(json, "stat05"		, null);
		
		Set<Integer>		catIds  	= ToolData.reqSetInt	(json, "catIds"		, null);

		String 				keyWord 	= typ01 + "_" +entTyp + "_" +entId + "_" + begin + "_" + number + "_" + total ;
		ResultPagination 	rs 			= null;
		Set<Integer> 		lstIds		= null;
		boolean				addCache	= true;
		
		if (catIds!=null||searchKey!=null)
			addCache	= false;
//		else
//			rs			= cacheEnt_rs.reqData(keyWord); //cache những trang cố đinh

		if(rs==null) {
			Criterion cri = reqRestrictionPagination(searchKey, typ01, entTyp, entId);
			
			
//			if (catIds!=null) {
//				List<TaTpyInformationEntity> entIds = TaTpyInformationEntity.DAO.reqList_In(TaTpyInformationEntity.ATT_I_TPY_CATEGORY, catIds);
//				cri = Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_TYPE_03, typ03));
//			}
			
			
			List<TaTpyInformation> list		= TaTpyInformation.DAO.reqList(begin, number, Order.desc(TaTpyInformation.ATT_D_DATE_01), cri);		
			
//			if(withAvatar) {
//				TaTpyDocument.doBuildTpyDocuments(list, 
//						DefDBExt.ID_TA_MAT_MATERIAL, 
//						TaTpyDocument.TYPE_01_FILE_MEDIA, 
//						TaTpyDocument.TYPE_02_FILE_IMG_AVATAR, TaTpyInformation.ATT_O_DOCUMENTS, false);
//			}

			if (total == 0 )	total		= TaTpyInformation.DAO.reqCount(cri).intValue();
			rs								= new ResultPagination(list, total, begin, number);
			if (addCache) cacheEnt_rs.reqPut(keyWord, rs);			
		
		} else {
			ToolLogServer.doLogInf("---reqGetCats use cache-----");
			cacheEnt_rs.reqCheckIfOld(keyWord); //cache in 2 hour
		}

		return rs;

	}
	
	
	private static Criterion reqRestrictionPagination(
			Set<String> searchKey, 
			Integer typ01	, Integer entTyp	, Integer entId	) throws Exception {		
		
//		if (stat01 == null){
//			stat01 = TaTpyInformation.STAT_OK; 
//		}
		
		if (typ01==null) typ01=0;
		
		Criterion cri = Restrictions.eq(TaTpyInformation.ATT_I_TYPE_01, typ01);
		
		if(entTyp!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_TYPE , entTyp));
		}
		if(entId!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_ID , entId));
		}

		if (searchKey!=null) {
			for (String s : searchKey){
				s="%"+s+"%";
				s=s.replace("*", "%");
				cri = 	 Restrictions.and	(cri, Restrictions.or(
													Restrictions.ilike(TaTpyInformation.ATT_T_INFO_01	, s), 
													Restrictions.ilike(TaTpyInformation.ATT_T_INFO_02	, s),
													Restrictions.ilike(TaTpyInformation.ATT_D_DATE_01	, s)
											));
			}
		}
		
		return cri;
	}
	
	// ---------------------------------------------------------------------------------
	
	private static void doGetLast(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Set<String> 		searchKey	= ToolData.reqSetStr	(json, "sKey"		, null);
		Integer				typ01		= ToolData.reqInt		(json, "typ01"		, null);
		Integer				entTyp		= ToolData.reqInt		(json, "entTyp"		, null);
		Integer				entId		= ToolData.reqInt		(json, "entId"		, null);
		
		
		
		Criterion cri = reqRestrictionPagination(searchKey, typ01, entTyp, entId);

		List<TaTpyInformation> ent     	= TaTpyInformation.DAO.reqList(0,1, Order.desc(TaTpyInformation.ATT_D_DATE_01), cri);

		if (ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		if (!canWorkWithObj(user, WORK_GET, ent)) {
			API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, 
				DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, 
				DefJS.RES_DATA, ent));
	}
	
	// ---------------------------------------------------------------------------------
	
	// ----- Get Inf -------------------------------------------------------------------
	
	private static void doGetInf(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Set<String> 		searchKey	= ToolData.reqSetStr	(json, "sKey"		, null);
		Integer				typ01		= ToolData.reqInt		(json, "typ01"		, 1);
		Integer				entTyp		= ToolData.reqInt		(json, "entTyp"		, 0);
		Integer				entId		= ToolData.reqInt		(json, "entId"		, 10);
		
		Integer 			status 		= TaTpyInformation.STAT_OK;
		
		Criterion 			cri 		= Restrictions.eq(TaTpyInformation.ATT_I_STATUS, status);
		 					cri 		= Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_ID	, entId));
		 					cri 		= Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_TYPE	, entTyp));
		 					cri 		= Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_TYPE_01		, typ01));
			
		List<TaTpyInformation> ent = reqListInfo(cri); 

		if (ent == null || ent.size() == 0) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		TaTpyInformation inf = ent.get(0);
		
		Double countApp = (TaMatMaterial.DAO.reqCount(Restrictions.eq(TaMatMaterial.ATT_I_TYPE_01, TaMatMaterial.TYPE_01_APP))).doubleValue();
		Double countEnv = (TaMatMaterial.DAO.reqCount(Restrictions.eq(TaMatMaterial.ATT_I_TYPE_01, TaMatMaterial.TYPE_01_ENV))).doubleValue();
		Double countNod = (TaMatMaterial.DAO.reqCount(Restrictions.eq(TaMatMaterial.ATT_I_TYPE_01, TaMatMaterial.TYPE_01_SA ))).doubleValue();
		Double countRef = (TaTpyCategory.DAO.reqCount(Restrictions.eq(TaTpyCategory.ATT_I_TYPE_01, TaTpyCategory.TYPE_01_REF))).doubleValue();;
		
		inf.reqSet(TaTpyInformation.ATT_F_VAL_01, countApp);
		inf.reqSet(TaTpyInformation.ATT_F_VAL_02, countEnv);
		inf.reqSet(TaTpyInformation.ATT_F_VAL_03, countNod);
		inf.reqSet(TaTpyInformation.ATT_F_VAL_04, countRef);
		
		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, 
				DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, 
				DefJS.RES_DATA, inf));
	}
	
	private static void doGetWelcome(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Set<String> 		searchKey	= ToolData.reqSetStr	(json, "sKey"		, null);
		Integer				typ01		= ToolData.reqInt		(json, "typ01"		, 1);
		Integer				entTyp		= ToolData.reqInt		(json, "entTyp"		, 0);
		Integer				entId		= ToolData.reqInt		(json, "entId"		, 10);
		
		Integer 			status 		= TaTpyInformation.STAT_OK;
		
		Criterion 			cri 		= Restrictions.eq(TaTpyInformation.ATT_I_STATUS, status);
		 					cri 		= Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_ID	, entId));
		 					cri 		= Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_TYPE	, entTyp));
		 					cri 		= Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_TYPE_01		, typ01));
			
		List<TaTpyInformation> ent = reqListInfo(cri); 

		if (ent == null || ent.size() == 0) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		TaTpyInformation inf = ent.get(0);
		
		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, 
				DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, 
				DefJS.RES_DATA, inf));
	}
	
	private static List<TaTpyInformation> reqListInfo(Criterion cri) throws Exception {		
		List<TaTpyInformation> ent = new ArrayList<TaTpyInformation>();
		ent = TaTpyInformation.DAO.reqList(0,1, Order.desc(TaTpyInformation.ATT_D_DATE_01), cri);

		return ent;
	}
	
	// ---------------------------------------------------------------------------------


}
