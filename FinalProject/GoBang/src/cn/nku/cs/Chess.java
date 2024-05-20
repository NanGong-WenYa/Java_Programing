package cn.nku.cs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class Chess {

	public Point p1;
	public Point p2;


	public int size;
	public Color color;

	public Chess( Point p1, Point p2,
			int size, Color color) {
		super();
	
		this.p1 = p1;
		this.p2 = p2;

	
		this.size = size;
		this.color = color;

	}
	
	
	
}
