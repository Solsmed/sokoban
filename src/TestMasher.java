import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * TODO Put here a description of what this class does.
 *
 * @author tim.
 *         Created Sep 6, 2012.
 */
public class TestMasher {
	public static void main(String [] args){
		int games = 0;
		String opponent="";
		Integer game = 0;
		try{
			game = Integer.parseInt(args[0]);
			games = Integer.parseInt(args[1]);
		}catch(Exception e){
			System.out.println("invalid use of the tester!");
			System.out.println("testrunner <number of games> <opponent>");
		}
		
		String port = "5032";
		
		System.out.println("--TESTMASHER--");
		System.out.println("Running " + games + " boards, starting with " + game);

		PrintStream originalOut = System.out; // To get it back later
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream newOut = new PrintStream(baos);
		System.setOut(newOut);
		Client c;
		String tmp;
		int wins = 0;
		
		for(int i=0;i<games;i++){
			c = new Client();
			String [] str = {"dd2380.csc.kth.se",port, game.toString() };
			long start = System.currentTimeMillis();
			c.main(str);
			long stop = System.currentTimeMillis();
			System.setOut(originalOut);
			tmp = baos.toString();
			System.out.print("Board #" + game + "\t");
			game++;
			
			if(tmp.contains("CORRECT SOLUTION")){
				System.out.println("Solved in " + (double)(stop-start)/1000 + " seconds");
				wins++;
			}
			else
				System.out.println("-");
			
				baos.reset();
			System.setOut(newOut);
		}
		
		System.out.flush();

		System.setOut(originalOut); // So you can print again
		    System.out.println("\nPlaying against: " + opponent);
		    System.out.println("Number of solved boards: " + wins + " of " + games + " boards");
		
	}

}