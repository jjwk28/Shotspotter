import java.awt.Point;

public class Gunfire {
	
	private Point gunfire_location; // �Ѱ�(����) �߻� ��ġ ��ǥ
	private int sound_size; // �Ѱ�(����) ũ��
	
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
