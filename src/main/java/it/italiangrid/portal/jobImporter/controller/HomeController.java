package it.italiangrid.portal.jobImporter.controller;

import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.jobImporter.util.UtilityMap;

import java.util.List;
import java.util.Map;
import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;


@Controller("homeController")
@RequestMapping(value = "VIEW")
public class HomeController {
	
	private static final Logger log = Logger
			.getLogger(HomeController.class);
	
	@Autowired
	private UserToVoService userToVoservice;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RenderMapping
	public String showHomePage(RenderRequest request) {
		log.info("Home Page");
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user == null){
			log.error("aaaaaaaaaaaaaaaaaaaaa");
		}
		return "home";
	}
	
	@ModelAttribute("jobMap")
	public Map<String, List<String>> getJobMap(RenderRequest request) {
		
		User user = (User) request.getAttribute(WebKeys.USER);
		
		if(user!=null){
			
			int userId = userInfoService.findByUsername(user.getScreenName()).getUserId();
			
			List<Vo> userVos = userToVoservice.findVoByUserId(userId);
			
			return UtilityMap.createMap(userVos);
			
		}
		
		
		
		return null;
	}
	
	@ModelAttribute("voList")
	public List<Vo> getVoList(RenderRequest request) {
		
		User user = (User) request.getAttribute(WebKeys.USER);
		
		if(user!=null){
			
			int userId = userInfoService.findByUsername(user.getScreenName()).getUserId();
			
			return userToVoservice.findVoByUserId(userId);
			
		}
		
		return null;
	}
	
	@ModelAttribute("isOK")
	public boolean getIsOK(RenderRequest request) {
		
		User user = (User) request.getAttribute(WebKeys.USER);
		
		if(user!=null){
			
			return true;
			
		}
		
		return false;
	}

}
