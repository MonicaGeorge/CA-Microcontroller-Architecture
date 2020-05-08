
public class Test {
	//static Instruction_Memory IM;
	static Register_File RF;
	//static Data_Memory DM;
	static ALU ALU;
	static InstructionFetch IFStage;
	static InstructionDecode IDStage;
	static Execute ExcuteStage;
	static MemoryAccess MAStage;
	static WriteBack WBStage;
	static Memory M;
	static int insructionCycles=0;
	public Test() {
	   // IM=new Instruction_Memory();
	    RF=new Register_File();	
	    M=new Memory();
	//	DM=new Data_Memory();
		ALU=new ALU();
		IFStage=new InstructionFetch();
		IDStage=new InstructionDecode();
	    ExcuteStage=new Execute();
	    MAStage=new MemoryAccess();
	    WBStage=new WriteBack();
	    
	}
	public static void main(String []  args) {
		Test t=new Test();

	 String [] instructions=new String [6];
	 //MY INSTRUCTIONS ARE ALL IN BINAARYY NOT HEXADECIMAL
	 //if you will test a branch insruction take care to let the bracnch adress is within size of instruction array array
	 
//	 instructions[0]="0001000100100011";//add
//	 instructions[1]="0000001100010001?";//sub

		instructions[0]="1000000100100001";
		instructions[1]="0110001100100001";
		instructions[2]="0001011001000101";
		instructions[3]="0010110010101011";
		instructions[4]="0111110101000010";
		instructions[5]="0100011001000011";
	// instructions[1]="0111100011000010";
	 //instructions[4]="00000000001000110001100000100000";
	 //instructions[5]="00000000001001100001100000100000";
	
	 insructionCycles=5+instructions.length-1;
//	 System.out.println("cycles"+insructionCycles);
	// System.out.println("cache data:         "+Memory.dataMem.cache[0][0]);
	 M.loadInstructions(instructions);
	 //System.out.println("cache data:     "+Memory.dataMem.cache[0][0]);
	 String execsign="";
	 String exec4="";
	 String exec2="";
	 String exec1="";
	 String dec13="";
	 String dec23="";
	
	 String dec16="";
	 String dec26="";
	 String ExecW0="";
	
	 String write1="";
	 String write2="";
	 String [] ID=new String[8];
	 String [] Excute=new String[11];
	 String[] MemoryAccess= {"0","0","0"};
	 String [] IF=new String[2];
	 int MemtoReg = 0;
	 int MemWrite;
	 int rt;int rd;
	 for(int i=0;i<insructionCycles;i++) {
		 System.out.println("-----------------------------------------------");
		 System.out.println("cycle: "+(i+1));
		 if(i>3 &&i<instructions.length+4) {
			 //System.out.println("in "+i);
			// if(dec16.equals("1")) {
			 String WriteBack=WBStage.WriteBack(ExecW0, MemoryAccess[1],dec13,dec16,RF,MemoryAccess[2]);
			 //System.out.println(M.memory[3]);
			// System.out.println(IFStage.ProgramCount);
			 //}
			 ExecW0=Excute[0];
		
			 dec13=dec23;
			 dec16=dec26;
			 dec23=""+IDStage.controlSignal.charAt(2);
			
			dec26=""+InstructionDecode.controlSignal.charAt(5);
			 }
		 if((i>2&& i<insructionCycles-1 &&i<instructions.length+3)) {
			
			      MemoryAccess=MAStage.MemAccess(Excute[0], Excute[3], execsign, Excute[1], Excute[2], exec4, exec2, exec1, M,Excute[5],Excute[6],Excute[7],Excute[8],Excute[9],Excute[10]);
			      
			// }
			 exec4=""+IDStage.controlSignal.charAt(3);
			  execsign= IDStage.signExtended;
			  exec2=""+IDStage.controlSignal.charAt(1);
			  exec1=""+IDStage.controlSignal.charAt(0);
			 }
		 if(i>1&& i<insructionCycles-2&&i<instructions.length+2) {
//		 	System.out.println("ID[O]="+ID[0]);
			 Excute=ExcuteStage.Execute(IDStage.gop, IDStage.controlSignal.charAt(4)+"", ID[1], ID[2], IDStage.signExtended, ID[3],ID[4],ID[0].charAt(0),ID[0].charAt(1),ID[0].charAt(2),ID[0].charAt(3),ID[0].charAt(5),ID[0].charAt(6),ID[5],ID[6],ID[7]);
			 MemtoReg=IDStage.controlSignal.charAt(2);
//			 MemWrite=IDStage.controlSignal.charAt(3);
			 if(i==2)
		    	  ExecW0=Excute[0];}
		 if(i>0&& i<insructionCycles-3 &&i<instructions.length+1) {
			  ID=IDStage.InstDecode(IF[0], IF[1],RF);
			  if(i==1) {
		 dec13=""+IDStage.controlSignal.charAt(2);
		// dec10=""+IDStage.controlSignal.charAt(0);
		dec16=""+InstructionDecode.controlSignal.charAt(5);
			  exec4=""+IDStage.controlSignal.charAt(3);
			  execsign= IDStage.signExtended;
			  exec2=""+IDStage.controlSignal.charAt(1);
			  exec1=""+IDStage.controlSignal.charAt(0);
			  }
			  if(i==2) {
				  dec23=""+IDStage.controlSignal.charAt(2);
					// dec20=""+IDStage.controlSignal.charAt(0);
					dec26=""+InstructionDecode.controlSignal.charAt(5);}
			  }
		 if(i<insructionCycles-4 &&i<instructions.length) {
		  IF=IFStage.InstFetch(IFStage.ProgramCount, M);}
	
		
	
	 }
//	 for(int i=0;i<insructionCycles;i++) {
//		// System.out.println(RF.registers[3].value);
//	 String [] IF=IFStage.InstFetch(IFStage.ProgramCount, IM);
//	 String [] ID=IDStage.InstDecode(IF[0], IF[1],RF);
//	 String [] Excute=ExcuteStage.Execute(IDStage.controlSignal.substring(7), IDStage.controlSignal.charAt(5)+"", ID[1], ID[2], IDStage.signExtended, ID[3]);
//	 String [] MemoryAccess= {"0","0"};
//	 if(IDStage.controlSignal.charAt(4)=='1'||IDStage.controlSignal.charAt(2)=='1'||IDStage.controlSignal.charAt(1)=='1') {
//	      MemoryAccess=MAStage.MemAccess(Excute[0], Excute[3], IDStage.signExtended, Excute[1], Excute[2], IDStage.controlSignal.charAt(4)+"", IDStage.controlSignal.charAt(2)+"", IDStage.controlSignal.charAt(1)+"", DM);
//	 }
//	 
//	 if(InstructionDecode.controlSignal.charAt(6)=='1') {
//	 String WriteBack=WBStage.WriteBack(Excute[0], MemoryAccess[1],IDStage.controlSignal.charAt(3)+"", IDStage.controlSignal.charAt(0)+"",RF);
//	 }
//	// System.out.println(IFStage.ProgramCount);
//	 }
		
		
	}}