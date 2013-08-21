package com.xiaonei.reg.guide.action.tobedel;

import java.util.List;

import com.xiaonei.platform.core.model.User;
import com.xiaonei.platform.core.opt.permission.define.Stage;
import com.xiaonei.reg.guide.flows.xfive.logics.GuideXFiveUseIdentityLogic;
import com.xiaonei.reg.guide.flows.xfive.logics.GuideXFiveUserFlagLogic;
import com.xiaonei.reg.guide.root.configs.IStepStatusDefinition;
import com.xiaonei.reg.guide.root.interfaces.IGuideUserLeadType;
import com.xiaonei.reg.guide.root.miscs.MyStringUtils;
import com.xiaonei.reg.guide.util.GuideLogger;
import com.xiaonei.reg.register.logic.additional.BirthdayLogic;
import com.xiaonei.reg.register.logic.pretreatment.RegFromSourceLogic;

interface IDefByStage {
	public final String URL_FILL_INFO = "r:fi";
	public final String URL_PREVIEW = "r:pv";
	public final String URL_IMPORT_MSN = "@im";
	public final String URL_UPLOAD_HEAD = "r:hu";
}

interface IDefXFive { 
	public final String URL_SEARCH_FRIEND = "r:searchfriend";
	public final String URL_FILL_INFO = "r:fillinfo";//?act_id=20201001";
	public final String URL_PREVIEW = "r:preview";//?act_id=20202001";
	public final String URL_UPLOAD_HEAD = "r:portrait";//?act_id=20203001";
	public final String URL_LANDING_PAGE = "r:landing";
	public final String URL_MORE_FRIEND = "r:morefriend";//?act_id=20205001";
	public final String STEP_IMPORT_MSN = "r:import";;
	public final String URL_GO_APP = "f:goapp";
}

interface IDefAppMazimainguan{ //给麻子面馆准备的
	public final String URL_FILL_INFO = "r:fillinfo";//?act_id=20201001";
	public final String URL_PREVIEW = "r:preview";//?act_id=20202001";
	public final String URL_UPLOAD_HEAD = "r:portrait";//?act_id=20203001";
	public final String URL_GO_APP = "f:goapp";
}

interface IDefXFiveBrown {
	public final String URL_FILL_INFO_BROWN = "r:fibrown";//?act_id=20201001";
	public final String URL_PREVIEW_BROWN = "r:prbrown";//?act_id=20202001";
	public final String URL_UPLOAD_HEAD_BROWN = "r:portrait";//?act_id=20203001";
	public final String URL_LANDING_PAGE_BROWN = "r:landing";
}

interface IDefNetbar{
	public final String URL_FILL_INFO = "f:finb";
}
interface IDefFlashFarm{
	public final String URL_FILL_INFO = "f:fiff";
	public final String URL_GO_APP = "f:goapp";
}
interface IDefFlashCook{
	public final String URL_FILL_INFO = "f:fifc";
	public final String URL_GO_APP = "f:goapp";
}

/**
 * StepProcessLogic.java
 * @author inshion(xin.yin@opi-corp.com)
 *
 * 2010-8-25 添加goWhereFromLeadToApp(host, step);
 * 2010-9-6 添加goWhereIMopFirst(User host, int step);
 * 2010-09-26 14:14:16 添加goWhereLeadAddressbook
 */
public class DeletedUserStepProcessLogic implements IStepStatusDefinition{
	
	private final static String LEAD_COMPLETE_URI = "f:compld";
	
	private static DeletedUserStepProcessLogic instance = new DeletedUserStepProcessLogic();

	public static DeletedUserStepProcessLogic getInstance() {
		return instance;
	}

	private DeletedUserStepProcessLogic() {
		super();
	}
	
	@SuppressWarnings("unused")
	private String goWhereByStage(User host, int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			boolean less17 = BirthdayLogic.getInstance().less17age(host);
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step+" less17 is:"+less17);
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefByStage.URL_FILL_INFO;
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefByStage.URL_PREVIEW;
			} else if ((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN && !less17) {
				toUrl = IDefByStage.URL_IMPORT_MSN;
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefByStage.URL_UPLOAD_HEAD;
			}
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	private String goWhereXFiveBlue(User host, int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} else if (((step & STEP_LANDING_PAGE) != STEP_LANDING_PAGE) ) {
				toUrl = IDefXFive.URL_LANDING_PAGE;
			}
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}

	private String goWhereXFiveCyanA(User host, int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} 

		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}

	
	private String goWhereXFiveCoral(User host, int step) {
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} 

		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	private String goWhereXFiveKouxiangtang(User host, int step) {
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 ||(step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} 
			else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			}
			else if ((step & STEP_MOREFRIEND) != STEP_MOREFRIEND){
				toUrl = IDefXFive.URL_MORE_FRIEND;
			}
			else if (((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN) && needImportmsn(host)) {
				toUrl = IDefXFive.STEP_IMPORT_MSN;
			} 

		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	private String goWhereXFiveFastPass(User host, int step) {
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			}

		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	private String goWhereXFiveBrown(User host, int step) {
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if(step == 0 || (step & STEP_SEARCH_FRIEND) != STEP_SEARCH_FRIEND){
				toUrl = IDefXFive.URL_SEARCH_FRIEND;
			} 
			else if ((step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} 
			else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			}
			//调整顺序试一下 2010-5-26 15:48:21
			else if ((step & STEP_MOREFRIEND) != STEP_MOREFRIEND){
				toUrl = IDefXFive.URL_MORE_FRIEND;
			}
			else if (((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN) && needImportmsn(host)) {
				toUrl = IDefXFive.STEP_IMPORT_MSN;
			} 
			else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} 
			
			//STEP_IMPORT_MSN

		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	private String goWhereXFiveBrown_0(User host, int step) {
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if(step == 0 || (step & STEP_SEARCH_FRIEND) != STEP_SEARCH_FRIEND){
				toUrl = IDefXFive.URL_SEARCH_FRIEND;
			} 
			else if (((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN)) {
				toUrl = IDefXFive.STEP_IMPORT_MSN;
			} 
			else if ((step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} 
			else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			} 
			else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} 
			else if ((step & STEP_MOREFRIEND) != STEP_MOREFRIEND){
				toUrl = IDefXFive.URL_MORE_FRIEND;
			}
			
			//STEP_IMPORT_MSN

		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}


	private String goWhereSKDefault(User host, int step) {
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 || (step & STEP_LANDING_PAGE) != STEP_LANDING_PAGE) {
				toUrl = IDefXFive.URL_LANDING_PAGE;
			} 


		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}

	private boolean needImportmsn(User host) {
		if(host == null)return false;
		GuideLogger.printLog(" host:"+ host.getId() +" [getStage]="+host.getStage());
		int identity = GuideXFiveUseIdentityLogic.getInstance().getIdentity(host.getId());
		GuideLogger.printLog(" host:"+ host.getId() +" [getIdentity]="+identity);
		if(identity == GuideXFiveUseIdentityLogic.IDENTITY_WORK){
			return true;
		}
		if(host.getStage() == Stage.STAGE_MAJOR){
			return true;
		}
		return false;
	}

	//2009-12-24 11:40:59 诗伦的整合版，用于0 1 2 4 5 6 7 8。分别用CyanA与CyanB
	private String goWhereXFive(User host, int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		if(GuideXFiveUserFlagLogic.getInstance().isXFiveCyanAUser(host)){
			GuideLogger.printLog(" host:"+ host.getId() + " goWhereXFiveCyanA -> ");
			return goWhereXFiveCyanA(host, step);
		}
		/*if(GuideXFiveUserFlagLogic.getInstance().isXFiveCyanBUser(host)){
			GuideLogger.printLog(" host:"+ host.getId() + " goWhereXFiveCyanB -> ");
			return goWhereXFiveCyanB(host, step);
		}*/
		
		if(GuideXFiveUserFlagLogic.getInstance().isXFiveCoralUser(host)){
			GuideLogger.printLog(" host:"+ host.getId() + " isXFiveCoralUser -> ");
			return goWhereXFiveCoral(host, step);
		}
		
		
		
		GuideLogger.printLog(" host:"+ host.getId() + "is X5default?:" +GuideXFiveUserFlagLogic.getInstance().isXFiveXUser(host));
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} 
			//去掉Lading步，2010-1-29 17:39:27 诗伦需求
			/*else if (((step & STEP_LANDING_PAGE) != STEP_LANDING_PAGE) ) {
				toUrl = IDefXFive.URL_LANDING_PAGE;
			}*/
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	@SuppressWarnings("unused")
	private String goWhereXFive20091224(User host, int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		if(GuideXFiveUserFlagLogic.getInstance().isXFiveBlueUser(host)){//2009-12-1 16:10:13 X5V4 XFive-Blue 应用于尾号为7的用户
			GuideLogger.printLog(" host:"+ host.getId() + " goWhereXFiveBlue -> ");
			return goWhereXFiveBlue(host, step);
		}
		if(GuideXFiveUserFlagLogic.getInstance().isXFiveCrimsonUser(host)){//2009-12-8 17:34:11 X5V? XFive-Corn 应用于尾号为2的用户
//			GuideLogger.printLog(" host:"+ host.getId() + " goWhereXFiveCorn -> ");
//			return goWhereXFiveCorn(host, step);
			GuideLogger.printLog(" host:"+ host.getId() + " goWhereNetBarXFive -> ");
			return goWhereNetBarXFive(host, step);
		}
		
//		if(host.getId() % 10 == 8){//2009-12-8 20:00:34 X5V6 XFive-Corn 应用于尾号为8的用户
//			GuideLogger.printLog(" host:"+ host.getId() + " goWhereXFiveCorn -> ");
//			return goWhereXFiveCoral(host, step);
//		}
//		else if(host.getId() % 10 == 4){//2009-12-2 12:01:23 X5V5 XFive-Brown 应用于尾号为4的用户
//			GuideLogger.printLog(" host:"+ host.getId() + " goWhereXFiveBrown -> ");
//			return goWhereXFiveBrown(host, step);
//		}
		
		GuideLogger.printLog(" host:"+ host.getId() + "is X5default?:" +GuideXFiveUserFlagLogic.getInstance().isXFiveXUser(host));
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} else if (((step & STEP_LANDING_PAGE) != STEP_LANDING_PAGE) ) {
				toUrl = IDefXFive.URL_LANDING_PAGE;
			}
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	private String goWhereNetBarXFive(User host, int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				//为了网吧 2009-12-14 16:33:47
				toUrl = IDefXFive.URL_FILL_INFO;
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} else if (((step & STEP_LANDING_PAGE) != STEP_LANDING_PAGE) ) {
				toUrl = IDefXFive.URL_LANDING_PAGE;
			}
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	private String goWhereFromLeadToApp(User host,int step){
	    GuideLogger.printLog(" host:"+ host.getId() + " - start");
        
        String toUrl = LEAD_COMPLETE_URI;
        try {
            if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
                toUrl = IDefXFive.URL_FILL_INFO;
            } 
            else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
                toUrl = IDefXFive.URL_PREVIEW;
            } 
            else if (((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN) && needImportmsn(host)) {
                toUrl = IDefXFive.STEP_IMPORT_MSN;
            } 
            else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
                toUrl = IDefXFive.URL_UPLOAD_HEAD;
            } 
            else if ((step & STEP_MOREFRIEND) != STEP_MOREFRIEND){
                toUrl = IDefXFive.URL_MORE_FRIEND;
            }
            else if(step == 0 ||(step & STEP_APP) != STEP_APP){
                toUrl = IDefXFive.URL_GO_APP;
            } 
        }
        catch (Exception e) {
            GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
            e.printStackTrace();
        }
        return toUrl;
	}
	
	private String goWhereXFiveFromAppToLead(User host, int step) {
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		
		String toUrl = LEAD_COMPLETE_URI;
		try {
			if(step == 0 ||(step & STEP_APP) != STEP_APP){
				toUrl = IDefXFive.URL_GO_APP;
			} 
			else if ((step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefXFive.URL_FILL_INFO;
			} 
			else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefXFive.URL_PREVIEW;
			} 
			else if (((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN) && needImportmsn(host)) {
				toUrl = IDefXFive.STEP_IMPORT_MSN;
			} 
			else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefXFive.URL_UPLOAD_HEAD;
			} 
			else if ((step & STEP_MOREFRIEND) != STEP_MOREFRIEND){
				toUrl = IDefXFive.URL_MORE_FRIEND;
			}
		}
		catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		return toUrl;
	}
	
	private String goWhereNetbar(User host, int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				//为了网吧 2009-12-14 16:33:47
				toUrl = IDefXFive.URL_FILL_INFO;
			} 
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	private String goWhereFlashFarm(User host,int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			
			if(step == 0 || (step & STEP_FLASH_APP_OPEN) != STEP_FLASH_APP_OPEN){
				toUrl = IDefFlashFarm.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+1);
			} else if ((step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefFlashFarm.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+2);
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefFlashFarm.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+3);
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefFlashFarm.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+4);
			} else if ((step & STEP_APP) != STEP_APP){
				toUrl = IDefFlashFarm.URL_GO_APP;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+5);
			}
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	private String goWhereAppMazimianguan(User host,int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			
			if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefAppMazimainguan.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+2);
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefAppMazimainguan.URL_PREVIEW;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+3);
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefAppMazimainguan.URL_UPLOAD_HEAD;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+4);
			} else if ((step & STEP_APP) != STEP_APP){
				toUrl = IDefAppMazimainguan.URL_GO_APP;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+5);
			}
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	private String goWhereFlashCook(User host,int step){
		GuideLogger.printLog(" host:"+ host.getId() + " - start");
		String toUrl = LEAD_COMPLETE_URI;
		try {
			GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
			
			if(step == 0 || (step & STEP_FLASH_APP_OPEN) != STEP_FLASH_APP_OPEN){
				toUrl = IDefFlashCook.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+1);
			} else if ((step & STEP_FILLINFO) != STEP_FILLINFO) {
				toUrl = IDefFlashCook.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+2);
			} else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
				toUrl = IDefFlashCook.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+3);
			} else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
				toUrl = IDefFlashCook.URL_FILL_INFO;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+4);
			} else if ((step & STEP_APP) != STEP_APP){
				toUrl = IDefFlashCook.URL_GO_APP;
				GuideLogger.printLog(" host:"+ host.getId() +" [Step]="+5);
			}
		} catch (Exception e) {
			GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
			e.printStackTrace();
		}
		GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
		return toUrl;
	}
	
	public int getGuideUserType(User host){
		String fromsource = RegFromSourceLogic.getFromSource(host);
		GuideLogger.printLog(" host:" + host.getId() + "-" + host.getName() + " from source is : "+fromsource);
		
		return getGuideUserType(host, fromsource);
	}
	
	
	public int getGuideUserType(User host,String fromsource){
		
//		//别忘了手机的……
//		if(fromsource == null){
//			return goWhereByStage(host,stepstatus);
//		}
		if(MyStringUtils.contains(host.getDomain(), "kaixin")){
			if(GuideXFiveUserFlagLogic.getInstance().isSKDefault(host)){
				GuideLogger.printLog(" host:" + host.getId()+" -> SKDefault");
				return IGuideUserLeadType.SKDefault;
			}
		}
		if(MyStringUtils.containsIgnoreCase(host.getDomain(), "imop")){
		    return IGuideUserLeadType.IMopFirst;
		}
		
		
		List<String> fromlist = RegFromSourceLogic.getFromSoureList(fromsource);
		if(!"".equals("")){
			return IGuideUserLeadType.XFiveGeneral;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_FROMAPPTOLEAD)){
			GuideLogger.printLog(" host:" + host.getId()+" -> AppToLead");
			return IGuideUserLeadType.XFiveFromAppToLead;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_FROMLEADTOAPP)){
		    GuideLogger.printLog(" host:" + host.getId()+" -> LeadToApp");
		    return IGuideUserLeadType.XFiveFromLeadToApp;
		}
		//面馆需求下掉 2010-3-12 14:30:40
		/*else if(fromlist.contains(RegFromSourceLogic.FS_MAZIMIANGUAN)){
			GuideLogger.printLog(" host:" + host.getId()+" -> XFiveMZMG");
			return IGuideUserLeadType.XFiveMZMG;
		}*/
		//2010-8-30 18:36:16 01新生引导
		else if(fromlist.contains(RegFromSourceLogic.FS_XS2010)){
		    GuideLogger.printLog(" host:" + host.getId()+" -> FS_XS2010");
            return IGuideUserLeadType.XS2010;
		}
		else if(fromlist.contains("NETBAR")){
			GuideLogger.printLog(" host:" + host.getId()+" -> Netbar");
			return IGuideUserLeadType.Netbar;
		}
		else if(fromlist.contains("S_FARMREG")){
			GuideLogger.printLog(" host:" + host.getId()+" -> FlashFarm");
			return IGuideUserLeadType.FlashFarm;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_COOKREG)){
			GuideLogger.printLog(" host:" + host.getId()+" -> FlashCook");
			return IGuideUserLeadType.FlashCook;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_NETBARXFIVE)){
			GuideLogger.printLog(" host:" + host.getId()+" -> XFiveNetbar");
			return IGuideUserLeadType.XFiveNetbar;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_LEAD_AAA)){
		    GuideLogger.printLog(" host:" + host.getId()+" -> FS_LEAD_AAA(Addressbook_lantian)");
	        return IGuideUserLeadType.Lead_AAA;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_LEAD_AAE)){
		    GuideLogger.printLog(" host:" + host.getId()+" -> FS_LEAD_AAE(Addressbook_new)");
	        return IGuideUserLeadType.Lead_AAE;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_LEAD_AAF)){
		    GuideLogger.printLog(" host:" + host.getId()+" -> FS_LEAD_AAF");
	        return IGuideUserLeadType.Lead_AAF;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_LEAD_AAH)){
		    GuideLogger.printLog(" host:" + host.getId()+" -> FS_LEAD_AAH");
	        return IGuideUserLeadType.Lead_AAH;
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_LEAD_AAI)){
			GuideLogger.printLog(" host:" + host.getId()+" -> FS_LEAD_AAI");
		    return IGuideUserLeadType.Lead_AAI;
		}
		else{
			//X5引导只留下Brown的 - 2010-2-25 15:25:16 - Inshion 
			/*if(GuideXFiveUserFlagLogic.getInstance().isXFiveCoralBUser(host)){
				GuideLogger.printLog(" host:"+ host.getId() + " -> XFiveCoralB");
				return IGuideUserLeadType.XFiveCoral;
			}
			
			if(GuideXFiveUserFlagLogic.getInstance().isXFiveCyanAUser(host)){
				GuideLogger.printLog(" host:"+ host.getId() + " -> XFiveCyanA");
				return IGuideUserLeadType.XFiveCyanA;
			}
			
			if(GuideXFiveUserFlagLogic.getInstance().isXFiveCoralUser(host)){
				GuideLogger.printLog(" host:"+ host.getId() + " -> XFiveCoral");
				return IGuideUserLeadType.XFiveCoralB;
			}*/
			//2010-4-19 10:48:05 0 的效果不好，去掉了
			/*if(GuideXFiveUserFlagLogic.getInstance().isXFiveBrown0User(host)){
				GuideLogger.printLog(" host:"+ host.getId() + " -> XFiveBrown_0");
				return IGuideUserLeadType.XFiveBrown_0;
			}*/
			//2010-5-18 10:40:00 用0到4的尾号上新页面
			if (GuideXFiveUserFlagLogic.getInstance().isXFiveDarkBlueUser(host)) {
				GuideLogger.printLog(" host:" + host.getId()+ " -> XFiveDarkBlue");
				return IGuideUserLeadType.XFiveDarkBlue;
			}
			if(GuideXFiveUserFlagLogic.getInstance().isXFiveBrownUser(host)){
				GuideLogger.printLog(" host:"+ host.getId() + " -> XFiveBrown");
				return IGuideUserLeadType.XFiveBrown;
			}
			GuideLogger.printLog(" host:" + host.getId()+" -> XFiveGeneral");
			return IGuideUserLeadType.XFiveGeneral;
		}
	}
	
	public String goWhere(User host,String fromsource,int step){
		String toUrl = LEAD_COMPLETE_URI;
		int t = getGuideUserType(host,fromsource);
		GuideLogger.printLog(" host:" + host.getId()+" type:"+t);
		switch(t){
		case IGuideUserLeadType.SKDefault:
			toUrl = goWhereSKDefault(host, step);
			break;
		case IGuideUserLeadType.XFiveDefault:
		case IGuideUserLeadType.XFiveGeneral:
			GuideLogger.printLog(" host:" + host.getId(),GuideLogger.WARN);
			toUrl = goWhereXFive(host, step);
			break;
		case IGuideUserLeadType.XFiveMZMG:
			toUrl = goWhereAppMazimianguan(host, step);
			break;
		case IGuideUserLeadType.FlashCook:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereFlashCook(host, step);
			break;
		case IGuideUserLeadType.FlashFarm:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereFlashFarm(host, step);
			break;
		case IGuideUserLeadType.Netbar:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereNetbar(host, step);
			break;
		case IGuideUserLeadType.XFiveNetbar:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereNetBarXFive(host, step);
			break;
		case IGuideUserLeadType.XFiveCoral:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereXFiveCoral(host, step);
			break;
		case IGuideUserLeadType.XFiveCoralB:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereXFiveCoral(host, step);
			break;
		case IGuideUserLeadType.XFiveCyanA:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereXFiveCyanA(host, step);
			break;
		case IGuideUserLeadType.XFiveBrown:
		case IGuideUserLeadType.XFiveDarkBlue:
		case IGuideUserLeadType.XS2010:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereXFiveBrown(host, step);
			break;
		case IGuideUserLeadType.IMopFirst:
		    GuideLogger.printLog(" host:" + host.getId());
            toUrl = goWhereIMopFirst(host, step);
		    break;
		case IGuideUserLeadType.XFiveBrown_0:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereXFiveBrown_0(host, step);
			break;
		case IGuideUserLeadType.XFiveFromAppToLead:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereXFiveFromAppToLead(host, step);
			break;
		case IGuideUserLeadType.XFiveFromLeadToApp:
		    GuideLogger.printLog(" host:" + host.getId());
		    toUrl = goWhereFromLeadToApp(host, step);
		    break;
		case IGuideUserLeadType.Lead_AAA:
		    GuideLogger.printLog(" host:" + host.getId());
            toUrl = goWhereLeadLantianbaiyun(host, step);
            break;
		case IGuideUserLeadType.Lead_AAE:
		case IGuideUserLeadType.Lead_AAH:
		    GuideLogger.printLog(" host:" + host.getId());
            toUrl = goWhereLeadAddressbook(host, step);
            break;
		case IGuideUserLeadType.Lead_AAF:
		    GuideLogger.printLog(" host:" + host.getId());
            toUrl = goWhereXFiveKouxiangtang(host, step);
            break;
		case IGuideUserLeadType.Lead_AAI:
			GuideLogger.printLog(" host:" + host.getId());
			toUrl = goWhereXFiveFastPass(host, step);
			break;
		default:
			GuideLogger.printLog(" host:" + host.getId(),GuideLogger.WARN);
			break;
		}
		return toUrl;
	}


	private String goWhereLeadLantianbaiyun(User host, int step) {
        GuideLogger.printLog(" host:"+ host.getId() + " - start");
        String toUrl = LEAD_COMPLETE_URI;
        try {
            GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
            if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
                toUrl = IDefXFive.URL_FILL_INFO;
            } 
            else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
                toUrl = IDefXFive.URL_PREVIEW;
            }
            else if (((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN) && needImportmsn(host)) { //蓝天白云的，还那么着
                toUrl = IDefXFive.STEP_IMPORT_MSN;
            } 
            else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
                toUrl = IDefXFive.URL_UPLOAD_HEAD;
            } 

        } catch (Exception e) {
            GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
            e.printStackTrace();
        }
        GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
        return toUrl;
    }
	
	private String goWhereLeadAddressbook(User host, int step) {
        GuideLogger.printLog(" host:"+ host.getId() + " - start");
        String toUrl = LEAD_COMPLETE_URI;
        try {
            GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
            if (step == 0 || (step & STEP_FILLINFO) != STEP_FILLINFO) {
                toUrl = IDefXFive.URL_FILL_INFO;
            } 
            else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
                toUrl = IDefXFive.URL_PREVIEW;
            }
            else if (((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN) && /*needImportmsn(host)*/ true) { //song.bao的需求，所有人都过msn
                toUrl = IDefXFive.STEP_IMPORT_MSN;
            } 
            else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
                toUrl = IDefXFive.URL_UPLOAD_HEAD;
            } 

        } catch (Exception e) {
            GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
            e.printStackTrace();
        }
        GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
        return toUrl;
    }

    private String goWhereIMopFirst(User host, int step) {
	    GuideLogger.printLog(" host:"+ host.getId() + " - start");
        String toUrl = LEAD_COMPLETE_URI;
        try {
            GuideLogger.printLog(" host:"+ host.getId() +" [StepStatus]="+step);
            if ((step & STEP_FILLINFO) != STEP_FILLINFO) {
                toUrl = IDefXFive.URL_FILL_INFO;
            } 
            else if ((step & STEP_PREVIEW) != STEP_PREVIEW) {
                toUrl = IDefXFive.URL_PREVIEW;
            }
            else if (((step & STEP_IMPORT_MSN) != STEP_IMPORT_MSN) && needImportmsn(host)) {
                toUrl = IDefXFive.STEP_IMPORT_MSN;
            } 
            else if ((step & STEP_UPLOAD_HEAD) != STEP_UPLOAD_HEAD) {
                toUrl = IDefXFive.URL_UPLOAD_HEAD;
            } 
            
            //STEP_IMPORT_MSN

        } catch (Exception e) {
            GuideLogger.printLog(" host:"+ host.getId() + "  error"+e.toString(),GuideLogger.ERROR);
            e.printStackTrace();
        }
        GuideLogger.printLog(" host:"+ host.getId() +" [toUrl]="+toUrl);
        return toUrl;
    }

    @SuppressWarnings("unused")
	private String goWhere20100128(User host,String fromsource, int stepstatus){
		GuideLogger.printLog(" host:" + host.getId() + "-" + host.getName() + " from source is : "+fromsource);
		
//		//别忘了手机的……
//		if(fromsource == null){
//			return goWhereByStage(host,stepstatus);
//		}
		
		
		List<String> fromlist = RegFromSourceLogic.getFromSoureList(fromsource);
		if(!"".equals("")){
			GuideLogger.printLog(" host:"+host.getId(),GuideLogger.FATAL);
			GuideLogger.printLog(" host:" + host.getId()+" -> goWhereXFive(host)");
			return goWhereXFive(host,stepstatus);
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_MAZIMIANGUAN)){
			GuideLogger.printLog(" host:" + host.getId()+" -> goWhereAppMazimianguan(host)");
			return goWhereAppMazimianguan(host,stepstatus);
		}
		//2009-12-24 11:25:29 陈周的需求，把网吧的优先级调高
		else if(fromlist.contains("NETBAR")){
			GuideLogger.printLog(" host:" + host.getId()+" -> goWhereNetbar(host)");
			return goWhereNetbar(host,stepstatus);
		}
		else if(fromlist.contains("S_FARMREG")){
			GuideLogger.printLog(" host:" + host.getId()+" -> goWhereFlashFarm(host)");
			return goWhereFlashFarm(host,stepstatus);
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_COOKREG)){
			GuideLogger.printLog(" host:" + host.getId()+" -> goWhereFlashCook(host)");
			return goWhereFlashCook(host,stepstatus);
		}
		else if(fromlist.contains(RegFromSourceLogic.FS_NETBARXFIVE)){
			GuideLogger.printLog(" host:" + host.getId()+" -> goWhereNetBarXFive(host)");
			return goWhereNetBarXFive(host,stepstatus);
		}
		else{
			GuideLogger.printLog(" host:" + host.getId()+" -> goWhereXFive(host)");
			return goWhereXFive(host,stepstatus);
//			return "r:guide.do";
		}
//		if(StringUtils.equals("S_FARMREG", fromsource)){
//			return goWhereFlashfarm(host,stepstatus);
//		}
//		
//		if(StringUtils.equals("S_COOKREG", fromsource)){
//			return goWhereFlashCook(host,stepstatus);
//		}
//		
//		
//		return  goWhereByStage(host,stepstatus);
	}
	
}
