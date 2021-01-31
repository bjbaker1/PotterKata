package baker.benjamin.PotterKata;

public class PotterApp {

	public static void main(String[] args) {
		
		PotterKata potter = new PotterKata();

		potter.getCost(2,2,2,1,1);
		System.out.println("Potter Kata: Best Set Option");  
		System.out.println("------------------------------");
		System.out.print(potter.cheapestSet() );
		System.out.println("___________________________________");
		System.out.println("Total Cost: " + potter.getCost(2,2,2,1,1));
	}

}
