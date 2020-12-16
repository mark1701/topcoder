import java.util.*;

public class Test {

	public static void main(String[] args){
		runTest(new String[] {""}, 0);
	}

	private static boolean runTest(String[] paths,  int expectedResult){
		
		RoboCourier instance = new RoboCourier();
		int result = instance.timeToDeliver(paths);
		
		if(result != expectedResult) {				
			System.out.println("Error: getting " + result + " instead of " + expectedResult);
			return false;
		}
		return true;
	}
}