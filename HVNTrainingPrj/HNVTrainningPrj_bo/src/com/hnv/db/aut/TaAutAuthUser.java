package com.hnv.db.aut;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefAPI;
import com.hnv.api.main.API;
import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolSet;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.def.DefDBExt;

@Entity
@Table(name = DefDBExt.TA_AUT_AUTH_USER)
public class TaAutAuthUser extends EntityAbstract<TaAutAuthUser>{
		private static final long serialVersionUID = 1000L;
		
		//---------------------------List of Column from DB-----------------------------
		public static final String	COL_I_ID                              =	"I_ID";
		public static final String	COL_I_AUT_USER                        =	"I_Aut_User";
		public static final String	COL_I_AUT_ROLE                        =	"I_Aut_Role";
		public static final String	COL_T_AUT_RIGHT                  	  =	"T_Aut_Right";
		public static final String	COL_I_STATUS                          =	"I_Status";
		public static final String	COL_D_DATE_01                         =	"D_Date_01"; //begin
		public static final String	COL_D_DATE_02                         =	"D_Date_02"; //end

		//---------------------------List of ATTR of class-----------------------------
		public static final String	ATT_I_ID                              =	"I_ID";
		public static final String	ATT_I_AUT_USER                        =	"I_Aut_User";
		public static final String	ATT_I_AUT_ROLE                        =	"I_Aut_Role";
		public static final String	ATT_T_AUT_RIGHT                  	  =	"T_Aut_Right";
		public static final String	ATT_I_STATUS                          =	"I_Status";
		public static final String	ATT_D_DATE_01                         =	"D_Date_01"; //begin
		public static final String	ATT_D_DATE_02                         =	"D_Date_02"; //end
		
		public static final int		STAT_ON								= 1;
		public static final int		STAT_OFF							= 0;

		//-------every entity class must initialize its DAO from here -----------------------------
		private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
		private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

		public		static 	final EntityDAO<TaAutAuthUser> 	DAO;
		static{
			DAO = new EntityDAO<TaAutAuthUser>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaAutAuthUser.class,RIGHTS, HISTORY, DefDBExt.TA_AUT_AUTH_USER, DefDBExt.ID_TA_AUT_AUTH_USER);
		}
		
		//-----------------------Class Attributes-------------------------
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name=COL_I_ID, nullable = true)
		private	Integer         I_ID;
	         
		@Column(name=COL_I_AUT_USER, nullable = false)
		private	Integer         I_Aut_User;
	       
		@Column(name=COL_I_AUT_ROLE, nullable = false)
		private	Integer         I_Aut_Role;
		
		@Column(name=COL_T_AUT_RIGHT, nullable = false)
		private	String         T_Aut_Right;
		
		@Column(name=COL_I_STATUS, nullable = true)
		private	Integer         I_Status;
		
		@Column(name=COL_D_DATE_01, nullable = true)
		private	Date            D_Date_01;
	    
		@Column(name=COL_D_DATE_02, nullable = true)
		private	Date            D_Date_02;
		
		private TaAutAuthUser(){}
		
		public TaAutAuthUser(Map<String, Object> attrs) throws Exception {
			this.reqSetAttrFromMap(attrs);
			//doInitDBFlag();
		}
		

		public TaAutAuthUser(int userId, int roleId, int stat, String rights) throws Exception {
			this.I_Aut_User 	= userId;
			this.I_Aut_Role		= roleId;
			this.T_Aut_Right	= rights;
			this.I_Status		= stat;
		}
		
		public TaAutAuthUser(int userId, int roleId, int stat, String rights, Date d01, Date d02) throws Exception {
			this.I_Aut_User 	= userId;
			this.I_Aut_Role		= roleId;
			this.T_Aut_Right	= rights;
			this.I_Status		= stat;
			this.D_Date_01		= d01;
			this.D_Date_02		= d02;
		}
		
		@Override
		public void doMergeWith(TaAutAuthUser e) {
			this.I_Aut_Role 	= e.I_Aut_Role;
			this.I_Aut_User 	= e.I_Aut_User;
			this.T_Aut_Right 	= e.T_Aut_Right;
			this.I_Status		= e.I_Status	;
			this.D_Date_01		= e.D_Date_01	;
			this.D_Date_02		= e.D_Date_02	;

		}
		@Override
		public Serializable reqRef() {
			// TODO Auto-generated method stub
			return this.I_ID;
		}
		
		//----------------------------------------------------------------------------------------------------------------------------
		
		public static List<TaAutAuthUser> reqListCheck (int modeCheck, Integer uId, JSONArray authArray) throws Exception {
			if (uId==null || uId<=0) return null;
			
			Hashtable<Integer, TaAutAuthUser> 	authDict 	= new Hashtable<Integer, TaAutAuthUser>();
			Set<Integer> 						roleIds 	= new HashSet<Integer>();
			
			List<TaAutAuthUser> 				lstNew	 	= new ArrayList<TaAutAuthUser>();
			List<TaAutAuthUser> 				lstMod	 	= new ArrayList<TaAutAuthUser>();
			List<TaAutAuthUser> 				lstDel	 	= new ArrayList<TaAutAuthUser>();
			for (Object a: authArray) {
				JSONObject auth = (JSONObject)a;
				
				Map<String, Object> attr = API.reqMapParamsByClass(auth	, TaAutAuthUser.class);
				if (modeCheck== DefAPI.SV_MODE_NEW) {
					attr.remove(TaAutAuthUser.ATT_I_ID);
				}
				attr.put(TaAutAuthUser.ATT_I_AUT_USER, uId);
				
				TaAutAuthUser 	au 	= new TaAutAuthUser(attr);
				Integer 			rId	= au.reqInt(TaAutAuthUser.ATT_I_AUT_ROLE);
				Integer 			aId	= au.reqInt(TaAutAuthUser.ATT_I_ID);
				
				if (rId==null) continue;
				
				if (aId==null) 	lstNew.add(au); //--new
				else			lstMod.add(au); //--existing
				
				authDict.put(rId, au);
				roleIds.add(rId);
			}
			
			//-------override status + rights from roles to auths-----------------------------------------
			List<TaAutRole>				roles		= TaAutRole.DAO.reqList_In(TaAutRole.ATT_I_ID, roleIds);
			for (TaAutRole r : roles) {
				int rId = r.reqId();
				TaAutAuthUser au = authDict.get(rId);
				au.reqSet(TaAutAuthUser.ATT_I_STATUS	, r.reqInt(TaAutRole.ATT_I_STATUS));
				au.reqSet(TaAutAuthUser.ATT_T_AUT_RIGHT, r.reqStr(TaAutRole.ATT_T_AUT_RIGHT));
			}
			
			//-------check old and new list--------------------------------------------------------------
			List<TaAutAuthUser>				dbAuths		= modeCheck== 	DefAPI.SV_MODE_NEW? new ArrayList<TaAutAuthUser>():
																				TaAutAuthUser.DAO.reqList(Restrictions.eq(TaAutAuthUser.ATT_I_AUT_USER, uId));
			//----compare Ids from client and from DB -----------
			Set<Integer> aClIds = ToolSet.reqSetInt(lstMod	, TaAutAuthUser.ATT_I_ID);
			Set<Integer> aDbIds = ToolSet.reqSetInt(dbAuths	, TaAutAuthUser.ATT_I_ID);
			
			for (TaAutAuthUser a: dbAuths) {
				int id = a.reqId();
				if (!aClIds.contains(id)) lstDel.add(a);
			}
			//----recheck if listMod contains really ids from DB--
			Iterator<TaAutAuthUser> it = lstMod.iterator();
			while (it.hasNext()) {
				TaAutAuthUser a = it.next();
				int id = a.reqId();
				if (!aDbIds.contains(id)) it.remove();
			}
			
			//----save to DB--------------------------------------
			Session sess = TaAutAuthUser.DAO.reqSessionCurrent();
			try {
				TaAutAuthUser.DAO.doPersist(sess, lstNew);
				TaAutAuthUser.DAO.doMerge	(sess, lstMod);
				TaAutAuthUser.DAO.doRemove	(sess, lstDel);
				TaAutAuthUser.DAO.doSessionCommit(sess);
			}catch(Exception e) {
				if (sess!=null) TaAutAuthUser.DAO.doSessionRollback(sess);
			}
			
			
			if (lstMod!=null && lstMod.size()>0) lstNew.addAll(lstMod);
			return lstNew;
		}
		
		
		public static void doListDel(Session sess, Integer userId) throws Exception {
			Collection	<TaAutAuthUser>  		lst 	= TaAutAuthUser.DAO.reqList(sess,	Restrictions.eq(TaAutAuthUser.ATT_I_AUT_USER, userId));		
			TaAutAuthUser.DAO.doRemove(sess, lst);
		}
		
}
