import java.io.*;
import java.io.File;
public class simulator
{
	public static int pReg,iReg;
	public static int mem[] = new int[1000]; 
	public static int reg[] = new int[10];
	public static boolean confirm=true;
	//public static boolean flag=true;
	public static void main(String[] args)throws IOException
	{
		if(args.length != 1)
		{
			System.out.println("invalid commandline!");
		}
		FileInputStream fstream = new FileInputStream(args[0]);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strline;
                while((strline = br.readLine()) != null)
                {
                        if(strline == "")
                        {
                                break;
                        }
                        if(strline.charAt(0) < 0)
                        {
                                continue;
                        }
                        String fld = strline.trim();
			String temp=fld.substring(0,3);
			int address= Integer.parseInt(temp);
                        String temp1=fld.substring(5,10);
			int instruc= Integer.parseInt(temp1);
                        mem[address] = instruc;
	//		System.out.println("the value in instruc:" + instruc);
                }
                in.close();
                System.out.println("\t \t The Mythical Machine\t \t");
                System.out.println("\tP reg \t 000000 \t \t I reg \t 000000\t");
                System.out.println("\t reg 0\t 000000 \t \t reg 5\t 000000\t");
                System.out.println("\t reg 1\t 000000 \t \t reg 6\t 000000\t");
                System.out.println("\t reg 2\t 000000 \t \t reg 7\t 000000\t");
                System.out.println("\t reg 3\t 000000 \t \t reg 8\t 000000\t");
                System.out.println("\t reg 4\t 000000 \t \t reg 9\t 000000\t");
                pReg = 100;
                //updateregisters(pReg,reg);
                for(int k=0;k<3;k++)
		{
                	cycle();
                	//System.exit(0);
		}

	}
	public static void updateregisters(int t, int [] reg)
	{
		System.out.println("the changed register" + t);
		System.out.println("All registers with updated value:");
		for(int i =0;i<reg.length;i++)
		{
			System.out.println(reg[i]);
		} 
	}
	public static void pause(String msg)
	{
		if(!confirm)
		{
			//String msg1 = "closing!";
			return;
		}
		String ans;
		ans=msg;
	//	System.out.println(msg);
		if(ans.charAt(0) == 'a' || ans.charAt(0) == 'A')
		{
			confirm=false;
		}
	}
	public static int cycle()
	{
		pause("About to Retrieve Instruction:");
		iReg=mem[pReg];
	//	for(int j=0;j< mem.length;j++)
	//	{
	//		System.out.print(mem[j]);
	//	}
		System.out.println("the ireg value:" + iReg);
		//updateregisters(iReg,reg);
		pause("About to Execute Instruction:");
		pReg=pReg+1;
	//	updateregisters(pReg,reg);
		int opcode=(iReg/10000);
		int r=(iReg/1000)%10;
		System.out.println("the value in r:" + r);
		int addr=(iReg)%1000;
		System.out.println("the value in addr:"+ addr);
	        if(opcode == 0)
                {
                        return 0;
                }
                if(opcode == 1)
                {
                        reg[r]=mem[addr];
	//		updateregisters(r,reg);
                }
                if(opcode == 2)
                {
                        mem[addr]=reg[r];
                }
                if(opcode == 3)
                {
                        reg[r]=addr;
                }
                if(opcode == 4)
                {
                     int t=reg[addr];
                        reg[r]=mem[t];
                }
                if(opcode == 5)
                {
                        reg[r]=reg[r]+reg[addr];
			//updateregisters(r,reg);
		}
                if(opcode == 6)
                {
                        reg[r]=reg[r]-reg[addr];
                }
                if(opcode == 7)
                {
                        reg[r]=reg[r]*reg[addr];
                }
                if(opcode == 8)
                {
                        reg[r]=reg[r]/reg[addr];
                }
                if(opcode == 10)
                {
                        pReg=addr;
                        updateregisters(r,reg);
                }
                if(opcode == 11)
                {
                        if(reg[r] == 0)
                        {
                                pReg = addr;
                                updateregisters(r,reg);
                        }
		 }
                updateregisters(r,reg);
                return 0;	
	}
}
