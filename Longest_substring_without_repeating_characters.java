package interview2;

import java.util.LinkedList;
import java.util.Queue;

public class Longest_substring_without_repeating_characters {
	
	public static void main(String[] args) 
    {
        String str = "ABDEFGABEF";
//        String str = "GEEKSFORGEEKS";
//		String str = "BBBB";
//		String str = "THIS IS A REPEATING CHAR";
        System.out.println("The input string is "+str);
        int len = longestUniqueSubsttr(str);
        System.out.println("The length of "
                + "the longest non repeating character is "+len);
    }
	
	public static int longestUniqueSubsttr(String str){
		int max_len = 0;
//		int start = 0;
		int end = 0;
		char arr[] = str.toCharArray();
		int temp_max = 0;
		Queue<Character> q = new LinkedList<>();
		for(int i = 0; i< arr.length; i++){
			if(!q.contains(arr[end])){
				temp_max = temp_max + 1;
				q.add(arr[end]);
				end = end+1;
			}else{
				if(temp_max> max_len){
					max_len = temp_max;
					temp_max = 0;
				}
				for(int j = 0 ; j < q.size() ; j++){ 
					if(arr[end] == q.remove()){
						break;
					}
				}
			}
		}
		
		max_len = (temp_max> max_len) ? temp_max : max_len;
		
		return max_len;
	}

}
