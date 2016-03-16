
package com.shp.common.context;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.alibaba.druid.util.StringUtils;
import com.shp.common.util.DesUtil;
import com.shp.common.util.StringUtil;

/**
 * Copyright (C) 2015 - 2020 SHP快易全栈应用开发平台
 */
public class PropertyHolderConfigurer extends PropertyPlaceholderConfigurer{
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		
		String username2 = props.getProperty("dbrs1.username");
		String password2 = props.getProperty("dbrs1.password");
		
		try {
			props.setProperty("jdbc.username", DesUtil.decrypt(username));
			props.setProperty("jdbc.password", DesUtil.decrypt(password));
			
			if(StringUtil.isNotEmpty(username2)){
				props.setProperty("dbrs1.username", DesUtil.decrypt(username2));
			}
			if (StringUtil.isNotEmpty(password2)) {
				props.setProperty("dbrs1.password", DesUtil.decrypt(password2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.processProperties(beanFactoryToProcess, props);
	}
}

