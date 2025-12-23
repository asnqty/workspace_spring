package org.chan.DI_5_component;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("applicationContext5.xml");
		
		TV tv = (TV)ctx.getBean("tv");
		tv.powerOn();
		tv.volumUp();
		tv.volumDown();
		tv.powerOff();
		
		ctx.close();
		
	}
}
