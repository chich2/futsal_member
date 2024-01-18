package model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reservation {
	int reservation_id;
	int member_id;
	int futsal_id;
	int status;
	LocalDate reservation_date;
	String reservateion_time;
	@Override
	public String toString() {
		return reservation_date + " " + reservateion_time + "에 " + "예약번호 " + reservation_id +  (status == 0 ? "로 예약되었습니다.": "로 취소되었습니다.");
	}
	
}
