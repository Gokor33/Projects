package course_code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang.ArrayUtils;

public class Travel {
	//Initialise all the global variables
   private static double[][] distance;
   private static int[][] tempDist;
   public static int population = 20; //Number of possible solution permutations we want to work with
   private static double bestFitness;
   private static int totalCities = 8;
   //currentPop and IntermediatePop are datastructures used for bookkeeping
   /* The 7th city or gene in the 5th candidate solution or chromosome in the current          
    population will be current_population[4][6]. */  
   private static int[][] intermediatePop;
   private static int[][] currentPop; 
   private static int elitism = 1; 
   private static int[] refSolutions;
   private static double total_distance;
   private static int bestSolution;
   private static int maximumPop = 20;
   private static int maximumCities = 20;
   private static double[] fitness; //Assigns a grading weight to each gene.
   private static int maxGen = 60; //The number of iterations it takes to get the best result.
   private static double muteRate = 0.01; //Probability of gene mutation is 10%.
   private static double xoRate = 0.9; // Probability of a crossover is 90%.
   private static int[] par1 = new int[totalCities];
   private static int[] par2 = new int[totalCities];
   private static Random random = new Random();
   public static void main(String[] args){
	   distance = new double[maximumCities][maximumCities];
	   refSolutions = new int[maximumPop];
	   currentPop = new int[maximumPop][maximumCities];
	   intermediatePop = new int[maximumPop][maximumCities];
	   fitness = new double[population];
	   Reading("C:/Users/SONY-PC/Desktop/distanceFile.txt");
	   Init();
   	   System.out.println();
   	   evaluateTheChromosome();
   	   
   	   int generation = 1;
	
	   while(generation < maxGen){
		System.out.println("generation " + generation);
        produceNextGeneration();
        evaluateTheChromosome();
        generation++;
	  }
   }
   
   public static void Init(){
   	int chromosome, i, currentCity, reflength;
   	for(chromosome = 0; chromosome < population; chromosome++){
   		/*Initialise the best fitness so far in the program to 0, such that the first generation will
   		  have the best fitness to begin with.*/
   		bestFitness = 0.0;
   		//An array to store the number of cities that can be picked for building the chromosomes later.
   		for(i = 0; i < totalCities; i++){
   				refSolutions[i] = i;	
   		} 
   		//refLength gives the length of the refSolutions (equal to the total number of cities).
   		reflength = totalCities;
   		/*Set the first city of each currentPop to be 0 (Edinbrugh) so that each 
   		 iteration starts from Edinbrugh.*/
   		currentPop[chromosome][0] = refSolutions[0];
   		refSolutions[0] = refSolutions[reflength - 1];
   		//reflength--;
   		//Take repeatedly all the from refSolutions and build the chromosomes.
   		for(i = 1; i < totalCities; i++){
   			currentCity = generateBetween(1, reflength);
   			currentPop[chromosome][i] = refSolutions[currentCity];
   			refSolutions[currentCity] = refSolutions[reflength - 1];
   			reflength--;
   		}
   	}
   }
   
   public static void Reading(String a){
		Scanner input = null;
		try {
			input = new Scanner (new File(a));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// pre-read in the number of rows/columns for further use.
		int rows = 0;
		int columns = 0;
		while(input.hasNextLine())
		{
		    ++rows;
		    while(new Scanner(input.nextLine()).hasNextInt())
		    {
		        ++columns;
		    }
		}
		distance = new double[rows][columns];
		//tempDist used to store the values to be converted later.
		tempDist = new int[rows][columns];
		input.close();

		// read again and assign the number of rows and columns in the 2d array.
		try {
			input = new Scanner(new File(a));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < rows; ++i)
		{
		    for(int j = 0; j < columns; ++j)
		    {
		        if(input.hasNextInt())
		        {
		        	tempDist[i][j] = input.nextInt();
		        }
		    }
		 
		    }
		//Convert the Integer values to doubles for later use in evaluating the population.
		for(int i = 0; i< rows; i++){
	    	for (int j = 0; j< columns; j++){
	    	    String l = Integer.toString(tempDist[i][j]);
	    	    distance[i][j] = Double.parseDouble(l);
	    	}
		}
	}
   
   
    public static void evaluateTheChromosome()
	{
    	int chromosome, city, city1, city2;
		for(chromosome = 0; chromosome < population; chromosome++)
		{
			total_distance = 0;
			
			for(city = 0; city < totalCities; city++)
			{
				city1 = currentPop[chromosome][city];
				city2 = currentPop[chromosome][(city + 1) % totalCities];
				total_distance += distance[city1][city2];
			}		
			if(total_distance == 0)
			{
				System.out.println("Something funny is going on - no solution should have a tour distance of 0");
               System.exit(0);
			}
			/*Standard fitness function will give probabilities 
			 for each of the chromosomes based on their distance.*/
			fitness[chromosome] = 1.0 / total_distance;
			
			//keep track fo the best fitness so far
			if(fitness[chromosome] >= bestFitness)
			{
				bestFitness = fitness[chromosome];
				bestSolution = chromosome;
			}	
		}
		//print population and fitness
		for(chromosome = 0 ; chromosome < population ; chromosome++)
		{
			if (chromosome == bestSolution) 
           {
               System.out.print(" best so far: ");
           }
			for (city1 = 0; city1 < totalCities; city1++) 
           {
               System.out.print(currentPop[chromosome][city1] + " ");;
           }
           System.out.print("- " + fitness[chromosome] + " (distance: " + Math.round(1.0 / fitness[chromosome]) + ")\n");
		}
		System.out.print("\n");
	}
   /*Step 1: Select a random swath of consecutive alleles from parent 1. 
     Step 2: Drop the swath down to Child 1 and mark out these alleles in Parent 2.
     Step 3: Starting on the right side of the swath, grab alleles from parent 2
     and insert them in Child 1 at the right edge of the swath.
     Step 4: swap 0 (Edinbrugh) with the first element so that the permutation has Edinbrugh at start.  */
   public static int[] orderOne(int parent1, int parent2){
	   int range = par1.length; 
	   int rng1 = generateBetween(1, range);
	   int rng2 = generateBetween(1, range);
	   
	   while(rng1 >= rng2){
		  rng1 = generateBetween(1, range);
		  rng2 = generateBetween(1, range);
	   }
	   
	   for(int i = 0; i < totalCities; i++){
		   par1[i] = currentPop[parent1][i];
	   }
	   
	   for(int i = 0; i < totalCities; i++){
		   par2[i] = currentPop[parent2][i];
	   }
	   
	   int[] child = new int[range];
		  for(int i = 0; i < range; i++){
			child[i] = -1;
		}

	   for(int i = rng1; i < rng2; i++){
		   child[i] = par1[i];
	   }
	   int[] sub1 = new int[range - (rng2 - rng1)];
	   int x = 1;
	   sub1[0] = 0;
	   for(int i = 1; i < range; i++){
		   if(!Arrays.asList(ArrayUtils.toObject(child)).contains(par1[i])){
			   sub1[x] = par1[i];
			   x++;
		   }
	   }
	   int[] parent2Copy = par2.clone();
	   parent2Copy = bubbleRotate(parent2Copy, (range - rng2));
	   
	   int[] sub2 = new int[range - (rng2 - rng1)];
	   int y = 1;
	   sub2[0] = 0;
	   for(int i = 1; i < range; i++){
		   if(Arrays.asList(ArrayUtils.toObject(sub1)).contains(parent2Copy[i])){
		   sub2[y] = parent2Copy[i];
		   y++;
		  }
	   }
	   for(int i = 0; i < sub2.length; i++){
		  int position = (rng2 + i) % range;//Find and assign values to the empty indexes.
		  child[position]=sub2[i]; 
	   }
	   int swp = ArrayUtils.indexOf(child, 0);
	   int temp = child[swp];
	   child[swp] = child[0];
	   child[0] = temp;
	   return child;
   }
   //Return the parent permutation when crossover doesn't occur.
   public static int[] doNothing(int parent1){
   	int[] child = new int[totalCities];
   	for(int i = 0; i < totalCities; i++){
		   child[i] = currentPop[parent1][i];
   	}
   	return child;
   }
   
   public static void produceNextGeneration() {
		int newCandidate, gene, parent1 , parent2;
       int child[] = new int[totalCities];

       if (elitism == 1) {
           for (gene = 0; gene < totalCities; gene++) {
               intermediatePop[0][gene] = currentPop[bestSolution][gene];
           }
       }

       for (newCandidate = elitism; newCandidate < population; newCandidate++) {
    	   double probMute = randPositive();
    	   double probXo = randPositive();
           parent1 = choose();
           parent2 = choose();
           //do crossover if the random number generated is lesser than the crosoover rate.
           if(probXo <= xoRate){
           child = orderOne(parent1, parent2);
           }
           //else return the parent array as the 'new' child.
           if(probXo > xoRate){
        	   child = doNothing(parent1);
           }
           //Mutate the genes if the random number generated in less than or equal to the mutation rate.
           if(probMute <= muteRate){
           child = swapping(child);
           }
           //Assign the child elements to a new 2d array.
           for (gene = 0; gene < totalCities; gene++) {
               intermediatePop[newCandidate][gene] = child[gene];
           }
       }
       //Replace the current population elements with the new generated population.
       for (newCandidate = 0; newCandidate < population; newCandidate++) {
           for (gene = 0; gene < totalCities; gene++) {
               currentPop[newCandidate][gene] = intermediatePop[newCandidate][gene];
           }
  		}
	}
   //Mutate any 2 numbers in the array except the first one (Edinburgh).
   public static int[] swapping(int[] parent){
	   int[] array = parent.clone();
	   int len = array.length;
	   int random1 = generateBetween(1, len);
	   int random2 = generateBetween(1, len);
	   while(random1 == random2){
		   random2 = generateBetween(1, len);   
	   }
	   int moo = array[random1];
	   array[random1] = array[random2];
	   array[random2] = moo;
	   
	   return array;
   }
   //Choose two chromosome arrays based on their fitness.
   public static int choose(){
	     int select = 0;
         double accumulated, weightSum;
         accumulated = 0.0;
         weightSum = 0.0;
		 for(int i = 0; i < population; i++){
			 weightSum = weightSum + fitness[i];
		 }
		 
		 double value = randPositive() * weightSum;
		 
		 while(select < population){
			 //The higher the fitness, the more likely they are to be selected.
			 accumulated += fitness[select];
			 if(value < accumulated){
				 break;
			 }
			 if(select != population - 1){
				 select++;
			 }
		 }
		return select;
	  }
   
	    public static int generateBetween(int min, int max) {
	        return random.nextInt(max - min) + min;
	    }
	    //Generate random number between 0.0 and 1.0.
	    static double randPositive(){
	      return new Random().nextDouble();
	    }
        //Rotate the array by a given number of indexes, except the first one (Edinbrugh).
        public static int[] bubbleRotate(int[] array, int order) {
		   if (array == null || order < 0) {
		    throw new IllegalArgumentException("Illegal argument!");
		   }
	 
		   for (int i = 0; i < order; i++) {
			   for (int j = array.length - 1; j > 1; j--) {
				   int temp = array[j];
				   array[j] = array[j - 1];
				   array[j - 1] = temp;
			   }
		   }
		   return array;
	}
}
