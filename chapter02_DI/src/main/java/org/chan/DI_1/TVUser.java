package org.chan.DI_1;

public class TVUser {
	public static void main(String[] args) {
		
		LgTV ltv = new LgTV();
		ltv.powerOn();
		ltv.volumUp();
		ltv.volumDown();
		ltv.powerOff();
		
		System.out.println("-----------------");
		
		SamsungTV stv = new SamsungTV();
		stv.powerOn();
		stv.volumUp();
		stv.volumDown();
		stv.powerOff();
	}
}
