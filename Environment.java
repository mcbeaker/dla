/**
 * @author Kenneth McGuinness
 * Creates a Diffusion Limited Aggregation Environment
 */

public class Environment
{

	public Collagen_Monomer origin = new Collagen_Monomer(0,0,0);
	public double longestrad = 0;
	public double brad = 0; 
	public double krad = 0;
	public int maxz = 0;
	public int minz = 0;
	public int bmnz = 0;
	public int bmxz = 0;
	public int kminz = 0;
	public int kmaxz = 0;
	
	public Environment(double br,int bzmx) 
	{
		
		brad = br;
		krad = 2*brad;
		bmxz = bzmx;
		bmnz = -bmxz;
		kminz = 2*bmnz;
		kmaxz = 2*bmxz; 

		
	}
	public void UpdateEnv(Collagen_Chain Cch)
	{		
		
		if(DlaTools.getDistance(Cch.chain.get(0),Driver.e.origin) > Driver.e.longestrad*Driver.e.longestrad)
			Driver.e.longestrad = Math.sqrt(DlaTools.getDistance(Cch.chain.get(0),Driver.e.origin));
				
		if(Cch.chain.get(0).z < Driver.e.minz)
			Driver.e.minz = Cch.chain.get(0).z;
		if(Cch.chain.get(Cch.chain.size()-1).z > Driver.e.maxz)
			Driver.e.maxz = Cch.chain.get(Cch.chain.size()-1).z;

		//Takes into account the length of the chain and the starting conditions
		Driver.e.bmxz = Driver.e.maxz + 10;
		Driver.e.bmnz = Driver.e.minz - 20;
			
		Driver.e.brad = Driver.e.longestrad + 10;
		Driver.e.krad = 2*Driver.e.brad;
					
		Driver.e.kmaxz = Driver.e.bmxz * 2;
		Driver.e.kminz = Driver.e.bmnz * 2;
		
	}
	public void reset()
	{
		Driver.e.longestrad = 0;
		Driver.e.brad = 0; 
		Driver.e.krad = 0;
		Driver.e.maxz = 0;
		Driver.e.minz = 0;
		Driver.e.bmnz = 0;
		Driver.e.bmxz = 0;
		Driver.e.kminz = 0;
		Driver.e.kmaxz = 0;
	}

}
