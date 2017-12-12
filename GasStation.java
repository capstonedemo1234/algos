package interview2;

public class GasStation {

	/*
	
	for i =  0 to gas.length 
		extra[i] = gas[i] - cost[i];	
	
	int[] gasLeft = new Array(gas.length);
	for i = 0 to gas.lenth
		if( (gasLeft[i] + extra[i]) < 0 )
			return false; 
		gasLeft[i+1] = gasLeft[i] + extra[i];
		
	 return true;
		
	
	
	*/
	
	public static void main(String[] args) {
//		int[] gas = {6,3,2,5,8};
//		int[] cost = {5,4,3,6,7};
		
//		int[] gas  = new int[]{7,7,7,7}; 
//		int[] cost = new int[]{5,6,2,6};
		
//		int[] gas  = new int[]{4,2,7,5}; 
//		int[] cost = new int[]{5,2,8,3};
		
//		int[] gas  = new int[]{1,2}; 
//		int[] cost = new int[]{2,1};
		
//		int[] gas  = new int[]{1,2, 3,4}; 
//		int[] cost = new int[]{1,2,4, 4};
		
		int[] gas  = new int[]{1,2,3,4,5}; 
		int[] cost = new int[]{1,3,2,4,5};
		
		int index = 0;
		boolean simpleMethod = canCompleteRoute(gas, cost, index);
		System.out.println("simpleMethod : start at "+ index +" : "+ simpleMethod);
		
		index = 1;
		simpleMethod = canCompleteRoute(gas, cost, index);
		System.out.println("simpleMethod : start at "+ index +" : "+ simpleMethod);
		
		index = 2;
		simpleMethod = canCompleteRoute(gas, cost, index);
		System.out.println("simpleMethod : start at "+ index +" : "+ simpleMethod);
		
		index = 3;
		simpleMethod = canCompleteRoute(gas, cost, index);
		System.out.println("simpleMethod : start at "+ index +" : "+ simpleMethod);
		
		
//         5 2 3 5 
//         5 6 3 6 
//		
//		System.out.println("gas.length/2 : " + gas.length%2);
//		
//		for(int i=0; i < gas.length; i++  ){
//			System.out.println("gas[i] + cost[i]  : " + gas[i] +" , "+  cost[i] );
//			boolean canComplete = canCompleteRoute(gas, cost , i);
//			System.out.println("gas[i] + cost[i]  = canComplete : " + gas[i] +" , "+  cost[i] +" , "+ canComplete );
//			System.out.println("canComplete : "+ canComplete);
//		}
		
		
	}
	
	public static boolean canCompleteRoute(int[] gas, int[] cost , int start){
		
		int[]  gasLeft = new int[gas.length];
		
		int curr = start;
		gasLeft[curr] = 0;
		for(int i = 0 ; i < gas.length ;i++){
			
			System.out.println("gasLeft[i] + gas[i] - cost[i] = Diff  : "+  gasLeft[curr]+ " " +  gas[curr] + " " +  cost[curr]+ " " + (gasLeft[curr] + gas[curr] - cost[curr]));
			
			if((gasLeft[curr] + gas[curr] - cost[curr]) < 0 ){
				return false;
			}
			
			if(curr == gas.length - 1 ){
				gasLeft[0] = gasLeft[curr] + gas[curr] - cost[curr];
				curr  = 0;
			}else {
				gasLeft[curr+1] = gasLeft[curr] + gas[curr] - cost[curr];
				curr = curr + 1;
			}
			
		}
		
		return true;
	
	}
	
	
public static boolean canCompleteRouteSimple (int[] gas, int[] cost , int start){
		
		int[]  gasLeft = new int[gas.length];
		
		gasLeft[0] = 0;
		for(int i = 0 ; i < gas.length ;i++){
			
			System.out.println("gasLeft[i] + gas[i] - cost[i] = Diff  : "+  gasLeft[i]+ " " +  gas[i] + " " +  cost[i]+ " " + (gasLeft[i] + gas[i] - cost[i]));
			
			if((gasLeft[i] + gas[i] - cost[i]) < 0 ){
				return false;
			}
				gasLeft[i+1] = gasLeft[i] + gas[i] - cost[i];
		}
		
		return true;
	
	} 
	
}
