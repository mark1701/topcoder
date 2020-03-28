public class FlowerGarden{
public static int [] getOrdering (int[] height, int[] bloom, int[] wilt){
 
		//TODO: Validate all entries are the same length, and no nulls. Also, give obvious answer if there is only one element.
 
		int N = height.length;

		int [] result = new int [N]; 
		boolean [] used = new boolean [N];
 
		for (int i = 0; i < N; i++){

			//calculate the i-th entry.
			int currentMax = -1;
			int currentMaxPos = -1;
 
			//First step is to go over the height array, and look into each one of the unused entries...
			for (int j = 0; j < N; j++){
				if ( !used [j] && height [j] > currentMax){ // <- Small improvement to prevent unnecessary iterations.
					//...And compare it against all the (unused) others, just to see if it's being blocked.
					boolean blocked = false;
					for ( int k = 0; k < N && !blocked; k++ ){
						if ( !used [ k ] ){
							if ( height [ j ] > height [ k ] ) {
								if ( wilt [ k ] >= bloom [ j ] && bloom [ k ] <= wilt [ j ]){ 
									blocked=true;	//It's been blocked. Break from this "k" for and go to the next entry in the "j" for.
								}
							}
						}
					}	
					if ( !blocked ){
						//Great! We have a candidate.
						if ( height [ j ] > currentMax ){
							//This one is better!
							currentMax = height [ j ];
							currentMaxPos = j;
						}
					}
				}
			}
			result [ i ] = currentMax;
			used [ currentMaxPos ] = true;
		}
 
		return result;
	}
}
