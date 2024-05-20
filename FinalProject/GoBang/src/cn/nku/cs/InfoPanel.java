package cn.nku.cs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author Lenovo
 *
 */
/**
 * @author Lenovo
 *
 */
public class InfoPanel extends JPanel{
	

	private JLabel whoPlayLabel=new JLabel();
	private JLabel timeLabel=new JLabel();
	

	private JTextField whoPlayTextField=new JTextField(10);
	private JTextField timeTextField=new JTextField(10);

	private JButton listenBtn = new JButton("�ڷ�׼��");
	private JButton connBtn = new JButton("�׷�׼��");
	private JButton regretBtn=new JButton("����");
	public InfoPanel() {
		
		
		final String ip = "localhost";
		final String port = "8000";
		
		whoPlayLabel.setText("���巽Ϊ��");
		timeLabel.setText("��ʱ��");
		
		regretBtn.addActionListener(new ActionListener(){
		

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Vars.net.sendRegretMessage();
				
				
			}});
		listenBtn.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Vars.net.startListen();
				Vars.control.setChessColor(ChessModel.BLACK);
				listenBtn.setEnabled(false);
				Vars.control.setOpenDoor(true);
			}
		});
		connBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				Vars.control.setChessColor(ChessModel.WHITE);
				connBtn.setEnabled(false);
				Vars.control.setOpenDoor(false);
				Vars.net.connect(ip, Integer.parseInt(port));
			}
		});

		add(listenBtn);
		add(connBtn);
		add(whoPlayLabel);
		add(whoPlayTextField);
		add(timeLabel);
		add(timeTextField);
		add(regretBtn);
		
	}
	
	public JTextField getTimeTextField() {
		return timeTextField;
	}
	public void setTimeTextField(String s) {
		this.timeTextField .setText(s);
	}
	

	public JTextField getWhoPlayTextField() {
		return whoPlayTextField;
	}

	public void setWhoPlayTextField(String s) {
		this.whoPlayTextField.setText(s);;
	}

}
