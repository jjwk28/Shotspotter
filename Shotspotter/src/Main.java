
// 10���� ������ ��ġ�� �������� ��ġ
// �� 10���� ������ �Ѱ��� �߻� ����
// �Ѱ����� �ش��� ��� : �Ѱ����� ��ġ�� ũ��, ��ü ������ ��ġ�� �� �������� �Ѱ������κ����� �Ÿ�, �Ѱ������κ��� ���� ����� �� ��ġ, ���� �Ѱ��� ���� ��ġ, �⵿��� ���� ���
// �Ѱ����� �ش����� ���� ��� : "���� ���ú��� �������� �����Ƿ� �Ѱ����� �ƴմϴ�" ���

public class Main {

	public static void main(String[] args) {
		Sensor sensor = new Sensor();
		Center center = new Center();
		Police police = new Police();
		
		// ���Ƿ� 10�� ����
		int n = 0;
		while(++n <= 10) {
			int x = (int)(Math.random()*200); // �������� �Ѱ��� x��ǥ ���� (���� : 0 ~ 199)
			int y = (int)(Math.random()*200); // �������� �Ѱ��� y��ǥ ���� (���� : 0 ~ 199)
			int size = (int)(Math.random()*40)+100; // �������� �Ѱ��� ũ�� ���� (���� : 100 ~ 139)
			
			Gunfire gunfire = new Gunfire(x, y, size); // gunfire �߻�
			
			System.out.println("----------------------------------------------------------------");
			System.out.println("[���� " + n + "] \n�Ѱ��� ��ġ : [" + x + ", " + y + "], ũ�� : " + size + "dB");
			
			// ���� ���� sensor���� ���ú��� ���� �Ѱ������� �ƴ����� �Ǵ�
			// �����δ� ��ϵǾ� �ִ� �Ѱ��� �����Ϳ� ���Ͽ� �Ѱ��� ���θ� �Ǵ�������, ������ ���� �ϱ� ���� �Ҹ��� ũ�⸸���� �Ѱ��� ���θ� �Ǵ�
			if(!sensor.check_gunfire(gunfire)) {
				System.out.println(">> 120dB ���Ϸ� �Ѱ����� �ƴմϴ�.\n");
				continue;
			}
			else {
				sensor.detect(gunfire); // sensor���� gunfire������ �Ÿ��� Ž��
				sensor.select_sensor(); // �Ÿ��� Ž���� ������ �� ���� ����� ���� 3���� ����
				center.get(sensor.getSensor_A(), sensor.getSensor_B(), sensor.getSensor_C()); // center���� sensor�� ���� ����
				center.cal_location(); // center���� gunfire�� ��ġ�� ���
				center.send(police); // center���� police���� ���� ����
				
				sensor.show();
				center.show();
				
				police.check(gunfire); // police�� ���޹��� ��ġ�� �⵿�Ͽ� ���� Ȯ��
				sensor.reset(); // sensor A, B, C�� ������ �ʱ�ȭ
			}
		}
	}
}
