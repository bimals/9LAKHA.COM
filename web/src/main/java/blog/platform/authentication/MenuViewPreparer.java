package blog.platform.authentication;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/*import com.getinsured.hix.model.Broker;
import com.getinsured.hix.model.DesignateBroker;
import com.getinsured.hix.planmgmt.config.PlanmgmtConfiguration.PlanmgmtConfigurationEnum;
import com.getinsured.hix.platform.config.DynamicPropertiesUtil;*/
/**
 * 
 * This class is used to set:-
 * 		1. iFrame Context
 * 		2. tab menus from a property file
 */
@Component("menuViewPreparer")
public class MenuViewPreparer implements ViewPreparer {

	@Autowired
	private MessageSource messageSource;
	/**
	 * to capture menu property file
	 *
	 */
	private enum MENU{

		SIZE("{0}.size"),
		CAPTION("{0}.{1}.caption"), URL("{0}.{1}.url"), TITLE("{0}.{1}.title"), REDIRECTURL("{0}.{1}.redirecturl"),
		SUB_SIZE("{0}.{1}.size"),
		SUB_CAPTION("{0}.{1}.{2}.caption"), SUB_URL("{0}.{1}.{2}.url"), SUB_TITLE("{0}.{1}.{2}.title"), SUB_REDIRECTURL("{0}.{1}.{2}.redirecturl")
		;

		private final String key;

		MENU(final String k) { key = k; }
	}

	//private static final Logger LOGGER = Logger.getLogger(MenuViewPreparer.class);

	/**
	 * This is a call back method invoked from tiles-definitions.xml for following:-
	 * 	1. to set iFrame Context
	 *  2. to read tab menus from a property file
	 */
	//@Override
	public void execute(final Request tilesRequest, final AttributeContext attributeContext) {

		final Locale locale = LocaleContextHolder.getLocale();

		//set iFrame in session scope...
		setIFrameContext(tilesRequest, attributeContext);

		//read user's roles from context
		final List<String> roleNames = getLoggedInUserRoles(tilesRequest);
		if (!roleNames.isEmpty()){
			//set tab menus from property file in request scope...
			setTabMenu(tilesRequest, locale, roleNames);

			//set broker menu
			setBrokerMenu(tilesRequest);

			//set enrollment entity menu
			setAssisterEnrollmentEntityMenu(tilesRequest);
		}
	}

	private void setAssisterEnrollmentEntityMenu(final Request tilesRequest) {
		final Map<String, Object> sessionScope = tilesRequest.getContext("session");
		tilesRequest.getContext("request").put("assisterEnrollmentEntity", sessionScope.get("assisterEnrollmentEntity"));		
	}

	private void setBrokerMenu(final Request tilesRequest) {
		final Map<String, Object> sessionScope = tilesRequest.getContext("session");
		//final Broker designatedBrokerProfile = (Broker) sessionScope.get("designatedBrokerProfile");
		//final DesignateBroker designateBroker = (DesignateBroker) sessionScope.get("designateBroker");
		//tilesRequest.getContext("request").put("designatedBrokerProfile", designatedBrokerProfile);
		//tilesRequest.getContext("request").put("designateBroker", designateBroker);
	}

	private List<String> getLoggedInUserRoles(final Request tilesRequest) {
		List<String> roleNames = Collections.emptyList();

		// get the session attribute
		final Map<String, Object> sessionScope = tilesRequest.getContext("session");
		final String switchToModuleName = (String) sessionScope.get("switchToModuleName");

		roleNames = new ArrayList<String>();
		if(StringUtils.isNotEmpty(switchToModuleName)){
			roleNames.add(StringUtils.lowerCase(switchToModuleName) + "_menu");
		}


		tilesRequest.getContext("request").put("roleNames", roleNames);
		return roleNames;
	}

	/*private List<String> getLoggedInUserRoles(TilesRequestContext tilesContext) {
		List<String> roleNames = Collections.emptyList();

		// get the session attribute
		Map<String, Object> sessionScope = tilesContext.getSessionScope();
		@SuppressWarnings("unchecked")
		Set<UserRole> userRoles = (Set<UserRole>) sessionScope.get("userRoleList");
		if (userRoles != null){
			for (UserRole userRole : userRoles) {
				roleNames = new ArrayList<String>();
				roleNames.add(StringUtils.lowerCase(userRole.getRole().getName()) + "_menu");
			}
		}
		tilesContext.getRequestScope().put("roleNames", roleNames);
		return roleNames;
	}*/


	private void setIFrameContext(final Request tilesRequest,
			final AttributeContext attributeContext) {
		// get the session attributes scopes "request" and "application" try "session"
		final Map<String, Object> sessionScope = tilesRequest.getContext("session");
		String iframe = (String) sessionScope.get("iframe");
		if(iframe == null){
			iframe = "no";
		}

		attributeContext.putAttribute("iframe", new Attribute(iframe));
	}

	private void setTabMenu(final Request tilesRequest, final Locale locale, final List<String> roleNames){

		for (final String roleName : roleNames) {
			final List<MenuItem> menus = buildMenus(roleName, locale);
			tilesRequest.getContext("request").put(roleName, menus);
		}
	}

	private List<MenuItem> buildMenus(final String tabMenu, final Locale locale) {

		final Integer headerMenuCount = getMenuCount(MessageFormat.format(MENU.SIZE.key, tabMenu), locale);
		List<MenuItem> menus = Collections.emptyList();

		if (headerMenuCount != null){
			menus = new LinkedList<MenuItem>();
			MenuItem tempMenu = null;
			for (int i = 0; i < headerMenuCount; i++) {

				final String caption = readValue(MessageFormat.format(MENU.CAPTION.key, tabMenu, i), locale);
				final String url = readValue(MessageFormat.format(MENU.URL.key, tabMenu, i), locale);
				final String title = readValue(MessageFormat.format(MENU.TITLE.key, tabMenu, i), locale);
				final String reDirectUrl = readValue(MessageFormat.format(MENU.REDIRECTURL.key, tabMenu, i), locale);

				final List<MenuItem> subMenus = buildSubMenus(i,tabMenu,locale);

				tempMenu = new MenuItem(caption, url, title, reDirectUrl, subMenus);
				menus.add(tempMenu);
			}
		}
		return menus;
	}



	private String readValue(final String key, final Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}

	private List<MenuItem> buildSubMenus(final int parentMenuId, final String tabMenu,
			final Locale locale) {

		final Integer subMenuCount = getMenuCount(
				MessageFormat.format(MENU.SUB_SIZE.key, tabMenu, parentMenuId),
				locale);
		List<MenuItem> subMenus = Collections.emptyList();

		//final String displayAddNewQHPSubMenu = DynamicPropertiesUtil.getPropertyValue(PlanmgmtConfigurationEnum.PLAN_MANAGEQHP_DISPLAYADDNEWQHPSUBMENU);
		//final String displayAddNewQDPSubMenu = DynamicPropertiesUtil.getPropertyValue(PlanmgmtConfigurationEnum.PLAN_MANAGEQDP_DISPLAYADDNEWQDPSUBMENU);
		//final String showUploadQualitRatingMenu = DynamicPropertiesUtil.getPropertyValue(PlanmgmtConfigurationEnum.SHOWUPLOADQUALITYRATINGMENU);

		if (subMenuCount != null) {
			subMenus = new LinkedList<MenuItem>();
			MenuItem tempMenu = null;

			for (int i = 0; i < subMenuCount; i++) {
				final String caption = readValue(MessageFormat.format(
						MENU.SUB_CAPTION.key, tabMenu, parentMenuId, i), locale);
				final String url = readValue(MessageFormat.format(MENU.SUB_URL.key,
						tabMenu, parentMenuId, i), locale);
				final String title = readValue(MessageFormat.format(
						MENU.SUB_TITLE.key, tabMenu, parentMenuId, i), locale);
				final String reDirectUrl = readValue(MessageFormat.format(
						MENU.SUB_REDIRECTURL.key, tabMenu, parentMenuId, i),
						locale);
			}
		}
		return subMenus;
	}

	private Integer getMenuCount(final String key, final Locale locale) {

		final String size = readValue(key, locale);
		if(StringUtils.isEmpty(size) || !StringUtils.isNumeric(size)){
			//LOGGER.error("Configuration Error - Tab Menu count is not defined in menu.properties file.");
			throw new IllegalStateException("Configuration Error - Menu count is not defined in menu.properties file.");
		}

		return Integer.parseInt(size);
	}

	/**
	 * 
	 * Static inner class to capture tab menu property file structure.
	 *
	 */
	public static class MenuItem {

		private final String url;
		private final String redirecturl;
		private final String caption;
		private final String title;
		private final List<MenuItem> subMenus;

		public MenuItem(final String caption, final String url, final String title, final String redirecturl, final List<MenuItem> subMenus) {
			this.caption = caption;
			this.url = url;
			this.title = title;
			this.redirecturl = redirecturl;
			this.subMenus = subMenus;
		}

		public String getUrl() {return url;}
		public String getCaption() {return caption;}
		public String getTitle() {return title;}
		public String getRedirecturl() {return redirecturl;}
		public List<MenuItem> getSubMenus() { return subMenus;}


	}
}
