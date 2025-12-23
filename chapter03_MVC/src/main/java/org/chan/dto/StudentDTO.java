package org.chan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// getter setter 대신 @Data를 쓰면 getter setter에다가 hashCode()와 toString()까지 만들어짐
public class StudentDTO {
	private String name;
	private String dept;
	private int gradeNo;
	private int classNo;
}
