import java.util.*;

public class Test {

	public static void main(String[] args){
		runTest(new String[] {"070","500","140"}, new int[] {150,150,150}, 218);
	}

	private static boolean runTest(String[] weapons,  int[] health, int expectedResult){
		
		KiloManX instance = new KiloManX();
		int result = instance.leastShots(weapons, health);
		
		if(result != expectedResult) {				
			System.out.println("Error: getting " + result + " vs " + expectedResult);
			return false;
		}
		return true;
	}
}