package it.italiangrid.portal.jobImporter.util;

import it.italiangrid.portal.jobImporter.controller.HomeController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class LoadProperties {

	private static final Logger log = Logger.getLogger(HomeController.class);

	private Properties props = new Properties();

	public LoadProperties(String file) {
		String contextPath = LoadProperties.class.getClassLoader()
				.getResource("").getPath();

		File test = new File(contextPath + file);
		log.info("File: " + test.getAbsolutePath());

		if (test.exists()) {
			log.info("Properties found: " + contextPath + file);
			try {
				FileInputStream inStream = new FileInputStream(contextPath
						+ file);

				props.load(inStream);

				inStream.close();

				log.info("Properties loaded");
			} catch (IOException e) {
				log.info("Properties non loaded: " + e.getMessage());
			}
		}
	}
	
	public Properties getProperties(){
		return this.props;
	}

}
