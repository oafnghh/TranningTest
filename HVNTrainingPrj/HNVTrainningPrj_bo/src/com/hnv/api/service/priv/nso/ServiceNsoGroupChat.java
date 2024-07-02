package com.hnv.api.service.priv.nso;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefJS;
import com.hnv.api.def.DefTime;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.common.APIAuth;
import com.hnv.api.service.common.ResultPagination;
import com.hnv.api.service.socket.ChatMessage;
import com.hnv.api.service.socket.ServiceChatEndpoint;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolDate;
import com.hnv.common.tool.ToolEmail;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.tool.ToolSet;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.msg.TaMsgMessage;
import com.hnv.db.msg.TaMsgMessageStore;
import com.hnv.db.nso.TaNsoGroup;
import com.hnv.db.nso.TaNsoGroupMember;
import com.hnv.db.nso.TaNsoOffer;
import com.hnv.db.nso.TaNsoPost;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.def.DefDBExt;


public class ServiceNsoGroupChat implements IService {

	//--------------------------------Service Definition----------------------------------
	public static final String SV_MODULE 							= "EC_V3".toLowerCase();

	public static final String SV_CLASS 							= "ServiceNsoGroupDyn".toLowerCase();

	public static final String SV_LST_PAGINATION					= "SVLstPagination"		.toLowerCase(); 
	public static final String SV_LST_BYUSER						= "SVLstByUser"			.toLowerCase(); 
	public static final String SV_LST_SEARCH						= "SVLstSearch"			.toLowerCase(); 
	
	public static final String SV_NEW_ROOM 							= "SVNewRoom".toLowerCase();
	public static final String SV_MOD_ROOM 							= "SVModRoom".toLowerCase();
	public static final String SV_DEL_ROOM 							= "SVDelRoom".toLowerCase();
	public static final String SV_OUT_ROOM 							= "SVOutRoom".toLowerCase();
	
	public static final String SV_JOIN_GROUP_AFTER_REGISTER 		= "SVJoinAfterRegister".toLowerCase();
	public static final String SV_LST_NEW_WAIT 						= "SVLstNewWait".toLowerCase();
	public static final String SV_DOC_ADD 							= "SVDocAdd".toLowerCase();
	public static final String SV_ADD_MEMBER_BY_MAIL 				= "SVAddMemberByEmail".toLowerCase();
	public static final String SV_DEL_ENT 							= "SVDelEnt".toLowerCase();
	public static final String SV_VALIDATED 						= "SVValidated".toLowerCase();
	public static final String SV_TRANSFORM 						= "SVTransform".toLowerCase();
	public static final String SV_ADD_MEMBER 						= "SVAddMember".toLowerCase();
	public static final String SV_GET_GROUP_2_USER 					= "SVGetGroup2User".toLowerCase();
	
	public static final Integer ENT_TYP       = DefDBExt.ID_TA_NSO_GROUP;
	//-------------------------Default Constructor - Required -------------------------------------
	public ServiceNsoGroupChat() {
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
	}

	//-----------------------------------------------------------------------------------------------

	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		String    sv   = API.reqSVFunctName(json);
		TaAutUser user = (TaAutUser) json.get("userInfo");
		try {
			if (sv.equals(SV_LST_PAGINATION) 				&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doLst(user, json, response);
				
			}else if (sv.equals(SV_LST_BYUSER) && APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doLstByMember(user, json, response);	
				
			}else if (sv.equals(SV_LST_SEARCH) && APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doLstSearch(user, json, response);		
				
			} else if(sv.equals(SV_DOC_ADD)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doDocAdd(user, json, response);
			} else if(sv.equals(SV_NEW_ROOM)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doNewRoom(user, json, response);
			} else if(sv.equals(SV_MOD_ROOM)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doModRoom(user, json, response);
			} else if(sv.equals(SV_DEL_ROOM)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doDelRoom(user, json, response);
			} else if(sv.equals(SV_OUT_ROOM)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doOutRoom(user, json, response);

			} else if(sv.equals(SV_DEL_ENT)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doDelEnt(user, json, response);
			} else if(sv.equals(SV_VALIDATED)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doValidated(user, json, response);
			} else if(sv.equals(SV_TRANSFORM)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doTransform(user, json, response);
			} else if(sv.equals(SV_ADD_MEMBER)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doAddMember(user, json, response);
			} else if(sv.equals(SV_GET_GROUP_2_USER)		&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doUser(user, json, response);
			} else if(sv.equals(SV_LST_NEW_WAIT)		&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doLstNewWait(user, json, response);
			} else if(sv.equals(SV_ADD_MEMBER_BY_MAIL)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doAddMemberByMail(user, json, response);
			} else if(sv.equals(SV_JOIN_GROUP_AFTER_REGISTER)	&& APIAuth.canAuthorize(user, SV_CLASS, sv)){
				doJoinMemberAfterRegister(user, json, response);

			} else {
				API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			}

		}catch(Exception e){
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------------------------------------------------------
	private static Hashtable<String,Integer> mapCol = new Hashtable<String, Integer>(){
		{
			put("action"	, -1);
			put("id"		, 0 );
			put("code"		, 1 );
			put("name"		, 2 );
			put("tag"		, 3 );	    
		}
	};

	private static Object[] reqDataTableOption(String searchKey, int beginDisplay, int nbDisplay, String colN, int sortOption){
		Object[] res = new Object[10];


		List<String> keyword 	= new ArrayList<String>();
		if (searchKey!=null && searchKey.length()>0){				
			StringTokenizer token = new StringTokenizer(searchKey, " ");
			while (token.hasMoreTokens()){
				String s = token.nextToken().trim();
				if (s.length()==0) continue;
				if (s.equals("%")) continue;
				s = "%" +s+ "%";
				s = s.replace("%+", "");
				s = s.replace("+%", "");
				keyword.add(s.toLowerCase());
			}			
		}else{
			//keyword.add("%");
		}

		int colToSort= mapCol.get(colN);
		res[0]		= keyword;
		res[1]		= (int)beginDisplay;
		res[2]		= (int)nbDisplay;
		res[3]		= (int)colToSort;
		res[4]		= (int)sortOption;
		res[5]		= -1;		
		return res;

	}

	//---------------------------------------------------------------------------------------------------------------------------
	private static CacheData<ResultPagination>		cache_rs 		= new CacheData<ResultPagination>	(100, DefTime.TIME_00_00_01_000); //30 minutes if project or epic

	private static void doLst(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		ResultPagination  	res = reqLst(user, json); //and other params if necessary
		if (res.reqList()==null ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		API.doResponse(response	, ToolJSON.reqJSonString(
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, res));
	}

	//-------------------------------------------------List dynamique filter mat--------------------------------------------------------------------------------------

	private static 	ResultPagination reqLst(TaAutUser user, JSONObject json) throws Exception  {
		//		Integer			manId		= ToolData.reqInt	(json, "manId"		, null);
		Integer 		manId 		= user.reqPerManagerId();
		
		Integer 		typ01		= ToolData.reqInt	(json, "typ01"		, null	);
		Integer 		begin		= ToolData.reqInt	(json, "begin"		, 0	);
		Integer 		number		= ToolData.reqInt	(json, "number"		, 20);
		Integer 		total		= ToolData.reqInt	(json, "total"		, 0	);
		String 			searchKey   = ToolData.reqStr	(json, "searchKey"	, null);

		String 			sortCol   	= ToolData.reqStr	(json, "sortCol"		, "name");
		Integer 		sortDir   	= ToolData.reqInt	(json, "sortDir"		,	0);

		Integer			stat		= ToolData.reqInt	(json, "stat"		, TaNsoGroup.STAT_01_ACTIVE); 
		Boolean 		hardLoad   	= ToolData.reqBool	(json, "hardLoad"	, false);

		if(hardLoad)	cache_rs.doClear();

		String keyWord 	= manId + "_" +  begin + "_" + number + "_" + total + "_" + searchKey + "_" + sortCol+ "_" + sortDir +"_" +  stat+ "_" + typ01;

		ResultPagination rs =	cache_rs.reqData(keyWord);

		if(rs==null) {
			Object[] dataTableOption 	= reqDataTableOption(searchKey, begin, number, sortCol, sortDir);
			List<TaNsoGroup> grpList 	= reqListFilterDyn(dataTableOption, manId, stat, typ01);
			
			Set<Integer> ids = ToolSet.reqSetInt(grpList, TaNsoGroup.ATT_I_ID);
			List<TaTpyDocument> docs = TaTpyDocument.DAO.reqList_In(TaTpyDocument.ATT_I_ENTITY_ID, ids, Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_TYPE, DefDBExt.ID_TA_NSO_GROUP))	;
			
			if (docs != null && docs.size() > 0) {
				Map<Integer, TaTpyDocument> mapDoc = new HashMap<Integer, TaTpyDocument>();
				for (TaTpyDocument doc : docs) {
					mapDoc.put((Integer) doc.req(TaTpyDocument.ATT_I_ENTITY_ID), doc);
				}
				
				for(TaNsoGroup grp : grpList) {
					grp.reqSet(TaNsoGroup.ATT_O_AVATAR, mapDoc.get((Integer) grp.req(TaNsoGroup.ATT_I_ID)));
				}
			}

			if (grpList==null || grpList.size() ==0 ){
				rs								= new ResultPagination(grpList, 0, 0, 0);
			}else {
				if (total == 0 )	total		= reqFilterListDynCount(dataTableOption, manId, stat, typ01);
				rs								= new ResultPagination(grpList, total, begin, number);
			}

			if (grpList!=null && grpList.size()>0)
				cache_rs.reqPut(keyWord, rs);			
		} else {
			cache_rs.reqCheckIfOld(keyWord); 
		}

		return rs;

	}
	private static List<TaNsoGroup> reqListFilterDyn(Object[] dataTableOption, Integer manId, Integer status, Integer typ01) throws Exception {
		List<String>			searchKey				= (List<String>)dataTableOption[0];
		int 		begin 		= (int)	dataTableOption[1];
		int 		number 		= (int)	dataTableOption[2]; 
		int 		sortCol 	= (int)	dataTableOption[3]; 
		int 		sortTyp 	= (int)	dataTableOption[4];

		String sortColName = null;
		String sortDir	   = null;

		switch(sortCol) {
		case 0: sortColName = TaNsoGroup.ATT_I_ID; break;		
		case 1: sortColName = TaNsoGroup.ATT_D_DATE_01; break;
		case 3: sortColName = TaNsoGroup.ATT_T_NAME; break;
		default: sortColName = TaNsoGroup.ATT_T_NAME; break;
		}

		if (sortColName != null) {
			switch(sortTyp) {
			case 0: sortDir = "ASC"; break;
			case 1: sortDir = "DESC"; break;								
			}
		}

		List<TaNsoGroup> result = null;

		Criterion cri = reqCri(searchKey, manId, status, typ01);

		Order order = null;
		if (sortColName!=null && sortDir!=null )
			if 		(sortDir.equals("DESC")) order = Order.desc(sortColName);
			else if (sortDir.equals("ASC"))  order = Order.asc(sortColName);

		if (order!=null)
			result = TaNsoGroup.DAO.reqList(begin, number, order, cri);
		else 
			result = TaNsoGroup.DAO.reqList(begin, number, cri);
		
		return result;
	}

	private static Integer reqFilterListDynCount(Object[] dataTableOption, Integer manId, Integer status, Integer typ01) throws Exception {
		List<String>	searchKey				= (List<String>)dataTableOption[0];
		Criterion cri = reqCri(searchKey, manId, status, typ01);

		Integer result = TaNsoGroup.DAO.reqCount(cri).intValue();
		return result;
	}

	private static Criterion reqCri(List<String> searchKey, Integer manId, Integer status, Integer typ01) throws Exception{
		Criterion cri = Restrictions.eq(TaNsoGroup.ATT_I_PER_MANAGER, manId);
		
		if(typ01 != null) cri = Restrictions.and(cri, Restrictions.eq(TaNsoGroup.ATT_I_TYPE_01, typ01));
		else cri = Restrictions.and(cri, Restrictions.eq(TaNsoGroup.ATT_I_TYPE_01, TaNsoGroup.TYP_01_ROOM_MULTI));
		
		if(status != null)
			cri = Restrictions.and(cri, Restrictions.eq(TaNsoGroup.ATT_I_STATUS_01, status));
		
		

		Criterion criKey =   reqRestriction (searchKey);
		if (criKey !=null)
			cri = Restrictions.and(cri, criKey);

		return cri;
	}
	
	private static Criterion reqRestriction(List<String> searchKey) {	
		if (searchKey==null) return null;
		Criterion cri = null;
		for (String s: searchKey){			
			if (cri ==null) 
				cri = Restrictions.or(
						Restrictions.ilike(TaNsoGroup.ATT_T_NAME, s), 
						Restrictions.ilike(TaNsoGroup.ATT_T_INFO_01, s));
			else {
				cri = Restrictions.and(cri, Restrictions.or(
						Restrictions.ilike(TaNsoGroup.ATT_T_NAME, s), 
						Restrictions.ilike(TaNsoGroup.ATT_T_INFO_01, s)));
			}
		}

		return cri;
	}
	
	//--------------------------------------------------------------------------------------------
	private static void doLstByMember(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		ResultPagination  	res = reqLstByMember(user, json); //and other params if necessary
		if (res.reqList()==null ){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		
		API.doResponse(response	, ToolJSON.reqJSonString(
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, res));
		
	}
	/*
	  from select max(msg.I_Id), grp.I_ID from TA_MSG_MSG msg inner join TA_NSO_GROUP on  msg.I_Entity_ID = grp.I_ID and msg.I_Entity_Type =40000 group by grp.I_ID
	 */
	static String SQL_GRP_BY_USERID_WHERE_SEARCHKEY = "where lower(gr.T_Name) like lower('%s') or lower(gr.T_Description) like lower('%s') ";
	static String SQL_GRP_BY_USERID = "select distinct gr.*" + 
			" from TA_NSO_GROUP_MEMBER m " + 
			" inner join TA_NSO_GROUP   gr  on  m.I_Group = gr.I_ID and m.I_Aut_User = %d " + 
			" inner join TA_AUT_USER    u   on  u.I_ID = %d and  m.I_Status = 2 " + 
			" left join ( select max(msg.I_Id) as msgId, grp.I_ID as gId from TA_MSG_MESSAGE  msg inner join TA_NSO_GROUP grp on  msg.I_Entity_ID = grp.I_ID and msg.I_Entity_Type =40000 group by grp.I_ID) lastMsg " +
			"             on lastMsg.gId = gr.I_ID " +
			" %s" +
			" order by  lastMsg.msgId desc"; //"gr.T_Name";
	
	static String SQL_GRP_BY_USERID_COUNT = "select count(distinct gr.I_ID)" + 
			" from TA_NSO_GROUP_MEMBER m " + 
			" inner join TA_NSO_GROUP   gr  on  m.I_Group = gr.I_ID and m.I_Aut_User = %d and m.I_Status = 2 " + 
			" %s ";
	
	private static 	ResultPagination reqLstByMember(TaAutUser user, JSONObject json) throws Exception  {
		//		Integer			manId		= ToolData.reqInt	(json, "manId"		, null);
		Integer 		manId 		= user.reqPerManagerId();

		Integer 		begin		= ToolData.reqInt	(json, "begin"		, 0	);
		Integer 		number		= ToolData.reqInt	(json, "number"		, 20);
		Integer 		total		= ToolData.reqInt	(json, "total"		, 0	);
		String 			searchKey   = ToolData.reqStr	(json, "searchKey"	, "");

		String 			sortCol   	= ToolData.reqStr	(json, "sortCol"		, "name");
		Integer 		sortDir   	= ToolData.reqInt	(json, "sortDir"		, 0);

		Integer			stat		= ToolData.reqInt	(json, "stat"		, TaNsoGroup.STAT_01_ACTIVE); 
		Boolean 		hardLoad   	= ToolData.reqBool	(json, "hardLoad"	, false);

		if(hardLoad)	cache_rs.doClear();

		String keyWord 	= "member_" + manId + "_" +  begin + "_" + number + "_" + total + "_" + searchKey + "_" + sortCol+ "_" + sortDir +"_" +  stat+ "_" + user.reqId();

		ResultPagination rs =	cache_rs.reqData(keyWord);

		if(rs==null) {
			Object[] dataTableOption 	= reqDataTableOption(searchKey, begin, number, sortCol, sortDir);
			List<TaNsoGroup> grpList 	= reqListByMemberFilterDyn(dataTableOption, user.reqId());
			
			Set<Integer> ids = ToolSet.reqSetInt(grpList, TaNsoGroup.ATT_I_ID);
			List<TaTpyDocument> docs = TaTpyDocument.DAO.reqList_In(TaTpyDocument.ATT_I_ENTITY_ID, ids, Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_TYPE, DefDBExt.ID_TA_NSO_GROUP))	;
			
			if (docs != null && docs.size() > 0) {
				Map<Integer, TaTpyDocument> mapDoc = new HashMap<Integer, TaTpyDocument>();
				for (TaTpyDocument doc : docs) {
					mapDoc.put((Integer) doc.req(TaTpyDocument.ATT_I_ENTITY_ID), doc);
				}
				
				for(TaNsoGroup grp : grpList) {
					grp.reqSet(TaNsoGroup.ATT_O_AVATAR, mapDoc.get((Integer) grp.req(TaNsoGroup.ATT_I_ID)));
				}
			}

			if (grpList==null || grpList.size() ==0 ){
				rs								= new ResultPagination(grpList, 0, 0, 0);
			}else {
				if (total == 0 )	total		= reqFilterListByMemberDynCount(dataTableOption, user.reqId());
				rs								= new ResultPagination(grpList, total, begin, number);
			}

			if (grpList!=null && grpList.size()>0)
				cache_rs.reqPut(keyWord, rs);			
		} else {
			cache_rs.reqCheckIfOld(keyWord); 
		}

		return rs;

	}
	
	private static List<TaNsoGroup> reqListByMemberFilterDyn(Object[] dataTableOption, Integer uId) throws Exception {
		List<String>			searchKey		= (List<String>)dataTableOption[0];
		String					sKey			= null;
		if (searchKey.size()>0) sKey			= searchKey.get(0);
		int 					begin 			= (int)	dataTableOption[1];
		int 					number 			= (int)	dataTableOption[2]; 
		
		String sqlWhere 		= "";
		if (sKey!=null) sqlWhere= String.format(SQL_GRP_BY_USERID_WHERE_SEARCHKEY, sKey, sKey);
		
		String sql = String.format(SQL_GRP_BY_USERID, uId, uId, sqlWhere);
		List<TaNsoGroup> result = TaNsoGroup.DAO.reqList(begin, number, sql);

		return result;
	}
	
	private static Integer reqFilterListByMemberDynCount(Object[] dataTableOption, Integer uId) throws Exception {
		List<String>			searchKey		= (List<String>)dataTableOption[0];
		String					sKey			= null;
		if (searchKey.size()>0) sKey			= searchKey.get(0);
		
		String sqlWhere 		= "";
		if (sKey!=null) sqlWhere= String.format(SQL_GRP_BY_USERID_WHERE_SEARCHKEY, sKey, sKey);
		String sql = String.format(SQL_GRP_BY_USERID_COUNT, uId, sqlWhere);
		
		Integer result = TaNsoGroup.DAO.reqCount(sql).intValue();
		return result;
	}
	
	private static void doLstSearch(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		Integer				manId	    = user.reqPerManagerId(); 
		String				searchkey	= ToolData.reqStr	(json, "searchkey"	, "%");// Integer.parseInt(request.getParameter("typ01")); 	
		Integer				nbLineMax	= ToolData.reqInt	(json, "nbLine"		, 10 );
		Integer				typ01   	= ToolData.reqInt	(json, "typ01"		, TaNsoGroup.TYP_01_WORK );
		Integer				stat	    = ToolData.reqInt	(json, "stat"		, TaNsoGroup.STAT_01_ACTIVE );
	
		Criterion cri	= null;
		cri 			= Restrictions.like(TaNsoGroup.ATT_T_NAME							        , "%"+searchkey+"%");
		cri 			= Restrictions.or(cri, Restrictions.like(TaNsoGroup.ATT_T_INFO_01		, "%"+searchkey+"%"));
		cri				= Restrictions.and(cri, 
				Restrictions.eq(TaNsoGroup.ATT_I_TYPE_01	, typ01),
				Restrictions.eq(TaNsoGroup.ATT_I_STATUS_01		, stat)
				);
		
		List<TaNsoGroup> listGroup = TaNsoGroup.DAO.reqList(0, nbLineMax, Order.asc(TaNsoGroup.ATT_T_NAME), cri);
		
		API.doResponse(response	, ToolJSON.reqJSonString(
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, listGroup));
		
	}



	private static void doDocAdd(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		Integer	grpId		= ToolData.reqInt	(json, "grpId"	, null);
		String	docIds		= ToolData.reqStr	(json, "docIds"	, null);
		if (grpId==null || docIds==null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		TaTpyDocument.reqModByIds(DefDBExt.ID_TA_NSO_GROUP, grpId, docIds);
		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES					
				));

	}

	private static void doDelEnt(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		boolean ok = reqNotValidateMember(user, json);

		if(!ok) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES					
				));

		Integer	memId		= ToolData.reqInt	(json, "memId"	, null);
		Integer	grpId		= ToolData.reqInt	(json, "grpId"	, null);

		JSONObject msg  = new JSONObject();
		msg.put("memId", memId);
		msg.put("grpId", grpId);
		String 	msgOut 	= ToolJSON.reqJSonString("type", ChatMessage.MSG_CHAT_GROUP_REFUSE, "payLoad", msg);
		List<javax.websocket.Session> lst = ServiceChatEndpoint.reqLstSessionByUserId(memId);
		ServiceChatEndpoint.doDistributeMsg (lst, msgOut);

	}

	private static boolean reqNotValidateMember(TaAutUser user, JSONObject json) throws Exception {
		Integer	reId		= ToolData.reqInt	(json, "id"	, null);
		if(reId == null)	return false;
		TaNsoGroupMember ent = TaNsoGroupMember.DAO.reqEntityByRef(reId);
		if(ent == null)		return false;

		TaNsoGroupMember.DAO.doRemove(ent);
		return true;
	}

	private static void doValidated(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		boolean ok = reqValidateMember(user, json);

		if(!ok) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES 				
				));

		Integer	memId		= ToolData.reqInt	(json, "memId"	, null);
		Integer	grpId		= ToolData.reqInt	(json, "grpId"	, null);

		JSONObject msg  = new JSONObject();
		msg.put("memId", memId);
		msg.put("grpId", grpId);
		String 	msgOut 	= ToolJSON.reqJSonString("type", ChatMessage.MSG_CHAT_GROUP_ACCEPT, "payLoad", msg);
		List<javax.websocket.Session> lst = ServiceChatEndpoint.reqLstSessionByUserId(memId);
		ServiceChatEndpoint.doDistributeMsg (lst, msgOut);
	}

	private static boolean reqValidateMember(TaAutUser user, JSONObject json) throws Exception {
		Integer	reId		= ToolData.reqInt	(json, "id"	, null);
		if(reId == null)	return false;
		TaNsoGroupMember ent = TaNsoGroupMember.DAO.reqEntityByRef(reId);
		if(ent == null)		return false;

		ent.reqSet(TaNsoGroupMember.ATT_I_STATUS	, TaNsoGroupMember.STAT_ACTIVE);
		ent.reqSet(TaNsoGroupMember.ATT_D_DATE_02	, new Date());

		TaNsoGroupMember.DAO.doMerge(ent);
		return true;
	}

	private static void doTransform(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		boolean ok = reqTransformUser(user, json);

		if(!ok) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES					
				));

	}

	private static boolean reqTransformUser(TaAutUser user, JSONObject json) throws Exception {
		Integer	reId		= ToolData.reqInt	(json, "id"	, null);
		Integer	typ			= ToolData.reqInt	(json, "typ"	, null);
		if(reId == null)	return false;
		TaNsoGroupMember ent = TaNsoGroupMember.DAO.reqEntityByRef(reId);
		if(ent == null)		return false;

		ent.reqSet(TaNsoGroupMember.ATT_I_TYPE		, typ);
		ent.reqSet(TaNsoGroupMember.ATT_D_DATE_02	, new Date());

		TaNsoGroupMember.DAO.doMerge(ent);
		return true;
	}

	private static void doAddMember(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		TaNsoGroupMember ent = reqAddMember(user, json);

		if(ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, ent
				));

	}

	private static TaNsoGroupMember reqAddMember(TaAutUser user, JSONObject json) throws Exception {
		JSONObject obj = ToolData.reqJson	(json, "obj", null);
		if(obj == null)	return null;

		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaNsoGroupMember.class);

		TaNsoGroupMember re 		= new TaNsoGroupMember(attr);
		re.reqSet(TaNsoGroupMember.ATT_D_DATE_01		, new Date());
		re.reqSet(TaNsoGroupMember.ATT_I_STATUS			, TaNsoGroupMember.STAT_ACTIVE);
		re.reqSet(TaNsoGroupMember.ATT_I_TYPE			, TaNsoGroupMember.TYP_WORKER);

		TaNsoGroupMember.DAO.doPersist(re);
		return re;
	}

	private static void doNewRoom(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		TaNsoGroup ent = reqAddGroup(user, json);

		if(ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, ent
				));

	}

	private static TaNsoGroup reqAddGroup(TaAutUser user, JSONObject json) throws Exception {
		JSONObject obj = ToolData.reqJson	(json, "obj", null);
		if(obj == null)	return null;
		//		int 				typ02		= Integer.parseInt(obj.get("typ02").toString());

		JSONArray		docs		= (JSONArray) obj.get("files");

		Map<String, Object> attr 	= API.reqMapParamsByClass(obj, TaNsoGroup.class);

		TaNsoGroup ent 		= new TaNsoGroup(attr);
		ent.reqSet(TaNsoGroup.ATT_D_DATE_01		, new Date());
		ent.reqSet(TaNsoGroup.ATT_I_STATUS_01		, TaNsoGroup.STAT_01_ACTIVE);
		ent.reqSet(TaNsoGroup.ATT_I_PER_MANAGER	, user.reqPerManagerId());
		ent.reqSet(TaNsoGroup.ATT_I_TYPE_01		, TaNsoGroup.TYP_01_ROOM_MULTI);
		//		ent.reqSet(TaNsoGroup.ATT_I_TYPE_02		, typ02);

		ent.reqSet(TaNsoGroup.ATT_T_REF	, "GRP_" + ToolDate.reqString(new Date(), "yyMMddHHmmss"));

		TaNsoGroup.DAO.doPersist(ent);

		Map<String, Object> mem 	= new HashMap<String, Object>();
		TaNsoGroupMember memG = new TaNsoGroupMember(mem);
		memG.reqSet(TaNsoGroupMember.ATT_I_NSO_GROUP	, ent.reqRef());
		memG.reqSet(TaNsoGroupMember.ATT_I_AUT_USER		, user.reqId());
		memG.reqSet(TaNsoGroupMember.ATT_I_STATUS		, TaNsoGroupMember.STAT_ACTIVE);
		memG.reqSet(TaNsoGroupMember.ATT_I_TYPE			, TaNsoGroupMember.TYP_MANAGER);
		memG.reqSet(TaNsoGroupMember.ATT_D_DATE_01		, new Date());
		TaNsoGroupMember.DAO.doPersist(memG);

		ent.reqSet(TaNsoOffer.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(DefAPI.SV_MODE_NEW, user, ENT_TYP, ent.reqId(), docs));

		ent.doBuildDocuments(false);

		return ent;
	}

	private static void doModRoom(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		TaNsoGroup ent = reqModGroup(user, json);

		if(ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, ent
				));

	}

	private static TaNsoGroup reqModGroup(TaAutUser user, JSONObject json) throws Exception {
		JSONObject obj = ToolData.reqJson	(json, "obj", null);
		if(obj == null)	return null;
		int 				objId	= Integer.parseInt(obj.get("id").toString());

		Integer 			typ02	= Integer.parseInt(obj.get("typ02").toString());
		String 				val01	= obj.get("val01").toString();
		String 				name	= obj.get("name").toString();

		TaNsoGroup  	ent	 		= TaNsoGroup.DAO.reqEntityByRef(objId);
		if (ent==null){
			return null;
		}
		ent.reqSet(TaNsoGroup.ATT_T_INFO_03, val01);
		ent.reqSet(TaNsoGroup.ATT_I_TYPE_02, typ02);
		ent.reqSet(TaNsoGroup.ATT_T_NAME, name);

		JSONArray 		docs 	= (JSONArray) obj.get("files");
		ent.reqSet(TaNsoOffer.ATT_O_DOCUMENTS, TaTpyDocument.reqListCheck(DefAPI.SV_MODE_MOD, user, ENT_TYP, ent.reqId(), docs));
		
		TaNsoGroup.DAO.doMerge(ent);

		ent.doBuildDocuments(false);

		return ent;
	}

	private static void doUser(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		TaNsoGroup ent = reqGroupUser(user, json);

		if(ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, ent
				));

	}

	private static TaNsoGroup reqGroupUser(TaAutUser user, JSONObject json) throws Exception {
		Integer 	uId 	= (Integer)user.reqRef();
		Integer		uId01	= ToolData.reqInt	(json, "uId01", null); //id of group or person		
		Integer		uId02	= ToolData.reqInt	(json, "uId02", null); //id of group or person

		if (uId01 == null || uId02==null)	return null;
		if (!uId.equals(uId01) && !uId.equals(uId02)) 	return null;

		String 		ref 	= uId01 < uId02 ? "GRP_" + uId01 + "_" + uId02 : "GRP_" + uId02 + "_" + uId01;//key relation 2 user
		TaNsoGroup 	gr 		= TaNsoGroup.DAO.reqEntityByValue(TaNsoGroup.ATT_T_REF, ref);


		if(gr == null) {
			JSONObject js = new JSONObject();
			js.put("img"	, null);

			String			login01		= (String) 			user.req(TaAutUser.ATT_T_LOGIN_01);
			TaTpyDocument 	avatar01 	= (TaTpyDocument) 	user.req(TaAutUser.ATT_O_AVATAR); 
			String 			val0101		= (String) 			user.req(TaAutUser.ATT_T_INFO_01); 

			JSONObject		js01		= new JSONObject();
			js01.put("img"	, avatar01!=null?avatar01.req(TaTpyDocument.ATT_T_INFO_03):null);
			js01.put("login", login01);

			js.put(uId01+""	, js01);
			String 	desc	= login01 + "," + login01 + "," + val0101 + "," + val0101;	
			if (!uId01.equals(uId02)) {
				TaAutUser 		u02 		= TaAutUser.DAO.reqEntityByID(uId02);
				String			login02		= (String) 			u02.req(TaAutUser.ATT_T_LOGIN_01);
				TaTpyDocument 	avatar02 	= (TaTpyDocument) 	u02.req(TaAutUser.ATT_O_AVATAR); 
				String 			val0102		= (String) 			u02.req(TaAutUser.ATT_T_INFO_01); 

				JSONObject		js02		= new JSONObject();
				js02.put("img"	, avatar02!=null?avatar02.req(TaTpyDocument.ATT_T_INFO_03):null);
				js02.put("login", login02);

				js.put(uId02+""	, js02);

				desc 		= uId01 < uId02 ?  (login01 + ", " + login02 + ", " + val0101 + ", " + val0102) : (login02 + ", " + login01+ ", " + val0101 + ", " + val0102);
			}

			gr = new TaNsoGroup(ref, TaNsoGroup.TYP_01_ROOM_SINGLE);
			gr.reqSet(TaNsoGroup.ATT_I_TYPE_02		, TaNsoGroup.TYP_02_PRIVATE);
			gr.reqSet(TaNsoGroup.ATT_I_STATUS_01	, TaNsoGroup.STAT_01_ACTIVE);
			gr.reqSet(TaNsoGroup.ATT_D_DATE_01		, new Date());
			gr.reqSet(TaNsoGroup.ATT_T_NAME			, ref);
			gr.reqSet(TaNsoGroup.ATT_T_INFO_01		, desc);
			gr.reqSet(TaNsoGroup.ATT_T_INFO_03		, js.toJSONString());

			TaNsoGroup.DAO.doPersist(gr);

			reqNewMember(uId01, (Integer)gr.reqRef());
			reqNewMember(uId02, (Integer)gr.reqRef());
		}

		return gr;
	}

	private static void reqNewMember(Integer uId, Integer grId) throws Exception {
		TaNsoGroupMember mem = new TaNsoGroupMember();
		mem.reqSet(TaNsoGroupMember.ATT_I_NSO_GROUP	, grId);
		mem.reqSet(TaNsoGroupMember.ATT_I_AUT_USER	, uId);
		mem.reqSet(TaNsoGroupMember.ATT_I_STATUS	, TaNsoGroupMember.STAT_ACTIVE);
		mem.reqSet(TaNsoGroupMember.ATT_I_TYPE		, TaNsoGroupMember.TYP_MANAGER);
		mem.reqSet(TaNsoGroupMember.ATT_D_DATE_01	, new Date());
		TaNsoGroupMember.DAO.doPersist(mem);
	}

	private static void doDelRoom(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		boolean ok = reqDelGroup(user, json);

		if(!ok) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES
				));

	}

	private static boolean reqDelGroup(TaAutUser user, JSONObject json) throws Exception {
		Integer id = ToolData.reqInt	(json, "id", null);
		if(id == null)	return false;

		TaNsoGroup gr = TaNsoGroup.DAO.reqEntityByRef(id);

		if(gr == null)	return false;

		//delete member
		List<TaNsoGroupMember> members = TaNsoGroupMember.DAO.reqList(Restrictions.eq(TaNsoGroupMember.ATT_I_NSO_GROUP, id));

		if(members == null || members.size() == 0)	return false;

		boolean isOwner = false;
		for(TaNsoGroupMember m : members) {
			if((Integer)m.req(TaNsoGroupMember.ATT_I_AUT_USER) == (int)user.reqId() && (Integer)m.req(TaNsoGroupMember.ATT_I_TYPE) == TaNsoGroupMember.TYP_MANAGER) {
				isOwner = true; break;
			}
		}

		if(!isOwner)	return false;

		TaNsoGroupMember.DAO.doRemove(members);

		//delete msg and doc of msg
		List<TaMsgMessage> msgs = TaMsgMessage.DAO.reqList(
				Restrictions.eq(TaMsgMessage.ATT_I_ENTITY_TYPE	, DefDBExt.ID_TA_NSO_GROUP),
				Restrictions.eq(TaMsgMessage.ATT_I_ENTITY_ID	, id),
				Restrictions.eq(TaMsgMessage.ATT_I_TYPE_01		, TaNsoGroup.TYP_01_ROOM_MULTI )
				);

		if(msgs != null && msgs.size() > 0) {
			Set<Integer> setId = ToolSet.reqSetInt(msgs, TaMsgMessage.ATT_I_ID);

			List<TaTpyDocument> lstDoc =  TaTpyDocument.DAO.reqList_In(TaTpyDocument.ATT_I_ENTITY_ID, setId, Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_TYPE, DefDBExt.ID_TA_MSG_MESSAGE));
			if(lstDoc != null && lstDoc.size() > 0) {
				TaTpyDocument.DAO.doRemove(lstDoc);
			}

			TaMsgMessage.DAO.doRemove(msgs);
		}
		//delete in store msg
		List<TaMsgMessageStore> msgStore = TaMsgMessageStore.DAO.reqList(
				Restrictions.eq(TaMsgMessageStore.ATT_I_ENTITY_TYPE		, DefDBExt.ID_TA_NSO_GROUP),
				Restrictions.eq(TaMsgMessageStore.ATT_I_ENTITY_ID_01	, id),
				Restrictions.eq(TaMsgMessageStore.ATT_I_TYPE_01			, TaNsoGroup.TYP_01_ROOM_MULTI )
				);

		if(msgStore != null && msgStore.size() > 0) {
			TaMsgMessageStore.DAO.doRemove(msgStore);
		}

		//del doc of group and group
		List<TaTpyDocument> lstDoc =  TaTpyDocument.DAO.reqList(
				Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_TYPE	, DefDBExt.ID_TA_NSO_GROUP),
				Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_ID	, id)
				);

		if(lstDoc != null && lstDoc.size() > 0) {
			TaTpyDocument.DAO.doRemove(lstDoc);
		}

		//delete member
		List<TaNsoGroupMember> mems = TaNsoGroupMember.DAO.reqList(Restrictions.eq(TaNsoGroupMember.ATT_I_NSO_GROUP		, id));

		if(mems != null && mems.size() > 0) {
			TaNsoGroupMember.DAO.doRemove(mems);
		}

		//del post of group and group
		List<TaNsoPost> lstP =  TaNsoPost.DAO.reqList(
				Restrictions.eq(TaNsoPost.ATT_I_VAL_01	, DefDBExt.ID_TA_NSO_GROUP),
				Restrictions.eq(TaNsoPost.ATT_I_VAL_02	, id)
				);

		if(lstP != null && lstP.size() > 0) {
			Set<Integer> setIdPost = ToolSet.reqSetInt(lstP, TaNsoPost.ATT_I_ID);
			List<TaTpyDocument> lstfilesP =  TaTpyDocument.DAO.reqList_In(TaTpyDocument.ATT_I_ENTITY_ID, setIdPost, Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_TYPE, DefDBExt.ID_TA_NSO_POST));
			if(lstfilesP != null && lstfilesP.size() > 0) {
				TaTpyDocument.DAO.doRemove(lstfilesP);
			}
			TaNsoPost.DAO.doRemove(lstP);
		}

		TaNsoGroup.DAO.doRemove(gr);

		return true;
	}

	private static void doOutRoom(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		boolean ok = reqOutGroup(user, json);

		if(!ok) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES
				));

	}

	private static boolean reqOutGroup(TaAutUser user, JSONObject json) throws Exception {
		Integer id 		= ToolData.reqInt	(json, "id", null);
		Integer idSel 	= ToolData.reqInt	(json, "idSel", null);
		if(id == null)	return false;

		TaNsoGroup gr = TaNsoGroup.DAO.reqEntityByRef(id);

		if(gr == null)	return false;

		List<TaNsoGroupMember> members = TaNsoGroupMember.DAO.reqList(Restrictions.eq(TaNsoGroupMember.ATT_I_NSO_GROUP, id));

		if(members == null || members.size() == 0)	return false;

		boolean isOwner = false;
		TaNsoGroupMember member 	= null;
		TaNsoGroupMember memberSel 	= null;
		for(TaNsoGroupMember m : members) {
			if((Integer)m.req(TaNsoGroupMember.ATT_I_AUT_USER) == (int)user.reqId()) {
				member = m;
				if((Integer)m.req(TaNsoGroupMember.ATT_I_TYPE) == TaNsoGroupMember.TYP_MANAGER)	isOwner = true;
			}
			if(idSel!=null && (Integer)m.req(TaNsoGroupMember.ATT_I_AUT_USER) == (int)idSel) {
				memberSel = m;
			}
		}

		if(isOwner) {
			if(memberSel == null)	return false;
			memberSel.reqSet(TaNsoGroupMember.ATT_I_TYPE, TaNsoGroupMember.TYP_MANAGER);
			TaNsoGroupMember.DAO.doMerge(memberSel);
		} 

		TaNsoGroupMember.DAO.doRemove(member);

		return true;
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doLstNewWait(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		List<TaNsoGroup> 	list = reqLstNewWait(user, json); //and other params if necessary
		if (list==null || list.size()==0){
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, list 
				));				
	}



	private static List<TaNsoGroup> reqLstNewWait(TaAutUser user, JSONObject json, Object...params) throws Exception {
		Integer 			objMan		= ToolData.reqInt	(json, "manId"	, null	);
		String  			grpIds		= ToolData.reqStr	(json, "idGrps"		, null	);

		Set<Integer> ids = null;
		if (grpIds!=null && grpIds.length()>0) {
			ids = ToolSet.reqSetInt(grpIds);
			if (ids.size()==0) ids = null;
		}


		List<TaNsoGroup>  list = TaNsoGroup.DAO.reqList_In(TaNsoGroup.ATT_I_ID, ids);
		if(list == null) return null;

		return list;
	}

	//---------------------------------------------------------------------------------------------------------
	private static void doAddMemberByMail(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {	
		//ServerLogTool.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");
		String 				emails	= ToolData.reqStr	(json, "emails"		, null);
		Integer 			grpId	= ToolData.reqInt	(json, "groupId"		, null);	

		if(emails == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		TaNsoGroup ent   = TaNsoGroup.DAO.reqEntityByRef(grpId);

		if(ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		boolean sendOK = doSendEmailInviteJoin(user, emails, ent);

		if(!sendOK) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES
				));

	}

	//-------------------------------------------------------------------------------------------------------
	private static String EMAIL_HOST 		= "";
	private static String EMAIL_PORT 		= "";
	private static String EMAIL_LOGIN 		= "";
	private static String EMAIL_PWD 		= "";

	private static String 	EMAIL_JOIN_TITLE 	= "";
	private static String 	EMAIL_JOIN_CONT 	= "";
	private static Integer 	EMAIL_JOIN_CONT_NB 	= 1;
	private static String 	EMAIL_JOIN_LINK		= "";


	public static boolean doSendEmailInviteJoin(TaAutUser user, String emails, TaNsoGroup ent) throws Exception{
		String        gName   = ent.req(TaNsoGroup.ATT_T_NAME).toString();

		String emailTitle 	= EMAIL_JOIN_TITLE;
		String emailLink	= String.format(EMAIL_JOIN_LINK,  ent.req(TaNsoGroup.ATT_I_ID));
		String emailCont 	= reqFormattedContent(EMAIL_JOIN_CONT, gName, emailLink, EMAIL_JOIN_CONT_NB); 

		boolean sendOK = ToolEmail.canSendEmail(
				EMAIL_HOST, EMAIL_PORT, null, EMAIL_LOGIN, EMAIL_PWD, 
				EMAIL_LOGIN, 
				emailTitle, emailCont,
				emails, 
				null, null, null);	

		return sendOK;

	}

	private static String reqFormattedContent(String template, String msg01, String msg02, Integer count) {
		String content = "";
		switch(count) {
		case 0:
		case 1: content 	= String.format(template, msg01, msg02);break;
		case 2: content 	= String.format(template, msg01, msg02, msg01, msg02);break;
		case 3: content 	= String.format(template, msg01, msg02, msg01, msg02, msg01, msg02);break;
		case 4: content 	= String.format(template, msg01, msg02, msg01, msg02, msg01, msg02, msg01, msg02);break;
		case 5: content 	= String.format(template, msg01, msg02, msg01, msg02, msg01, msg02, msg01, msg02, msg01, msg02);break;
		default : content 	= template;
		}
		return content;
	}


	private static void doJoinMemberAfterRegister(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception  {

		Integer groupId					=  ToolData.reqInt	(json, "groupId", null);

		if(groupId == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		TaNsoGroup ent = doJoinMemberAfterRegister(user, groupId);

		if(ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response	, ToolJSON.reqJSonString(
				// filter,
				DefJS.SESS_STAT	, 1, 
				DefJS.SV_CODE	, DefAPI.SV_CODE_API_YES,
				DefJS.RES_DATA	, ent
				));

	}

	public static TaNsoGroup doJoinMemberAfterRegister(TaAutUser user, Integer groupId) throws Exception {
		TaNsoGroup group 	= TaNsoGroup.DAO.reqEntityByRef(groupId);
		boolean canAdd 		= true;
		
		if (group == null) return group;

		List<TaNsoGroupMember> lstMember = TaNsoGroupMember.DAO.reqList(Restrictions.eq(TaNsoGroupMember.ATT_I_NSO_GROUP, groupId));

		if (lstMember != null && lstMember.size() > 0) {
			for (TaNsoGroupMember mem : lstMember) {
				if (mem.req(TaNsoGroupMember.ATT_I_AUT_USER).equals(user.reqId())) {
					canAdd = false;
				}
			}
		}
		
		if (canAdd) {
			Integer typ02   = (Integer) group.req(TaNsoGroup.ATT_I_TYPE_02);
	
			Integer statMember  = TaNsoGroupMember.STAT_NEW;
			if(typ02.equals(TaNsoGroup.TYP_02_PUBLIC)) statMember = TaNsoGroupMember.STAT_ACTIVE;
	
			Map<String, Object> attr = new HashMap<String, Object>();
			TaNsoGroupMember ent = new TaNsoGroupMember(attr);
			ent.reqSet(TaNsoGroupMember.ATT_I_NSO_GROUP				, groupId);
			ent.reqSet(TaNsoGroupMember.ATT_I_AUT_USER			, user.reqId());
			ent.reqSet(TaNsoGroupMember.ATT_I_STATUS			, statMember);
			ent.reqSet(TaNsoGroupMember.ATT_I_TYPE				, TaNsoGroupMember.TYP_WORKER);
			ent.reqSet(TaNsoGroupMember.ATT_D_DATE_01			, new Date());
			TaNsoGroupMember.DAO.doPersist(ent);
		}
		return group;
	}

	//---------------------------------------------------------------------------------------------------------

}
