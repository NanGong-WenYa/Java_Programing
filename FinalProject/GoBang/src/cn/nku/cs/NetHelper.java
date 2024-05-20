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
			Vars.infoPanel.setWhoPlayTextField("�ڷ�������");
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
			
			
			Vars.infoPanel.setWhoPlayTextField("�ڷ�������");
			
			
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
	                        // ���������� TextArea ����ʾ�յ���������Ϣ
	                        Vars.chatPanel.getChatArea().append("�Է���"+chatMessage + "\n");
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
	                            Vars.infoPanel.setWhoPlayTextField("�׷�������");
	                        } else {
	                            whoPlay = Vars.model.BLACK;
	                            Vars.infoPanel.setWhoPlayTextField("�ڷ�������");
	                        }
	                    } else if ("resetTimeTextField".equals(line)) {
	                        Vars.infoPanel.setTimeTextField("0");
	                        }
	                    

	                    else if ("whiteDon'tAgreeToRestart".equals(line) || "blackDon'tAgreeToRestart".equals(line)) {
	                        System.out.println("�յ��Է�������Ϣ");
	                        otherSideRestart = false;
	                        Vars.net.notifyRestartMessageReceived(); // �յ�������Ϣʱ֪ͨ�ȴ����߳�
	                    } else if ("blackAgreeToRestart".equals(line) || "whiteAgreeToRestart".equals(line)) {
	                        System.out.println("�յ��Է�������Ϣ");
	                        otherSideRestart = true;
	                        Vars.net.notifyRestartMessageReceived(); // �յ�������Ϣʱ֪ͨ�ȴ����߳�
	                    }
                 
	                   
	                    else if("otherSideRegret".equals(line)){
	                    	dealWithotherSideRegret();
	                    }
	                    else if("agreeToRegret".equals(line)){
	                    	System.out.println("�յ�ͬ�����");
	                    	undoMyLastMove();
	                    	
	                    }
	                    else if("don'tAgreeToRegret".equals(line)){
	                    	  JOptionPane.showMessageDialog(null, "�Է���ͬ����壡", "�������󱻾ܾ�", JOptionPane.INFORMATION_MESSAGE);
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
	                "�Ƿ�Է�ͬ�����?",
	                "ͬ��",
	                
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
			Vars.infoPanel.setWhoPlayTextField("�׷�������");
		}
		else{
			Vars.net.setWhoPlay(Vars.model.BLACK);
			Vars.infoPanel.setWhoPlayTextField("�ڷ�������");
		}
		Vars.net.callForChangeWhoPlay();
		
		Vars.control.setOpenDoor(false);
		// TODO Auto-generated method stub
		if (!lastMove.isEmpty()) {
	        String[] str = lastMove.split(":");
	        // �����岽��Ϣ���ö��ŷָ������������ַ���
	        String[]parts=str[1].split(",");
	        if (parts.length == 2) {
	            int row = Integer.parseInt(parts[0]);
	            int col = Integer.parseInt(parts[1]);
	            
	            // ��������� row �� col ������һ����Ĳ���
	            // ���磬�������������ݽṹ��һ����ά���飬���Խ���һ�����λ������Ϊ��
	            // ���磺board[row][col] = EMPTY;
	            int [][]chess=Vars.model.getChessArray();
	            chess[otherSideLastRow][otherSideLastCol]=0;
	            
	            lastMove = ""; // �����һ������գ���ʾ�ѻڹ���
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
	    // �ö�����Լ���ֹͣ��ʱ
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

	                restartLock.wait(); // �ȴ�ֱ����֪ͨ          
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    System.out.println("restartMessageReceived");
	    
	    if (restart && otherSideRestart) {
	        // �ػ�����
	    	
	        Vars.model.resetChessArray();
	        Vars.chessBoard.repaint();
	        

	        // ���ü�ʱ��
	        TimerUpdater t = Vars.net.getTimer();
	        t.stopTimer();
	        t = new TimerUpdater(Vars.infoPanel.getTimeTextField());
	        Vars.net.callForStartTiming();
	    } else {
	    	//callForClosing();
	        System.out.println("׼���ر�");

	        
	        System.exit(0);
	    }
	   
	}

	
	// �����������֪ͨ�ȴ��е��߳�
	public void notifyRestartMessageReceived() {
	    synchronized (restartLock) {
//	        restartMessageReplied = true;
	        restartLock.notify(); // ����֪ͨ���ȴ����߳�
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
