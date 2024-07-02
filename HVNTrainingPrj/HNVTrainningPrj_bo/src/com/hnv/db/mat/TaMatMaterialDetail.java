package com.hnv.db.mat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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

import com.hnv.api.main.API;
import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolSet;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.def.DefDBExt;

/**
* TaMatMaterialDetail by H&V SAS
*/
@Entity
@Table(name = DefDBExt.TA_MAT_MATERIAL_DETAIL )
public class TaMatMaterialDetail extends EntityAbstract<TaMatMaterialDetail> {

	private static final long serialVersionUID = 1L;

	public static final int	STAT_01_UNDEF 		= -1;
	public static final int	STAT_01_INACT 		= 0; //en attente, 
	public static final int	STAT_01_ACTIVE 		= 1; //en exploitation
	public static final int	STAT_01_ACTIVE_02	= 2; //réformé
	public static final int	STAT_01_REVIEW 		= 5; //réaffectation
	public static final int	STAT_01_DELETED 	= 10;
	
	public static final int	TYP_01_DIRECT 		= 10;
	public static final int	TYP_01_INDIRECT 	= 20;
	 
	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_I_MAT_MATERIAL_01                 =	"I_Mat_Material_01";//parent of BOM
	public static final String	COL_I_MAT_MATERIAL_02                 =	"I_Mat_Material_02";//child of BOM
	public static final String	COL_T_INFO_01                         =	"T_Info_01";
	public static final String	COL_T_INFO_02                         =	"T_Info_02";
	public static final String	COL_T_INFO_03                         =	"T_Info_03";
	public static final String	COL_T_INFO_04                         =	"T_Info_04";
	public static final String	COL_T_INFO_05                         =	"T_Info_05";
	
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
	
	public static final String	COL_D_DATE_01                         =	"D_Date_01";
	public static final String	COL_D_DATE_02                         =	"D_Date_02";
	public static final String	COL_D_DATE_03                         =	"D_Date_03";
	public static final String	COL_D_DATE_04                         =	"D_Date_04";
	public static final String	COL_D_DATE_05                         =	"D_Date_05";
	
	public static final String	COL_I_VAL_01                          =	"I_Val_01"; //prioritt
	public static final String	COL_I_VAL_02                          =	"I_Val_02";
	public static final String	COL_I_VAL_03                          =	"I_Val_03";
	public static final String	COL_I_VAL_04                          =	"I_Val_04";
	public static final String	COL_I_VAL_05                          =	"I_Val_05";
	
	public static final String	COL_F_VAL_01                          =	"F_Val_01"; //quant
	public static final String	COL_F_VAL_02                          =	"F_Val_02"; //unit ratio
	public static final String	COL_F_VAL_03                          =	"F_Val_03"; //unit label
	public static final String	COL_F_VAL_04                          =	"F_Val_04";
	public static final String	COL_F_VAL_05                          =	"F_Val_05";

	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_I_MAT_MATERIAL_01                 =	"I_Mat_Material_01";
	public static final String	ATT_I_MAT_MATERIAL_02                 =	"I_Mat_Material_02";
	public static final String	ATT_T_INFO_01                         =	"T_Info_01";
	public static final String	ATT_T_INFO_02                         =	"T_Info_02";
	public static final String	ATT_T_INFO_03                         =	"T_Info_03";
	public static final String	ATT_T_INFO_04                         =	"T_Info_04";
	public static final String	ATT_T_INFO_05                         =	"T_Info_05";
	
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
	
	public static final String	ATT_I_VAL_01                          =	"I_Val_01";
	public static final String	ATT_I_VAL_02                          =	"I_Val_02";
	public static final String	ATT_I_VAL_03                          =	"I_Val_03";
	public static final String	ATT_I_VAL_04                          =	"I_Val_04";
	public static final String	ATT_I_VAL_05                          =	"I_Val_05";
	
	public static final String	ATT_F_VAL_01                          =	"F_Val_01";
	public static final String	ATT_F_VAL_02                          =	"F_Val_02";
	public static final String	ATT_F_VAL_03                          =	"F_Val_03";
	public static final String	ATT_F_VAL_04                          =	"F_Val_04";
	public static final String	ATT_F_VAL_05                          =	"F_Val_05";

	public static final String	ATT_D_DATE_01                         =	"D_Date_01";
	public static final String	ATT_D_DATE_02                         =	"D_Date_02";
	public static final String	ATT_D_DATE_03                         =	"D_Date_03";
	public static final String	ATT_D_DATE_04                         =	"D_Date_04";
	public static final String	ATT_D_DATE_05                         =	"D_Date_05";
	

	public static final String	ATT_O_CHILD                           =	"O_Child";
	public static final String	ATT_O_PARENT                          =	"O_Parent";
	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<TaMatMaterialDetail> 	DAO;
	static{
		DAO = new EntityDAO<TaMatMaterialDetail>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaMatMaterialDetail.class,RIGHTS, HISTORY, DefDBExt.TA_MAT_MATERIAL_DETAIL, DefDBExt.ID_TA_MAT_MATERIAL_DETAIL);

	}

	//-----------------------Class Attributs-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
         
	@Column(name=COL_I_MAT_MATERIAL_01, nullable = true)
	private	Integer         I_Mat_Material_01;
	@Column(name=COL_I_MAT_MATERIAL_02, nullable = true)
	private	Integer         I_Mat_Material_02;

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
  
	@Column(name=COL_I_VAL_01, nullable = true)
	private	Integer         I_Val_01;
	@Column(name=COL_I_VAL_02, nullable = true)
	private	Integer         I_Val_02;
	@Column(name=COL_I_VAL_03, nullable = true)
	private	Integer          I_Val_03;
	@Column(name=COL_I_VAL_04, nullable = true)
	private	Integer          I_Val_04;
	@Column(name=COL_I_VAL_05, nullable = true)
	private	Integer          I_Val_05;
     
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
    

	//-----------------------Transient Variables-------------------------
	//-----------------------Transient Variables-------------------------
	@Transient
	private	TaMatMaterial   		O_Child;
	
	@Transient
	private	TaMatMaterial   		O_Parent;

//	@Transient
//	private	TaMatPrice    			O_Price;
	
	@Transient
	private	List<TaMatMaterialDetail>   O_Children;


	//---------------------Constructeurs-----------------------
	private TaMatMaterialDetail(){}

	public TaMatMaterialDetail(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		// doInitDBFlag();
	}
	
	public TaMatMaterialDetail(int matId01, int matId02, int typ01, int stat01, Integer iv01){
		this.I_Mat_Material_01 	= matId01;
		this.I_Mat_Material_02	= matId02;
		this.I_Status_01		= stat01;
		this.I_Type_01			= typ01;
		this.I_Val_01			= iv01;
		this.D_Date_01			= new Date();
	}
	
	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(TaMatMaterialDetail ent) {
		if (ent == this) return;
		this.I_Mat_Material_01      = ent.I_Mat_Material_01;
		this.I_Mat_Material_02      = ent.I_Mat_Material_02;
		this.T_Info_01              = ent.T_Info_01;
		this.T_Info_02              = ent.T_Info_02;
		this.T_Info_03              = ent.T_Info_03;
		this.T_Info_04              = ent.T_Info_04;
		this.T_Info_05              = ent.T_Info_05;
		this.I_Type_01              = ent.I_Type_01;
		this.I_Type_02              = ent.I_Type_02;
		this.I_Status_01            = ent.I_Status_01;
		this.I_Status_02            = ent.I_Status_02;
		this.I_Val_01               = ent.I_Val_01;
		this.I_Val_02               = ent.I_Val_02;
		this.F_Val_01               = ent.F_Val_01;
		this.F_Val_02               = ent.F_Val_02;
		this.F_Val_03               = ent.F_Val_03;
		this.F_Val_04               = ent.F_Val_04;
		this.F_Val_05               = ent.F_Val_05;



		//---------------------Merge Transient Variables if exist-----------------------
	}

    

	//---------------------Constructeurs-----------------------

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		
		ok = (I_ID == ((TaMatMaterialDetail)o).I_ID);
		if (!ok) return ok;

				
		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}

	public void dobuildMat() throws Exception{
		int idChild 	= (int) this.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
		this.O_Child	= TaMatMaterial.DAO.reqEntityByRef(idChild);
	}
	

	//-------------------------------------------------------------------------------------
	public static List<TaMatMaterialDetail> reqBuildBOMStruct(Session sess, Integer matID) throws Exception{
		List<TaMatMaterialDetail> bom = TaMatMaterialDetail.DAO.reqList(sess, Restrictions.eq(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, matID));
		
		for (TaMatMaterialDetail det: bom) {
			List<TaMatMaterial> mats = TaMatMaterial.DAO.reqList(sess, 
					Restrictions.and(	Restrictions.eq(TaMatMaterial.ATT_I_ID		, det.req(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02)), 
										Restrictions.eq(TaMatMaterial.ATT_I_TYPE_01	, TaMatMaterial.TYPE_01_NOD)));
			if (mats!=null && mats.size()>0) {
				for (TaMatMaterial mat : mats) {
					List<TaMatMaterialDetail> dets = reqBuildBOMStruct (sess, mat.reqId());
					if (det.O_Children==null) 
						det.O_Children = dets;
					else 
						det.O_Children.addAll( dets);
				}
			} 
		}
		return bom;
	}
	//-------------------------------------------------------------------------------------
	public static void doBuildChildren (List<TaMatMaterialDetail> lst) throws Exception {
		if (lst == null || lst.size() == 0) return;
		
		Set<Integer> 						ids 		= ToolSet.reqSetInt(lst, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
		List<TaMatMaterial> 				children 	= TaMatMaterial.DAO.reqList_In(TaMatMaterial.ATT_I_ID, ids);
		Hashtable<Integer,EntityAbstract> 	dict 		= ToolDBEntity.reqTabKeyInt(children, TaMatMaterial.ATT_I_ID);
		for (TaMatMaterialDetail o: lst) {
			Integer chiId = o.reqInt(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
			o.reqSet(TaMatMaterialDetail.ATT_O_CHILD, dict.get(chiId));
		}
	}
	
	public static void doBuildParents (List<TaMatMaterialDetail> lst) throws Exception {
		if (lst == null || lst.size() == 0) return;
		
		Set<Integer> 						ids 		= ToolSet.reqSetInt(lst, TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);
		List<TaMatMaterial> 				parents 	= TaMatMaterial.DAO.reqList_In(TaMatMaterial.ATT_I_ID, ids);
		Hashtable<Integer,EntityAbstract> 	dict 		= ToolDBEntity.reqTabKeyInt(parents, TaMatMaterial.ATT_I_ID);
		for (TaMatMaterialDetail o: lst) {
			Integer chiId = o.reqInt(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);
			o.reqSet(TaMatMaterialDetail.ATT_O_PARENT, dict.get(chiId));
		}
	}
	//-------------------------------------------------------------------------------------
	public static List<TaMatMaterialDetail> reqListNew (Integer parID,  JSONArray lstJson) throws Exception {
		if (lstJson == null || lstJson.size() == 0)
			return null;

		List<TaMatMaterialDetail> list = new ArrayList<TaMatMaterialDetail>();
		for (Object o : lstJson) {
			TaMatMaterialDetail inf = new TaMatMaterialDetail(API.reqMapParamsByClass((JSONObject) o, TaMatMaterialDetail.class));
			inf.reqSet(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, parID);
			
			Integer chiId = inf.reqInt(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_02);
			if (chiId==null || chiId<=0) continue;
			
			inf.reqSet(TaMatMaterialDetail.ATT_I_ID, null);
			list.add(inf);
		}
		
		TaMatMaterialDetail.DAO.doPersist(list);
		return list;
	}	
	
	
	public static List<TaMatMaterialDetail> reqListMod(Integer parID,  JSONArray lstJson) throws Exception {
		if (lstJson==null || lstJson.size()==0)	return null;
		if ( parID==null)						return null;

		List		<TaMatMaterialDetail> 		lstMod 			= new ArrayList<TaMatMaterialDetail > 	();
		List		<Map<String, Object>> 		lstModVal 		= new ArrayList<Map<String, Object>>();
		List		<TaMatMaterialDetail> 		lstNew 			= new ArrayList<TaMatMaterialDetail > 	();
		Collection	<TaMatMaterialDetail> 		lstDel 			= null;
		Collection	<TaMatMaterialDetail>  		lstObj 			= TaMatMaterialDetail.DAO.reqList(	
				Restrictions.eq(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, parID));		

		HashMap		<Integer,TaMatMaterialDetail> 	map 		= new HashMap<Integer,TaMatMaterialDetail>();


		if (lstObj!=null){
			for(TaMatMaterialDetail d:lstObj){
				Integer id = (Integer) d.req(TaMatMaterialDetail.ATT_I_ID);			
				d.reqSet(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01	, parID	);				
				map.put(id, d);			 
			}
		}

		for(int i = 0; i < lstJson.size(); i++) {
			JSONObject 			o 		= (JSONObject) lstJson.get(i);
			if(o == null) continue;
			Map<String, Object> attr 	= API.reqMapParamsByClass(o, TaMatMaterialDetail.class);


			Integer 			id		= (Integer) attr.get(TaMatMaterialDetail.ATT_I_ID);

			if (id!=null && id>0 && map.containsKey(id)){
				attr.remove(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01);

				lstMod		.add	(map.get(id));					
				lstModVal	.add	(attr);
				map			.remove	(id);
			}else{
				TaMatMaterialDetail det	= new TaMatMaterialDetail(API.reqMapParamsByClass(o, TaMatMaterialDetail.class));		
				det.reqSet(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01	, parID);
				det.reqSet(TaMatMaterialDetail.ATT_I_ID					, null);

				lstNew		.add	(det);
			}
		}

		if (map.size()>0){
			lstDel = map.values();				
		}

		Session sess = TaMatMaterialDetail.DAO.reqSessionCurrent();
		try {
			TaMatMaterialDetail.DAO.doMerge			(sess, lstMod, lstModVal);
			TaMatMaterialDetail.DAO.doPersist		(sess, lstNew);
			TaMatMaterialDetail.DAO.doRemove		(sess, lstDel);
			TaMatMaterialDetail.DAO.doSessionCommit	(sess);
			lstMod.addAll(lstNew);
			return lstMod;
		}catch(Exception e){
			e.printStackTrace();
			TaMatMaterialDetail.DAO.doSessionRollback(sess);
		}
		return null;
	}
	
	public static void doListDel(Session sess, Integer parID) throws Exception {
		Collection	<TaMatMaterialDetail>  		lstObj 			= TaMatMaterialDetail.DAO.reqList(	sess, Restrictions.eq(TaMatMaterialDetail.ATT_I_MAT_MATERIAL_01, parID));		
		TaMatMaterialDetail.DAO.doRemove		(sess, lstObj);
	}
}
