package util;

public class Solution {

	public static void main(String[] args) {
//		int[] A = {2,1,4,7,3,2,5};
		int[] A = {2,2,2};
		System.out.println(longestMountain(A));
	}
	
	public static int longestMountain(int[] A) {
        int maxLength = 0;
        int index = 0;
        int length = 0;
        for(int i = 1;i < A.length - 1;i++){
            if(A[i] == A[i+1]){
	        		index = i + 1;
	        		continue;
	        	}
            if(valleys(A[i-1],A[i],A[i+1]) == true && maxLength<(length = i-index+1 )){
                maxLength = length;
                index = i;
            }
        }
        return maxLength;
    }
    public static boolean valleys(int a,int b,int c){
        if(a>b&&b<c){
            return true;
        }
        return false;
    }
    public static boolean peak(int a,int b,int c){
    	if(a<b&&b>c){
            return true;
        }
    	return false;
    }

}
