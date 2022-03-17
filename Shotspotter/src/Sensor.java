import java.awt.Point;

public class Sensor {
	
	private Point[] sensor_location; // 각 센서별 위치 저장
	private double[] distance; // 각 센서별 거리 저장
	private int sound_size; // 총격 크기
	private Sensor_A sensor_A; // 총격음에서 1번째로 가까운 센서
	private Sensor_B sensor_B; // 총격음에서 2번째로 가까운 센서
	private Sensor_C sensor_C; // 총격음에서 3번째로 가까운 센서
	
	public Sensor() { // 센서는 임의로 10개 생성
		sensor_location = new Point[10];
		distance = new double[10];
		sensor_A = new Sensor_A();
		sensor_B = new Sensor_B();
		sensor_C = new Sensor_C();
		
		// 센서 좌표 랜덤으로 생성하여 저장
		for(int i = 0; i < 10; i++) {
			int location_x = (int)(Math.random()*200); // 센서 x좌표
			int location_y = (int)(Math.random()*200); // 센서 y좌표
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
	
	// 총격음 감지
	// 소리의 속도와 도달하는 시간을 계산하여 거리를 구하여야 하지만, 구현을 쉽게 하기 위해 gunfire의 위치를 사용하여 거리를 계산함. (실제로 sensor는 gunfire의 정확한 위치 정보를 알 수 없음)
	void detect(Gunfire gunfire) {
		for(int i = 0; i < 10; i++) {
			distance[i] = Math.sqrt(Math.pow(Math.abs(sensor_location[i].x-gunfire.getGunfire_location().x), 2) + Math.pow(Math.abs(sensor_location[i].y-gunfire.getGunfire_location().y), 2));
		}
	}
	
	// 총격음과 가장 가까운 거리에 있는 3개의 센서를 선택
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
	
	// 총격음인지 아닌지 판별
	boolean check_gunfire(Gunfire gunfire) {
		sound_size = gunfire.getSound_size();
		
		// 소리의 크기가 120dB보다 작으면 false, 크면 true
		if(sound_size < 120) {
			return false;
		}
		else {
			return true;
		}
	}
	
	// 센서 측정 정보 초기화
	void reset() {
		sensor_A = new Sensor_A();
		sensor_B = new Sensor_B();
		sensor_C = new Sensor_C();
	}
	
	// 전체 센서의 위치와 총격음으로부터의 거리, 총격음으로부터 가장 가까운 세 센서의 위치를 출력
	void show() {
		System.out.println("\n<전체 센서의 위치와 총격음으로부터의 거리>");
		for(int i = 0; i < 10; i++) {
			System.out.println("센서" + (i+1) + " 위치 : [" + sensor_location[i].x + ", " + sensor_location[i].y + "] \t거리 : " + Math.round(distance[i]*100)/100.0);
		}
		System.out.println("\n총격음으로부터 가장 가까운 세 위치 : [" + sensor_A.getA().x + ", " + sensor_A.getA().y + "], [" + sensor_B.getB().x + ", " + sensor_B.getB().y + "], [" + sensor_C.getC().x + ", " + sensor_C.getC().y + "]");
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
