package cn.nku.cs;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Start {
	public static void main(String[] args) {
		JFrame frame = new JFrame("painter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	    
		Vars.chatPanel.setPreferredSize(new Dimension(800,200));
		Vars.chessBoard.setPreferredSize(new Dimension(800,100));
		frame.getContentPane().add(Vars.chatPanel,BorderLayout.SOUTH);
		frame.getContentPane().add(Vars.infoPanel,BorderLayout.NORTH);
		frame.getContentPane().add(Vars.chessBoard,BorderLayout.CENTER);
		frame.setSize(800, 300);
		frame.setVisible(true);
	}
}
