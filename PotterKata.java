package baker.benjamin.PotterKata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PotterKata {

	private double cost;
	private int booksInSet = 5;
	private String whatSetsDidWeMake = "";
	private List<Integer> currentOrder = new ArrayList<Integer>();
	private List<String> setOptions = new ArrayList<String>();
	private List<Double> totalOptions = new ArrayList<Double>();
	private int bestOptionIndex = 0;
	
	protected void addOrder(int book) {
		currentOrder.add(book);
	}
	protected void addTotals(double total) {
		totalOptions.add(total);
	}
	protected void addSets(String set) {
		setOptions.add(set);
	}
	
	private String cheapestOption() {
		double bestOption = 0.00;
		for(double option : totalOptions) {
			if(option > bestOption) bestOption = option;
		}
		for (double option : totalOptions) {
			if (option < bestOption) bestOption = option;
		}
		bestOptionIndex = totalOptions.indexOf(bestOption);
		return String.valueOf(bestOption);
	}
	public String cheapestSet() {
		String[] bestSet = setOptions.get(bestOptionIndex).split(" ");
		String cheapestSet = "";
		for(String options : bestSet) {
			cheapestSet += "A Set of " + options + "\n";
		}
		return cheapestSet;
	}
	
	public double getCost(int book1, int book2, int book3, int book4, int book5) {
		addOrder(book1);
		addOrder(book2);
		addOrder(book3);
		addOrder(book4);
		addOrder(book5);
		

		double totalCost = 0.00;
	
		// Find All Set Options
			for(int i = booksInSet; i > 1; i--) {
				whatSetsDidWeMake = "";
				List<Integer> thisSet = new ArrayList<Integer>(currentOrder);
				//System.out.println("Sets of " + i);
				int setCount = 0;
				boolean getSets = true;
				
				while(getSets) {
					int booksInSet = 1;
					int largestNumber = 0;  //ADDED
					
					
					// Find which book is getting the most purchased and remove first
					for(int numberOfEachBook : thisSet) {
						if(numberOfEachBook > largestNumber) largestNumber = numberOfEachBook;
					}
					if(largestNumber == 0) booksInSet = 0;
					int largestIndex = thisSet.indexOf(largestNumber);
					thisSet.add(largestIndex, thisSet.get(largestIndex) -1);
					thisSet.remove(largestIndex + 1);
					
	
					for (int j = 0; j < thisSet.size(); j++) {
						
						// exludes the book with largest number
						if(j != largestIndex) {
						
							// executes the set builder
							if(thisSet.get(j) != 0) {
								
								thisSet.add(j, thisSet.get(j) - 1);
								booksInSet += 1;
								
								if(j+1 < thisSet.size()) {
									thisSet.remove(j+1);
								}
								if(booksInSet == i) break;
							}
							
						}
						
					}
					setCount += 1;
					
					if(booksInSet != 0) {
						//System.out.println(setCount + ") A set of " + booksInSet);
						whatSetsDidWeMake += booksInSet + " ";
						
						
						if(booksInSet == 5) totalCost += 30.00;
						if(booksInSet == 4) totalCost += 25.60;
						if(booksInSet == 3) totalCost += 21.60;
						if(booksInSet == 2) totalCost += 15.20;
						if(booksInSet == 1) totalCost += 8.00;
						
					} else {
					    //System.out.println("-------------------------------------");
						//System.out.println("Total Cost: " + totalCost);
						//System.out.println();
						addSets(whatSetsDidWeMake);    // make string of set and store in map with iteration - 1
						addTotals(totalCost);         // make list of costs and store in map with iteration - 1
						totalCost = 0.00;
						//System.out.println();
						getSets = false;
					}
					
				}
	
		}	
		
		return Double.parseDouble(cheapestOption());
	} 

}
