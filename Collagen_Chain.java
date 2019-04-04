/**
 * @author Kenneth McGuinness
 * Creates a Diffusion Limited Aggregation Environment
 */
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;


public class Collagen_Chain {
	
public Vector<Collagen_Monomer> chain;

public Collagen_Chain()
{
	this.chain= new Vector<Collagen_Monomer>();
}

public Collagen_Chain(Collagen_Chain one)
{
	this.chain = one.chain;
	
}

public static void ChargedChain(Collagen_Chain one, int[] charges) throws IOException
{
	if(one.chain.size() != charges.length)
		throw new IOException();  
	
	for(int i = 0; i < one.chain.size(); i++)
	{
		one.chain.get(i).charge = charges[i];
	}

}

public static int charge(Collagen_Chain one)
{
	int overallcharge = 0;
	Iterator<Collagen_Monomer> itr = one.chain.iterator();
	
	while(itr.hasNext())
	{
		overallcharge += itr.next().charge;
	}
	
	return overallcharge;
}

public static Collagen_Chain createChainZ(Collagen_Monomer mnz)
{
	Collagen_Chain seed = new Collagen_Chain();
	
	if(Driver.sequenceLength == 1)
	{
		mnz.charge = Driver.charge.get(0);
		seed.chain.add(mnz);
		return seed;
	}
	int c = 0;
	for(int j = mnz.z ; j < mnz.z + Driver.sequenceLength ; j++)
		{
		Collagen_Monomer temp = new Collagen_Monomer(mnz.m,mnz.n,j);
		temp.charge = Driver.charge.get(c);
		c++;
		seed.chain.add(temp);
		}

	return seed;
	
}

}
