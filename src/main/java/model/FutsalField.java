package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FutsalField {

	int futsal_id;
	int manager_id;
	int capacity;
	String futsal_name;
	@Override
	public String toString() {
		return "" +futsal_name + "(id: " + futsal_id + ") 풋살장은 " + capacity + "명 수용 가능합니다.";
	}
}
