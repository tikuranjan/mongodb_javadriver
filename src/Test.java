
public class Test {
	
	
	public static void main(String[] args){
		int a [] = {6,10,6,9,7,8};
				 
		System.out.print(solution(a));
		
	}
	 public static int solution(int[] A) {
	      sort(A, A.length);
	      int qc = 0;
	      int test[];
	      int tempqc=0;
	      for(int k=A.length-1;k>=0;k--){
	          
	          if((A[k]-A[0])<=1){
	        	    
	        	  return k+1;
	          }
	      }
	    return qc;
	    }
	    
	    private static void sort(int nos[], int n) {
	     for (int i = 1; i < n; i++){
	          int j = i;
	          int B = nos[i];
	          while ((j > 0) && (nos[j-1] > B)){
	            nos[j] = nos[j-1];
	            j--;
	          }
	          nos[j] = B;
	        }
	    }
    }

