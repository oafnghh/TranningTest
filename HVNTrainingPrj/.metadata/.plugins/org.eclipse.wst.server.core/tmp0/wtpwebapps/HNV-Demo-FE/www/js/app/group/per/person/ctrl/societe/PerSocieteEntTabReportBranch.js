define([
	'jquery',
	'text!template/per/partner/PerPartner_Ent_Tab_Report_ThirdParty.html'
	],
	function($,
			PerPartner_Ent_Tab_Report_ThirdParty
	) {

	var PerPartnerEntTabReportThirdParty     = function (header,content,footer, grpName) {
		var pr_divHeader 			= header  ? $(header) : null;
		var pr_divContent 			= content ? $(content): null;
		var pr_divFooter 			= footer  ? $(footer) : null;

		//------------------------------------------------------------------------------------
		var tmplName				= App.template.names;
		var tmplCtrl				= App.template.controller;

		var svClass 				= App['const'].SV_CLASS;
		var svName					= App['const'].SV_NAME;
		var sessId					= App['const'].SESS_ID;
		var userId          		= App['const'].USER_ID;

		var fVar					= App['const'].FUNCT_SCOPE;
		var fName					= App['const'].FUNCT_NAME;
		var fParam					= App['const'].FUNCT_PARAM;		

		var self 					= this;
		
		//------------------------------------------------------------------------------------
		var pr_OBJ_TYPE				= 100;
		var pr_parentId				= null;
		var pr_typeBankAcc			= 2;
		
		//------------------controllers------------------------------------------------------
		var pr_ctr_Main 			= null;
		var pr_ctr_Ent				= null;
		var pr_ctr_EntHeader 		= null;
		var pr_ctr_EntBtn 			= null;
		var pr_ctr_EntTabs 			= null;
		
		var pr_default_new_line_mat	= {
				code04 		: null,
				name01 		: null,
				name02		: null,
				unitRef		: null,
				stat		: null,
				code01		: null,
				code02		: null,
				code03		: null,
		};
		
		var pr_default_new_line_inv_detail	= {
				code04 		: null,
				invNum 		: null,
				invSuppl	: null,
				code		: null,
				title		: null,
				q			: null,
				unit		: null,
				val01		: null,
				val02		: null,
				val03		: null,
				val05		: null,
				val06		: null,
				id			: null,
				invId		: null,
		};
		
		//-----------------------------------------------------------------------------------
		var pr_obj					= null;
		var pr_mode					= null;
		//--------------------APIs--------------------------------------//
		this.do_lc_init		= function(){
			pr_ctr_Main 			= App.controller.Per.Main;
			pr_ctr_List				= App.controller.Per.List;
			pr_ctr_Ent				= App.controller.Per.Ent;
			pr_ctr_EntHeader 		= App.controller.Per.EntHeader;
			pr_ctr_EntBtn 			= App.controller.Per.EntBtn;
			pr_ctr_EntTabs 			= App.controller.Per.EntTabs;
		}

		this.do_lc_show		= function(obj, mode){
			pr_obj 				= obj;
			pr_mode					= mode;
			pr_parentId 			= obj.id;
			try{
				tmplCtrl.do_lc_put_tmpl(tmplName.PER_PARTNER_ENT_TAB_REPORT_THIRDPARTY, PerPartner_Ent_Tab_Report_ThirdParty); 	
				$("#div_PerPartner_Ent_Tab_Report").html(tmplCtrl.req_lc_compile_tmpl(tmplName.PER_PARTNER_ENT_TAB_REPORT_THIRDPARTY, {}));
//				refreshListBankAcc(App.data.tpyListBank);
				do_get_thirdParty_mat_lst(obj);
				do_get_thirdParty_inv_detail_lst_dyn(obj);
			}catch(e) {
				do_gl_show_Notify_Msg_Error("PerPartnerEntTabBank: " + e.toString());
				do_gl_exception_send(App.path.BASE_URL_API_PRIV,  "per.partner", "PerPartnerEntTabBank", "do_lc_show", e.toString()) ;
			}
		};
		
		//---------Bind Event-------------------------------------
		function do_bind_event(){
		}
		
		function do_get_thirdParty_mat_lst(obj){
			var ref 			= {};
			ref[svClass] 		= "ServicePerPerson"; 
			ref[svName]			= "SVPerThirdPartyMatMaterialLst"; 
			ref[userId]			= App.data.user.id;
			ref[sessId]			= App.data.session_id;
			ref["thirdPartyId"]	= obj.id;
			
			var fSucces			= [];
			fSucces.push(req_gl_funct(null, do_show_producer_mat_lst));

			var fError 			= req_gl_funct(App, do_gl_show_Notify_Msg, [$.i18n("common_err_ajax")]);	

			App.network.do_lc_ajax (App.path.BASE_URL_API_PRIV, App.data["HttpSecuHeader"], ref, 100000, fSucces, fError) ;
		}
		
		function do_show_producer_mat_lst(sharedJson){
			if(sharedJson[App['const'].SV_CODE] == App['const'].SV_CODE_API_YES) {
				var obj = {};
				obj.lstProducerMat = sharedJson.res_data;
				var additionalConfig = {
					"stat": {
						fnCreatedCell: function(nTd, sData, oData,iRow, iCol) {
							if(oData.stat != null){
								if(oData.stat == 0){
									$(nTd).html($.i18n("mat_material_status_00"))
								}else if(oData.stat == 1){
									$(nTd).html($.i18n("mat_material_status_01"));
								}else {
									$(nTd).html($.i18n("mat_material_status_02"));
								} 
							} else {
								$(nTd).html("");
							} 
						},
					},
					"unitRef": {
						fnCreatedCell: function(nTd, sData, oData,iRow, iCol) {
							var lstUnit = App.data.unitRef[0].vals;
							$.each(lstUnit, function(i, o){
								if(oData.unitRef == o.id){
									$(nTd).html(o.val01);
								}
							})
						},
					},
				}
				
				req_gl_create_datatable(obj, "#div_PerPartner_Ent_Tab_report_Tparty_Mat", additionalConfig, pr_default_new_line_mat, function(){
//					if(pr_mode == App['const'].MODE_MOD || pr_mode == App['const'].MODE_NEW) {
//							do_gl_enable_edit($(pr_divContent));
//						}
				});
			} else {
				//TODO do something here
				//notify here
			}		
		}
		
		function do_get_thirdParty_inv_detail_lst_dyn(obj){
			var ref 			= {};
			ref[svClass] 		= "ServiceInvInvoiceDyn"; 
			ref[svName]			= "SVInvInvoiceLstDynThirdParty"; 
			ref[userId]			= App.data.user.id;
			ref[sessId]			= App.data.session_id;
			ref["thirdPartyId"]	= obj.id;
			
			var lang = localStorage.language;
			if (lang ==null ) lang = "en";
			var div ="#div_PerPartner_Ent_Tab_report_Tparty_Inv_Detail";
			
			var filename = "www/js/lib/datatables/datatable_"+lang+".json";
			
			var additionalConfig = {
			}
			
			var colConfig	= req_gl_table_col_config($(div).find("table"), null, additionalConfig);		        
			
			var fError 		= req_gl_funct(App, do_gl_show_Notify_Msg, [$.i18n("common_err_ajax")]);	
			var oTable 		= req_gl_Datatable_Ajax_Dyn		(div, App.path.BASE_URL_API_PRIV,App.data["HttpSecuHeader"], filename, colConfig, ref, fError, 1000, null, null, null , { "order": [[ 0, "desc" ]]});
		}
		
	};

	return PerPartnerEntTabReportThirdParty;
});