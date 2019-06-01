package util;

public class Solution2 {
	public static void main(String[] args) {
//		int[] A = {2,1,4,7,3,2,5};
		int[] A = {2,2,2};
		System.out.println(longestMountain(A));
	}
	
	public static int longestMountain(int[] A) {
        int maxLength = 0;
        int length = 0;
        boolean up = true;
        for(int i = 0;i < A.length ;i++){
        	if(up){
        		if(A[i] < A[i + 1]){
                	length ++;
                }else{
                	up = false;
                }
        	}else{
        		if(A[i] < A[i + 1]){
                	length ++;
                }
        	}
            
            maxLength = length;
        }
        return maxLength;
    }
    
}
