package com.hnv.db.nso.vi;
//Created by H&V Entreprise Tue Dec 27 12:03:55 CET 2016

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.criterion.Order;

import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolString;
import com.hnv.data.json.JSONObject;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.db.nso.TaNsoOffer;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.db.tpy.TaTpyTranslation;
import com.hnv.def.DefDBExt;
import com.hnv.index.tool.GeoPoint;	

@Entity
@Table(name = DefDBExt.TA_NSO_OFFER )
public class ViNsoOfferMap extends EntityAbstract<ViNsoOfferMap> {

	private static final long serialVersionUID = 1L;

	//---------------------------List of Column-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_T_TITLE                           =	"T_Title";
	public static final String	COL_I_STATUS_01                       =	"I_Status_01";
	public static final String	COL_I_STATUS_02                       =	"I_Status_02";
	public static final String	COL_I_PARENT                          =	"I_Parent";
	public static final String	COL_T_CODE_01                         =	"T_Code_01";
	public static final String	COL_T_CODE_02                         =	"T_Code_02";
	public static final String	COL_I_TYPE_01                         =	"I_Type_01";
	public static final String	COL_I_TYPE_02                         =	"I_Type_02";
	public static final String	COL_I_TYPE_03                         =	"I_Type_03";
	public static final String	COL_T_CONTENT_01                      =	"T_Content_01";
	public static final String	COL_T_CONTENT_02                      =	"T_Content_02";
	public static final String	COL_T_CONTENT_03                      =	"T_Content_03";
	public static final String	COL_T_CONTENT_04                      =	"T_Content_04";
	public static final String	COL_T_CONTENT_05                      =	"T_Content_05";
	public static final String	COL_T_CONTENT_06                      =	"T_Content_06";
	public static final String	COL_T_CONTENT_07                      =	"T_Content_07";
	public static final String	COL_T_CONTENT_08                      =	"T_Content_08";
	public static final String	COL_T_CONTENT_09                      =	"T_Content_09";
	public static final String	COL_T_CONTENT_10                      =	"T_Content_10";
	public static final String	COL_T_INFO_01                         =	"T_Info_01";
	public static final String	COL_T_INFO_02                         =	"T_Info_02";
	public static final String	COL_T_INFO_03                         =	"T_Info_03";
	public static final String	COL_T_INFO_04                         =	"T_Info_04";
	public static final String	COL_T_INFO_05                         =	"T_Info_05";
	public static final String	COL_D_DATE_01                         =	"D_Date_01";
	public static final String	COL_D_DATE_02                         =	"D_Date_02";
	public static final String	COL_D_DATE_03                         =	"D_Date_03";
	public static final String	COL_D_DATE_04                         =	"D_Date_04";
	public static final String	COL_D_DATE_05                         =	"D_Date_05";
	public static final String	COL_I_AUT_USER_01                     =	"I_Aut_User_01";
	public static final String	COL_I_AUT_USER_02                     =	"I_Aut_User_02";
	public static final String	COL_I_AUT_USER_03                     =	"I_Aut_User_03";
	public static final String	COL_I_VAL_01                          =	"I_Val_01";
	public static final String	COL_I_VAL_02                          =	"I_Val_02";
	public static final String	COL_I_VAL_03                          =	"I_Val_03";
	public static final String	COL_I_VAL_04                          =	"I_Val_04";
	public static final String	COL_I_VAL_05                          =	"I_Val_05";
	public static final String	COL_F_VAL_01                          =	"F_Val_01";
	public static final String	COL_F_VAL_02                          =	"F_Val_02";
	public static final String	COL_F_VAL_03                          =	"F_Val_03";
	public static final String	COL_F_VAL_04                          =	"F_Val_04";
	public static final String	COL_F_VAL_05                          =	"F_Val_05";


	public static final String	COL_T_AVATAR                          =	"T_Avatar";
	public static final String	COL_T_AVATAR_MAN                      =	"T_Avatar_Man";
	
	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_T_TITLE                           =	"T_Title";
	public static final String	ATT_I_STATUS_01                       =	"I_Status_01";
	public static final String	ATT_I_STATUS_02                       =	"I_Status_02";
	public static final String	ATT_I_PARENT                          =	"I_Parent";
	public static final String	ATT_T_CODE_01                         =	"T_Code_01";
	public static final String	ATT_T_CODE_02                         =	"T_Code_02";
	public static final String	ATT_I_TYPE_01                         =	"I_Type_01";
	public static final String	ATT_I_TYPE_02                         =	"I_Type_02";
	public static final String	ATT_I_TYPE_03                         =	"I_Type_03";
	public static final String	ATT_T_CONTENT_01                      =	"T_Content_01";
	public static final String	ATT_T_CONTENT_02                      =	"T_Content_02";
	public static final String	ATT_T_CONTENT_03                      =	"T_Content_03";
	public static final String	ATT_T_CONTENT_04                      =	"T_Content_04";
	public static final String	ATT_T_CONTENT_05                      =	"T_Content_05";
	public static final String	ATT_T_CONTENT_06                      =	"T_Content_06";
	public static final String	ATT_T_CONTENT_07                      =	"T_Content_07";
	public static final String	ATT_T_CONTENT_08                      =	"T_Content_08";
	public static final String	ATT_T_CONTENT_09                      =	"T_Content_09";
	public static final String	ATT_T_CONTENT_10                      =	"T_Content_10";
	public static final String	ATT_T_INFO_01                         =	"T_Info_01";
	public static final String	ATT_T_INFO_02                         =	"T_Info_02";
	public static final String	ATT_T_INFO_03                         =	"T_Info_03";
	public static final String	ATT_T_INFO_04                         =	"T_Info_04";
	public static final String	ATT_T_INFO_05                         =	"T_Info_05";
	public static final String	ATT_D_DATE_01                         =	"D_Date_01";
	public static final String	ATT_D_DATE_02                         =	"D_Date_02";
	public static final String	ATT_D_DATE_03                         =	"D_Date_03";
	public static final String	ATT_D_DATE_04                         =	"D_Date_04";
	public static final String	ATT_D_DATE_05                         =	"D_Date_05";
	public static final String	ATT_I_AUT_USER_01                     =	"I_Aut_User_01";
	public static final String	ATT_I_AUT_USER_02                     =	"I_Aut_User_02";
	public static final String	ATT_I_AUT_USER_03                     =	"I_Aut_User_03";
	public static final String	ATT_I_VAL_01                          =	"I_Val_01";
	public static final String	ATT_I_VAL_02                          =	"I_Val_02";
	public static final String	ATT_I_VAL_03                          =	"I_Val_03";
	public static final String	ATT_I_VAL_04                          =	"I_Val_04";
	public static final String	ATT_I_VAL_05                          =	"I_Val_05";
	public static final String	ATT_F_VAL_01                          =	"F_Val_01";
	public static final String	ATT_F_VAL_02                          =	"F_Val_02";
	public static final String	ATT_F_VAL_03                          =	"F_Val_03";
	public static final String	ATT_F_VAL_04                          =	"F_Val_04";
	public static final String	ATT_F_VAL_05                          =	"F_Val_05";

	public static final String	ATT_T_AVATAR                      	  =	"T_Avatar";
	
	public static final String	ATT_O_DOCUMENTS                       =	"O_Documents";
	public static final String	ATT_O_TRANSLATIONS                	  =	"O_Translations";
	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, false, false, false, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static	final boolean				API_CACHE 	= false;
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<ViNsoOfferMap> 	DAO;
		
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	static{
		DAO = new EntityDAO<ViNsoOfferMap>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), ViNsoOfferMap.class,RIGHTS);
	
	}
	
	//-----------------------Attributs-------------------------
	@Id
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
	
	@Column(name=COL_T_CODE_01, nullable = true)
	private	String          T_Code_01;
        
	@Column(name=COL_T_TITLE, nullable = false)
	private	String          T_Title;
      
	@Column(name=COL_T_CONTENT_01, nullable = true)
	private	String          T_Content_01;
    
	@Column(name=COL_T_CONTENT_05, nullable = true)
	private	String          T_Content_05;
 
 
	@Column(name=COL_T_INFO_02, nullable = true)
	private	String          T_Info_02;
	   
	@Column(name=COL_I_VAL_03, nullable = true)
	private	Double          I_Val_03;
	   
	@Column(name=COL_I_VAL_04, nullable = true)
	private	Double          I_Val_04;;
	   
	@Column(name=COL_I_VAL_05, nullable = true)
	private	Double          I_Val_05;
        
	@Column(name=COL_F_VAL_01, nullable = true)
	private	Double          F_Val_01;
     
	@Column(name=COL_F_VAL_02, nullable = true)
	private	Double          F_Val_02;
     
	@Column(name=COL_D_DATE_01, nullable = true)
	private	Date            D_Date_01;
 
	@Column(name=COL_D_DATE_02, nullable = true)
	private	Date            D_Date_02;
	
	@Column(name=COL_D_DATE_03, nullable = true)
	private	Date            D_Date_03;
	
	@Column(name=COL_D_DATE_04, nullable = true)
	private	Date            D_Date_04;
	
	@Column(name=COL_D_DATE_05, nullable = true)
	private	Date            D_Date_05;
	
	@Column(name=COL_I_TYPE_01, nullable = true)
	private	Integer         I_Type_01;
	
	@Column(name=COL_I_TYPE_02, nullable = true)
	private	Integer         I_Type_02;
	
	@Column(name=COL_I_TYPE_03, nullable = true)
	private	Integer         I_Type_03;
	
	@Column(name=COL_T_AVATAR, nullable = true)
	private	String          T_Avatar;
	
	@Column(name=COL_T_AVATAR_MAN, nullable = true)
	private	String          T_Avatar_Man;
	
	@Transient
	private List<TaTpyTranslation> O_Translations;
	//---------------------Constructeurs-----------------------
	private ViNsoOfferMap(){}

	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;
	}

	@Override
	public void doMergeWith(ViNsoOfferMap ent) {
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		ok = (I_ID == ((ViNsoOfferMap)o).I_ID);
		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;
	}
	
	//-------------------------------------------------------------
	private static final String SQL_REQ_LIST_SELECT 		= "SELECT"
			+   " a." 			+ TaNsoOffer.COL_I_ID						+ " AS " +COL_I_ID 			+ ","
			
			+	"COALESCE(d."	+ TaTpyDocument.COL_T_INFO_05 				+ ", d." + TaTpyDocument.COL_T_INFO_03 	+") AS " +COL_T_AVATAR	+ ","
			
			+   " a." 			+ TaNsoOffer.COL_T_CODE_01					+ " AS " +COL_T_CODE_01 	+ ","
			+   " a." 			+ TaNsoOffer.COL_T_TITLE					+ " AS " +COL_T_TITLE 		+ ","
			+   " a." 			+ TaNsoOffer.COL_T_CONTENT_01				+ " AS " +COL_T_CONTENT_01	+ ","
			+   " a." 			+ TaNsoOffer.COL_T_CONTENT_05				+ " AS " +COL_T_CONTENT_05	+ ","
			
			+   " a." 			+ TaNsoOffer.COL_F_VAL_01					+ " AS " +COL_F_VAL_01 		+ ","
			+   " a." 			+ TaNsoOffer.COL_F_VAL_02					+ " AS " +COL_F_VAL_02 		+ ","
			
			+   " a." 			+ TaNsoOffer.COL_D_DATE_01					+ " AS " +COL_D_DATE_01 	+ ","
			+   " a." 			+ TaNsoOffer.COL_D_DATE_03					+ " AS " +COL_D_DATE_03 	+ ","
			+   " a." 			+ TaNsoOffer.COL_D_DATE_04					+ " AS " +COL_D_DATE_04 	+ ","
			
			+   " a." 			+ TaNsoOffer.COL_T_INFO_01					+ " AS " +COL_T_INFO_01 	+ ","									
			+   " a." 			+ TaNsoOffer.COL_T_INFO_02					+ " AS " +COL_T_INFO_02	 	+ ","	//address									
			+   " a." 			+ TaNsoOffer.COL_T_INFO_03					+ " AS " +COL_T_INFO_03 	+ ","
			+   " a." 			+ TaNsoOffer.COL_T_INFO_04					+ " AS " +COL_T_INFO_04 	+ ","
			
			+   " a." 			+ TaNsoOffer.COL_I_TYPE_01					+ " AS " +COL_I_TYPE_01 	+ ","
			+   " a." 			+ TaNsoOffer.COL_I_TYPE_02					+ " AS " +COL_I_TYPE_02 	+ ","

//			+   " NULL"														+ " AS " +COL_I_PER_MANAGER	+ ","
			+   " NULL"														+ " AS " +COL_T_AVATAR_MAN 	;
			
	
	private static final String SQL_REQ_LIST_SELECT_MAP 		= "SELECT"
			+   " a." 			+ TaNsoOffer.COL_I_ID						+ " AS " +COL_I_ID 			+ ","
			
			+	"COALESCE(d."	+ TaTpyDocument.COL_T_INFO_05 				+ ", d." + TaTpyDocument.COL_T_INFO_03 	+") AS " +COL_T_AVATAR	+ ","
			
			+   " a." 			+ TaNsoOffer.COL_T_CODE_01					+ " AS " +COL_T_CODE_01 	+ ","
			+   " a." 			+ TaNsoOffer.COL_T_TITLE					+ " AS " +COL_T_TITLE 		+ ","
			+   " a." 			+ TaNsoOffer.COL_T_CONTENT_01				+ " AS " +COL_T_CONTENT_01	+ ","
			+   " a." 			+ TaNsoOffer.COL_T_CONTENT_05				+ " AS " +COL_T_CONTENT_05	+ ","
			
			+   " a." 			+ TaNsoOffer.COL_F_VAL_01					+ " AS " +COL_F_VAL_01 		+ ","
			+   " a." 			+ TaNsoOffer.COL_F_VAL_02					+ " AS " +COL_F_VAL_02 		+ ","
			+   " a." 			+ TaNsoOffer.COL_F_VAL_03					+ " AS " +COL_F_VAL_03 		+ ","
			
			+   " a." 			+ TaNsoOffer.COL_D_DATE_01					+ " AS " +COL_D_DATE_01 	+ ","
			+   " a." 			+ TaNsoOffer.COL_D_DATE_02					+ " AS " +COL_D_DATE_02 	+ ","
			+   " a." 			+ TaNsoOffer.COL_D_DATE_03					+ " AS " +COL_D_DATE_03 	+ ","
			+   " a." 			+ TaNsoOffer.COL_D_DATE_04					+ " AS " +COL_D_DATE_04 	+ ","
			+   " a." 			+ TaNsoOffer.COL_D_DATE_05					+ " AS " +COL_D_DATE_05 	+ ","
		
			+   " a." 			+ TaNsoOffer.COL_T_INFO_01					+ " AS " +COL_T_INFO_01 	+ ","									
			+   " a." 			+ TaNsoOffer.COL_T_INFO_02					+ " AS " +COL_T_INFO_02	 	+ ","	//address									
			+   " a." 			+ TaNsoOffer.COL_T_INFO_03					+ " AS " +COL_T_INFO_03 	+ ","
			+   " a." 			+ TaNsoOffer.COL_T_INFO_04					+ " AS " +COL_T_INFO_04 	+ ","
			
			+   " a." 			+ TaNsoOffer.COL_I_VAL_03					+ " AS " +COL_I_VAL_03 		+ ","
			+   " a." 			+ TaNsoOffer.COL_I_VAL_04					+ " AS " +COL_I_VAL_04 		+ ","
			+   " a." 			+ TaNsoOffer.COL_I_VAL_05					+ " AS " +COL_I_VAL_05 		+ ","
			
			+   " a." 			+ TaNsoOffer.COL_I_TYPE_01					+ " AS " +COL_I_TYPE_01 	+ ","
			+   " a." 			+ TaNsoOffer.COL_I_TYPE_02					+ " AS " +COL_I_TYPE_02  	+ ","
			+   " a." 			+ TaNsoOffer.COL_I_TYPE_03					+ " AS " +COL_I_TYPE_03  	+ ","	
			+   " NULL"														+ " AS " +COL_T_AVATAR_MAN	;
			

//			+   " NULL"														+ " AS " +COL_I_PER_MANAGER	+ ","
//			+   " NULL"														+ " AS " +COL_T_AVATAR_MAN 	;
			
	
	
	
	private static final String SQL_REQ_LIST_SELECT_SEARCH_AUTOCOMPLETE 		= "SELECT"
			+   " a." 			+ TaNsoOffer.COL_I_ID						+ " AS " +COL_I_ID 			+ ","
			+	"NULL" 														+ " AS " +COL_T_AVATAR		+ ","
			
			+   " a." 			+ TaNsoOffer.COL_T_CODE_01					+ " AS " +COL_T_CODE_01 		+ ","
			+   " a." 			+ TaNsoOffer.COL_I_PARENT					+ " AS " +COL_I_PARENT 		+ ","
			+   " a." 			+ TaNsoOffer.COL_T_TITLE					+ " AS " +COL_T_TITLE 		+ ","
			+   " NULL"                                      				+ " AS " +COL_T_CONTENT_01	+ ","
			+   " NULL"                                      				+ " AS " +COL_T_CONTENT_05	+ ","
			
			
			+   " a." 			+ TaNsoOffer.COL_T_INFO_02					+ " AS " +COL_T_INFO_02 	+ ","//address
			+   " a." 			+ TaNsoOffer.COL_F_VAL_01					+ " AS " +COL_F_VAL_01 		+ ","
			+   " a." 			+ TaNsoOffer.COL_F_VAL_02					+ " AS " +COL_F_VAL_02 		+ ","
			
			+   " NULL"    													+ " AS " +COL_D_DATE_01 	+ ","
			+   " NULL" 													+ " AS " +COL_D_DATE_03 	+ ","
			+   " NULL" 													+ " AS " +COL_D_DATE_04 	+ ","
			+   " NULL" 													+ " AS " +COL_T_INFO_01 	+ ","
			+   " NULL"														+ " AS " +COL_T_INFO_03 	+ ","
			+   " NULL" 													+ " AS " +COL_T_INFO_04 	+ ","
			
			+   " NULL"              										+ " AS " +COL_I_TYPE_01 	+ ","
			+   " NULL"              										+ " AS " +COL_I_TYPE_02 	+ ","
			
//			+   " NULL"   													+ " AS " +COL_I_PER_MANAGER	+ ","
			+   " NULL"														+ " AS " +COL_T_AVATAR_MAN 	;
	
	
//	private static final String SQL_REQ_LIST_SELECT_COUNT 	= "SELECT count(*)";
	private static final String SQL_REQ_LIST_SELECT_COUNT 	= "SELECT count(DISTINCT(a."+ TaNsoOffer.COL_I_ID		+"))";
	
	private static final String SQL_REQ_LIST_FROM			= " FROM "		+ DefDBExt.TA_NSO_OFFER		+ " a";
			
	private static final String SQL_REQ_LIST_JOIN_CAT		= " INNER JOIN "+ DefDBExt.TA_TPY_CATEGORY_ENTITY	+ " c"	
			+	" ON (c."		+ TaTpyCategoryEntity.COL_I_ENTITY_ID		+ " = a."	+ TaNsoOffer.COL_I_ID
			+	" AND c."		+ TaTpyCategoryEntity.COL_I_ENTITY_TYPE 	+ " = " 	+ DefDBExt.ID_TA_NSO_OFFER
			+	" AND c."		+ TaTpyCategoryEntity.COL_I_TPY_CATEGORY	+ " IN (%s) "
			+	")";
	
	private static final String SQL_REQ_LIST_JOIN_DOCUMENT	= " LEFT JOIN "+ DefDBExt.TA_TPY_DOCUMENT	+ " d"	
			+	" ON (d."		+ TaTpyDocument.COL_I_ENTITY_ID				+ " = a."	+ TaNsoOffer.COL_I_ID 
			+	" AND d."		+ TaTpyDocument.COL_I_ENTITY_TYPE 			+ " = " 	+ DefDBExt.ID_TA_NSO_OFFER
			+	" AND d."		+ TaTpyDocument.COL_I_TYPE_01 				+ " = " 	+ TaTpyDocument.TYPE_01_FILE_MEDIA
			+	" AND d."		+ TaTpyDocument.COL_I_TYPE_02 				+ " = " 	+ TaTpyDocument.TYPE_02_FILE_IMG_AVATAR
			+	" AND d."		+ TaTpyDocument.COL_I_PRIORITY 				+ " = 1" 	+ ")";
			
//			+	" LEFT JOIN "	+ DefDBExt.TA_NSO_EVALUATION				+ " e"
//			+	" ON (a."		+ TaNsoOffer.COL_I_ID						+ " = e."	+ TaNsoEvaluation.COL_I_ENTITY_ID
//			+	" AND e."		+ TaNsoEvaluation.COL_I_ENTITY_TYPE			+ " = "		+ DefDBExt.ID_TA_NSO_OFFER						+ ")";		
	
//	private static final String SQL_REQ_LIST_JOIN_PROP_QUANT	= " INNER JOIN "+ com.hnv.tool.db.tpy_cfg.config.DefDBExt.TA_TPY_PROPERTY_QUANT	+ " q"	
//			+	" ON q."		+ TaTpyPropertyQuant.COL_I_ID				+ " = "	
//			+	" ( SELECT  q2." 	+ TaTpyPropertyQuant.COL_I_ID
//			+ 	" FROM " 		+ com.hnv.tool.db.tpy_cfg.config.DefDBExt.TA_TPY_PROPERTY_QUANT + " q2"
//			+	" WHERE a."		+ TaNsoOffer.COL_I_ID						+ " = q2."	+ TaTpyPropertyQuant.COL_I_ENTITY_ID
//			+	" AND q2."		+ TaTpyPropertyQuant.COL_I_ENTITY_TYPE 		+ " = " 	+ DefDBExt.ID_TA_NSO_OFFER   
//			+	" AND q2."		+ TaTpyPropertyQuant.COL_I_TYPE 			+ " in " 	+ "(%s)" 
//			+	" AND q2."		+ TaTpyPropertyQuant.COL_F_PROP_01 			+ " >0 " 	
//			+   " LIMIT 1) ";
			
	
	
	private static final String SQL_REQ_LIST_COUNT 	= "SELECT count(DISTINCT(a."+ TaNsoOffer.COL_I_ID		+"))";
	
	private static final String SQL_REQ_WHERE 		= " WHERE true"	;	

	private static final String SQL_REQ_WHERE_IDS 	= " a."			+TaNsoOffer.COL_I_ID 		+ " IN (%s)";
	
	private static final String SQL_REQ_WHERE_TYPE 	= " a."			+TaNsoOffer.COL_I_TYPE_01 	+ " = %d";
		
	private static final String SQL_REQ_WHERE_TYPES = " a."			+TaNsoOffer.COL_I_TYPE_01 	+ " IN (%s)";
	
	private static final String SQL_REQ_WHERE_TYPE_02 	= " a."		+TaNsoOffer.COL_I_TYPE_02 	+ " = %d";
	
	private static final String SQL_REQ_WHERE_STAT_01 	= " a."			+TaNsoOffer.COL_I_STATUS_01 	+ " = %d";
	
	private static final String SQL_REQ_WHERE_STAT_02 	= " a."			+TaNsoOffer.COL_I_STATUS_02 	+ " = %d";
	
	private static final String SQL_REQ_WHERE_BETWEEN_MON 	= " a."		+TaNsoOffer.COL_I_VAL_03 	+ " BETWEEN %d AND %d";
	
	private static final String SQL_REQ_WHERE_TYP_01 	= " a."			+TaNsoOffer.COL_I_TYPE_01 	+ " = %d";
	
	private static final String SQL_REQ_WHERE_TYP_03 	= " a."			+TaNsoOffer.COL_I_TYPE_03 	+ " = %d";
	
	private static final String SQL_REQ_WHERE_PER_MAN_ID 	= " a."		+TaNsoOffer.COL_I_PER_MANAGER 	+ " = %d";
	
	private static final String SQL_REQ_WHERE_SEARCH= " lower(a."	+ TaNsoOffer.COL_T_TITLE + ") like lower('%s') OR lower(a." + TaNsoOffer.COL_T_INFO_02 + ") like lower('%s')";
	
	private static final String SQL_REQ_WHERE_DISTANCE= "(a." 		+ TaNsoOffer.COL_F_VAL_01 + ">= %f"
														+ " AND a." 	+ TaNsoOffer.COL_F_VAL_01 + "<= %f"
														+ " AND a." 	+ TaNsoOffer.COL_F_VAL_02 + ">= %f"
														+ " AND a." 	+ TaNsoOffer.COL_F_VAL_02 + "<= %f)";
	
	private static final String SQL_REQ_ORDER_LEVEL_01		= " ORDER BY %s %s"	;
	
	private static final String SQL_REQ_ORDER_LEVEL_02		= " ORDER BY %s %s, %s %s";
	
	private static final String SQL_REQ_ORDER_LEVEL_03		= " ORDER BY %s %s, %s %s, %s %s";
	
	private static final String SQL_REQ_WHERE_USER 	= " a."+TaNsoOffer.COL_I_AUT_USER_01 + " = %d";
	
	public static final String SQL_REQ_WHERE_DATE 	= " ( a." + TaNsoOffer.COL_D_DATE_04		+ " >= '%s' " + "OR a." + TaNsoOffer.COL_D_DATE_04	+ " IS NULL )";
	
//	static final String SQL_REQ_LIST_JOIN_FAVORTIE	= " INNER JOIN " 	+ DefDBExt.TA_TPY_FAVORITE 		+" fav"
//			+	" ON  a." 		+ TaNsoOffer.COL_I_ID					+ " = fav." 	+ TaTpyFavorite.COL_I_ENTITY_ID	
//			+	" AND fav." 	+ TaTpyFavorite.COL_I_ENTITY_TYPE		+ " = " 		+ DefDBExt.ID_TA_NSO_OFFER
//			+	" AND fav." 	+ TaTpyFavorite.ATT_I_TYPE				+ " = " 		+ TaTpyFavorite.FAVORITE_TYPE_LIKE
//			+	" AND fav." 	+ TaTpyFavorite.COL_I_AUT_USER			+ " = %d" 		;
	
	private static String reqRestriction(String searchKey) {	
		if (searchKey==null) 		return null;
		if (searchKey.equals("%")) 	return null;
		String restr = "";
		
		restr = String.format(SQL_REQ_WHERE_SEARCH, searchKey, searchKey);

		if (restr.length()==0) return null;
		else restr = "(" + restr + ")";
		return restr;
	}
	
	//----------------------------------------------------------------
	private static String reqLstGridSql(Integer type, String multiType, Integer stat, Integer style, Integer companyId, Integer perManId, Integer typ02) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT;
		
		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN_DOCUMENT;
		
		sql += SQL_REQ_WHERE;
		
		if(type != null && type != 0)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE, type);
		else if(multiType!=null && !multiType.isEmpty() && !multiType.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPES, multiType);
		
		if(typ02 != null && typ02 > 0)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE_02, typ02);
		
		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat);
		
		if(perManId != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_PER_MAN_ID, perManId);
		
//		if(companyId != null)
//			sql += " AND " + String.format(SQL_REQ_WHERE_COMPANY, companyId);
		//searchKey = %
		String restriction	= reqRestriction("%");
		
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
						orderCol01 = COL_D_DATE_01; 	
						sql += String.format(SQL_REQ_ORDER_LEVEL_01, orderCol01, orderDir);
						break;		
					}
				case 2: {									//hot
						orderCol01 = COL_F_VAL_05;
						sql += String.format(SQL_REQ_ORDER_LEVEL_01, orderCol01, orderDir);
						break;
					}
				case 3: {									//1st featured, 2nd latest
						orderCol01 = COL_I_TYPE_01;
						orderCol02 = COL_D_DATE_01;
						sql += String.format(SQL_REQ_ORDER_LEVEL_02, orderCol01, orderDir, orderCol02, orderDir);
						break;
					}
				case 4: {									//1st featured, 2nd hot
						orderCol01 = COL_I_TYPE_01;
						orderCol02 = COL_F_VAL_05;
						sql += String.format(SQL_REQ_ORDER_LEVEL_02, orderCol01, orderDir, orderCol02, orderDir);
						break;
					}
				case 5: {									//1st featured, 2nd hot, 3nd latest
						orderCol01 = COL_I_TYPE_01;
						orderCol02 = COL_F_VAL_05;
						orderCol03 = COL_D_DATE_01;
						sql += String.format(SQL_REQ_ORDER_LEVEL_03, orderCol01, orderDir, orderCol02, orderDir, orderCol03, orderDir);
						break;
				}
			}

//			if (orderCol01!=null){
//				sql += String.format(SQL_REQ_ORDER, orderCol01, orderDir);
//			}
		}
		
		return sql;
	}
	
	
	public static List<ViNsoOfferMap> reqLstGrid(Integer begin, Integer nbRes, Integer type, String multiType,  Integer stat, Integer style, Integer companyId, Integer perManId, Integer typ02) throws Exception {
		List<ViNsoOfferMap> 	list 	= new ArrayList<ViNsoOfferMap>();		
		String sql = reqLstGridSql(type, multiType, stat, style, companyId, perManId, typ02);		
		list = ViNsoOfferMap.DAO.reqList(begin, nbRes, sql);
			
		return list;
	}

	//------------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------------
	private static String reqLstMapGridSql(String stat, Integer style, String searchKey, String lstCat, List<GeoPoint> lstPoint) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT;

		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN_DOCUMENT;

		sql += SQL_REQ_WHERE;

		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat);

		String restriction	= reqRestriction("%");

		if(restriction != null) {
			sql += " AND " + restriction;
		}
		
		//type
		if(!lstCat.isEmpty() && !lstCat.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPES, lstCat);

		//distance
		if(lstPoint.size() > 0) {
			Double nlat = (double)lstPoint.get(1).reqLat();
			Double slat = (double)lstPoint.get(2).reqLat();
			Double elng = (double)lstPoint.get(3).reqLon();
			Double wlng = (double)lstPoint.get(4).reqLon();

			sql += " AND " + String.format(SQL_REQ_WHERE_DISTANCE, slat, nlat, wlng, elng);
		}
		
		if(style != null && style != 0) {
			String orderCol01 	= null;
			String orderCol02 	= null;
			String orderCol03 	= null;
			String orderDir		= "DESC";
			
			switch(style){
				case 1: {									//latest
						orderCol01 = COL_D_DATE_01; 	
						sql += String.format(SQL_REQ_ORDER_LEVEL_01, orderCol01, orderDir);
						break;		
					}
				case 2: {									//hot
						orderCol01 = COL_F_VAL_05;
						sql += String.format(SQL_REQ_ORDER_LEVEL_01, orderCol01, orderDir);
						break;
					}
				case 3: {									//1st featured, 2nd latest
						orderCol01 = COL_I_TYPE_01;
						orderCol02 = COL_D_DATE_01;
						sql += String.format(SQL_REQ_ORDER_LEVEL_02, orderCol01, orderDir, orderCol02, orderDir);
						break;
					}
				case 4: {									//1st featured, 2nd hot
						orderCol01 = COL_I_TYPE_01;
						orderCol02 = COL_F_VAL_05;
						sql += String.format(SQL_REQ_ORDER_LEVEL_02, orderCol01, orderDir, orderCol02, orderDir);
						break;
					}
				case 5: {									//1st featured, 2nd hot, 3nd latest
						orderCol01 = COL_I_TYPE_01;
						orderCol02 = COL_F_VAL_05;
						orderCol03 = COL_D_DATE_01;
						sql += String.format(SQL_REQ_ORDER_LEVEL_03, orderCol01, orderDir, orderCol02, orderDir, orderCol03, orderDir);
						break;
				}
			}

		}

		return sql;
	}
	
	public static List<ViNsoOfferMap> reqLstMapGrid(Integer begin, Integer nbRes, String stat, Integer style, String searchKey, String lstCat, List<GeoPoint> lstPoint) throws Exception {
		List<ViNsoOfferMap> 	list 	= new ArrayList<ViNsoOfferMap>();		
		String sql = reqLstMapGridSql(stat, style, searchKey, lstCat, lstPoint);		
		list = ViNsoOfferMap.DAO.reqList(begin, nbRes, sql);

		return list;
	}


	private static String reqLstMapGridSqlCount(String stat, Integer style, String searchKey, String lstCat, List<GeoPoint> lstPoint) throws Exception {
		String sql	= SQL_REQ_LIST_COUNT;

		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN_DOCUMENT;

		sql += SQL_REQ_WHERE;

//		if(type != null && type != 0)
//			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE, type);

		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat);
		
		//searchKey = %
		String restriction	= reqRestriction("%");

		if(restriction != null) {
			sql += " AND " + restriction;
		}
//		if(!searchKey.equals("%%")) {
//			String restriction	= reqRestriction(searchKey);
//			
//			if(restriction != null) {
//				sql += " AND " + restriction;
//			}
//		}

//			if(style != null && style != 0) {
//				String orderCol = null;
//				String orderDir	= "DESC";
//
//				switch(style){
//				case 1: orderCol = COL_D_DATE_ADD; 	break;	//latest	
//				case 2: orderCol = COL_F_EVAL_05; 	break;	//hot						
//				}
//
//				if (orderCol!=null){
//					sql += String.format(SQL_REQ_ORDER, orderCol, orderDir);
//				}
//			}
		
		//type
		if(!lstCat.isEmpty() && !lstCat.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPES, lstCat);

		//distance
		if(lstPoint.size() > 0) {
			Double nlat = (double)lstPoint.get(1).reqLat();
			Double slat = (double)lstPoint.get(2).reqLat();
			Double elng = (double)lstPoint.get(3).reqLon();
			Double wlng = (double)lstPoint.get(4).reqLon();

			sql += " AND " + String.format(SQL_REQ_WHERE_DISTANCE, slat, nlat, wlng, elng);
		}

		return sql;
	}

	public static Integer reqLstMapGridCount(String stat, Integer style, String searchKey, String lstCat, List<GeoPoint> lstPoint) throws Exception {
		String sql = reqLstMapGridSqlCount(stat, style, searchKey,lstCat, lstPoint);			
		Integer count = ViNsoOfferMap.DAO.reqCount(sql).intValue();

		return count;
	}
	private static String reqLstGridSqlCount(Integer type, String multiType, Integer stat, Integer style, Integer companyId,Integer perManId, Integer typ02, Integer uIdFavorite) throws Exception {
		String sql	= SQL_REQ_LIST_COUNT;
		
		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN_DOCUMENT;
		
//		if (uIdFavorite!=null)
//			sql = sql + String.format(SQL_REQ_LIST_JOIN_FAVORTIE, uIdFavorite); 
		
		sql += SQL_REQ_WHERE;
		
		if(type != null && type != 0)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE, type);
		else if(multiType!=null && !multiType.isEmpty() && !multiType.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPES, multiType);
		
		if(typ02 != null && typ02 > 0)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPE_02, typ02);
		
		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat);
		
		if(perManId != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_PER_MAN_ID, perManId);
		
//		if(companyId != null)
//			sql += " AND " + String.format(SQL_REQ_WHERE_COMPANY, companyId);
		
		//searchKey = %
		String restriction	= reqRestriction("%");
		
		if(restriction != null) {
			sql += " AND " + restriction;
		}
		
//		if(style != null && style != 0) {
//			String orderCol = null;
//			String orderDir	= "DESC";
//			
//			switch(style){
//				case 1: orderCol = COL_D_DATE_01; 	break;	//latest	
//				case 2: orderCol = COL_F_EVAL_05; 	break;	//hot						
//				}
//	
//				if (orderCol!=null){
//					sql += String.format(SQL_REQ_ORDER, orderCol, orderDir);
//				}
//		}
		
		return sql;
	}
	
	public static Integer reqLstGridCount(Integer type, String multiType, Integer stat, Integer style, Integer companyId, Integer perManId, Integer typ02, Integer uIdFavorite) throws Exception {
		String sql = reqLstGridSqlCount(type, multiType, stat, style, companyId, perManId, typ02, uIdFavorite);			
		Integer count = ViNsoOfferMap.DAO.reqCount(sql).intValue();
			
		return count;
	}
	
	//----------------------------------------------------------------
	
	
	//----------------------------------------------------------------
	private static String reqLstSearchSql(Integer stat, String searchKey, String lstCat, List<GeoPoint>  lstPoint, Set<Integer> setOptQuant) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT;
		
		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN_DOCUMENT;
		
//		if (setOptQuant !=null) sql += String.format(SQL_REQ_LIST_JOIN_PROP_QUANT, ToolString.reqStringFromCollection(setOptQuant, ","));
		
		sql += SQL_REQ_WHERE;
		
		
		//type
		if(lstCat!=null&& !lstCat.isEmpty() && !lstCat.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPES, lstCat);
		
		//status
		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat);
		
		//searchKey
		String restriction	= reqRestriction(searchKey);
		
		if(restriction != null) {
			sql += " AND " + restriction;
		}
		
		//distance
		if(lstPoint!=null && lstPoint.size() > 0) {
			Double nlat = (double)lstPoint.get(1).reqLat();
			Double slat = (double)lstPoint.get(2).reqLat();
			Double elng = (double)lstPoint.get(3).reqLon();
			Double wlng = (double)lstPoint.get(4).reqLon();
			
			sql += " AND " + String.format(SQL_REQ_WHERE_DISTANCE, slat, nlat, wlng, elng);
		}
		return sql;
	}
	
	
	
	public static List<ViNsoOfferMap> reqLstSearch(Integer begin, Integer nbRes, Integer stat, String searchKey, String lstCat, List<GeoPoint>  lstPoint, Set<Integer> setOptquant) throws Exception {
		List<ViNsoOfferMap> 	list 	= new ArrayList<ViNsoOfferMap>();		
		String sql = reqLstSearchSql(stat, searchKey, lstCat, lstPoint, setOptquant);		
		list = ViNsoOfferMap.DAO.reqList(begin, nbRes, sql);
			
		return list;
	}

	//----------------------------------------------------------------
	private static String reqGetLstByLocSql(Integer stat, String searchKey, String lstCat, List<GeoPoint>  lstPoint) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT_SEARCH_AUTOCOMPLETE;
		
		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_WHERE;
		
		//type
		if(lstCat!=null&& !lstCat.isEmpty() && !lstCat.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPES, lstCat);
		
		//status
		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat);
		
		//searchKey
		String restriction	= reqRestriction(searchKey);
		
		if(restriction != null) {
			sql += " AND " + restriction;
		}
		
		//distance
		if(lstPoint!=null && lstPoint.size() > 0) {
			Double nlat = (double)lstPoint.get(1).reqLat();
			Double slat = (double)lstPoint.get(2).reqLat();
			Double elng = (double)lstPoint.get(3).reqLon();
			Double wlng = (double)lstPoint.get(4).reqLon();
			
			sql += " AND " + String.format(SQL_REQ_WHERE_DISTANCE, slat, nlat, wlng, elng);
		}
		return sql;
	}
	
	public static List<ViNsoOfferMap> reqListAreaByLoc(Integer begin, Integer nbRes, Integer stat, String searchKey, String lstCat, List<GeoPoint>  lstPoint) throws Exception {
		List<ViNsoOfferMap> 	list 	= new ArrayList<ViNsoOfferMap>();		
		String sql = reqGetLstByLocSql(stat, searchKey, lstCat, lstPoint);		
		list = ViNsoOfferMap.DAO.reqList(begin, nbRes, sql);
			
		return list;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------
	private static String reqLstSearchSqlCount(Integer stat, String searchKey, String lstCat, List<GeoPoint>  lstPoint, Set<Integer> setOptQuant) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT_COUNT;
		
		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN_DOCUMENT;
		
//		if (setOptQuant !=null) sql += String.format(SQL_REQ_LIST_JOIN_PROP_QUANT, ToolString.reqStringFromCollection(setOptQuant, ","));
		
		sql += SQL_REQ_WHERE;
		
		
		//type
		if(!lstCat.isEmpty() && !lstCat.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPES, lstCat);
		
		//status
		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat);
		
		//searchKey
		String restriction	= reqRestriction(searchKey);
		
		if(restriction != null) {
			sql += " AND " + restriction;
		}
		
		//distance
		if(lstPoint.size() > 0) {
			Double nlat = (double)lstPoint.get(1).reqLat();
			Double slat = (double)lstPoint.get(2).reqLat();
			Double elng = (double)lstPoint.get(3).reqLon();
			Double wlng = (double)lstPoint.get(4).reqLon();
			
			sql += " AND " + String.format(SQL_REQ_WHERE_DISTANCE, slat, nlat, wlng, elng);
		}
		return sql;
	}
	
	public static Integer reqLstSearchCount(Integer stat, String searchKey, String lstCat, List<GeoPoint> lstPoint, Set<Integer> setOptquant) throws Exception {
		
		String sql 	= reqLstSearchSqlCount(stat, searchKey, lstCat, lstPoint, setOptquant);		
		Integer count = ViNsoOfferMap.DAO.reqCount(sql).intValue();
			
		return count;
	}
	
	//----------------------------------------------------------------
	private static String reqLstMapSql(Integer stat01, Integer stat02, Integer typ01, Integer typ03, String searchKey, Set<Integer> lstCat, JSONObject mon, List<GeoPoint> lstPoint) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT_MAP;
		
		sql += SQL_REQ_LIST_FROM;
		
		if (lstCat!=null && lstCat.size()>0){
			sql += String.format(SQL_REQ_LIST_JOIN_CAT, ToolString.reqStringFromCollection(lstCat, ",")); 
		}

		sql += SQL_REQ_LIST_JOIN_DOCUMENT;
		
		sql += SQL_REQ_WHERE;
		
		if(mon != null) {
			Integer moneyMin = ToolData.reqInt	(mon, "min"		, 0); ;
			Integer moneyMax = ToolData.reqInt	(mon, "max"		, Integer.MAX_VALUE);  
			sql += " AND " + String.format(SQL_REQ_WHERE_BETWEEN_MON, moneyMin, moneyMax);
		}
		
		if(typ01 != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYP_01, typ01);
		if(typ03 != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_TYP_03, typ03);
		
		//status
		if(stat01 != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat01);
		if(stat02 != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_02, stat02);
		
		//searchKey
		String restriction	= reqRestriction(searchKey);
		
		if(restriction != null) {
			sql += " AND " + restriction;
		}
		
		//distance
		if(lstPoint.size() > 0) {
			Double nlat = (double)lstPoint.get(1).reqLat();
			Double slat = (double)lstPoint.get(2).reqLat();
			Double elng = (double)lstPoint.get(3).reqLon();
			Double wlng = (double)lstPoint.get(4).reqLon();
			
			sql += " AND " + String.format(SQL_REQ_WHERE_DISTANCE, slat, nlat, wlng, elng);
		}
		sql += " Order By " + ATT_I_ID + " DESC";
		return sql;
	}
		
	public static List<ViNsoOfferMap> reqLstMap(int begin, int nbRes, int stat01, int stat02, int typ01, int typ03, String searchKey, Set<Integer> lstCat, JSONObject mon, List<GeoPoint> lstPoint) throws Exception {
		List<ViNsoOfferMap> 	list 	= new ArrayList<ViNsoOfferMap>();		
		String 					sql 	= reqLstMapSql(stat01, stat02, typ01, typ03, searchKey, lstCat, mon, lstPoint);		
		list = ViNsoOfferMap.DAO.reqList(begin, nbRes, sql);
			
		return list;
	}
	
		
		
	private static String reqLstMapSqlCount(Integer stat, String searchKey, String lstCat, List<GeoPoint> lstPoint) throws Exception {
		String sql	= SQL_REQ_LIST_SELECT_COUNT;
		
		sql += SQL_REQ_LIST_FROM;

		sql += SQL_REQ_LIST_JOIN_DOCUMENT;
		
		sql += SQL_REQ_WHERE;
		
		
		//type
		if(!lstCat.isEmpty() && !lstCat.equals("0"))
			sql += " AND " + String.format(SQL_REQ_WHERE_TYPES, lstCat);
		
		//status
		if(stat != null)
			sql += " AND " + String.format(SQL_REQ_WHERE_STAT_01, stat);
		
		//searchKey
		String restriction	= reqRestriction(searchKey);
		
		if(restriction != null) {
			sql += " AND " + restriction;
		}
		
		//distance
		if(lstPoint.size() > 0) {
			Double nlat = (double)lstPoint.get(1).reqLat();
			Double slat = (double)lstPoint.get(2).reqLat();
			Double elng = (double)lstPoint.get(3).reqLon();
			Double wlng = (double)lstPoint.get(4).reqLon();
			
			sql += " AND " + String.format(SQL_REQ_WHERE_DISTANCE, slat, nlat, wlng, elng);
		}
		return sql;
	}
	
	public static Integer reqLstMapCount(Integer stat, String searchKey, String lstCat, List<GeoPoint> lstPoint) throws Exception {
		
		String sql 	= reqLstMapSqlCount(stat, searchKey, lstCat, lstPoint);		
		Integer count = ViNsoOfferMap.DAO.reqCount(sql).intValue();
			
		return count;
	}
	
	public static void doBuildTranslations(List<ViNsoOfferMap> list) throws Exception{
		//TranslationTool.doBuildTpyTranslations(list, DefDBExt.ID_TA_NSO_OFFER, ViNsoOfferMap.ATT_O_TRANSLATIONS, false);
	}

	//---------------------------------------------------------------------------
	public static List<ViNsoOfferMap> reqLstSearch(Integer begin, Integer nbRes, Integer stat, String searchKey, Collection<Integer> ids) throws Exception {
		List<ViNsoOfferMap> 	list 	= new ArrayList<ViNsoOfferMap>();		
		String sql = reqLstSearchSql(stat, searchKey, null, null, null);	
		sql += " AND " + SQL_REQ_WHERE_IDS;
		
		if(ids != null && ids.size() > 0) {
			int i = 0;
			int count = 0;
			Iterator<Integer> it = ids.iterator();
			do {
				String strLstIn = "";
				for(; i < ids.size(); ) {
					strLstIn += "," + it.next();
					count ++; 
					i++;
					if(count > 900) {							
						count  = 0;
						break;
					}
				}
									strLstIn 	= strLstIn.substring(1);
				String 				sqlt 		= String.format(sql, strLstIn);
				List<ViNsoOfferMap> 	l 			= ViNsoOfferMap.DAO.reqList(sqlt)  ;
				list.addAll(l);
			} while(i < ids.size());
		}
		
//		list = ViNsoArea.DAO.reqList_In_SQL(sql, ids);
		return list;
	}
	
	public static Integer reqLstSearchCount(Integer stat, String searchKey, Collection<Integer> ids) throws Exception {		
		String sql 	= reqLstSearchSqlCount(stat, searchKey, null, null, null);		
		sql += " AND " + SQL_REQ_WHERE_IDS;
		
		//con loi cho nay vi neu ids .size>1000 thi chet
		Integer count = ViNsoOfferMap.DAO.reqCount(sql).intValue();
			
		return count;
	}
	
}