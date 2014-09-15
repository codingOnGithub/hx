package com.hotent.core.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hotent.core.bpm.cache.ActivitiDefCache;
import com.hotent.core.cache.ICache;
import com.hotent.core.web.tag.AnchorTag;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.bpm.thread.TaskUserAssignService;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysPaurService;
import com.hotent.platform.service.system.SysUserService;

/**
 * 取得当前用户登录时的上下文环境，一般用于获取当前登录的用户
 * @author csx
 *
 */
public class ContextUtil {
	private static Logger logger=LoggerFactory.getLogger(ContextUtil.class);
	private static ThreadLocal<String> curUserAccount=new ThreadLocal<String>();
	private static ThreadLocal<SysUser> curUser=new ThreadLocal<SysUser>();
	private static ThreadLocal<Locale> curLocale = new ThreadLocal<Locale>();
	//当前组织。
	private static ThreadLocal<SysOrg> curOrg=new ThreadLocal<SysOrg>();
	//当前岗位
	private static ThreadLocal<Position> curPos=new ThreadLocal<Position>();
		
	public static final String CurrentOrg="CurrentOrg_";
	
	public static final String CurrentPos="CurrentPos_";
	
	//连接超时时间
	private static Integer _connTimeout = 0;
	//读取超时时间
	private static Integer _readTimeout = 0;
	
	/**
	 * 获取连接超时时间
	 * @return
	 */
	public static Integer getConnectTimeout() {
		if(_connTimeout.intValue()==0){
			_connTimeout = Integer.parseInt(AppConfigUtil.get("webservice.connTimeout"));
			if(_connTimeout.intValue()==0)
				_connTimeout = 3000;
		}
		return _connTimeout;
	}
	/**
	 * 获取读取超时时间
	 * @return
	 */
	public static Integer getReadTimeout() {
		if(_readTimeout.intValue()==0){
			_readTimeout = Integer.parseInt(AppConfigUtil.get("webservice.readTimeout"));
			if(_readTimeout.intValue()==0)
				_readTimeout = 3000;
		}
		return _readTimeout;
	}
	
	/**
	 * 取得当前登录的用户。
	 * <pre>
	 * 1.首先尝试从线程变量中获取获取当前用户，线程变量的是通过setCurrentUserAccount方法进行设置。
	 * 
	 * 2.如果没有获取到则从登录用户中进行获取。
	 * <pre>
	 * @return
	 */
	public static SysUser getCurrentUser(){
		//通过setCurrentUserAccount设置的用户。
		if(curUser.get()!=null){
			SysUser user=curUser.get();
			return user;
		}
		SysUser sysUser=null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication auth = securityContext.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof SysUser) {
                	sysUser=(SysUser)principal;
                	setLog4jMDC(sysUser);
                }
            } 
        }
        
        return sysUser;
	}
	
	/**
	 * 获取当前用户岗位对象。
	 * <pre>
	 * 1.如果设置了主岗位，则取主岗位。
	 * 2.如果没有设置则或其中的一个。
	 * 3.没有岗位则返回为空。
	 * </pre>
	 * @return
	 */
//	public static Position getCurrentPos(){
//		SysUser curUser=getCurrentUser();
//		PositionService positionService=(PositionService)AppUtil.getBean("positionService");
//		
//		//List<Position> positions=positionService.getByUserId(curUser.getUserId());
//		UserPositionService userPositionService=(UserPositionService)AppUtil.getBean("userPositionService");
//		List<UserPosition> userPositions=userPositionService.getByUserId(curUser.getUserId());
//		if(BeanUtils.isNotEmpty(userPositions)){
//			Position posMain=null;
//			for(UserPosition pos:userPositions){
//				if(pos.getIsPrimary().shortValue()==1){
//					posMain=positionService.getById(pos.getPosId());
//					return posMain;
//				}
//			}
//			if(posMain==null){
//				return positionService.getById(userPositions.get(0).getPosId()); 
//			}
//		}
//		return null;
//	}
	
	/**
	 * 获取当前语言环境
	 * @return
	 */
	public static Locale getLocale(){
		if(curLocale.get()!=null){
			return curLocale.get();
		}
		setLocale(new Locale("zh","CN"));
		return curLocale.get();
	}
	
	/**
	 * 设置当前语言环境
	 * @param locale
	 */
	public static void setLocale(Locale locale){
		curLocale.set(locale);
	}
	
	/**
	 * 取得当前用户的ID
	 * @return
	 */
	public static Long getCurrentUserId(){
		SysUser curUser=getCurrentUser();
		if(curUser!=null) return curUser.getUserId();
		return 0L;
	}
	/**
	 * 设置当前用户账号
	 * @param curUserAccount
	 */
	public static void setCurrentUserAccount(String account){
		SysUserService sysUserService=(SysUserService)AppUtil.getBean("sysUserService");
		SysUser sysUser=sysUserService.getByAccount(account);
		curUser.set(sysUser);
		setLog4jMDC(sysUser);
	}
	/**
	 * 设置当前用户
	 * @param sysUser
	 */
	public static void setCurrentUser(SysUser sysUser){
		curUser.set(sysUser);
		setLog4jMDC(sysUser);
	}
	
	/**
	 * 设置Log4j的MDC
	 * @param user
	 */
	private static void setLog4jMDC(SysUser user){
		if(user!=null){
			MDC.put("current_user_id", user.getUserId());
			MDC.put("current_user_name", user.getFullname());
			MDC.put("current_user_account",user.getAccount());
		}
	}
	/**
	 * 设置当前岗位。
	 * @param orgId
	 */
	public static void setCurrentPos(Long posId){
		PositionService positionService=(PositionService)AppUtil.getBean("positionService");
		Position position=positionService.getById(posId);
		HttpServletRequest request= RequestUtil.getHttpServletRequest();
		HttpServletResponse response= RequestUtil.getHttpServletResponse();
		HttpSession session= request.getSession();
		saveSessionCookie(position,request,response,session);
	}
	
	/**
	 * 从当前session中设置当前人的岗位数据。
	 * <pre>
	 * 首先判断session中是否有 岗位 数据。
	 * 1.如果组织获取为空。
	 * 	    1.1从coolie中获取当前 岗位 Id。
	 *      
	 *          判断此id是否为空。
	 *          1.1.1为空的情况。
	 *      	根据当前用户id从数据库获取默认的组织对象.
	 *      
	 *         2.不为空则根据岗位ID获取
	 *      	根据组织ID获取岗位对象
	 * 2.获取不为空。
	 * 
	 * 判定岗位对象是否为空
	 *  不为空则加入到session和cookie中。
	 *  并设置当前session线程变量和缓存。
	 * </pre>
	 */
	public static void getCurrentPosFromSession(){
		HttpServletRequest request= RequestUtil.getHttpServletRequest();
		HttpServletResponse response= RequestUtil.getHttpServletResponse();
		Position position = null;
		HttpSession session =  request.getSession();
		//从session中获取。
		if(request!=null){
			position = (Position)session.getAttribute(ContextUtil.CurrentPos);			
		}
		Long userId=getCurrentUserId();
		if(position==null){
			PositionService positionService=(PositionService)AppUtil.getBean("positionService");
			//从cookie中获取。
			String currentPosId= CookieUitl.getValueByName(ContextUtil.CurrentPos, request);
			if(StringUtil.isEmpty(currentPosId)) {
				position = positionService.getDefaultPosByUserId(userId);
			}
			else{
				//从数据库中获取。
				Long posId=Long.parseLong(currentPosId);
				position= positionService.getById(posId);
			}
			if(position!=null){
				//设置cookie和sesion。
				saveSessionCookie(position,request,response,session);
			}
		}
		if(position!=null){
			ContextUtil.setCurrentPos(position);
			ContextUtil.setCurrentOrg(getOrgByPos(position));
		}
	}
	/**
	 * 根据岗位得到组织
	 * @author hjx
	 * @version 创建时间：2014-1-21  上午11:42:24
	 * @param position
	 * @return
	 */
	private  static SysOrg getOrgByPos(Position position){
		SysOrgService sysOrgService=(SysOrgService)AppUtil.getBean("sysOrgService");
		SysOrg sysOrg = sysOrgService.getById(position.getOrgId());
		return sysOrg;
	}
	
	/**
	 * 设置默认的岗位。
	 */
	public static void setDefaultPos(){
		HttpServletRequest request= RequestUtil.getHttpServletRequest();
		HttpServletResponse response= RequestUtil.getHttpServletResponse();
		
		HttpSession session = null;
		//从session中获取。
		if(request!=null){
			session = request.getSession();
			session.removeAttribute(ContextUtil.CurrentPos);
			session.removeAttribute(ContextUtil.CurrentOrg);
		}
		CookieUitl.delCookie(ContextUtil.CurrentPos, request, response);
		CookieUitl.delCookie(ContextUtil.CurrentOrg, request, response);
		PositionService positionService=(PositionService)AppUtil.getBean("positionService");
		Long userId=getCurrentUserId();
		Position position = positionService.getDefaultPosByUserId(userId);
		
		if(position!=null){
			saveSessionCookie(position,request,response,session);
			ContextUtil.setCurrentPos(position);
			ContextUtil.setCurrentOrg(getOrgByPos(position));
		}
		
		
	}
	/**
	 * 获取当前组织。
	 * <pre>
	 * 1.从线程中获取当前用户的组织。
	 * 2.如果获取不到则根据当前用户去缓存中获取。
	 * </pre>
	 * @return
	 */
	public static SysOrg getCurrentOrg(){
		SysOrg sysOrg =null;
		Long userId=getCurrentUserId();
		if(userId>0){
			ICache iCache=(ICache)AppUtil.getBean(ICache.class);
			String userKey=ContextUtil.CurrentOrg + userId;
			sysOrg=(SysOrg)iCache.getByKey(userKey);
		}
		if(sysOrg==null){
			sysOrg = curOrg.get();
		}
		return sysOrg;
	}
	/**
	 * 获取当前组织。
	 * <pre>
	 * 1.从线程中获取当前用户的组织。
	 * 2.如果获取不到则根据当前用户去缓存中获取。
	 * </pre>
	 * @return
	 */
	public static Position getCurrentPos(){
		Position position =null;
		Long userId=getCurrentUserId();
		if(userId>0){
			ICache iCache=(ICache)AppUtil.getBean(ICache.class);
			String userKey=ContextUtil.CurrentPos + userId;
			position=(Position)iCache.getByKey(userKey);
		}
		if(position==null){
			position = curPos.get();
		}
		return position;
	}
	
	/**
	 *  获取当前组织ID
	 * @return
	 */
	public static Long getCurrentOrgId(){
		Position position = getCurrentPos();
		if(position!=null) return position.getOrgId();
		return null;
	}
	
	/**
	 * 取当前用户皮肤设置。
	 * @return
	 */
	public static String getCurrentUserSkin(HttpServletRequest request){
		String skinStyle="default";
		
		HttpSession session=request.getSession();
		String skin=(String)session.getAttribute("skinStyle");
		if(StringUtil.isNotEmpty(skin)) return skin;
		
		SysPaurService sysPaurService=(SysPaurService)AppUtil.getBean("sysPaurService");
		Long userId = getCurrentUserId();		
		skinStyle=sysPaurService.getCurrentUserSkin(userId);	
		session.setAttribute("skinStyle", skinStyle);
		return skinStyle;
	}
	
	/**
	 * 设置当前组织。
	 * @param sysOrg
	 */
	public static void setCurrentOrg(SysOrg sysOrg){
		if(sysOrg==null) return;
		curOrg.set(sysOrg);
	}
	
	/**
	 * 设置当前岗位。
	 * @param sysOrg
	 */
	public static void setCurrentPos(Position position){
		if(position==null) return;
		curPos.set(position);
	}
	

	/**
	 * 清除当前组织对象。
	 */
	public static void cleanCurrentPos(){
		curPos.remove();
	}
	
	/**
	 * 清除当前用户。
	 */
	public static void cleanCurUser(){
		curUser.remove();
	}
	
	/**
	 * 将组织数据存放到session，cookie和缓存中。
	 * @param sysOrg
	 * @param request
	 * @param response
	 * @param session
	 */
	private static void saveSessionCookieWithOrg(Position position,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Long orgId=position.getPosId();
		SysOrg sysOrg=getOrgByPos(position);
		//放到session。
		session.setAttribute(CurrentOrg, position);
		//获取组织id。
		//添加cookie。
		CookieUitl.addCookie(CurrentOrg, orgId.toString(), request, response);
		
		Long userId=ContextUtil.getCurrentUserId();
		
		//将当前人和组织放到缓存中。
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		String userKey=ContextUtil.CurrentOrg + userId;
		iCache.add(userKey, sysOrg);
	}
	
	/**
	 * 将岗位数据存放到session，cookie和缓存中。
	 * @param sysOrg
	 * @param request
	 * @param response
	 * @param session
	 */
	private static void saveSessionCookie(Position position,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//放到session。
		session.setAttribute(CurrentPos, position);
		//获取组织id。
		Long posId=position.getPosId();
		//添加cookie。
		CookieUitl.addCookie(CurrentPos, posId.toString(), request, response);
		
		Long userId=ContextUtil.getCurrentUserId();
		
		//将当前人和组织放到缓存中。
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		String userKey=ContextUtil.CurrentPos + userId;
		iCache.add(userKey, position);
		
		saveSessionCookieWithOrg( position, request, response, session);
	}
	
	/**
	 * 从session和cookie中清除当前组织。
	 * @param request
	 * @param response
	 */
	public static void removeCurrentOrg(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession(false);
		if(session!=null){
			session.removeAttribute(CurrentPos);
			session.removeAttribute(CurrentOrg);
		}
		CookieUitl.delCookie(CurrentPos,  request, response);
		CookieUitl.delCookie(CurrentOrg,  request, response);
		Long userId=ContextUtil.getCurrentUserId();
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		String userKey=ContextUtil.CurrentPos + userId;
		iCache.delByKey(userKey);
		iCache.delByKey(ContextUtil.CurrentOrg + userId);
	}
	
	/**
	 * 清除所有的线程变量。
	 */
	public static void clearAll(){
		curUser.remove();
		curOrg.remove();
		curPos.remove();
		curLocale.remove();
		
		RequestUtil.clearHttpReqResponse();
		TaskThreadService.clearAll();
		TaskUserAssignService.clearAll();
		MessageUtil.clean();
		//清除流程缓存。
		
		SysUser.removeRoleList();
		
		AnchorTag.cleanFuncRights();
		
		
		ActivitiDefCache.clearLocal();
		
		
	}
	
	
	
	/**
	 * 当退出系统时，清除当前用户
	 * add by taoguifang at 2012/1/3
	 */
	public static void removeCurrentUser(){
		curUserAccount.remove();		
	}
	
	
	/**
	 * 通过资源的key获得对于key语言
	 * @param code 资源的key
	 * @return
	 */
	public static String getMessages(String code){
		return getMessages(code, null);
	}
	/**
	 * 通过资源的key获得对于key语言
	 * @param code 资源的key
	 * @return
	 */
	public static String getMessagesL(String code,Locale locale){
		return getMessages(code, null,locale);
	}
	
	/**
	 * 	通过资源的key获得对于key语言
	 * @param code 资源的key
	 * @param args
	 * @param locale
	 * @return
	 */
	public static String getMessages(String code,Object[] args,Locale locale){
		AbstractMessageSource messages = (AbstractMessageSource) AppUtil.getBean("messageSource");
		if(locale == null)
			locale = getLocale();
		return messages.getMessage(code,args,locale);
	}
	
	/**
	 * 	通过资源的key获得对于key语言
	 * @param code 资源的key
	 * @param args
	 * @return
	 */
	public static String getMessages(String code,Object[] args){
		return getMessages(code, args, getLocale());
	}
}
