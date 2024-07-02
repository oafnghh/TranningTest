package com.hnv.def;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.hnv.api.main.API;
import com.hnv.common.tool.ToolFile;
import com.hnv.process.ThreadManager;

public class DefAPIExt {
	public static final String		UI_CONTEXT		= "ui"; //ex: hnv_ui, prodige_ui, ec_ui
	
	public static final String		MODULE_NAME		= "em";
	public static final String		PREFIX_NAME		= "EC_V3";
	
	public static final int 		TIMEOUT_MAX 	= 1000*60*60*2;
	public static final	String[]	PACK_SERVICE_PRIVATE	= {	
														"com.hnv.api.service.priv"
														//others here														
														};
	public static final	String[]	PACK_SERVICE_PUBLIC	= {	
														"com.hnv.api.service.publ"
														//others here														
														};
	
	public static 		String 		API_VERSION 		= "1.25_240308" ;
	public static final	String		API_PATH_MAIN		= "com.hnv.api.main";
	public static final	String		API_PATH_SV_MAIN		= "com.hnv.api.service.main";
	
	public static final String 		URL_API_PRIVATE			= "/api/priv";
	public static final String 		URL_API_PUBLIC			= "/api/publ";
	public static final String 		URL_API_CHAT			= "/api/chat";
	public static final String 		URL_API_UP 				= "/api/up";
	public static final String 		URL_API_VERSION			= "/api/appVer";
	public static final String 		URL_API_LOGIN 			= "/api/login";
	public static final String 		URL_API_LOGOUT 			= "/api/logout";
	
	
	public static 		String 		LOC_PATH_EXP_CSV		= "C:/tmp/files/exp_csv";	
	public static 		String 		API_PATH_EXP_CSV		= "/files/exp_csv";	
	
	public static 		String 		API_PATH_UPLOAD			= "/files/upl";	
	public static 		String 		API_PATH_VENDOR			= "files/vendor";	
	public static 		String 		API_PATH_DOCUMENT		= "files/docs";
	public static 		String 		API_PATH_URL_DOCBASE	= "/files";
	public static 		String 		API_PATH_REPORT_GEN		= "files/rp_gen";
	public static 		String 		API_PATH_REPORT_TMPL	= "../_report/";
	
	public static 		String 		API_PATH_REPORT_EXPORT	= "files/report_export";
	
	
	public static final String 		API_PATH_UPLOAD_LAB		= "api_path_upload";
	
	
	public static 		String 		SITEMAP_HOST		= "https://wygo.club";	
	public static 		String 		SITEMAP_PATH		= "../_data/sitemap";	
	
	public static 		String 		SITEMAP_STR_AREA		= "view_area";	
	public static 		String 		SITEMAP_STR_OFFER		= "view_offer";	
	public static 		String 		SITEMAP_STR_PLAN		= "view_plan";	
	public static 		String 		SITEMAP_STR_POST		= "view_post";
	public static 		String 		SITEMAP_STR_PRODUCT		= "view_product";	
	
	
	public static 		String 		API_EMAIL_HOST		= "host";
	public static 		String 		API_EMAIL_PORT 		= "";
	public static 		String 		API_EMAIL_USER 		= "";
	public static 		String 		API_EMAIL_PASS 		= "";
	
	
	
	//-----------------------------------------------------------------------------------------
	public static 		String 		SYNAPSE_URL 		= "https://synapse-int.ratp.net/oauth2/v1/token";
	public static 		String 		SYNAPSE_PROXY_URL 	= "proxy.reseau.ratp";
	public static		Integer	    SYNAPSE_PROXY_PORT 	= 80;
	public static 		String 		SYNAPSE_CLI_ID 		= "0oa6s6ie82eW4V1N10x7";
	public static 		String 		SYNAPSE_CLI_PWD		= "x0mVCTBfHmTZ7W5bJS5xzqq1xzSNP1jiBpxAoXC-QR5N0eMnHoEddcMfMco4Zbk-";
	public static 		String 		SYNAPSE_URL_REDIR	= "https://openidconnect.net/callback";
	//-----------------------------------------------------------------------------------------

	public static 		String 		REF_CMDB_PATH 	= "C:\\tmp\\files\\cmdb.csv";
	public static 		String 		REF_ECAR_PATH 	= "";
	public static 		String 		REF_EXP_PATH 	= "C:/tmp/files/";
	public static 		String 		REF_DEXP_PATH 	= "C:\\tmp\\files\\DEXP_Export.csv";
	public static 		String 		REF_GEO_PATH	= "C:\\tmp\\files\\ASID_VM_GEO.csv";
	
	public static 		String 		REF_INT_PILOTAGE		= "C:\\tmp\\files\\Int_Pilotage.xlsm";
	public static 		String 		REF_INT_PILOTAGE_PATH 	= "C:\\tmp\\files\\Int_Pilotage.csv";
	public static 		String 		REF_DECOM_PATH 			= "C:\\tmp\\files\\Decom.xlsm";
	public static 		String 		REF_DECOM_PATH_CSV 		= "C:\\tmp\\files\\Decom.csv";
	
//	public static 		String 		REF_LMD_URL 	= "https://gitlab.ratp.net/api/v4/projects/977/repository/files/liste_machines.cfg/raw?ref=master";
//	public static 		String 		REF_LMD_TOK 	= "Private-Token:_zXRSJm_Eritw8aq8mds";
	
	public static 		String 		REF_LMD_URL 	= "https://exploitweb.info.ratp/divers/liste_machines-DEV.cfg";
	public static 		String 		REF_LMD_TOK 	= null;
	public static 		String 		REF_LMD_PATH 	= "C:\\tmp\\files\\lmd.cfg";
	
	public static 		String 		REF_LMP_URL 	= "https://exploitweb.info.ratp/divers/liste_machines-PROD.cfg";
	public static 		String 		REF_LMP_TOK 	= null;
	public static 		String 		REF_LMP_PATH 	= "C:\\tmp\\files\\lmp.cfg";
	
	public static 		String 		REF_COD_URL 	= "https://exploitweb.info.ratp/divers/liste_machines-PROD.cfg";
	public static 		String 		REF_COD_TOK 	= null;
	public static 		String 		REF_COD_PATH 	= "C:\\tmp\\files\\ASID_Application_Code_Appli.csv";
	public static 		String 		REF_APP_EXTRANET= "C:\\tmp\\files\\APP_EXTRANET.csv";
	public static 		String 		REF_DMZ_PATH 	= "C:\\tmp\\files\\DMZ.csv";
	public static 		String 		REF_ASID_PATH 	= "C:\\tmp\\files\\ASID_Serveur_site_salle_armoire_Model_SerialNumber.csv";
	
	
	private static String apiPath = DefAPIExt.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private static String libPath = "";
	static{
		File f = new File(apiPath);
		while (!(f.getName().equals("bin")||f.getName().equals("classes"))){
			if (f.getParentFile()!=null)
				f = f.getParentFile();
			else break;
		}
		
		//-----del all jasper built files------------------
		List<File> lstJasper = ToolFile.reqFileList(apiPath, "jasper", true);
		if (lstJasper!=null)
			for (File fJas: lstJasper) ToolFile.canDelFile(fJas.getAbsolutePath());
		
		lstJasper = ToolFile.reqFileList(API_PATH_REPORT_TMPL, "jasper", true);
		if (lstJasper!=null)
			for (File fJas: lstJasper) ToolFile.canDelFile(fJas.getAbsolutePath());
		//-------------------------------------------------
		
		apiPath = f.getAbsolutePath().replace("%20", " ");
		libPath = (f.getParent() + File.separator + "lib").replace("%20", " ");
	}
	
	public static final String        API_PATH        = apiPath; 
	public static final String        LIB_PATH        = libPath; 
	
	
	public static final boolean       	API_SESSION_ACTIVE 	= true; 
	public static final boolean			API_PROXY_ACTIVE 	= true;
	
	//----------------------------------------------------------------------------------------------------------
	
	public static int API_UPLOAD_MAX_SIZE 		= 1024 * 1024 * 800; // 800MB
	public static int API_UPLOAD_MAX_MEM 		= 1024 * 1024 * 10; // 800MB
	//----------------------------------------------------------------------------------------------------------
	   
	public static void doInit(Object...params){			
		ServletContextEvent sce = (ServletContextEvent) params[0];
		
		//----put servlet context to API for future use -----
		ServletContext svContext = sce.getServletContext();
		API.doSetServletContext(svContext);
		
		//---load all service class form paths-----------------------------------
		API.doLoadWebServiceFrom(API.SV_TYPE_PRIVATE, DefAPIExt.API_PATH, DefAPIExt.PACK_SERVICE_PRIVATE, true);
		API.doLoadWebServiceFrom(API.SV_TYPE_PUBLIC	, DefAPIExt.API_PATH, DefAPIExt.PACK_SERVICE_PUBLIC	, true);
		
		Object path = null;
		/*
		path = sce.getServletContext().getAttribute("path_index");
		if (path!=null){
			API_PATH_INDEX = (String) path;
			API_INDEXER.doSetIndexPath(API_PATH_INDEX);
		}
		*/
		
		path = svContext.getInitParameter("PATH_UPLOAD_TMP");
		if (path!=null){
			API_PATH_UPLOAD = (String) path;	
			API_PATH_UPLOAD = API_PATH_UPLOAD.replace(PREFIX_NAME, MODULE_NAME);
			ToolFile.canNewDir(API_PATH_UPLOAD);
		}
		
		path = svContext.getInitParameter("PATH_SOR_VENDOR");
		if (path!=null){
			API_PATH_VENDOR = (String) path;	
			API_PATH_VENDOR = API_PATH_VENDOR.replace(PREFIX_NAME, MODULE_NAME);
			ToolFile.canNewDir(API_PATH_VENDOR);
		}
		
		path = svContext.getInitParameter("PATH_TPY_DOCUMENT");
		if (path!=null){
			API_PATH_DOCUMENT = (String) path;	
			API_PATH_DOCUMENT = API_PATH_DOCUMENT.replace(PREFIX_NAME, MODULE_NAME);
			ToolFile.canNewDir(API_PATH_DOCUMENT);
		}
		
		path = svContext.getInitParameter("URL_TPY_DOCUMENT");
		if (path!=null){
			API_PATH_URL_DOCBASE = (String) path;				
		}
		
		
		path = svContext.getInitParameter("PATH_REPORT_GEN_TMP");
		if (path!=null){
			API_PATH_REPORT_GEN = (String) path;	
			API_PATH_REPORT_GEN = API_PATH_REPORT_GEN.replace(PREFIX_NAME, MODULE_NAME);
			ToolFile.canNewDir(API_PATH_REPORT_GEN);
		}
		
		path = svContext.getInitParameter("PATH_REPORT_TMPL");
		if (path!=null){
			API_PATH_REPORT_TMPL = (String) path;	
			API_PATH_REPORT_TMPL = API_PATH_REPORT_TMPL.replace(PREFIX_NAME, MODULE_NAME);
		}
		
		//-------------------------------SITEMAP--------------------------------------------
		path = svContext.getInitParameter("SITEMAP_HOST");
		if (path!=null){
			SITEMAP_HOST = (String) path;	
		}
		
		path = svContext.getInitParameter("SITEMAP_PATH");
		if (path!=null){
			SITEMAP_PATH = (String) path;	
		}
			
		path = svContext.getInitParameter("SITEMAP_STR_POST");
		if (path!=null){
			SITEMAP_STR_POST = (String) path;	
		}
		
		path = svContext.getInitParameter("SITEMAP_STR_AREA");
		if (path!=null){
			SITEMAP_STR_AREA = (String) path;	
		}
		
		path = svContext.getInitParameter("SITEMAP_STR_OFFER");
		if (path!=null){
			SITEMAP_STR_OFFER = (String) path;	
		}
		
		path = svContext.getInitParameter("SITEMAP_STR_PLAN");
		if (path!=null){
			SITEMAP_STR_PLAN = (String) path;	
		}
		
		path = svContext.getInitParameter("SITEMAP_STR_PRODUCT");
		if (path!=null){
			SITEMAP_STR_PRODUCT = (String) path;	
		}
		
		//-----------------------------------------------------------------------------------
		path = svContext.getInitParameter("API_UPLOAD_MAX_SIZE");
		if (path!=null){
			API_UPLOAD_MAX_SIZE = Integer.parseInt((String) path);	
		} 
		
		//-----------------------------------------------------------------------------------
		path = svContext.getInitParameter("API_VERSION");
		if (path!=null){
			API_VERSION =(String) path;	
		} 
		
		//-----------------------------------------------------------------------------------
		path = svContext.getInitParameter("SYNAPSE_URL");
		if (path!=null){
			SYNAPSE_URL =(String) path;	
		} 
		
		path = svContext.getInitParameter("SYNAPSE_CLI_ID");
		if (path!=null){
			SYNAPSE_CLI_ID =(String) path;	
		} 
		
		path = svContext.getInitParameter("SYNAPSE_CLI_PWD");
		if (path!=null){
			SYNAPSE_CLI_PWD =(String) path;	
		} 
		
		path = svContext.getInitParameter("SYNAPSE_URL_REDIR");
		if (path!=null){
			SYNAPSE_URL_REDIR =(String) path;	
		} 
		
		path = svContext.getInitParameter("SYNAPSE_PROXY_URL");
		if (path!=null){
			SYNAPSE_PROXY_URL =(String) path;	
		} 
		
		path = svContext.getInitParameter("SYNAPSE_PROXY_PORT");
		if (path!=null){
			SYNAPSE_PROXY_PORT =Integer.parseInt((String) path);	
		} 
		
		//-----------------------------------------------------------------------------------------
		path = svContext.getInitParameter("REF_CMDB_PATH");
		if (path!=null){
			REF_CMDB_PATH =(String) path;	
		} 
		
		
		path = svContext.getInitParameter("REF_ECAR_PATH");
		if (path!=null){
			REF_ECAR_PATH =(String) path;	
		} 
		
		path = svContext.getInitParameter("REF_LMP_URL");
		if (path!=null){
			REF_LMP_URL =(String) path;	
		} 
		path = svContext.getInitParameter("REF_LMP_TOK");
		if (path!=null){
			REF_LMP_TOK =(String) path;	
		}
		path = svContext.getInitParameter("REF_LMP_PATH");
		if (path!=null){
			REF_LMP_PATH =(String) path;	
		} 
		
		path = svContext.getInitParameter("REF_LMD_URL");
		if (path!=null){
			REF_LMD_URL =(String) path;	
		} 
		path = svContext.getInitParameter("REF_LMD_TOK");
		if (path!=null){
			REF_LMD_TOK =(String) path;	
		}
		path = svContext.getInitParameter("REF_LMD_PATH");
		if (path!=null){
			REF_LMD_PATH =(String) path;	
		} 
		path = svContext.getInitParameter("REF_DEXP_PATH");
		if (path!=null){
			REF_DEXP_PATH =(String) path;	
		}
		path = svContext.getInitParameter("REF_GEO_PATH");
		if (path!=null){
			REF_GEO_PATH =(String) path;	
		} 
		path = svContext.getInitParameter("REF_COD_URL");
		if (path!=null){
			REF_COD_URL =(String) path;	
		} 
		path = svContext.getInitParameter("REF_COD_TOK");
		if (path!=null){
			REF_COD_TOK =(String) path;	
		}
		path = svContext.getInitParameter("REF_COD_PATH");
		if (path!=null){
			REF_COD_PATH =(String) path;	
		} 
		//-----------------------------------------------------------------------------------------
		path = svContext.getInitParameter("LOC_PATH_EXP_CSV");
		if (path!=null){
			LOC_PATH_EXP_CSV =(String) path;	
		}
		path = svContext.getInitParameter("API_PATH_EXP_CSV");
		if (path!=null){
			API_PATH_EXP_CSV =(String) path;	
		}
		

		path = svContext.getInitParameter("REF_DMZ_PATH");
		if (path!=null){
			REF_DMZ_PATH =(String) path;	
		} 
		
		path = svContext.getInitParameter("REF_APP_EXTRANET");
		if (path!=null){
			REF_APP_EXTRANET =(String) path;	
		} 
		
		path = svContext.getInitParameter("REF_INT_PILOTAGE");
		if (path!=null){
			REF_INT_PILOTAGE =(String) path;	
		} 
		
		path = svContext.getInitParameter("REF_INT_PILOTAGE_PATH");
		if (path!=null){
			REF_INT_PILOTAGE_PATH =(String) path;	
		} 
		
		path = svContext.getInitParameter("REF_DECOM_PATH");
		if (path!=null){
			REF_DECOM_PATH =(String) path;	
		} 
		
		path = svContext.getInitParameter("REF_DECOM_PATH_CSV");
		if (path!=null){
			REF_DECOM_PATH_CSV =(String) path;	
		} 
		path = svContext.getInitParameter("REF_ASID_PATH");
		if (path!=null){
			REF_ASID_PATH =(String) path;	
		} 
		
	}
	
	public static void doClose(Object...params){
		//API_INDEXER.doClose(params);
		ThreadManager.doCloseAll();
	}
}
