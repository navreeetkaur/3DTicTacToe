import java.util.*;
public class main{

    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Enter a number bw 1 to 6 to generate a magic cube");
        //Scanner s = new Scanner(System.in);
        //int n = s.nextInt();
        Random rand = new Random();
        int n = rand.nextInt(5) + 0;
        magic_cube mc = new magic_cube();
        mc.display();
             
        System.out.println("Do you want to play first? Answer in Y/N");
        Scanner s = new Scanner(System.in);
        char c = s.next().charAt(0);
        boolean human_first;
        if(c=='Y'){
            human_first=true;
        }
        else{
            human_first=false;
        }
        System.out.println("Playing options:");
        System.out.println("1:one collinear line");
        System.out.println("2:two collinear lines");
        System.out.println("3:who so ever gets more lines than its opponent after 20th move");
        Scanner ss = new Scanner(System.in);
        int num = s.nextInt();

        ttt game = new ttt(human_first); // true if human plays first
        game.play(num);
                
    }
    
}
