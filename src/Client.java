import java.io.*;
import java.net.*;



public class Client {
	private static GameBoard gameBoard;
	public Client(){
		gameBoard = null;
	}
	//method that reads map from local file
	public static void readFromFile(String fileName){
		BufferedReader br = null;
		try {
			String lLine;
			int lNumRows=0;
			//
			br = new BufferedReader(new FileReader("Maps/"+fileName));
			String[] lBoard = null;
			while(lNumRows==0&&(lLine = br.readLine()) != null){
				lNumRows=Integer.parseInt(lLine);
				lBoard = new String[lNumRows];
			}

			//read each row

			for(int i=0;i<lNumRows;i++)
			{
				lLine=br.readLine();
				lBoard[i] = lLine;
			}
			gameBoard = new GameBoard(lBoard);
			TreeSearcher treeSearcher = new TreeSearcher(/*gameBoard*/);

			System.out.println(treeSearcher.totalSearch());

			//System.out.println(lLine);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] pArgs) 
	{
		//Client.readFromFile("test_board_6");
		if(pArgs.length<3)
		{
			System.out.println("usage: java Client host port boardnum");
			return;
		}
		try{
			Socket lSocket=new Socket(pArgs[0],Integer.parseInt(pArgs[1]));
			PrintWriter lOut=new PrintWriter(lSocket.getOutputStream());
			BufferedReader lIn=new BufferedReader(new InputStreamReader(lSocket.getInputStream()));

			lOut.println(pArgs[2]);
			lOut.flush();

			String lLine=lIn.readLine();

			//read number of rows
			int lNumRows=Integer.parseInt(lLine);
			String[] lBoard = new String[lNumRows];

			//read each row
			//agent.readFromFile("test_board_1");
			for(int i=0;i<lNumRows;i++)
			{
				
				lLine=lIn.readLine();
				lBoard[i] = lLine;
				System.out.println(lLine);
			}
			gameBoard = new GameBoard(lBoard);
			TreeSearcher treeSearcher = new TreeSearcher(/*gameBoard*/);

//			treeSearcher.totalSearch();
			//here, we would store the row somewhere, to build our board
			//in this demo, we just print it
//			System.out.println(lLine);
			String out=treeSearcher.totalSearch();
			System.out.println(out);
			lOut.println(out);
			lOut.flush();
			
			lLine=lIn.readLine();
			System.out.println(lLine);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}
}
