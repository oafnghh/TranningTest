package com.hnv.api.service.priv.tpy;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefJS;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.common.APIAuth;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.def.DefRight;

public class ServiceTpyDocument implements IService  {
	public static final String SV_CLASS = "ServiceTpyDocument".toLowerCase();

	public static final String SV_DO_LIST 	= "SVList".toLowerCase();
	public static final String SV_DO_NEW 	= "SVNew".toLowerCase();
	public static final String SV_DO_MOD 	= "SVMod".toLowerCase();
	public static final String SV_DO_GET 	= "SVGet".toLowerCase();
	public static final String SV_DO_DEL 	= "SVDel".toLowerCase();

	public ServiceTpyDocument() {
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
	}

	//---------------------------------------------------------------------------
	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		//ToolLogServer.doLogInf("--------- "+ SV_CLASS+ ".doService --------------");
		String 		sv 		= API.reqSVFunctName(json);
		TaAutUser 	user	= (TaAutUser) json.get("userInfo");
		try {
			// mapping service
			if (sv.equals(SV_DO_GET)) {
				doGet(user, json, response);
			
			} else if (sv.equals(SV_DO_NEW) ) {
				doNew(user, json, response);
				
			} else if (sv.equals(SV_DO_DEL) ) {
				doDel(user, json, response);
			
			} else {
				API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			}
		} catch (Exception e) {
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------
	public static void doNew(TaAutUser user, JSONObject json, HttpServletResponse response ) throws Exception {
		String 				filePath 	= "";
		List<TaTpyDocument> listDoc 	= reqListNew(user, json,  filePath);
		if (listDoc==null){
			API.doResponse(response, ToolJSON.reqJSonString(
					DefJS.SESS_STAT, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_NO					
					));
		}else{				
			API.doResponse(response, ToolJSON.reqJSonString(
					DefJS.SESS_STAT, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, listDoc
					));				
		}
	}

	private static List<TaTpyDocument> reqListNew(TaAutUser user, JSONObject json, String filePath) throws Exception {
		Integer				entTyp			= ToolData.reqInt(json, "enttyp", TaTpyDocument.STAT_NEW); //data-entTyp give enttyp
		
		Integer				fileTyp01		= ToolData.reqInt(json, "typ01"	, TaTpyDocument.TYPE_01_FILE_MEDIA);
		Integer				fileTyp02		= ToolData.reqInt(json, "typ02"	, TaTpyDocument.TYPE_02_UNKNOW);
		Integer				fileTyp03		= ToolData.reqInt(json, "typ03"	, TaTpyDocument.TYPE_03_PUBLIC);
		
		Integer				stat			= TaTpyDocument.STAT_NEW; 
		
		List<String>		names			= (ArrayList) json.get("fileNames");	//filename not add dateTime
		List<String>		paths			= (ArrayList) json.get("filePaths");   //path full of filename

		List<TaTpyDocument> docs			= new ArrayList<TaTpyDocument>();

		for (int i=0; i<names.size(); i++){
			String 	fName 			= names.get(i);
			String 	fPath			= paths.get(i);
			File 	file 			= new File(fPath);
			Double 	filesize 		= file.length() * 1.0;
			
			TaTpyDocument doc		= new TaTpyDocument(fileTyp01, fileTyp02, fileTyp03, filesize, fName, null, null, fPath, new Date(),user.reqId(), entTyp, -1, stat, i);
			docs.add(doc);
		}

		if (docs.size()>0) TaTpyDocument.DAO.doPersist(docs);
		return docs;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------
	private static void doDel(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
			Integer			id = ToolData.reqInt(json, "key", null); //--FileInput return key
			if(id == null)	id = ToolData.reqInt(json, "id" , null);
			
			if (!canDel(id)){
				API.doResponse(response,DefAPI.API_MSG_KO);
				return;
			} 

			API.doResponse(response, ToolJSON.reqJSonString(
					DefJS.SESS_STAT		, 1, 
					DefJS.SV_CODE		, DefAPI.SV_CODE_API_YES,
					DefJS.RES_DATA		, id
			));
	}

	private static boolean canDel(Integer id) throws Exception {
		if(id == null || id<=0) return false;
		
		TaTpyDocument  ent	 = TaTpyDocument.DAO.reqEntityByRef(id);
		if (ent==null){
			return false;
		}

		//remove table parent id
		ent.reqSet(TaTpyDocument.ATT_I_ENTITY_TYPE	, -1);
		ent.reqSet(TaTpyDocument.ATT_I_ENTITY_ID	, -1);
		TaTpyDocument.DAO.doMerge(ent);
		
		return true;
	}

	private static void doGet(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		Integer			id = ToolData.reqInt(json, "key", null);
		if(id == null)	id = ToolData.reqInt(json, "id" , null);

		if(id == null || id<=0) {
			API.doResponse(response,DefAPI.API_MSG_KO);
			return;
		}


		TaTpyDocument d = TaTpyDocument.DAO.reqEntityByRef(id);
		API.doResponse(response, ToolJSON.reqJSonString(		
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, d 
				));
	}

	//---------------------------------------------------------------------------------------------------------------------
	public static List<TaTpyDocument> reqNewtMod(TaAutUser user, Integer entTyp, Integer entId, JSONObject json) throws Exception {
		try{

//			JSONArray arrFiles = ToolJSON.reqJSonArrayFromString((String) json.get("files"));
			JSONArray arrFiles =  (JSONArray)json.get("files");

			return reqNewtMod (user, entTyp, entId, arrFiles);
		}catch(Exception e){	
			return null;
		}
	}
	
	public static List<TaTpyDocument> reqNewtMod(TaAutUser user, Integer entTyp, Integer entId, JSONArray arrFiles) throws Exception {
		try{
			if(arrFiles == null || arrFiles.size() == 0)		return null;

			List<TaTpyDocument> docs = new ArrayList<TaTpyDocument>();

			Set<Integer> setDocs = new HashSet<Integer>();
			for(int i = 0; i < arrFiles.size() ; i++) {
				Map<String, Object> attr 	= API.reqMapParamsByClass((JSONObject)arrFiles.get(i), TaTpyDocument.class);
				Integer 			id		=  (Integer) attr.get(TaTpyDocument.ATT_I_ID);
				if(id == null)	continue;
				setDocs.add(id);
			}

			if(!setDocs.isEmpty()) {
				List<TaTpyDocument> lst = TaTpyDocument.DAO.reqList_In(TaTpyDocument.ATT_I_ID, setDocs);
				if(lst != null && lst.size() > 0) {
					for(TaTpyDocument d : lst) {
						d.reqSet(TaTpyDocument.ATT_I_ENTITY_ID	, entId);
						d.reqSet(TaTpyDocument.ATT_I_ENTITY_TYPE, entTyp);
						d.reqSet(TaTpyDocument.ATT_D_DATE_02	, new Date());
						docs.add(d);
					}
				}
			}

			if(docs.size() > 0)	TaTpyDocument.DAO.doMerge(docs);

			return docs;
		}catch(Exception e){	
			return null;
		}
	}
	
	
}
