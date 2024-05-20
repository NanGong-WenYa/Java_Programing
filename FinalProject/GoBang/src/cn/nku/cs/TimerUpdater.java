package cn.nku.cs;
import javax.swing.*;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUpdater {
    private Timer timer;
    private int currentTime = 0;
    private JTextField textField;

    public TimerUpdater(final JTextField textField) {
        this.textField = textField;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                updateTextField();
                
                currentTime++; // ���������ÿ������һ��
                if(currentTime==60){
               
                	if(Vars.net.getWhoPlay()==ChessModel.BLACK){
                
                		JOptionPane.showMessageDialog(null, "�ڷ���ʱ���׷�ʤ");
                		Vars.net.restart();
                		
                	}
                	else{
                		
                		JOptionPane.showMessageDialog(null, "�׷���ʱ���ڷ�ʤ");
                		Vars.net.restart();
                		
                		
                	}
                }
            }
        }, 1000, 1000); // ÿ��1��ִ��һ��
    }

    private void updateTextField() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textField.setText(String.valueOf(currentTime));
            }
        });
    }
    
    public void reset(){
    	currentTime=0;
    	return;
    }
    
    
    
	public int getCurrentTime() {
		return currentTime;
	}
	
	public void stopTimer() {
		   if(timer==null)
			   return;
	        timer.cancel(); // ֹͣ��ʱ��
	        timer.purge(); // �����ȡ��������
	        textField=null;
	        timer = null; // �� Timer ��������Ϊ null
	}
	
	
	
}
