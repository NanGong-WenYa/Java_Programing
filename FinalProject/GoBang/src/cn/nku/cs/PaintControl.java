package cn.nku.cs;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JOptionPane;

public class PaintControl {
	private int currentShapeType;
	private int chessColor = ChessModel.BLACK;
	private Point p1,p2;
	private boolean openDoor=true;
	
//	public void setPoint1(Point p){
//		p1 = p;
//		System.out.println(p1);
//	}
//	public void setPoint2(Point p){
//		p2 = p;
//		System.out.println(p2);
//		
//		Shape shape = new Shape(currentShapeType, p1, p2, null, null, 20,this.c, false);
//		Vars.model.addShape(shape);
//		
//		Vars.chessBoard.repaint();
//	}
	
	
//	public void reportCurrentShapeType(int shapeType){
//		this.currentShapeType = shapeType;
//	}
//
//	public void reportForeColor(Color color) {
//		this.foreColor = color;
//	}
//	public Color getForeColor() {
//		return foreColor;
//	}
//	public int getCurrentShapeType() {
//		return currentShapeType;
//	}
//	public Point getP1() {
//		return p1;
//	}
	
	public void reportUserPressMouse(int row, int col) {
		if(!openDoor) return;
		boolean success = Vars.model.putChess(row,col,chessColor);
		boolean isVictory=checkVictory(row,col,chessColor);
		if(success){
			
			//chessColor = -chessColor;
			openDoor = false;
			Vars.chessBoard.repaint();
			Vars.net.sendChess(row, col);
			if(chessColor==Vars.model.BLACK){
				Vars.net.setWhoPlay(Vars.model.WHITE);
				Vars.infoPanel.setWhoPlayTextField("�׷�������");
			}
			else{
				Vars.net.setWhoPlay(Vars.model.BLACK);
				Vars.infoPanel.setWhoPlayTextField("�ڷ�������");
			}
			Vars.net.callForChangeWhoPlay();
			
			Vars.net.getTimer().reset();
		}
		String s;
		if(chessColor==ChessModel.BLACK)
			s="�ڷ���ʤ��";
			else
				s="�׷���ʤ";
		if(isVictory){
			 JOptionPane.showMessageDialog(null, s);
			 Vars.net.callForRestart();
			 Vars.net.restart();
		       
		}
	}
	public static boolean askForRestart() {
        int option = JOptionPane.showConfirmDialog(
                null,
                "�Ƿ����¿�ʼ��Ϸ?",
                "���¿�ʼ",
                JOptionPane.YES_NO_OPTION);

        return option == JOptionPane.YES_OPTION;
    }
	
	public boolean checkVictory(int row, int col, int color) {
	    // ������ʵ�����ʤ����������߼�
	    // ���ᡢ����б�����Ƿ��������ͬ��ɫ����������һ��
	    // �����ʤ��������ɣ����� true�����򷵻� false
	    // ע�⣺����Ĳ���row��col���ܴ������һ��������꣬����Ҫ�Դ�Ϊ�����ʤ������
		if(((row-4>=0)&&(col-4>=0)&&Vars.model.getChess(row-1, col-1)==color&&Vars.model.getChess(row-2, col-2)==color&&Vars.model.getChess(row-3, col-3)==color&&Vars.model.getChess(row-4, col-4)==color)||
				((col-4>=0)&&Vars.model.getChess(row, col-1)==color&&Vars.model.getChess(row, col-2)==color&&Vars.model.getChess(row, col-3)==color&&Vars.model.getChess(row, col-4)==color)||
				((col+4<Vars.model.getWidth())&&Vars.model.getChess(row, col+1)==color&&Vars.model.getChess(row, col+2)==color&&Vars.model.getChess(row, col+3)==color&&Vars.model.getChess(row, col+4)==color)||
				
				((row+4<Vars.model.getWidth())&&Vars.model.getChess(row+1, col)==color&&Vars.model.getChess(row+2, col)==color&&Vars.model.getChess(row+3, col)==color&&Vars.model.getChess(row+4, col)==color)||
				((row-4>=0)&&Vars.model.getChess(row-1, col)==color&&Vars.model.getChess(row-2, col)==color&&Vars.model.getChess(row-3, col)==color&&Vars.model.getChess(row-4, col)==color)||
				((row-4>=0&&col+4<Vars.model.getWidth())&&Vars.model.getChess(row-1, col+1)==color)&&Vars.model.getChess(row-2, col+2)==color&&Vars.model.getChess(row-3, col+3)==color&&Vars.model.getChess(row-4, col+4)==color||
				((row+4<Vars.model.getWidth()&&col+4<Vars.model.getWidth())&&Vars.model.getChess(row+1, col+1)==color&&Vars.model.getChess(row+2, col+2)==color&&Vars.model.getChess(row+3, col+3)==color&&Vars.model.getChess(row+4, col+4)==color)||
				((row+4<Vars.model.getWidth()&&col-4>=0)&&Vars.model.getChess(row+1, col-1)==color)&&Vars.model.getChess(row+2, col-2)==color&&Vars.model.getChess(row+3, col-3)==color&&Vars.model.getChess(row+4, col-4)==color)
			return true;
		else
			return false; 
	}
	public void otherChess(int row, int col) {
		boolean success = Vars.model.putChess(row,col,-chessColor);
		if(success){
			
			//chessColor = -chessColor;
			Vars.chessBoard.repaint();
			openDoor = true;
			//Vars.net.sendChess(row, col);
		}
	}
	public void setChessColor(int color){
		chessColor = color;
	}
	public void setOpenDoor(boolean openDoor) {
		this.openDoor = openDoor;
	}
	public int getChessColor() {
		return chessColor;
	}
	
	
}
