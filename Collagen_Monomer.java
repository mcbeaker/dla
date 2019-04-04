/**
 * @author Kenneth McGuinness
 * Creates a Diffusion Limited Aggregation Environment
 */
class Collagen_Monomer
	{
       public int m,n,z;
       public int charge;
	   
	   public Collagen_Monomer()
	   {
		   m = 0;
		   n = 0;
		   z = 0;
		   charge = 0;
	   }
	   public Collagen_Monomer(int cm, int cn, int cz)
	   {
		   m = cm;
		   n = cn;
		   z = cz;
	   }
	   public Collagen_Monomer(int cm, int cn, int cz, int ch)
	   {
		   m = cm;
		   n = cn;
		   z = cz;
		   charge = ch;
	   }
	   	   
	   public Collagen_Monomer(Collagen_Monomer xyz)
	   {
	   m = xyz.m;
	   n = xyz.n;
	   z = xyz.z;
	   charge = xyz.charge;
	   }
	   
	   public Collagen_Monomer(int[] mnzch)
	   {
		   m = mnzch[0];
		   n = mnzch[1];
		   z = mnzch[2];
		   charge = mnzch[3];
	   }

	   
	   
       /*public int compareTo(Molecule other)
       {
               double thisdistance = Math.sqrt(Math.pow(this.cx,2)+Math.pow(this.cy,2)+ Math.pow(this.cz,2));
               double otherdistance = Math.sqrt(Math.pow(other.cx,2)+Math.pow(other.cy,2)+ Math.pow(other.cz,2));
               
			   if (thisdistance > otherdistance)
               {
                       return 1;
               }
                
			   if (thisdistance < otherdistance)
               {
                       return -1;
               }
			   
               if(thisdistance == otherdistance && other.cx!=this.cx && other.cy!=this.cy && other.cz!=this.cz)
               {
				   return 1;
			   }
                   return 0;
               
               
       }*/
}

/*// intersection
Iterator<Molecule> iter = moleculeSet.iterator();
while (iter.hasNext())
{
       Molecule m = iter.next();
       // check whether m intersects newm, if so stop moving
       if (m intersects newm)
       {
               moleculeSet.add(newm);
               break;
       }
}

		    Iterator<Molecule> mol1 = dla3d.moleculeSet.descendingIterator();
			while(mol1.hasNext())
			{
			Molecule mol = new Molecule(mol1.next());
			System.out.println("This is iterator distance: " + getDistance(mol,dla3d.start));
			}

*/
