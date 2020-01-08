package main.java.models;

import java.io.Serializable;

public class PoleChild implements Serializable
{
	private String buttonId;
	private int index;
	private double width;
	
	public PoleChild(String buttonId, int index, double width)
	{
		this.buttonId = buttonId;
		this.index = index;
		this.width = width;
	}
	
	public String getButtonId()
	{
		return buttonId;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public double getWidth()
	{
		return width;
	}
}
