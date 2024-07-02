package com.hnv.db.tpy;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.def.DefDBExt;		

/**
* TaTpyRelationship by H&V SAS
*/
@Entity
@Table(name = DefDBExt.TA_TPY_RELATIONSHIP )
public class TaTpyRelationship extends EntityAbstract<TaTpyRelationship> {

	private static final long serialVersionUID = 1L;

	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_I_ENTITY_TYPE_01                  =	"I_Entity_Type_01";
	public static final String	COL_I_ENTITY_TYPE_02                  =	"I_Entity_Type_02";
	public static final String	COL_I_ENTITY_ID_01                    =	"I_Entity_ID_01";
	public static final String	COL_I_ENTITY_ID_02                    =	"I_Entity_ID_02";
	public static final String	COL_D_DATE_NEW                        =	"D_Date_New";
	public static final String	COL_D_DATE_MOD                        =	"D_Date_Mod";
	public static final String	COL_D_DATE_BEGIN                      =	"D_Date_Begin";
	public static final String	COL_D_DATE_END                        =	"D_Date_End";
	public static final String	COL_I_TYPE                            =	"I_Type";
	public static final String	COL_I_STATUS                          =	"I_Status";
	public static final String	COL_I_LEVEL                           =	"I_Level";
	public static final String	COL_T_COMMENT                         =	"T_Comment";



	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_I_ENTITY_TYPE_01                  =	"I_Entity_Type_01";
	public static final String	ATT_I_ENTITY_TYPE_02                  =	"I_Entity_Type_02";
	public static final String	ATT_I_ENTITY_ID_01                    =	"I_Entity_ID_01";
	public static final String	ATT_I_ENTITY_ID_02                    =	"I_Entity_ID_02";
	public static final String	ATT_D_DATE_NEW                        =	"D_Date_New";
	public static final String	ATT_D_DATE_MOD                        =	"D_Date_Mod";
	public static final String	ATT_D_DATE_BEGIN                      =	"D_Date_Begin";
	public static final String	ATT_D_DATE_END                        =	"D_Date_End";
	public static final String	ATT_I_TYPE                            =	"I_Type";
	public static final String	ATT_I_STATUS                          =	"I_Status";
	public static final String	ATT_I_LEVEL                           =	"I_Level";
	public static final String	ATT_T_COMMENT                         =	"T_Comment";

	public static final String	ATT_O_ENTITY_01                       =	"O_Entity_01";
	public static final String	ATT_O_ENTITY_02                       =	"O_Entity_02";

	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 

	public		static 	final EntityDAO<TaTpyRelationship> 	DAO;
	static{
		DAO = new EntityDAO<TaTpyRelationship>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaTpyRelationship.class,RIGHTS);
	}

	//-----------------------Class Attributs-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
         
	@Column(name=COL_I_ENTITY_TYPE_01, nullable = false)
	private	Integer         I_Entity_Type_01;

	@Column(name=COL_I_ENTITY_TYPE_02, nullable = false)
	private	Integer         I_Entity_Type_02;

	@Column(name=COL_I_ENTITY_ID_01, nullable = false)
	private	Integer         I_Entity_ID_01;

	@Column(name=COL_I_ENTITY_ID_02, nullable = false)
	private	Integer         I_Entity_ID_02;

	@Column(name=COL_D_DATE_NEW, nullable = true)
	private	Date            D_Date_New;
   
	@Column(name=COL_D_DATE_MOD, nullable = true)
	private	Date            D_Date_Mod;
   
	@Column(name=COL_D_DATE_BEGIN, nullable = true)
	private	Date            D_Date_Begin;
 
	@Column(name=COL_D_DATE_END, nullable = true)
	private	Date            D_Date_End;
   
	@Column(name=COL_I_TYPE, nullable = true)
	private	Integer         I_Type;
       
	@Column(name=COL_I_STATUS, nullable = true)
	private	Integer         I_Status;
     
	@Column(name=COL_I_LEVEL, nullable = true)
	private	Integer         I_Level;
      
	@Column(name=COL_T_COMMENT, nullable = true)
	private	String          T_Comment;
    
	@Transient 
	private Object O_Entity_01;
	
	@Transient 
	private Object O_Entity_02;
    
	//-----------------------Transient Variables-------------------------


	//---------------------Constructeurs-----------------------
	private TaTpyRelationship(){}

	public TaTpyRelationship(Map<String, Object> attrs) throws Exception {
		this.reqSetAttr(attrs);
		doInitDBFlag();
	}
	
	public TaTpyRelationship(Integer I_Entity_Type_01, Integer I_Entity_Type_02, Integer I_Entity_ID_01, Integer I_Entity_ID_02) throws Exception {
		this.reqSetAttr(
			ATT_I_ENTITY_TYPE_01, I_Entity_Type_01,
			ATT_I_ENTITY_TYPE_02, I_Entity_Type_02,
			ATT_I_ENTITY_ID_01, I_Entity_ID_01,
			ATT_I_ENTITY_ID_02, I_Entity_ID_02
		);
		doInitDBFlag();
	}
	public TaTpyRelationship(Integer I_Entity_Type_01, Integer I_Entity_Type_02, Integer I_Entity_ID_01, Integer I_Entity_ID_02, Date D_Date_New, Date D_Date_Mod, Date D_Date_Begin, Date D_Date_End, Integer I_Type, Integer I_Status, Integer I_Level, String T_Comment) throws Exception {
		this.reqSetAttr(
			ATT_I_ENTITY_TYPE_01       , I_Entity_Type_01,
			ATT_I_ENTITY_TYPE_02       , I_Entity_Type_02,
			ATT_I_ENTITY_ID_01         , I_Entity_ID_01,
			ATT_I_ENTITY_ID_02         , I_Entity_ID_02,
			ATT_D_DATE_NEW             , D_Date_New,
			ATT_D_DATE_MOD             , D_Date_Mod,
			ATT_D_DATE_BEGIN           , D_Date_Begin,
			ATT_D_DATE_END             , D_Date_End,
			ATT_I_TYPE                 , I_Type,
			ATT_I_STATUS               , I_Status,
			ATT_I_LEVEL                , I_Level,
			ATT_T_COMMENT              , T_Comment
		);
		doInitDBFlag();
	}
	
	
	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(TaTpyRelationship ent) {
		if (ent == this) return;
		this.I_Entity_Type_01       = ent.I_Entity_Type_01;
		this.I_Entity_Type_02       = ent.I_Entity_Type_02;
		this.I_Entity_ID_01         = ent.I_Entity_ID_01;
		this.I_Entity_ID_02         = ent.I_Entity_ID_02;
		this.D_Date_New             = ent.D_Date_New;
		this.D_Date_Mod             = ent.D_Date_Mod;
		this.D_Date_Begin           = ent.D_Date_Begin;
		this.D_Date_End             = ent.D_Date_End;
		this.I_Type                 = ent.I_Type;
		this.I_Status               = ent.I_Status;
		this.I_Level                = ent.I_Level;
		this.T_Comment              = ent.T_Comment;



		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		
		ok = (I_ID == ((TaTpyRelationship)o).I_ID);
		if (!ok) return ok;

				
		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}

	@Override
	public String toString() {
		return 	"TaTpyRelationship { " +
				"I_ID:"+                      I_ID +"," + 
				"I_Entity_Type_01:"+          I_Entity_Type_01 +"," + 
				"I_Entity_Type_02:"+          I_Entity_Type_02 +"," + 
				"I_Entity_ID_01:"+            I_Entity_ID_01 +"," + 
				"I_Entity_ID_02:"+            I_Entity_ID_02 +"," + 
				"D_Date_New:"+                D_Date_New +"," + 
				"D_Date_Mod:"+                D_Date_Mod +"," + 
				"D_Date_Begin:"+              D_Date_Begin +"," + 
				"D_Date_End:"+                D_Date_End +"," + 
				"I_Type:"+                    I_Type +"," + 
				"I_Status:"+                  I_Status +"," + 
				"I_Level:"+                   I_Level +"," + 
				"T_Comment:"+                 T_Comment +"," + 
				 "}";

	}


}
