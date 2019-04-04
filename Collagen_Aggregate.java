/**
 * @author Kenneth McGuinness
 * Creates a Diffusion Limited Aggregation Environment
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;


public class Collagen_Aggregate {
	
	public double cmx = 0;
	public double cmy = 0;
	public int cmz = 0;
	public int mass = 0;
	public int length = 0;
	
	public ArrayList<Collagen_Chain> aggregate;
	
	public Collagen_Aggregate() 
	{
		this.aggregate = new ArrayList<Collagen_Chain>();
	}
	
	public Collagen_Aggregate(int size)
	{
		this.aggregate = new ArrayList<Collagen_Chain>(size);
	}
	
	public Collagen_Aggregate(Collagen_Aggregate one)
	{
		this.aggregate = one.aggregate;
	}

	public double getXcenterOfmass()
	{
		return cmx/mass;
	}
	public double getYcenterOfmass()
	{
		return cmy/mass;
	}
	public double getZcenterOfmass()
	{
		return cmz/mass;
	}
	public int length()
	{
		this.length = Driver.e.maxz - Driver.e.minz;
		return length;
	}
	
	public double radius()
	{
		return Driver.e.longestrad;
	}
	
		
	
}
