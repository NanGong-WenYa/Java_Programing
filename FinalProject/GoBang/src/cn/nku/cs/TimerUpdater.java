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
                
                currentTime++; // 假设计数器每秒增加一次
                if(currentTime==60){
               
                	if(Vars.net.getWhoPlay()==ChessModel.BLACK){
                
                		JOptionPane.showMessageDialog(null, "黑方超时，白方胜");
                		Vars.net.restart();
                		
                	}
                	else{
                		
                		JOptionPane.showMessageDialog(null, "白方超时，黑方胜");
                		Vars.net.restart();
                		
                		
                	}
                }
            }
        }, 1000, 1000); // 每隔1秒执行一次
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
	        timer.cancel(); // 停止计时器
	        timer.purge(); // 清除已取消的任务
	        textField=null;
	        timer = null; // 将 Timer 对象设置为 null
	}
	
	
	
}
