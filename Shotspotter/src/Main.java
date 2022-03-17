
// 10개의 센서의 위치를 랜덤으로 배치
// 총 10번의 임의의 총격음 발생 측정
// 총격음에 해당할 경우 : 총격음의 위치와 크기, 전체 센서의 위치와 각 센서에서 총격음으로부터의 거리, 총격음으로부터 가장 가까운 세 위치, 계산된 총격음 예상 위치, 출동결과 등을 출력
// 총격음에 해당하지 않을 경우 : "기준 데시벨을 충족하지 않으므로 총격음이 아닙니다" 출력

public class Main {

	public static void main(String[] args) {
		Sensor sensor = new Sensor();
		Center center = new Center();
		Police police = new Police();
		
		// 임의로 10번 측정
		int n = 0;
		while(++n <= 10) {
			int x = (int)(Math.random()*200); // 랜덤으로 총격음 x좌표 생성 (범위 : 0 ~ 199)
			int y = (int)(Math.random()*200); // 랜덤으로 총격음 y좌표 생성 (범위 : 0 ~ 199)
			int size = (int)(Math.random()*40)+100; // 랜덤으로 총격음 크기 생성 (범위 : 100 ~ 139)
			
			Gunfire gunfire = new Gunfire(x, y, size); // gunfire 발생
			
			System.out.println("----------------------------------------------------------------");
			System.out.println("[측정 " + n + "] \n총격음 위치 : [" + x + ", " + y + "], 크기 : " + size + "dB");
			
			// 가장 먼저 sensor에서 데시벨을 통해 총격음인지 아닌지를 판단
			// 실제로는 기록되어 있는 총격음 데이터와 비교하여 총격음 여부를 판단하지만, 구현을 쉽게 하기 위해 소리의 크기만으로 총격음 여부를 판단
			if(!sensor.check_gunfire(gunfire)) {
				System.out.println(">> 120dB 이하로 총격음이 아닙니다.\n");
				continue;
			}
			else {
				sensor.detect(gunfire); // sensor에서 gunfire까지의 거리를 탐지
				sensor.select_sensor(); // 거리를 탐지한 센서들 중 가장 가까운 센서 3개를 선택
				center.get(sensor.getSensor_A(), sensor.getSensor_B(), sensor.getSensor_C()); // center에서 sensor의 정보 수집
				center.cal_location(); // center에서 gunfire의 위치를 계산
				center.send(police); // center에서 police에게 위지 전송
				
				sensor.show();
				center.show();
				
				police.check(gunfire); // police가 전달받은 위치로 출동하여 현장 확인
				sensor.reset(); // sensor A, B, C의 정보를 초기화
			}
		}
	}
}
