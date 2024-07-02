package com.hnv.db.cfg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.main.API;
import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.def.DefDBExt;

/**
* TaCfgGroupValue by H&V SAS
*/
@Entity
@Table(name = DefDBExt.TA_CFG_GROUP_VALUE )
public class TaCfgGroupValue extends EntityAbstract<TaCfgGroupValue> implements Cloneable{
	
	@Override
	public TaCfgGroupValue clone(){
		TaCfgGroupValue cl = new TaCfgGroupValue();
		cl.doMergeWith(this);
		if (this.O_Children!=null){
			cl.O_Children = new ArrayList<TaCfgGroupValue>();
			cl.O_Children.addAll(this.O_Children);
		}
		return cl;		
	}

	private static final long serialVersionUID = 1L;

	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_I_CFG_GROUP                       =	"I_Cfg_Group";
	public static final String	COL_I_PARENT                          =	"I_Parent";
	public static final String	COL_T_CODE                            =	"T_Code";
	public static final String	COL_T_VAL_01                          =	"T_Val_01";
	public static final String	COL_T_VAL_02                          =	"T_Val_02";
	public static final String	COL_T_VAL_03                          =	"T_Val_03";
	public static final String	COL_T_VAL_04                          =	"T_Val_04";
	public static final String	COL_T_VAL_05                          =	"T_Val_05";

	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_I_CFG_GROUP                       =	"I_Cfg_Group";
	public static final String	ATT_I_PARENT                          =	"I_Parent";
	public static final String	ATT_T_CODE                            =	"T_Code";
	public static final String	ATT_T_VAL_01                          =	"T_Val_01";
	public static final String	ATT_T_VAL_02                          =	"T_Val_02";
	public static final String	ATT_T_VAL_03                          =	"T_Val_03";
	public static final String	ATT_T_VAL_04                          =	"T_Val_04";
	public static final String	ATT_T_VAL_05                          =	"T_Val_05";

	public static final String	ATT_O_CHILDREN						  = "O_Children";


	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 

	public		static 	final EntityDAO<TaCfgGroupValue> 	DAO;
	static{
		DAO = new EntityDAO<TaCfgGroupValue>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaCfgGroupValue.class,RIGHTS);
	}

	//-----------------------Class Attributs-------------------------
	@Id
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
         
	@Column(name=COL_I_CFG_GROUP, nullable = false)
	private	Integer         I_Cfg_Group;
      
	@Column(name=COL_I_PARENT, nullable = true)
	private	Integer         I_Parent;
     
	@Column(name=COL_T_CODE, nullable = true)
	private	String          T_Code;
       
	@Column(name=COL_T_VAL_01, nullable = true)
	private	String          T_Val_01;
     
	@Column(name=COL_T_VAL_02, nullable = true)
	private	String          T_Val_02;
     
	@Column(name=COL_T_VAL_03, nullable = true)
	private	String          T_Val_03;
     
	@Column(name=COL_T_VAL_04, nullable = true)
	private	String          T_Val_04;
     
	@Column(name=COL_T_VAL_05, nullable = true)
	private	String          T_Val_05;
     

    
	//-----------------------Transient Variables-------------------------
	@Transient
	private List<TaCfgGroupValue> O_Children = null;

	//---------------------Constructeurs-----------------------
	private TaCfgGroupValue(){}

	public TaCfgGroupValue(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		doInitDBFlag();
	}
	
	public TaCfgGroupValue(Integer I_ID, Integer I_Group) throws Exception {
		this.reqSetAttr(
			ATT_I_ID         , I_ID,
			ATT_I_CFG_GROUP      , I_Group
		);
		doInitDBFlag();
	}
	public TaCfgGroupValue(Integer I_ID, Integer I_Group, Integer I_Parent, String T_Code, String T_Val_01, String T_Val_02, String T_Val_03, String T_Val_04, String T_Val_05) throws Exception {
		this.reqSetAttr(
			ATT_I_ID                   , I_ID,
			ATT_I_CFG_GROUP            , I_Group,
			ATT_I_PARENT               , I_Parent,
			ATT_T_CODE                 , T_Code,
			ATT_T_VAL_01               , T_Val_01,
			ATT_T_VAL_02               , T_Val_02,
			ATT_T_VAL_03               , T_Val_03,
			ATT_T_VAL_04               , T_Val_04,
			ATT_T_VAL_05               , T_Val_05
		);
		doInitDBFlag();
	}
	
	
	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(TaCfgGroupValue ent) {
		if (ent == this) return;
		this.I_ID                	= ent.I_ID;
		this.I_Cfg_Group            = ent.I_Cfg_Group;
		this.I_Parent               = ent.I_Parent;
		this.T_Code                 = ent.T_Code;
		this.T_Val_01               = ent.T_Val_01;
		this.T_Val_02               = ent.T_Val_02;
		this.T_Val_03               = ent.T_Val_03;
		this.T_Val_04               = ent.T_Val_04;
		this.T_Val_05               = ent.T_Val_05;

		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		
		ok = (I_ID == ((TaCfgGroupValue)o).I_ID);
		if (!ok) return ok;

				
		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}

	public void doAddChild(TaCfgGroupValue child) {
		if(O_Children == null) {
			O_Children = new ArrayList<TaCfgGroupValue>();
		}
		O_Children.add(child);
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	public static List<TaCfgGroupValue> reqListDel (Integer grpID) throws Exception {
		List<TaCfgGroupValue>  	list 	= TaCfgGroupValue.DAO.reqList(Restrictions.eq(TaCfgGroupValue.ATT_I_CFG_GROUP	 , grpID));	
		TaCfgGroupValue.DAO.doRemove(list);
		return list;
	}
	public static List<TaCfgGroupValue> reqListNew (Integer grpID,  JSONArray lstJson) throws Exception {
		if (lstJson == null || lstJson.size() == 0)
			return null;

		List<TaCfgGroupValue> list = new ArrayList<TaCfgGroupValue>();
		for (Object o : lstJson) {
			TaCfgGroupValue inf = new TaCfgGroupValue(API.reqMapParamsByClass((JSONObject) o, TaCfgGroupValue.class));
			inf.reqSet(TaCfgGroupValue.ATT_I_CFG_GROUP, grpID);
			inf.reqSet(TaCfgGroupValue.ATT_I_ID, null);
			list.add(inf);
		}
		TaCfgGroupValue.DAO.doPersist(list);
		return list;
	}
	public static List<TaCfgGroupValue> reqListMod(Integer grpID,  JSONArray lstJson) throws Exception {
		if (lstJson==null || lstJson.size()==0)	return null;
		if (grpID==null)						return null;

		List		<TaCfgGroupValue> 		lstMod 			= new ArrayList<TaCfgGroupValue > 	();
		List		<Map<String, Object>> 	lstModVal 		= new ArrayList<Map<String, Object>>();
		List		<TaCfgGroupValue> 		lstNew 			= new ArrayList<TaCfgGroupValue > 	();
		Collection	<TaCfgGroupValue> 		lstDel 			= null;
		Collection	<TaCfgGroupValue>  		lstObj 			= TaCfgGroupValue.DAO.reqList(Restrictions.eq(TaCfgGroupValue.ATT_I_CFG_GROUP	 , grpID));		

		HashMap		<Integer,TaCfgGroupValue> 	map 		= new HashMap<Integer,TaCfgGroupValue>();


		if (lstObj!=null){
			for(TaCfgGroupValue d:lstObj){
				Integer id = (Integer) d.req(TaCfgGroupValue.ATT_I_ID);			
				d.reqSet(TaCfgGroupValue.ATT_I_CFG_GROUP	, grpID	);				
				map.put(id, d);			 
			}
		}

		for(int i = 0; i < lstJson.size(); i++) {
			JSONObject 			o 		= (JSONObject) lstJson.get(i);
			if(o == null) continue;
			Map<String, Object> attr 	= API.reqMapParamsByClass(o, TaCfgGroupValue.class);


			Integer 			id		= (Integer) attr.get(TaCfgGroupValue.ATT_I_ID);

			if (id!=null && map.containsKey(id)){
				attr.remove(TaCfgGroupValue.ATT_I_CFG_GROUP);

				lstMod		.add	(map.get(id));					
				lstModVal	.add	(attr);
				map			.remove	(id);
			}else{
				TaCfgGroupValue poO	= new TaCfgGroupValue(API.reqMapParamsByClass(o, TaCfgGroupValue.class));		
				poO.reqSet(TaCfgGroupValue.ATT_I_CFG_GROUP		, grpID);
				poO.reqSet(TaCfgGroupValue.ATT_I_ID				, null);

				lstNew		.add	(poO);
			}
		}

		if (map.size()>0){
			lstDel = map.values();				
		}

		Session sess = TaCfgGroupValue.DAO.reqSessionCurrent();
		try {
			TaCfgGroupValue.DAO.doMerge			(sess, lstMod, lstModVal);
			TaCfgGroupValue.DAO.doPersist		(sess, lstNew);
			TaCfgGroupValue.DAO.doRemove		(sess, lstDel);
			TaCfgGroupValue.DAO.doSessionCommit	(sess);
			lstMod.addAll(lstNew);
			return lstMod;
		}catch(Exception e){
			e.printStackTrace();
			TaCfgGroupValue.DAO.doSessionRollback(sess);
		}
		return null;
	}
}
