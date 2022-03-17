import java.awt.Point;

public class Gunfire {
	
	private Point gunfire_location; // 총격(추정) 발생 위치 좌표
	private int sound_size; // 총격(추정) 크기
	
	public Gunfire(int x, int y, int size) {
		gunfire_location = new Point(x, y);
		sound_size = size;
	}

	Point getGunfire_location() {
		return gunfire_location;
	}

	int getSound_size() {
		return sound_size;
	}
}
