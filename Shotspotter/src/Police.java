import java.awt.Point;

public class Police {
	
	private Point gunfire_location; // center�� ���� ���޹��� �Ѱ��� ��ġ

	Point getGunfire_location() {
		return gunfire_location;
	}

	void setGunfire_location(Point gunfire_location) {
		this.gunfire_location = gunfire_location;
	}
	
	// ���޹��� ��ġ�� �⵿�Ͽ� Ȯ��
	void check(Gunfire gunfire) { 
		
		// �⵿��ҿ� ��ġ�� ���
		if(gunfire.getGunfire_location().x == gunfire_location.x && gunfire.getGunfire_location().y == gunfire_location.y) {
			System.out.println(">> �⵿��� : �߻���� ��ġ!");
		}
		
		// �⵿��ҿ� ����ġ�� ���
		else {
			System.out.println(">> �⵿��� : �߻���� ����ġ!");
		}
		System.out.println();
	}
}
