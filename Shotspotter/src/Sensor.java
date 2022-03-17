import java.awt.Point;

public class Sensor {
	
	private Point[] sensor_location; // �� ������ ��ġ ����
	private double[] distance; // �� ������ �Ÿ� ����
	private int sound_size; // �Ѱ� ũ��
	private Sensor_A sensor_A; // �Ѱ������� 1��°�� ����� ����
	private Sensor_B sensor_B; // �Ѱ������� 2��°�� ����� ����
	private Sensor_C sensor_C; // �Ѱ������� 3��°�� ����� ����
	
	public Sensor() { // ������ ���Ƿ� 10�� ����
		sensor_location = new Point[10];
		distance = new double[10];
		sensor_A = new Sensor_A();
		sensor_B = new Sensor_B();
		sensor_C = new Sensor_C();
		
		// ���� ��ǥ �������� �����Ͽ� ����
		for(int i = 0; i < 10; i++) {
			int location_x = (int)(Math.random()*200); // ���� x��ǥ
			int location_y = (int)(Math.random()*200); // ���� y��ǥ
			sensor_location[i] = new Point(location_x, location_y);
		}
	}
	
	Sensor_A getSensor_A() {
		return sensor_A;
	}

	Sensor_B getSensor_B() {
		return sensor_B;
	}

	Sensor_C getSensor_C() {
		return sensor_C;
	}
	
	// �Ѱ��� ����
	// �Ҹ��� �ӵ��� �����ϴ� �ð��� ����Ͽ� �Ÿ��� ���Ͽ��� ������, ������ ���� �ϱ� ���� gunfire�� ��ġ�� ����Ͽ� �Ÿ��� �����. (������ sensor�� gunfire�� ��Ȯ�� ��ġ ������ �� �� ����)
	void detect(Gunfire gunfire) {
		for(int i = 0; i < 10; i++) {
			distance[i] = Math.sqrt(Math.pow(Math.abs(sensor_location[i].x-gunfire.getGunfire_location().x), 2) + Math.pow(Math.abs(sensor_location[i].y-gunfire.getGunfire_location().y), 2));
		}
	}
	
	// �Ѱ����� ���� ����� �Ÿ��� �ִ� 3���� ������ ����
	void select_sensor() {
		for(int i = 0; i < 10; i++) {
			if(distance[i] < sensor_A.getDis_A()) {
				sensor_C.setDis_C(sensor_B.getDis_B());
				sensor_B.setDis_B(sensor_A.getDis_A());
				sensor_A.setDis_A(distance[i]);
				sensor_C.setC(sensor_B.getB());
				sensor_B.setB(sensor_A.getA());
				sensor_A.setA(sensor_location[i]);
			}
			else if(distance[i] < sensor_B.getDis_B()) {
				sensor_C.setDis_C(sensor_B.getDis_B());
				sensor_B.setDis_B(distance[i]);
				sensor_C.setC(sensor_B.getB());
				sensor_B.setB(sensor_location[i]);
			}
			else if(distance[i] < sensor_C.getDis_C()) {
				sensor_C.setDis_C(distance[i]);
				sensor_C.setC(sensor_location[i]);
			}
		}
	}
	
	// �Ѱ������� �ƴ��� �Ǻ�
	boolean check_gunfire(Gunfire gunfire) {
		sound_size = gunfire.getSound_size();
		
		// �Ҹ��� ũ�Ⱑ 120dB���� ������ false, ũ�� true
		if(sound_size < 120) {
			return false;
		}
		else {
			return true;
		}
	}
	
	// ���� ���� ���� �ʱ�ȭ
	void reset() {
		sensor_A = new Sensor_A();
		sensor_B = new Sensor_B();
		sensor_C = new Sensor_C();
	}
	
	// ��ü ������ ��ġ�� �Ѱ������κ����� �Ÿ�, �Ѱ������κ��� ���� ����� �� ������ ��ġ�� ���
	void show() {
		System.out.println("\n<��ü ������ ��ġ�� �Ѱ������κ����� �Ÿ�>");
		for(int i = 0; i < 10; i++) {
			System.out.println("����" + (i+1) + " ��ġ : [" + sensor_location[i].x + ", " + sensor_location[i].y + "] \t�Ÿ� : " + Math.round(distance[i]*100)/100.0);
		}
		System.out.println("\n�Ѱ������κ��� ���� ����� �� ��ġ : [" + sensor_A.getA().x + ", " + sensor_A.getA().y + "], [" + sensor_B.getB().x + ", " + sensor_B.getB().y + "], [" + sensor_C.getC().x + ", " + sensor_C.getC().y + "]");
	}
}
class Sensor_A {
	private double dis_A;
	private Point A;
	
	public Sensor_A() {
		this.dis_A = Double.MAX_VALUE;
		this.A = null;
	}
	double getDis_A() {
		return dis_A;
	}
	Point getA() {
		return A;
	}
	void setDis_A(double dis_A) {
		this.dis_A = dis_A;
	}
	void setA(Point a) {
		A = a;
	}
}
class Sensor_B {
	private double dis_B;
	private Point B;
	
	public Sensor_B() {
		this.dis_B = Double.MAX_VALUE;
		this.B = null;
	}
	double getDis_B() {
		return dis_B;
	}
	Point getB() {
		return B;
	}
	void setDis_B(double dis_B) {
		this.dis_B = dis_B;
	}
	void setB(Point b) {
		B = b;
	}
}
class Sensor_C {
	private double dis_C;
	private Point C;
	
	public Sensor_C() {
		dis_C = Double.MAX_VALUE;
		C = null;
	}
	double getDis_C() {
		return dis_C;
	}
	Point getC() {
		return C;
	}
	void setDis_C(double dis_C) {
		this.dis_C = dis_C;
	}
	void setC(Point c) {
		C = c;
	}
}
