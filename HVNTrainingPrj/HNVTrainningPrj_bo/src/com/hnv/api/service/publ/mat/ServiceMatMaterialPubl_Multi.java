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

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefJS;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.priv.mat.util.FileExportMulti;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDatatable;
import com.hnv.common.tool.ToolDate;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.tool.ToolSet;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.mat.TaMatMaterial;
import com.hnv.db.mat.TaMatMaterialDetail;
import com.hnv.db.mat.vi.ViMatMaterialMulti;
import com.hnv.def.DefAPIExt;
import com.hnv.def.DefDBExt;
/**
* ----- ServiceNsoOffer by H&V
* ----- Copyright 2023------------
*/
public class ServiceMatMaterialPubl_Multi implements IService {
	private static	String 			filePath	= null; 
	private	static	String 			urlPath		= null; 
	

	//--------------------------------Service Definition----------------------------------
	public static final String SV_MODULE 				= "HNV".toLowerCase();

	public static final String SV_CLASS 				= "ServiceMatMaterialPubl_Multi".toLowerCase();	
	
	public static final String SV_GET 					= "SVGet"		.toLowerCase();	
	public static final String SV_LST 					= "SVLst"		.toLowerCase();
	public static final String SV_LST_DYN_SQL			= "SVLstDynSQL"	.toLowerCase();
	public static final String SV_LST_EXP				= "SVLstExp"	.toLowerCase();	
	

	public static final Integer	ENT_TYP					= DefDBExt.ID_TA_MAT_MATERIAL;
		
	//-----------------------------------------------------------------------------------------------
	//-------------------------Default Constructor - Required -------------------------------------
	public ServiceMatMaterialPubl_Multi(){
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
				
			}  else if(sv.equals(SV_LST_DYN_SQL)		){
				doLstDynSQL(user, json, response);
				
			} else if(sv.equals(SV_LST_EXP)		){
				doLstExp(user, json, response);
			} 
			
			 

		}catch(Exception e){
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			e.printStackTrace();
		}		
	}
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------
	private static Hashtable<String,String> mapColJsAtt = new Hashtable<String, String>(){
		{
			put("action"  , "");
			put("id01"	  , "m1."+TaMatMaterial.ATT_I_ID );
			put("name0101", "m1."+TaMatMaterial.ATT_T_NAME_01 );
			put("name0102", "m1."+TaMatMaterial.ATT_T_NAME_02 );
			
			put("code0101", "m1."+TaMatMaterial.ATT_T_CODE_01 );
			put("code0102", "m1."+TaMatMaterial.ATT_T_CODE_02);
			put("code0103", "m1."+TaMatMaterial.ATT_T_CODE_03 );
			put("code0104", "m1."+TaMatMaterial.ATT_T_CODE_04 );
			
			put("stat0101", "m1."+TaMatMaterial.ATT_I_STATUS_01 );
			put("stat0102", "m1."+TaMatMaterial.ATT_I_STATUS_02 );
			put("stat0103", "m1."+TaMatMaterial.ATT_I_STATUS_03 );
			put("stat0104", "m1."+TaMatMaterial.ATT_I_STATUS_04 );
			put("stat0105", "m1."+TaMatMaterial.ATT_I_STATUS_05 );
			
			put("typ0101", "m1."+TaMatMaterial.ATT_I_TYPE_01 );
			put("typ0102", "m1."+TaMatMaterial.ATT_I_TYPE_02 );
			put("typ0103", "m1."+TaMatMaterial.ATT_I_TYPE_03 );
			put("typ0104", "m1."+TaMatMaterial.ATT_I_TYPE_04 );
			
			put("inf0101" , "m1."+TaMatMaterial.ATT_T_INFO_01 );
			put("inf0102" , "m1."+TaMatMaterial.ATT_T_INFO_02 );
			put("inf0103" , "m1."+TaMatMaterial.ATT_T_INFO_03 );
			put("inf0104" , "m1."+TaMatMaterial.ATT_T_INFO_04 );
			put("inf0105" , "m1."+TaMatMaterial.ATT_T_INFO_05 );
			put("inf0106" , "m1."+TaMatMaterial.ATT_T_INFO_06 );
			put("inf0107" , "m1."+TaMatMaterial.ATT_T_INFO_07 );
			put("inf0108" , "m1."+TaMatMaterial.ATT_T_INFO_08 );
			put("inf0109" , "m1."+TaMatMaterial.ATT_T_INFO_09 );
			put("inf0110" , "m1."+TaMatMaterial.ATT_T_INFO_10 );
			
			//-----------------------------------------------------
			put("id02"	  , "m2."+TaMatMaterial.ATT_I_ID );
			put("name0201", "m2."+TaMatMaterial.ATT_T_NAME_01 );
			put("name0202", "m2."+TaMatMaterial.ATT_T_NAME_02 );
			
			put("code0201", "m2."+TaMatMaterial.ATT_T_CODE_01 );
			put("code0202", "m2."+TaMatMaterial.ATT_T_CODE_02);
			put("code0203", "m2."+TaMatMaterial.ATT_T_CODE_03 );
			put("code0204", "m2."+TaMatMaterial.ATT_T_CODE_04 );
			
			put("stat0201", "m2."+TaMatMaterial.ATT_I_STATUS_01 );
			put("stat0202", "m2."+TaMatMaterial.ATT_I_STATUS_02 );
			put("stat0203", "m2."+TaMatMaterial.ATT_I_STATUS_03 );
			put("stat0204", "m2."+TaMatMaterial.ATT_I_STATUS_04 );
			put("stat0205", "m2."+TaMatMaterial.ATT_I_STATUS_05 );
			
			put("typ0201", "m2."+TaMatMaterial.ATT_I_TYPE_01 );
			put("typ0202", "m2."+TaMatMaterial.ATT_I_TYPE_02 );
			put("typ0203", "m2."+TaMatMaterial.ATT_I_TYPE_03 );
			put("typ0204", "m2."+TaMatMaterial.ATT_I_TYPE_04 );
			
			put("inf0201" , "m2."+TaMatMaterial.ATT_T_INFO_01 );
			put("inf0202" , "m2."+TaMatMaterial.ATT_T_INFO_02 );
			put("inf0203" , "m2."+TaMatMaterial.ATT_T_INFO_03 );
			put("inf0204" , "m2."+TaMatMaterial.ATT_T_INFO_04 );
			put("inf0205" , "m2."+TaMatMaterial.ATT_T_INFO_05 );
			put("inf0206" , "m2."+TaMatMaterial.ATT_T_INFO_06 );
			put("inf0207" , "m2."+TaMatMaterial.ATT_T_INFO_07 );
			put("inf0208" , "m2."+TaMatMaterial.ATT_T_INFO_08 );
			put("inf0209" , "m2."+TaMatMaterial.ATT_T_INFO_09 );
			put("inf0210" , "m2."+TaMatMaterial.ATT_T_INFO_10 );
			//-----------------------------------------------------
			put("id03"	  , "m3."+TaMatMaterial.ATT_I_ID );
			put("name0301", "m3."+TaMatMaterial.ATT_T_NAME_01 );
			put("name0302", "m3."+TaMatMaterial.ATT_T_NAME_02 );
			
			put("code0301", "m3."+TaMatMaterial.ATT_T_CODE_01 );
			put("code0302", "m3."+TaMatMaterial.ATT_T_CODE_02);
			put("code0303", "m3."+TaMatMaterial.ATT_T_CODE_03 );
			put("code0304", "m3."+TaMatMaterial.ATT_T_CODE_04 );
			
			put("stat0301", "m3."+TaMatMaterial.ATT_I_STATUS_01 );
			put("stat0302", "m3."+TaMatMaterial.ATT_I_STATUS_02 );
			put("stat0303", "m3."+TaMatMaterial.ATT_I_STATUS_03 );
			put("stat0304", "m3."+TaMatMaterial.ATT_I_STATUS_04 );
			put("stat0305", "m3."+TaMatMaterial.ATT_I_STATUS_05 );
			
			put("typ0301", "m3."+TaMatMaterial.ATT_I_TYPE_01 );
			put("typ0302", "m3."+TaMatMaterial.ATT_I_TYPE_02 );
			put("typ0303", "m3."+TaMatMaterial.ATT_I_TYPE_03 );
			put("typ0304", "m3."+TaMatMaterial.ATT_I_TYPE_04 );
			
			put("inf0301" , "m3."+TaMatMaterial.ATT_T_INFO_01 );
			put("inf0302" , "m3."+TaMatMaterial.ATT_T_INFO_02 );
			put("inf0303" , "m3."+TaMatMaterial.ATT_T_INFO_03 );
			put("inf0304" , "m3."+TaMatMaterial.ATT_T_INFO_04 );
			put("inf0305" , "m3."+TaMatMaterial.ATT_T_INFO_05 );
			put("inf0306" , "m3."+TaMatMaterial.ATT_T_INFO_06 );
			put("inf0307" , "m3."+TaMatMaterial.ATT_T_INFO_07 );
			put("inf0308" , "m3."+TaMatMaterial.ATT_T_INFO_08 );
			put("inf0309" , "m3."+TaMatMaterial.ATT_T_INFO_09 );
			put("inf0310" , "m3."+TaMatMaterial.ATT_T_INFO_10 );
			//-----------------------------------------------------
			put("id04"	  , "m4."+TaMatMaterial.ATT_I_ID );
			put("name0401", "m4."+TaMatMaterial.ATT_T_NAME_01 );
			put("name0402", "m4."+TaMatMaterial.ATT_T_NAME_02 );
			
			put("code0401", "m4."+TaMatMaterial.ATT_T_CODE_01 );
			put("code0402", "m4."+TaMatMaterial.ATT_T_CODE_02);
			put("code0403", "m4."+TaMatMaterial.ATT_T_CODE_03 );
			put("code0404", "m4."+TaMatMaterial.ATT_T_CODE_04 );
			
			put("stat0401", "m4."+TaMatMaterial.ATT_I_STATUS_01 );
			put("stat0402", "m4."+TaMatMaterial.ATT_I_STATUS_02 );
			put("stat0403", "m4."+TaMatMaterial.ATT_I_STATUS_03 );
			put("stat0404", "m4."+TaMatMaterial.ATT_I_STATUS_04 );
			put("stat0405", "m4."+TaMatMaterial.ATT_I_STATUS_05 );
			
			put("typ0401", "m4."+TaMatMaterial.ATT_I_TYPE_01 );
			put("typ0402", "m4."+TaMatMaterial.ATT_I_TYPE_02 );
			put("typ0403", "m4."+TaMatMaterial.ATT_I_TYPE_03 );
			put("typ0404", "m4."+TaMatMaterial.ATT_I_TYPE_04 );
			
			put("inf0401" , "m4."+TaMatMaterial.ATT_T_INFO_01 );
			put("inf0402" , "m4."+TaMatMaterial.ATT_T_INFO_02 );
			put("inf0403" , "m4."+TaMatMaterial.ATT_T_INFO_03 );
			put("inf0404" , "m4."+TaMatMaterial.ATT_T_INFO_04 );
			put("inf0405" , "m4."+TaMatMaterial.ATT_T_INFO_05 );
			put("inf0406" , "m4."+TaMatMaterial.ATT_T_INFO_06 );
			put("inf0407" , "m4."+TaMatMaterial.ATT_T_INFO_07 );
			put("inf0408" , "m4."+TaMatMaterial.ATT_T_INFO_08 );
			put("inf0409" , "m4."+TaMatMaterial.ATT_T_INFO_09 );
			put("inf0410" , "m4."+TaMatMaterial.ATT_T_INFO_10 );
			
			//-----------------------------------------------------
			put("id03"	  , "m5."+TaMatMaterial.ATT_I_ID );
			put("name0501", "m5."+TaMatMaterial.ATT_T_NAME_01 );
			put("name0502", "m5."+TaMatMaterial.ATT_T_NAME_02 );
			
			put("code0501", "m5."+TaMatMaterial.ATT_T_CODE_01 );
			put("code0502", "m5."+TaMatMaterial.ATT_T_CODE_02);
			put("code0503", "m5."+TaMatMaterial.ATT_T_CODE_03 );
			put("code0504", "m5."+TaMatMaterial.ATT_T_CODE_04 );
			
			put("stat0501", "m5."+TaMatMaterial.ATT_I_STATUS_01 );
			put("stat0502", "m5."+TaMatMaterial.ATT_I_STATUS_02 );
			put("stat0503", "m5."+TaMatMaterial.ATT_I_STATUS_03 );
			put("stat0504", "m5."+TaMatMaterial.ATT_I_STATUS_04 );
			put("stat0505", "m5."+TaMatMaterial.ATT_I_STATUS_05 );
			
			put("typ0501", "m5."+TaMatMaterial.ATT_I_TYPE_01 );
			put("typ0502", "m5."+TaMatMaterial.ATT_I_TYPE_02 );
			put("typ0503", "m5."+TaMatMaterial.ATT_I_TYPE_03 );
			put("typ0504", "m5."+TaMatMaterial.ATT_I_TYPE_04 );
			
			put("inf0501" , "m5."+TaMatMaterial.ATT_T_INFO_01 );
			put("inf0502" , "m5."+TaMatMaterial.ATT_T_INFO_02 );
			put("inf0503" , "m5."+TaMatMaterial.ATT_T_INFO_03 );
			put("inf0504" , "m5."+TaMatMaterial.ATT_T_INFO_04 );
			put("inf0505" , "m5."+TaMatMaterial.ATT_T_INFO_05 );
			put("inf0506" , "m5."+TaMatMaterial.ATT_T_INFO_06 );
			put("inf0507" , "m5."+TaMatMaterial.ATT_T_INFO_07 );
			put("inf0508" , "m5."+TaMatMaterial.ATT_T_INFO_08 );
			put("inf0509" , "m5."+TaMatMaterial.ATT_T_INFO_09 );
			put("inf0510" , "m5."+TaMatMaterial.ATT_T_INFO_10 );
			
			
		}
	};
	
	
	//-----------------------------------------------------------------------------------------------------------------------
	private static void doLstDynSQL(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		Object[]  			dataTableOption = ToolDatatable.reqDataTableOptionJSNameToDBName (json, mapColJsAtt);
		Object				searchKey		= (Set<String>)	dataTableOption[0];	
		Set<String>			searchOptCol	= (Set<String>)	dataTableOption[5];	
		String				searchOptOpe	= (String)	   	dataTableOption[6]; //null, like, eq, in, between
		
//		Integer				multi			= ToolData.reqInt		(json, "multi"			, 2); //--sel double/triple mat in relationship
		Integer				repTyp			= ToolData.reqInt		(json, "repTyp"			, 102);
		
//		Set<Integer>		stat0101		= ToolData.reqSetInt	(json, "stat0101"		, null);
//		Set<Integer>		stat0201		= ToolData.reqSetInt	(json, "stat0201"		, null);
//		Set<Integer>		stat0301		= ToolData.reqSetInt	(json, "stat0301"		, null);
		
		Set<Integer>		typ0101			= ToolData.reqSetInt	(json, "typ0101"		, null);
		Set<Integer>		typ0102			= ToolData.reqSetInt	(json, "typ0102"		, null);
		Set<Integer>		typ0103			= ToolData.reqSetInt	(json, "typ0103"		, null);
		Set<Integer>		typ0104			= ToolData.reqSetInt	(json, "typ0104"		, null);
		Set<Integer>		typ0105			= ToolData.reqSetInt	(json, "typ0105"		, null);
		
		Set<Integer>		typ0201			= ToolData.reqSetInt	(json, "typ0201"		, null);
		Set<Integer>		typ0202			= ToolData.reqSetInt	(json, "typ0202"		, null);
		Set<Integer>		typ0203			= ToolData.reqSetInt	(json, "typ0203"		, null);
		Set<Integer>		typ0204			= ToolData.reqSetInt	(json, "typ0204"		, null);
		Set<Integer>		typ0205			= ToolData.reqSetInt	(json, "typ0205"		, null);
		
		Set<Integer>		typ0301			= ToolData.reqSetInt	(json, "typ0301"		, null);
		Set<Integer>		typ0401			= ToolData.reqSetInt	(json, "typ0401"		, null);
		Set<Integer>		typ0501			= ToolData.reqSetInt	(json, "typ0501"		, null);
		
		
		
		JSONArray			whereCond		= ToolData.reqJsonArr 	(json, "whereCond"	, null);
		
		if(typ0101 == null && typ0201 ==null) {
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		
		//-------------------------------------------------------------------
		String[]sqlFromWhere				= reqWhere_SubSelect(	user,  repTyp, whereCond, 
																	typ0101, typ0102, typ0103, typ0104, typ0105, 
																	typ0201, typ0202, typ0203, typ0204, typ0205,
																	typ0301, 
																	typ0401, 
																	typ0501,  
																	searchOptCol, searchOptOpe, searchKey);				
		String 	sqlSelect					= "";	
		String	sqlSubSel					= "";
		
		
		switch(repTyp) {
		case 102: 	sqlSelect		= select_mat_102 + sqlFromWhere[0]+sqlFromWhere[1];
					sqlSubSel		= "m1." + TaMatMaterial.COL_I_ID + ", m2." + TaMatMaterial.COL_I_ID;
					break;
		case 103: 	sqlSelect		= select_mat_103 + sqlFromWhere[0]+sqlFromWhere[1];
					sqlSubSel		= "m1." + TaMatMaterial.COL_I_ID + ", m2." + TaMatMaterial.COL_I_ID + ", m3." + TaMatMaterial.COL_I_ID;
					break;
		case 104: 	sqlSelect		= select_mat_104 + sqlFromWhere[0]+sqlFromWhere[1];
					sqlSubSel		= "m1." + TaMatMaterial.COL_I_ID + ", m2." + TaMatMaterial.COL_I_ID + ", m3." + TaMatMaterial.COL_I_ID + ", m4." + TaMatMaterial.COL_I_ID + ", m5." + TaMatMaterial.COL_I_ID;
					break;
		
					//---the same with 102
		case 501: 	sqlSelect		= select_mat_102 + sqlFromWhere[0]+sqlFromWhere[1];
					sqlSubSel		= "m1." + TaMatMaterial.COL_I_ID + ", m2." + TaMatMaterial.COL_I_ID;
					break;
					
					//---the same with 103
		case 502: 	sqlSelect		= select_mat_103 + sqlFromWhere[0]+sqlFromWhere[1];
					sqlSubSel		= "m1." + TaMatMaterial.COL_I_ID + ", m2." + TaMatMaterial.COL_I_ID + ", m3." + TaMatMaterial.COL_I_ID;
					break;
					
		case 508: 	sqlSelect		= select_mat_508 + sqlFromWhere[0]+sqlFromWhere[1];
					sqlSubSel		= "m1." + TaMatMaterial.COL_I_ID + ", m2." + TaMatMaterial.COL_I_ID;
					break;
					
		case 509: 	sqlSelect		= select_mat_509 + sqlFromWhere[0]+sqlFromWhere[1];
					sqlSubSel		= "m1." + TaMatMaterial.COL_I_ID + ", m2." + TaMatMaterial.COL_I_ID;
					break;
		
		}
		
		String  sqlCountAll 	= String.format(select_Count_Multi, sqlSubSel , sqlFromWhere[0]);
		String  sqlCountCond	= String.format(select_Count_Multi, sqlSubSel , sqlFromWhere[0]+sqlFromWhere[1]);
		//-----------------------------------------------------------------------------
		List<ViMatMaterialMulti> lst 	= reqListDynSQL(dataTableOption, sqlSelect);
		if (lst==null ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		// ----------------------------------------------------------------
		Integer iTotalRecords 			= Integer.MAX_VALUE; //reqNbListDynSQL(sqlCountAll ).intValue(); // tong so dong trong bang
		Integer iTotalDisplayRecords 	= reqNbListDynSQL(sqlCountCond).intValue();

		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"iTotalRecords"				, iTotalRecords,
				"iTotalDisplayRecords"		, iTotalDisplayRecords,
				"aaData"					, lst
				));
	}
	
//	private static String select_Count_double	= " select count(*) from ( select m1.%s, m2.%s 		from %s) v";
//	private static String select_Count_triple	= " select count(*) from ( select m1.%s, m2.%s, m3.%s 	from %s) v";
	
	private static String select_Count_Multi	= " select count(*) from ( select %s from %s) v";
	
	private static String select_mat_2		= "select  " 
			+ ViMatMaterialMulti.reqSelectSqlMat(1, false)+ ", " 
			+ ViMatMaterialMulti.reqSelectSqlMat(2, false)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(3, true)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(4, true)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(5, true)
			+ " from " ;
	
	private static String select_mat_3		= "select " 
			+ ViMatMaterialMulti.reqSelectSqlMat(1, false)+ ", " 
			+ ViMatMaterialMulti.reqSelectSqlMat(2, false)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(3, false)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(4, true)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(5, true)
			+ " from " ;
	
	private static String select_mat_5		= "select  " 
			+ ViMatMaterialMulti.reqSelectSqlMat(1, false)+ ", " 
			+ ViMatMaterialMulti.reqSelectSqlMat(2, false)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(3, false)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(4, false)+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(5, false)
			+ " from " ;
	
	private static String select_mat_102				= "select  " 
			+ ViMatMaterialMulti.reqSelectSqlMat (1, 	TaMatMaterial.COL_I_ID, 
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (2, 	TaMatMaterial.COL_I_ID, 
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_I_TYPE_02,
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01,
														TaMatMaterial.COL_T_CODE_02,
														TaMatMaterial.COL_T_CODE_03,
														TaMatMaterial.COL_T_INFO_05,
														TaMatMaterial.COL_T_INFO_06)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(3, 	TaMatMaterial.COL_T_NAME_01,//env
														TaMatMaterial.COL_I_TYPE_02)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(4, 	true)							+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(5, 	true)	
			+ " from " ;
	
	private static String select_mat_103				= "select "
			+ ViMatMaterialMulti.reqSelectSqlMat (1, 	TaMatMaterial.COL_I_ID, //app
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (2, 	TaMatMaterial.COL_I_ID, //nod
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_I_TYPE_02,
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01,
														TaMatMaterial.COL_T_CODE_02,
														TaMatMaterial.COL_T_CODE_03,
														TaMatMaterial.COL_T_INFO_05,
														TaMatMaterial.COL_T_INFO_06)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (3, 	TaMatMaterial.COL_I_ID, //sa
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(4, 	TaMatMaterial.COL_T_NAME_01,//env
														TaMatMaterial.COL_I_TYPE_02)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(5, 	true)	
			+ " from " ;
	
	private static String select_mat_104				= "select "
			+ ViMatMaterialMulti.reqSelectSqlMat (1, 	TaMatMaterial.COL_I_ID, //app
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01)	+ ", "
			
			+ ViMatMaterialMulti.reqSelectSqlMat (2, 	TaMatMaterial.COL_I_ID, //nod
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_I_TYPE_02,
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_INFO_06)	+ ", "  //OS
			
			+ ViMatMaterialMulti.reqSelectSqlMat (3, 	TaMatMaterial.COL_I_ID, //sa
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01,
														TaMatMaterial.COL_I_STATUS_03 )	+ ", " //QoS
			
			+ ViMatMaterialMulti.reqSelectSqlMat (4, 	TaMatMaterial.COL_I_ID, //env
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_I_TYPE_02, //offre
														TaMatMaterial.COL_T_NAME_01,
														TaMatMaterial.COL_T_NAME_02,
														TaMatMaterial.COL_D_DATE_03,
														TaMatMaterial.COL_D_DATE_05)	+ ", "
			
			+ ViMatMaterialMulti.reqSelectSqlMat (5, 	TaMatMaterial.COL_I_ID, //ca
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01,
														TaMatMaterial.COL_T_INFO_03) //version composant
			+ " from " ;
	
	
	private static String select_mat_501				= "select  " 
			+ ViMatMaterialMulti.reqSelectSqlMat (1, 	TaMatMaterial.COL_I_ID, 
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (2, 	TaMatMaterial.COL_I_ID, 
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_I_TYPE_02,
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01,
														TaMatMaterial.COL_T_CODE_02,
														TaMatMaterial.COL_T_CODE_03,
														TaMatMaterial.COL_T_INFO_05,
														TaMatMaterial.COL_T_INFO_06)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(3, 	TaMatMaterial.COL_T_NAME_01,//env
														TaMatMaterial.COL_I_TYPE_02)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(4, 	true)							+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(5, 	true)	
			+ " from " ;
	
	private static String select_mat_502				= "select "
			+ ViMatMaterialMulti.reqSelectSqlMat (1, 	TaMatMaterial.COL_I_ID, //app
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (2, 	TaMatMaterial.COL_I_ID, //nod
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_I_TYPE_02,
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01,
														TaMatMaterial.COL_T_CODE_02,
														TaMatMaterial.COL_T_CODE_03,
														TaMatMaterial.COL_T_INFO_05,
														TaMatMaterial.COL_T_INFO_06)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (3, 	TaMatMaterial.COL_I_ID, //sa
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(4, 	TaMatMaterial.COL_T_NAME_01,//env
														TaMatMaterial.COL_I_TYPE_02)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(5, 	true)	
			+ " from " ;
	private static String select_mat_508				= "select  " 
			+ ViMatMaterialMulti.reqSelectSqlMat (1, 	TaMatMaterial.COL_I_ID, 
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01,
														TaMatMaterial.COL_T_INFO_02)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (2, 	TaMatMaterial.COL_I_ID, 
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_I_TYPE_05,
														TaMatMaterial.COL_T_NAME_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(3, 	true)							+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(4, 	true)							+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat(5, 	true)	
			+ " from " ;
	private static String select_mat_509				= "select "
			+ ViMatMaterialMulti.reqSelectSqlMat (1, 	TaMatMaterial.COL_I_ID, //app
														TaMatMaterial.COL_T_INFO_03, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_T_NAME_01, 
														TaMatMaterial.COL_T_CODE_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (2, 	TaMatMaterial.COL_I_ID, //nod
														TaMatMaterial.COL_I_STATUS_01, 
														TaMatMaterial.COL_I_TYPE_01, 
														TaMatMaterial.COL_I_TYPE_02,
														TaMatMaterial.COL_T_NAME_01)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (3, 	true)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (4, 	true)	+ ", "
			+ ViMatMaterialMulti.reqSelectSqlMat (5, 	true)	
			+ " from " ;

	
	
	private static String cond_where_filterLk_colStr	= "(lower(m1.%s) like  lower('%s'))";
	private static String cond_where_filterIn_colStr	= "(m1.%s in %s)";
	private static String cond_where_filterBt_colStr	= "(m1.%s between '%s' and '%s')";
	
	private static String sql_subSelect_mat			= " (select * from " + DefDBExt.TA_MAT_MATERIAL 	+ " where I_STATUS_01 <> 10 and %s) %s ";
	
	private static String sql_where_typ01			= TaMatMaterial.COL_I_TYPE_01 + " in %s";
	private static String sql_where_typ02			= TaMatMaterial.COL_I_TYPE_02 + " in %s";
	private static String sql_where_typ03			= TaMatMaterial.COL_I_TYPE_03 + " in %s";
	private static String sql_where_typ04			= TaMatMaterial.COL_I_TYPE_04 + " in %s";
	private static String sql_where_typ05			= TaMatMaterial.COL_I_TYPE_05 + " in %s";
	
	private static String sql_innerJoin_matDet  	= " inner join TA_MAT_MATERIAL_DETAIL %s ";
	private static String sql_innerJoin_On_01  		= " on %s.I_ID = %s.I_Mat_Material_0%d ";
	private static String sql_innerJoin_On_02  		= " on %s.I_ID = %s.I_Mat_Material_0%d and %s.I_ID = %s.I_Mat_Material_0%d ";
	
	private static String[] reqWhere_SubSelect(
			TaAutUser 		user,  
			int 			repTyp, 
			JSONArray		whereCond	,
//			Set<Integer> 	stat0101	, Set<Integer> 	stat0201	, Set<Integer> 	stat0301	,
			Set<Integer> 	typ0101		, Set<Integer> 	typ0102, Set<Integer> 	typ0103, Set<Integer> 	typ0104, Set<Integer> 	typ0105,
			Set<Integer> 	typ0201		, Set<Integer> 	typ0202, Set<Integer> 	typ0203, Set<Integer> 	typ0204, Set<Integer> 	typ0205,
			Set<Integer> 	typ0301		, 
			Set<Integer> 	typ0401		, 
			Set<Integer> 	typ0501		, 
			
			Set<String> 	searchCol	, String searchOptOpe		, Object sKey  ) throws Exception {	
		//--Pre-Check condition---------------------------------------------------
		
		
		String 	condMat01 	= "true";
		if(typ0101!=null) 
				condMat01 	= 						String.format(sql_where_typ01 , ToolSet.reqStr(typ0101, "(", ",", ")" )) ;
		if(typ0102!=null) 
				condMat01 	= condMat01 + "and " + 	String.format(sql_where_typ02 , ToolSet.reqStr(typ0102, "(", ",", ")" )) ;
		if(typ0103!=null) 
				condMat01 	= condMat01 + "and " + 	String.format(sql_where_typ03 , ToolSet.reqStr(typ0103, "(", ",", ")" )) ;
		if(typ0104!=null) 
				condMat01 	= condMat01 + "and " + 	String.format(sql_where_typ04 , ToolSet.reqStr(typ0104, "(", ",", ")" )) ;
		if(typ0105!=null) 
				condMat01 	= condMat01 + "and " + 	String.format(sql_where_typ05 , ToolSet.reqStr(typ0105, "(", ",", ")" )) ;

		String 	sqlMat01 	= String.format(sql_subSelect_mat, condMat01, "m1");
		
		String 	condMat02 	= "true";
		if(typ0201!=null) 
				condMat02 	= 						String.format(sql_where_typ01 , ToolSet.reqStr(typ0201, "(", ",", ")" )) ;
		if(typ0202!=null) 
				condMat02 	= condMat02 + "and " + 	String.format(sql_where_typ02 , ToolSet.reqStr(typ0202, "(", ",", ")" )) ;
		if(typ0203!=null) 
				condMat02 	= condMat02 + "and " + 	String.format(sql_where_typ03 , ToolSet.reqStr(typ0203, "(", ",", ")" )) ;
		if(typ0204!=null) 
				condMat02 	= condMat02 + "and " + 	String.format(sql_where_typ04 , ToolSet.reqStr(typ0204, "(", ",", ")" )) ;
		if(typ0205!=null) 
				condMat02 	= condMat02 + "and " + 	String.format(sql_where_typ05 , ToolSet.reqStr(typ0205, "(", ",", ")" )) ;
		
		String	sqlMat02 	= String.format(sql_subSelect_mat, condMat02, "m2");
		
		
		
		String 	condMat03 	= "true";
		if(typ0301!=null) 
				condMat03 	= String.format(sql_where_typ01	 , ToolSet.reqStr(typ0301, "(", ",", ")" )) ;
		String 	sqlMat03 	= String.format(sql_subSelect_mat, condMat03, "m3");
		
		String 	condMat04 	= "true";
		if(typ0401!=null) 
				condMat04 	= String.format(sql_where_typ01	 , ToolSet.reqStr(typ0401, "(", ",", ")" )) ;
		String 	sqlMat04 	= String.format(sql_subSelect_mat, condMat04, "m4");
		
		String 	condMat05 	= "true";
		if(typ0501!=null) 
				condMat05 	= String.format(sql_where_typ01	 , ToolSet.reqStr(typ0501, "(", ",", ")" )) ;
		String 	sqlMat05 	= String.format(sql_subSelect_mat, condMat05, "m5");
		
		
		
		//---node -- parent of app/env/sa/ca 
		//---app  -- parent of env/sa/ca
		String sqlFrom 		= "";
		switch(repTyp) {
		//---report : App Lst Server Env
//		case 102: 	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); 
//					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1);  	
//					break;
		case 102:	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); 
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
					sqlFrom		= sqlFrom									+ String.format(sql_innerJoin_matDet		, "d2") +  String.format(sql_innerJoin_On_01		, "m1", "d2", 1);
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat03 														+  String.format(sql_innerJoin_On_01		, "m3", "d2", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d3") +  String.format(sql_innerJoin_On_02		, "m2", "d3", 1, "m3", "d3", 2);
					break;	
		//---report : App Lst Server SA Env
//		case 103:	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); 
//					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
//					sqlFrom		= sqlFrom									+ String.format(sql_innerJoin_matDet		, "d2") +  String.format(sql_innerJoin_On_01		, "m1", "d2", 1);
//					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat03 														+  String.format(sql_innerJoin_On_01		, "m3", "d2", 2);
//					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d3") +  String.format(sql_innerJoin_On_02		, "m2", "d3", 1, "m3", "d3", 2);
//					break;	
		case 103:	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); //--nod -app
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
					sqlFrom		= sqlFrom									+ String.format(sql_innerJoin_matDet		, "d2") +  String.format(sql_innerJoin_On_01		, "m1", "d2", 1); //--app -sa
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat03 														+  String.format(sql_innerJoin_On_01		, "m3", "d2", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d3") +  String.format(sql_innerJoin_On_01		, "m2", "d3", 1); //--nod -env
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat04 														+  String.format(sql_innerJoin_On_01		, "m4", "d3", 2); 
					
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d5") +  String.format(sql_innerJoin_On_02		, "m2", "d5", 1, "m3", "d5", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d6") +  String.format(sql_innerJoin_On_02		, "m1", "d6", 1, "m4", "d6", 2);
					break;
		//---report : App Lst Server SA CA Env
		case 104:	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); //--nod -app
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
					sqlFrom		= sqlFrom									+ String.format(sql_innerJoin_matDet		, "d2") +  String.format(sql_innerJoin_On_01		, "m1", "d2", 1); //--app -sa
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat03 														+  String.format(sql_innerJoin_On_01		, "m3", "d2", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d3") +  String.format(sql_innerJoin_On_01		, "m2", "d3", 1); //--nod -env
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat04 														+  String.format(sql_innerJoin_On_01		, "m4", "d3", 2); 
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d4") +  String.format(sql_innerJoin_On_01		, "m2", "d4", 1); //--nod -ca
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat05 														+  String.format(sql_innerJoin_On_01		, "m5", "d4", 2);
					
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d5") +  String.format(sql_innerJoin_On_02		, "m2", "d5", 1, "m3", "d5", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d6") +  String.format(sql_innerJoin_On_02		, "m1", "d6", 1, "m4", "d6", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d7") +  String.format(sql_innerJoin_On_02		, "m1", "d7", 1, "m5", "d7", 2);
					break;
//	   //---report : App Extranet Lst Server 			
//		case 106:	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2);  //--nod -app
//					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
//					break;	

					
		//---report : App Lst Server
		case 501: 	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); 
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
					sqlFrom		= sqlFrom									+ String.format(sql_innerJoin_matDet		, "d2") +  String.format(sql_innerJoin_On_01		, "m1", "d2", 1);
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat03 														+  String.format(sql_innerJoin_On_01		, "m3", "d2", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d3") +  String.format(sql_innerJoin_On_02		, "m2", "d3", 1, "m3", "d3", 2);
					break;	
		
		//---report : App Lst Server SA
		case 502:	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); //--nod -app
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
					sqlFrom		= sqlFrom									+ String.format(sql_innerJoin_matDet		, "d2") +  String.format(sql_innerJoin_On_01		, "m1", "d2", 1); //--app -sa
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat03 														+  String.format(sql_innerJoin_On_01		, "m3", "d2", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d3") +  String.format(sql_innerJoin_On_01		, "m2", "d3", 1); //--nod -env
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat04 														+  String.format(sql_innerJoin_On_01		, "m4", "d3", 2); 
					
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d5") +  String.format(sql_innerJoin_On_02		, "m2", "d5", 1, "m3", "d5", 2);
					sqlFrom		= sqlFrom 									+ String.format(sql_innerJoin_matDet		, "d6") +  String.format(sql_innerJoin_On_02		, "m1", "d6", 1, "m4", "d6", 2);
					break;
					
		case 508:	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); 
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
					break;
					
		case 509:	sqlFrom		= 								sqlMat01 	+ String.format(sql_innerJoin_matDet		, "d1") +  String.format(sql_innerJoin_On_01		, "m1", "d1", 2); 
					sqlFrom		= sqlFrom 	+ " inner join " + 	sqlMat02 														+  String.format(sql_innerJoin_On_01		, "m2", "d1", 1); 
					break;
					
		}
		
		String sqlWhere = " where true " ;
		String condW = " ";
		if (whereCond!=null && whereCond.size()>0) {
			for (Object obj: whereCond) {
				JSONObject 	cond 	= (JSONObject) obj;
				String		dbCol 	= mapColJsAtt.get(cond.get("col"));
				if (dbCol==null) continue;
				
				if (condW.length()>0) condW= condW +" and ";
				
				String 		ope		= ToolData.reqStr(cond, "ope", "like"); 
				String 		typ		= ToolData.reqStr(cond, "typ", "str");
				String 		val		= ToolData.reqStr(cond, "val", "");
				
				if (val.contains("%")) ope = "like";
				
				if (typ.equals("str")||typ.equals("date")) 
					condW = 	condW + dbCol + "  " + ope + " '"+ (val!=null?val:"") +"'";
				else 
					condW = 	condW + dbCol + "  " + ope + " "+ (val!=null?val:"");
			}
		}
		sqlWhere = sqlWhere + condW ;
		
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

						if (sub!=null) sqlWhere = sqlWhere 	+ " and (" +  sub + ") ";
					}
				}
			}else if (searchOptOpe.equals("in")) {
				Criterion		colCri 		= null;
				TaMatMaterial	test   		= new TaMatMaterial();
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
					if (sub!=null) sqlWhere = sqlWhere 	+ " and (" +  sub + ") ";
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
					if (sub!=null) sqlWhere = sqlWhere 	+ " and (" +  sub + ") ";
				}
			}
		}
		
		return new String[]{sqlFrom, sqlWhere};
	}
	
	
	private static List<ViMatMaterialMulti> reqListDynSQL(Object[] dataTableOption, String sql) throws Exception {	
		Set<String>		searchKey	= (Set<String>)	dataTableOption[0];	
		int 			begin 		= (int)			dataTableOption[1];
		int 			number 		= (int)			dataTableOption[2]; 
		String 			sortCol 	= (String)		dataTableOption[3]; 
		int 			sortTyp 	= (int)			dataTableOption[4];	

		List<ViMatMaterialMulti> list = new ArrayList<ViMatMaterialMulti>();		

		String 	order 	= null;	
		if (sortCol==null)
		 		sortCol = ViMatMaterialMulti.ATT_T_NAME_01_01;; // sap xep 
		 		

		if (sortCol!=null){
			switch(sortTyp){
			case 0: order = " order by " + sortCol + " asc" ; break;
			case 1: order = " order by " + sortCol + " desc"; break;				
			}
		}

		if (order!=null)
			sql = sql + order;
			
		list	= ViMatMaterialMulti.DAO.reqList(begin, number, sql);
		return list;
	}

	private static Number reqNbListDynSQL(String sql) throws Exception {	
		return ViMatMaterialMulti.DAO.reqCount(sql);	
	}

	
	private static void doLstExp(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		
//		Set<Integer>		stat01			= ToolData.reqSetInt	(json, "stat01"		, null);
//		Set<Integer>		typ01			= ToolData.reqSetInt	(json, "typ01"		, null);
//		Set<Integer>		typ02			= ToolData.reqSetInt	(json, "typ02"		, null);
		List<String>		colExport	    = ToolData.reqListStr 	(json, "colExport"	, ",;", null);
		List<String>		colLab	    	= ToolData.reqListStr 	(json, "colLab"		, ",;", null);
//		Set<Integer>		cat				= ToolData.reqSetInt	(json, "catIds"		, null);
		String				category		= ToolData.reqStr		(json, "cat"		, null);
		Integer 			manId	 		= null;
		
//		Integer				multi			= ToolData.reqInt		(json, "multi"			, 2); //--sel double/triple mat in relationship
		Integer				repTyp			= ToolData.reqInt		(json, "repTyp"			, 102);
//		Set<Integer>		stat0101		= ToolData.reqSetInt	(json, "stat0101"		, null);
//		Set<Integer>		stat0201		= ToolData.reqSetInt	(json, "stat0201"		, null);
//		Set<Integer>		stat0301		= ToolData.reqSetInt	(json, "stat0301"		, null);
		
		Set<Integer>		typ0101			= ToolData.reqSetInt	(json, "typ0101"		, null);
		Set<Integer>		typ0102			= ToolData.reqSetInt	(json, "typ0102"		, null);
		Set<Integer>		typ0103			= ToolData.reqSetInt	(json, "typ0103"		, null);
		Set<Integer>		typ0104			= ToolData.reqSetInt	(json, "typ0104"		, null);
		Set<Integer>		typ0105			= ToolData.reqSetInt	(json, "typ0105"		, null);
		
		
		Set<Integer>		typ0201			= ToolData.reqSetInt	(json, "typ0201"		, null);
		Set<Integer>		typ0202			= ToolData.reqSetInt	(json, "typ0202"		, null);
		Set<Integer>		typ0203			= ToolData.reqSetInt	(json, "typ0203"		, null);
		Set<Integer>		typ0204			= ToolData.reqSetInt	(json, "typ0204"		, null);
		Set<Integer>		typ0205			= ToolData.reqSetInt	(json, "typ0205"		, null);
		
		Set<Integer>		typ0301			= ToolData.reqSetInt	(json, "typ0301"		, null);
		Set<Integer>		typ0401			= ToolData.reqSetInt	(json, "typ0401"		, null);
		Set<Integer>		typ0501			= ToolData.reqSetInt	(json, "typ0501"		, null);
		

		JSONArray			whereCond		= ToolData.reqJsonArr 	(json, "whereCond"	, null);
		
		if(typ0101 == null && typ0201 ==null && typ0301 ==null) {
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}
		
		//-------------------------------------------------------------------
	
		String[]sqlFromWhere	= reqWhere_SubSelect(	user,  repTyp, whereCond, 
														typ0101, typ0102, typ0103, typ0104, typ0105,
														typ0201, typ0202, typ0203, typ0204, typ0205,
														typ0301, 
														typ0401, 
														typ0501,  
														null, null, null);	
				
		String 	sqlSelect		= "";		
		switch(repTyp) {
		case 102: sqlSelect		= select_mat_102 + sqlFromWhere[0]+sqlFromWhere[1]; 
				break;
		case 103: sqlSelect		= select_mat_103 + sqlFromWhere[0]+sqlFromWhere[1];
				break;
		case 104:	sqlSelect	= select_mat_104 + sqlFromWhere[0]+sqlFromWhere[1];
			break;
		
		case 501: sqlSelect		= select_mat_501 + sqlFromWhere[0]+sqlFromWhere[1]; 
				break;
		case 502: sqlSelect		= select_mat_502 + sqlFromWhere[0]+sqlFromWhere[1];
				break;
		case 508: sqlSelect		= select_mat_508 + sqlFromWhere[0]+sqlFromWhere[1]; 
			break;
		case 509: 	sqlSelect   = select_mat_509 + sqlFromWhere[0]+sqlFromWhere[1];
		   break;


		}

		//-----------------------------------------------------------------------------
		List<ViMatMaterialMulti> lst 	= reqListExp(sqlSelect) ;
				
		if (lst==null ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
//		Call exportFile
		String pathFile = DefAPIExt.LOC_PATH_EXP_CSV + File.separator;
		String fileName = category+ "_" + ToolDate.reqString(new Date(), "yyMMddHHmmss") + ".csv";
		FileExportMulti.doExport(lst, colExport, colLab, pathFile+fileName);

		API.doResponse(response, ToolJSON.reqJSonString(		//filter,
				DefJS.SESS_STAT				, 1, 
				DefJS.SV_CODE				, DefAPI.SV_CODE_API_YES,					
				"pathFile"					, DefAPIExt.API_PATH_EXP_CSV +"/" + fileName
				));
	}
	
	private static List<ViMatMaterialMulti> reqListExp(String sql) throws Exception {		
		List<ViMatMaterialMulti> list = new ArrayList<>();		
		list	= ViMatMaterialMulti.DAO.reqList(sql);

		return list;
	}
	
	
}
