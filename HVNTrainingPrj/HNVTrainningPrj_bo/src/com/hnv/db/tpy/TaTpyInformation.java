package com.hnv.db.tpy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.def.DefDBExt;		

/**
* TaTpyInformation by H&V SAS
*/
@Entity
@Table(name = DefDBExt.TA_TPY_INFORMATION )
public class TaTpyInformation extends EntityAbstract<TaTpyInformation> {
	public static int STAT_WAITING          = 0;
    public static int STAT_OK               = 1; //--seen
    public static int STAT_DELETED          = 2; //--wait for deleting

    public  static int TYPE_01_ALARM_CFG    = 100; //--img
    public  static int TYPE_01_ALARM_PRICE  = 101;
	
	private static final long serialVersionUID = 1L;
	
	public static final int TYP_01_HISTO	= 100;

	//---------------------------List of Column from DB-----------------------------
	 public static final String COL_I_ID           = "I_ID";
     public static final String COL_I_STATUS       = "I_Status";
     public static final String COL_I_ENTITY_TYPE  = "I_Entity_Type";
     public static final String COL_I_ENTITY_ID    = "I_Entity_ID";
     public static final String COL_I_PRIORITY     = "I_Priority";
     
     public static final String	COL_I_TYPE_01      =	"I_Type_01";
     public static final String	COL_I_TYPE_02      =	"I_Type_02";
     public static final String	COL_I_TYPE_03      =	"I_Type_03";
     public static final String	COL_I_TYPE_04      =	"I_Type_04";
     public static final String	COL_I_TYPE_05      =	"I_Type_05";
    
     public static final String COL_F_VAL_01       = "F_Val_01";
     public static final String COL_F_VAL_02       = "F_Val_02";
     public static final String COL_F_VAL_03       = "F_Val_03";
     public static final String COL_F_VAL_04       = "F_Val_04";
     public static final String COL_F_VAL_05       = "F_Val_05";
     public static final String COL_F_VAL_06       = "F_Val_06";
     public static final String COL_F_VAL_07       = "F_Val_07";
     public static final String COL_F_VAL_08       = "F_Val_08";
     public static final String COL_F_VAL_09       = "F_Val_09";
     public static final String COL_F_VAL_10       = "F_Val_10";
     public static final String COL_F_VAL_11       = "F_Val_11";
     public static final String COL_F_VAL_12       = "F_Val_12";
     public static final String COL_F_VAL_13       = "F_Val_13";
     public static final String COL_F_VAL_14       = "F_Val_14";
     public static final String COL_F_VAL_15       = "F_Val_15";
     public static final String COL_F_VAL_16       = "F_Val_16";
     public static final String COL_F_VAL_17       = "F_Val_17";
     public static final String COL_F_VAL_18       = "F_Val_18";
     public static final String COL_F_VAL_19       = "F_Val_19";
     public static final String COL_F_VAL_20       = "F_Val_20";
     
     public static final String COL_T_INFO_01      = "T_Info_01";
     public static final String COL_T_INFO_02      = "T_Info_02";
     public static final String COL_T_INFO_03      = "T_Info_03";
     public static final String COL_T_INFO_04      = "T_Info_04";
     public static final String COL_T_INFO_05      = "T_Info_05";
     public static final String COL_T_INFO_06      = "T_Info_06";
     public static final String COL_T_INFO_07      = "T_Info_07";
     public static final String COL_T_INFO_08      = "T_Info_08";
     public static final String COL_T_INFO_09      = "T_Info_09";
     public static final String COL_T_INFO_10      = "T_Info_10";
     public static final String COL_T_INFO_11      = "T_Info_11";
     public static final String COL_T_INFO_12      = "T_Info_12";
     public static final String COL_T_INFO_13      = "T_Info_13";
     public static final String COL_T_INFO_14      = "T_Info_14";
     public static final String COL_T_INFO_15      = "T_Info_15";
     public static final String COL_T_INFO_16      = "T_Info_16";
     public static final String COL_T_INFO_17      = "T_Info_17";
     public static final String COL_T_INFO_18      = "T_Info_18";
     public static final String COL_T_INFO_19      = "T_Info_19";
     public static final String COL_T_INFO_20      = "T_Info_20";
     
     public static final String COL_D_DATE_01      = "D_Date_01";
     public static final String COL_D_DATE_02      = "D_Date_02";
    
     public static final String COL_I_AUT_USER_01   = "I_Aut_User_01";
     public static final String COL_I_AUT_USER_02   = "I_Aut_User_02";



     public static final String ATT_I_ID           = "I_ID";
     public static final String ATT_I_STATUS       = "I_Status";
     public static final String ATT_I_ENTITY_TYPE  = "I_Entity_Type";
     public static final String ATT_I_ENTITY_ID    = "I_Entity_ID";
     public static final String ATT_I_PRIORITY     = "I_Priority";
    
     public static final String	ATT_I_TYPE_01      =	"I_Type_01";
     public static final String	ATT_I_TYPE_02      =	"I_Type_02";
     public static final String	ATT_I_TYPE_03      =	"I_Type_03";
     public static final String	ATT_I_TYPE_04      =	"I_Type_04";
     public static final String	ATT_I_TYPE_05      =	"I_Type_05";

 	 public static final String	ATT_F_VAL_M        = "F_Val_%s";
     public static final String ATT_F_VAL_01       = "F_Val_01";
     public static final String ATT_F_VAL_02       = "F_Val_02";
     public static final String ATT_F_VAL_03       = "F_Val_03";
     public static final String ATT_F_VAL_04       = "F_Val_04";
     public static final String ATT_F_VAL_05       = "F_Val_05";
     public static final String ATT_F_VAL_06       = "F_Val_06";
     public static final String ATT_F_VAL_07       = "F_Val_07";
     public static final String ATT_F_VAL_08       = "F_Val_08";
     public static final String ATT_F_VAL_09       = "F_Val_09";
     public static final String ATT_F_VAL_10       = "F_Val_10";
     public static final String ATT_F_VAL_11       = "F_Val_11";
     public static final String ATT_F_VAL_12       = "F_Val_12";
     public static final String ATT_F_VAL_13       = "F_Val_13";
     public static final String ATT_F_VAL_14       = "F_Val_14";
     public static final String ATT_F_VAL_15       = "F_Val_15";
     public static final String ATT_F_VAL_16       = "F_Val_16";
     public static final String ATT_F_VAL_17       = "F_Val_17";
     public static final String ATT_F_VAL_18       = "F_Val_18";
     public static final String ATT_F_VAL_19       = "F_Val_19";
     public static final String ATT_F_VAL_20       = "F_Val_20";

     public static final String	ATT_T_INFO_M      = "T_Info_%s";
     public static final String ATT_T_INFO_01      = "T_Info_01";
     public static final String ATT_T_INFO_02      = "T_Info_02";
     public static final String ATT_T_INFO_03      = "T_Info_03";
     public static final String ATT_T_INFO_04      = "T_Info_04";
     public static final String ATT_T_INFO_05      = "T_Info_05";
     public static final String ATT_T_INFO_06      = "T_Info_06";
     public static final String ATT_T_INFO_07      = "T_Info_07";
     public static final String ATT_T_INFO_08      = "T_Info_08";
     public static final String ATT_T_INFO_09      = "T_Info_09";
     public static final String ATT_T_INFO_10      = "T_Info_10";
     public static final String ATT_T_INFO_11      = "T_Info_11";
     public static final String ATT_T_INFO_12      = "T_Info_12";
     public static final String ATT_T_INFO_13      = "T_Info_13";
     public static final String ATT_T_INFO_14      = "T_Info_14";
     public static final String ATT_T_INFO_15      = "T_Info_15";
     public static final String ATT_T_INFO_16      = "T_Info_16";
     public static final String ATT_T_INFO_17      = "T_Info_17";
     public static final String ATT_T_INFO_18      = "T_Info_18";
     public static final String ATT_T_INFO_19      = "T_Info_19";
     public static final String ATT_T_INFO_20      = "T_Info_20";
     
     public static final String ATT_D_DATE_01      = "D_Date_01";
     public static final String ATT_D_DATE_02      = "D_Date_02";

     public static final String ATT_I_AUT_USER_01      = "I_Aut_User_01";
     public static final String ATT_I_AUT_USER_02      = "I_Aut_User_02";

	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<TaTpyInformation> 	DAO;
	static{
		DAO = new EntityDAO<TaTpyInformation>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaTpyInformation.class,RIGHTS);

	}

	//-----------------------Class Attributs-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
         
	@Column(name=COL_I_ENTITY_TYPE, nullable = true)
	private	Integer         I_Entity_Type;

	@Column(name=COL_I_ENTITY_ID, nullable = true)
	private	Integer         I_Entity_ID;
  
	@Column(name=COL_I_STATUS, nullable = true)
	private	Integer         I_Status;
     
	@Column(name=COL_I_PRIORITY, nullable = true)
	private	Integer         I_Priority;
   
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

	@Column(name=COL_T_INFO_11, nullable = true)
	private	String          T_Info_11;
    
	@Column(name=COL_T_INFO_12, nullable = true)
	private	String          T_Info_12;
    
	@Column(name=COL_T_INFO_13, nullable = true)
	private	String          T_Info_13;
    
	@Column(name=COL_T_INFO_14, nullable = true)
	private	String          T_Info_14;
    
	@Column(name=COL_T_INFO_15, nullable = true)
	private	String          T_Info_15;
    
	@Column(name=COL_T_INFO_16, nullable = true)
	private	String          T_Info_16;
    
	@Column(name=COL_T_INFO_17, nullable = true)
	private	String          T_Info_17;
    
	@Column(name=COL_T_INFO_18, nullable = true)
	private	String          T_Info_18;
    
	@Column(name=COL_T_INFO_19, nullable = true)
	private	String          T_Info_19;
    
	@Column(name=COL_T_INFO_20, nullable = true)
	private	String          T_Info_20;
	
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
     
	@Column(name=COL_F_VAL_06, nullable = true)
	private	Double          F_Val_06;
     
	@Column(name=COL_F_VAL_07, nullable = true)
	private	Double          F_Val_07;
     
	@Column(name=COL_F_VAL_08, nullable = true)
	private	Double          F_Val_08;
     
	@Column(name=COL_F_VAL_09, nullable = true)
	private	Double          F_Val_09;
     
	@Column(name=COL_F_VAL_10, nullable = true)
	private	Double          F_Val_10;
	@Column(name=COL_F_VAL_11, nullable = true)
	private	Double          F_Val_11;
     
	@Column(name=COL_F_VAL_12, nullable = true)
	private	Double          F_Val_12;
     
	@Column(name=COL_F_VAL_13, nullable = true)
	private	Double          F_Val_13;
     
	@Column(name=COL_F_VAL_14, nullable = true)
	private	Double          F_Val_14;
     
	@Column(name=COL_F_VAL_15, nullable = true)
	private	Double          F_Val_15;
     
	@Column(name=COL_F_VAL_16, nullable = true)
	private	Double          F_Val_16;
     
	@Column(name=COL_F_VAL_17, nullable = true)
	private	Double          F_Val_17;
     
	@Column(name=COL_F_VAL_18, nullable = true)
	private	Double          F_Val_18;
     
	@Column(name=COL_F_VAL_19, nullable = true)
	private	Double          F_Val_19;
     
	@Column(name=COL_F_VAL_20, nullable = true)
	private	Double          F_Val_20;
     
	@Column(name=COL_D_DATE_01, nullable = true)
	private	Date            D_Date_01;
    
	@Column(name=COL_D_DATE_02, nullable = true)
	private	Date            D_Date_02;
    
	@Column(name=COL_I_AUT_USER_01, nullable = true)
	private	Integer         I_Aut_User_01;

	@Column(name=COL_I_AUT_USER_02, nullable = true)
	private	Integer         I_Aut_User_02;
	
	
    
	//-----------------------Transient Variables-------------------------


	//---------------------Constructeurs-----------------------
	public TaTpyInformation(){}

	public TaTpyInformation(Integer entTyp, Integer entId) throws Exception {
		this.I_Entity_Type 	= entTyp;
		this.I_Entity_ID	= entId;
	}
	
	public TaTpyInformation(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		doInitDBFlag();
	}
	
	
	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(TaTpyInformation ent) {
		if (ent == this) return;
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
		
		this.I_Type_01              = ent.I_Type_01;
		this.I_Type_02              = ent.I_Type_02;
		this.I_Entity_Type          = ent.I_Entity_Type;
		this.I_Entity_ID            = ent.I_Entity_ID;
	


		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		
		ok = (I_ID == ((TaTpyInformation)o).I_ID);
		if (!ok) return ok;

				
		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}

	
	public static List<TaTpyInformation> reqTpyInfomations (Integer entType, Integer entId, Integer typ01) throws Exception{		
		Criterion cri = Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_TYPE, entType);	

		if (entId!=null)
			cri = Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_ID, entId));

		if (typ01!=null)
			cri = Restrictions.and(cri, Restrictions.eq(TaTpyInformation.ATT_I_TYPE_01, typ01));

		return TaTpyInformation.DAO.reqList(cri);
	}
}
