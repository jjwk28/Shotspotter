import java.awt.Point;

public class Center {
	
	private Point A; // �Ѱ������� 1��°�� ����� ������ ��ġ
	private Point B; // �Ѱ������� 2��°�� ����� ������ ��ġ
	private Point C; // �Ѱ������� 3��°�� ����� ������ ��ġ
	private double dis_A; // 1��°�� ����� �������� ������ �Ÿ�
	private double dis_B; // 2��°�� ����� �������� ������ �Ÿ�
	private double dis_C; // 3��°�� ����� �������� ������ �Ÿ�
	private Point gunfire_location; // �Ѱ��� ��ġ ��ǥ
	
	// sensor�� ������ �Ѱ������� ���� ����� �� ���� ��ġ�� �Ѱ��������� �Ÿ��� ����
	void get(Sensor_A sensor_A, Sensor_B sensor_B, Sensor_C sensor_C) { 
		A = sensor_A.getA();
		B = sensor_B.getB();
		C = sensor_C.getC();
		dis_A = sensor_A.getDis_A();
		dis_B = sensor_B.getDis_B();
		dis_C = sensor_C.getDis_C();
	}
	
	// �� ������ ������ �Ÿ� �ݰ� ���� �ִ� ��ǥ�� ���
	void cal_location() { 
		int min_x = (int)Math.min(Math.round(A.x-dis_A), Math.min(Math.round(B.x-dis_B), Math.round(C.x-dis_C))); // �Ÿ��� ������� �� ���� ������ x ��ǥ
		int min_y = (int)Math.min(Math.round(A.y-dis_A), Math.min(Math.round(B.y-dis_B), Math.round(C.y-dis_C))); // �Ÿ��� ������� �� ���� ������ y ��ǥ
		int max_x = (int)Math.max(Math.round(A.x+dis_A), Math.max(Math.round(B.x+dis_B), Math.round(C.x+dis_C))); // �Ÿ��� ������� �� ���� �������� x ��ǥ
		int max_y = (int)Math.max(Math.round(A.y+dis_A), Math.max(Math.round(B.y+dis_B), Math.round(C.y+dis_C))); // �Ÿ��� ������� �� ���� �Ʒ����� y ��ǥ
		
		for(int i = min_x; i <= max_x; i++) {
			for(int j = min_y; j <= max_y; j++) {
				
				// �� ������ ���� (i, j)������ �Ÿ��� �� ������ ������ �Ÿ��� ���� ������ ���� �Ѱ��� ��ġ�� ���
				if(cal_dis(A.x, A.y, i, j) == dis_A && cal_dis(B.x, B.y, i, j) == dis_B && cal_dis(C.x, C.y, i, j) == dis_C) { 
					gunfire_location = new Point(i, j);
					return;
				}
			}
		}
	}
	
	// �� ��ǥ�� �����Ÿ� ���
	double cal_dis(int x1, int y1, int x2, int y2) { 
		return Math.sqrt(Math.pow(Math.abs(x2-x1), 2) + Math.pow(Math.abs(y2-y1), 2));
	}
	
	// police���� ������ �Ѱ��� ��ġ ����
	void send(Police police) { 
		police.setGunfire_location(gunfire_location);
	}
	
	// ���� �Ѱ��� ���� ��ġ�� ���
	void show() {
		System.out.println("���� �Ѱ��� ���� ��ġ : [" + gunfire_location.x + ", " + gunfire_location.y + "]");
	}
}
