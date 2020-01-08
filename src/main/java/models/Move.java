package main.java.models;

import java.io.Serializable;

public class Move implements Serializable
{
	private String targetid, sourceid, sourcebid;
	
	public Move(String targetid, String sourceid, String sourcebid)
	{
		this.targetid = "#"+targetid;
		this.sourceid = "#"+sourceid;
		this.sourcebid = "#"+sourcebid;
	}
	
	public String getTargetId()
	{
		return targetid;
	}
	
	public String getSourceId()
	{
		return sourceid;
	}
	
	public String getSourceButtonId()
	{
		return sourcebid;
	}
}
