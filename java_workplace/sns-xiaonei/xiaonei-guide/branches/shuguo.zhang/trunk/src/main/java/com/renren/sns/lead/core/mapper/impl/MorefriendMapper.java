package com.renren.sns.lead.core.mapper.impl;

import com.renren.sns.lead.core.ILeadConstants;
import com.renren.sns.lead.core.LeadConfigUtil;
import com.renren.sns.lead.core.mapper.AbstractLeadPageMapper;
import com.xiaonei.platform.core.model.User;
import com.xiaonei.reg.guide.root.configs.IStepStatusDefinition;
import com.xiaonei.reg.guide.root.interfaces.IGuideUserLeadType;
import com.xiaonei.reg.guide.root.miscs.MyStringUtils;
import com.xiaonei.reg.guide.util.GuideLogger;

public class MorefriendMapper extends AbstractLeadPageMapper  {
	private static final String SUCC_BROWN = "/views/guide/flowxfivedark/morefriend_dark.jsp";
	
	private static final String INIT_LEAD_AAJ = "/views/guide/flowxfivedarkcyan/morefriend_darkcyan.jsp"; 
	
	@Override
	public String getThisJspUrl(User host, String fromsource) {
		int type = getUserLeadType(host,fromsource);
		String ret = "@";
		
		String pageurifrom = "switch";
		switch(type){
		case IGuideUserLeadType.Lead_AAJ:
			//String aairet = doAAISearch(inv, request,host);
			//return aairet;
			ret = INIT_LEAD_AAJ;
			break;
		default:
			//2010-3-12 14:42:17 为网吧引导同步
			/*doDefault(request, host);
			return SUCC_DEFAULT;*/
			ret = LeadConfigUtil.getPageUri(host, fromsource, ILeadConstants.STEP_KEY_MOREFRIEND);
			pageurifrom = "config";
		break;
		}
		
		if(MyStringUtils.isNullOrEmpty(ret)){
			pageurifrom = "default";
			ret = SUCC_BROWN;
		}
		GuideLogger.printLog(" host:"+host.getId()+" type:"+type+" urif:"+pageurifrom+" topg:"+ret);
		
		return ret;
	}

	@Override
	public int getThisStep() {
		return IStepStatusDefinition.STEP_MOREFRIEND;
	}
	

/*	public List<String> getDataWindows(User host) {
		int type = getUserLeadType(host);
		List<String> ret = new ArrayList<String>();
		switch(type){
		case IGuideUserLeadType.Lead_AAJ:
			ret.add("guideGetOfferFriends");
			ret.add("guideGetRequestFriendsCluster");
			break;
		default:
			ret = null;
			break;
		}
			
		return ret;
	}*/

}
