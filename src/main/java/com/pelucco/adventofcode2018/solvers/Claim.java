package com.pelucco.adventofcode2018.solvers;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Claim {

	private String id;
	private String leftMargin;
	private String topMargin;
	private String width;
	private String height;

	// coordinates
	int x0, y0, x1, y1;
	private List<String> coordinates;

	
	private Rectangle rectangle;
	
	private Point p1;
	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public Point getP3() {
		return p3;
	}

	public void setP3(Point p3) {
		this.p3 = p3;
	}

	public Point getP4() {
		return p4;
	}

	public void setP4(Point p4) {
		this.p4 = p4;
	}

	private Point p2;
	private Point p3;
	private Point p4;

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	// #13 @ 374,584: 10x10
	public Claim(String x) {
		String[] split = StringUtils.split(x, " ");

		// assuming strings are correct
		id = split[0];

		String marginSplit = StringUtils.replace(split[2], ":", "");
		String whSplit = split[3];

		leftMargin = StringUtils.split(marginSplit, ",")[0];
		topMargin = StringUtils.split(marginSplit, ",")[1];

		width = StringUtils.split(whSplit, "x")[0];
		height = StringUtils.split(whSplit, "x")[1];

		// coordinates
		x0 = Integer.valueOf(leftMargin);
		y0 = Integer.valueOf(topMargin);
		x1 = Integer.valueOf(leftMargin) + Integer.valueOf(width);
		y1 = Integer.valueOf(topMargin) + Integer.valueOf(height);

		// vertexes
		p1 = new Point(x0, y0);
		p2 = new Point(x0 + Integer.valueOf(width), y0);
		p3 = new Point(x0, y0 + Integer.valueOf(height));
		p4 = new Point(x0 + Integer.valueOf(width), y0 + Integer.valueOf(height));
		
		setRectangle(new Rectangle(x0, y0, Integer.valueOf(width), Integer.valueOf(height)));

		coordinates = findCoordinates(this);

	}

	private List<String> findCoordinates(Claim claim) {
		List<String> list = new LinkedList<String>();
		for (int x = claim.getX0(); x < claim.getX1(); x++) {
			for (int y = claim.getY0(); y < claim.getY1(); y++) {
				list.add(x + "x" + y);
			}
		}
		return list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLeftMargin() {
		return leftMargin;
	}

	public void setLeftMargin(String leftMargin) {
		this.leftMargin = leftMargin;
	}

	public String getTopMargin() {
		return topMargin;
	}

	public void setTopMargin(String topMargin) {
		this.topMargin = topMargin;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public List<String> getCoordinates() {
		return coordinates;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

}
