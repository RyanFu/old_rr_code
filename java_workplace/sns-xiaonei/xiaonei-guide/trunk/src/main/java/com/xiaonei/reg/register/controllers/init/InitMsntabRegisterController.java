package com.xiaonei.reg.register.controllers.init;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.paoding.rose.web.annotation.Path;

import com.xiaonei.platform.core.opt.base.PassportManager;
import com.xiaonei.reg.register.controllers.base.BaseRegController;
import com.xiaonei.reg.register.controllers.base.RegForward;
import com.xiaonei.reg.register.controllers.base.RegMapping;
import com.xiaonei.reg.register.form.base.RegForm;
import com.xiaonei.reg.usertrace.dao.InviteUrlParamsDAO;
import com.xiaonei.reg.usertrace.logic.UserTraceLogic;
import com.xiaonei.reg.usertrace.model.RegOprDefine;

/**
 * <action path="/initmsntabreg"
 * type="com.xiaonei.reg.register.action.init.InitMsntabRegisterAction"
 * name="regForm" scope="request" > <forward name="success"
 * path="/pages/register/reg/reg_6201.jsp" /> </action> 普通注册的初始化 2008-9-24
 * 
 * @author wei.cheng@opi-corp.com
 */
@Path("initmsntabreg.do")
public class InitMsntabRegisterController extends BaseRegController {

	public RegForward executeRt(RegMapping mapping, RegForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// 登出在线用户
		PassportManager.getInstance().clearHostId(request, response);

		com.xiaonei.reg.register.form.RegForm regForm = (com.xiaonei.reg.register.form.RegForm) request
				.getAttribute("regForm");
		if (regForm == null || regForm.getFromUrl() == null
				|| "".equals(regForm.getFromUrl())) {
			// 第一次的初始化
			regForm = new com.xiaonei.reg.register.form.RegForm();
			// 拼装来源url
			String uri = request.getRequestURI();
			String domain = request.getServerName();
			String url = "http://" + domain + uri;

			regForm.setFromUrl(url);
			// regForm.setGender("男生");
		}

		// 用户跟踪日志增加
		String ss = request.getParameter("ss");
		if (ss == null || "".equals(ss))
			ss = (String) request.getAttribute("ss");
		String uuid = request.getParameter("iid");
		// 取不到，重新从数据库中获取
		if (ss == null || "".equals(ss)) {
			try {
				ss = InviteUrlParamsDAO.getInstance().getSubSourceByUuid(uuid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		regForm.setSs(ss);
		String actionStr = String.valueOf(RegOprDefine.DEFAULT_XN_REG);
		// 如果页面上有参数传递
		if (request.getParameter("action_id") != null
				&& "".equals(request.getParameter("action_id"))) {
			actionStr = request.getParameter("action_id");
		}
		if (regForm.getFrom() != null && regForm.getFrom().equals("msntab")) {
			actionStr = String.valueOf(RegOprDefine.MSNTAB_VIEW);
		}
		UserTraceLogic.getInstance().insertVisitHisFirst(request, actionStr,
				ss != null ? ss : "", null, null, null, 0);

		request.setAttribute("regForm", regForm);
		return mapping.findForward("success");

	}

	@Override
	protected Map<String, RegForward> setRegMapping(Map<String, RegForward> m) {
		// <forward name="success" path="/pages/register/reg/reg_6201.jsp" />
		m.put("success", new RegForward("/pages/register/reg/reg_6201.jsp"));
		return m;
	}

}
