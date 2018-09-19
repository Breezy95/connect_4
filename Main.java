package Game;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner input = new Scanner(System.in);
//-------------------Initialize colors for both players--------------------------------
boolean isValid = false;
String p1Color="",p2Color;
while(!isValid) {
System.out.println("Player 1 press Y or R for yellow or red");
p1Color = input.next(); 
if(p1Color.equals("Y")|| p1Color.equals("R"))
	isValid = true;
else
	System.out.println("Enter R or Y");
}

if(p1Color.equals("Y"))
	p2Color = "R";
else p2Color = "Y";
char[][] c4grid = new char[6][7];
//input.close();
//---------------------------------------------------------------------------------------
Player p1 = new Player(p1Color);
Player p2 = new Player(p2Color);
//---------------------Start game-------------------------------

GUI.displayGrid(c4grid);
start(c4grid,p1,p2);
}
public static void start(char[][] array, Player one, Player two) {
	char[][] startArray = array;
	Scanner player = new Scanner(System.in);
	boolean victory = false,player1turn=true;
	int column=0;
	int x = 0,y=0;
	while(victory == false) {
		
		if(player1turn) {
		System.out.println("Player 1 Enter column number 0-6");
		boolean commence = false;
		while(commence == false) {
			try {
			column = player.nextInt();
			int row = columnfinder(startArray,column);
			x=row;
			y=column;
			startArray[row][column] = one.getColor().charAt(0);
		commence = true;
			} catch(InputMismatchException ex) { 
				System.out.println("WRONG INPUT\n\n Enter a number please"); 
				player.nextLine();}
			catch( Exception exc) {
				System.out.println("NUMBER OUT OF RANGE");
				player.nextLine();
			}
		}
			
			GUI.displayGrid(startArray);
		player1turn=false;
		if(victoryCheckerRow(startArray,one.getColor().charAt(0))==true || victoryCheckerCol(startArray,one.getColor().charAt(0)) ||
		   DescendingRightDiagCheck(startArray,one.getColor().charAt(0)) || DescendingLeftDiagCheck(startArray, one.getColor().charAt(0)) ||
		   AscendingRightDiagCheck(startArray, one.getColor().charAt(0)) || AscendingLeftDiagCheck(startArray,one.getColor().charAt(0))) {
			System.out.println("Player 1 wins");
			victory = true;
			break;
		
		}
		if(fullSpaceCheck(startArray)) {
			System.out.println("DRAW!!!");
			break;
		}
		System.out.println("Player 2's turn");
		}
			
		
		
		else {												//Player two's turn
			System.out.println("Player 2 enter column for either 0-6");
			boolean commence = false;
			while(commence == false) {
			try {
				column = player.nextInt();
			int row = columnfinder(startArray,column);
			startArray[row][column] = two.getColor().charAt(0);
			commence = true;
			} catch(InputMismatchException ex) { 
				System.out.println("WRONG INPUT\n\n Enter a number please"); 
				player.nextLine();}
			catch( Exception exc) {
				System.out.println("NUMBER OUT OF RANGE");
				player.nextLine();
			}
		}
				
			 
			GUI.displayGrid(startArray);
			player1turn = true;
			if((victoryCheckerRow(startArray,two.getColor().charAt(0))==true || victoryCheckerCol(startArray,two.getColor().charAt(0)) ||
					   DescendingRightDiagCheck(startArray,two.getColor().charAt(0)) || DescendingLeftDiagCheck(startArray, two.getColor().charAt(0)) ||
					   AscendingRightDiagCheck(startArray, two.getColor().charAt(0)) || AscendingLeftDiagCheck(startArray,two.getColor().charAt(0)))) {
				System.out.println("Player 2 wins");
				victory = true;
				break;
			}
			if(fullSpaceCheck(startArray)) {
				System.out.println("DRAW!!!");
				break;
			}
			System.out.println(" Player 1's turn");
			}
		
	}
}
		
	
public static int columnfinder(char[][] arr,int column){
	if(column>arr[0].length || column<0) 
		throw new NumberFormatException("NUMBER OUT OF RANGE");
	int s=arr.length-1;
	for(int i = arr.length-1;i>-1;i--) {
		if(!(arr[i][column]=='R'|| arr[i][column] == 'Y')) {
					s=i;
					break;
			}
		if((arr[0][column]=='R' || arr[0][column]== 'Y')) {
			s=-1;
		break;
		}	
		}
	return s;
	}

public static boolean victoryCheckerRow(char[][] arr,char color) {
	boolean victory = false;
	int counter = 0;
	outer:for(int i = 0;i<arr.length;i++) {
		for(int j = 0;j<arr[i].length;j++){
		if(arr[i][j] == color)
		    counter++;
		else
			counter = 0;
		if(counter ==4){
			    victory = true;
			    break outer;
			}
		}
		counter = 0;
	}
		
		return victory;
	
}
public static boolean victoryCheckerCol(char[][] arr,char color) {
	boolean victory = false;
	int counter = 0;
	outer: for(int i = 0;i<arr[0].length;i++) {
		for(int j =0;j<arr.length;j++) {
			if(arr[j][i] == color)
				counter++;
			else
				counter = 0;
			if(counter == 4) {
				victory = true;
				break outer;
			}
		}
		counter = 0;
	}
	return victory;
}



public static boolean DescendingRightDiagCheck(char[][] arr, char color) {
	boolean victory = false;
	outer:for(int i =0;i<3;i++) {
		for(int j = 0;j<4;j++) {
			if(arr[i][j] == color && arr[i+1][j+1] == color && arr[i+2][j+2] == color && arr[i+3][j+3]==color) {
				victory = true;
				break outer;
			}
		}
}	
	return victory;
}
public static boolean DescendingLeftDiagCheck(char[][] arr, char color) {
	boolean victory = false;
	outer:for(int i =2;i>-1;i--) {
		for(int j = 3;j<7;j++) {
			if(arr[i][j] == color && arr[i+1][j-1] == color && arr[i+2][j-2] == color && arr[i+3][j-3] == color) {
				victory = true;
				break outer;
			}
		}
	}
	return victory;
}
public static boolean AscendingRightDiagCheck(char[][] arr, char color) {
	boolean victory = false;
	outer: for(int i =5;i>2;i--) {
		for(int j = 0;j<3;j++) {
			if(arr[i][j] == color && arr[i-1][j+1] == color && arr[i-2][j+2] == color && arr[i-3][j+3] == color) {
				victory = true;
				break outer;
			}
		}
	}
	return victory;
}
public static boolean AscendingLeftDiagCheck(char[][] arr, char color) {
	boolean victory = false;
	outer: for(int i = 3;i<6;i++) {
		for(int j = 3;j<7;j++) {
			if(arr[i][j] == color && arr[i-1][j-1] == color && arr[i-2][j-2] == color && arr[i-3][j-3] == color) {
				victory = true;
				break outer;
			}
		}
	}
	return victory;
}
public static boolean fullSpaceCheck(char[][] arr) {
	boolean draw = false;
	outer:for(int i = 0; i<arr.length;i++) {
		for(int j = 0;j<arr[i].length;j++) {
			if((arr[i][j] == 'R' || arr[i][j] == 'Y')) {
				draw = true;
			}
			else {
			    draw = false;
			break outer;
		}
	}
	
}
return draw;
}

}