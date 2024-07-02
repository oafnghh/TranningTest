package com.hnv.api.service.main;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefJS;
import com.hnv.api.interf.IServiceCallback;
import com.hnv.api.main.API;
import com.hnv.api.main.SprB_Token;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.tool.ToolString;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutHistory;
import com.hnv.db.aut.TaAutUser;
import com.hnv.def.DefAPIExt;

/**
 * NVu.Hoang - Rin
 */

@RestController
@CrossOrigin
@RequestMapping(value = DefAPIExt.URL_API_LOGOUT)
public class ServiceAPILogout extends HttpServlet implements IServiceCallback{
	private static final long serialVersionUID = 1L;

	static {

	}

	@RequestMapping(method = RequestMethod.POST)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		try {
			TaAutUser uInf   		= (TaAutUser) request.getAttribute("userInfo");
			if (uInf==null){
				API.doResponse(response, DefAPI.API_MSG_ERR_AUTHEN);
				return;
			}	
			String login = uInf.reqStr(TaAutUser.ATT_T_LOGIN_01);
			ServiceAPILoginCheck.doRemoveFromCache(login);
		} catch (Exception e) {
			ToolLogServer.doLogErr(e);
		}
	}

	@Override
	public void doCallBack(HttpServletRequest arg0, HttpServletResponse arg1, Object... arg2) throws Exception {
		// TODO Auto-generated method stub

	}
}
