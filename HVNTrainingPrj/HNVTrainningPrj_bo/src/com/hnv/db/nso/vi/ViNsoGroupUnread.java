package com.hnv.db.nso.vi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;		

/**
* TaNsoGroup by H&V SAS
*/
@Entity
public class ViNsoGroupUnread extends EntityAbstract<ViNsoGroupUnread> {

	private static final long serialVersionUID 		= 1L;
	
	public static final int STAT_PUBLISH			= 1;
	public static final int STAT_PRIVATE			= 2;
	public static final int STAT_DEACTIVE			= 3;
	
	public static final int TYP_02_PRIVATE			= 401;
	public static final int TYP_02_PUBLIC			= 402;

	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_T_NAME                            =	"T_Name";
	
	public static final String	COL_I_TYPE_01                         =	"I_Type_01";
	public static final String	COL_I_TYPE_02                         =	"I_Type_02";
	public static final String	COL_T_VAL_01                          =	"T_Val_01";
	public static final String	COL_T_VAL_02                          =	"T_Val_02";

	public static final String	COL_T_MSGFROM                         =	"T_MsgFrom";
	public static final String	COL_T_MSGBODY                        =	"T_MsgBody";
	public static final String	COL_I_MSGUSER                         =	"I_MsgUser";
	public static final String	COL_D_MSGDATE                         =	"D_MsgDate";
	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_T_NAME                            =	"T_Name";
	
	public static final String	ATT_I_TYPE_01                         =	"I_Type_01";
	public static final String	ATT_I_TYPE_02                         =	"I_Type_02";
	
	public static final String	ATT_T_VAL_01                        =	"T_Val_01";
	public static final String	ATT_T_VAL_02                        =	"T_Val_02";
	
	public static final String	ATT_T_MSGFROM                         =	"T_MsgFrom";
	public static final String	ATT_T_MSGFBODY                        =	"T_MsgBody";
	public static final String	ATT_I_MSGUSER                         =	"I_MsgUser";
	public static final String	ATT_D_MSGDATE                         =	"D_MsgDate";

	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static	final boolean				API_CACHE 	= false;
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<ViNsoGroupUnread> 	DAO;
	static{
		DAO = new EntityDAO<ViNsoGroupUnread>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), ViNsoGroupUnread.class,RIGHTS);
	}

	//-----------------------Class Attributs-------------------------
	@Id
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
	
	@Column(name=COL_T_NAME, nullable = true)
	private	String          T_Name;
     
	@Column(name=COL_I_TYPE_01, nullable = true)
	private	Integer         I_Type_01;
    
	@Column(name=COL_I_TYPE_02, nullable = true)
	private	Integer         I_Type_02;
    	
	@Column(name=COL_T_VAL_01, nullable = true)
	private	String         T_Val_01;
	
	@Column(name=COL_T_VAL_02, nullable = true)
	private	String         T_Val_02;


	@Column(name=COL_T_MSGFROM, nullable = true)
	private	String          T_MsgFrom;
       
	@Column(name=COL_T_MSGBODY, nullable = true)
	private	String          T_MsgBody;
       
	@Column(name=COL_I_MSGUSER, nullable = true)
	private	Integer         I_MsgUser;
   
	@Column(name=COL_D_MSGDATE, nullable = true)
	private	Date            D_MsgDate;	
	
	//-----------------------Transient Variables-------------------------
	//---------------------Constructeurs-----------------------
	private ViNsoGroupUnread(){}

	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(ViNsoGroupUnread ent) {
		if (ent == this) return;
		this.T_Name                 = ent.T_Name;
		this.I_Type_01              = ent.I_Type_01;
		this.I_Type_02              = ent.I_Type_02;
		this.T_Val_01          		= ent.T_Val_01;
		this.T_Val_02          		= ent.T_Val_02;


		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		
		ok = (I_ID == ((ViNsoGroupUnread)o).I_ID);
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
		return 	"TaNsoGroup { " +
				"I_ID:"+                      I_ID +"," + 
				"T_Name:"+                    T_Name +"," + 
				"I_Type_01:"+                 I_Type_01 +"," + 
				"I_Type_02:"+                 I_Type_02 +"," + 
				"T_Val_01:"+             	  T_Val_01 +
				"T_Val_02:"+             	  T_Val_02 +"," + 
				 "}";

	}
	
	static String SQL = "select "
			+ "gr.I_Id 			as " + COL_I_ID 		+ ", "
			+ "gr.T_Name 		as " + COL_T_NAME		+ ", "
			+ "gr.I_Type_01		as " + COL_I_TYPE_01	+ ", "
			+ "gr.I_Type_02		as " + COL_I_TYPE_02	+ ", "
			+ "gr.T_Val_01		as " + COL_T_VAL_01		+ ", "
			+ "gr.T_Val_02		as " + COL_T_VAL_01		+ ", "
			+ "msg.I_Aut_User	as " + COL_I_MSGUSER	+ ", "
			+ "msg.T_From 		as " + COL_T_MSGFROM	+ ", "
			+ "msg.T_Body 		as " + COL_T_MSGBODY	+ ", "
			+ "msg.D_Date 		as " + COL_D_MSGDATE	+ " from "+ 
			"(" + 
				"select max(h.D_Date) as D_Date, h.I_Nso_Group from " + 
				"TA_NSO_GROUP_MEMBER m inner join TA_NSO_GROUP_HISTORY h " + 
				"on m.I_Aut_User = %d and m.I_Status = 2 and m.I_Aut_User = h.I_Aut_User and m.I_Group = h.I_Nso_Group group by h.I_Nso_Group " + 
			") g " + 
			" inner join TA_MSG_MESSAGE msg on  msg.I_Entity_ID = g.I_Nso_Group and msg.D_Date> g.D_Date and msg.I_Aut_User != %d" + 
			" inner join TA_NSO_GROUP   gr  on  msg.I_Entity_ID = gr.I_ID " + 
			"order by  msg.I_ID desc";
	
	public static List<ViNsoGroupUnread> reqList(int uId) throws Exception{
		String s = String.format(SQL, uId, uId);
		List<ViNsoGroupUnread> lst = ViNsoGroupUnread.DAO.reqList(s);
		return lst;
	}
	
}
