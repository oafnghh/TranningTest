package com.hnv.api.service.publ.mat;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
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
import com.hnv.api.service.priv.mat.util.FileExportOBS;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDatatable;
import com.hnv.common.tool.ToolDate;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.tool.ToolSet;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.mat.TaMatMaterialOBS;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.def.DefAPIExt;
import com.hnv.def.DefDBExt;
/**
* ----- ServiceNsoOffer by H&V
* ----- Copyright 2023------------
*/
public class ServiceMatMaterialOBSPubl implements IService {
	private static	String 			filePath	= null; 
	private	static	String 			urlPath		= null; 
	

	//--------------------------------Service Definition----------------------------------
	public static final String SV_MODULE 				= "HNV".toLowerCase();

	public static final String SV_CLASS 				= "ServiceMatMaterialOBSPubl".toLowerCase();	
	
	public static final String SV_GET 					= "SVGet"		.toLowerCase();	
	public static final String SV_LST 					= "SVLst"		.toLowerCase();
	public static final String SV_LST_BY_COL 			= "SVLstByCol"	.toLowerCase();
	public static final String SV_LST_PAGE				= "SVLstPage"	.toLowerCase();
	public static final String SV_LST_DYN				= "SVLstDyn"	.toLowerCase();
	public static final String SV_LST_DYN_SQL			= "SVLstDynSQL"	.toLowerCase();
	public static final String SV_LST_EXP				= "SVLstExp"	.toLowerCase();	
	public static final String SV_GET_LST_DEX			= "SVGetLstDex"	.toLowerCase();	

	public static final Integer	ENT_TYP					= DefDBExt.ID_TA_MAT_MATERIAL;
		
	//-----------------------------------------------------------------------------------------------
	//-------------------------Default Constructor - Required -------------------------------------
	public ServiceMatMaterialOBSPubl(){
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
		
	}
	
	//-----------------------------------------------------------------------------------------------

	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		String 		sv 		= API.reqSVFunctName(json);
		TaAutUser 	user	= (TaAutUser) json.get("userInfo");
		try {
			//---------------------------------------------------------------------------------
			if(sv.equals(SV_GET) 				){
				doGet(user,  json, response);
			} else if(sv.equals(SV_LST)			){
				doLst(user, json, response);
				
			} else if(sv.equals(SV_LST_BY_COL)			){
				doLstByCol(user, json, response);
				
			} else if(sv.equals(SV_LST_PAGE)	){
				doLstPage(user,  json, response);
			} else if(sv.equals(SV_LST_DYN)		){
				doLstDyn(user, json, response);
			
			}  else if(sv.equals(SV_LST_DYN_SQL)		){
				doLstDynSQL(user, json, response);
			} else if(sv.equals(SV_LST_EXP)		){
				doLstExp(user, json, response);
			} else if(sv.equals(SV_GET_LST_DEX)		){
				doGetLstDex(user, json, response);
			} 
			
			 

		}catch(Exception e){
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			e.printStackTrace();
		}		
	}
	
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private static CacheData<TaMatMaterialOBS> 		cache_entity= new CacheData<TaMatMaterialOBS>		(500, DefTime.TIME_24_00_00_000, DefTime.TIME_00_30_00_000 );

	private static void doGet(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {	
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Integer 		entId		= ToolData.reqInt	(json, "id"			, -1	);		
		String			entCode01	= ToolData.reqStr	(json, "code01"		, null	);
		String			entName01	= ToolData.reqStr	(json, "name01"		, null	);

		if (entId==null || (entCode01==null&&entName01==null) ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		TaMatMaterialOBS 		ent 		= reqGet(entId, entCode01, entName01);

		if (ent==null){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response		, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent 
				));
	}
	
	public static TaMatMaterialOBS reqGet(Integer entId, String entCode01, String entName01) throws Exception{
		String				key		= entId + "-" + entCode01 + "-" + entName01;
		TaMatMaterialOBS 		ent 	= cache_entity.reqData(key);	
		if (ent ==null) {
			if (entCode01!=null)
				ent 	= TaMatMaterialOBS.DAO.reqEntityByValues(
						TaMatMaterialOBS.ATT_I_ID			, entId, 
						TaMatMaterialOBS.ATT_T_CODE_01		, entCode01
//						TaMatMaterialOBS.ATT_I_STATUS_01	, TaMatMaterialOBS.STAT_01_ACTIVE
						);
			else
				ent 	= TaMatMaterialOBS.DAO.reqEntityByValues(
						TaMatMaterialOBS.ATT_I_ID			, entId, 
						TaMatMaterialOBS.ATT_T_NAME_01		, entName01
//						TaMatMaterialOBS.ATT_I_STATUS_01	, TaMatMaterialOBS.STAT_01_ACTIVE
						);
			
			if (ent!=null) {
				//---do something and put to cache
				cache_entity.reqPut(key, ent);
				ent.doBuildStructTo(5, TaMatMaterialOBS.TYPE_01_NOD);
				ent.doBuildCatIds(true);
				ent.doBuildCatObjs();
			}
		}else {				
			ToolLogServer.doLogInf("---reqGet use cache-----");
//			cache_entity.reqCheckIfOld(key); //cache in 20 hour//check auto
		}
		
		//---do build something other of ent like details, document, categories....
//		if (ent != null) {
//			ent.doBuildCats(forced);
//		}

		return ent;
	}
	
	//---------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	private static void doLst(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		List<TaMatMaterialOBS> 	list = reqLst(user, json); //and other params if necessary
		if (list==null){
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString(		
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, list 
				));				
	}
	
	private static List<TaMatMaterialOBS> reqLst(TaAutUser user, JSONObject json) throws Exception  {
		Integer 			nbLine      = ToolData.reqInt		(json, "nbLine" 	, 10);
		String 				searchkey	= ToolData.reqStr		(json, "searchkey"	, "");
		Integer				stat01		= ToolData.reqInt		(json, "stat01"		, TaMatMaterialOBS.STAT_01_ACTIVE);
		Integer				typ01		= ToolData.reqInt		(json, "typ01"		, null);
		Integer				typInf		= ToolData.reqInt		(json, "typInf"		, null);
		
		Criterion cri					= reqRestrictionList (searchkey, stat01, null,  typ01, null, typInf);
		List<TaMatMaterialOBS>	list	= TaMatMaterialOBS.DAO.reqList(0, nbLine, Order.asc(TaMatMaterialOBS.ATT_T_NAME_01), cri);	

		return list;
	}
	
	private static Criterion reqRestrictionList(
			String  searchKey, 
			Integer stat01	, Integer stat02, 
			Integer typ01	, Integer typ02	,
			Integer typInf) throws Exception {		
		
		if (stat01 == null){
			stat01 = TaMatMaterialOBS.STAT_01_ACTIVE; 
		}
		
		int colToSearch = 0; // => TaMatMaterialOBS.ATT_T_NAME_01
		
		Criterion cri = Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_01, stat01);
		
		if (stat02!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_02, stat02));
		}
		
		if(typ01!=null && typ01>0) {
			colToSearch = typ01%10;
			typ01 		= (typ01/10)*10;
			cri 		= Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_01 , typ01));
		}
		if(typ02!=null && typ02>0) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_02 , typ02));
		}

		if (searchKey!=null && searchKey.length()>0) {
			String colSearch 	= null;
			switch(colToSearch){
			case 0	: colSearch =	TaMatMaterialOBS.ATT_T_NAME_01; searchKey = searchKey + "%"		; break;
			case 1	: colSearch =	TaMatMaterialOBS.ATT_T_CODE_01; searchKey = "%"+ searchKey + "%"	; break;
			default	: colSearch =	TaMatMaterialOBS.ATT_T_NAME_01;
			}
			
			if (typInf!=null && typInf>0) {
				switch(typInf){
				case 0	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_01; break;
				case 2	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_02; break;
				case 3	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_03; break;
				case 4	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_04; break;
				case 5	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_05; break;
				case 6	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_06; break;
				case 7	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_07; break;
				case 8	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_08; break;
				case 9	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_09; break;
				case 10	: colSearch =	TaMatMaterialOBS.ATT_T_INFO_10; break;
				}
			}
			searchKey = searchKey.replace("*", "%");
			searchKey = searchKey.replace("$%", "");
			searchKey = searchKey.replace("%$", "");
			cri = 	 Restrictions.and	(cri, Restrictions.ilike(colSearch, searchKey));
		}
		
		return cri;
	}
	
	private static void doLstByCol(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		List<TaMatMaterialOBS> 	list = reqLstByCol(user, json); //and other params if necessary
		if (list==null){
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString(		
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, list 
				));				
	}
	
	private static List<TaMatMaterialOBS> reqLstByCol(TaAutUser user, JSONObject json) throws Exception  {
 		Integer 			nbLine      = ToolData.reqInt		(json, "nbLine" 	, 10);
		String 				searchkey	= ToolData.reqStr		(json, "searchkey"	, "");
		Integer				stat01		= ToolData.reqInt		(json, "stat01"		, TaMatMaterialOBS.STAT_01_ACTIVE);
		Integer				typ01		= ToolData.reqInt		(json, "typ01"		, null);
		Integer				typInf		= ToolData.reqInt		(json, "typInf"		, null);
		String 				colToSearch = ToolData.reqStr		(json, "colToSearch", null);
		
		Criterion cri					= reqRestrictionListByCol (searchkey, stat01, null,  typ01, null, typInf, colToSearch);
		List<TaMatMaterialOBS>	list		= TaMatMaterialOBS.DAO.reqList(0, nbLine, Order.asc(TaMatMaterialOBS.ATT_T_NAME_01), cri);	

		return list;
	}
	
	
	private static Criterion reqRestrictionListByCol(
			String  searchKey, 
			Integer stat01	, Integer stat02, 
			Integer typ01	, Integer typ02	,
			Integer typInf, String col) throws Exception {		
		
		if (stat01 == null){
			stat01 = TaMatMaterialOBS.STAT_01_ACTIVE; 
		}
		
//		int colToSearch = 0; // => TaMatMaterialOBS.ATT_T_NAME_01
		
		String colSearch = mapColJsAtt.get(col);
		
		Criterion cri = Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_01, stat01);
		
		if (stat02!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_02, stat02));
		}
		
		if(typ01!=null && typ01>0) {
//			colToSearch = typ01%10;
//			typ01 		= (typ01/10)*10;
			cri 		= Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_01 , typ01));
		}
		if(typ02!=null && typ02>0) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_02 , typ02));
		}

		if (searchKey!=null && searchKey.length()>0) {
//			String colSearch 	= null;
			searchKey = searchKey.replace("*", "%");
			searchKey = searchKey.replace("$%", "");
			searchKey = searchKey.replace("%$", "");
			searchKey = searchKey + "%";
			
			cri = 	 Restrictions.and	(cri, Restrictions.ilike(colSearch, searchKey));
			
		}
		
		return cri;
	}
	//--------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------		
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
		Boolean 			withAvatar	= ToolData.reqBool		(json, "withAvatar"	, false	);
	
		Set<String> 		searchKey	= ToolData.reqSetStr	(json, "sKey"		, null);
		Integer				typ01		= ToolData.reqInt		(json, "typ01"		, null);
		Integer				typ02		= ToolData.reqInt		(json, "typ02"		, null);
		Integer				typ03		= ToolData.reqInt		(json, "typ03"		, null);
		Integer				typ04		= ToolData.reqInt		(json, "typ04"		, null);
		Integer				typ05		= ToolData.reqInt		(json, "typ05"		, null);
		Integer				stat01		= ToolData.reqInt		(json, "stat01"		, null);
		Integer				stat02		= ToolData.reqInt		(json, "stat02"		, null);
		Integer				stat03		= ToolData.reqInt		(json, "stat03"		, null);
		Integer				stat04		= ToolData.reqInt		(json, "stat04"		, null);
		Integer				stat05		= ToolData.reqInt		(json, "stat05"		, null);
		
		Set<Integer>		catIds  	= ToolData.reqSetInt	(json, "catIds"		, null);
		Set<Integer>		favIds  	= ToolData.reqSetInt	(json, "favIds"		, null);
		
		Integer				typInf		= ToolData.reqInt		(json, "typInf"		, null);
		

		String 				keyWord 	= typ01 + "_" + typ02 + "_" +typ03 + "_" + begin + "_" + number + "_" + total ;
		ResultPagination 	rs 			= null;
//		Set<Integer> 		lstIds		= null;
//		boolean				addCache	= true;
		
//		if (catIds!=null||searchKey!=null)
//			addCache	= false;
//		else
//			rs			= cacheEnt_rs.reqData(keyWord); //cache những trang cố đinh

		if(rs==null) {
			Criterion cri = reqRestrictionPagination(searchKey, stat01, stat02, stat03, stat04, stat05, typ01, typ02, typ03, typ04, typ05, favIds);
			
			
//			if (catIds!=null) {
//				List<TaTpyCategoryEntity> entIds = TaTpyCategoryEntity.DAO.reqList_In(TaTpyCategoryEntity.ATT_I_TPY_CATEGORY, catIds);
//				cri = Restrictions.and(cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_03, typ03));
//			}
			
			
			List<TaMatMaterialOBS> list		= TaMatMaterialOBS.DAO.reqList(begin, number, Order.asc(TaMatMaterialOBS.ATT_T_NAME_01), cri);		
			
			if(withAvatar) {
				TaTpyDocument.doBuildTpyDocuments(list, 
						DefDBExt.ID_TA_MAT_MATERIAL, 
						TaTpyDocument.TYPE_01_FILE_MEDIA, 
						TaTpyDocument.TYPE_02_FILE_IMG_AVATAR, TaMatMaterialOBS.ATT_O_DOCUMENTS, false);
			}

			if (total == 0 )	total		= TaMatMaterialOBS.DAO.reqCount(cri).intValue();
			rs								= new ResultPagination(list, total, begin, number);
//			if (addCache) cacheEnt_rs.reqPut(keyWord, rs);			
		
		} else {
//			ToolLogServer.doLogInf("---reqGetMat use cache-----");
//			cacheEnt_rs.reqCheckIfOld(keyWord); //cache in 2 hour
		}

		return rs;

	}
	
	private static Set<Integer> STAT01_ALL = new HashSet<Integer>(){
		{
			add(TaMatMaterialOBS.STAT_01_NEW);
			add(TaMatMaterialOBS.STAT_01_ACTIVE);
			add(TaMatMaterialOBS.STAT_01_ACTIVE_02);
			add(TaMatMaterialOBS.STAT_01_ACTIVE_03);
			add(TaMatMaterialOBS.STAT_01_REVIEW);
		}
	};
	private static Criterion reqRestrictionPagination(
			Set<String> searchKey, 
			Integer stat01	, Integer stat02, Integer stat03, Integer stat04, Integer stat05,
			Integer typ01	, Integer typ02	, Integer typ03	, Integer typ04	, Integer typ05,
			Set<Integer> favIds) throws Exception {		
		
		Criterion cri = null;
		if (stat01 == null)
			cri = Restrictions.in(TaMatMaterialOBS.ATT_I_STATUS_01, STAT01_ALL);
		else
			cri = Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_01, stat01);
		
		if (stat02!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_02, stat02));
		}
		if (stat03!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_03, stat03));
		}
		if (stat04!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_04, stat04));
		}
		if (stat05!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_STATUS_05, stat05));
		}
		
		int colToSearch = 0; // => All
		if(typ01!=null && typ01>0) {
			colToSearch = typ01%10;
			typ01 		= (typ01/10)*10;
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_01 , typ01));
		}
		
		if(typ02!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_02 , typ02));
		}
		if(typ03!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_03 , typ03));
		}
		if(typ04!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_04 , typ04));
		}
		if(typ05!=null) {
			cri = Restrictions.and(	cri, Restrictions.eq(TaMatMaterialOBS.ATT_I_TYPE_05 , typ05));
		}

		if (searchKey!=null) {
			for (String s : searchKey){
				s="%"+s+"%";
				s=s.replace("*", "%");
				
				switch(colToSearch){
				case 0	: cri = 	 Restrictions.and	(cri, Restrictions.or(
																Restrictions.ilike(TaMatMaterialOBS.ATT_T_NAME_01	, s), 
																Restrictions.ilike(TaMatMaterialOBS.ATT_T_CODE_01	, s),
																Restrictions.ilike(TaMatMaterialOBS.ATT_T_CODE_02	, s),
																Restrictions.ilike(TaMatMaterialOBS.ATT_T_INFO_01	, s)
						)); break;
				case 1	: cri = 	 Restrictions.and	(cri, Restrictions.or(
						Restrictions.ilike(TaMatMaterialOBS.ATT_T_CODE_01	, s)
						)); break;
				}
				
				
			}
		}
		
		if (favIds!=null && favIds.size()>0) {
			cri = Restrictions.and(	cri, Restrictions.in(TaMatMaterialOBS.ATT_I_ID , favIds));
		}
		return cri;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------
	
	private static Hashtable<String,Integer> mapCol = new Hashtable<String, Integer>(){
		{
			put("action", -1);
			put("id"	, 0 );
			put("name01", 1 );
			put("name02", 2 );
			put("code01", 3 );
			put("code02", 4 );
			put("code03", 5 );
			put("code04", 6 );
			put("stat"	, 7 );
			
			put("inf01",  11 );
			put("inf02",  12 );
			put("inf03",  13 );
			put("inf04",  14 );
			put("inf05",  15 );
			put("inf06",  16 );
			put("inf07",  17 );
			put("inf08",  18 );
			put("inf09",  19 );
			put("inf10",  20 );
		}
	};
	
	private static Hashtable<String,String> mapColJsAtt = new Hashtable<String, String>(){
		{
			put("action", "");
			put("id"	, TaMatMaterialOBS.ATT_I_ID );
			put("name01", TaMatMaterialOBS.ATT_T_NAME_01 );
			put("name02", TaMatMaterialOBS.ATT_T_NAME_02 );
			
			put("code01", TaMatMaterialOBS.ATT_T_CODE_01 );
			put("code02", TaMatMaterialOBS.ATT_T_CODE_02);
			put("code03", TaMatMaterialOBS.ATT_T_CODE_03 );
			put("code04", TaMatMaterialOBS.ATT_T_CODE_04 );
			
			put("stat"	, TaMatMaterialOBS.ATT_I_STATUS_01 );
			put("stat01", TaMatMaterialOBS.ATT_I_STATUS_01 );
			put("stat02", TaMatMaterialOBS.ATT_I_STATUS_02 );
			put("stat03", TaMatMaterialOBS.ATT_I_STATUS_03 );
			put("stat04", TaMatMaterialOBS.ATT_I_STATUS_04 );
			put("stat05", TaMatMaterialOBS.ATT_I_STATUS_05 );
			
			put("typ01", TaMatMaterialOBS.ATT_I_TYPE_01 );
			put("typ02", TaMatMaterialOBS.ATT_I_TYPE_02 );
			put("typ03", TaMatMaterialOBS.ATT_I_TYPE_03 );
			put("typ04", TaMatMaterialOBS.ATT_I_TYPE_04 );
			
			put("inf01" , TaMatMaterialOBS.ATT_T_INFO_01 );
			put("inf02" , TaMatMaterialOBS.ATT_T_INFO_02 );
			put("inf03" , TaMatMaterialOBS.ATT_T_INFO_03 );
			put("inf04" , TaMatMaterialOBS.ATT_T_INFO_04 );
			put("inf05" , TaMatMaterialOBS.ATT_T_INFO_05 );
			put("inf06" , TaMatMaterialOBS.ATT_T_INFO_06 );
			put("inf07" , TaMatMaterialOBS.ATT_T_INFO_07 );
			put("inf08" , TaMatMaterialOBS.ATT_T_INFO_08 );
			put("inf09" , TaMatMaterialOBS.ATT_T_INFO_09 );
			put("inf10" , TaMatMaterialOBS.ATT_T_INFO_10 );
		}
	};
	
	
	private static void doLstDyn(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		Object[]  			dataTableOption = ToolDatatable.reqDataTableOptionJSNameToDBName (json, mapColJsAtt);
		
		Object				searchKey		= 				dataTableOption[0];	
		Set<String>			searchOptCol	= (Set<String>)	dataTableOption[5];
		String				searchOptOpe	= (String)	   	dataTableOption[6]; //null, like, eq, in, between
		
		
		Set<Integer>		typ01			= ToolData.reqSetInt	(json, "typ01"		, null);
		Set<Integer>		typ02			= ToolData.reqSetInt	(json, "typ02"		, null);
		Set<Integer>		typ03			= ToolData.reqSetInt	(json, "typ03"		, null);
		JSONArray			whereCond		= ToolData.reqJsonArr 	(json, "whereCond"	, null);
		
//		Set<Integer>		stat01			= ToolData.reqSetInt	(json, "stat01"		, null);
//		Set<Integer>		cat				= ToolData.reqSetInt	(json, "cat"		, null);
//		Integer 			manId	 		= null;
		
		if(typ01 == null ) { //minimun
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		
		//-------------------------------------------------------------------
	

		Criterion 	cri 			= reqRestriction(user, whereCond,  typ01, typ02, searchOptCol, searchOptOpe, searchKey);				
//		String criKey				= manId + "_" + searchKey.toString() + "_" + typ01 +"_" + typ02+"_" + stat; // get list voi du lieu trung voi du lieu dq tim trc

		if(typ03 != null ) { //minimun
			cri = Restrictions.and(cri, Restrictions.in(TaMatMaterialOBS.ATT_I_TYPE_03 , typ03));
		}
		
		//-----------------------------------------------------------------------------
		List<TaMatMaterialOBS> lst 	= reqListDyn(dataTableOption, cri);
		if (lst==null ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		// ----------------------------------------------------------------
		Integer iTotalRecords 			= reqNbListDyn(typ01).intValue(); // tong so dong trong bang
		Integer iTotalDisplayRecords 	= reqNbListDyn(cri).intValue();

		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"iTotalRecords"				, iTotalRecords,
				"iTotalDisplayRecords"		, iTotalDisplayRecords,
				"aaData"					, lst
				));
	}
	private static Criterion reqRestriction(
			TaAutUser 		user		, 
			JSONArray		whereCond	,
//			Set<Integer> 	stats		, 
			Set<Integer> 	typ01		, Set<Integer> 	typ02,
			Set<String> 	searchCol	, String searchOptOpe	, Object sKey
			) throws Exception {	
		//--Pre-Check condition---------------------------------------------------
		Criterion cri =  Restrictions.gt(TaMatMaterialOBS.ATT_I_ID,0);//Restrictions.ne(TaMatMaterialOBS.ATT_I_STATUS_01 , TaMatMaterialOBS.STAT_01_DELETED);
		 		  cri =  Restrictions.and(cri, Restrictions.in(TaMatMaterialOBS.ATT_I_TYPE_01 , typ01));
		 		  
		if(typ02!=null) {
			cri = Restrictions.and(	cri, Restrictions.in(TaMatMaterialOBS.ATT_I_TYPE_02 , typ02));
		}
		
		if (whereCond!=null && whereCond.size()>0) {
			for (Object obj: whereCond) {
				JSONObject 	cond 	= (JSONObject) obj;
				String		dbCol 	= mapColJsAtt.get(cond.get("col"));
				if (dbCol==null) continue;
				
				String 		ope		= ToolData.reqStr(cond, "ope", "like"); 
				String 		typ		= ToolData.reqStr(cond, "typ", "str");
				String 		val		= ToolData.reqStr(cond, "val", "");

				if (val.contains("%")) ope = "like";
				

				ope					= ope.toLowerCase();
				if (ope.equals("like"))
					cri = 	Restrictions.and(cri, Restrictions.ilike (dbCol, val));
				else if (ope.equals("is null"))
					cri = 	Restrictions.and(cri, Restrictions.isNull(dbCol));
				else if (ope.equals("is not null"))
					cri = 	Restrictions.and(cri, Restrictions.isNotNull(dbCol));
				
				else if (ope.equals("in")){
					if (typ.equals("int")) 
						cri = 	Restrictions.and(cri, Restrictions.in (dbCol, ToolSet.reqSetInt(val)));
					else if (typ.equals("double")) 
						cri = 	Restrictions.and(cri, Restrictions.in (dbCol, ToolSet.reqSetDouble(val)));
					else
						cri = 	Restrictions.and(cri, Restrictions.in (dbCol, ToolSet.reqSetStr(val)));

				}else {
					Object v;
					if (typ.equals("int")) 
						v = Integer.parseInt(val);
					else if (typ.equals("double")) 
						v = Double.parseDouble(val);
					else
						v = val;

					if (ope.equals("=")||ope.equals("=="))
						cri = 	Restrictions.and(cri, Restrictions.eq (dbCol, v));
					else if (ope.equals(">="))
						cri = 	Restrictions.and(cri, Restrictions.ge (dbCol, v));
					else if (ope.equals(">"))
						cri = 	Restrictions.and(cri, Restrictions.gt (dbCol, v));
					else if (ope.equals("<="))
						cri = 	Restrictions.and(cri, Restrictions.le (dbCol, v));
					else if (ope.equals("<"))
						cri = 	Restrictions.and(cri, Restrictions.lt (dbCol, v));
				}
			}
		}
		
		if (sKey!=null && searchCol!=null&&searchCol.size()>0) {
			if (searchOptOpe==null||searchOptOpe.equals("like")) {
				Set<String> searchKey = (Set<String>) sKey;
				if (searchKey.size()>0) { 
					for (String s : searchKey) {
						Criterion	colCri = null;
						for (String col: searchCol) {
							String		dbCol 	= mapColJsAtt.get(col);
							if (dbCol==null) continue;
							if (colCri==null) 
								colCri 	= Restrictions.ilike(dbCol, s);
							else
								colCri	= Restrictions.or(colCri, Restrictions.ilike(dbCol, s));
						}	

						if (colCri!=null) cri = Restrictions.and(cri, colCri);
					}
				}
			}else if (searchOptOpe.equals("in")) {
				Criterion		colCri 		= null;
				TaMatMaterialOBS	test   		= new TaMatMaterialOBS();
				Set<String> 	searchKey  	= (Set<String>) sKey;
				if (searchKey.size()>0) { 
					for (String col: searchCol) {
						String		dbCol 		= mapColJsAtt.get(col);
						if (dbCol==null) continue;
						
						Set 			setVal 	= ToolData.reqSet(searchKey, test.reqDataType(dbCol)) ;
						if (colCri==null) 
							colCri 	= Restrictions.in(dbCol, setVal);
						else
							colCri	= Restrictions.or(colCri, Restrictions.in(dbCol, setVal));
					}
					if (colCri!=null) cri = Restrictions.and(cri, colCri);
				}
			}else if (searchOptOpe.equals("between")) {
				List<String> searchKey 	= (List<String>) sKey;
				
				if (searchKey.size()>=2) {
					Criterion		colCri = null;					
					for (String col: searchCol) {
						String		dbCol 		= mapColJsAtt.get(col);
						if (dbCol==null) continue;
						
						if (colCri==null) 
							colCri 	= Restrictions.between(dbCol, searchKey.get(0), searchKey.get(1));
						else
							colCri	= Restrictions.or(colCri, Restrictions.between(dbCol, searchKey.get(0), searchKey.get(1)));
					}
					if (colCri!=null) cri = Restrictions.and(cri, colCri);
				}
			}
		}	
		
		return cri;
	}
	private static List<TaMatMaterialOBS> reqListDyn(Object[] dataTableOption, Criterion cri) throws Exception {	
		Set<String>		searchKey	= (Set<String>)	dataTableOption[0];	
		int 			begin 		= (int)			dataTableOption[1];
		int 			number 		= (int)			dataTableOption[2]; 
		String 			sortCol 	= (String)		dataTableOption[3]; 
		int 			sortTyp 	= (int)			dataTableOption[4];	

		List<TaMatMaterialOBS> list = new ArrayList<TaMatMaterialOBS>();		

		Order 	order 	= null;	
		if (sortCol==null)
		 		sortCol = TaMatMaterialOBS.ATT_T_NAME_01;; // sap xep 

 		switch(sortTyp){
 		case 0 : order = Order.asc(sortCol) ; break;
 		case 1 : order = Order.desc(sortCol); break;		
 		}

		if (order==null)
			list	= TaMatMaterialOBS.DAO.reqList(begin, number, cri);
		else
			list	= TaMatMaterialOBS.DAO.reqList(begin, number, order, cri);	

		return list;
	}

	private static Number reqNbListDyn(Set<Integer> typ01) throws Exception {	
		if (typ01!=null){
			Criterion cri = Restrictions.in(TaMatMaterialOBS.ATT_I_TYPE_01, typ01);
			return TaMatMaterialOBS.DAO.reqCount(cri);		
		}
		
		return TaMatMaterialOBS.DAO.reqCount();	
	}

	private static Number reqNbListDyn(Criterion cri) throws Exception {			
		return TaMatMaterialOBS.DAO.reqCount(cri);
	}
	//-----------------------------------------------------------------------------------------------------------------------
	private static void doLstDynSQL(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		Object[]  			dataTableOption = ToolDatatable.reqDataTableOptionJSNameToDBName (json, mapColJsAtt);
		Object				searchKey		= (Set<String>)dataTableOption[0];	
		Set<String>			searchOptCol	= (Set<String>)dataTableOption[5];	
		String				searchOptOpe	= (String)	   	dataTableOption[6]; //null, like, eq, in, between
		
		Set<Integer>		stat01			= ToolData.reqSetInt	(json, "stat01"		, null);
		Set<Integer>		typ01			= ToolData.reqSetInt	(json, "typ01"		, null);
		Set<Integer>		typ02			= ToolData.reqSetInt	(json, "typ02"		, null);
		Integer				cat				= ToolData.reqInt		(json, "cat"		, 0);
		Integer 			manId	 		= null;
		
		
		if(typ01 == null && stat01 ==null) {
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		
		//-------------------------------------------------------------------
	
		String sql 					= reqWhere(user,  null, stat01, typ01, typ02, cat, searchOptCol, searchOptOpe, searchKey);				

		//-----------------------------------------------------------------------------
		List<TaMatMaterialOBS> lst 	= reqListDynSQL(dataTableOption, sql);
		if (lst==null ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		// ----------------------------------------------------------------
		String  sqlCountAll				= select_Count + String.format(cond_join_cat, cat);
		String  sqlCountCond			= sql.replace(select_mat, select_Count);
		
		Integer iTotalRecords 			= reqNbListDynSQL(sqlCountAll ).intValue(); // tong so dong trong bang
		Integer iTotalDisplayRecords 	= reqNbListDynSQL(sqlCountCond).intValue();

		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"iTotalRecords"				, iTotalRecords,
				"iTotalDisplayRecords"		, iTotalDisplayRecords,
				"aaData"					, lst
				));
	}
	
	private static String select_Count			= " select count(*) from ";
	
	private static String select_mat			= "select m.* from " ;
	private static String cond_join_cat 		= DefDBExt.TA_MAT_MATERIAL 			+ " m inner join " 
												+ DefDBExt.TA_TPY_CATEGORY_ENTITY 	+ " c on " 
												+ "     c."+  TaTpyCategoryEntity.COL_I_ENTITY_ID    + " = m." + TaMatMaterialOBS.COL_I_ID 	
												+ " and c." + TaTpyCategoryEntity.COL_I_ENTITY_TYPE  + " = "   + DefDBExt.ID_TA_MAT_MATERIAL 
												+ " and c." + TaTpyCategoryEntity.COL_I_TPY_CATEGORY + " = %d";
	
	private static String cond_where_stat01 	= "m." + TaMatMaterialOBS.COL_I_STATUS_01 + " in %s";
	private static String cond_where_typ01 		= "m." + TaMatMaterialOBS.COL_I_TYPE_01 + " in %s";
	
	private static String cond_where_filter_00 	= "(lower(m." + TaMatMaterialOBS.ATT_T_NAME_01 + ") like  lower('%s') or " //1
												+  "lower(m." + TaMatMaterialOBS.ATT_T_CODE_01 + ") like  lower('%s') or "  //2
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_01 + ") like  lower('%s') or "  //3
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_02 + ") like  lower('%s') or "  //4
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_03 + ") like  lower('%s') or "  //5
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_04 + ") like  lower('%s') or "  //6
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_05 + ") like  lower('%s') or "  //7
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_06 + ") like  lower('%s') or "  //8
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_07 + ") like  lower('%s') or "  //9
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_08 + ") like  lower('%s') or "  //10
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_09 + ") like  lower('%s') or "  //11
												+  "lower(m." + TaMatMaterialOBS.ATT_T_INFO_10 + ") like  lower('%s') ) ";  //12
	private static String cond_where_filter_01 	= "(lower(m." + TaMatMaterialOBS.ATT_T_NAME_01 + ") like  lower('%s') ) "; //1
	private static String cond_where_filter_02 	= "(lower(m." + TaMatMaterialOBS.ATT_T_CODE_01 + ") like  lower('%s') ) "; //1

	private static String cond_where_filterLk_colStr	= "(lower(m.%s) like  lower('%s'))";
	private static String cond_where_filterIn_colStr	= "(m.%s in %s)";
	private static String cond_where_filterBt_colStr	= "(m.%s between %s and %s)";

	private static String reqWhere(
			TaAutUser user,  Integer manId, 
			Set<Integer> stats, 
			Set<Integer> typ01, Set<Integer> typ02, 
			Integer cat,
			Set<String> 	searchCol	, String searchOptOpe, Object sKey  ) throws Exception {	
		//--Pre-Check condition---------------------------------------------------
		
		
		if (stats == null){
			stats = new HashSet<Integer>() ; 
			stats.add(TaMatMaterialOBS.STAT_01_ACTIVE);
		}
		
		String sql = select_mat 
				+ String.format(cond_join_cat, cat) 
				+ " where " 
				+  String.format(cond_where_stat01, ToolSet.reqStr(stats, "(", ",", ")" )) ;
				
		
		if(typ01!=null) {
			sql = sql 	+ " and "
						+  String.format(cond_where_typ01, ToolSet.reqStr(typ01, "(", ",", ")" )) ;
		}
		
		if (sKey!=null && searchCol!=null&&searchCol.size()>0) {
			if (searchOptOpe==null||searchOptOpe.equals("like")) {
				Set<String> searchKey = (Set<String>) sKey;
				if (searchKey.size()>0) { 
					for (String s : searchKey) {
						String	sub = null;
						for (String col: searchCol) {
							String		dbCol 	= mapColJsAtt.get(col);
							if (dbCol==null) continue;
							
							if (sub==null) 
								sub = String.format(cond_where_filterLk_colStr, dbCol, s);
							else
								sub	= sub + " or " + String.format(cond_where_filterLk_colStr, dbCol, s) ;
						}	

						if (sub!=null) sql = sql 	+ " and (" +  sub + ") ";
					}
				}
			}else if (searchOptOpe.equals("in")) {
				Criterion		colCri 		= null;
				TaMatMaterialOBS	test   		= new TaMatMaterialOBS();
				Set<String> 	searchKey  	= (Set<String>) sKey;
				if (searchKey.size()>0) { 
					String	sub = null;
					for (String col: searchCol) {
						String		dbCol 		= mapColJsAtt.get(col);
						if (dbCol==null) continue;
						
						Set 			setVal 	= ToolData.reqSet(searchKey, test.reqDataType(dbCol)) ;
						
						if (sub==null) 
							sub = String.format(cond_where_filterIn_colStr, dbCol, ToolSet.reqStr(setVal, "(", ",", ")"));
						else
							sub	= sub + " or " + String.format(cond_where_filterIn_colStr, dbCol, ToolSet.reqStr(setVal, "(", ",", ")")) ;
					}
					if (sub!=null) sql = sql 	+ " and (" +  sub + ") ";
				}
			}else if (searchOptOpe.equals("between")) {
				List<String> searchKey 	= (List<String>) sKey;
				
				if (searchKey.size()>=2) {
					String	sub = null;
					for (String col: searchCol) {
						String		dbCol 		= mapColJsAtt.get(col);
						if (dbCol==null) continue;
						
						if (sub==null) 
							sub = String.format(cond_where_filterBt_colStr, dbCol, searchKey.get(0), searchKey.get(1));
						else
							sub	= sub + " or " + String.format(cond_where_filterBt_colStr, dbCol, searchKey.get(0), searchKey.get(1)) ;
						
					}
					if (sub!=null) sql = sql 	+ " and (" +  sub + ") ";
				}
			}
		}
		
		return sql;
	}
	
	private static List<TaMatMaterialOBS> reqListDynSQL(Object[] dataTableOption, String sql) throws Exception {	
		Set<String>		searchKey	= (Set<String>)	dataTableOption[0];	
		int 			begin 		= (int)			dataTableOption[1];
		int 			number 		= (int)			dataTableOption[2]; 
		String 			sortCol 	= (String)		dataTableOption[3]; 
		int 			sortTyp 	= (int)			dataTableOption[4];	

		List<TaMatMaterialOBS> list = new ArrayList<TaMatMaterialOBS>();		

		String 	order 	= null;	
		if (sortCol==null)
		 		sortCol = TaMatMaterialOBS.ATT_T_NAME_01;; // sap xep 
		 		
		sortCol		= "m." + sortCol;


		if (sortCol!=null){
			switch(sortTyp){
			case 0: order = " order by " + sortCol + " asc" ; break;
			case 1: order = " order by " + sortCol + " desc"; break;				
			}
		}

		if (order!=null)
			sql = sql + order;
			
		list	= TaMatMaterialOBS.DAO.reqList(begin, number, sql);
		return list;
	}

	private static Number reqNbListDynSQL(String sql) throws Exception {	
		return TaMatMaterialOBS.DAO.reqCount(sql);	
	}

	private static void doLstExp(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		
//		Set<Integer>		stat01			= ToolData.reqSetInt	(json, "stat01"		, null);
		Set<Integer>		typ01			= ToolData.reqSetInt	(json, "typ01"		, null);
		Set<Integer>		typ02			= ToolData.reqSetInt	(json, "typ02"		, null);
		List<String>		colExport	    = ToolData.reqListStr 	(json, "colExport"	, ",;", null);
		List<String>		colLab	    	= ToolData.reqListStr 	(json, "colLab"		, ",;", null);
//		Set<Integer>		cat				= ToolData.reqSetInt	(json, "catIds"		, null);
		String				category		= ToolData.reqStr		(json, "cat"		, null);
		Integer 			manId	 		= null;
		
		
		JSONArray			whereCond		= ToolData.reqJsonArr 	(json, "whereCond"	, null);
		
		if(typ01 == null ) {
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		
		//-------------------------------------------------------------------
	

		Criterion 	cri 			= reqRestriction(user, whereCond,  typ01, typ02, null, null, null);	
//		cri = Restrictions.and(	cri, Restrictions.in(TaMatMaterialOBS.ATT_O_CATS , cat));

//		String criKey				= manId + "_" + searchKey.toString() + "_" + typ01 +"_" + typ02+"_" + stat; // get list voi du lieu trung voi du lieu dq tim trc

		//-----------------------------------------------------------------------------
		List<TaMatMaterialOBS> lst 	= reqListExp(cri);
				
		if (lst==null ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
//		Call exportFile
		String pathFile = DefAPIExt.LOC_PATH_EXP_CSV + File.separator;
		String fileName = category+ "_" + ToolDate.reqString(new Date(), "yyMMddHHmmss") + ".csv";
		FileExportOBS.doExport(lst, colExport, colLab, pathFile+fileName);

		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"pathFile"					, DefAPIExt.API_PATH_EXP_CSV +"/" + fileName
				));
	}
	
	private static List<TaMatMaterialOBS> reqListExp(Criterion cri) throws Exception {		
		List<TaMatMaterialOBS> list = new ArrayList<TaMatMaterialOBS>();		
		list	= TaMatMaterialOBS.DAO.reqList(cri);

		return list;
	}
	private void doGetLstDex(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {

		Integer		typ01			= ToolData.reqInt	(json, "typ01"		, null);
		Integer		typ02			= ToolData.reqInt	(json, "typ02"		, null);
		Integer		entId			= ToolData.reqInt	(json, "entId"		, null);
		
		
		if(entId == null ) {
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		
		//-------------------------------------------------------------------
		//-----------------------------------------------------------------------------
		List<TaTpyDocument> lst 	= TaTpyDocument.reqList(DefDBExt.ID_TA_MAT_MATERIAL, entId, typ01, typ02, null);
				
		if (lst==null||lst.size()==0){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				DefJS.RES_DATA			    , lst
				));
		
	}
}
