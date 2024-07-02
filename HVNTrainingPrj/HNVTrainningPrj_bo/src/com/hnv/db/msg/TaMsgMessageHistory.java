package com.hnv.db.msg;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.def.DefDBExt;		

/**
* TaMsgMessageHistory by H&V SAS
*/
@Entity
@Table(name = DefDBExt.TA_MSG_MESSAGE_HISTORY )
public class TaMsgMessageHistory extends EntityAbstract<TaMsgMessageHistory> {

	private static final long serialVersionUID = 1L;

	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_I_MESSAGE                         =	"I_Message";
	public static final String	COL_I_STATUS                          =	"I_Status";
	public static final String	COL_I_AUT_USER                        =	"I_Aut_User";
	public static final String	COL_D_DATE                            =	"D_Date";



	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_I_MESSAGE                         =	"I_Message";
	public static final String	ATT_I_STATUS                          =	"I_Status";
	public static final String	ATT_I_AUT_USER                        =	"I_Aut_User";
	public static final String	ATT_D_DATE                            =	"D_Date";



	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static	final boolean				API_CACHE 	= false;
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<TaMsgMessageHistory> 	DAO;
	static{
		DAO = new EntityDAO<TaMsgMessageHistory>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaMsgMessageHistory.class,RIGHTS); 
/*	
		DAO.doSetIndexer(APIConfig.API_INDEXER, 
				ATT_I_ID,  //obligatoire pour lineID, les suivants pour composer le content pour l'indexation
//				ATT_ ..., 
				
				//next level with list children				
//				EntityIndexTool		.IND_ENT_LST_BEGIN,
//					TaMsgMessageHistory.ATT_O_, 
//						TaMsgMessageHistory_Child	.ATT_T__01,
//						TaMsgMessageHistory_Child	.ATT_T__02,				
//				EntityIndexTool		.IND_ENT_LST_END
	
				
				//next level with object single
//				EntityIndexTool.IND_ENT_SING_BEGIN
//					TaMsgMessageHistory.ATT_O_, 
//						TaMsgMessageHistory_Child	.ATT_T__01,
//						TaMsgMessageHistory_Child	.ATT_T__02,	
//				EntityIndexTool.IND_ENT_SING_END
		);
*/

	}

	//-----------------------Class Attributs-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
         
	@Column(name=COL_I_MESSAGE, nullable = false)
	private	Integer         I_Message;
    
	@Column(name=COL_I_STATUS, nullable = false)
	private	Integer         I_Status;
     
	@Column(name=COL_I_AUT_USER, nullable = false)
	private	Integer         I_Aut_User;
   
	@Column(name=COL_D_DATE, nullable = true)
	private	Date            D_Date;
       

    
	//-----------------------Transient Variables-------------------------


	//---------------------Constructeurs-----------------------
	private TaMsgMessageHistory(){}

	public TaMsgMessageHistory(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		doInitDBFlag();
	}
	
	public TaMsgMessageHistory(Integer I_Message, Integer I_Status, Integer I_Aut_User) throws Exception {
		this.reqSetAttr(
			ATT_I_MESSAGE    , I_Message,
			ATT_I_STATUS     , I_Status,
			ATT_I_AUT_USER   , I_Aut_User
		);
		doInitDBFlag();
	}
	public TaMsgMessageHistory(Integer I_Message, Integer I_Status, Integer I_Aut_User, Date D_Date) throws Exception {
		this.reqSetAttr(
			ATT_I_MESSAGE              , I_Message,
			ATT_I_STATUS               , I_Status,
			ATT_I_AUT_USER             , I_Aut_User,
			ATT_D_DATE                 , D_Date
		);
		doInitDBFlag();
	}
	
	
	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(TaMsgMessageHistory ent) {
		if (ent == this) return;
		this.I_Message              = ent.I_Message;
		this.I_Status               = ent.I_Status;
		this.I_Aut_User             = ent.I_Aut_User;
		this.D_Date                 = ent.D_Date;



		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		
		ok = (I_ID == ((TaMsgMessageHistory)o).I_ID);
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
		return 	"TaMsgMessageHistory { " +
				"I_ID:"+                      I_ID +"," + 
				"I_Message:"+                 I_Message +"," + 
				"I_Status:"+                  I_Status +"," + 
				"I_Aut_User:"+                I_Aut_User +"," + 
				"D_Date:"+                    D_Date +"," + 
				 "}";

	}


}
