package org.msn;

public class Address {
	private int x;
	private int y;
	
	public Address(int i,int j)
	{
		x = i;
		y = j;
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
	
	public String getAddress() {
		return "x: " + x + ", y: " + y;
	}
}
