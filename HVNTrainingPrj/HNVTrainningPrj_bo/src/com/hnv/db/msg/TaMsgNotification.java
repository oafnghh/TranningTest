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

@Entity
@Table(name= DefDBExt.TA_MSG_NOTIFICATION)
public class TaMsgNotification extends EntityAbstract<TaMsgNotification> implements Comparable<TaMsgNotification>{

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_NO_READ		= 1;
	public static final int TYPE_READED			= 2;
	
	public static final int TYPE_EMAIL_SECU		= 100;
	
	public static final int STAT_NEW			= 0;
	public static final int STAT_VALIDATE		= 1;
	public static final int STAT_STOP			= 2;
	
	public static final int STAT_MAIL_SECRET_SEND	    = 1;
	public static final int STAT_MAIL_SECRET_DEL_PING	= 2;
	public static final int STAT_MAIL_SECRET_DELETED	= 3;
	
	//------------------- List of columns from DB
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_I_STATUS                          =	"I_Status";
	public static final String	COL_I_TYPE                            =	"I_Type";
	public static final String	COL_I_PRIORITY                        =	"I_Priority";
	public static final String	COL_T_CONTENT_01                      =	"T_Content_01";
	public static final String	COL_T_CONTENT_02                      =	"T_Content_02";
	public static final String	COL_T_CONTENT_03                      =	"T_Content_03";
	public static final String	COL_T_CONTENT_04                      =	"T_Content_04";
	public static final String	COL_I_AUT_USER                        =	"I_Aut_User";
	public static final String	COL_D_DATE_01                        =	"D_Date_01";
	public static final String	COL_D_DATE_02                        =	"D_Date_02";
	public static final String	COL_D_DATE_03                        =	"D_Date_03";
	
	//-------------------List of Attributes in class
	// List of columns from DB
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_I_STATUS                          =	"I_Status";
	public static final String	ATT_I_TYPE                            =	"I_Type";
	public static final String	ATT_I_PRIORITY                        =	"I_Priority";
	public static final String	ATT_T_CONTENT_01                      =	"T_Content_01";
	public static final String	ATT_T_CONTENT_02                      =	"T_Content_02";
	public static final String	ATT_T_CONTENT_03                      =	"T_Content_03";
	public static final String	ATT_T_CONTENT_04                      =	"T_Content_04";
	public static final String	ATT_I_AUT_USER                        =	"I_Aut_User";
	public static final String	ATT_D_DATE_01                        =	"D_Date_01";
	public static final String	ATT_D_DATE_02                        =	"D_Date_02";
	public static final String	ATT_D_DATE_03                        =	"D_Date_03";
	
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static	final boolean				API_CACHE 	= false;
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<TaMsgNotification> 	DAO;
		
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	static{
		DAO = new EntityDAO<TaMsgNotification>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaMsgNotification.class,RIGHTS); 
	}
	
	public TaMsgNotification() {
		
	}
		
	//-----------------------Attributes-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
	
	@Column(name=COL_I_STATUS, nullable = false)
	private	Integer          I_Status;
        
	@Column(name=COL_I_TYPE, nullable = true)
	private	Integer          I_Type;
	
	@Column(name=COL_I_PRIORITY, nullable = true)
	private	Integer          I_Priority;
	
	@Column(name=COL_T_CONTENT_01, nullable = true)
	private	String          T_Content_01;
        
	@Column(name=COL_T_CONTENT_02, nullable = true)
	private	String          T_Content_02;
      
	@Column(name=COL_T_CONTENT_03, nullable = true)
	private	String          T_Content_03;
	
	@Column(name=COL_T_CONTENT_04, nullable = true)
	private	String          T_Content_04;
	
	@Column(name=COL_I_AUT_USER, nullable = true)
	private	Integer          I_Aut_User;
	
	@Column(name=COL_D_DATE_01, nullable = true)
	private	Date          D_Date_01;
        
	@Column(name=COL_D_DATE_02, nullable = true)
	private	Date          D_Date_02;
      
	@Column(name=COL_D_DATE_03, nullable = true)
	private	Date          D_Date_03;
	
	//-----------------------Transient Variables-------------------------
	public TaMsgNotification(Map<String, Object> attrs) throws Exception {
		this.D_Date_01 = new Date();
		this.reqSetAttrFromMap(attrs);
	}

	@Override
	public void doMergeWith(TaMsgNotification ent) {
		if(ent == this) return;
	}

	@Override
	public Serializable reqRef() {
		return this.I_ID;
	}
	
	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		ok = (I_ID == ((TaMsgNotification)o).I_ID);
		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;
	}

	@Override
	public String toString() {
		return "TaMsgNotification ["
				+ "I_ID=" + I_ID + ", "
				+ "I_Status=" + I_Status + ", "
				+ "I_Type=" + I_Type + ", "
				+ "I_Priority="	+ I_Priority + ", "
				+ "T_Content_01=" + T_Content_01 + ", "
				+ "T_Content_02=" + T_Content_02 + ", "
				+ "T_Content_03=" + T_Content_03 + ", "
				+ "T_Content_04=" + T_Content_04 + ", "
				+ "I_Aut_User=" + I_Aut_User + ", "
				+ "D_Date_01=" + D_Date_01 + ", "
				+ "D_Date_02=" + D_Date_02 + ", "
				+ "D_Date_03=" + D_Date_03 + "]";
	}

	@Override
	public int compareTo(TaMsgNotification o) {		
		return - this.D_Date_01.compareTo(o.D_Date_01);
	}
}
