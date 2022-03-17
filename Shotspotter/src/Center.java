import java.awt.Point;

public class Center {
	
	private Point A; // 총격음에서 1번째로 가까운 센서의 위치
	private Point B; // 총격음에서 2번째로 가까운 센서의 위치
	private Point C; // 총격음에서 3번째로 가까운 센서의 위치
	private double dis_A; // 1번째로 가까운 센서에서 측정된 거리
	private double dis_B; // 2번째로 가까운 센서에서 측정된 거리
	private double dis_C; // 3번째로 가까운 센서에서 측정된 거리
	private Point gunfire_location; // 총격음 위치 좌표
	
	// sensor가 측정한 총격음에서 가장 가까운 세 곳의 위치와 총격음까지의 거리를 받음
	void get(Sensor_A sensor_A, Sensor_B sensor_B, Sensor_C sensor_C) { 
		A = sensor_A.getA();
		B = sensor_B.getB();
		C = sensor_C.getC();
		dis_A = sensor_A.getDis_A();
		dis_B = sensor_B.getDis_B();
		dis_C = sensor_C.getDis_C();
	}
	
	// 세 곳에서 측정된 거리 반경 내에 있는 좌표를 계산
	void cal_location() { 
		int min_x = (int)Math.min(Math.round(A.x-dis_A), Math.min(Math.round(B.x-dis_B), Math.round(C.x-dis_C))); // 거리를 계산했을 때 가장 왼쪽의 x 좌표
		int min_y = (int)Math.min(Math.round(A.y-dis_A), Math.min(Math.round(B.y-dis_B), Math.round(C.y-dis_C))); // 거리를 계산했을 때 가장 위쪽의 y 좌표
		int max_x = (int)Math.max(Math.round(A.x+dis_A), Math.max(Math.round(B.x+dis_B), Math.round(C.x+dis_C))); // 거리를 계산했을 때 가장 오른쪽의 x 좌표
		int max_y = (int)Math.max(Math.round(A.y+dis_A), Math.max(Math.round(B.y+dis_B), Math.round(C.y+dis_C))); // 거리를 계산했을 때 가장 아래쪽의 y 좌표
		
		for(int i = min_x; i <= max_x; i++) {
			for(int j = min_y; j <= max_y; j++) {
				
				// 세 곳에서 각각 (i, j)까지의 거리와 세 곳에서 측정된 거리가 같은 지점을 예상 총격음 위치로 계산
				if(cal_dis(A.x, A.y, i, j) == dis_A && cal_dis(B.x, B.y, i, j) == dis_B && cal_dis(C.x, C.y, i, j) == dis_C) { 
					gunfire_location = new Point(i, j);
					return;
				}
			}
		}
	}
	
	// 두 좌표의 직선거리 계산
	double cal_dis(int x1, int y1, int x2, int y2) { 
		return Math.sqrt(Math.pow(Math.abs(x2-x1), 2) + Math.pow(Math.abs(y2-y1), 2));
	}
	
	// police에게 측정된 총격음 위치 전달
	void send(Police police) { 
		police.setGunfire_location(gunfire_location);
	}
	
	// 계산된 총격음 예상 위치를 출력
	void show() {
		System.out.println("계산된 총격음 예상 위치 : [" + gunfire_location.x + ", " + gunfire_location.y + "]");
	}
}
