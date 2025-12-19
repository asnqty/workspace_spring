package org.chan.DI_1;

public class SamsungTV {

	public SamsungTV() {
		System.out.println("==> SamsungTV 객체 생성");
	}

	public void powerOn() {
		System.out.println("SamsungTV -- 전원 켠다.");
	}
	
	public void powerOff() {
		System.out.println("SamsungTV -- 전원 끈다.");
	}
	
	public void volumUp() {
		System.out.println("SamsungTV -- 소리 올린다.");
	}
	
	public void volumDown() {
		System.out.println("SamsungTV -- 소리 내린다.");
	}
	
}
