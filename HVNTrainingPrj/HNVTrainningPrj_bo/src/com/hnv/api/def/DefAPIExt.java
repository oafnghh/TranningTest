package com.hnv.api.def;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;


public class DefAPIExt {
	public static final String		UI_CONTEXT		= "ui"; //ex: hnv_ui, prodige_ui, ec_ui
	
	public static final String		MODULE_NAME		= "em";
	public static final String		PREFIX_NAME		= "EC_V3";
	
	public static final int 		TIMEOUT_MAX 	= 1000*60*60*2;
	public static final	String[]	PACK_SERVICE	= {	
														"com.hnv.api.service.sub"
														//others here														
														};
		
	public static final	String		API_PATH_MAIN		= "com.hnv.api.main";
	public static final	String		API_PATH_SV_MAIN	= "com.hnv.api.service.main";
	
	public static final String 		URL_API 				= "/api";
	public static final String 		URL_API_UP 				= "/api/up";
	public static final String 		URL_API_VERSION			= "/api/appVer";
	public static final String 		URL_API_LOGIN 			= "/api/login";
//	public static final String 		API_LOGOUT_NAME 		= "/api/logout";
	
	public static 		String 		API_PATH_UPLOAD			= "files/upl";	
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
		API.doLoadWebServiceFrom(DefAPIExt.API_PATH, DefAPIExt.PACK_SERVICE, true);
		
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
		}
		path = svContext.getInitParameter("PATH_SOR_VENDOR");
		if (path!=null){
			API_PATH_VENDOR = (String) path;	
			API_PATH_VENDOR = API_PATH_VENDOR.replace(PREFIX_NAME, MODULE_NAME);
		}
		
		path = svContext.getInitParameter("PATH_TPY_DOCUMENT");
		if (path!=null){
			API_PATH_DOCUMENT = (String) path;	
			API_PATH_DOCUMENT = API_PATH_DOCUMENT.replace(PREFIX_NAME, MODULE_NAME);
		}
		
		path = svContext.getInitParameter("URL_TPY_DOCUMENT");
		if (path!=null){
			API_PATH_URL_DOCBASE = (String) path;				
		}
		
		
		path = svContext.getInitParameter("PATH_REPORT_GEN_TMP");
		if (path!=null){
			API_PATH_REPORT_GEN = (String) path;	
			API_PATH_REPORT_GEN = API_PATH_REPORT_GEN.replace(PREFIX_NAME, MODULE_NAME);
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
		
	}
	
	public static void doClose(Object...params){
		//API_INDEXER.doClose(params);
		ThreadManager.doCloseAll();
	}
}
