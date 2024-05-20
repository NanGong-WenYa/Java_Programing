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
				Vars.infoPanel.setWhoPlayTextField("白方行棋中");
			}
			else{
				Vars.net.setWhoPlay(Vars.model.BLACK);
				Vars.infoPanel.setWhoPlayTextField("黑方行棋中");
			}
			Vars.net.callForChangeWhoPlay();
			
			Vars.net.getTimer().reset();
		}
		String s;
		if(chessColor==ChessModel.BLACK)
			s="黑方获胜！";
			else
				s="白方获胜";
		if(isVictory){
			 JOptionPane.showMessageDialog(null, s);
			 Vars.net.callForRestart();
			 Vars.net.restart();
		       
		}
	}
	public static boolean askForRestart() {
        int option = JOptionPane.showConfirmDialog(
                null,
                "是否重新开始游戏?",
                "重新开始",
                JOptionPane.YES_NO_OPTION);

        return option == JOptionPane.YES_OPTION;
    }
	
	public boolean checkVictory(int row, int col, int color) {
	    // 在这里实现你的胜利条件检测逻辑
	    // 检查横、竖、斜方向是否有五颗相同颜色的棋子连成一线
	    // 如果有胜利条件达成，返回 true，否则返回 false
	    // 注意：这里的参数row、col可能代表最后一步棋的坐标，你需要以此为起点检查胜利条件
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
