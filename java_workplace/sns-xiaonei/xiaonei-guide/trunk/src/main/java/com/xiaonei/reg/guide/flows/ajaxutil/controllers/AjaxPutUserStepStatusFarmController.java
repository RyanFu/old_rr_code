package com.xiaonei.reg.guide.flows.ajaxutil.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import net.sf.json.JSONObject;

import com.xiaonei.commons.interceptors.access.loginrequired.LoginRequired;
import com.xiaonei.platform.core.model.User;
import com.xiaonei.platform.service.PlatformServiceHome;
import com.xiaonei.reg.common.RunnableTaskExecutor;
import com.xiaonei.reg.guide.flows.xfive.utils.GuideXFiveRequestUtil;
import com.xiaonei.reg.guide.logic.GuideUserTraceLogic;
import com.xiaonei.reg.guide.logic.StepStatusLogic;
import com.xiaonei.reg.guide.util.GuideLogger;
import com.xiaonei.reg.guide.util.GuideTimeCost;

/**
 * AjaxPutUserStepStatusFarmController.java
 * @author inshion(xin.yin@opi-corp.com)
 * 
 * 2010-9-25 添加说明 
 */
@LoginRequired
@Path("axputs.do")
public class AjaxPutUserStepStatusFarmController{

	@Post
	@Get
	public String post(Invocation inv){
		HttpServletRequest request = GuideXFiveRequestUtil.getRequest(inv);
		HttpServletResponse response = GuideXFiveRequestUtil.getResponse(inv);
		final User host = GuideXFiveRequestUtil.getCurrentHost(request);
		
		if(request == null || response == null){
			GuideLogger.printLog(" request or response null "+(request==null)+(response==null),GuideLogger.ERROR);
		}
		else if(host == null){
			GuideLogger.printLog(" host null ",GuideLogger.ERROR);
		}
		else{
			GuideTimeCost cost = GuideTimeCost.begin();
			cost.endStep();
			String sstep = request.getParameter("thestep");
			cost.endStep();
			if(sstep==null || sstep.equals("")){
				JSONObject jo = new JSONObject();
				jo.put("msg", "no step in request");
				writeAjax(response, jo.toString());
				cost.endStep();
				return null;
			}
			
			int step = 0;
			cost.endStep();
			try {
				step = Integer.parseInt(sstep);
				
			} catch (Exception e) {
				JSONObject jo = new JSONObject();
				jo.put("msg", "step is not a number:"+sstep);
				writeAjax(response, jo.toString());
				return null;
			}
			cost.endStep();
			
			if(step == 0){
				JSONObject jo = new JSONObject();
				jo.put("msg", "step is 0");
				writeAjax(response, jo.toString());
				return null;
			}
			cost.endStep();
			
			int defined_step = 0;
			String skip = "0";
			switch(step){
			case 1:
				defined_step = StepStatusLogic.STEP_FLASH_APP_OPEN;
				//注意此处是安装农场的应用
				asyncInstallFarm(host);
				GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP001_CONFIRM_OPENFLOW);
				GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP002_ARRIVAL_FILLINFO);
				break;
			case 2:
				defined_step = StepStatusLogic.STEP_FILLINFO;
				GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP002_CONFIRM_FILLINFO);
				GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP003_ARRIVAL_PREVIEW);
				break;
			case 3:
				defined_step = StepStatusLogic.STEP_PREVIEW;
				skip = request.getParameter("skip");
				if("0".equals(skip)){
					GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP003_CONFIRM_PREVIEW);
					GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP004_ARRIVAL_SEECERTIFICATE);
				}
				else if("1".equals(skip)){
					GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP003_SKIPPED_PREVIEW);
					GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP004_ARRIVAL_SEECERTIFICATE);
				}
				else if("4".equals(skip)){
					GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP004_CONFIRM_SEECERTIFICATE);
					GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP005_ARRIVAL_UPLOAD);
				}
				else{
					GuideLogger.printLog("Step error! host:"+host.getId()+" step=3&skip="+skip,GuideLogger.ERROR);
					GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP004_ARRIVAL_SEECERTIFICATE);
				}
				break;
			case 4:
				defined_step = StepStatusLogic.STEP_UPLOAD_HEAD;
				GuideUserTraceLogic.getInstance().asyncWriteTrace(request, host, GuideUserTraceLogic.STEP005_CONFIRM_UPLOAD);
				break;
			default:
				JSONObject jo = new JSONObject();
				jo.put("msg", "step is not a defined step:"+sstep);
				writeAjax(response, jo.toString());
				return null;
			}
			GuideLogger.writeLine(this,"executePt() - host:"+host.getId()+" step:"+defined_step);
			cost.endStep();
			StepStatusLogic.updateStepStatus(host.getId(), defined_step);
			cost.endStep();
			
			JSONObject jo = new JSONObject();
			jo.put("msg", "OK");
			writeAjax(response, jo.toString());
			cost.endFinally();
		}
		return null;
	}
	
	private static void asyncInstallFarm(final User host) {
		GuideLogger.printLog("start - host:"+host.getId());
		Runnable task = new Runnable(){
			@Override
			public void run() {
				try {
					GuideLogger.printLog("doing - host:"+host.getId()+"-"+23163);
					PlatformServiceHome.getInstance().installApp(host.getId(),23163);
				} catch (Exception e) {
					GuideLogger.printLog("error - host:"+host.getId(),GuideLogger.DEBUG);
				}
			}
			
		};
		RunnableTaskExecutor.getInstance().runTask(task);
	}
	
	private String writeAjax(final HttpServletResponse response,
			final String str) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(str);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {

		} finally {
		}
		return null;
	}
}
