package cn.nku.cs;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vars {
	//public static ShapeBar shapeBar = new ShapeBar();
	
	public static PaintControl control = new PaintControl();
	public static InfoPanel infoPanel = new InfoPanel();
	
	static ImageIcon icon = new ImageIcon("C:/Users/Lenovo/Desktop/java/GoBang/GoBang/GoBang/src/woodenTable.jpg"); // Ìæ»»ÎªÄãµÄ±³¾°Í¼Â·¾¶
    
	public static ChessBoard chessBoard=new ChessBoard(icon.getImage());
	
	
	public static ChatPanel chatPanel=new ChatPanel();
	
	public static ChessModel model = new ChessModel();
	public static NetHelper net = new NetHelper();
	
}
