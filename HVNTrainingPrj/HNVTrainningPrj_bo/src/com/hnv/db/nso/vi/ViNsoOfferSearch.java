package com.hnv.db.nso.vi;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.db.tpy.vi.ViTpyCategoryDyn;
import com.hnv.def.DefDBExt;	

/**
* TaNsoOffer by H&V SAS
*/
@Entity
@Table(name = DefDBExt.TA_NSO_OFFER )
public class ViNsoOfferSearch extends EntityAbstract<ViNsoOfferSearch> {

	private static final long serialVersionUID = 1L;
	 
	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_T_TITLE                           =	"T_Title";
	public static final String	COL_I_STATUS_01                       =	"I_Status_01";
	public static final String	COL_I_STATUS_02                       =	"I_Status_02";
	public static final String	COL_I_PARENT                          =	"I_Parent";
	public static final String	COL_T_CODE_01                         =	"T_Code_01";
	public static final String	COL_I_TYPE_01                         =	"I_Type_01";
	public static final String	COL_I_TYPE_02                         =	"I_Type_02";
	public static final String	COL_I_TYPE_03                         =	"I_Type_03";
	public static final String	COL_T_CONTENT_02                      =	"T_Content_02";
	public static final String	COL_T_CONTENT_03                      =	"T_Content_03";
	public static final String	COL_T_CONTENT_04                      =	"T_Content_04";
	public static final String	COL_T_CONTENT_05                      =	"T_Content_05";
	public static final String	COL_T_INFO_02                         =	"T_Info_02";
	public static final String	COL_D_DATE_05                         =	"D_Date_05";
	public static final String	COL_I_VAL_03                          =	"I_Val_03";
	public static final String	COL_I_VAL_04                          =	"I_Val_04";
	public static final String	COL_I_VAL_05                          =	"I_Val_05";
	public static final String	COL_F_VAL_01                          =	"F_Val_01";
	public static final String	COL_F_VAL_02                          =	"F_Val_02";



	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_T_TITLE                           =	"T_Title";
	public static final String	ATT_I_STATUS_01                       =	"I_Status_01";
	public static final String	ATT_I_STATUS_02                       =	"I_Status_02";
	public static final String	ATT_I_PARENT                          =	"I_Parent";
	public static final String	ATT_T_CODE_01                         =	"T_Code_01";
	public static final String	ATT_I_TYPE_01                         =	"I_Type_01";
	public static final String	ATT_I_TYPE_02                         =	"I_Type_02";
	public static final String	ATT_I_TYPE_03                         =	"I_Type_03";
	public static final String	ATT_T_CONTENT_02                      =	"T_Content_02";
	public static final String	ATT_T_CONTENT_03                      =	"T_Content_03";
	public static final String	ATT_T_CONTENT_04                      =	"T_Content_04";
	public static final String	ATT_T_CONTENT_05                      =	"T_Content_05";
	public static final String	ATT_T_INFO_02                         =	"T_Info_02";
	public static final String	ATT_D_DATE_03                         =	"D_Date_03";
	public static final String	ATT_D_DATE_04                         =	"D_Date_04";
	public static final String	ATT_D_DATE_05                         =	"D_Date_05";
	public static final String	ATT_I_VAL_03                          =	"I_Val_03";
	public static final String	ATT_I_VAL_04                          =	"I_Val_04";
	public static final String	ATT_I_VAL_05                          =	"I_Val_05";
	public static final String	ATT_F_VAL_01                          =	"F_Val_01";
	public static final String	ATT_F_VAL_02                          =	"F_Val_02";
	
	public static final String 	ATT_O_CATS 					  		  = "O_Cats";
	public static final String	ATT_O_DOCUMENTS                       =	"O_Documents";



	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<ViNsoOfferSearch> 	DAO;
	static{
		DAO = new EntityDAO<ViNsoOfferSearch>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), ViNsoOfferSearch.class,RIGHTS, HISTORY, DefDBExt.TA_NSO_OFFER, DefDBExt.ID_TA_NSO_OFFER);

	}

	//-----------------------Class Attributs-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;
         
	@Column(name=COL_T_TITLE, nullable = true)
	private	String          T_Title;
    
	@Column(name=COL_I_STATUS_01, nullable = true)
	private	Integer         I_Status_01;
	
	@Column(name=COL_I_STATUS_02, nullable = true)
	private	Integer         I_Status_02;
     
	@Column(name=COL_I_PARENT, nullable = true)
	private	Integer         I_Parent;
     
	@Column(name=COL_T_CODE_01, nullable = true)
	private	String          T_Code_01;
    
	@Column(name=COL_I_TYPE_01, nullable = true)
	private	Integer         I_Type_01;
    
	@Column(name=COL_I_TYPE_02, nullable = true)
	private	Integer         I_Type_02;
    
	@Column(name=COL_I_TYPE_03, nullable = true)
	private	Integer         I_Type_03;
	
	@Column(name=COL_T_CONTENT_02, nullable = true)
	private	String          T_Content_02;
	
	@Column(name=COL_T_CONTENT_03, nullable = true)
	private	String          T_Content_03;
 
	@Column(name=COL_T_CONTENT_04, nullable = true)
	private	String          T_Content_04;
	 
	@Column(name=COL_T_CONTENT_05, nullable = true)
	private	String          T_Content_05;
    
	@Column(name=COL_T_INFO_02, nullable = true)
	private	String          T_Info_02;
    
	@Column(name=COL_D_DATE_05, nullable = true)
	private	Date            D_Date_05;
     
	@Column(name=COL_I_VAL_03, nullable = true)
	private	Integer         I_Val_03;
     
	@Column(name=COL_I_VAL_04, nullable = true)
	private	Integer         I_Val_04;
     
	@Column(name=COL_I_VAL_05, nullable = true)
	private	Integer         I_Val_05;
    
	@Column(name=COL_F_VAL_01, nullable = true)
	private	Double         	F_Val_01;
    
	@Column(name=COL_F_VAL_02, nullable = true)
	private	Double         	F_Val_02;

	
	//-----------------------Transient Variables-------------------------

	@Transient
	private	List<TaTpyCategoryEntity>  	O_Category_Entity;
	
	@Transient
	private	List<ViTpyCategoryDyn>  	O_Cats;

	@Transient
	private List<TaTpyDocument>			O_Documents;

	//---------------------Constructeurs-----------------------
	private ViNsoOfferSearch(){}

	public ViNsoOfferSearch(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		// doInitDBFlag();
	}
	
	
	
	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(ViNsoOfferSearch ent) {
		if (ent == this) return;
		this.T_Title                = ent.T_Title;		
		this.I_Parent               = ent.I_Parent;
		this.T_Code_01              = ent.T_Code_01;
		this.I_Type_01              = ent.I_Type_01;
		this.I_Type_02              = ent.I_Type_02;
		this.I_Type_03              = ent.I_Type_03;
		this.T_Content_03           = ent.T_Content_03;
		this.T_Content_04           = ent.T_Content_04;
		this.T_Info_02              = ent.T_Info_02;
		this.D_Date_05              = ent.D_Date_05;
		this.I_Val_03               = ent.I_Val_03;
		this.I_Val_04               = ent.I_Val_04;
		this.I_Val_05               = ent.I_Val_05;


		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;
		
		ok = (I_ID == ((ViNsoOfferSearch)o).I_ID);
		if (!ok) return ok;

				
		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}
	
	public void doBuildCats(boolean forced) throws Exception {       
		if (this.O_Cats!=null && !forced) return;
		
		Criterion 					cri 			= Restrictions.eq(TaTpyCategoryEntity.ATT_I_ENTITY_ID, this.I_ID);
		List<TaTpyCategoryEntity> 	catEntities 	= TaTpyCategoryEntity.DAO.reqList(cri);
		
		Set<Integer> lstId = new HashSet<Integer>();
		for(TaTpyCategoryEntity catEntity : catEntities) {
			lstId.add(catEntity.reqInt(TaTpyCategoryEntity.ATT_I_TPY_CATEGORY));
		}
		
		this.O_Cats = ViTpyCategoryDyn.DAO.reqList(
				Restrictions.in(ViTpyCategoryDyn.ATT_I_ID	, lstId)
		);
	}

}
