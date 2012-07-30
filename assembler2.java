import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
public class assembler2
{
	public static Map<String,String> codes = new HashMap<String,String>(10);
        static{
                codes.put("hlt","00");
                codes.put("ld", "01");
                codes.put("sto","02");
                codes.put("ld#","03");
                codes.put("ldi","04");
                codes.put("add","05");
                codes.put("sub","06");
                codes.put("mul","07");
                codes.put("div","08");
                codes.put("jmp","10");
                codes.put("jz","11");
                }
	 public static Map<String,String> lookup = new HashMap<String,String>();
        static{
                lookup.put("r0","0");
                lookup.put("r1","1");
                lookup.put("r2","2");
                lookup.put("r3","3");
                lookup.put("r4","4");
                lookup.put("r5","5");
                lookup.put("r6","6");
                lookup.put("r7","7");
                lookup.put("r8","8");
                lookup.put("r9","9");
		lookup.put("00","0");
                }
	  public static Map<String,String> listnum = new HashMap<String,String>();
        static{
                listnum.put("0","000");
                listnum.put("1","001");
                listnum.put("2","002");
                listnum.put("3","003");
                listnum.put("4","004");
                listnum.put("5","005");
                listnum.put("6","006");
                listnum.put("7","007");
                listnum.put("8","008");
                listnum.put("9","009");
                listnum.put("00","000");
                listnum.put("loop","103");
                listnum.put("done","108");
                listnum.put("nums","###");
        }
	public static void main(String[] args)throws IOException
	{
		if(args.length != 1)
		{
			System.out.println("invalid commandline!");
		}
		FileInputStream fstream = new FileInputStream(args[0]);
                Scanner strline = new Scanner(fstream);
		String[] fld = new String[50];
		String fld1;
		int pReg=100;
		String[] last = new String[100];
		String lasta;
		String lastb;
		String c = "";
		String a = "";
		String b = "";
		String s = "";
		String t = "";
		while(strline.hasNextLine())
		{
			String line = strline.nextLine();
			Scanner linescanner = new Scanner(line);
			linescanner.useDelimiter("\n");
			while(linescanner.hasNext())
			{
				String part = linescanner.next();
				fld = part.split("\\s+");
				fld1=fld[fld.length - 1];
				last=fld1.split(",");
				lasta=last[0];
				lastb=last[last.length - 1];
				if(listnum.containsKey(lastb) == true)
				{
					a = listnum.get(lastb);		
				}
				else
				{
					if(lookup.containsKey(lastb) == true)
					{
						c = "00";
						t = lookup.get(lastb);
						a = c+t;
					}
				}
				if(codes.containsKey(fld[1]) == true)
				{
					if(fld[1]=="hlt")
					{
						a="000";
						s="0";
					//	System.out.println("i am here");
					}
					b = codes.get(fld[1]);		
				}
				else
				{
					c = "000";
					b = c+fld[1]; 
					a = "";
					s="";
				}
				if(lookup.containsKey(lasta) == true)
				{
					s = lookup.get(lasta);		
				}
				if(lasta=="00" || lastb=="00")
				{
					b="00";
					a="000";
					s = "0";
				}
				if(lastb=="nums")
				{
					a="***";
					s="0";
				}
				if(lastb=="loop")
				{
					a=String.valueOf(pReg);
					s="0";
				}
					System.out.println(""+pReg+" "+b+s+a+"	"+part);
					pReg=pReg+1;
			
			}
		}
		
	}
}
