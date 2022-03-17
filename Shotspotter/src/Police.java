import java.awt.Point;

public class Police {
	
	private Point gunfire_location; // center로 부터 전달받은 총격음 위치

	Point getGunfire_location() {
		return gunfire_location;
	}

	void setGunfire_location(Point gunfire_location) {
		this.gunfire_location = gunfire_location;
	}
	
	// 전달받은 위치로 출동하여 확인
	void check(Gunfire gunfire) { 
		
		// 출동장소와 일치할 경우
		if(gunfire.getGunfire_location().x == gunfire_location.x && gunfire.getGunfire_location().y == gunfire_location.y) {
			System.out.println(">> 출동결과 : 발생장소 일치!");
		}
		
		// 출동장소와 불일치할 경우
		else {
			System.out.println(">> 출동결과 : 발생장소 불일치!");
		}
		System.out.println();
	}
}
