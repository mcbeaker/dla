/**
 * @author Kenneth McGuinness
 * Creates a Diffusion Limited Aggregation Environment
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.Vector;
import java.util.regex.Pattern;

public class Driver {

	public static Collagen_Aggregate dla = new Collagen_Aggregate();
	public static Environment e = new Environment(10,10); //(brad,bmaxz/bminz)
	public static Collagen_Monomer lastmove = new Collagen_Monomer();
	public static Collagen_Chain tempCollagen_Chain = new Collagen_Chain();
	public static Vector<Integer> charge = new Vector<Integer>() ;
	public static int chargedGroups = 0;
	public static int numOfcombin = 0;
	public static int sequenceLength = 0;
	public static int chains = 0;
	public static Collagen_Monomer origin = new Collagen_Monomer(0,0,0);
	public static File file;
	public static File folder;
	public static File analysisFiles; 
	public static String combination = "";
	//Tracks simulation stats
	public static int numMonomers = 0;

	//Steps till bound
	public static int simStepsBound = 0;

	//Overall Steps
	public static int stepsOverall = 0;

	//Kills
	public static int kills = 0;


	//Hard coded binary sequences containing a minimum of two 1's because only one 1 forms a sheet
	public static int[] arrayBinComb =
	{3,5,6,7,9,10,11,12,13,14,15,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,33
		,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57
		,58,59,60,61,62,63,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82
		,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106
		,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,129,130,131
		,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156
		,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181
		,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206
		,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231
		,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,257
		,258,259,260,261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,279,280,281,282
		,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,305,306,307
		,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332
		,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357
		,358,359,360,361,362,363,364,365,366,367,368,369,370,371,372,373,374,375,376,377,378,379,380,381,382
		,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,406,407
		,408,409,410,411,412,413,414,415,416,417,418,419,420,421,422,423,424,425,426,427,428,429,430,431,432,433
		,434,435,436,437,438,439,440,441,442,443,444,445,446,447,448,449,450,451,452,453,454,455,456,457,458,459
		,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485
		,486,487,488,489,490,491,492,493,494,495,496,497,498,499,500,501,502,503,504,505,506,507,508,509,510,511
		,513,514,515,516,517,518,519,520,521,522,523,524,525,526,527,528,529,530,531,532,533,534,535,536,537,538
		,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,554,555,556,557,558,559,560,561,562,563,564
		,565,566,567,568,569,570,571,572,573,574,575,576,577,578,579,580,581,582,583,584,585,586,587,588,589,590
		,591,592,593,594,595,596,597,598,599,600,601,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616
		,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642
		,643,644,645,646,647,648,649,650,651,652,653,654,655,656,657,658,659,660,661,662,663,664,665,666,667,668
		,669,670,671,672,673,674,675,676,677,678,679,680,681,682,683,684,685,686,687,688,689,690,691,692,693,694
		,695,696,697,698,699,700,701,702,703,704,705,706,707,708,709,710,711,712,713,714,715,716,717,718,719,720
		,721,722,723,724,725,726,727,728,729,730,731,732,733,734,735,736,737,738,739,740,741,742,743,744,745,746
		,747,748,749,750,751,752,753,754,755,756,757,758,759,760,761,762,763,764,765,766,767,768,769,770,771,772
		,773,774,775,776,777,778,779,780,781,782,783,784,785,786,787,788,789,790,791,792,793,794,795,796,797,798
		,799,800,801,802,803,804,805,806,807,808,809,810,811,812,813,814,815,816,817,818,819,820,821,822,823,824
		,825,826,827,828,829,830,831,832,833,834,835,836,837,838,839,840,841,842,843,844,845,846,847,848,849,850
		,851,852,853,854,855,856,857,858,859,860,861,862,863,864,865,866,867,868,869,870,871,872,873,874,875,876
		,877,878,879,880,881,882,883,884,885,886,887,888,889,890,891,892,893,894,895,896,897,898,899,900,901,902
		,903,904,905,906,907,908,909,910,911,912,913,914,915,916,917,918,919,920,921,922,923,924,925,926,927,928
		,929,930,931,932,933,934,935,936,937,938,939,940,941,942,943,944,945,946,947,948,949,950,951,952,953,954
		,955,956,957,958,959,960,961,962,963,964,965,966,967,968,969,970,971,972,973,974,975,976,977,978,979,980
		,981,982,983,984,985,986,987,988,989,990,991,992,993,994,995,996,997,998,999,1000,1001,1002,1003,1004,1005
		,1006,1007,1008,1009,1010,1011,1012,1013,1014,1015,1016,1017,1018,1019,1020,1021,1022,1023};
	
	public static void main(String[] Args) throws Exception
	{
			
		//Instantiate Chain Variables
		Driver.sequenceLength = 10;
		System.out.print("Enter number of Chains: ");
		Driver.chains = IO.readInt();
		System.out.print("Enter number of simulations per combination: ");
		int simsPercombo = IO.readInt();
		
		//These start and end values are based on the location within the Driver.arrayBinComb - refer to hard coded array above for location
		System.out.println("Enter Combo Start: ");
		int comboSt = IO.readInt();
		System.out.println("Enter Combo End: ");
		int comboEnd = IO.readInt();
		
		//File checking does not occur - be careful not to overwrite files
		File dir1 = new File (".");
		Driver.folder = new File(dir1.getCanonicalPath()+ "/xyzFiles");
		Driver.folder.mkdir();
		Driver.analysisFiles = new File(dir1.getCanonicalPath()+"/Analysis");
		Driver.analysisFiles.mkdir();


		//ArrayList of Binary Vector Sequences of Driver.length
		Combinations combo = new Combinations(Combinations.getBinaryArray(Driver.sequenceLength));

		//Iterate through selected combinations
		for(int citer = comboSt; citer <= comboEnd; citer++)
		{
			//Begin Time of Simulation 
			long start = System.currentTimeMillis();
			long tempTime = System.currentTimeMillis();

			Driver.charge.addAll(combo.Array.get(citer));
			Driver.combination = Driver.charge.toString();
			//**Define Simulation Variables**//
			//Length
			double meanZlength = 0;
			double varZlength = 0;
			double meanMNlength = 0; 
			double varMNlength = 0;
			double[] aveCOM = {0,0,0};//Average Center of Mass

			//Time
			double aveElapTime = 0;
			//Overall Steps 
			double meanStepsOverall = 0;
			double varStepsOverall = 0;
			//Bound Steps
			double meanSimStepsBound = 0;
			double varSimStepsBound = 0;
			double meanCombStepsBound = 0;
			double varCombStepsBound = 0;
			
			//Statistics on rate of growth
			//Kills
			double meanKills = 0;
			double varKills = 0;
			
			//Monomers
			double meanMonomers = 0;
			double varMonomers = 0;
			int simNum = 0;


			//sim iteration# formats filename
			for(int siter = 0; siter < simsPercombo; siter++)
			{
				simNum++;
				//Seed the Environment
				Collagen_Chain one = new Collagen_Chain(Collagen_Chain.createChainZ(Driver.origin));
				Driver.dla.aggregate.add(one); 
				Driver.file = new File(Driver.folder,Driver.arrayBinComb[citer] + "_" + siter + "_" + Driver.combination + ".xyz");	 
				FileWriter writer = new FileWriter(Driver.file);
				writer.write((Driver.chains*10) + "\n");
				writer.close();

				DlaTools.appendCollagen_Chain(Driver.file,one);
				Driver.e.UpdateEnv(one);

				//Cycle Check
				for(int chiter = 1; chiter < Driver.chains; chiter++)
				{		
					String add = null;
					boolean addchain = false;

					Collagen_Monomer birthmonomer = new Collagen_Monomer(DlaTools.generateMNZch());

					while(!addchain)
					{	

						Collagen_Monomer temp = new Collagen_Monomer(birthmonomer);		
						birthmonomer = DlaTools.Move(birthmonomer);


						double distance = DlaTools.getDistance(birthmonomer,Driver.e.origin);

						//Checks interactions if near aggregate 
						if(birthmonomer.z < (Driver.e.maxz+1) && birthmonomer.z> (Driver.e.minz-9)) 
						{
							if(distance <= (Driver.e.longestrad + 1)*(Driver.e.longestrad + 1))
							{	
								add = DlaTools.checkInteraction(Driver.file,birthmonomer);
							}								
						}

						if(add == "Add")
						{
//							System.out.println("Steps Bound: " + Driver.simStepsBound);
							addchain = true;
							//Sim Steps Bound
							double deltaSimStepsBound = Driver.simStepsBound - meanSimStepsBound;
							meanSimStepsBound = meanSimStepsBound + deltaSimStepsBound/(Driver.dla.aggregate.size()-1); //Takes into account the seed monomer not moving
							varSimStepsBound = varSimStepsBound + deltaSimStepsBound*(Driver.simStepsBound - meanSimStepsBound);//Uses updated aveStepsBound
							Driver.simStepsBound = 0;
						}

						else if(add == "Collision")
						{
							birthmonomer = temp;
						}			
					}
				}
				

				//Print out Aggregate Specs
				File AggText = new File(Driver.analysisFiles,Driver.arrayBinComb[citer] + "_" + Driver.combination+ "_" + "Analysis" + ".txt");
				RandomAccessFile raf = new RandomAccessFile(AggText, "rw");
				long aggTextlength = AggText.length();
				raf.seek(aggTextlength);
				raf.writeBytes("Simulation#: " + siter + "\nCombo: " + Driver.combination.replace(" ", "") + "\n");
				raf.writeBytes("C.O.M: " + Driver.dla.getXcenterOfmass() + " " 
						+ Driver.dla.getYcenterOfmass() + " " + Driver.dla.getZcenterOfmass()+"\n");
				aveCOM[0] = aveCOM[0] + ((Driver.dla.getXcenterOfmass()- aveCOM[0])/(siter+1));
				aveCOM[1] = aveCOM[1] + ((Driver.dla.getYcenterOfmass()- aveCOM[1])/(siter+1));
				aveCOM[2] = aveCOM[2] + ((Driver.dla.getZcenterOfmass()- aveCOM[2])/(siter+1));
				raf.writeBytes("MaxZ: " + Driver.e.maxz + "\n");
				raf.writeBytes("MinZ: " + Driver.e.minz + "\n");


				//Calculate Average/Variance Z length
				int zLength = Driver.e.maxz - Driver.e.minz;
				raf.writeBytes("ZLength: " + zLength + "\n");
				double deltaZ = zLength - meanZlength;
				meanZlength = meanZlength + (deltaZ/(siter+1));
				varZlength = varZlength  + deltaZ*(zLength - meanZlength);//Uses updated meanZLength

				//Calculate Average/Variance MN length
				double deltaMN = Driver.e.longestrad - meanMNlength;
				meanMNlength = meanMNlength + deltaMN/(siter+1);
				varMNlength = varMNlength + deltaMN*(Driver.e.longestrad - meanMNlength);//Uses updated meanMNLength
				raf.writeBytes("MaxMNRadius: " + Driver.e.longestrad + "\n");

				//Calculate Kills
				double deltaKills = Driver.kills - meanKills;
				meanKills = meanKills + deltaKills/(siter+1);
				varKills = varKills + deltaKills*(Driver.kills-meanKills);
				
				//Calculate Monomers
				double deltaMonomers = Driver.numMonomers - meanMonomers;
				meanMonomers = meanMonomers + deltaMonomers/(siter+1);
				varMonomers = varMonomers + deltaMonomers*(Driver.numMonomers - meanMonomers);
				

				//Print Kills, NumOfMonomers, AveStepsBound, OverallSteps
				double varStepsBound = varSimStepsBound/(Driver.dla.aggregate.size()-2); //Takes into account seed monomer as well as a finite population factor
				raf.writeBytes("Kills: " + Driver.kills + "\nMonomers: " + Driver.numMonomers 
										+ "\nAveStepsBound: " + meanSimStepsBound + "\nVarStepsBound: " + varStepsBound + "\nStdStepsBound: " + Math.sqrt(varStepsBound) 
										+ "\nOverallSteps: " + Driver.stepsOverall + "\n");
				raf.writeBytes("Kills/Monomers: " + (double)Driver.kills/(double)Driver.numMonomers + "\n");
			
				//Calculate Overall Steps
				double deltaStepsOverall = Driver.stepsOverall - meanStepsOverall;
				meanStepsOverall = meanStepsOverall + deltaStepsOverall/(siter+1);
				varStepsOverall = varStepsOverall + deltaStepsOverall*(Driver.stepsOverall - meanStepsOverall);

				//Calculate Combination Bound Steps
				double deltaStepsBound = meanSimStepsBound - meanCombStepsBound;
				meanCombStepsBound = meanCombStepsBound + deltaStepsBound/(siter+1);
				varCombStepsBound= varCombStepsBound + deltaStepsBound*(meanSimStepsBound - meanCombStepsBound); 

				//Time elapsed per Experiment	
				long elpsdTimeMillis = System.currentTimeMillis()-tempTime;
				float elpsdTimeMin = elpsdTimeMillis/(60*1000F);
				double deltaElapTime = elpsdTimeMin - aveElapTime;
				aveElapTime = aveElapTime + (deltaElapTime/(siter+1));
				raf.writeBytes("Elapsed Time: " + elpsdTimeMin + "\n" + "\n");

				if(siter+1 != simsPercombo)
					raf.close();

				tempTime = System.currentTimeMillis();
				//Print out the Final Elapsed Time of the Experiment
				if(siter+1 == simsPercombo)
				{
					long elapsedTimeMillis = System.currentTimeMillis()-start;
					float elapsedTimeMin = elapsedTimeMillis/(60*1000F);
					
					//Print out Specs
					raf.writeBytes("***Combination Analysis***\n");
					raf.writeBytes("TotalEllapsedTime(min): " + elapsedTimeMin + "\n" );
					raf.writeBytes("AveElapTime: " + aveElapTime + "\n");
					
					//Print Z Length Info
					raf.writeBytes("***ZLength***\n");
					raf.writeBytes("AverZLength: " + meanZlength + "\n");
					double varZLength = varZlength/siter;
					raf.writeBytes("VarZLength: " + varZLength + "\n");
					raf.writeBytes("StdDZLength: " + Math.sqrt(varZLength) + "\n");
					
					//Print MN Length Info
					raf.writeBytes("***MNLength***\n");
					raf.writeBytes("AverMNLength: " + meanMNlength + "\n");
					double varMNLength = varMNlength/siter;
					raf.writeBytes("VarMNLength: " + varMNLength + "\n");
					raf.writeBytes("StdDMNLength: " + Math.sqrt(varMNLength) + "\n");
					raf.writeBytes("AveC.O.M " + aveCOM[0]  +" " + aveCOM[1]+" " + aveCOM[2]+ "\n");

					//Print Kills Info
					raf.writeBytes("***Kills***\n");
					raf.writeBytes("AverKills: "  + meanKills + "\n");
					double VarKills = varKills/siter;
					raf.writeBytes("VarKills: "  + VarKills + "\n");
					raf.writeBytes("StdDKill: "  + Math.sqrt(VarKills) + "\n");
					
					//Print Monomer Info
					raf.writeBytes("***Monomers***\n");
					raf.writeBytes("AverMonomers: " + meanMonomers + "\n");
					double VarMonomer = varMonomers/siter;
					raf.writeBytes("VarMonomers: " + VarMonomer + "\n");
					raf.writeBytes("StdDMonomers: " + Math.sqrt(VarMonomer) + "\n");
					
					//Steps Bound Info
					raf.writeBytes("***BoundSteps***\n");
					raf.writeBytes("AverStepsBound: "  + meanCombStepsBound + "\n");
					double VarCombStepsBound = varCombStepsBound/siter;
					raf.writeBytes("VarStepsBound: " + VarCombStepsBound + "\n");
					raf.writeBytes("StdDStepsBound: " + Math.sqrt(VarCombStepsBound) + "\n");
					
					//Print Steps Overall Info
					raf.writeBytes("***OverallSteps***\n");
					raf.writeBytes("AverCombStepsOverall: "  + meanStepsOverall + "\n");
					double VarStepsOverall = varStepsOverall/siter;
					raf.writeBytes("VarCombStepsOverall: " + VarStepsOverall + "\n");
					raf.writeBytes("StdDCombStepsOverall: " + Math.sqrt(VarStepsOverall) + "\n");

					raf.close();
				}

				//Reset Environment
				Driver.e.reset();
				Driver.e = new Environment(10,10);
				Driver.dla = new Collagen_Aggregate(Driver.chains);
				Driver.kills = 0;
				Driver.numMonomers = 0;
				Driver.stepsOverall = 0;

			}
			Driver.charge.clear();
		}

	}

}