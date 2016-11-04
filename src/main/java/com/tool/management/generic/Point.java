package com.tool.management.generic;

public class Point implements Comparable<Point> {

	private Double x;
	private long y;

	public Double getX()
	{
		return x;
	}

	public void setX( Double workDay )
	{
		this.x = workDay;
	}

	public long getY()
	{
		return y;
	}

	public void setY( long y )
	{
		this.y = y;
	}

	public int compareTo( Point o )
	{
		int last = this.x.compareTo(o.x);
        return last == 0 ? this.x.compareTo(o.x) : last;
	}

}
