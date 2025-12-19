package org.chan.DI_2;

public class TVUser {
	public static void main(String[] args) {
		
		TV ltv = new LgTV();
		ltv.powerOn();
		ltv.volumUp();
		ltv.volumDown();
		ltv.powerOff();
		
		System.out.println("-----------------");
		
		TV stv = new SamsungTV();
		stv.powerOn();
		stv.volumUp();
		stv.volumDown();
		stv.powerOff();
	}
}
