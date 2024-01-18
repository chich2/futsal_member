package model;

import futsal_maanger_user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member implements User{
	int member_id;
	String login_id;
	String login_pw;
	String phoneNum;
	String member_name;
	@Override
	public String toString() {
		return "회원 정보: " + member_name + " (phone: " +phoneNum + ")";
	}
	
	
}