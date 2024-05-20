package cn.nku.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class NetHelper {
	public static final int PORT = 8000;
	private Socket s;
	private int whoPlay=1;
	private BufferedReader in;
	private	PrintStream out;
	private TimerUpdater timer;
	private boolean otherSideRestart=false;
	private boolean restart=false;
	private boolean restartMessageReplied=false;
	protected String oTherSidelastMove;
	private String lastMove;
	private int otherSideLastRow;
	private int otherSideLastCol;
	public void startListen(){
		new Thread(){
			public void run() {
				listen();
			}
		}.start();
	}
	private void listen() {
		try {

			ServerSocket ss = new ServerSocket(PORT);
			s = ss.accept();
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintStream(s.getOutputStream(),true);
			
			whoPlay=Vars.model.WHITE;
			Vars.infoPanel.setWhoPlayTextField("黑方行棋中");
			startReadThread();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connect(String ip,int port){
		try {
			Socket s = new Socket(ip,port);
			Vars.control.setChessColor(Vars.model.WHITE);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintStream(s.getOutputStream(),true);
			timer= new TimerUpdater(Vars.infoPanel.getTimeTextField());
			
			
			Vars.infoPanel.setWhoPlayTextField("黑方行棋中");
			
			
			startReadThread();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void startReadThread() {
	    new Thread() {
	        public void run() {
	            while (true) {
	                try {
	                    String line = in.readLine();
	                   
	                    System.out.println(line);

	                    if (line.startsWith("chess:")) {
	                        if (timer == null) {
	                            timer = new TimerUpdater(Vars.infoPanel.getTimeTextField());
	                        } else {
	                            timer.stopTimer();
	                            timer = new TimerUpdater(Vars.infoPanel.getTimeTextField());
	                        }
	                        lastMove=line;
	                        otherChess(line.substring("chess:".length()));
	                    } else if (line.startsWith("chat:")) {
	                        String chatMessage = line.substring("chat:".length());
	                        // 在聊天界面的 TextArea 中显示收到的聊天消息
	                        Vars.chatPanel.getChatArea().append("对方："+chatMessage + "\n");
	                    }
	                    	else if ("Reset".equals(line)) {
	                        Vars.model.resetChessArray();
	                        Vars.chessBoard.repaint();
	                    } else if ("exit".equals(line)) {
	                        System.exit(0);
	                    } else if ("startNow".equals(line)) {
	                        if (timer == null) {
	                            timer = new TimerUpdater(Vars.infoPanel.getTimeTextField());
	                        } else {
	                            timer.stopTimer();
	                            timer = new TimerUpdater(Vars.infoPanel.getTimeTextField());
	                        }
	                    } else if ("stopTiming".equals(line)) {
	                        Vars.infoPanel.getTimeTextField().setText("0");
	                        timer.stopTimer();
	                    } else if ("whoPlay".equals(line)) {
	                        if (whoPlay == Vars.model.BLACK) {
	                            whoPlay = Vars.model.WHITE;
	                            Vars.infoPanel.setWhoPlayTextField("白方行棋中");
	                        } else {
	                            whoPlay = Vars.model.BLACK;
	                            Vars.infoPanel.setWhoPlayTextField("黑方行棋中");
	                        }
	                    } else if ("resetTimeTextField".equals(line)) {
	                        Vars.infoPanel.setTimeTextField("0");
	                        }
	                    

	                    else if ("whiteDon'tAgreeToRestart".equals(line) || "blackDon'tAgreeToRestart".equals(line)) {
	                        System.out.println("收到对方重启信息");
	                        otherSideRestart = false;
	                        Vars.net.notifyRestartMessageReceived(); // 收到重启信息时通知等待的线程
	                    } else if ("blackAgreeToRestart".equals(line) || "whiteAgreeToRestart".equals(line)) {
	                        System.out.println("收到对方重启信息");
	                        otherSideRestart = true;
	                        Vars.net.notifyRestartMessageReceived(); // 收到重启信息时通知等待的线程
	                    }
                 
	                   
	                    else if("otherSideRegret".equals(line)){
	                    	dealWithotherSideRegret();
	                    }
	                    else if("agreeToRegret".equals(line)){
	                    	System.out.println("收到同意悔棋");
	                    	undoMyLastMove();
	                    	
	                    }
	                    else if("don'tAgreeToRegret".equals(line)){
	                    	  JOptionPane.showMessageDialog(null, "对方不同意悔棋！", "悔棋请求被拒绝", JOptionPane.INFORMATION_MESSAGE);
	                    }
	                }

	                 catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }.start();
	    };
	    

	
	
	protected void undoMyLastMove() {
		
		
		// TODO Auto-generated method stub
		Vars.control.setOpenDoor(true);
		int [][]chess=Vars.model.getChessArray();
		System.out.println("");
		chess[Vars.model.lastRow][Vars.model.lastcol]=0;
		 Vars.chessBoard.repaint();
	}
	protected void dealWithotherSideRegret() {
		  int option = JOptionPane.showConfirmDialog(
	                null,
	                "是否对方同意悔棋?",
	                "同意",
	                
	                JOptionPane.YES_NO_OPTION);

	        if( option == JOptionPane.YES_OPTION){
	        	sendAgreeToRegretOrNot(true);
	        	undoOtherSideMove();
	        	
	        }
	        else{
	        	sendAgreeToRegretOrNot(false);
	        };
		
	}
	private void undoOtherSideMove() {
		if(Vars.control.getChessColor()==Vars.model.BLACK){
			Vars.net.setWhoPlay(Vars.model.WHITE);
			Vars.infoPanel.setWhoPlayTextField("白方行棋中");
		}
		else{
			Vars.net.setWhoPlay(Vars.model.BLACK);
			Vars.infoPanel.setWhoPlayTextField("黑方行棋中");
		}
		Vars.net.callForChangeWhoPlay();
		
		Vars.control.setOpenDoor(false);
		// TODO Auto-generated method stub
		if (!lastMove.isEmpty()) {
	        String[] str = lastMove.split(":");
	        // 假设棋步信息是用逗号分隔的行列坐标字符串
	        String[]parts=str[1].split(",");
	        if (parts.length == 2) {
	            int row = Integer.parseInt(parts[0]);
	            int col = Integer.parseInt(parts[1]);
	            
	            // 在这里根据 row 和 col 撤销上一步棋的操作
	            // 例如，如果你的棋盘数据结构是一个二维数组，可以将上一步棋的位置重置为空
	            // 例如：board[row][col] = EMPTY;
	            int [][]chess=Vars.model.getChessArray();
	            chess[otherSideLastRow][otherSideLastCol]=0;
	            
	            lastMove = ""; // 将最后一步棋清空，表示已悔过棋
	            Vars.chessBoard.repaint();
	        }
		}
	}
	private void sendAgreeToRegretOrNot(boolean b) {
		// TODO Auto-generated method stub
		if(b){
			out.println("agreeToRegret");
		}
		else{
			out.println("don'tAgreeToRegret");
		}
	}
	public TimerUpdater getTimer() {
		if(timer==null){
			timer=new TimerUpdater(Vars.infoPanel.getTimeTextField());
		}
		return timer;
	}
	protected void otherChess(String line) {
		String[] param = line.split(",");
		int row = Integer.parseInt(param[0]);
		int col = Integer.parseInt(param[1]);
		otherSideLastRow=row;
		otherSideLastCol=col;
		Vars.control.otherChess(row,col);
		
	}
	public void sendChess(int row,int col){
		if(out!=null){
			out.println("chess:"+row+","+col);
		}
	}
	
	public void sendChat(String line){
		if(out!=null){
			out.println("chat:"+line);
		}
	}
	public void callForStartTiming(){
		if(out!=null){
			out.println("startNow");
		}
	}
	
	public void callForReset(){
		if(out!=null){
			out.println("Reset");
		}
	}
	
	public void callForExit() {
		// TODO Auto-generated method stub
		if(out!=null){
			out.println("exit");
		}
	}
	
	public void callForStopTiming(){
		
		out.println("stopTiming");
	}
	public void callForChangeWhoPlay(){
		
		out.println("whoPlay");
	}
	
	public  void callForResetTimeTextField() {
		out.println("resetTimeTextField");
		
	}
	
	

	private final Object restartLock = new Object();
	

	public void restart() {
	    // 让对面和自己都停止计时
	    Vars.net.callForStopTiming();
	    timer.stopTimer();
	    Vars.infoPanel.setTimeTextField("0");
	    Vars.net.callForResetTimeTextField();

	    setRestart(Vars.control.askForRestart());
	    callForRestart();
	    sendRestartMessage();
	    
	    
	    System.out.println(Vars.control.getChessColor() + " restartMessageReceived");
	    System.out.println(restartMessageReplied);

	    synchronized (restartLock) {
	        try {

	                restartLock.wait(); // 等待直到有通知          
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    System.out.println("restartMessageReceived");
	    
	    if (restart && otherSideRestart) {
	        // 重绘棋盘
	    	
	        Vars.model.resetChessArray();
	        Vars.chessBoard.repaint();
	        

	        // 重置计时器
	        TimerUpdater t = Vars.net.getTimer();
	        t.stopTimer();
	        t = new TimerUpdater(Vars.infoPanel.getTimeTextField());
	        Vars.net.callForStartTiming();
	    } else {
	    	//callForClosing();
	        System.out.println("准备关闭");

	        
	        System.exit(0);
	    }
	   
	}

	
	// 这个方法用于通知等待中的线程
	public void notifyRestartMessageReceived() {
	    synchronized (restartLock) {
//	        restartMessageReplied = true;
	        restartLock.notify(); // 发送通知给等待的线程
	    }
	}

	public int getWhoPlay() {
		return whoPlay;
	}
	public void setWhoPlay(int whoPlay) {
		this.whoPlay = whoPlay;
	}
	public void setOtherSideRestart(boolean otherSideRestart) {
		this.otherSideRestart = otherSideRestart;
	}
	public void setRestart(boolean restart) {
		this.restart = restart;
	}
	
	public boolean getRestart(){
		return restart;
	}
	
	public void sendRestartMessage() {
		if(restart==false){
			if(Vars.control.getChessColor()==Vars.model.BLACK)
				out.println("blackDon'tAgreeToRestart" );
			else{
				out.println("whiteDon'tAgreeToRestart");
			}
		}
		else{
			if(Vars.control.getChessColor()==Vars.model.BLACK)
					out.println("blackAgreeToRestart" );
				else{
					out.println("whiteAgreeToRestart");
				}
		}
		
	}
	public void callForRestart(){
		if(out!=null){
			out.println("restart");
		}
	}
	public void sendRegretMessage() {
		// TODO Auto-generated method stub
		out.println("otherSideRegret");
		
	}
}
