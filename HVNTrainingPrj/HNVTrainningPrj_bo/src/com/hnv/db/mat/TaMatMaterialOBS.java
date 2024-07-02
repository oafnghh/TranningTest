package com.hnv.db.mat;

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
import com.hnv.db.per.TaPerPerson;
import com.hnv.db.tpy.TaTpyCategory;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.def.DefDBExt;	


/**
 * TaMatMaterial by H&V SAS
 */
@Entity
@Table(name = DefDBExt.TA_MAT_MATERIAL_OBS )
public class TaMatMaterialOBS extends EntityAbstract<TaMatMaterialOBS> {

	private static final long serialVersionUID = 1L;
	
	private static final int  ENT_TYP			= DefDBExt.ID_TA_MAT_MATERIAL_OBS;
	
	public static final int	TYPE_01_CODE 		= 1;
	
	public static final int	TYPE_01_APP 		= 10;
	public static final int	TYPE_01_ENV 		= 30;
	
	public static final int	TYPE_01_NOD 		= 50;
	public static final int	TYPE_01_NOD_DEV 	= 51;
	public static final int	TYPE_01_NOD_PROD	= 52;
	
	public static final int	TYPE_01_DBS			= 60; //DB Service : Post
	public static final int	TYPE_01_KUN 		= 70; //Kubemets namespace
	
	public static final int	TYPE_01_SA 			= 80; //Service APP
	public static final int	TYPE_01_CA 			= 100; //Compo	APP
	
	
	
	public static final int	TYPE_01_SAN 		= 1100;
	public static final int	TYPE_01_ADD_IP 		= 1200;
	public static final int	TYPE_01_TINA 		= 1300;
	public static final int	TYPE_01_ESX4_VM 	= 1500;
	

	
	//---------------------------------------------------------------------
	public static final int	TYPE_02_NOD_DD      = 50100; //sv dedicated
	public static final int	TYPE_02_NOD_VM 	    = 50200; //sv vm
	public static final int	TYPE_02_NOD_SC 	    = 50300; //sv consolidated
	
	public static final int	TYPE_03_NOD_LIN     = 50100; //linux
	public static final int	TYPE_03_NOD_WIN 	= 50200; //win
	public static final int	TYPE_03_NOD_VMW 	= 50300; //vmware
	
	public static final int	TYPE_04_NOD_32     	= 32; //archi
	public static final int	TYPE_04_NOD_64 		= 64; 
	
	public static final int	TYPE_05_NOD_GEO 	= 50100; //serveur geo
	//---------------------------------------------------------------------
	public static final int	TYPE_02_APP_NEW     = 10101; //new
	public static final int	TYPE_02_APP_EVO     = 10102; //evolution
	public static final int	TYPE_02_APP_MIG     = 10103; //migration tech
	public static final int	TYPE_02_APP_SPEC    = 10110; //no env
	public static final int	TYPE_02_APP_NO_ENV  = 10110; //app non envi
	
	public static final int	TYPE_03_APP_SAAS    = 10101; //SAAS
	//---------------------------------------------------------------------
	public static final int	TYPE_02_ENV_A     	= 30101; //A
	public static final int	TYPE_02_ENV_B     	= 30102; //B
	public static final int	TYPE_02_ENV_C     	= 30103; //C
	public static final int	TYPE_02_ENV_D     	= 30104; //D
	
	//---------------------------------------------------------------------
	public static final int	TYPE_02_CA_OTH      = 100100; //other
	public static final int	TYPE_02_CA_DB      	= 100200; //database
	public static final int	TYPE_02_CA_PRO      = 100300; //pro-software
	public static final int	TYPE_02_CA_WS       = 100400; //web service
	public static final int	TYPE_02_CA_AS       = 100500; //app server
	public static final int	TYPE_02_CA_AI       = 100600; //app instance
	
	
	//---------------------------------------------------------------------
	public static final int	STAT_01_UNDEF 		= -1;
	public static final int	STAT_01_NEW 		= 0;  //en attente
	public static final int	STAT_01_ACTIVE 		= 1; 
	public static final int	STAT_01_ACTIVE_02	= 2; //réformé
	public static final int	STAT_01_ACTIVE_03	= 3; //réservé 
	public static final int	STAT_01_REVIEW 		= 5; //en cours de réaffectation
	public static final int	STAT_01_DELETED 	= 10;
	
	public static final int	STAT_UNDEF 			= -1;
	public static final int	STAT_INACTIVE 		= 0;
	public static final int	STAT_ACTIVE 		= 1;
	 
	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	
	public static final String	COL_I_STATUS_01                       =	"I_Status_01";
	public static final String	COL_I_STATUS_02                       =	"I_Status_02";
	public static final String	COL_I_STATUS_03                       =	"I_Status_03";
	public static final String	COL_I_STATUS_04                       =	"I_Status_04";
	public static final String	COL_I_STATUS_05                       =	"I_Status_05";
	
	public static final String	COL_I_TYPE_01                         =	"I_Type_01";
	public static final String	COL_I_TYPE_02                         =	"I_Type_02";
	public static final String	COL_I_TYPE_03                         =	"I_Type_03";
	public static final String	COL_I_TYPE_04                         =	"I_Type_04";
	public static final String	COL_I_TYPE_05                         =	"I_Type_05";
	
	public static final String	COL_T_NAME_01                         =	"T_Name_01";
	public static final String	COL_T_NAME_02                         =	"T_Name_02";
	public static final String	COL_T_NAME_03                         =	"T_Name_03";
	
	public static final String	COL_T_CODE_01                         =	"T_Code_01";
	public static final String	COL_T_CODE_02                         =	"T_Code_02";
	public static final String	COL_T_CODE_03                         =	"T_Code_03";
	public static final String	COL_T_CODE_04                         =	"T_Code_04";
	public static final String	COL_T_CODE_05                         =	"T_Code_05";
	
	public static final String	COL_T_INFO_01                         =	"T_Info_01";
	public static final String	COL_T_INFO_02                         =	"T_Info_02";
	public static final String	COL_T_INFO_03                         =	"T_Info_03";
	public static final String	COL_T_INFO_04                         =	"T_Info_04";
	public static final String	COL_T_INFO_05                         =	"T_Info_05";
	public static final String	COL_T_INFO_06                         =	"T_Info_06";
	public static final String	COL_T_INFO_07                         =	"T_Info_07";
	public static final String	COL_T_INFO_08                         =	"T_Info_08";
	public static final String	COL_T_INFO_09                         =	"T_Info_09";
	public static final String	COL_T_INFO_10                         =	"T_Info_10";
	
	public static final String	COL_D_DATE_01                         =	"D_Date_01";
	public static final String	COL_D_DATE_02                         =	"D_Date_02";
	public static final String	COL_D_DATE_03                         =	"D_Date_03";
	public static final String	COL_D_DATE_04                         =	"D_Date_04";
	public static final String	COL_D_DATE_05                         =	"D_Date_05";
	
	public static final String	COL_I_AUT_USER_01                     =	"I_Aut_User_01";
	public static final String	COL_I_AUT_USER_02                     =	"I_Aut_User_02";
	public static final String	COL_I_PER_MANAGER                     =	"I_Per_Manager";
	public static final String	COL_I_PER_PERSON_01                   =	"I_Per_Person_01";
	public static final String	COL_I_PER_PERSON_02                   =	"I_Per_Person_02";


	public static final String	COL_F_VAL_01                          =	"F_Val_01"; //nb app
	public static final String	COL_F_VAL_02                          =	"F_Val_02";
	public static final String	COL_F_VAL_03                          =	"F_Val_03"; 
	public static final String	COL_F_VAL_04                          =	"F_Val_04";
	public static final String	COL_F_VAL_05                          =	"F_Val_05";
	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_I_STATUS_01                       =	"I_Status_01";
	public static final String	ATT_I_STATUS_02                       =	"I_Status_02";
	public static final String	ATT_I_STATUS_03                       =	"I_Status_03";
	public static final String	ATT_I_STATUS_04                       =	"I_Status_04";
	public static final String	ATT_I_STATUS_05                       =	"I_Status_05";
	
	public static final String	ATT_I_TYPE_01                         =	"I_Type_01";
	public static final String	ATT_I_TYPE_02                         =	"I_Type_02";
	public static final String	ATT_I_TYPE_03                         =	"I_Type_03";
	public static final String	ATT_I_TYPE_04                         =	"I_Type_04";
	public static final String	ATT_I_TYPE_05                         =	"I_Type_05";
	
	public static final String	ATT_T_NAME_01                         =	"T_Name_01";
	public static final String	ATT_T_NAME_02                         =	"T_Name_02";
	public static final String	ATT_T_NAME_03                         =	"T_Name_03";
	
	public static final String	ATT_T_CODE_01                         =	"T_Code_01";
	public static final String	ATT_T_CODE_02                         =	"T_Code_02";
	public static final String	ATT_T_CODE_03                         =	"T_Code_03";
	public static final String	ATT_T_CODE_04                         =	"T_Code_04";
	public static final String	ATT_T_CODE_05                         =	"T_Code_05";
	
	public static final String	ATT_T_INFO_01                         =	"T_Info_01";
	public static final String	ATT_T_INFO_02                         =	"T_Info_02";
	public static final String	ATT_T_INFO_03                         =	"T_Info_03";
	public static final String	ATT_T_INFO_04                         =	"T_Info_04";
	public static final String	ATT_T_INFO_05                         =	"T_Info_05";
	public static final String	ATT_T_INFO_06                         =	"T_Info_06";
	public static final String	ATT_T_INFO_07                         =	"T_Info_07";
	public static final String	ATT_T_INFO_08                         =	"T_Info_08";
	public static final String	ATT_T_INFO_09                         =	"T_Info_09";
	public static final String	ATT_T_INFO_10                         =	"T_Info_10";
	
	public static final String	ATT_D_DATE_01                         =	"D_Date_01";
	public static final String	ATT_D_DATE_02                         =	"D_Date_02";
	public static final String	ATT_D_DATE_03                         =	"D_Date_03";
	public static final String	ATT_D_DATE_04                         =	"D_Date_04";
	public static final String	ATT_D_DATE_05                         =	"D_Date_05";
	
	public static final String	ATT_I_AUT_USER_01                     =	"I_Aut_User_01";
	public static final String	ATT_I_AUT_USER_02                     =	"I_Aut_User_02";
	public static final String	ATT_I_PER_MANAGER                     =	"I_Per_Manager";
	public static final String	ATT_I_PER_PERSON_01                   =	"I_Per_Person_01";
	public static final String	ATT_I_PER_PERSON_02                   =	"I_Per_Person_02";

	public static final String	ATT_F_VAL_01                          =	"F_Val_01"; //nb app
	public static final String	ATT_F_VAL_02                          =	"F_Val_02";
	public static final String	ATT_F_VAL_03                          =	"F_Val_03"; 
	public static final String	ATT_F_VAL_04                          =	"F_Val_04";
	public static final String	ATT_F_VAL_05                          =	"F_Val_05";
	//----------------------------------------------------------------------------------------------
	public static final String	ATT_O_PRODUCER                   	  =	"O_Producer";
	public static final String	ATT_O_MANAGER                   	  =	"O_Manager";
	
	public static final String	ATT_O_DOCUMENTS                       =	"O_Documents";
	public static final String	ATT_O_CATS                      	  =	"O_Cats";
	public static final String	ATT_O_CATS_OBJ                     	  =	"O_Cats_Obj";
	
	public static final String	ATT_O_CHILDREN                        =	"O_Children";
	public static final String	ATT_O_PARENTS                     	  =	"O_Parents";
	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<TaMatMaterialOBS> 	DAO;
	static	{
		DAO = new EntityDAO<TaMatMaterialOBS>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaMatMaterialOBS.class,RIGHTS, HISTORY, DefDBExt.TA_MAT_MATERIAL, DefDBExt.ID_TA_MAT_MATERIAL);
	}

	//-----------------------Class Attributs-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
         
	@Column(name=COL_I_STATUS_01, nullable = true)
	private	Integer         I_Status_01;
	@Column(name=COL_I_STATUS_02, nullable = true)
	private	Integer         I_Status_02;
	@Column(name=COL_I_STATUS_03, nullable = true)
	private	Integer         I_Status_03;
	@Column(name=COL_I_STATUS_04, nullable = true)
	private	Integer         I_Status_04;
	@Column(name=COL_I_STATUS_05, nullable = true)
	private	Integer         I_Status_05;
     
	@Column(name=COL_I_TYPE_01, nullable = true)
	private	Integer         I_Type_01;
	@Column(name=COL_I_TYPE_02, nullable = true)
	private	Integer         I_Type_02;
	@Column(name=COL_I_TYPE_03, nullable = true)
	private	Integer         I_Type_03;
	@Column(name=COL_I_TYPE_04, nullable = true)
	private	Integer         I_Type_04;
	@Column(name=COL_I_TYPE_05, nullable = true)
	private	Integer         I_Type_05;
    
	@Column(name=COL_F_VAL_01, nullable = true)
	private	Double          F_Val_01;
	@Column(name=COL_F_VAL_02, nullable = true)
	private	Double          F_Val_02;
	@Column(name=COL_F_VAL_03, nullable = true)
	private	Double          F_Val_03;
	@Column(name=COL_F_VAL_04, nullable = true)
	private	Double          F_Val_04;
	@Column(name=COL_F_VAL_05, nullable = true)
	private	Double          F_Val_05;
    
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
	
	@Column(name=COL_T_NAME_01, nullable = true)
	private	String          T_Name_01;
	@Column(name=COL_T_NAME_02, nullable = true)
	private	String          T_Name_02;
	@Column(name=COL_T_NAME_03, nullable = true)
	private	String          T_Name_03;
	
	@Column(name=COL_T_CODE_01, nullable = true)
	private	String          T_Code_01;
	@Column(name=COL_T_CODE_02, nullable = true)
	private	String          T_Code_02;
	@Column(name=COL_T_CODE_03, nullable = true)
	private	String          T_Code_03;
	@Column(name=COL_T_CODE_04, nullable = true)
	private	String          T_Code_04;
	@Column(name=COL_T_CODE_05, nullable = true)
	private	String          T_Code_05;
    
	
	
	@Column(name=COL_T_INFO_01, nullable = true)
	private	String          T_Info_01;
	@Column(name=COL_T_INFO_02, nullable = true)
	private	String          T_Info_02;
	@Column(name=COL_T_INFO_03, nullable = true)
	private	String          T_Info_03;
	@Column(name=COL_T_INFO_04, nullable = true)
	private	String          T_Info_04;
	@Column(name=COL_T_INFO_05, nullable = true)
	private	String          T_Info_05;
	@Column(name=COL_T_INFO_06, nullable = true)
	private	String          T_Info_06;
	@Column(name=COL_T_INFO_07, nullable = true)
	private	String          T_Info_07;
	@Column(name=COL_T_INFO_08, nullable = true)
	private	String          T_Info_08;
	@Column(name=COL_T_INFO_09, nullable = true)
	private	String          T_Info_09;
	@Column(name=COL_T_INFO_10, nullable = true)
	private	String          T_Info_10;
    
	
    
	@Column(name=COL_I_AUT_USER_01, nullable = true)
	private	Integer         I_Aut_User_01;
	@Column(name=COL_I_AUT_USER_02, nullable = true)
	private	Integer         I_Aut_User_02;

	@Column(name=COL_I_PER_MANAGER, nullable = true)
	private	Integer         I_Per_Manager;
	@Column(name=COL_I_PER_PERSON_01, nullable = true)
	private	Integer         I_Per_Person_01;
	@Column(name=COL_I_PER_PERSON_02, nullable = true)
	private	Integer         I_Per_Person_02;


	//-----------------------Transient Variables-------------------------
	@Transient
	private	List<TaTpyDocument> 		O_Documents;

	@Transient
	private List<TaTpyCategoryEntity>	O_Cats;
	
	@Transient
	private List<TaTpyCategory>			O_Cats_Obj;
	
	@Transient
	private	TaPerPerson 				O_Producer;

	@Transient
	private	TaPerPerson 				O_Manager;
	
	@Transient
	private	List<TaMatMaterialDetail> 	O_Children;
	
	@Transient
	private	List<TaMatMaterialDetail> 	O_Parents;
	
	//---------------------Constructeurs-----------------------
	public TaMatMaterialOBS(){}

	public TaMatMaterialOBS(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		//doInitDBFlag();
	}

	public TaMatMaterialOBS(String s, String commentTag){ //list machine
		if (s.startsWith(commentTag))
			this.I_Status_01 = STAT_01_REVIEW;
		else 
			this.I_Status_01 = STAT_01_ACTIVE;
		
		String []inf = s.split(" ");
		//#Nom Site OS Version Verif ClusterHP MotDePasse FonctionPrincipale VersionNoyau Architecture [FonctionSecondaire/CA pour les Windows] [Alias]
		this.T_Name_01 	=  	inf[0];
		this.T_Code_01	= 	inf[1];
	
	}
	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(TaMatMaterialOBS ent) {
		if (ent == this) return;
		this.I_Status_01            = ent.I_Status_01;
		this.I_Status_02            = ent.I_Status_02;
		this.I_Status_03            = ent.I_Status_03;
		this.I_Status_04            = ent.I_Status_04;
		this.I_Status_05            = ent.I_Status_05;
		
		this.I_Type_01              = ent.I_Type_01;
		this.I_Type_02              = ent.I_Type_02;
		this.I_Type_03              = ent.I_Type_03;
		this.I_Type_04              = ent.I_Type_04;
		this.I_Type_05              = ent.I_Type_05;
		
		this.T_Name_01              = ent.T_Name_01;
		this.T_Name_02              = ent.T_Name_02;
		this.T_Name_03              = ent.T_Name_03;
		
		this.T_Code_01              = ent.T_Code_01;
		this.T_Code_02              = ent.T_Code_02;
		this.T_Code_03              = ent.T_Code_03;
		this.T_Code_04              = ent.T_Code_04;
		this.T_Code_05              = ent.T_Code_05;
		this.T_Info_01              = ent.T_Info_01;
		this.T_Info_02              = ent.T_Info_02;
		this.T_Info_03              = ent.T_Info_03;
		this.T_Info_04              = ent.T_Info_04;
		this.T_Info_05              = ent.T_Info_05;
		this.T_Info_06              = ent.T_Info_06;
		this.T_Info_07              = ent.T_Info_07;
		this.T_Info_08              = ent.T_Info_08;
		this.T_Info_09              = ent.T_Info_09;
		this.T_Info_10              = ent.T_Info_10;
		this.D_Date_01              = ent.D_Date_01;
		this.D_Date_02              = ent.D_Date_02;
		this.D_Date_03              = ent.D_Date_03;
		this.D_Date_04              = ent.D_Date_04;
		this.I_Aut_User_01          = ent.I_Aut_User_01;
		this.I_Aut_User_02          = ent.I_Aut_User_02;
		this.I_Per_Manager          = ent.I_Per_Manager;
		this.I_Per_Person_01        = ent.I_Per_Person_01;
		this.I_Per_Person_02        = ent.I_Per_Person_02;

		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;

		ok = (I_ID == ((TaMatMaterialOBS)o).I_ID);
		if (!ok) return ok;


		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}

	//-------------------------------------------------------------------------------------------------------------------------
	public void doBuildChildren() throws Exception{
		if (O_Children!=null) return;
		this.O_Children 	= TaMatMaterialDetail.DAO.reqList(Restrictions.eq(TaMatMaterialDetail.ATT_I_TYPE_01,  TaMatMaterialDetail.TYP_01_DIRECT), Restrictions.eq(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, I_ID));
		TaMatMaterialDetail.doBuildChildren(this.O_Children);
	}
	public void doBuildParents() throws Exception{
		if (O_Parents!=null) return;
		this.O_Parents 		= TaMatMaterialDetail.DAO.reqList(Restrictions.eq(TaMatMaterialDetail.ATT_I_TYPE_01,  TaMatMaterialDetail.TYP_01_DIRECT), Restrictions.eq(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02, I_ID));
		TaMatMaterialDetail.doBuildParents(this.O_Parents);
	}
	
	public void doBuildDetailsChildren() throws Exception{
		TaMatMaterialDetail.doBuildChildren(this.O_Children);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
//	public void doBuildDetails(boolean forced) throws Exception{
//		if (!forced && O_Details!=null) return;
//		this.O_Details 	= TaMatMaterialDetail.DAO.reqList(Restrictions.eq(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, I_ID));	
//		TaMatMaterialDetail.doBuildChildren(this.O_Details);
//	} 
	
	public void doBuildProducer (boolean forced) throws Exception {
		if (!forced && O_Producer!=null) return;	
		if (this.I_Per_Person_01 == null) {
			O_Producer = TaPerPerson.DAO.reqEntityByRef(1);
		} else {
			O_Producer = TaPerPerson.DAO.reqEntityByRef(this.I_Per_Person_01);
		}
	}
	
	//---khong chung session duoc vi nam trong package khac
	public void doBuildDocuments(boolean forced) throws Exception {
		if (this.O_Documents != null && !forced) return;
		this.O_Documents = TaTpyDocument.reqTpyDocuments(ENT_TYP, I_ID, null, null);
	}
	
	
	private void doAddChild (TaMatMaterialDetail p) {
		if (this.O_Children ==null) {
			this.O_Children = new ArrayList<TaMatMaterialDetail>();
		}
		O_Children.add(p);
	}
	
	private void doAddParent (TaMatMaterialDetail p) {
		if (this.O_Parents ==null) {
			this.O_Parents = new ArrayList<TaMatMaterialDetail>();
		}
		O_Parents.add(p);
	}
	
	public static void doBuildDetails(List<TaMatMaterialOBS> mats) throws Exception{
		Set<Integer> ids = ToolSet.reqSetInt(mats, TaMatMaterialOBS.ATT_I_ID);
		Hashtable<Integer,EntityAbstract> map = ToolDBEntity.reqTabKeyInt(mats, TaMatMaterialOBS.ATT_I_ID);

		List<TaMatMaterialDetail> dets 	= TaMatMaterialDetail.DAO.reqList_In(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, ids);	

		for (TaMatMaterialDetail d: dets) {
			Integer 		matId 	= d.reqInt(d, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);
			TaMatMaterialOBS 	mat 	= (TaMatMaterialOBS) map.get(matId);
			mat.doAddChild(d);
		}

		//----O_Child------
		Set<Integer> 		setUids = ToolSet.reqSetInt(dets, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
		List<TaMatMaterialOBS> lstChild = TaMatMaterialOBS.DAO.reqList_In(TaMatMaterialOBS.ATT_I_ID, setUids);
		map = ToolDBEntity.reqTabKeyInt(lstChild, TaMatMaterialOBS.ATT_I_ID);
		for(TaMatMaterialDetail d: dets){
			Integer idChild = (Integer) d.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
			d.reqSet(TaMatMaterialDetail.ATT_O_CHILD, map.get(idChild));
		}
	}
	
	public void doBuildCatIds(boolean forced) throws Exception {
		if (!forced && O_Cats!=null) return;
		Integer matId	= this.reqId();
		
		this.O_Cats = TaTpyCategoryEntity.DAO.reqList(
				Restrictions.eq(TaTpyCategoryEntity.ATT_I_ENTITY_TYPE	, DefDBExt.ID_TA_MAT_MATERIAL), 
				Restrictions.eq(TaTpyCategoryEntity.ATT_I_ENTITY_ID		, matId)
		);
		
	}
	public void doBuildCatObjs() throws Exception {
		if (this.O_Cats!=null && this.O_Cats.size()>0) {
			Set<Integer> ids = ToolSet.reqSetInt(this.O_Cats, TaTpyCategoryEntity.ATT_I_TPY_CATEGORY);
			this.O_Cats_Obj = TaTpyCategory.DAO.reqList_In(TaTpyCategory.ATT_I_ID, ids);
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	public void doBuildChildren(Integer levMax) throws Exception{
		if (levMax==null) levMax = 10;
		Session sess = TaMatMaterialOBS.DAO.reqSessionCurrent();
		try {
			Set<Integer> ids = new HashSet<Integer>();
			ids.add(this.I_ID);
			this.O_Children = reqBuildChildren (sess, ids, levMax);
			if (sess!=null) TaMatMaterialOBS.DAO.doSessionCommit(sess);
		}catch (Exception e) {
			if (sess!=null) TaMatMaterialOBS.DAO.doSessionRollback(sess);
			ToolLogServer.doLogErr(e);
		}
	}
	//----Build struct with bottom-up way to typeDest
	public void doBuildStructTo(Integer levMax, Integer typDest) throws Exception{
		if (levMax==null) levMax = 10;
		Session sess = TaMatMaterialOBS.DAO.reqSessionCurrent();
		try {
			Hashtable<Integer,EntityAbstract> mapPar  = new Hashtable<Integer,EntityAbstract> ();
			mapPar.put(this.I_ID, this);
			
			this.O_Children = reqBuildStructDown (sess, mapPar, levMax, typDest);
			if (sess!=null) TaMatMaterialOBS.DAO.doSessionCommit(sess);
		}catch (Exception e) {
			if (sess!=null) TaMatMaterialOBS.DAO.doSessionRollback(sess);
			ToolLogServer.doLogErr(e);
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------------
	
	public static List<TaMatMaterialDetail> reqBuildStructDown(Session sess, Hashtable<Integer,EntityAbstract> mapPar, Integer levMax, Integer typDest) throws Exception{
		Set<Integer> 				matIDs 		= mapPar.keySet();
		
		List<TaMatMaterialDetail> 	dets 		= TaMatMaterialDetail.DAO.reqList_In(sess, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, matIDs, Restrictions.eq(TaMatMaterialDetail.ATT_I_TYPE_01,  TaMatMaterialDetail.TYP_01_DIRECT));
		Set<Integer> 				setUids 	= ToolSet.reqSetInt(dets, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
		
		List<TaMatMaterialOBS> 		lstChild 	= TaMatMaterialOBS.DAO.reqList_In(sess, TaMatMaterialOBS.ATT_I_ID, setUids);
		Hashtable<Integer,EntityAbstract> mapChi	= ToolDBEntity.reqTabKeyInt(lstChild, TaMatMaterialOBS.ATT_I_ID); 
		
		boolean canStop = false;
		for(TaMatMaterialDetail d: dets){
			Integer 		idPar 	= (Integer) d.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);
			Integer 		idChild = (Integer) d.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
			TaMatMaterialOBS	mPar	= (TaMatMaterialOBS) mapPar.get(idPar); 
			TaMatMaterialOBS 	mChi 	= (TaMatMaterialOBS) mapChi.get(idChild);
			
			if (mChi.I_Type_01.equals(typDest)) canStop = true;
			
			if (mChi.O_Parents!=null) continue;
			
			mPar.doAddChild(d);
			d.reqSet(TaMatMaterialDetail.ATT_O_CHILD, mChi);
		}
		
		if (!canStop && setUids!=null && setUids.size()>0 && levMax>0)
			reqBuildStructDown(sess, mapChi, levMax-1, typDest);
		else if (!canStop && (setUids==null || setUids.size()==0) && levMax>0)
			reqBuildStructUp(sess, mapPar, levMax-1, typDest);
		
		return dets;
	}
	
	public static List<TaMatMaterialDetail> reqBuildStructUp(Session sess, Hashtable<Integer,EntityAbstract> mapChi, Integer levMax, Integer typDest) throws Exception{
		Set<Integer> 				matIDs 		= mapChi.keySet();
		List<TaMatMaterialDetail> 	dets 		= TaMatMaterialDetail.DAO.reqList_In(sess, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02, matIDs, Restrictions.eq(TaMatMaterialDetail.ATT_I_TYPE_01,  TaMatMaterialDetail.TYP_01_DIRECT));
		Set<Integer> 				setUids 	= ToolSet.reqSetInt(dets, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);
		
		List<TaMatMaterialOBS> 		lstPar 		= TaMatMaterialOBS.DAO.reqList_In(sess, TaMatMaterialOBS.ATT_I_ID, setUids);
		Hashtable<Integer,EntityAbstract> mapPar= ToolDBEntity.reqTabKeyInt(lstPar, TaMatMaterialOBS.ATT_I_ID); 
		
		boolean canStop = false;
		for(TaMatMaterialDetail d: dets){
			Integer 		idPar 	= (Integer) d.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);
			Integer 		idChild = (Integer) d.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
			TaMatMaterialOBS	mPar	= (TaMatMaterialOBS) mapPar.get(idPar); 
			TaMatMaterialOBS 	mChi 	= (TaMatMaterialOBS) mapChi.get(idChild);
			
			if (mPar.I_Type_01.equals(typDest)) canStop = true;
			
			if (mPar.O_Children!=null) continue;
			
			mChi.doAddParent(d);
			d.reqSet(TaMatMaterialDetail.ATT_O_PARENT, mPar);
		}
		
		if (!canStop && setUids!=null && setUids.size()>0 && levMax>0)
			reqBuildStructUp(sess, mapPar, levMax-1, typDest);
		else if (!canStop && (setUids==null || setUids.size()==0) && levMax>0)
			reqBuildStructDown(sess, mapChi, levMax-1, typDest);
		
		return dets;
	}
	
	public static List<TaMatMaterialDetail> reqBuildChildren(Session sess, Set<Integer> matIDs, Integer levMax) throws Exception{
		List<TaMatMaterialDetail> 	dets 		= TaMatMaterialDetail.DAO.reqList_In(sess, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, matIDs, Restrictions.eq(TaMatMaterialDetail.ATT_I_TYPE_01,  TaMatMaterialDetail.TYP_01_DIRECT));
		Set<Integer> 				setUids 	= ToolSet.reqSetInt(dets, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
		
		List<TaMatMaterialOBS> 		lstChild 	= TaMatMaterialOBS.DAO.reqList_In(TaMatMaterialOBS.ATT_I_ID, setUids);
		Hashtable<Integer,EntityAbstract> map 	= ToolDBEntity.reqTabKeyInt(lstChild, TaMatMaterialOBS.ATT_I_ID); 
		
		for(TaMatMaterialDetail d: dets){
			Integer idChild = (Integer) d.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
			d.reqSet(TaMatMaterialDetail.ATT_O_CHILD, map.get(idChild));
		}
		
		if (setUids!=null && setUids.size()>0 && levMax>0)
			reqBuildChildren(sess, setUids, levMax-1);
		
		return dets;
	}
	
	public static List<TaMatMaterialDetail> reqBuildParents(Session sess, Set<Integer> matIDs, Integer levMax) throws Exception{
		List<TaMatMaterialDetail> 	dets 		= TaMatMaterialDetail.DAO.reqList_In(sess, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02, matIDs, Restrictions.eq(TaMatMaterialDetail.ATT_I_TYPE_01,  TaMatMaterialDetail.TYP_01_DIRECT));
		Set<Integer> 				setUids 	= ToolSet.reqSetInt(dets, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);
		
		List<TaMatMaterialOBS> 		lstPar 		= TaMatMaterialOBS.DAO.reqList_In(TaMatMaterialOBS.ATT_I_ID, setUids);
		Hashtable<Integer,EntityAbstract> map 	= ToolDBEntity.reqTabKeyInt(lstPar, TaMatMaterialOBS.ATT_I_ID); 
		
		for(TaMatMaterialDetail d: dets){
			Integer idPar = (Integer) d.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);
			d.reqSet(TaMatMaterialDetail.ATT_O_PARENT, map.get(idPar));
		}
		
		if (setUids!=null && setUids.size()>0 && levMax>0)
			reqBuildParents(sess, setUids, levMax-1);
		
		return dets;
	}
}
