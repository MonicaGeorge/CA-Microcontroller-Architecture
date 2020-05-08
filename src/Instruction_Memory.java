import java.util.ArrayList;

public class Instruction_Memory {

	static ArrayList<String> instructions;//Assume that one position in the arraylist is 32 bits
	
	public Instruction_Memory() {
		instructions=new ArrayList<String>();
	}
	
	public static String fetch(int PC) {
		return instructions.get(PC/4);
	}
	
	 public static void loadInstructions(String [] inst) {
		   for(int i=0;i<inst.length;i++) {
	         instructions.add(inst[i]);		   
		   }
	 }
}
		  
	  