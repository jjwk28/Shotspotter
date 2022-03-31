import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Lotto extends JFrame {
	
	static int idx = 0;
	static String title = "로또번호 추출기";
	static JLabel jt1, jt2, jt3, jt4, jt5, jt6;
	static JLabel[] jlabel = {jt1, jt2, jt3, jt4, jt5, jt6};
	static ArrayList<JLabel> result_list = new ArrayList<>();
	
	public Lotto() {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		
		p1.setLayout(new GridLayout(2, 1));
		JPanel p1_1 = new JPanel();
		JPanel p1_2 = new JPanel();
		p1_1.setLayout(new FlowLayout());
		p1_2.setLayout(new FlowLayout());
		
		JLabel lb = new JLabel(title);
		
		JButton start = new JButton("추출");
		start.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				randomNumber();
				
				StringBuilder result = new StringBuilder();
				Arrays.sort(jlabel, new Comparator<JLabel>() {
					public int compare(JLabel o1, JLabel o2) {
						return Integer.parseInt(o1.getText()) - Integer.parseInt(o2.getText());
					}
				});
				 
				if(idx == 10) {
					for(int i = 0; i < idx-1; i++) {
						result_list.get(i).setText(result_list.get(i+1).getText());
					}
					for(int i = 0; i < 6; i++) {
						result.append(jlabel[i].getText() + " ");
					}
					result_list.get(idx-1).setText(result.toString());
				}
				else {
					for(int i = 0; i < 6; i++) {
						result.append(jlabel[i].getText() + " ");
					}
					result_list.get(idx++).setText(result.toString());
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				lb.setText("로또번호 6자리를 랜덤으로 추출합니다.");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				lb.setText(title);
			}
		});
		
		JButton reset = new JButton("초기화");
		reset.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				for(int i = 0; i < 6; i++) {
					jlabel[i].setText("0");
				}
				for(int i = 0; i < 10; i++) {
					result_list.get(i).setText("");
				}
				idx = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				lb.setText("처음 상태로 초기화합니다.");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				lb.setText(title);
			}
			
		});
		p1_1.add(lb);
		p1_2.add(start);
		p1_2.add(reset);
		p1.add(p1_1);
		p1.add(p1_2);
		
		p2.setLayout(null);
		for(int i = 0; i < 6; i++) {
			jlabel[i] = new JLabel("0");
			jlabel[i].setFont(new Font("맑은고딕", Font.BOLD, 30));
			jlabel[i].setSize(40, 40);
			jlabel[i].setLocation(i*45, 0);
			jlabel[i].setOpaque(true);
			jlabel[i].setBackground(Color.white);
			jlabel[i].setBorder(new TitledBorder(new LineBorder(Color.black)));
			jlabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			p2.add(jlabel[i]);
		}
		
		p3.setLayout(new GridLayout(5, 2, 1, 1));
		for(int i = 0; i < 10; i++) {
			JPanel jp = new JPanel();
			jp.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel jn = new JLabel(Integer.toString(i+1) + ") ");
			JLabel jl = new JLabel("");
			result_list.add(jl);
			jp.add(jn);
			jp.add(jl);
			p3.add(jp);
		}
		
		c.add(p1, BorderLayout.NORTH);
		c.add(p2, BorderLayout.CENTER);
		c.add(p3, BorderLayout.SOUTH);
		c.add(p4, BorderLayout.EAST);
		c.add(p5, BorderLayout.WEST);
		
		setSize(300, 300);
		setVisible(true);
	}
	
	static void randomNumber() {
		for(int i = 0; i < 6; i++) {
			int n = (int)(Math.random()*45)+1;
			jlabel[i].setText(Integer.toString(n));
			
			for(int j = 0; j < i; j++) {
				if(jlabel[j].getText().equals(jlabel[i].getText())) {
					i--;
					break;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new Lotto();
	}
}
