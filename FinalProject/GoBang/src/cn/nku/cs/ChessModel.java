package cn.nku.cs;

import java.util.LinkedList;

public class ChessModel {
	public static final int WIDTH = 19;
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static final int SPACE = 0;
	public int lastRow;
	public int lastcol;
	
	private LinkedList<Chess> data = new LinkedList<>();
	private int[][] chess = new int[WIDTH][WIDTH];
	public void addShape(Chess shape){
		data.addLast(shape);
	}
	
	public LinkedList<Chess> getData() {
		return data;
	}
	public boolean putChess(int row, int col, int chessColor) {
		if(row>=0&&row<WIDTH&&col>=0&&col<WIDTH && chess[row][col]==SPACE){
			chess[row][col] = chessColor;
			lastRow=row;
			lastcol=col;
			return true;
		}
		// TODO Auto-generated method stub
			
		return false;
	}
	public int[][] getChessArray() {
		// TODO Auto-generated method stub
		
		return chess;
	}
	
	
	public int getChess(int row,int col){
		return chess[row][col];
	}
	public void resetChessArray(){
		int[][] newChess= new int[WIDTH][WIDTH];
		chess=newChess;
	}

	public static int getWidth() {
		return WIDTH;
	}
	
}
