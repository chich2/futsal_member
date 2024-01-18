package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.FutsalField;
import model.Member;
import model.Reservation;
import util.DBUtil;

public class MemberDAO implements AutoCloseable{
	private Connection connection = null;
	
	public MemberDAO(Connection connection) {
		this.connection = connection;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void close() throws Exception {
		connection.close();
	}
	
//	로그인		로그인된 멤버 객체 반환
	public  Member login(String id , String password) throws Exception  {
			
			String sqlQuery = "SELECT * FROM member WHERE login_id=? and login_pw=?";
			
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, id);
			statement.setString(2, password);
			
			ResultSet resuslt = statement.executeQuery();
			
			try ( statement; resuslt;){
				
				if(!resuslt.next()) return null;
				
				
				int memberId =resuslt.getInt("member_id");
				String loginId= resuslt.getString("login_id");
				String loginPassword = resuslt.getString("login_pw");
				String phoneNumber = resuslt.getString("phoneNum");
				String memberName = resuslt.getString("member_name");
				
				return new Member(memberId, loginId, loginPassword, phoneNumber, memberName);
				
			}catch (Exception e) {
				throw new Exception("로그인 에러 발생");
			}
	}
	
//	회원가입		void
	public void signup(String id , String password , String phoneNumber ,String memberName) throws Exception {
		String sqlQuery = "INSERT INTO member (login_id, login_pw, phoneNum, member_name) VALUES (?,?,?,?)";
		
		
		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		statement.setString(1, id);
		statement.setString(2, password);
		statement.setString(3, phoneNumber);
		statement.setString(4, memberName);
		
		int resuslt = statement.executeUpdate();
		
		try ( statement;){
			System.out.println(resuslt);
			if(resuslt == 1) {
				
			}
			
		}catch (Exception e) {

			throw new Exception("회원가입 에러 발생");
		}
		
	}
	
	
	
//	풋살장 예약	void create
	
	public void createReservation(int member_id, int futsal_id, int status, LocalDate reservation_date, String reservation_time) throws Exception {
		String sqlQuery = "INSERT INTO Reservation (member_id, futsal_id, status, reservation_date, reservation_time) VALUES(?,?,?,?,?)";		
		
		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		
		statement.setInt(1, member_id);
		statement.setInt(2, futsal_id);
		statement.setInt(3, status);
		statement.setString(4, reservation_date.toString());
		statement.setString(5, reservation_time);
		
		int resuslt = statement.executeUpdate();
		
		try ( statement; ){
			
			if(resuslt == 1) {
				
			}
			
		}catch (Exception e) {

			throw new Exception("에러 발생");
		}
		
	}
	
//	나의 예약 보기
	public List<Reservation> showMyReservationById (int id) throws Exception{
		
		String sqlQuery = "SELECT * FROM reservation WHERE member_id =?";		
		
		List<Reservation> reservations = new ArrayList();

		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		
		statement.setInt(1, id);
		
		
		ResultSet result = statement.executeQuery();
		
		try ( statement; result){
			
			while(result.next()) {
//				reservation_id | member_id | futsal_id | status | reservation_date | reservation_time
				int reservation_id =result.getInt("reservation_id");
				int member_id =result.getInt("member_id");
				int futsal_id =result.getInt("futsal_id");
				int status =result.getInt("status");
				LocalDate reservationDate =result.getDate("reservation_date").toLocalDate();
				String reservationTime =result.getString("reservation_time");
				
				Reservation reservation = new Reservation(reservation_id, member_id, futsal_id, status, reservationDate, reservationTime);
				reservations.add(reservation);
				
			}
			
		}catch (Exception e) {

			throw new Exception("에러 발생");
		}
		
		return reservations;
	}
	
	
//	예약 취소		void delete
	public void cancelReservation(int deleteId) throws Exception {
		String sqlQuery = "DELETE FROM reservation WHERE reservation_id =?";		
		

		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		
		statement.setInt(1, deleteId);
		
		
		int result = statement.executeUpdate();
		
		try ( statement){
			
			if(result == 1) {
				
			}
			
		}catch (Exception e) {

			throw new Exception("에러 발생");
		}
	}
	
	
	public List<FutsalField> showAvailableFutsals(LocalDate date , String time) throws Exception{
		List<FutsalField> futsals = new ArrayList<FutsalField>();
		
		
		String sqlQuery = "SELECT * "
				+ "FROM futsal "
				+ "where futsal_id NOT IN(SELECT futsal_id FROM reservation WHERE reservation_date = ? and reservation_time = ?)";		

//		SELECT * FROM futsal where futsal_id NOT IN(SELECT futsal_id FROM reservation WHERE reservation_date = '2024-01-22' and reservation_time = '18:00')
		
		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		
		statement.setString(1, date.toString());
		statement.setString(2, time);
		
		
		ResultSet result = statement.executeQuery();
		
		try (statement; result){
			
			while(result.next()) {
				
				int futsal_id = result.getInt("futsal_id");
				int manager_id = result.getInt("manager_id");
				int capacity = result.getInt("capacity");
				String futsal_name = result.getString("futsal_name");
				
				futsals.add(new FutsalField(futsal_id, manager_id, capacity, futsal_name));
			}
			
		}catch (Exception e) {

			throw new Exception("에러 발생");
		}
		
		return futsals;
		
	}
//	풋살장 조회(날짜, 시간 입력)	List<futsal>: 날짜 기준으로 예약 가능한 풋살장만 조회되도록 select
//	18 20 22
	

}
