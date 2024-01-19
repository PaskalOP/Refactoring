import java.util.Scanner;

public class Game {
   public static Scanner scan = new Scanner(System.in);
    private byte input;
    private byte rand;
    private boolean boxAvailable;
   private int winner;
    public static char box[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private boolean boxEmpty;

    public Game(){
        boxEmpty = false;
        winner = 0;
        boxAvailable = false;
        System.out.println("Enter box number to select. Enjoy!\n");
    }

    private void getUserData(){
        checkEmptySpase();
        if(boxAvailable && winner==0){
            while (true) {
                input = scan.nextByte();
                if (input > 0 && input < 10) {
                    if (box[input - 1] == 'X' || box[input - 1] == 'O')
                        System.out.println("That one is already in use. Enter another.");
                    else {
                        box[input - 1] = 'X';
                        break;
                    }
                }
                else
                    System.out.println("Invalid input. Enter again.");
            }
        }

    }
    private void printBox(){
        System.out.println("\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }
    private void doEmptyBox(){
        if(!boxEmpty){
            for(int i = 0; i < 9; i++)
                box[i] = ' ';
            boxEmpty = true;
        }
    }
    private void defineWinner(){

        switch (winner){
            case 1:
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case 2:
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case 3:
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            default:
                break;

        }
    }

    private void checkEmptySpase(){
        for(int i=0; i<9; i++){
            if(box[i] != 'X' && box[i] != 'O'){
                boxAvailable = true;
                break;
            }
            else boxAvailable = false;
        }
    }

    private void doComputerStep(){
        checkEmptySpase();
        if(boxAvailable==true && winner==0){
            while (true) {
                rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
                if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                    box[rand - 1] = 'O';
                    break;
                }
            }
        }

    }
    private boolean horisontalMatch(char charOfStep){
        if ((box[0]== box[1]&& box[1]==box[2]&& box[2]==charOfStep)
                ||(box[3]== box[4]&& box[4]==box[5] &&box[5]==charOfStep)
                ||(box[6]== box[7]&& box[7]==box[8] &&box[8]==charOfStep))return true;
        else return false;
    }
    private boolean verticalMatch(char charOfStep){
        if((box[0]== box[3]&& box[3]==box[6]&&box[6]==charOfStep)
                ||(box[1]== box[4]&& box[4]==box[7]&&box[7]==charOfStep)
                ||(box[2]== box[5]&& box[5]==box[8]&&box[8]==charOfStep)) return true;
        else return false;
    }
    private boolean checkWinner(char charOfStep){
        if(horisontalMatch(charOfStep)||verticalMatch(charOfStep)
                ||(box[0]== box[4]&& box[4]==box[8]&&box[8]==charOfStep)
                ||(box[2]== box[4]&& box[4]==box[6]&&box[6]==charOfStep)) return true;
       else return false;
    }


    public void goPlay(){
        printBox();
        doEmptyBox();
        while (true) {
            getUserData();
            if(checkWinner('X')) {
                    winner=1;
                    printBox();
                    defineWinner();
                    break;
            }else {
                doComputerStep();
                if(checkWinner('O')){
                            winner=2;
                            printBox();
                            defineWinner();
                            break;
                }
            }
            printBox();
            if (!boxAvailable) {
                winner=3;
                printBox();
                defineWinner();
                break;
            }
        }

    }

}
