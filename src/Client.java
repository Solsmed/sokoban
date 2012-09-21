import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.ArrayDeque;
import java.util.ArrayList;


public class Client {
	Board gameBoard=null;
	
	public Client(){
		gameBoard=new Board();
		
	}
	
	public void readFromFile(String fileName){
		BufferedReader br = null;
		 System.out.println("hej");
		try {
 
			String lLine;
			int lNumRows=0;
 
			br = new BufferedReader(new FileReader("Maps/"+fileName));

			while(lNumRows==0&&(lLine = br.readLine()) != null){
				lNumRows=Integer.parseInt(lLine);
				gameBoard.yrange=lNumRows;
			}

//			while ((lLine = br.readLine()) != null) {


				//read each row

				for(int y=0;y<lNumRows;y++)
				{
					lLine=br.readLine();
					if(gameBoard.map==null){
						gameBoard.map= new MapNode[lLine.length()][lNumRows];
						gameBoard.xrange=lLine.length();
					}

					for(int x=0; x<lLine.length();x++){
						if(lLine.charAt(x)=='@'||lLine.charAt(x)=='+'){
							gameBoard.start=new MapNode(x,y,lLine.charAt(x));
							gameBoard.playerPos=new Point(x,y);
						}
						MapNode Node = new MapNode(x,y,lLine.charAt(x));
						gameBoard.addMapNode(x,y,Node);



					}
					//here, we would store the row somewhere, to build our board
					//in this demo, we just print it
					//System.out.println(lLine);
				}
				gameBoard.initialize();
				String path=gameBoard.BFS();
				gameBoard.printBoard();
				gameBoard.printStartBoard();
				gameBoard.printEndBoard();
				System.out.println("MOVING DOWN WEEE");
				gameBoard.Walk(new MapNode(2,3,' '));
				gameBoard.printBoard();
				gameBoard.printStartBoard();
				gameBoard.printEndBoard();

				if(path==null){
					path="";
				}
				System.out.println(path);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}








	public static void main(String[] pArgs) 
	{
		Client agent= new Client();
		agent.readFromFile("test_board_1");
	/*	if(pArgs.length<3)
		{
			System.out.println("usage: java Client host port boardnum");
			return;
		}
*/
		try
		{
		/*	Socket lSocket=new Socket(pArgs[0],Integer.parseInt(pArgs[1]));
			PrintWriter lOut=new PrintWriter(lSocket.getOutputStream());
			BufferedReader lIn=new BufferedReader(new InputStreamReader(lSocket.getInputStream()));

			lOut.println(pArgs[2]);
			lOut.flush();

			String lLine=lIn.readLine();
			
			//read number of rows
			int lNumRows=Integer.parseInt(lLine);
			agent.gameBoard.yrange=lNumRows;

			//read each row
			agent.readFromFile("test_board_1");
			for(int y=0;y<lNumRows;y++)
			{
				lLine=lIn.readLine();
				if(agent.gameBoard.map==null){
				agent.gameBoard.map= new MapNode[lLine.length()][lNumRows];
				agent.gameBoard.xrange=lLine.length();
				}
			
				for(int x=0; x<lLine.length();x++){
					if(lLine.charAt(x)=='@'||lLine.charAt(x)=='+'){
						agent.gameBoard.start=new MapNode(x,y,lLine.charAt(x));
					}
					MapNode Node = new MapNode(x,y,lLine.charAt(x));
					agent.gameBoard.addMapNode(x,y,Node);



				}
				//here, we would store the row somewhere, to build our board
				//in this demo, we just print it
				 System.out.println(lLine);
			}
			//System.out.println(agent.BFS());
			String path=agent.gameBoard.BFS();
			System.out.println(path);
			if(path==null)
				path="";
			//now, we should find a path from the player to any goal

			//we've found our solution
			//String lMySol="U R R U U L D L L U L L D R R R R L D D R U R U D L L U R";
			//these formats are also valid:
			//String lMySol="URRUULDLLULLDRRRRLDDRURUDLLUR";
			//String lMySol="0 3 3 0 0 2 1 2 2 0 2 2 1 3 3 3 3 2 1 1 3 0 3 0 1 2 2 0 3";

			//send the solution to the server
			lOut.println(path);
			lOut.flush();

			//read answer from the server
			lLine=lIn.readLine();

			System.out.println(lLine);*/
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}
}
