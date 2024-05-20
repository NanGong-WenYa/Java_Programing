package cn.nku.cs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.sun.prism.Image;

public class ChessBoard extends JPanel{
	private java.awt.Image backgroundImage;
	private int gap = 15;
	private int unit = 10;
	private int screenWidth;
	private int screenHeight;
	private int x1,y1;
	private int x2,y2;
//	private Point oldDragPoint,newDragPoint;

	private Point p1;

	public ChessBoard(java.awt.Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int row = (y-y1) / unit;
				if((y-y1)%unit > unit/2 ){
					row++;
				}
				int col = (x-x1) / unit;
				if( (x-x1)%unit > unit/2){
					col++;
				}
				Vars.control.reportUserPressMouse(row,col);
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				screenWidth = getWidth();
				screenHeight = getHeight();
				int min = Math.min(screenHeight, screenWidth);
				unit = (min - gap*2 )/(ChessModel.WIDTH-1);
				x1 = (screenWidth-unit*(ChessModel.WIDTH-1)) /2;
				y1 = (screenHeight-unit*(ChessModel.WIDTH-1))/2;
				repaint();
			}
		});
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		drawChessPanel(g);
		drawChess(g);
	}


	private void drawChess(Graphics g) {
		for(int row=0;row<ChessModel.WIDTH;row++){
			for(int col=0;col<ChessModel.WIDTH;col++){
				int c = Vars.model.getChess(row,col);
				if(c == ChessModel.BLACK){
					g.setColor(Color.black);
					g.fillOval(x1+unit*col-unit/2, y1+unit*row-unit/2, unit, unit);
				}else if(c==ChessModel.WHITE){
					g.setColor(Color.white);
					g.fillOval(x1+unit*col-unit/2, y1+unit*row-unit/2, unit, unit);
				}
				
			}
		}
	}


	private void drawChessPanel(Graphics g) {
		for(int i=0;i<ChessModel.WIDTH;i++){
			g.drawLine(x1, y1+unit*i, x1+unit*(ChessModel.WIDTH-1), y1+unit*i);
			g.drawLine(x1+unit*i, y1, x1+unit*i, y1+unit*(ChessModel.WIDTH-1));
		}
		
	}
}
