package com.hnv.db.aut;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefDBExt;
import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolSet;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;

@Entity
@Table(name = DefDBExt.TA_AUT_AUTHORIZATION)
public class TaAutAuthorization extends EntityAbstract<TaAutAuthorization>{
		private static final long serialVersionUID = 1000L;
		
		//---------------------------List of Column from DB-----------------------------
		public static final String	COL_I_ID                              =	"I_ID";
		public static final String	COL_I_AUT_USER                        =	"I_Aut_User";
		public static final String	COL_I_AUT_ROLE                        =	"I_Aut_Role";
		public static final String	COL_T_AUT_RIGHT                  	  =	"T_Aut_Right";



		//---------------------------List of ATTR of class-----------------------------
		public static final String	ATT_I_ID                              =	"I_ID";
		public static final String	ATT_I_AUT_USER                        =	"I_Aut_User";
		public static final String	ATT_I_AUT_ROLE                        =	"I_Aut_Role";
		public static final String	ATT_T_AUT_RIGHT                  	  =	"T_Aut_Right";


		//-------every entity class must initialize its DAO from here -----------------------------
		private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
		private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

		public		static 	final EntityDAO<TaAutAuthorization> 	DAO;
		static{
			DAO = new EntityDAO<TaAutAuthorization>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaAutAuthorization.class,RIGHTS, HISTORY, DefDBExt.TA_AUT_AUTHORIZATION, DefDBExt.ID_TA_AUT_AUTHORIZATION);
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
		
		private TaAutAuthorization(){}
		
		public TaAutAuthorization(Map<String, Object> attrs) throws Exception {
			this.reqSetAttrFromMap(attrs);
			//doInitDBFlag();
		}
		

		public TaAutAuthorization(int userId, int roleId, String rights) throws Exception {
			this.I_Aut_User 	= userId;
			this.I_Aut_Role		= roleId;
			this.T_Aut_Right	= rights;
		}
		
		@Override
		public void doMergeWith(TaAutAuthorization e) {
			this.I_Aut_Role = e.I_Aut_Role;
			this.I_Aut_User = e.I_Aut_User;
			this.T_Aut_Right = e.T_Aut_Right;

		}
		@Override
		public Serializable reqRef() {
			// TODO Auto-generated method stub
			return this.I_ID;
		}
		
		//----------------------------------------------------------------------------------------------------------------------------
		public static List<TaAutAuthorization> reqListCheck (Integer userId,  String strRoles) throws Exception {
			if (strRoles==null) strRoles=""; //add address

			Set<Integer> 				roles 		= ToolSet.reqSetInt(strRoles);
			
			List<TaAutRole> 			lstRoles 	= roles.size()>0?TaAutRole.DAO.reqList(TaAutRole.ATT_I_ID, roles): new ArrayList<TaAutRole>();
			List<TaAutAuthorization> 	lstAOld 	= TaAutAuthorization.DAO.reqList(Restrictions.eq(TaAutAuthorization.ATT_I_AUT_USER, userId));
			Hashtable<Integer, EntityAbstract> tab  = ToolDBEntity.reqTabKeyInt(lstAOld, TaAutAuthorization.ATT_I_AUT_ROLE);
			
			List<TaAutAuthorization>	lstANew		= new ArrayList<TaAutAuthorization>();
			List<TaAutAuthorization>	lstAKeep	= new ArrayList<TaAutAuthorization>();
			
			for (TaAutRole r: lstRoles) {
				int rId = r.reqId();
				if (tab.contains(rId)) {
					TaAutAuthorization auth = (TaAutAuthorization) tab.remove(rId); 
					lstAOld.remove(auth);
					lstAKeep.add(auth);
				}else {
					TaAutAuthorization auth = new TaAutAuthorization(userId, r.reqId(), r.reqStr(TaAutRole.ATT_T_AUT_RIGHT));
					lstANew.add(auth);
				}
			}
			TaAutAuthorization.DAO.doPersist(lstANew);
			TaAutAuthorization.DAO.doRemove(lstAOld);
			
			lstAKeep.addAll(lstANew);
			return lstAKeep;
		}
		
		
		public static void doListDel(Session sess, Integer userId) throws Exception {
			Collection	<TaAutAuthorization>  		lst 	= TaAutAuthorization.DAO.reqList(sess,	Restrictions.eq(TaAutAuthorization.ATT_I_AUT_USER, userId));		
			TaAutAuthorization.DAO.doRemove(sess, lst);
		}
		
}
