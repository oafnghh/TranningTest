package com.hnv.api.service.priv.mat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
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
import com.hnv.api.def.DefJS;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.common.APIAuth;
import com.hnv.api.service.priv.mat.util.FileImport;
import com.hnv.common.tool.ToolDBLock;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDatatable;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.tool.ToolSet;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.mat.TaMatMaterial;
import com.hnv.db.mat.TaMatMaterialDetail;
import com.hnv.db.mat.TaMatPrice;
import com.hnv.db.mat.vi.ViMatMaterialDyn;
import com.hnv.db.per.TaPerPerson;
import com.hnv.db.sor.TaSorOrder;
import com.hnv.db.sor.TaSorOrderDetail;
import com.hnv.db.sys.TaSysLock;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.def.DefDBExt;
import com.hnv.def.DefRight;

/**
 * ----- ServiceMatMaterial by H&V ----- Copyright 2017------------
 */


public class ServiceMatMaterial implements IService{
	private static String filePath = null;
	private static String urlPath = null;

	// --------------------------------Service
	// Definition----------------------------------
	public static final String SV_MODULE 			= "EC_V3".toLowerCase();

	public static final String SV_CLASS 			= "ServiceMatMaterial".toLowerCase();

	public static final String SV_GET 				= "SVGet".toLowerCase();
	public static final String SV_LST 				= "SVLst".toLowerCase();

	public static final String SV_NEW 				= "SVNew".toLowerCase();
	public static final String SV_MOD 				= "SVMod".toLowerCase();
	public static final String SV_DEL 				= "SVDel".toLowerCase();


	public static final String SV_LCK_REQ 			= "SVLckReq".toLowerCase(); // req or refresh
	public static final String SV_LCK_SAV 			= "SVLckSav".toLowerCase(); //
	public static final String SV_LCK_END 			= "SVLckEnd".toLowerCase();
	public static final String SV_LCK_DEL 			= "SVLckDel".toLowerCase();

	public static final String SV_LST_DYN			= "SVLstDyn".toLowerCase(); 
	public static final String SV_SEARCH 			= "SVSearch".toLowerCase();

	public static final String SV_GROUP_SEARCH 		= "SVMatGroupSearch".toLowerCase();
	public static final String SV_UNIT_SEARCH 		= "SVMatUnitSearch".toLowerCase();
	public static final String SV_LST_PROVIDER 		= "SVMatLstProvider".toLowerCase();

	public static final String SV_FILES 			= "SVFiles".toLowerCase();

	public static final String SV_FILE_EXTRACT 		= "SVMatFileExtract".toLowerCase();

	public static final String SV_ALERT_INFO 		= "SVMatAlertInfo".toLowerCase();

	public static final String SV_TRANSFERT_INFO 	= "SVMatTransfertInfo".toLowerCase();


	public static final String SV_STK_QUANT 		= "SVMatStkQuant".toLowerCase(); // chi thay doi label cua don vi can
	// ban, ty le khong thay doi

	public static final String SV_DETAIL_INF_GET 	= "SVMatDetInfGet".toLowerCase(); // chi thay doi label cua don
	// vi can ban, ty le khong
	// thay doi

	public static final String SV_BARCODE_CHK 		= "SVBarcodeChk".toLowerCase();

	
	public static final String SV_FILE_IMPORT 		= "SVFileImport".toLowerCase();
	
	public static final Integer	ENT_TYP				= DefDBExt.ID_TA_MAT_MATERIAL;
	private static final Object SV_UNIT_BASE_CHANGE = null;
	// -----------------------------------------------------------------------------------------------
	// -------------------------Default Constructor - Required
	// -------------------------------------
	public ServiceMatMaterial() {
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
	}

	// -----------------------------------------------------------------------------------------------

	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogInf("--------- "+ SV_CLASS+ ".doService --------------");

		if (filePath == null) 	filePath 	= API.reqContextParameter("MAT_MATERIAL_PATH_FILE");
		if (urlPath == null)	urlPath 	= API.reqContextParameter("MAT_MATERIAL_PATH_URL");

		String 		sv 		= API.reqSVFunctName(json);
		TaAutUser 	user	= (TaAutUser) json.get("userInfo");
		try {

			if(sv.equals(SV_GET) 						&&  (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_GET)
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doGet(user, json, response);
			
			} else if(sv.equals(SV_LST_DYN)				&&  (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_GET)
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doLstDyn(user, json, response);
			
			} else if(sv.equals(SV_LST)					&&  (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_GET)
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doLst(user, json, response);

				//			} else if (sv.equals(SV_PRICE)				&& APIAuth.canAuthorize(user, SV_CLASS, svMAT_PRICE_G)) {
				//			} else if (sv.equals(SV_PRICE_LST)			&& APIAuth.canAuthorize(user, SV_CLASS, svMAT_PRICE_G)) {
				//			} else if (sv.equals(SV_PRICE_LST_BY_DATE)	&& APIAuth.canAuthorize(user, SV_CLASS, svMAT_PRICE_G)) {

			} else if (sv.equals(SV_NEW)				&& (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_NEW) 
															|| 	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doNew(user, json, response);

			} else if (sv.equals(SV_MOD)				&& (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD) 
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doMod(user, json, response);

			} else if (sv.equals(SV_DEL) 				&& (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_DEL)
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doDel(user, json, response);

			} else if (sv.equals(SV_SEARCH) 			&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doSearch(user, json, response);

			} else if (sv.equals(SV_LCK_REQ)			&& (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD) 
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doLckReq(user, json, response);
			} else if (sv.equals(SV_LCK_SAV)			&& (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD) 
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doLckSav(user, json, response);
			} else if (sv.equals(SV_LCK_END)			&& (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD) 
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doLckEnd(user, json, response);				
			} else if (sv.equals(SV_LCK_DEL)			&& (	APIAuth.canAuthorizeWithOneRight(user, APIAuth.R_ADMIN, APIAuth.R_AUT_ALL_MOD) 
															||	APIAuth.canAuthorize(user, SV_CLASS, sv))) {
				doLckDel(user, json, response);


			} else if (sv.equals(SV_GROUP_SEARCH) 				&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {
			} else if (sv.equals(SV_UNIT_SEARCH) 				&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {
			} else if (sv.equals(SV_LST_PROVIDER)			 	&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {
			} else if (sv.equals(SV_FILE_EXTRACT) 				&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {
			} else if (sv.equals(SV_ALERT_INFO) 				&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {
			} else if (sv.equals(SV_TRANSFERT_INFO) 			&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {
			
			} else if (sv.equals(SV_DETAIL_INF_GET) 			&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {

			} else if (sv.equals(SV_BARCODE_CHK)) {
				doBarcodeChk(user, json, response);

			} else if (sv.equals(SV_FILE_IMPORT)) {
				doFileImport(user, json, response);	
				
			} else {
				API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			}
		} catch (Exception e) {
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			e.printStackTrace();
		}
	}
	// ---------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------

	private static final int WORK_GET = 1;
	private static final int WORK_LST = 2;
	private static final int WORK_NEW = 3;
	private static final int WORK_MOD = 4;
	private static final int WORK_DEL = 5;
	private static final int WORK_UPL = 10; // upload

	private static boolean canWorkWithObj(TaAutUser user, int typWork, Object... params) {
		if (params == null || params.length == 0 || user == null)
			return false;
		// int userType = (int) user.req(TaAutUser.ATT_I_TYPE);
		// if (userType==TYPE_ADM_ALL) return true;

		switch (typWork) {

		case WORK_MOD:
			return true;
		case WORK_GET:
			return true;
		case WORK_DEL:
			return true;
		case WORK_LST:
			return true;
		case WORK_NEW:
			// check something with params
			return true;

		}

		return false;
	}

	private static void doGet(TaAutUser user, JSONObject json, HttpServletResponse response)		throws Exception {
		int 			objId 	= ToolData.reqInt (json, "id"		, 0);
		Boolean 		forced 	= ToolData.reqBool(json, "forced"	, false);
		TaMatMaterial 	ent 	= reqGet(user, objId, forced);

		if (ent != null)
			API.doResponse(response, ToolJSON.reqJSonString( // filter,
					DefJS.SESS_STAT	, 1, 
					DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA	, ent));
		else
			API.doResponse(response, DefAPI.API_MSG_KO);
	}

	private static TaMatMaterial reqGet(TaAutUser user, Integer matId, Boolean forced) throws Exception {
		if (matId == null) return null;

		TaMatMaterial ent = TaMatMaterial.DAO.reqEntityByRef(matId);

		if (ent != null) {
			//----O_Per_Producer------
			ent.doBuildProducer		(forced);

			//----details------
			ent.doBuildChildren		();
			ent.doBuildParents		();

			//------file
			ent.doBuildDocuments	(forced);		

			//------cat
			ent.doBuildCatIds			(forced);
		}
		return ent;
	}

	private static void doLst(TaAutUser user, JSONObject json, HttpServletResponse response)
			throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		List<TaMatMaterial> list = reqLst(user, json);
		if (list == null || list.size() == 0) {
			API.doResponse(response, ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE,
					DefAPI.SV_CODE_API_NO));
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString( // filter,
				DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA,
				list));

	}

	private static List<TaMatMaterial> reqLst(TaAutUser user, JSONObject json, Object... params) throws Exception {
		Set<Integer>		stat01			= ToolData.reqSetInt	(json, "stat01"		, null);
		Set<Integer>		typ01			= ToolData.reqSetInt	(json, "typ01"		, null);
		Set<Integer>		typ02			= ToolData.reqSetInt	(json, "typ02"		, null);
		Integer 			manId	 		= user.reqPerManagerId();
		
		String				sKey			= ToolData.reqStr		(json, "searchkey"	, "%");
		Integer				sTyp			= ToolData.reqInt		(json, "searchType"	, 1); //1: by name, 2: by code, ifno all
		Integer 			nbLine		 	= ToolData.reqInt		(json, "nbLine"		, 10);
		
		Criterion			cri				= reqRestriction(user, sTyp, manId, stat01, typ01, typ02, sKey);
		List<TaMatMaterial> list = TaMatMaterial.DAO.reqList(0, nbLine, Order.asc(TaMatMaterial.ATT_T_NAME_01), cri);

		return list;
	}
	
	private static Criterion reqRestriction(TaAutUser user, int searchType, Integer manId, Set<Integer> stat01, Set<Integer> typ01, Set<Integer> typ02, String searchKey) throws Exception {	
		//--Pre-Check condition---------------------------------------------------
		if (stat01 == null){
			stat01 = new HashSet<Integer>() ; 
			stat01.add(TaMatMaterial.STAT_01_ACTIVE);
		}
		Criterion cri = Restrictions.in(TaMatMaterial.ATT_I_STATUS_01, stat01);
		
		if(typ01!=null) {
			cri = Restrictions.and(	cri, Restrictions.in(TaMatMaterial.ATT_I_TYPE_01 , typ01));
		}
		if(typ02!=null) {
			cri = Restrictions.and(	cri, Restrictions.in(TaMatMaterial.ATT_I_TYPE_02 , typ02));
		}
		if (manId==null && !user.canBeSuperAdmin()) manId = user.reqPerManagerId();
		if (manId!=null) cri = Restrictions.and(cri, Restrictions.eq(TaMatMaterial.ATT_I_PER_MANAGER, manId));
		
		if (searchKey!=null&&searchKey.length()>0) {
			switch(searchType) {
			case 	1: 	cri = 	Restrictions.and(	cri, 
								Restrictions.or(
										Restrictions.ilike(TaMatMaterial.ATT_T_NAME_01		, searchKey),
										Restrictions.ilike(TaMatMaterial.ATT_T_NAME_02		, searchKey)
										));
						break;
			case 	2:	cri = 	Restrictions.and(	cri, 
								Restrictions.or(
										Restrictions.ilike(TaMatMaterial.ATT_T_CODE_01		, searchKey), 
										Restrictions.ilike(TaMatMaterial.ATT_T_CODE_02		, searchKey), 
										Restrictions.ilike(TaMatMaterial.ATT_T_CODE_03		, searchKey), 
										Restrictions.ilike(TaMatMaterial.ATT_T_CODE_04		, searchKey)
										));
						break;
			default: 	cri = 	Restrictions.and(	cri, 
								Restrictions.or(
										Restrictions.ilike(TaMatMaterial.ATT_T_NAME_01		, searchKey),
										Restrictions.ilike(TaMatMaterial.ATT_T_NAME_02		, searchKey),
										Restrictions.ilike(TaMatMaterial.ATT_T_CODE_01		, searchKey), 
										Restrictions.ilike(TaMatMaterial.ATT_T_CODE_02		, searchKey), 
										Restrictions.ilike(TaMatMaterial.ATT_T_CODE_03		, searchKey), 
										Restrictions.ilike(TaMatMaterial.ATT_T_CODE_04		, searchKey)
										));
						break;
			}
		}
		
		return cri;
	}
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private void doLckReq(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckReq --------------");		

		Integer 		entId	= ToolData.reqInt	(json, "id", null);	
		TaSysLock 		lock 	= ToolDBLock.reqLock(ENT_TYP, entId, user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		if (lock==null || lock.reqStatus() != TaSysLock.STAT_ACTIVE){
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
	private void doLckDel(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckDel --------------");

		boolean isDeleted = ToolDBLock.canDeleteLock(json);		
		if (isDeleted)
			API.doResponse(response, DefAPI.API_MSG_OK);		
		else 
			API.doResponse(response, DefAPI.API_MSG_KO);
	}

	private void doLckSav(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckSav --------------");	
		boolean isLocked 	= ToolDBLock.canExistLock(json);
		if(!isLocked){
			API.doResponse(response, DefAPI.API_MSG_ERR_LOCK);
			return;
		}

		TaMatMaterial  		ent	 	=  reqMod(user, json); 								
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
	private void doLckEnd(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckEnd --------------");	
		boolean isLocked 	= ToolDBLock.canExistLock(json);
		if(!isLocked){
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}


		TaMatMaterial ent = reqMod(user, json);				
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
	
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	private static void doNew(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		TaMatMaterial 	ent	 = reqNew(user, json);
		if (ent==null){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		TaSysLock 	lock 	= ToolDBLock.reqLock (json, "lock", DefDB.DB_LOCK_NEW, ENT_TYP, (Integer)ent.reqId(), user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, ent,
				"lock"				, lock
		));
	}


	private static TaMatMaterial reqNew(TaAutUser user, JSONObject json) throws Exception {	
		JSONObject			obj		= ToolData.reqJson 		(json, "obj"	, null);
		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaMatMaterial.class);
		TaMatMaterial  		ent	 	= new TaMatMaterial(attr);
		
		ent.reqSet(TaMatMaterial.ATT_D_DATE_01		, new Date());
		ent.reqSet(TaMatMaterial.ATT_I_AUT_USER_01	, user.req(TaMatMaterial.ATT_I_ID));
		ent.reqSet(TaMatMaterial.ATT_I_PER_MANAGER	, user.req(TaMatMaterial.ATT_I_PER_MANAGER));

		TaMatMaterial.DAO.doPersist(ent);
		
		Integer objId = (Integer)ent.req(TaMatMaterial.ATT_I_ID);

		//----O_Per_Producer------
		Integer pId = (Integer)ent.req(TaMatMaterial.ATT_I_PER_PERSON_01);
		if (pId!=null) {
			TaPerPerson producer = TaPerPerson.DAO.reqEntityByRef((Integer)ent.req(TaMatMaterial.ATT_I_PER_PERSON_01));
			ent.reqSet(TaMatMaterial.ATT_O_PRODUCER		, producer);
		}

		
		int				entId 		= ent.reqId();
		JSONArray 		details 	= ToolData.reqJsonArr	(obj,  "details", null);
		JSONArray 		docs 		= ToolData.reqJsonArr	(obj,  "files"	, null);
		JSONArray 		cats 		= ToolData.reqJsonArr	(obj,  "cats"	, null);
		//-------check details-------------------
		ent.reqSet(TaMatMaterial.ATT_O_CHILDREN	, TaMatMaterialDetail.reqListNew(entId, details));
		ent.doBuildDetailsChildren();
		
		//-------check file --------- ------------
		ent.reqSet(TaMatMaterial.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(DefAPI.SV_MODE_NEW, user, ENT_TYP, entId, docs));
		//-------check cats --------- ------------
		ent.reqSet(TaMatMaterial.ATT_O_CATS		, TaTpyCategoryEntity.reqListNew(ENT_TYP, entId, cats));

		return ent;
	}


	private static void doMod(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		
		TaMatMaterial  		ent	 	=  reqMod(user, json); 								
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

	private static TaMatMaterial reqMod(TaAutUser user, JSONObject json) throws Exception {
		JSONObject			obj		= ToolData.reqJson 	(json	, "obj"	, null);
		int 				entId 	= ToolData.reqInt	(obj	, "id"	, -1);
		TaMatMaterial  		ent	 	= TaMatMaterial.DAO.reqEntityByRef(entId);
		
		if (ent==null){
			return null;
		}

		if (!canWorkWithObj(user, WORK_MOD, ent)){ //other param after obj...
			return null;
		}
		
		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaMatMaterial.class);
		attr.remove	(TaMatMaterial.ATT_D_DATE_01		);
		attr.remove	(TaMatMaterial.ATT_I_AUT_USER_01	);
		attr.put	(TaMatMaterial.ATT_D_DATE_02		,  new Date());
		attr.put	(TaMatMaterial.ATT_I_AUT_USER_02	, user.req(TaMatMaterial.ATT_I_ID));
		
		TaMatMaterial.DAO.doMerge(ent, attr);
		
		//----O_Per_Producer------
		if (ent.req(TaMatMaterial.ATT_I_PER_PERSON_01) != null){
			TaPerPerson producer = TaPerPerson.DAO.reqEntityByRef((Integer)ent.req(TaMatMaterial.ATT_I_PER_PERSON_01));
			ent.reqSet(TaMatMaterial.ATT_O_PRODUCER		, producer);
		}
		
		JSONArray 		details 	= ToolData.reqJsonArr	(obj,  "details", null);
		JSONArray 		docs 		= ToolData.reqJsonArr	(obj,  "files"	, null);
		JSONArray 		cats 		= ToolData.reqJsonArr	(obj,  "cats"	, null);

		//-------check details-------------------
		ent.reqSet(TaMatMaterial.ATT_O_CHILDREN	, TaMatMaterialDetail.reqListMod(entId, details));
		ent.doBuildDetailsChildren();
		//-------check file --------- ------------
		ent.reqSet(TaMatMaterial.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(DefAPI.SV_MODE_MOD, user, ENT_TYP, entId, docs));
		//-------check cats --------- ------------
		ent.reqSet(TaMatMaterial.ATT_O_CATS		, TaTpyCategoryEntity.reqListMod(ENT_TYP, entId, cats));
				
		return ent;
	}

	
	private static void doDel(TaAutUser user,  JSONObject json, HttpServletResponse response) throws Exception  {
		//ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doDel --------------");

		int				entId 	= ToolData.reqInt(json, "id", null);
		TaSysLock 		lock 	= ToolDBLock.reqLock(ENT_TYP, entId, user.reqId(), user.reqStr(TaAutUser.ATT_T_LOGIN_01), null);
		if (lock==null || lock.reqStatus() == 0){
			API.doResponse(response, ToolJSON.reqJSonString(						
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO,
					DefJS.RES_DATA		, lock
					));	
			return;
		}

		if (!canDel(user, json)){
			API.doResponse(response,DefAPI.API_MSG_KO);
		} else {
			API.doResponse(response,DefAPI.API_MSG_OK);
		}

		ToolDBLock.canDeleteLock(lock);
	}
	

	private static boolean canDel(TaAutUser user, JSONObject json) throws Exception {
		Integer 		entId	= ToolData.reqInt	(json, "id", null	);	
		TaMatMaterial  	ent	 	= TaMatMaterial.DAO.reqEntityByRef(entId);
		if (ent==null){
			return false;
		}

		Integer stat = ent.reqInt(TaMatMaterial.COL_I_STATUS_01);
		if (stat.equals(TaMatMaterial.STAT_01_DELETED)) {
			
			if (!canWorkWithObj(user, WORK_DEL, ent)){ //other param after ent...
				return false;
			}	
			
			
			//remove all other object connecting to this ent first-------
			
//			if 3 tables are in 3 different db, we need user 3 session
//			Session sessSub 	= TaTpyDocument	.DAO.reqSessionCurrent();
			Session sessMain 	= TaMatMaterial		.DAO.reqSessionCurrent();
			try {
				TaMatMaterialDetail	.doListDel(sessMain, entId);
				TaTpyCategoryEntity	.doListDel(sessMain, ENT_TYP, entId);
				TaTpyDocument		.doListDel(sessMain, ENT_TYP, entId);
				
				TaMatMaterial.DAO	.doRemove (sessMain, ent);
				
				TaMatMaterial		.DAO.doSessionCommit(sessMain);
//				TaTpyDocument		.DAO.doSessionCommit(sessSub);
			}catch(Exception e){
				e.printStackTrace();
				TaMatMaterial		.DAO.doSessionRollback(sessMain);
//				TaTpyDocument		.DAO.doSessionRollback(sessSub);
			}
			
		} else {
			//Set status = STAT_DELETED
			ent.reqSet(TaMatMaterial.ATT_I_STATUS_01, TaMatMaterial.STAT_01_DELETED);
			TaMatMaterial.DAO.doMerge(ent);	
		}

		return true;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
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
		}
	};
	
	private static void doLstDyn(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		Object[]  			dataTableOption = ToolDatatable.reqDataTableOption (json, mapCol);
		Set<String>			searchKey		= (Set<String>)dataTableOption[0];	
		int					searchOpt		= (int)dataTableOption[5];	
		
		Set<Integer>		stat01			= ToolData.reqSetInt	(json, "stat01"		, null);
		Set<Integer>		typ01			= ToolData.reqSetInt	(json, "typ01"		, null);
		Set<Integer>		typ02			= ToolData.reqSetInt	(json, "typ02"		, null);
		Integer 			manId	 		= user.reqPerManagerId();
		
		
		if(typ01 == null && stat01 ==null) {
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		
		if (!canWorkWithObj(user, WORK_LST, null, stat01)){ //other param after objTyp...
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		//-------------------------------------------------------------------
	
//		if (typ01!= null && typ01.contains(TaMatMaterial.TYPE_01_MAT)) {
//			switch (typ02) {
//			case TaMatMaterial.TYPE_02_SINGLE 	: if (!APIAuth.canAuthorize(user, SV_CLASS, svMAT_MATERIAL_SIMPLE_G)) {
//				API.doResponse(response, DefAPI.API_MSG_KO);
//				return;
//			}
//			break;
//			case TaMatMaterial.TYPE_02_BOM 		: if (!APIAuth.canAuthorize(user, SV_CLASS, sv)) 	{
//				API.doResponse(response, DefAPI.API_MSG_KO);
//				return;
//			}
//			break;
//			}
//		}

		Criterion 	cri 			= reqRestriction(user, searchKey, searchOpt, user.reqPerManagerId(), stat01, typ01, typ02);				
//		String criKey				= manId + "_" + searchKey.toString() + "_" + typ01 +"_" + typ02+"_" + stat; // get list voi du lieu trung voi du lieu dq tim trc

		//-----------------------------------------------------------------------------
		List<ViMatMaterialDyn> lst 	= reqListDyn(dataTableOption, cri);
		if (lst==null ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		// ----------------------------------------------------------------
		Integer iTotalRecords 			= reqNbListDyn(manId).intValue(); // tong so dong trong bang
		Integer iTotalDisplayRecords 	= reqNbListDyn(cri).intValue();

		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"iTotalRecords"				, iTotalRecords,
				"iTotalDisplayRecords"		, iTotalDisplayRecords,
				"aaData"					, lst
				));
	}

	private static Criterion reqRestriction(TaAutUser user,  Set<String> searchKey, int searchFilter, Integer manId, Set<Integer> stats, Set<Integer> typ01, Set<Integer> typ02) throws Exception {	
		//--Pre-Check condition---------------------------------------------------
		if (stats == null){
			stats = new HashSet<Integer>() ; 
			stats.add(TaMatMaterial.STAT_01_ACTIVE);
		}
		Criterion cri = Restrictions.in(TaMatMaterial.ATT_I_STATUS_01, stats);
		
		if(typ01!=null) {
			cri = Restrictions.and(	cri, Restrictions.in(TaMatMaterial.ATT_I_TYPE_01 , typ01));
		}
		if(typ02!=null) {
			cri = Restrictions.and(	cri, Restrictions.in(TaMatMaterial.ATT_I_TYPE_02 , typ02));
		}
		if (manId==null && !user.canBeSuperAdmin()) manId = user.reqPerManagerId();
		if (manId!=null) cri = Restrictions.and(cri, Restrictions.eq(TaMatMaterial.ATT_I_PER_MANAGER, manId));
		
		if (searchKey!=null) {
			switch(searchFilter) {
			case	 0:
				for (String s : searchKey){			
					cri = 	Restrictions.and(	cri, 
							Restrictions.or(
									Restrictions.ilike(TaMatMaterial.ATT_T_CODE_01		, s), 
									Restrictions.ilike(TaMatMaterial.ATT_T_CODE_02		, s), 
									Restrictions.ilike(TaMatMaterial.ATT_T_CODE_03		, s), 
									Restrictions.ilike(TaMatMaterial.ATT_T_NAME_01		, s),
									Restrictions.ilike(TaMatMaterial.ATT_T_NAME_02		, s),
									Restrictions.ilike(TaMatMaterial.ATT_T_INFO_01		, s),
									Restrictions.ilike(TaMatMaterial.ATT_T_INFO_02		, s)
									));
				}; break;

			case 	1: //--search by name 01 - product name
				for (String s : searchKey){			
					cri = 	Restrictions.and(	cri, 
							Restrictions.or(							
									Restrictions.ilike(TaMatMaterial.ATT_T_NAME_01		, s)
									));
				}; break;

			case	2://--search by name 02 - product name
				for (String s : searchKey){			
					cri = 	Restrictions.and(	cri, 
							Restrictions.or(							
									Restrictions.ilike(TaMatMaterial.ATT_T_NAME_02		, s)
									));
				}; break;

			case	3://--search by code 01 - product content
				for (String s : searchKey){			
					cri = 	Restrictions.and(	cri, 
							Restrictions.or(							
									Restrictions.ilike(TaMatMaterial.ATT_T_CODE_01		, s)
									));
				}; break;

			case	4://--search by code 02 - product content
				for (String s : searchKey){			
					cri = 	Restrictions.and(	cri, 
							Restrictions.or(							
									Restrictions.ilike(TaMatMaterial.ATT_T_CODE_02		, s)
									));
				}; break;

			case	5://--search by code 03 - product content
				for (String s : searchKey){			
					cri = 	Restrictions.and(	cri, 
							Restrictions.or(							
									Restrictions.ilike(TaMatMaterial.ATT_T_CODE_03		, s)
									));
				}; break;

			case	6://--search by code 04 - product content
				for (String s : searchKey){			
					cri = 	Restrictions.and(	cri, 
							Restrictions.or(							
									Restrictions.ilike(TaMatMaterial.ATT_T_CODE_04		, s)
									));
				}; break;
			default: 
				for (String s : searchKey){
					cri = Restrictions.and(	cri, Restrictions.or(
							Restrictions.ilike(TaMatMaterial.ATT_T_NAME_01, s), 
							Restrictions.ilike(TaMatMaterial.ATT_T_NAME_01, s),
							Restrictions.ilike(TaMatMaterial.ATT_T_CODE_01, s),
							Restrictions.ilike(TaMatMaterial.ATT_T_CODE_02, s),
							Restrictions.ilike(TaMatMaterial.ATT_T_CODE_03, s),
							Restrictions.ilike(TaMatMaterial.ATT_T_INFO_01, s),
							Restrictions.ilike(TaMatMaterial.ATT_T_INFO_02, s))
							);
				}
			}
		}
		
		return cri;
	}
	

	private static List<ViMatMaterialDyn> reqListDyn(Object[] dataTableOption, Criterion cri) throws Exception {	
		Set<String>		searchKey	= (Set<String>)dataTableOption[0];	
		int 			begin 		= (int)	dataTableOption[1];
		int 			number 		= (int)	dataTableOption[2]; 
		int 			sortCol 	= (int)	dataTableOption[3]; 
		int 			sortTyp 	= (int)	dataTableOption[4];	

		List<ViMatMaterialDyn> list = new ArrayList<ViMatMaterialDyn>();		

		Order 	order 	= null;			
		String 	colName = ViMatMaterialDyn.ATT_T_NAME_01;; // sap xep 

		switch(sortCol){ // sap xep
		case 0: colName = ViMatMaterialDyn.ATT_I_ID; break;
		case 1: colName = ViMatMaterialDyn.ATT_T_NAME_01; break;
		case 2: colName = ViMatMaterialDyn.ATT_T_NAME_02; break;
		case 3: colName = ViMatMaterialDyn.ATT_T_CODE_01; break;	
		case 4: colName = ViMatMaterialDyn.ATT_T_CODE_02; break;	
		case 5: colName = ViMatMaterialDyn.ATT_T_CODE_03; break;	
		case 6: colName = ViMatMaterialDyn.ATT_T_CODE_04; break;	
		case 7: colName = ViMatMaterialDyn.ATT_I_STATUS_01; break;	
		}

		if (colName!=null){
			switch(sortTyp){
			case 0: order = Order.asc(colName); break;
			case 1: order = Order.desc(colName); break;								
			}
		}

		if (order==null)
			list	= ViMatMaterialDyn.DAO.reqList(begin, number, cri);
		else
			list	= ViMatMaterialDyn.DAO.reqList(begin, number, order, cri);	

		return list;
	}

	private static Number reqNbListDyn(Integer manId) throws Exception {	
		if (manId!=null){
			Criterion cri = Restrictions.eq(ViMatMaterialDyn.ATT_I_PER_MANAGER, manId);
			return ViMatMaterialDyn.DAO.reqCount(cri);		
		}
		
		return ViMatMaterialDyn.DAO.reqCount();	
	}

	private static Number reqNbListDyn(Criterion cri) throws Exception {			
		return ViMatMaterialDyn.DAO.reqCount(cri);
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------
	private static void doBarcodeChk(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		Integer				bCodeTyp		= ToolData.reqInt	(json, "bCodeTyp"	, null);
		String				bcode			= ToolData.reqStr	(json, "bcode"		, null);
		Integer 			manId	 		= user.reqPerManagerId();
		if (bcode == null) {
			API.doResponse(response, DefAPI.API_MSG_OK);
			return;
		}
		
		String colSearch = TaMatMaterial.ATT_T_CODE_01;
		switch (bCodeTyp) {
		case 1:  colSearch = TaMatMaterial.ATT_T_CODE_01;break;
		case 2:  colSearch = TaMatMaterial.ATT_T_CODE_02;break;
		case 3:  colSearch = TaMatMaterial.ATT_T_CODE_03;break;
		case 4:  colSearch = TaMatMaterial.ATT_T_CODE_04;break;
		}

		TaMatMaterial mat = TaMatMaterial.DAO.reqEntityByValues(TaMatMaterial.ATT_I_PER_MANAGER, manId, colSearch, bcode);
		if (mat==null) {
			API.doResponse(response, DefAPI.API_MSG_OK);
		}else {
			API.doResponse(response, DefAPI.API_MSG_KO);
		}
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------
	private static void doSearch(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		Integer				nbLine			= ToolData.reqInt	(json, "nbLine"		, null);
		Integer				bCodeTyp		= ToolData.reqInt	(json, "bCodeTyp"	, null);
		Integer				searchType		= ToolData.reqInt	(json, "searchType"	, null);
		Integer				typOpt			= ToolData.reqInt	(json, "typOpt"		, null);
		Integer				typ01			= ToolData.reqInt	(json, "typ01"		, null);
		Integer				typ02			= ToolData.reqInt	(json, "typ02"		, null);
		Integer				typ03			= ToolData.reqInt	(json, "typ03"	, null);
		String				searchkey		= ToolData.reqStr	(json, "searchkey"		, null);
		Integer 			manId	 		= user.reqPerManagerId();
		
		List<TaMatMaterial> lstResult = reqSearch(user, manId, typ01, typ02, typ03, searchkey, searchType, nbLine, typOpt);

		API.doResponse(response, ToolJSON.reqJSonString(	
				DefJS.SESS_STAT		, 1, 
				DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA		, lstResult
				));
	}	

	private static List<TaMatMaterial> reqSearch(TaAutUser user, Integer manId, Integer type01, Integer type02, Integer type03, String searchkey, Integer searchType, Integer nbLine, Integer typOpt) throws Exception {		
		Criterion cri = Restrictions.gt(TaMatMaterial.ATT_I_ID, 0);

		if (manId!=null) {
			cri = Restrictions.and(cri, Restrictions.eq(TaMatMaterial.ATT_I_PER_MANAGER, manId));			
		}
		if(type01 != null){
			cri = Restrictions.and(cri, Restrictions.eq(TaMatMaterial.ATT_I_TYPE_01, type01));			
		} 
		if(type02 != null){		
			cri = Restrictions.and(cri, Restrictions.eq(TaMatMaterial.ATT_I_TYPE_02, type02));			
		}

		if(type03 != null){		
			cri = Restrictions.and(cri, Restrictions.eq(TaMatMaterial.ATT_I_STATUS_01, type03));			
		}

		if (searchkey!=null && searchkey.length()>0) {
			switch(searchType) {
			case 	1: //by code
				cri = 	Restrictions.and(	cri, 
						Restrictions.or(							
								Restrictions.like(TaMatMaterial.ATT_T_CODE_01		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_02		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_03		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_04		, "%"+searchkey+"%")
								));
				break;

			case	2: //by name
				cri = 	Restrictions.and(	cri, 
						Restrictions.or(							
								Restrictions.like(TaMatMaterial.ATT_T_NAME_01		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_NAME_02		, "%"+searchkey+"%")
								));
				break;

			case	3: //all
				cri = 	Restrictions.and(	cri, 
						Restrictions.or(		
								Restrictions.like(TaMatMaterial.ATT_T_CODE_01		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_02		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_03		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_04		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_NAME_01		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_NAME_02		, "%"+searchkey+"%")
								));
				break;

			default:
				cri = 	Restrictions.and(	cri, 
						Restrictions.or(		
								Restrictions.like(TaMatMaterial.ATT_T_CODE_01		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_02		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_03		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_CODE_04		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_NAME_01		, "%"+searchkey+"%"),
								Restrictions.like(TaMatMaterial.ATT_T_NAME_02		, "%"+searchkey+"%")
								));
				break;
			}
		}

//		if (typOpt != null && typOpt > TaSorOrder.TYPE_01_OU_STK_SELL) {
//			cri = 	Restrictions.and(	cri, 
//					Restrictions.or(							
//							Restrictions.eq(TaMatMaterial.ATT_I_TYPE_02		, TaMatMaterial.TYPE_02_SINGLE)
//							));
//		}

		if ( nbLine == null || nbLine <= 0) nbLine = 10;

		return TaMatMaterial.DAO.reqList(0, nbLine, Order.asc(TaMatMaterial.ATT_T_NAME_01), cri);
	}

	
	
	
	private static void doFileImport(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		String				fPath		= ToolData.reqStr	(json, "fPath"		, null);
		Integer				typNode		= ToolData.reqInt	(json, "typ"		, null); 
//		List<String> 		data 		= FileImport.reqReadFile_LstMachine(fPath, "#DEBUT_FICHIER");
//		FileImport.doCheck_LstMachine(data, typNode);

		API.doResponse(response, ToolJSON.reqJSonString( // filter,
					DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_NO));
	}

}