package com.hnv.db.nso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefDBExt;
import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;

/**
 * ViNsoPost by H&V SAS //rut gon de tinh evaluation nhanh hon
 */
@Entity
public class ViNsoPost extends EntityAbstract<ViNsoPost> {
	public static final double EVAL_MIN = TaNsoPost.EVAL_MIN;
	public static final double EVAL_MAX = TaNsoPost.EVAL_MAX;
	private static final long serialVersionUID = 1L;

	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_F_EVAL_01                         =	"F_Eval_01";
	public static final String	COL_F_EVAL_02                         =	"F_Eval_02";
	public static final String	COL_F_EVAL_03                         =	"F_Eval_03";
	public static final String	COL_F_EVAL_04                         =	"F_Eval_04";
	public static final String	COL_F_EVAL_05                         =	"F_Eval_05";	

	public static final String	COL_T_REF                             =	"T_Ref";
	public static final String	COL_T_TITLE                           =	"T_Title";

	public static final String	COL_T_CONTENT_01                      =	"T_Content_01";
	//	public static final String	COL_T_CONTENT_02                      =	"T_Content_02";
	//	public static final String	COL_T_CONTENT_03                      =	"T_Content_03";
	//	public static final String	COL_T_CONTENT_04                      =	"T_Content_04";
	//	public static final String	COL_T_CONTENT_05                      =	"T_Content_05";

	
	public static final String	COL_I_STATUS                          =	"I_Status";
	public static final String	COL_T_COMMENT                         =	"T_Comment";

	public static final String	COL_D_DATE_NEW                        =	"D_Date_New";
	public static final String	COL_D_DATE_MOD                        =	"D_Date_Mod";

	public static final String	COL_I_NB_RESP                         =	"I_Nb_Resp";
	public static final String	COL_I_PARENT                          =	"I_Parent";

	public static final String	COL_I_ENTITY_TYPE                     =	"I_Entity_Type";
	public static final String	COL_I_ENTITY_ID                       =	"I_Entity_ID";
	public static final String	COL_T_ENTITY_TITLE                    =	"T_Entity_Title";

	public static final String	COL_I_TYPE_01                         =	"I_Type_01";
	public static final String	COL_I_TYPE_02                      	  =	"I_Type_02";
	public static final String	COL_I_TYPE_03                         =	"I_Type_03";

	public static final String	COL_I_AUT_USER_NEW                    =	"I_Aut_User_New";

	public static final String	COL_T_PROPERTY_01                     =	"T_Property_01";
	public static final String	COL_T_PROPERTY_02                     =	"T_Property_02";
	public static final String	COL_T_PROPERTY_03                     =	"T_Property_03";
	public static final String	COL_T_PROPERTY_04                     =	"T_Property_04";
	
	public static final String	COL_T_AVATAR                          =	"T_Avatar";
	public static final String	COL_T_TYPE_AVATAR                     =	"T_Type_Avatar";
	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_F_EVAL_01                         =	"F_Eval_01";
	public static final String	ATT_F_EVAL_02                         =	"F_Eval_02";
	public static final String	ATT_F_EVAL_03                         =	"F_Eval_03";
	public static final String	ATT_F_EVAL_04                         =	"F_Eval_04";
	public static final String	ATT_F_EVAL_05                         =	"F_Eval_05";

	public static final String	ATT_T_REF                             =	"T_Ref";
	public static final String	ATT_T_TITLE                           =	"T_Title";

	public static final String	ATT_T_CONTENT_01                      =	"T_Content_01";
	//	public static final String	ATT_T_CONTENT_02                      =	"T_Content_02";
	//	public static final String	ATT_T_CONTENT_03                      =	"T_Content_03";
	//	public static final String	ATT_T_CONTENT_04                      =	"T_Content_04";
	//	public static final String	ATT_T_CONTENT_05                      =	"T_Content_05";

	public static final String	ATT_I_TYPE                            =	"I_Type";
	public static final String	ATT_I_STATUS                          =	"I_Status";
	public static final String	ATT_T_COMMENT                         =	"T_Comment";

	public static final String	ATT_D_DATE_ADD                        =	"D_Date_Add";
	public static final String	ATT_D_DATE_MOD                        =	"D_Date_Mod";

	public static final String	ATT_I_NB_RESP                         =	"I_Nb_Resp";
	public static final String	ATT_I_PARENT                          =	"I_Parent";

	public static final String	ATT_I_ENTITY_TYPE                     =	"I_Entity_Type";
	public static final String	ATT_I_ENTITY_ID                       =	"I_Entity_ID";
	public static final String	ATT_I_ENTITY_TITLE                    =	"I_Entity_Title";

	public static final String	ATT_I_TYPE_01                       =	"I_Type_01";
	public static final String	ATT_I_TYPE_02                       =	"I_Type_02";

	public static final String	ATT_I_AUT_USER                        =	"I_Aut_User";
	
	public static final String	ATT_T_PROPERTY_01                     =	"T_Property_01";
	public static final String	ATT_T_PROPERTY_02                     =	"T_Property_02";
	public static final String	ATT_T_PROPERTY_03                     =	"T_Property_03";
	public static final String	ATT_T_PROPERTY_04                     =	"T_Property_04";
	
	public static final String	ATT_T_AVATAR                          =	"T_Avatar";
	public static final String	ATT_T_TYPE_AVATAR                     =	"T_Type_Avatar";
	
	public static final String	ATT_O_DOCUMENTS                       =	"O_Documents";
	
	public static final String	ATT_O_TRANSLATIONS                	  =	"O_Translations";

	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<ViNsoPost> 	DAO;

	public static final int POST_STATUS_DRAFT				= 0;
	public static final int POST_STATUS_NOT_VALIDATED		= 1;
	public static final int POST_STATUS_VALIDATED			= 2;
	public static final int POST_STATUS_REPORTED			= 3;
	public static final int POST_STATUS_DELETED				= 4;

	public static final int POST_TYPE_VIDEO					= 100;
	public static final int POST_TYPE_NEWS					= 101;
	public static final int POST_TYPE_FENGSHUI_SUGGES		= 200;
	public static final int POST_TYPE_FENGSHUI_OVERVIEW		= 201;
	public static final int POST_TYPE_FENGSHUI_HOUSE		= 202;
	public static final int POST_TYPE_FENGSHUI_OFFICE		= 203;
	public static final int POST_TYPE_FENGSHUI_YEAR_OLD		= 204;

	//---------------------------------------------------------------------------------------------------------------------------------------

	static{
		DAO = new EntityDAO<ViNsoPost>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN) , ViNsoPost.class,RIGHTS);


	}

	//-----------------------Class Attributs-------------------------
	@Id
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;

	@Column(name=COL_F_EVAL_01, nullable = true)
	private	Double          F_Eval_01;

	@Column(name=COL_F_EVAL_02, nullable = true)
	private	Double          F_Eval_02;

	@Column(name=COL_F_EVAL_03, nullable = true)
	private	Double          F_Eval_03;

	@Column(name=COL_F_EVAL_04, nullable = true)
	private	Double          F_Eval_04;

	@Column(name=COL_F_EVAL_05, nullable = true)
	private	Double          F_Eval_05;

	@Column(name=COL_T_REF, nullable = true)
	private	String          T_Ref;

	@Column(name=COL_T_TITLE, nullable = true)
	private	String          T_Title;

	@Column(name=COL_T_CONTENT_01, nullable = true)
	private	String          T_Content_01;

	//	@Column(name=COL_T_CONTENT_02, nullable = true)
	//	private	String          T_Content_02;
	// 
	//	@Column(name=COL_T_CONTENT_03, nullable = true)
	//	private	String          T_Content_03;
	// 
	//	@Column(name=COL_T_CONTENT_04, nullable = true)
	//	private	String          T_Content_04;
	// 
	//	@Column(name=COL_T_CONTENT_05, nullable = true)
	//	private	String          T_Content_05;

	@Column(name=COL_I_TYPE_01, nullable = true)
	private	Integer         I_Type;

	@Column(name=COL_I_STATUS, nullable = true)
	private	Integer         I_Status;

	@Column(name=COL_T_COMMENT, nullable = true)
	private	String          T_Comment;

	@Column(name=COL_D_DATE_NEW, nullable = true)
	private	Date            D_Date_Add;

	@Column(name=COL_D_DATE_MOD, nullable = true)
	private	Date            D_Date_Mod;

	@Column(name=COL_I_AUT_USER_NEW, nullable = true)
	private	Integer         I_Aut_User;

	@Column(name=COL_I_NB_RESP, nullable = true)
	private	Integer         I_Nb_Resp;

	@Column(name=COL_I_PARENT, nullable = true)
	private	Integer         I_Parent;

	@Column(name=COL_I_ENTITY_TYPE, nullable = true)
	private	Integer         I_Entity_Type;

	@Column(name=COL_I_ENTITY_ID, nullable = true)
	private	Integer         I_Entity_ID;

	@Column(name=COL_T_ENTITY_TITLE, nullable = true)
	private	String          T_Entity_Title;

	@Column(name=COL_I_TYPE_02, nullable = true)
	private	Integer         I_Type_01;

	@Column(name=COL_I_TYPE_03, nullable = true)
	private	Integer         I_Type_02;

//	@Column(name=COL_T_PROPERTY_01, nullable = true)
//	private	String          T_Property_01;

//	@Column(name=COL_T_PROPERTY_02, nullable = true)
//	private	String          T_Property_02;

//	@Column(name=COL_T_PROPERTY_03, nullable = true)
//	private	String          T_Property_03;

	@Column(name=COL_T_PROPERTY_04, nullable = true)
	private	String          T_Property_04;
	
	@Column(name=COL_T_AVATAR, nullable = true)
	private	String          T_Avatar;
	
	@Column(name=COL_T_TYPE_AVATAR, nullable = true)
	private	Integer         T_Type_Avatar;
	//-----------------------Transient Variables-------------------------
	@Transient
	private List<TaTpyDocument>			O_Documents;
	
//	@Transient
//	private List<TaTpyTranslation>     O_Translations;

	
	//---------------------Constructeurs-----------------------
	private ViNsoPost(){}

	public ViNsoPost(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		//doInitDBFlag();
	}



	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(ViNsoPost ent) {
		if (ent == this) return;
		this.F_Eval_01              = ent.F_Eval_01;
		this.F_Eval_02              = ent.F_Eval_02;
		this.F_Eval_03              = ent.F_Eval_03;
		this.F_Eval_04              = ent.F_Eval_04;
		this.F_Eval_05              = ent.F_Eval_05;

		this.I_Type                 = ent.I_Type;
		this.I_Status               = ent.I_Status;
		this.T_Comment              = ent.T_Comment;

		this.D_Date_Add             = ent.D_Date_Add;
		this.D_Date_Mod             = ent.D_Date_Mod;

		this.I_Parent               = ent.I_Parent;
		this.I_Entity_Type          = ent.I_Entity_Type;
		this.I_Entity_ID            = ent.I_Entity_ID;
		this.T_Entity_Title         = ent.T_Entity_Title;

		this.I_Aut_User             = ent.I_Aut_User;

		this.I_Type_01            	= ent.I_Type_01;
		this.I_Type_02            	= ent.I_Type_02;


		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;

		ok = (I_ID == ((ViNsoPost)o).I_ID);
		if (!ok) return ok;


		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}

	//-----------------------------------------------------------------------
	//-------------------------------------------------------------
	private static final String SQL_REQ_LIST_SELECT		= "SELECT"
				+   " a." 			+ TaNsoPost.COL_I_ID						+ " AS " + COL_I_ID 			+ "," 
				+	"COALESCE(d."	+ TaTpyDocument.COL_T_INFO_03 				+ ", d." + TaTpyDocument.COL_T_INFO_04 	+") AS " + COL_T_AVATAR + ","
				+	"d."	        + TaTpyDocument.COL_I_TYPE_02 				+ " AS " + COL_T_TYPE_AVATAR    + ","
				+   " a." 			+ TaNsoPost.COL_T_REF						+ " AS " + COL_T_REF 			+ ","
				+   " a." 			+ TaNsoPost.COL_T_TITLE						+ " AS " + COL_T_TITLE 			+ ","
				+   " a." 			+ TaNsoPost.COL_T_CONTENT_01				+ " AS " + COL_T_CONTENT_01		+ ","
				//				+   " NULL" 													+ " AS " + COL_T_CONTENT_02 	+ ","
				//				+   " NULL" 													+ " AS " + COL_T_CONTENT_03 	+ ","
				//				+   " NULL" 													+ " AS " + COL_T_CONTENT_04 	+ ","
				//				+   " NULL" 													+ " AS " + COL_T_CONTENT_05 	+ ","
				+   " a." 			+ TaNsoPost.COL_D_DATE_MOD					+ " AS " + COL_D_DATE_MOD 		+ ","
				+   " a." 			+ TaNsoPost.COL_D_DATE_NEW					+ " AS " + COL_D_DATE_NEW		+ ","
				+   " a." 			+ TaNsoPost.COL_I_TYPE_01					+ " AS " + COL_I_TYPE_01 			+ ","
				+   " a." 			+ TaNsoPost.COL_I_STATUS					+ " AS " + COL_I_STATUS 		+ ","
				+   " a." 		    + TaNsoPost.COL_T_COMMENT					+ " AS " + COL_T_COMMENT 		+ ","

				+   " a." 			+ TaNsoPost.COL_T_PROPERTY_04				+ " AS " +COL_T_PROPERTY_04 	+ ","
				
				+   " a." 		    + TaNsoPost.COL_I_TYPE_01					+ " AS " + COL_I_TYPE_02 		+ ","
				+   " a." 		    + TaNsoPost.COL_I_TYPE_02					+ " AS " + COL_I_TYPE_03 		+ ","

				+   " NULL" 													+ " AS " + COL_I_NB_RESP 		+ ","
				+   " NULL" 													+ " AS " + COL_I_PARENT 		+ ","
				+   " a." 			+ TaNsoPost.COL_I_ENTITY_TYPE				+ " AS " + COL_I_ENTITY_TYPE 	+ ","
				+   " a." 			+ TaNsoPost.COL_I_ENTITY_ID					+ " AS " + COL_I_ENTITY_ID 		+ ","
				+   " a." 			+ TaNsoPost.COL_I_AUT_USER_MOD					+ " AS " + COL_I_AUT_USER_NEW 		+ ","

				//				+   " p." 			+ ViNsoPost.COL_T_ENTITY_TITLE				+ " AS " + COL_T_ENTITY_TITLE 	+ ","
				+   " NULL" 													+ " AS " + COL_T_ENTITY_TITLE 		+ ","

				+	" a."			+ TaNsoPost.COL_F_EVAL_01					+ " AS " + COL_F_EVAL_01		+ ","
				+	" a."			+ TaNsoPost.COL_F_EVAL_02					+ " AS " + COL_F_EVAL_02		+ ","
				+	" a."			+ TaNsoPost.COL_F_EVAL_03					+ " AS " + COL_F_EVAL_03		+ ","
				+	" a."			+ TaNsoPost.COL_F_EVAL_04					+ " AS " + COL_F_EVAL_04		+ ","
				+	" a."			+ TaNsoPost.COL_F_EVAL_05					+ " AS " + COL_F_EVAL_05		;//+ ","

	//				+	"COALESCE(d."	+ TaTpyDocument.COL_T_INFO_03 				+ ", d." + TaTpyDocument.COL_T_INFO_04 	+") AS " + COL_T_AVATAR;


	//-----------------------------------------------------------------------

	private static final String SQL_REQ_LIST_SELECT_COUNT 	= "SELECT count(*)";


	private static final String SQL_REQ_LIST_FROM			= " FROM "		+ DefDBExt.TA_NSO_POST	+ " a";

	private static final String SQL_REQ_LIST_JOIN			= " LEFT JOIN "+ DefDBExt.TA_TPY_DOCUMENT	+ " d"	
			+	" ON (a."		+ TaNsoPost.COL_I_ID						+ " = d."	+ TaTpyDocument.COL_I_ENTITY_ID
			+	" AND d."		+ TaTpyDocument.COL_I_ENTITY_TYPE 			+ " = " 	+ DefDBExt.ID_TA_NSO_POST
			+	" AND d."		+ TaTpyDocument.COL_I_PRIORITY 			    + " = 1 "
			+	" AND (d."		+ TaTpyDocument.ATT_I_TYPE_02 				+ " = " 	+ TaTpyDocument.TYPE_02_FILE_IMG
			+	" OR d."		+ TaTpyDocument.ATT_I_TYPE_02 				+ " = " 	+ TaTpyDocument.TYPE_02_FILE_VIDEO			+"))";	
	
	private	static final String SQL_REQ_LIST_JOIN_CAT		= " INNER JOIN " 	+ DefDBExt.TA_TPY_CATEGORY_ENTITY 		+ " cat"
			+ " ON  cat." 		+ TaTpyCategoryEntity.COL_I_ENTITY_TYPE + " = "	+ DefDBExt.ID_TA_NSO_POST			
			+ " AND cat."		+ TaTpyCategoryEntity.COL_I_ID 			+ " = (Select "		
																		+ TaTpyCategoryEntity.COL_I_ID 		+ " From "	+  DefDBExt.TA_TPY_CATEGORY_ENTITY
																		+ " tmp where tmp."					+ TaTpyCategoryEntity.COL_I_ENTITY_ID + " = a." + TaNsoPost	.COL_I_ID		
																		+ " %s "
																		+ " limit 1)";
	//	private static final String SQL_REQ_LIST_JOIN_AREA  = "LEFT JOIN " 		+ DBConfig.TA_NSO_AREA 		+ " p"
	//			+	" ON (a."		+ TaNsoPost.COL_I_ENTITY_ID		+ " = p."	+ TaNsoArea.COL_I_ID
	//			+	" AND a."		+ TaNsoPost.COL_I_ENTITY_TYPE	+ " = "		+ DBConfig.ID_TA_NSO_AREA	+")";
	//
	//	private static final String SQL_REQ_LIST_JOIN_OFFER  = "LEFT JOIN " 	+ DBConfig.TA_NSO_OFFER 	+ " p"
	//			+	" ON (a."		+ TaNsoPost.COL_I_ENTITY_ID		+ " = p."	+ TaNsoOffer.COL_I_ID
	//			+	" AND a."		+ TaNsoPost.COL_I_ENTITY_TYPE	+ " = "		+ DBConfig.ID_TA_NSO_OFFER	+")";
	//
	//	private static final String SQL_REQ_LIST_JOIN_PLAN  = "LEFT JOIN " 		+ DBConfig.TA_NSO_PLAN 		+ " p"
	//			+	" ON (a."		+ TaNsoPost.COL_I_ENTITY_ID		+ " = p."	+ TaNsoPlan.COL_I_ID
	//			+	" AND a."		+ TaNsoPost.COL_I_ENTITY_TYPE	+ " = "		+ DBConfig.ID_TA_NSO_PLAN	+")";


	private static final String SQL_REQ_LIST_COUNT 	= "SELECT count(DISTINCT(a."+ TaNsoPost.COL_I_ID		+"))";

	private static final String SQL_REQ_WHERE 		= " WHERE true"	;	

	private static final String SQL_REQ_WHERE_IDS 	= " a."			+TaNsoPost.COL_I_ID 		+ " IN (%s)";

	private static final String SQL_REQ_WHERE_TYPE 	= " a."			+TaNsoPost.COL_I_TYPE_01 	+ " = %d";

	private static final String SQL_REQ_WHERE_TYPE_01 = " a."		+TaNsoPost.COL_I_TYPE_01 	+ " IN (%s)";
	
	private static final String SQL_REQ_WHERE_TYPE_02 	= " a."		+TaNsoPost.COL_I_TYPE_02 	+ " IN (%s)";

	private static final String SQL_REQ_WHERE_STAT 	= " a."			+TaNsoPost.COL_I_STATUS 	+ " = %d";
	
	private static final String SQL_REQ_WHERE_ENTID 	= " a."		+TaNsoPost.ATT_I_ENTITY_ID 	+ " = %d";
	
	private static final String SQL_REQ_WHERE_CAT 			=  " AND cat."		+ TaTpyCategoryEntity.COL_I_TPY_CATEGORY	+ " in %s "	;
	
	private static final String SQL_REQ_WHERE_STAT_NOT_EQUAL 	= " a."			+TaNsoPost.COL_I_STATUS 	+ " != %d";

	private static final String SQL_REQ_WHERE_SEARCH= " lower(a."	+ TaNsoPost.COL_T_TITLE + ") like lower('%s')";

	private static String COL_EVAL	= "D_Eval";
	private static String COL_NOW	= "D_Now";
	private static final String SQL_REQ_WHERE_THREAD_EVALUATION	= " a."		+ TaNsoPost.ATT_I_PARENT 	+ " IS NULL"
			+ " AND a." + TaNsoPost.ATT_I_STATUS 	+ " = " 		+ TaNsoPost.POST_STATUS_VALIDATED
			+ " AND (a."+ TaNsoPost.ATT_D_DATE_MOD	+ " BETWEEN :"	+	COL_EVAL + " AND :" + COL_NOW + ")";
	//	+ " AND (a."+ TaNsoPost.ATT_D_DATE_MOD	+ " BETWEEN '%tF' AND '%tF')";


	private static final String SQL_REQ_ORDER		= " ORDER BY %s %s"	;

	private static final String SQL_REQ_ORDER_LEVEL_01		= " ORDER BY %s %s, %s %s";

	private static final String SQL_REQ_ORDER_LEVEL_02		= " ORDER BY %s %s, %s %s, %s %s";

	private static final String SQL_REQ_WHERE_USER 	= " a." + TaNsoPost.COL_I_AUT_USER_MOD + " = %d";

	private static String reqRestriction(String searchKey) {			
		String restr = "";

		restr = String.format(SQL_REQ_WHERE_SEARCH, searchKey, searchKey);

		if (restr.length()==0) return null;
		else restr = "(" + restr + ")";
		return restr;
	}

	//--------------------------------------------------------------------------

	@Override
	public String toString() {
		return 	"TaNsoPost { " +
				"I_ID:"+                      I_ID +"," + 
				"F_Eval_01:"+                 F_Eval_01 +"," + 
				"F_Eval_02:"+                 F_Eval_02 +"," + 
				"F_Eval_03:"+                 F_Eval_03 +"," + 
				"F_Eval_04:"+                 F_Eval_04 +"," + 
				"F_Eval_05:"+                 F_Eval_05 +"," + 

				"I_Type:"+                    I_Type +"," + 
				"I_Status:"+                  I_Status +"," + 

				"I_Type_01:"+                 I_Type_01 +"," + 
				"I_Type_02:"+                 I_Type_02 +"," + 

				"D_Date_Add:"+                D_Date_Add +"," + 
				"D_Date_Mod:"+                D_Date_Mod +"," + 

				"I_Parent:"+                  I_Parent +"," + 
				"I_Entity_Type:"+             I_Entity_Type +"," + 
				"I_Entity_ID:"+               I_Entity_ID +"," + 

				"I_Aut_User:"+                I_Aut_User +"," + 
				"}";

	}

	//=====================================================================================================
	public void doBuildDocuments(boolean forced) throws Exception{
		if (!forced && (this.O_Documents!=null)){
			return;
		}

		O_Documents = TaTpyDocument.DAO.reqList(
				Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_TYPE	, DefDBExt.ID_TA_NSO_POST), 
				Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_ID	, I_ID)
				);		
	}

	//=====================================================================================================

	private void doBuildEntity(boolean forced) throws Exception{
		if (!forced){
			return;
		}

		switch (this.I_Entity_Type) {
//		case DBConfig.ID_TA_NSO_AREA: {
//			TaNsoArea a = TaNsoArea.DAO.reqEntityByRef(this.I_Entity_ID);
//			if(a == null)	return;
//			T_Entity_Title = (String) a.req(TaNsoArea.ATT_T_TITLE);
//
//			break;
//		}

//		case DBConfig.ID_TA_NSO_OFFER: {
//			TaNsoOffer o = TaNsoOffer.DAO.reqEntityByRef(this.I_Entity_ID);
//			if(o == null)	return;
//			T_Entity_Title = (String) o.req(TaNsoOffer.ATT_T_TITLE);
//
//			break;
//		}

//		case DBConfig.ID_TA_NSO_PLAN: {
//			TaNsoPlan p = TaNsoPlan.DAO.reqEntityByRef(this.I_Entity_ID);
//			if(p == null)	return;
//			T_Entity_Title = (String) p.req(TaNsoPlan.ATT_T_TITLE);
//
//			break;
//		}
		

		default:
			break;
		}

	}


	public void doCheckVal(int nbEval) {
		if (F_Eval_05!=null) return;

		if (this.F_Eval_01==null || this.F_Eval_01<EVAL_MIN) this.F_Eval_01 = EVAL_MIN;
		if (this.F_Eval_01>EVAL_MAX) this.F_Eval_01 = EVAL_MAX;

		if (this.F_Eval_02==null || this.F_Eval_02<EVAL_MIN) this.F_Eval_02 = EVAL_MIN;
		if (this.F_Eval_02>EVAL_MAX) this.F_Eval_02 = EVAL_MAX;

		if (this.F_Eval_03==null || this.F_Eval_03<EVAL_MIN) this.F_Eval_03 = EVAL_MIN;
		if (this.F_Eval_03>EVAL_MAX) this.F_Eval_03 = EVAL_MAX;

		if (this.F_Eval_04==null || this.F_Eval_04<EVAL_MIN) this.F_Eval_04 = EVAL_MIN;
		if (this.F_Eval_04>EVAL_MAX) this.F_Eval_04 = EVAL_MAX;

		//		if (this.F_Eval_05==null || this.F_Eval_05<EVAL_MIN) this.F_Eval_05 = EVAL_MIN;
		//		if (this.F_Eval_05>EVAL_MAX) this.F_Eval_05 = EVAL_MAX;

		this.F_Eval_01              = Math.ceil(this.F_Eval_01);
		this.F_Eval_02              = Math.ceil(this.F_Eval_02);
		this.F_Eval_03              = Math.ceil(this.F_Eval_03);
		this.F_Eval_04              = Math.ceil(this.F_Eval_04);
		//		this.F_Eval_05              = Math.ceil(this.F_Eval_05);

		switch(nbEval) {
		case 1: F_Eval_05 = F_Eval_01;									break;
		case 2: F_Eval_05 = F_Eval_01+F_Eval_02;						break;
		case 3: F_Eval_05 = F_Eval_01+F_Eval_02+F_Eval_03;				break;
		case 4: F_Eval_05 = F_Eval_01+F_Eval_02+F_Eval_03+F_Eval_04;	break;
		}	

		//		F_Eval_05 = Math.ceil(F_Eval_05/nbEval);
		F_Eval_05 = F_Eval_05/nbEval;

		if (F_Eval_05>EVAL_MAX) F_Eval_05= EVAL_MAX;
		if (F_Eval_05<EVAL_MIN) F_Eval_05= EVAL_MIN;
	}

	public double reqEval(int index) {

		switch(index) {
		case 0: return F_Eval_01!=null? F_Eval_01 : EVAL_MIN;
		case 1: return F_Eval_02!=null? F_Eval_02 : EVAL_MIN;
		case 2: return F_Eval_03!=null? F_Eval_03 : EVAL_MIN;
		case 3: return F_Eval_04!=null? F_Eval_04 : EVAL_MIN;
		case 4: return F_Eval_05!=null? F_Eval_05 : EVAL_MIN;
		}
		return 0;
	}

	//---------------------------------------------------------------------------------
	public static List<ViNsoPost> reqNsoPostLstByUserId(Integer userId) throws Exception {
		List<ViNsoPost> 	list 	= new ArrayList<ViNsoPost>();		
		String sql = reqNsoPostLstByUserIdSql(userId);		
		list = ViNsoPost.DAO.reqList(sql);

		for (ViNsoPost p : list) {
			p.doBuildEntity(true);
		}

		return list;
	}

	//---------------------------------------------------------------------------------
	public static List<ViNsoPost> reqNsoPostLstByUserId(Integer userId, Integer begin, Integer nbRes) throws Exception {
		List<ViNsoPost> 	list 	= new ArrayList<ViNsoPost>();		
		String sql = reqNsoPostLstByUserIdSql(userId);		
		list = ViNsoPost.DAO.reqList(begin, nbRes, sql);

		for (ViNsoPost p : list) {
			p.doBuildEntity(true);
		}

		return list;
	}

	//-----------------------------------------------------------------------------------
	private static String reqNsoPostLstByUserIdSql(Integer userId) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT;

		sql += SQL_REQ_LIST_FROM;

		//		sql += SQL_REQ_LIST_JOIN;

		sql += SQL_REQ_WHERE;

		sql += " AND " + String.format(SQL_REQ_WHERE_USER, userId);
		sql += " AND " + String.format(SQL_REQ_WHERE_STAT_NOT_EQUAL, TaNsoPost.POST_STATUS_DELETED);


		String orderCol = ViNsoPost.ATT_D_DATE_MOD;
		String orderDir	= " DESC";
		sql += String.format(SQL_REQ_ORDER, orderCol, orderDir);

		return sql;
	}

	//====================================================================	
	public static Integer reqNsoPostLstByUserCount(Integer userId) throws Exception {
		String sql = reqNsoPostLstGridSqlCount( userId);			
		Integer count = ViNsoPost.DAO.reqCount(sql).intValue();

		return count;
	}

	//----------------------------------------------------------------------
	private static String reqNsoPostLstGridSqlCount(Integer userId) throws Exception {
		String sql	= SQL_REQ_LIST_COUNT;

		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN;

		sql += SQL_REQ_WHERE;

		sql += " AND " + String.format(SQL_REQ_WHERE_USER, userId);
		sql += " AND " + String.format(SQL_REQ_WHERE_STAT_NOT_EQUAL, TaNsoPost.POST_STATUS_DELETED);

		return sql;
	}

	//----------------------------------------------------------------------
	public static List<ViNsoPost> reqNsoPostLstThreadEvaluation(Date eval, Date now) throws Exception {
		List<ViNsoPost> 	list 	= new ArrayList<ViNsoPost>();		
		String sql = reqNsoPostLstGridSqlThreadEvaluation();		
		list = ViNsoPost.DAO.reqList(sql, COL_EVAL, eval, COL_NOW, now);

		return list;
	}

	//----------------------------------------------------------------------
	private static String reqNsoPostLstGridSqlThreadEvaluation() throws Exception {
		String sql	= SQL_REQ_LIST_SELECT;

		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_WHERE;

		sql += " AND " + SQL_REQ_WHERE_THREAD_EVALUATION;

		return sql;
	}

	public Integer reqID() {
		return this.I_ID;
	}

	public static List<ViNsoPost> reqLstPost(Integer begin, Integer nbRes, Integer type, String multiType,  Integer stat, Integer style,  Set<Integer> catIds, String langId, String searchkey) throws Exception {
		List<ViNsoPost> 	list 	= new ArrayList<ViNsoPost>();		
		String sql = reqNsoPostSql(type, multiType, stat, style, catIds, langId, searchkey);		
		list = ViNsoPost.DAO.reqList(begin, nbRes, sql);

//		for(ViNsoPost post : list){
//			post.doBuildDocuments(true);
//		}

		return list;
	}

	//----------------------------------------------------------------
	private static String reqNsoPostSql(Integer type, String multiType, Integer stat, Integer style, Set<Integer> catIds, String langId, String searchkey) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT;

		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN;
		
		String catCond = "";		
		if(catIds != null) {
			String sCatIds = catIds.toString();
			sCatIds = sCatIds.replace("[", "(").replace("]", ")"); 
			catCond = String.format(SQL_REQ_WHERE_CAT, sCatIds);
			sql = sql + String.format(SQL_REQ_LIST_JOIN_CAT, catCond);
		}
		
		sql += SQL_REQ_WHERE;

		if(type != null && type != 0)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE, type);
		else if(multiType!=null && !multiType.isEmpty() && !multiType.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE_01, multiType);

		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT, stat);

		if(langId != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE_02, langId);
		
		//searchKey = %
		String restriction	= reqRestriction(searchkey);

		if(restriction != null) {
			sql += " AND " + restriction;
		}

		if(style != null && style != 0) {
			String orderCol01 	= null;
			String orderCol02 	= null;
			String orderCol03 	= null;
			String orderDir		= "DESC";

			switch(style){
			case 1: {									//latest
				orderCol01 = COL_D_DATE_MOD; 	
				sql += String.format(SQL_REQ_ORDER, orderCol01, orderDir);
				break;		
			}
			case 2: {									//hot
				orderCol01 = COL_F_EVAL_05;
				sql += String.format(SQL_REQ_ORDER, orderCol01, orderDir);
				break;
			}
			case 3: {									//1st featured, 2nd latest
				orderCol01 = COL_I_TYPE_01;
				orderCol02 = COL_D_DATE_NEW;
				sql += String.format(SQL_REQ_ORDER_LEVEL_01, orderCol01, orderDir, orderCol02, orderDir);
				break;
			}
			case 4: {									//1st featured, 2nd hot
				orderCol01 = COL_I_TYPE_01;
				orderCol02 = COL_F_EVAL_05;
				sql += String.format(SQL_REQ_ORDER_LEVEL_01, orderCol01, orderDir, orderCol02, orderDir);
				break;
			}
			case 5: {									//1st featured, 2nd hot, 3nd latest
				orderCol01 = COL_I_TYPE_01;
				orderCol02 = COL_F_EVAL_05;
				orderCol03 = COL_D_DATE_NEW;
				sql += String.format(SQL_REQ_ORDER_LEVEL_02, orderCol01, orderDir, orderCol02, orderDir, orderCol03, orderDir);
				break;
			}
			}

			//				if (orderCol01!=null){
			//					sql += String.format(SQL_REQ_ORDER, orderCol01, orderDir);
			//				}
		}

		return sql;
	}

	public static Integer reqLstPostCount(Integer type, String multiType, Integer stat, Integer style, Set<Integer> catIds, String langId, String searchkey) throws Exception {
		String sql = reqLstPostCountSqlCount(type, multiType, stat, style, catIds, langId, searchkey);			
		Integer count = ViNsoPost.DAO.reqCount(sql).intValue();

		return count;
	}

	private static String reqLstPostCountSqlCount(Integer type, String multiType, Integer stat, Integer style, Set<Integer> catIds, String langId, String searchkey) throws Exception {
		String sql	= SQL_REQ_LIST_COUNT;

		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN;
		
		String catCond = "";		
		if(catIds != null) {
			String sCatIds = catIds.toString();
			sCatIds = sCatIds.replace("[", "(").replace("]", ")"); 
			catCond = String.format(SQL_REQ_WHERE_CAT, sCatIds);
			sql = sql + String.format(SQL_REQ_LIST_JOIN_CAT, catCond);
		}

		sql += SQL_REQ_WHERE;

		if(type != null && type != 0)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE, type);
		else if(multiType!=null && !multiType.isEmpty() && !multiType.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE_01, multiType);

		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT, stat);
		
		if(langId != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE_02, langId);

		//searchKey = %
		String restriction	= reqRestriction(searchkey);

		if(restriction != null) {
			sql += " AND " + restriction;
		}

		return sql;
	}

//	public static void doBuildTranslations(List<ViNsoPost> list) throws Exception{
//		TranslationTool.doBuildTpyTranslations(list, DBConfig.ID_TA_NSO_POST, ViNsoPost.ATT_O_TRANSLATIONS, false);
//	}
}
