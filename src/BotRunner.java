import java.util.Scanner;


public class BotRunner
{

    public static void main(String[] args)
    {
        PhaticDialogBot chatbot1 = new PhaticDialogBot();
        Scanner in = new Scanner (System.in);
        System.out.println("Welcome to the chatbot, nice to meet you.");
        String statement = in.nextLine();
        chatbot1.chatLoop(statement);

    }

}
