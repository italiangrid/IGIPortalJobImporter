package it.italiangrid.portal.jobImporter.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.jobImporter.controller.HomeController;

import org.apache.log4j.Logger;

public class UtilityMap {
	
	private static final Logger log = Logger.getLogger(HomeController.class);
	
	public static Map<String, List<String>> createMap(List<Vo> userVos){
		
		Map<String, List<String>> jobMap = new HashMap<String, List<String>>();
			
		Properties props = new LoadProperties("jobs.properties").getProperties();
		
		String types[] = props.getProperty("job.types").split(", ");
		String value,tmp;
		for (Vo vo : userVos) {
			List<String> jobs = new ArrayList<String>();
			
			for(int i=0; i < types.length; i++){
				value = props.getProperty(vo.getVo()+"."+types[i].toLowerCase());
				
				if(value!=null){
					tmp=types[i]+":"+value;
					jobs.add(tmp);
				}
			}
			
			if(jobs != null){
				jobMap.put(vo.getVo(), jobs);
			}
		}
			
		
		
		return jobMap;
	}
}
