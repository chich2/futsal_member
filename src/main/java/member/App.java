package member;


import java.time.LocalDate;
import java.util.List;

import DAO.MemberDAO;
import model.FutsalField;
import model.Member;
import util.DBUtil;

public class App {

	public static void main(String[] args) {

		DBUtil.getConnection();
		
		String id = "bpk244";
		String passwrod = "12345555";
		String phoneN = "010-2222-111";
		String memberName = "박치원2";
		
		
		try(
				MemberDAO memberDAO = new MemberDAO(DBUtil.getConnection())
			) {
//			# 로그인
//			
			Member mem = memberDAO.login(id, passwrod);
			System.out.println(mem);
			
//			#1 회원 가입
//			Member mem =  memberDAO.signup(id,passwrod,phoneN,memberName);

//			#1 예약 하기 ( int member_id, int futsal_id, int status, LocalDate reservation_date, String reservation_time )
//			memberDAO.createReservation(1, 3, 0, LocalDate.of(2024, 01, 18),  "17:00");

//			#1 나의 에약 조회 by member_id
//			List<Reservation> ra = memberDAO.showMyReservationById(1);
//			
//			for (Reservation reservation : ra) {
//				
//				System.out.println(reservation);
//			}
//			
//			System.out.println(mem);
			
			
//			#1 비어 있는 풋살조회 
			List<FutsalField> futals = memberDAO.showAvailableFutsals(LocalDate.of(2024, 01, 18),"17:00");
			
			for (FutsalField futsalField : futals) {
				System.out.println(futsalField);
			}
//			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
