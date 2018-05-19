package ru.javamentor.gismart.model;

public class Point {

	private int x;
	private int y;
	private String color;
	private boolean isPhoto;

	public Point() {
		isPhoto = false;
	}

	public Point(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = color;
		isPhoto = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Point point = (Point) o;

		if (x != point.x) return false;
		return y == point.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
