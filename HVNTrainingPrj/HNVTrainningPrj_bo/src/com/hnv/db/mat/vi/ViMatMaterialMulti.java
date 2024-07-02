package com.hnv.db.mat.vi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.tool.ToolSet;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.db.mat.TaMatMaterial;
import com.hnv.db.per.TaPerPerson;
import com.hnv.db.tpy.TaTpyCategory;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.def.DefDBExt;

import sun.security.action.PutAllAction;	


/**
 * TaMatMaterial by H&V SAS
 */
@Entity
public class ViMatMaterialMulti extends EntityAbstract<ViMatMaterialMulti> {

	private static final long serialVersionUID = 1L;
	
	private static final int  ENT_TYP			= DefDBExt.ID_TA_MAT_MATERIAL;
	
	//---------------------------List of Column from DB-----------------------------
	
	public static final String	COL_I_ID_M                           	 =	"I_ID_%s";
	
	public static final String	COL_I_STATUS_M_01                       =	"I_Status_%s_01";
	public static final String	COL_I_STATUS_M_02                       =	"I_Status_%s_02";
	public static final String	COL_I_STATUS_M_03                       =	"I_Status_%s_03";
	public static final String	COL_I_STATUS_M_04                       =	"I_Status_%s_04";
	public static final String	COL_I_STATUS_M_05                       =	"I_Status_%s_05";
	
	public static final String	COL_I_TYPE_M_01                         =	"I_Type_%s_01";
	public static final String	COL_I_TYPE_M_02                         =	"I_Type_%s_02";
	public static final String	COL_I_TYPE_M_03                         =	"I_Type_%s_03";
	public static final String	COL_I_TYPE_M_04                         =	"I_Type_%s_04";
	public static final String	COL_I_TYPE_M_05                         =	"I_Type_%s_05";
	
	public static final String	COL_T_NAME_M_01                         =	"T_Name_%s_01";
	public static final String	COL_T_NAME_M_02                         =	"T_Name_%s_02";
	public static final String	COL_T_NAME_M_03                         =	"T_Name_%s_03";
	
	public static final String	COL_T_CODE_M_01                         =	"T_Code_%s_01";
	public static final String	COL_T_CODE_M_02                         =	"T_Code_%s_02";
	public static final String	COL_T_CODE_M_03                         =	"T_Code_%s_03";
	public static final String	COL_T_CODE_M_04                         =	"T_Code_%s_04";
	public static final String	COL_T_CODE_M_05                         =	"T_Code_%s_05";
	
	public static final String	COL_T_INFO_M_01                         =	"T_Info_%s_01";
	public static final String	COL_T_INFO_M_02                         =	"T_Info_%s_02";
	public static final String	COL_T_INFO_M_03                         =	"T_Info_%s_03";
	public static final String	COL_T_INFO_M_04                         =	"T_Info_%s_04";
	public static final String	COL_T_INFO_M_05                         =	"T_Info_%s_05";
	public static final String	COL_T_INFO_M_06                         =	"T_Info_%s_06";
	public static final String	COL_T_INFO_M_07                         =	"T_Info_%s_07";
	public static final String	COL_T_INFO_M_08                         =	"T_Info_%s_08";
	public static final String	COL_T_INFO_M_09                         =	"T_Info_%s_09";
	public static final String	COL_T_INFO_M_10                         =	"T_Info_%s_10";
	
	public static final String	COL_D_DATE_M_01                         =	"D_Date_%s_01";
	public static final String	COL_D_DATE_M_02                         =	"D_Date_%s_02";
	public static final String	COL_D_DATE_M_03                         =	"D_Date_%s_03";
	public static final String	COL_D_DATE_M_04                         =	"D_Date_%s_04";
	public static final String	COL_D_DATE_M_05                         =	"D_Date_%s_05";
	

	public static final String	COL_F_VAL_M_01                          =	"F_Val_%s_01"; //nb app
	public static final String	COL_F_VAL_M_02                          =	"F_Val_%s_02";
	public static final String	COL_F_VAL_M_03                          =	"F_Val_%s_03"; 
	public static final String	COL_F_VAL_M_04                          =	"F_Val_%s_04";
	public static final String	COL_F_VAL_M_05                          =	"F_Val_%s_05";
	
	
	
	//------------------------------------------------------------------------------
	public static final String	COL_I_ID_01                           	 =	"I_ID_01";
	
	public static final String	COL_I_STATUS_01_01                       =	"I_Status_01_01";
	public static final String	COL_I_STATUS_01_02                       =	"I_Status_01_02";
	public static final String	COL_I_STATUS_01_03                       =	"I_Status_01_03";
	public static final String	COL_I_STATUS_01_04                       =	"I_Status_01_04";
	public static final String	COL_I_STATUS_01_05                       =	"I_Status_01_05";
	
	public static final String	COL_I_TYPE_01_01                         =	"I_Type_01_01";
	public static final String	COL_I_TYPE_01_02                         =	"I_Type_01_02";
	public static final String	COL_I_TYPE_01_03                         =	"I_Type_01_03";
	public static final String	COL_I_TYPE_01_04                         =	"I_Type_01_04";
	public static final String	COL_I_TYPE_01_05                         =	"I_Type_01_05";
	
	public static final String	COL_T_NAME_01_01                         =	"T_Name_01_01";
	public static final String	COL_T_NAME_01_02                         =	"T_Name_01_02";
	public static final String	COL_T_NAME_01_03                         =	"T_Name_01_03";
	
	public static final String	COL_T_CODE_01_01                         =	"T_Code_01_01";
	public static final String	COL_T_CODE_01_02                         =	"T_Code_01_02";
	public static final String	COL_T_CODE_01_03                         =	"T_Code_01_03";
	public static final String	COL_T_CODE_01_04                         =	"T_Code_01_04";
	public static final String	COL_T_CODE_01_05                         =	"T_Code_01_05";
	
	public static final String	COL_T_INFO_01_01                         =	"T_Info_01_01";
	public static final String	COL_T_INFO_01_02                         =	"T_Info_01_02";
	public static final String	COL_T_INFO_01_03                         =	"T_Info_01_03";
	public static final String	COL_T_INFO_01_04                         =	"T_Info_01_04";
	public static final String	COL_T_INFO_01_05                         =	"T_Info_01_05";
	public static final String	COL_T_INFO_01_06                         =	"T_Info_01_06";
	public static final String	COL_T_INFO_01_07                         =	"T_Info_01_07";
	public static final String	COL_T_INFO_01_08                         =	"T_Info_01_08";
	public static final String	COL_T_INFO_01_09                         =	"T_Info_01_09";
	public static final String	COL_T_INFO_01_10                         =	"T_Info_01_10";
	
	public static final String	COL_D_DATE_01_01                         =	"D_Date_01_01";
	public static final String	COL_D_DATE_01_02                         =	"D_Date_01_02";
	public static final String	COL_D_DATE_01_03                         =	"D_Date_01_03";
	public static final String	COL_D_DATE_01_04                         =	"D_Date_01_04";
	public static final String	COL_D_DATE_01_05                         =	"D_Date_01_05";
	

	public static final String	COL_F_VAL_01_01                          =	"F_Val_01_01"; //nb app
	public static final String	COL_F_VAL_01_02                          =	"F_Val_01_02";
	public static final String	COL_F_VAL_01_03                          =	"F_Val_01_03"; 
	public static final String	COL_F_VAL_01_04                          =	"F_Val_01_04";
	public static final String	COL_F_VAL_01_05                          =	"F_Val_01_05";
	
	
	public static final String	COL_I_ID_02                              =	"I_ID_02";
	
	public static final String	COL_I_STATUS_02_01                       =	"I_Status_02_01";
	public static final String	COL_I_STATUS_02_02                       =	"I_Status_02_02";
	public static final String	COL_I_STATUS_02_03                       =	"I_Status_02_03";
	public static final String	COL_I_STATUS_02_04                       =	"I_Status_02_04";
	public static final String	COL_I_STATUS_02_05                       =	"I_Status_02_05";
	
	public static final String	COL_I_TYPE_02_01                         =	"I_Type_02_01";
	public static final String	COL_I_TYPE_02_02                         =	"I_Type_02_02";
	public static final String	COL_I_TYPE_02_03                         =	"I_Type_02_03";
	public static final String	COL_I_TYPE_02_04                         =	"I_Type_02_04";
	public static final String	COL_I_TYPE_02_05                         =	"I_Type_02_05";
	
	public static final String	COL_T_NAME_02_01                         =	"T_Name_02_01";
	public static final String	COL_T_NAME_02_02                         =	"T_Name_02_02";
	public static final String	COL_T_NAME_02_03                         =	"T_Name_02_03";
	
	public static final String	COL_T_CODE_02_01                         =	"T_Code_02_01";
	public static final String	COL_T_CODE_02_02                         =	"T_Code_02_02";
	public static final String	COL_T_CODE_02_03                         =	"T_Code_02_03";
	public static final String	COL_T_CODE_02_04                         =	"T_Code_02_04";
	public static final String	COL_T_CODE_02_05                         =	"T_Code_02_05";
	
	public static final String	COL_T_INFO_02_01                         =	"T_Info_02_01";
	public static final String	COL_T_INFO_02_02                         =	"T_Info_02_02";
	public static final String	COL_T_INFO_02_03                         =	"T_Info_02_03";
	public static final String	COL_T_INFO_02_04                         =	"T_Info_02_04";
	public static final String	COL_T_INFO_02_05                         =	"T_Info_02_05";
	public static final String	COL_T_INFO_02_06                         =	"T_Info_02_06";
	public static final String	COL_T_INFO_02_07                         =	"T_Info_02_07";
	public static final String	COL_T_INFO_02_08                         =	"T_Info_02_08";
	public static final String	COL_T_INFO_02_09                         =	"T_Info_02_09";
	public static final String	COL_T_INFO_02_10                         =	"T_Info_02_10";
	
	public static final String	COL_D_DATE_02_01                         =	"D_Date_02_01";
	public static final String	COL_D_DATE_02_02                         =	"D_Date_02_02";
	public static final String	COL_D_DATE_02_03                         =	"D_Date_02_03";
	public static final String	COL_D_DATE_02_04                         =	"D_Date_02_04";
	public static final String	COL_D_DATE_02_05                         =	"D_Date_02_05";
	

	public static final String	COL_F_VAL_02_01                          =	"F_Val_02_01"; 
	public static final String	COL_F_VAL_02_02                          =	"F_Val_02_02";
	public static final String	COL_F_VAL_02_03                          =	"F_Val_02_03"; 
	public static final String	COL_F_VAL_02_04                          =	"F_Val_02_04";
	public static final String	COL_F_VAL_02_05                          =	"F_Val_02_05";
	
	public static final String	COL_I_ID_03                              =	"I_ID_03";
	
	public static final String	COL_I_STATUS_03_01                       =	"I_Status_03_01";
	public static final String	COL_I_STATUS_03_02                       =	"I_Status_03_02";
	public static final String	COL_I_STATUS_03_03                       =	"I_Status_03_03";
	public static final String	COL_I_STATUS_03_04                       =	"I_Status_03_04";
	public static final String	COL_I_STATUS_03_05                       =	"I_Status_03_05";
	
	public static final String	COL_I_TYPE_03_01                         =	"I_Type_03_01";
	public static final String	COL_I_TYPE_03_02                         =	"I_Type_03_02";
	public static final String	COL_I_TYPE_03_03                         =	"I_Type_03_03";
	public static final String	COL_I_TYPE_03_04                         =	"I_Type_03_04";
	public static final String	COL_I_TYPE_03_05                         =	"I_Type_03_05";
	
	public static final String	COL_T_NAME_03_01                         =	"T_Name_03_01";
	public static final String	COL_T_NAME_03_02                         =	"T_Name_03_02";
	public static final String	COL_T_NAME_03_03                         =	"T_Name_03_03";
	
	public static final String	COL_T_CODE_03_01                         =	"T_Code_03_01";
	public static final String	COL_T_CODE_03_02                         =	"T_Code_03_02";
	public static final String	COL_T_CODE_03_03                         =	"T_Code_03_03";
	public static final String	COL_T_CODE_03_04                         =	"T_Code_03_04";
	public static final String	COL_T_CODE_03_05                         =	"T_Code_03_05";
	
	public static final String	COL_T_INFO_03_01                         =	"T_Info_03_01";
	public static final String	COL_T_INFO_03_02                         =	"T_Info_03_02";
	public static final String	COL_T_INFO_03_03                         =	"T_Info_03_03";
	public static final String	COL_T_INFO_03_04                         =	"T_Info_03_04";
	public static final String	COL_T_INFO_03_05                         =	"T_Info_03_05";
	public static final String	COL_T_INFO_03_06                         =	"T_Info_03_06";
	public static final String	COL_T_INFO_03_07                         =	"T_Info_03_07";
	public static final String	COL_T_INFO_03_08                         =	"T_Info_03_08";
	public static final String	COL_T_INFO_03_09                         =	"T_Info_03_09";
	public static final String	COL_T_INFO_03_10                         =	"T_Info_03_10";
	
	public static final String	COL_D_DATE_03_01                         =	"D_Date_03_01";
	public static final String	COL_D_DATE_03_02                         =	"D_Date_03_02";
	public static final String	COL_D_DATE_03_03                         =	"D_Date_03_03";
	public static final String	COL_D_DATE_03_04                         =	"D_Date_03_04";
	public static final String	COL_D_DATE_03_05                         =	"D_Date_03_05";
	

	public static final String	COL_F_VAL_03_01                          =	"F_Val_03_01"; //nb app
	public static final String	COL_F_VAL_03_02                          =	"F_Val_03_02";
	public static final String	COL_F_VAL_03_03                          =	"F_Val_03_03"; 
	public static final String	COL_F_VAL_03_04                          =	"F_Val_03_04";
	public static final String	COL_F_VAL_03_05                          =	"F_Val_03_05";
	
	
	public static final String	COL_I_ID_04                              =	"I_ID_04";
	
	public static final String	COL_I_STATUS_04_01                       =	"I_Status_04_01";
	public static final String	COL_I_STATUS_04_02                       =	"I_Status_04_02";
	public static final String	COL_I_STATUS_04_03                       =	"I_Status_04_03";
	public static final String	COL_I_STATUS_04_04                       =	"I_Status_04_04";
	public static final String	COL_I_STATUS_04_05                       =	"I_Status_04_05";
	
	public static final String	COL_I_TYPE_04_01                         =	"I_Type_04_01";
	public static final String	COL_I_TYPE_04_02                         =	"I_Type_04_02";
	public static final String	COL_I_TYPE_04_03                         =	"I_Type_04_03";
	public static final String	COL_I_TYPE_04_04                         =	"I_Type_04_04";
	public static final String	COL_I_TYPE_04_05                         =	"I_Type_04_05";
	
	public static final String	COL_T_NAME_04_01                         =	"T_Name_04_01";
	public static final String	COL_T_NAME_04_02                         =	"T_Name_04_02";
	public static final String	COL_T_NAME_04_03                         =	"T_Name_04_03";
	
	public static final String	COL_T_CODE_04_01                         =	"T_Code_04_01";
	public static final String	COL_T_CODE_04_02                         =	"T_Code_04_02";
	public static final String	COL_T_CODE_04_03                         =	"T_Code_04_03";
	public static final String	COL_T_CODE_04_04                         =	"T_Code_04_04";
	public static final String	COL_T_CODE_04_05                         =	"T_Code_04_05";
	
	public static final String	COL_T_INFO_04_01                         =	"T_Info_04_01";
	public static final String	COL_T_INFO_04_02                         =	"T_Info_04_02";
	public static final String	COL_T_INFO_04_03                         =	"T_Info_04_03";
	public static final String	COL_T_INFO_04_04                         =	"T_Info_04_04";
	public static final String	COL_T_INFO_04_05                         =	"T_Info_04_05";
	public static final String	COL_T_INFO_04_06                         =	"T_Info_04_06";
	public static final String	COL_T_INFO_04_07                         =	"T_Info_04_07";
	public static final String	COL_T_INFO_04_08                         =	"T_Info_04_08";
	public static final String	COL_T_INFO_04_09                         =	"T_Info_04_09";
	public static final String	COL_T_INFO_04_10                         =	"T_Info_04_10";
	
	public static final String	COL_D_DATE_04_01                         =	"D_Date_04_01";
	public static final String	COL_D_DATE_04_02                         =	"D_Date_04_02";
	public static final String	COL_D_DATE_04_03                         =	"D_Date_04_03";
	public static final String	COL_D_DATE_04_04                         =	"D_Date_04_04";
	public static final String	COL_D_DATE_04_05                         =	"D_Date_04_05";
	

	public static final String	COL_F_VAL_04_01                          =	"F_Val_04_01"; //nb app
	public static final String	COL_F_VAL_04_02                          =	"F_Val_04_02";
	public static final String	COL_F_VAL_04_03                          =	"F_Val_04_03"; 
	public static final String	COL_F_VAL_04_04                          =	"F_Val_04_04";
	public static final String	COL_F_VAL_04_05                          =	"F_Val_04_05";
	
	
	public static final String	COL_I_ID_05                              =	"I_ID_05";
	
	public static final String	COL_I_STATUS_05_01                       =	"I_Status_05_01";
	public static final String	COL_I_STATUS_05_02                       =	"I_Status_05_02";
	public static final String	COL_I_STATUS_05_03                       =	"I_Status_05_03";
	public static final String	COL_I_STATUS_05_04                       =	"I_Status_05_04";
	public static final String	COL_I_STATUS_05_05                       =	"I_Status_05_05";
	
	public static final String	COL_I_TYPE_05_01                         =	"I_Type_05_01";
	public static final String	COL_I_TYPE_05_02                         =	"I_Type_05_02";
	public static final String	COL_I_TYPE_05_03                         =	"I_Type_05_03";
	public static final String	COL_I_TYPE_05_04                         =	"I_Type_05_04";
	public static final String	COL_I_TYPE_05_05                         =	"I_Type_05_05";
	
	public static final String	COL_T_NAME_05_01                         =	"T_Name_05_01";
	public static final String	COL_T_NAME_05_02                         =	"T_Name_05_02";
	public static final String	COL_T_NAME_05_03                         =	"T_Name_05_03";
	
	public static final String	COL_T_CODE_05_01                         =	"T_Code_05_01";
	public static final String	COL_T_CODE_05_02                         =	"T_Code_05_02";
	public static final String	COL_T_CODE_05_03                         =	"T_Code_05_03";
	public static final String	COL_T_CODE_05_04                         =	"T_Code_05_04";
	public static final String	COL_T_CODE_05_05                         =	"T_Code_05_05";
	
	public static final String	COL_T_INFO_05_01                         =	"T_Info_05_01";
	public static final String	COL_T_INFO_05_02                         =	"T_Info_05_02";
	public static final String	COL_T_INFO_05_03                         =	"T_Info_05_03";
	public static final String	COL_T_INFO_05_04                         =	"T_Info_05_04";
	public static final String	COL_T_INFO_05_05                         =	"T_Info_05_05";
	public static final String	COL_T_INFO_05_06                         =	"T_Info_05_06";
	public static final String	COL_T_INFO_05_07                         =	"T_Info_05_07";
	public static final String	COL_T_INFO_05_08                         =	"T_Info_05_08";
	public static final String	COL_T_INFO_05_09                         =	"T_Info_05_09";
	public static final String	COL_T_INFO_05_10                         =	"T_Info_05_10";
	
	public static final String	COL_D_DATE_05_01                         =	"D_Date_05_01";
	public static final String	COL_D_DATE_05_02                         =	"D_Date_05_02";
	public static final String	COL_D_DATE_05_03                         =	"D_Date_05_03";
	public static final String	COL_D_DATE_05_04                         =	"D_Date_05_04";
	public static final String	COL_D_DATE_05_05                         =	"D_Date_05_05";
	

	public static final String	COL_F_VAL_05_01                          =	"F_Val_05_01"; //nb app
	public static final String	COL_F_VAL_05_02                          =	"F_Val_05_02";
	public static final String	COL_F_VAL_05_03                          =	"F_Val_05_03"; 
	public static final String	COL_F_VAL_05_04                          =	"F_Val_05_04";
	public static final String	COL_F_VAL_05_05                          =	"F_Val_05_05";
	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID_01                           	=	"I_ID_01";
	
	public static final String	ATT_I_STATUS_01_01                       =	"I_Status_01_01";
	public static final String	ATT_I_STATUS_01_02                       =	"I_Status_01_02";
	public static final String	ATT_I_STATUS_01_03                       =	"I_Status_01_03";
	public static final String	ATT_I_STATUS_01_04                       =	"I_Status_01_04";
	public static final String	ATT_I_STATUS_01_05                       =	"I_Status_01_05";
	
	public static final String	ATT_I_TYPE_01_01                         =	"I_Type_01_01";
	public static final String	ATT_I_TYPE_01_02                         =	"I_Type_01_02";
	public static final String	ATT_I_TYPE_01_03                         =	"I_Type_01_03";
	public static final String	ATT_I_TYPE_01_04                         =	"I_Type_01_04";
	public static final String	ATT_I_TYPE_01_05                         =	"I_Type_01_05";
	
	public static final String	ATT_T_NAME_01_01                         =	"T_Name_01_01";
	public static final String	ATT_T_NAME_01_02                         =	"T_Name_01_02";
	public static final String	ATT_T_NAME_01_03                         =	"T_Name_01_03";
	
	public static final String	ATT_T_CODE_01_01                         =	"T_Code_01_01";
	public static final String	ATT_T_CODE_01_02                         =	"T_Code_01_02";
	public static final String	ATT_T_CODE_01_03                         =	"T_Code_01_03";
	public static final String	ATT_T_CODE_01_04                         =	"T_Code_01_04";
	public static final String	ATT_T_CODE_01_05                         =	"T_Code_01_05";
	
	public static final String	ATT_T_INFO_01_01                         =	"T_Info_01_01";
	public static final String	ATT_T_INFO_01_02                         =	"T_Info_01_02";
	public static final String	ATT_T_INFO_01_03                         =	"T_Info_01_03";
	public static final String	ATT_T_INFO_01_04                         =	"T_Info_01_04";
	public static final String	ATT_T_INFO_01_05                         =	"T_Info_01_05";
	public static final String	ATT_T_INFO_01_06                         =	"T_Info_01_06";
	public static final String	ATT_T_INFO_01_07                         =	"T_Info_01_07";
	public static final String	ATT_T_INFO_01_08                         =	"T_Info_01_08";
	public static final String	ATT_T_INFO_01_09                         =	"T_Info_01_09";
	public static final String	ATT_T_INFO_01_10                         =	"T_Info_01_10";
	
	public static final String	ATT_D_DATE_01_01                         =	"D_Date_01_01";
	public static final String	ATT_D_DATE_01_02                         =	"D_Date_01_02";
	public static final String	ATT_D_DATE_01_03                         =	"D_Date_01_03";
	public static final String	ATT_D_DATE_01_04                         =	"D_Date_01_04";
	public static final String	ATT_D_DATE_01_05                         =	"D_Date_01_05";
	

	public static final String	ATT_F_VAL_01_01                          =	"F_Val_01_01"; //nb app
	public static final String	ATT_F_VAL_01_02                          =	"F_Val_01_02";
	public static final String	ATT_F_VAL_01_03                          =	"F_Val_01_03"; 
	public static final String	ATT_F_VAL_01_04                          =	"F_Val_01_04";
	public static final String	ATT_F_VAL_01_05                          =	"F_Val_01_05";
	
	
	public static final String	ATT_I_ID_02                              =	"I_ID_02";
	
	public static final String	ATT_I_STATUS_02_01                       =	"I_Status_02_01";
	public static final String	ATT_I_STATUS_02_02                       =	"I_Status_02_02";
	public static final String	ATT_I_STATUS_02_03                       =	"I_Status_02_03";
	public static final String	ATT_I_STATUS_02_04                       =	"I_Status_02_04";
	public static final String	ATT_I_STATUS_02_05                       =	"I_Status_02_05";
	
	public static final String	ATT_I_TYPE_02_01                         =	"I_Type_02_01";
	public static final String	ATT_I_TYPE_02_02                         =	"I_Type_02_02";
	public static final String	ATT_I_TYPE_02_03                         =	"I_Type_02_03";
	public static final String	ATT_I_TYPE_02_04                         =	"I_Type_02_04";
	public static final String	ATT_I_TYPE_02_05                         =	"I_Type_02_05";
	
	public static final String	ATT_T_NAME_02_01                         =	"T_Name_02_01";
	public static final String	ATT_T_NAME_02_02                         =	"T_Name_02_02";
	public static final String	ATT_T_NAME_02_03                         =	"T_Name_02_03";
	
	public static final String	ATT_T_CODE_02_01                         =	"T_Code_02_01";
	public static final String	ATT_T_CODE_02_02                         =	"T_Code_02_02";
	public static final String	ATT_T_CODE_02_03                         =	"T_Code_02_03";
	public static final String	ATT_T_CODE_02_04                         =	"T_Code_02_04";
	public static final String	ATT_T_CODE_02_05                         =	"T_Code_02_05";
	
	public static final String	ATT_T_INFO_02_01                         =	"T_Info_02_01";
	public static final String	ATT_T_INFO_02_02                         =	"T_Info_02_02";
	public static final String	ATT_T_INFO_02_03                         =	"T_Info_02_03";
	public static final String	ATT_T_INFO_02_04                         =	"T_Info_02_04";
	public static final String	ATT_T_INFO_02_05                         =	"T_Info_02_05";
	public static final String	ATT_T_INFO_02_06                         =	"T_Info_02_06";
	public static final String	ATT_T_INFO_02_07                         =	"T_Info_02_07";
	public static final String	ATT_T_INFO_02_08                         =	"T_Info_02_08";
	public static final String	ATT_T_INFO_02_09                         =	"T_Info_02_09";
	public static final String	ATT_T_INFO_02_10                         =	"T_Info_02_10";
	
	public static final String	ATT_D_DATE_02_01                         =	"D_Date_02_01";
	public static final String	ATT_D_DATE_02_02                         =	"D_Date_02_02";
	public static final String	ATT_D_DATE_02_03                         =	"D_Date_02_03";
	public static final String	ATT_D_DATE_02_04                         =	"D_Date_02_04";
	public static final String	ATT_D_DATE_02_05                         =	"D_Date_02_05";
	

	public static final String	ATT_F_VAL_02_01                          =	"F_Val_02_01"; //nb app
	public static final String	ATT_F_VAL_02_02                          =	"F_Val_02_02";
	public static final String	ATT_F_VAL_02_03                          =	"F_Val_02_03"; 
	public static final String	ATT_F_VAL_02_04                          =	"F_Val_02_04";
	public static final String	ATT_F_VAL_02_05                          =	"F_Val_02_05";
	
	
	public static final String	ATT_I_ID_03                              =	"I_ID_03";
	
	public static final String	ATT_I_STATUS_03_01                       =	"I_Status_03_01";
	public static final String	ATT_I_STATUS_03_02                       =	"I_Status_03_02";
	public static final String	ATT_I_STATUS_03_03                       =	"I_Status_03_03";
	public static final String	ATT_I_STATUS_03_04                       =	"I_Status_03_04";
	public static final String	ATT_I_STATUS_03_05                       =	"I_Status_03_05";
	
	public static final String	ATT_I_TYPE_03_01                         =	"I_Type_03_01";
	public static final String	ATT_I_TYPE_03_02                         =	"I_Type_03_02";
	public static final String	ATT_I_TYPE_03_03                         =	"I_Type_03_03";
	public static final String	ATT_I_TYPE_03_04                         =	"I_Type_03_04";
	public static final String	ATT_I_TYPE_03_05                         =	"I_Type_03_05";
	
	public static final String	ATT_T_NAME_03_01                         =	"T_Name_03_01";
	public static final String	ATT_T_NAME_03_02                         =	"T_Name_03_02";
	public static final String	ATT_T_NAME_03_03                         =	"T_Name_03_03";
	
	public static final String	ATT_T_CODE_03_01                         =	"T_Code_03_01";
	public static final String	ATT_T_CODE_03_02                         =	"T_Code_03_02";
	public static final String	ATT_T_CODE_03_03                         =	"T_Code_03_03";
	public static final String	ATT_T_CODE_03_04                         =	"T_Code_03_04";
	public static final String	ATT_T_CODE_03_05                         =	"T_Code_03_05";
	
	public static final String	ATT_T_INFO_03_01                         =	"T_Info_03_01";
	public static final String	ATT_T_INFO_03_02                         =	"T_Info_03_02";
	public static final String	ATT_T_INFO_03_03                         =	"T_Info_03_03";
	public static final String	ATT_T_INFO_03_04                         =	"T_Info_03_04";
	public static final String	ATT_T_INFO_03_05                         =	"T_Info_03_05";
	public static final String	ATT_T_INFO_03_06                         =	"T_Info_03_06";
	public static final String	ATT_T_INFO_03_07                         =	"T_Info_03_07";
	public static final String	ATT_T_INFO_03_08                         =	"T_Info_03_08";
	public static final String	ATT_T_INFO_03_09                         =	"T_Info_03_09";
	public static final String	ATT_T_INFO_03_10                         =	"T_Info_03_10";
	
	public static final String	ATT_D_DATE_03_01                         =	"D_Date_03_01";
	public static final String	ATT_D_DATE_03_02                         =	"D_Date_03_02";
	public static final String	ATT_D_DATE_03_03                         =	"D_Date_03_03";
	public static final String	ATT_D_DATE_03_04                         =	"D_Date_03_04";
	public static final String	ATT_D_DATE_03_05                         =	"D_Date_03_05";
	

	public static final String	ATT_F_VAL_03_01                          =	"F_Val_03_01"; //nb app
	public static final String	ATT_F_VAL_03_02                          =	"F_Val_03_02";
	public static final String	ATT_F_VAL_03_03                          =	"F_Val_03_03"; 
	public static final String	ATT_F_VAL_03_04                          =	"F_Val_03_04";
	public static final String	ATT_F_VAL_03_05                          =	"F_Val_03_05";
	
	public static final String	ATT_I_ID_04                              =	"I_ID_04";
	
	public static final String	ATT_I_STATUS_04_01                       =	"I_Status_04_01";
	public static final String	ATT_I_STATUS_04_02                       =	"I_Status_04_02";
	public static final String	ATT_I_STATUS_04_03                       =	"I_Status_04_03";
	public static final String	ATT_I_STATUS_04_04                       =	"I_Status_04_04";
	public static final String	ATT_I_STATUS_04_05                       =	"I_Status_04_05";
	
	public static final String	ATT_I_TYPE_04_01                         =	"I_Type_04_01";
	public static final String	ATT_I_TYPE_04_02                         =	"I_Type_04_02";
	public static final String	ATT_I_TYPE_04_03                         =	"I_Type_04_03";
	public static final String	ATT_I_TYPE_04_04                         =	"I_Type_04_04";
	public static final String	ATT_I_TYPE_04_05                         =	"I_Type_04_05";
	
	public static final String	ATT_T_NAME_04_01                         =	"T_Name_04_01";
	public static final String	ATT_T_NAME_04_02                         =	"T_Name_04_02";
	public static final String	ATT_T_NAME_04_03                         =	"T_Name_04_03";
	
	public static final String	ATT_T_CODE_04_01                         =	"T_Code_04_01";
	public static final String	ATT_T_CODE_04_02                         =	"T_Code_04_02";
	public static final String	ATT_T_CODE_04_03                         =	"T_Code_04_03";
	public static final String	ATT_T_CODE_04_04                         =	"T_Code_04_04";
	public static final String	ATT_T_CODE_04_05                         =	"T_Code_04_05";
	
	public static final String	ATT_T_INFO_04_01                         =	"T_Info_04_01";
	public static final String	ATT_T_INFO_04_02                         =	"T_Info_04_02";
	public static final String	ATT_T_INFO_04_03                         =	"T_Info_04_03";
	public static final String	ATT_T_INFO_04_04                         =	"T_Info_04_04";
	public static final String	ATT_T_INFO_04_05                         =	"T_Info_04_05";
	public static final String	ATT_T_INFO_04_06                         =	"T_Info_04_06";
	public static final String	ATT_T_INFO_04_07                         =	"T_Info_04_07";
	public static final String	ATT_T_INFO_04_08                         =	"T_Info_04_08";
	public static final String	ATT_T_INFO_04_09                         =	"T_Info_04_09";
	public static final String	ATT_T_INFO_04_10                         =	"T_Info_04_10";
	
	public static final String	ATT_D_DATE_04_01                         =	"D_Date_04_01";
	public static final String	ATT_D_DATE_04_02                         =	"D_Date_04_02";
	public static final String	ATT_D_DATE_04_03                         =	"D_Date_04_03";
	public static final String	ATT_D_DATE_04_04                         =	"D_Date_04_04";
	public static final String	ATT_D_DATE_04_05                         =	"D_Date_04_05";
	

	public static final String	ATT_F_VAL_04_01                          =	"F_Val_04_01"; //nb app
	public static final String	ATT_F_VAL_04_02                          =	"F_Val_04_02";
	public static final String	ATT_F_VAL_04_03                          =	"F_Val_04_03"; 
	public static final String	ATT_F_VAL_04_04                          =	"F_Val_04_04";
	public static final String	ATT_F_VAL_04_05                          =	"F_Val_04_05";
	
	public static final String	ATT_I_ID_05                              =	"I_ID_05";
	
	public static final String	ATT_I_STATUS_05_01                       =	"I_Status_05_01";
	public static final String	ATT_I_STATUS_05_02                       =	"I_Status_05_02";
	public static final String	ATT_I_STATUS_05_03                       =	"I_Status_05_03";
	public static final String	ATT_I_STATUS_05_04                       =	"I_Status_05_04";
	public static final String	ATT_I_STATUS_05_05                       =	"I_Status_05_05";
	
	public static final String	ATT_I_TYPE_05_01                         =	"I_Type_05_01";
	public static final String	ATT_I_TYPE_05_02                         =	"I_Type_05_02";
	public static final String	ATT_I_TYPE_05_03                         =	"I_Type_05_03";
	public static final String	ATT_I_TYPE_05_04                         =	"I_Type_05_04";
	public static final String	ATT_I_TYPE_05_05                         =	"I_Type_05_05";
	
	public static final String	ATT_T_NAME_05_01                         =	"T_Name_05_01";
	public static final String	ATT_T_NAME_05_02                         =	"T_Name_05_02";
	public static final String	ATT_T_NAME_05_03                         =	"T_Name_05_03";
	
	public static final String	ATT_T_CODE_05_01                         =	"T_Code_05_01";
	public static final String	ATT_T_CODE_05_02                         =	"T_Code_05_02";
	public static final String	ATT_T_CODE_05_03                         =	"T_Code_05_03";
	public static final String	ATT_T_CODE_05_04                         =	"T_Code_05_04";
	public static final String	ATT_T_CODE_05_05                         =	"T_Code_05_05";
	
	public static final String	ATT_T_INFO_05_01                         =	"T_Info_05_01";
	public static final String	ATT_T_INFO_05_02                         =	"T_Info_05_02";
	public static final String	ATT_T_INFO_05_03                         =	"T_Info_05_03";
	public static final String	ATT_T_INFO_05_04                         =	"T_Info_05_04";
	public static final String	ATT_T_INFO_05_05                         =	"T_Info_05_05";
	public static final String	ATT_T_INFO_05_06                         =	"T_Info_05_06";
	public static final String	ATT_T_INFO_05_07                         =	"T_Info_05_07";
	public static final String	ATT_T_INFO_05_08                         =	"T_Info_05_08";
	public static final String	ATT_T_INFO_05_09                         =	"T_Info_05_09";
	public static final String	ATT_T_INFO_05_10                         =	"T_Info_05_10";
	
	public static final String	ATT_D_DATE_05_01                         =	"D_Date_05_01";
	public static final String	ATT_D_DATE_05_02                         =	"D_Date_05_02";
	public static final String	ATT_D_DATE_05_03                         =	"D_Date_05_03";
	public static final String	ATT_D_DATE_05_04                         =	"D_Date_05_04";
	public static final String	ATT_D_DATE_05_05                         =	"D_Date_05_05";
	

	public static final String	ATT_F_VAL_05_01                          =	"F_Val_05_01"; //nb app
	public static final String	ATT_F_VAL_05_02                          =	"F_Val_05_02";
	public static final String	ATT_F_VAL_05_03                          =	"F_Val_05_03"; 
	public static final String	ATT_F_VAL_05_04                          =	"F_Val_05_04";
	public static final String	ATT_F_VAL_05_05                          =	"F_Val_05_05";
	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<ViMatMaterialMulti> 	DAO;
	static	{
		DAO = new EntityDAO<ViMatMaterialMulti>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), ViMatMaterialMulti.class,RIGHTS, HISTORY, DefDBExt.TA_MAT_MATERIAL, DefDBExt.ID_TA_MAT_MATERIAL);
	}

	//-----------------------Class Attributs-------------------------
	@Id
	@Column(name=COL_I_ID_01, nullable = false)
	private	Integer         I_ID_01;
	@Id
	@Column(name=COL_I_ID_02, nullable = false)
	private	Integer         I_ID_02;
	@Id
	@Column(name=COL_I_ID_03, nullable = true)
	private	Integer         I_ID_03;
	
         
	@Column(name=COL_I_STATUS_01_01, nullable = true)
	private	Integer         I_Status_01_01;
	@Column(name=COL_I_STATUS_01_02, nullable = true)
	private	Integer         I_Status_01_02;
	@Column(name=COL_I_STATUS_01_03, nullable = true)
	private	Integer         I_Status_01_03;
	@Column(name=COL_I_STATUS_01_04, nullable = true)
	private	Integer         I_Status_01_04;
	@Column(name=COL_I_STATUS_01_05, nullable = true)
	private	Integer         I_Status_01_05;
     
	@Column(name=COL_I_TYPE_01_01, nullable = true)
	private	Integer         I_Type_01_01;
	@Column(name=COL_I_TYPE_01_02, nullable = true)
	private	Integer         I_Type_01_02;
	@Column(name=COL_I_TYPE_01_03, nullable = true)
	private	Integer         I_Type_01_03;
	@Column(name=COL_I_TYPE_01_04, nullable = true)
	private	Integer         I_Type_01_04;
	@Column(name=COL_I_TYPE_01_05, nullable = true)
	private	Integer         I_Type_01_05;
    
	@Column(name=COL_F_VAL_01_01, nullable = true)
	private	Double          F_Val_01_01;
	@Column(name=COL_F_VAL_01_02, nullable = true)
	private	Double          F_Val_01_02;
	@Column(name=COL_F_VAL_01_03, nullable = true)
	private	Double          F_Val_01_03;
	@Column(name=COL_F_VAL_01_04, nullable = true)
	private	Double          F_Val_01_04;
	@Column(name=COL_F_VAL_01_05, nullable = true)
	private	Double          F_Val_01_05;
    
	@Column(name=COL_D_DATE_01_01, nullable = true)
	private	Date            D_Date_01_01;
	@Column(name=COL_D_DATE_01_02, nullable = true)
	private	Date            D_Date_01_02;
	@Column(name=COL_D_DATE_01_03, nullable = true)
	private	Date            D_Date_01_03;
	@Column(name=COL_D_DATE_01_04, nullable = true)
	private	Date            D_Date_01_04;
	@Column(name=COL_D_DATE_01_05, nullable = true)
	private	Date            D_Date_01_05;
	
	@Column(name=COL_T_NAME_01_01, nullable = true)
	private	String          T_Name_01_01;
	@Column(name=COL_T_NAME_01_02, nullable = true)
	private	String          T_Name_01_02;
	@Column(name=COL_T_NAME_01_03, nullable = true)
	private	String          T_Name_01_03;
	
	@Column(name=COL_T_CODE_01_01, nullable = true)
	private	String          T_Code_01_01;
	@Column(name=COL_T_CODE_01_02, nullable = true)
	private	String          T_Code_01_02;
	@Column(name=COL_T_CODE_01_03, nullable = true)
	private	String          T_Code_01_03;
	@Column(name=COL_T_CODE_01_04, nullable = true)
	private	String          T_Code_01_04;
	@Column(name=COL_T_CODE_01_05, nullable = true)
	private	String          T_Code_01_05;
    
	
	
	@Column(name=COL_T_INFO_01_01, nullable = true)
	private	String          T_Info_01_01;
	@Column(name=COL_T_INFO_01_02, nullable = true)
	private	String          T_Info_01_02;
	@Column(name=COL_T_INFO_01_03, nullable = true)
	private	String          T_Info_01_03;
	@Column(name=COL_T_INFO_01_04, nullable = true)
	private	String          T_Info_01_04;
	@Column(name=COL_T_INFO_01_05, nullable = true)
	private	String          T_Info_01_05;
	@Column(name=COL_T_INFO_01_06, nullable = true)
	private	String          T_Info_01_06;
	@Column(name=COL_T_INFO_01_07, nullable = true)
	private	String          T_Info_01_07;
	@Column(name=COL_T_INFO_01_08, nullable = true)
	private	String          T_Info_01_08;
	@Column(name=COL_T_INFO_01_09, nullable = true)
	private	String          T_Info_01_09;
	@Column(name=COL_T_INFO_01_10, nullable = true)
	private	String          T_Info_01_10;
    
	//------------------------------------------------
	@Column(name=COL_I_STATUS_02_01, nullable = true)
	private	Integer         I_Status_02_01;
	@Column(name=COL_I_STATUS_02_02, nullable = true)
	private	Integer         I_Status_02_02;
	@Column(name=COL_I_STATUS_02_03, nullable = true)
	private	Integer         I_Status_02_03;
	@Column(name=COL_I_STATUS_02_04, nullable = true)
	private	Integer         I_Status_02_04;
	@Column(name=COL_I_STATUS_02_05, nullable = true)
	private	Integer         I_Status_02_05;
     
	@Column(name=COL_I_TYPE_02_01, nullable = true)
	private	Integer         I_Type_02_01;
	@Column(name=COL_I_TYPE_02_02, nullable = true)
	private	Integer         I_Type_02_02;
	@Column(name=COL_I_TYPE_02_03, nullable = true)
	private	Integer         I_Type_02_03;
	@Column(name=COL_I_TYPE_02_04, nullable = true)
	private	Integer         I_Type_02_04;
	@Column(name=COL_I_TYPE_02_05, nullable = true)
	private	Integer         I_Type_02_05;
    
	@Column(name=COL_F_VAL_02_01, nullable = true)
	private	Double          F_Val_02_01;
	@Column(name=COL_F_VAL_02_02, nullable = true)
	private	Double          F_Val_02_02;
	@Column(name=COL_F_VAL_02_03, nullable = true)
	private	Double          F_Val_02_03;
	@Column(name=COL_F_VAL_02_04, nullable = true)
	private	Double          F_Val_02_04;
	@Column(name=COL_F_VAL_02_05, nullable = true)
	private	Double          F_Val_02_05;
    
	@Column(name=COL_D_DATE_02_01, nullable = true)
	private	Date            D_Date_02_01;
	@Column(name=COL_D_DATE_02_02, nullable = true)
	private	Date            D_Date_02_02;
	@Column(name=COL_D_DATE_02_03, nullable = true)
	private	Date            D_Date_02_03;
	@Column(name=COL_D_DATE_02_04, nullable = true)
	private	Date            D_Date_02_04;
	@Column(name=COL_D_DATE_02_05, nullable = true)
	private	Date            D_Date_02_05;
	
	@Column(name=COL_T_NAME_02_01, nullable = true)
	private	String          T_Name_02_01;
	@Column(name=COL_T_NAME_02_02, nullable = true)
	private	String          T_Name_02_02;
	@Column(name=COL_T_NAME_02_03, nullable = true)
	private	String          T_Name_02_03;
	
	@Column(name=COL_T_CODE_02_01, nullable = true)
	private	String          T_Code_02_01;
	@Column(name=COL_T_CODE_02_02, nullable = true)
	private	String          T_Code_02_02;
	@Column(name=COL_T_CODE_02_03, nullable = true)
	private	String          T_Code_02_03;
	@Column(name=COL_T_CODE_02_04, nullable = true)
	private	String          T_Code_02_04;
	@Column(name=COL_T_CODE_02_05, nullable = true)
	private	String          T_Code_02_05;
    
	
	
	@Column(name=COL_T_INFO_02_01, nullable = true)
	private	String          T_Info_02_01;
	@Column(name=COL_T_INFO_02_02, nullable = true)
	private	String          T_Info_02_02;
	@Column(name=COL_T_INFO_02_03, nullable = true)
	private	String          T_Info_02_03;
	@Column(name=COL_T_INFO_02_04, nullable = true)
	private	String          T_Info_02_04;
	@Column(name=COL_T_INFO_02_05, nullable = true)
	private	String          T_Info_02_05;
	@Column(name=COL_T_INFO_02_06, nullable = true)
	private	String          T_Info_02_06;
	@Column(name=COL_T_INFO_02_07, nullable = true)
	private	String          T_Info_02_07;
	@Column(name=COL_T_INFO_02_08, nullable = true)
	private	String          T_Info_02_08;
	@Column(name=COL_T_INFO_02_09, nullable = true)
	private	String          T_Info_02_09;
	@Column(name=COL_T_INFO_02_10, nullable = true)
	private	String          T_Info_02_10;
    
	//------------------------------------------------
	@Column(name=COL_I_STATUS_03_01, nullable = true)
	private	Integer         I_Status_03_01;
	@Column(name=COL_I_STATUS_03_02, nullable = true)
	private	Integer         I_Status_03_02;
	@Column(name=COL_I_STATUS_03_03, nullable = true)
	private	Integer         I_Status_03_03;
	@Column(name=COL_I_STATUS_03_04, nullable = true)
	private	Integer         I_Status_03_04;
	@Column(name=COL_I_STATUS_03_05, nullable = true)
	private	Integer         I_Status_03_05;

	@Column(name=COL_I_TYPE_03_01, nullable = true)
	private	Integer         I_Type_03_01;
	@Column(name=COL_I_TYPE_03_02, nullable = true)
	private	Integer         I_Type_03_02;
	@Column(name=COL_I_TYPE_03_03, nullable = true)
	private	Integer         I_Type_03_03;
	@Column(name=COL_I_TYPE_03_04, nullable = true)
	private	Integer         I_Type_03_04;
	@Column(name=COL_I_TYPE_03_05, nullable = true)
	private	Integer         I_Type_03_05;

	@Column(name=COL_F_VAL_03_01, nullable = true)
	private	Double          F_Val_03_01;
	@Column(name=COL_F_VAL_03_02, nullable = true)
	private	Double          F_Val_03_02;
	@Column(name=COL_F_VAL_03_03, nullable = true)
	private	Double          F_Val_03_03;
	@Column(name=COL_F_VAL_03_04, nullable = true)
	private	Double          F_Val_03_04;
	@Column(name=COL_F_VAL_03_05, nullable = true)
	private	Double          F_Val_03_05;

	@Column(name=COL_D_DATE_03_01, nullable = true)
	private	Date            D_Date_03_01;
	@Column(name=COL_D_DATE_03_02, nullable = true)
	private	Date            D_Date_03_02;
	@Column(name=COL_D_DATE_03_03, nullable = true)
	private	Date            D_Date_03_03;
	@Column(name=COL_D_DATE_03_04, nullable = true)
	private	Date            D_Date_03_04;
	@Column(name=COL_D_DATE_03_05, nullable = true)
	private	Date            D_Date_03_05;

	@Column(name=COL_T_NAME_03_01, nullable = true)
	private	String          T_Name_03_01;
	@Column(name=COL_T_NAME_03_02, nullable = true)
	private	String          T_Name_03_02;
	@Column(name=COL_T_NAME_03_03, nullable = true)
	private	String          T_Name_03_03;

	@Column(name=COL_T_CODE_03_01, nullable = true)
	private	String          T_Code_03_01;
	@Column(name=COL_T_CODE_03_02, nullable = true)
	private	String          T_Code_03_02;
	@Column(name=COL_T_CODE_03_03, nullable = true)
	private	String          T_Code_03_03;
	@Column(name=COL_T_CODE_03_04, nullable = true)
	private	String          T_Code_03_04;
	@Column(name=COL_T_CODE_03_05, nullable = true)
	private	String          T_Code_03_05;



	@Column(name=COL_T_INFO_03_01, nullable = true)
	private	String          T_Info_03_01;
	@Column(name=COL_T_INFO_03_02, nullable = true)
	private	String          T_Info_03_02;
	@Column(name=COL_T_INFO_03_03, nullable = true)
	private	String          T_Info_03_03;
	@Column(name=COL_T_INFO_03_04, nullable = true)
	private	String          T_Info_03_04;
	@Column(name=COL_T_INFO_03_05, nullable = true)
	private	String          T_Info_03_05;
	@Column(name=COL_T_INFO_03_06, nullable = true)
	private	String          T_Info_03_06;
	@Column(name=COL_T_INFO_03_07, nullable = true)
	private	String          T_Info_03_07;
	@Column(name=COL_T_INFO_03_08, nullable = true)
	private	String          T_Info_03_08;
	@Column(name=COL_T_INFO_03_09, nullable = true)
	private	String          T_Info_03_09;
	@Column(name=COL_T_INFO_03_10, nullable = true)
	private	String          T_Info_03_10;
	
	//-------------------------------------------------------------------
	@Id
	@Column(name=COL_I_ID_04, nullable = true)
	private	Integer         I_ID_04;
	@Id
	@Column(name=COL_I_ID_05, nullable = true)
	private	Integer         I_ID_05;
	
	@Column(name=COL_I_STATUS_04_01, nullable = true)
	private	Integer         I_Status_04_01;
	@Column(name=COL_I_STATUS_04_02, nullable = true)
	private	Integer         I_Status_04_02;
	@Column(name=COL_I_STATUS_04_03, nullable = true)
	private	Integer         I_Status_04_03;
	@Column(name=COL_I_STATUS_04_04, nullable = true)
	private	Integer         I_Status_04_04;
	@Column(name=COL_I_STATUS_04_05, nullable = true)
	private	Integer         I_Status_04_05;

	@Column(name=COL_I_TYPE_04_01, nullable = true)
	private	Integer         I_Type_04_01;
	@Column(name=COL_I_TYPE_04_02, nullable = true)
	private	Integer         I_Type_04_02;
	@Column(name=COL_I_TYPE_04_03, nullable = true)
	private	Integer         I_Type_04_03;
	@Column(name=COL_I_TYPE_04_04, nullable = true)
	private	Integer         I_Type_04_04;
	@Column(name=COL_I_TYPE_04_05, nullable = true)
	private	Integer         I_Type_04_05;

	@Column(name=COL_F_VAL_04_01, nullable = true)
	private	Double          F_Val_04_01;
	@Column(name=COL_F_VAL_04_02, nullable = true)
	private	Double          F_Val_04_02;
	@Column(name=COL_F_VAL_04_03, nullable = true)
	private	Double          F_Val_04_03;
	@Column(name=COL_F_VAL_04_04, nullable = true)
	private	Double          F_Val_04_04;
	@Column(name=COL_F_VAL_04_05, nullable = true)
	private	Double          F_Val_04_05;

	@Column(name=COL_D_DATE_04_01, nullable = true)
	private	Date            D_Date_04_01;
	@Column(name=COL_D_DATE_04_02, nullable = true)
	private	Date            D_Date_04_02;
	@Column(name=COL_D_DATE_04_03, nullable = true)
	private	Date            D_Date_04_03;
	@Column(name=COL_D_DATE_04_04, nullable = true)
	private	Date            D_Date_04_04;
	@Column(name=COL_D_DATE_04_05, nullable = true)
	private	Date            D_Date_04_05;

	@Column(name=COL_T_NAME_04_01, nullable = true)
	private	String          T_Name_04_01;
	@Column(name=COL_T_NAME_04_02, nullable = true)
	private	String          T_Name_04_02;
	@Column(name=COL_T_NAME_04_03, nullable = true)
	private	String          T_Name_04_03;

	@Column(name=COL_T_CODE_04_01, nullable = true)
	private	String          T_Code_04_01;
	@Column(name=COL_T_CODE_04_02, nullable = true)
	private	String          T_Code_04_02;
	@Column(name=COL_T_CODE_04_03, nullable = true)
	private	String          T_Code_04_03;
	@Column(name=COL_T_CODE_04_04, nullable = true)
	private	String          T_Code_04_04;
	@Column(name=COL_T_CODE_04_05, nullable = true)
	private	String          T_Code_04_05;



	@Column(name=COL_T_INFO_04_01, nullable = true)
	private	String          T_Info_04_01;
	@Column(name=COL_T_INFO_04_02, nullable = true)
	private	String          T_Info_04_02;
	@Column(name=COL_T_INFO_04_03, nullable = true)
	private	String          T_Info_04_03;
	@Column(name=COL_T_INFO_04_04, nullable = true)
	private	String          T_Info_04_04;
	@Column(name=COL_T_INFO_04_05, nullable = true)
	private	String          T_Info_04_05;
	@Column(name=COL_T_INFO_04_06, nullable = true)
	private	String          T_Info_04_06;
	@Column(name=COL_T_INFO_04_07, nullable = true)
	private	String          T_Info_04_07;
	@Column(name=COL_T_INFO_04_08, nullable = true)
	private	String          T_Info_04_08;
	@Column(name=COL_T_INFO_04_09, nullable = true)
	private	String          T_Info_04_09;
	@Column(name=COL_T_INFO_04_10, nullable = true)
	private	String          T_Info_04_10;
	
	
	@Column(name=COL_I_STATUS_05_01, nullable = true)
	private	Integer         I_Status_05_01;
	@Column(name=COL_I_STATUS_05_02, nullable = true)
	private	Integer         I_Status_05_02;
	@Column(name=COL_I_STATUS_05_03, nullable = true)
	private	Integer         I_Status_05_03;
	@Column(name=COL_I_STATUS_05_04, nullable = true)
	private	Integer         I_Status_05_04;
	@Column(name=COL_I_STATUS_05_05, nullable = true)
	private	Integer         I_Status_05_05;

	@Column(name=COL_I_TYPE_05_01, nullable = true)
	private	Integer         I_Type_05_01;
	@Column(name=COL_I_TYPE_05_02, nullable = true)
	private	Integer         I_Type_05_02;
	@Column(name=COL_I_TYPE_05_03, nullable = true)
	private	Integer         I_Type_05_03;
	@Column(name=COL_I_TYPE_05_04, nullable = true)
	private	Integer         I_Type_05_04;
	@Column(name=COL_I_TYPE_05_05, nullable = true)
	private	Integer         I_Type_05_05;

	@Column(name=COL_F_VAL_05_01, nullable = true)
	private	Double          F_Val_05_01;
	@Column(name=COL_F_VAL_05_02, nullable = true)
	private	Double          F_Val_05_02;
	@Column(name=COL_F_VAL_05_03, nullable = true)
	private	Double          F_Val_05_03;
	@Column(name=COL_F_VAL_05_04, nullable = true)
	private	Double          F_Val_05_04;
	@Column(name=COL_F_VAL_05_05, nullable = true)
	private	Double          F_Val_05_05;

	@Column(name=COL_D_DATE_05_01, nullable = true)
	private	Date            D_Date_05_01;
	@Column(name=COL_D_DATE_05_02, nullable = true)
	private	Date            D_Date_05_02;
	@Column(name=COL_D_DATE_05_03, nullable = true)
	private	Date            D_Date_05_03;
	@Column(name=COL_D_DATE_05_04, nullable = true)
	private	Date            D_Date_05_04;
	@Column(name=COL_D_DATE_05_05, nullable = true)
	private	Date            D_Date_05_05;

	@Column(name=COL_T_NAME_05_01, nullable = true)
	private	String          T_Name_05_01;
	@Column(name=COL_T_NAME_05_02, nullable = true)
	private	String          T_Name_05_02;
	@Column(name=COL_T_NAME_05_03, nullable = true)
	private	String          T_Name_05_03;

	@Column(name=COL_T_CODE_05_01, nullable = true)
	private	String          T_Code_05_01;
	@Column(name=COL_T_CODE_05_02, nullable = true)
	private	String          T_Code_05_02;
	@Column(name=COL_T_CODE_05_03, nullable = true)
	private	String          T_Code_05_03;
	@Column(name=COL_T_CODE_05_04, nullable = true)
	private	String          T_Code_05_04;
	@Column(name=COL_T_CODE_05_05, nullable = true)
	private	String          T_Code_05_05;



	@Column(name=COL_T_INFO_05_01, nullable = true)
	private	String          T_Info_05_01;
	@Column(name=COL_T_INFO_05_02, nullable = true)
	private	String          T_Info_05_02;
	@Column(name=COL_T_INFO_05_03, nullable = true)
	private	String          T_Info_05_03;
	@Column(name=COL_T_INFO_05_04, nullable = true)
	private	String          T_Info_05_04;
	@Column(name=COL_T_INFO_05_05, nullable = true)
	private	String          T_Info_05_05;
	@Column(name=COL_T_INFO_05_06, nullable = true)
	private	String          T_Info_05_06;
	@Column(name=COL_T_INFO_05_07, nullable = true)
	private	String          T_Info_05_07;
	@Column(name=COL_T_INFO_05_08, nullable = true)
	private	String          T_Info_05_08;
	@Column(name=COL_T_INFO_05_09, nullable = true)
	private	String          T_Info_05_09;
	@Column(name=COL_T_INFO_05_10, nullable = true)
	private	String          T_Info_05_10;
	//-----------------------Transient Variables-------------------------
	
	//---------------------Constructeurs-----------------------
	public ViMatMaterialMulti(){}

	public ViMatMaterialMulti(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		//doInitDBFlag();
	}

	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID_01;

	}

	@Override
	public void doMergeWith(ViMatMaterialMulti ent) {
		if (ent == this) return;
		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;

		ok = (I_ID_01 == ((ViMatMaterialMulti)o).I_ID_01) && (I_ID_02 == ((ViMatMaterialMulti)o).I_ID_02) && (I_ID_03 == ((ViMatMaterialMulti)o).I_ID_03);
		if (!ok) return ok;


		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID_01*100000+I_ID_02;
	}

	public static String reqSelectSqlMat (int id, boolean isnull) {
		String objAlias 	= " m"+id+".";
		String strId		= "0"+id;
		String selectSQL 	=  "";
		if (!isnull) selectSQL 	= 
				  objAlias + TaMatMaterial.COL_I_ID 			+ " " + String.format(ViMatMaterialMulti.COL_I_ID_M, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_STATUS_01 		+ " " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_01, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_STATUS_02 		+ " " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_02, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_STATUS_03 		+ " " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_03, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_STATUS_04 		+ " " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_04, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_STATUS_05 		+ " " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_05, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_TYPE_01 		+ " " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_01, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_TYPE_02		+ " " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_02, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_TYPE_03 		+ " " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_03, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_TYPE_04		+ " " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_04, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_I_TYPE_05 		+ " " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_05, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_NAME_01 		+ " " + String.format(ViMatMaterialMulti.COL_T_NAME_M_01, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_NAME_02 		+ " " + String.format(ViMatMaterialMulti.COL_T_NAME_M_02, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_NAME_03 		+ " " + String.format(ViMatMaterialMulti.COL_T_NAME_M_03, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_CODE_01 		+ " " + String.format(ViMatMaterialMulti.COL_T_CODE_M_01, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_CODE_02 		+ " " + String.format(ViMatMaterialMulti.COL_T_CODE_M_02, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_CODE_03 		+ " " + String.format(ViMatMaterialMulti.COL_T_CODE_M_03, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_CODE_04 		+ " " + String.format(ViMatMaterialMulti.COL_T_CODE_M_04, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_CODE_05 		+ " " + String.format(ViMatMaterialMulti.COL_T_CODE_M_05, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_01 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_01, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_02 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_02, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_03 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_03, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_04 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_04, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_05 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_05, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_06 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_06, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_07 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_07, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_08 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_08, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_09 		+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_09, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_T_INFO_10	 	+ " " + String.format(ViMatMaterialMulti.COL_T_INFO_M_10, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_D_DATE_01 		+ " " + String.format(ViMatMaterialMulti.COL_D_DATE_M_01, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_D_DATE_02 		+ " " + String.format(ViMatMaterialMulti.COL_D_DATE_M_02, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_D_DATE_03 		+ " " + String.format(ViMatMaterialMulti.COL_D_DATE_M_03, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_D_DATE_04 		+ " " + String.format(ViMatMaterialMulti.COL_D_DATE_M_04, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_D_DATE_05 		+ " " + String.format(ViMatMaterialMulti.COL_D_DATE_M_05, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_F_VAL_01 		+ " " + String.format(ViMatMaterialMulti.COL_F_VAL_M_01, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_F_VAL_02 		+ " " + String.format(ViMatMaterialMulti.COL_F_VAL_M_02, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_F_VAL_03 		+ " " + String.format(ViMatMaterialMulti.COL_F_VAL_M_03, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_F_VAL_04 		+ " " + String.format(ViMatMaterialMulti.COL_F_VAL_M_04, strId)  + ", " 
				+ objAlias + TaMatMaterial.COL_F_VAL_05 		+ " " + String.format(ViMatMaterialMulti.COL_F_VAL_M_05, strId)  ;
		
		else selectSQL 	= 
				  " null " + String.format(ViMatMaterialMulti.COL_I_ID_M, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_01, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_02, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_03, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_04, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_STATUS_M_05, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_01, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_02, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_03, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_04, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_I_TYPE_M_05, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_NAME_M_01, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_NAME_M_02, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_NAME_M_03, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_CODE_M_01, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_CODE_M_02, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_CODE_M_03, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_CODE_M_04, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_CODE_M_05, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_01, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_02, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_03, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_04, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_05, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_06, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_07, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_08, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_09, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_T_INFO_M_10, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_D_DATE_M_01, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_D_DATE_M_02, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_D_DATE_M_03, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_D_DATE_M_04, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_D_DATE_M_05, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_F_VAL_M_01, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_F_VAL_M_02, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_F_VAL_M_03, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_F_VAL_M_04, strId)  + ", " 
				+ " null " + String.format(ViMatMaterialMulti.COL_F_VAL_M_05, strId)  ;
		
		return selectSQL;
	}
	
	private static Hashtable<String,String> mapCol = new Hashtable<String, String>(){
		{
			put(TaMatMaterial.COL_I_ID , ViMatMaterialMulti.COL_I_ID_M);
			put(TaMatMaterial.COL_I_STATUS_01 , ViMatMaterialMulti.COL_I_STATUS_M_01); 
			put(TaMatMaterial.COL_I_STATUS_02 , ViMatMaterialMulti.COL_I_STATUS_M_02); 
			put(TaMatMaterial.COL_I_STATUS_03 , ViMatMaterialMulti.COL_I_STATUS_M_03); 
			put(TaMatMaterial.COL_I_STATUS_04 , ViMatMaterialMulti.COL_I_STATUS_M_04); 
			put(TaMatMaterial.COL_I_STATUS_05 , ViMatMaterialMulti.COL_I_STATUS_M_05); 
			put(TaMatMaterial.COL_I_TYPE_01 , ViMatMaterialMulti.COL_I_TYPE_M_01); 
			put(TaMatMaterial.COL_I_TYPE_02 , ViMatMaterialMulti.COL_I_TYPE_M_02); 
			put(TaMatMaterial.COL_I_TYPE_03 , ViMatMaterialMulti.COL_I_TYPE_M_03); 
			put(TaMatMaterial.COL_I_TYPE_04, ViMatMaterialMulti.COL_I_TYPE_M_04); 
			put(TaMatMaterial.COL_I_TYPE_05 , ViMatMaterialMulti.COL_I_TYPE_M_05); 
			put(TaMatMaterial.COL_T_NAME_01 , ViMatMaterialMulti.COL_T_NAME_M_01); 
			put(TaMatMaterial.COL_T_NAME_02 , ViMatMaterialMulti.COL_T_NAME_M_02); 
			put(TaMatMaterial.COL_T_NAME_03 , ViMatMaterialMulti.COL_T_NAME_M_03); 
			put(TaMatMaterial.COL_T_CODE_01 , ViMatMaterialMulti.COL_T_CODE_M_01); 
			put(TaMatMaterial.COL_T_CODE_02 , ViMatMaterialMulti.COL_T_CODE_M_02); 
			put(TaMatMaterial.COL_T_CODE_03 , ViMatMaterialMulti.COL_T_CODE_M_03); 
			put(TaMatMaterial.COL_T_CODE_04 , ViMatMaterialMulti.COL_T_CODE_M_04); 
			put(TaMatMaterial.COL_T_CODE_05 , ViMatMaterialMulti.COL_T_CODE_M_05); 
			put(TaMatMaterial.COL_T_INFO_01 , ViMatMaterialMulti.COL_T_INFO_M_01); 
			put(TaMatMaterial.COL_T_INFO_02 , ViMatMaterialMulti.COL_T_INFO_M_02); 
			put(TaMatMaterial.COL_T_INFO_03 , ViMatMaterialMulti.COL_T_INFO_M_03); 
			put(TaMatMaterial.COL_T_INFO_04 , ViMatMaterialMulti.COL_T_INFO_M_04); 
			put(TaMatMaterial.COL_T_INFO_05 , ViMatMaterialMulti.COL_T_INFO_M_05); 
			put(TaMatMaterial.COL_T_INFO_06 , ViMatMaterialMulti.COL_T_INFO_M_06); 
			put(TaMatMaterial.COL_T_INFO_07 , ViMatMaterialMulti.COL_T_INFO_M_07); 
			put(TaMatMaterial.COL_T_INFO_08 , ViMatMaterialMulti.COL_T_INFO_M_08); 
			put(TaMatMaterial.COL_T_INFO_09 , ViMatMaterialMulti.COL_T_INFO_M_09); 
			put(TaMatMaterial.COL_T_INFO_10	, ViMatMaterialMulti.COL_T_INFO_M_10); 
			put(TaMatMaterial.COL_D_DATE_01 , ViMatMaterialMulti.COL_D_DATE_M_01); 
			put(TaMatMaterial.COL_D_DATE_02 , ViMatMaterialMulti.COL_D_DATE_M_02); 
			put(TaMatMaterial.COL_D_DATE_03 , ViMatMaterialMulti.COL_D_DATE_M_03); 
			put(TaMatMaterial.COL_D_DATE_04 , ViMatMaterialMulti.COL_D_DATE_M_04); 
			put(TaMatMaterial.COL_D_DATE_05 , ViMatMaterialMulti.COL_D_DATE_M_05); 
			put(TaMatMaterial.COL_F_VAL_01 , ViMatMaterialMulti.COL_F_VAL_M_01); 
			put(TaMatMaterial.COL_F_VAL_02 , ViMatMaterialMulti.COL_F_VAL_M_02); 
			put(TaMatMaterial.COL_F_VAL_03 , ViMatMaterialMulti.COL_F_VAL_M_03); 
			put(TaMatMaterial.COL_F_VAL_04 , ViMatMaterialMulti.COL_F_VAL_M_04); 
			put(TaMatMaterial.COL_F_VAL_05 , ViMatMaterialMulti.COL_F_VAL_M_05)  ;
		}
	};
	
	public static String reqSelectSqlMat (int id, String...colNames) {
		String objAlias 	= " m"+id+".";
		String strId		= "0"+id;
		String selectSQL 	=  "";
		
		Set<String> colSet = new HashSet<String>();
		colSet.addAll(mapCol.keySet());
		
		for (String col : colNames) {
			String colView = mapCol.get(col);
			if (colView==null) continue;
			selectSQL 	= selectSQL + objAlias + col + " " + String.format(colView, strId)  + ", ";
			colSet.remove(col);
		}
		
		for (String col : colSet) {
			String colView = mapCol.get(col);
			selectSQL 	=  selectSQL +  " null " + String.format(colView, strId)  + ", " ;
		}
		
		return selectSQL.substring(0, selectSQL.length()-2);
	}
}
