import com.sun.pisces.Surface;
import java.util.*;
import static javafx.application.Platform.exit;

public class ttt {
    magic_cube magicCube ;
    int curr_move_number;
    //1-27 mapped - divide by 9 :  / - layer, % - index
    //moves played by user
    Vector<Integer> user_val;
    Vector<Tuple> user_pos; 
    //moves by computer
    Vector<Integer> comp_val;
    Vector<Tuple> comp_pos;
    boolean first_move_comp;
    Vector<Tuple> corner_elems = new Vector<>();
    Vector<Tuple> facecentre_elems = new Vector<>();
    Vector<Tuple> edgecentre_elems = new Vector<>();
    private Tuple e1 = new Tuple(0,0,0);
    private Tuple e2 = new Tuple(0,0,2);
    private Tuple e3 = new Tuple(0,2,0);
    private Tuple e4 = new Tuple(0,2,2);
    private Tuple e5 = new Tuple(2,0,0);
    private Tuple e6 = new Tuple(2,0,2);
    private Tuple e7 = new Tuple(2,2,0);
    private Tuple e8 = new Tuple(2,2,2);
    private Tuple e9 = new Tuple(1,0,1);
    private Tuple e10 = new Tuple(1,1,0);
    private Tuple e11 = new Tuple(2,1,1);
    private Tuple e12= new Tuple(0,1,1);
    private Tuple e13 = new Tuple(1,1,2);
    private Tuple e14 = new Tuple(1,2,1);
    private Tuple e15 = new Tuple(1,0,0);
    private Tuple e16 = new Tuple(2,1,0);
    private Tuple e17 = new Tuple(1,2,0);
    private Tuple e18 = new Tuple(0,1,0);
    private Tuple e19 = new Tuple(0,0,1);
    private Tuple e20 = new Tuple(0,2,1);
    private Tuple e21 = new Tuple(2,0,1);
    private Tuple e22 = new Tuple(2,2,1);
    private Tuple e23 = new Tuple(1,0,2);
    private Tuple e24 = new Tuple(2,1,2);
    private Tuple e25 = new Tuple(1,2,2);
    private Tuple e26 = new Tuple(0,1,2);
    private Tuple e27 = new Tuple(1,1,1);
    
    
    int human_col_line;
    int comp_col_line;

    //constructor
    public ttt(boolean human_first) {
        if(human_first) {
            first_move_comp=false;
        }
        else {
            first_move_comp=true;
        }
        magicCube = new magic_cube();
        curr_move_number = 1;
        user_val = new Vector<>();
        user_pos = new Vector<>();
        comp_val = new Vector<>();
        comp_pos = new Vector<>();
        corner_elems = new Vector<>();
        corner_elems.add(e1);
        corner_elems.add(e2);
        corner_elems.add(e3);
        corner_elems.add(e4);
        corner_elems.add(e5);
        corner_elems.add(e6);
        corner_elems.add(e7);
        corner_elems.add(e8);
        facecentre_elems.add(e9);
        facecentre_elems.add(e10);
        facecentre_elems.add(e11);
        facecentre_elems.add(e12);
        facecentre_elems.add(e13);
        facecentre_elems.add(e14);
        edgecentre_elems.add(e15);
        edgecentre_elems.add(e16);
        edgecentre_elems.add(e17);
        edgecentre_elems.add(e18);
        edgecentre_elems.add(e19);
        edgecentre_elems.add(e20);
        edgecentre_elems.add(e21);
        edgecentre_elems.add(e22);
        edgecentre_elems.add(e23);
        edgecentre_elems.add(e24);
        edgecentre_elems.add(e25);
        edgecentre_elems.add(e26);       
    }
    
    
   
    //if first_move_comp = true and curr move number odd- comp chlega
    public void play(int option){
        if(option==1){
            while(human_col_line<1 && comp_col_line<1){
            //if comp plays first
                if(first_move_comp==true){
                    //comp plays
                    if(curr_move_number%2==1){
                        int n = computenextmove(option);
                        fill_pos(n, true);
                        curr_move_number++;                
                    }
                    //user's turn
                    else{
                        int n = getnextmove();
                        fill_pos(n, false);
                        curr_move_number++;
                    }   
                }  
                //user plays first
                else{
                    //comp plays
                    if(curr_move_number%2==0){
                        int n = computenextmove(option);
                        fill_pos(n, true);
                        curr_move_number++;                
                    }
                    //user's turn
                    else{
                        int n = getnextmove();
                        fill_pos(n, false);
                        curr_move_number++;                
                    }              
                } 
                human_col_line = coll_line(user_val);
                comp_col_line = coll_line(comp_val);
            } 
        }
      
      
        else if (option==2){ 
            while(human_col_line<2 && comp_col_line<2){
                //if comp plays first
                if(first_move_comp==true){
                    //hmaari baari
                    if(curr_move_number%2==1){
                        int n = computenextmove(option);
                        fill_pos(n, true);
                        curr_move_number++;                
                    }
                    //hooman's baari
                    else{
                        int n = getnextmove();
                        fill_pos(n, false);
                        curr_move_number++;
                    }   
                }  
                //hooman plays first
                else{
                    //hmaari baari
                    if(curr_move_number%2==0){
                        int n = computenextmove(option);
                        fill_pos(n, true);
                        curr_move_number++;                
                    }
                    //hooman's baari
                    else{
                        int n = getnextmove();
                        fill_pos(n, false);
                        curr_move_number++;                
                    }              
                }
              
            }
            human_col_line = coll_line2(user_val);
            comp_col_line = coll_line2(comp_val);
        }
      
      
        else{
            while(curr_move_number<21){
                //if comp plays first
                if(first_move_comp==true){
                    //hmaari baari
                    if(curr_move_number%2==1){
                        int n = computenextmove(option);
                        fill_pos(n, true);
                        curr_move_number++;                
                    }
                    //hooman's baari
                    else{
                        int n = getnextmove();
                        fill_pos(n, false);
                        curr_move_number++;
                    }   
                }  
                //hooman plays first
                else{
                    //hmaari baari
                    if(curr_move_number%2==0){
                        int n = computenextmove(option);
                        fill_pos(n, true);
                        curr_move_number++;                
                    }
                    //hooman's baari
                    else{
                        int n = getnextmove();
                        fill_pos(n, false);
                        curr_move_number++;                
                    }              
                }
                human_col_line = coll_line2(user_val);
                comp_col_line = coll_line2(comp_val);
            }
        }
      
        /*for(int i=0;i<user_val.size();i++){
            System.out.print(user_val.elementAt(i)+", ");
        }
        System.out.println();
        for(int i=0;i<comp_val.size();i++){
            System.out.print(comp_val.elementAt(i)+ ",  ");
        }*/
        System.out.println();
        if(human_col_line<comp_col_line) System.out.println("Shite! You lost!! (:");
        else if(human_col_line==comp_col_line) System.out.println("It is a draw! You are boring -.- AF");
        else System.out.println("Congrats! You won!!");
      
    }
    
    
    //fill a particular position - (position number, comp/human move)
    public void fill_pos(int n, boolean move_comp){          
        Tuple e = location(n); 
        //int index = magicCube.array.indexOf(n);
        //System.out.println("Index: "+index);
        //e.print();       
        if(move_comp==false){
            if(user_val.contains(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z]) == false){
                user_pos.add(e);
                user_val.add(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z]); 
                System.out.println("You filled "+magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z]);
            }
            else{
            System.out.println("Invalid move");
            }        
        }
        else{
            if(comp_val.contains(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z]) == false){
                comp_pos.add(e);
                comp_val.add(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z]);   
                System.out.println("I filled "+magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z]);
            }
            else{
            System.out.println("Invalid move");
            }              
        }  
        //if((curr_move_number%2==0 && first_move_comp==true) || (curr_move_number%2==1 && first_move_comp==false) )
        if(move_comp)
        {   
            System.out.println();
            magicCube.display2(user_pos, comp_pos);
        }
          
    }

    //humans next move
    public int getnextmove(){
        System.out.println("Enter the position for next move");
        Scanner s = new Scanner(System.in);
        System.out.println();  
        return s.nextInt();    
    }    

    //check if position already occupied
    public boolean occupied(int num){
        return user_val.contains(num) || comp_val.contains(num);
    }
           
    
    //computers next move - return the number
    private int computenextmove(int option){
        //first move at centre of cube - 14
        if (curr_move_number==1){
            return magicCube.magicCubeMatrix[1][1][1];
        }
        //second move - hmaari first move -- if centre khaali, cool; if not - corner chlenge 
        else if(curr_move_number==2){
            if(occupied(magicCube.magicCubeMatrix[1][1][1])==false){
                return magicCube.magicCubeMatrix[1][1][1];
            }
            else{
                return magicCube.magicCubeMatrix[2][2][0];
            }   
        }
        //corner element chlenge - assumption- human plays corner- dont assume, take following cases-- consider other cases--2 cases- human pays middle on plane OR middle on edge 
        //if human plays- we play corner
        // if human plays middle on plane - usi k plane mein corner pr chlenge
        // if human plays middle on edge - 
        else if(curr_move_number==3 && option==1){
            int num = user_val.firstElement(); 
            //location of human's first move
            Tuple num_loc = location(num);
            //if human plays at the corner
            if(atCorner(num)){
                //hum bhi corner pr chlenge - but NOT the opposite corner of the one filled in by human
                int number = 42 - (num + 14);
                Tuple opp_corner_loc = location(number); //do not make a move on THIS location
                int index_plane = magicCube.findplane(num, number);
                int [][] plane = (int[][]) magicCube.surfaces.get(index_plane);
                int n1 = plane[0][0];
                int n2 = plane[0][2];
                int n3 = plane[2][0];
                int n4 = plane[2][2];
                Tuple loc1 = location(n1);
                Tuple loc2 = location(n2);
                Tuple loc3 = location(n3);
                Tuple loc4 = location(n4);
                if (num==n1)
                {
                    //System.out.print("lel");
                    Vector<Integer> v=new Vector<>();
                    v.add(num);v.add(n2);v.add(42-num-n2);
                    Collections.sort(v);
                    if (magicCube.search(v.get(0),v.get(1),v.get(2),magicCube.l2,37))
                    {
                        return n2;
                    }
                    else
                    {
                        return n3;
                    }
                }
                if (num==n4)
                {
                   //System.out.print("yo");
                    Vector<Integer> v=new Vector<>();
                    v.add(num);v.add(n2);v.add(42-num-n2);
                    Collections.sort(v);
                    if (magicCube.search(v.get(0),v.get(1),v.get(2),magicCube.l2,37))
                    {
                        return n2;
                    }
                    else
                    {
                        return n3;
                    }
                }
                else if (num==n2)
                {
                   // System.out.print("yay");
                    Vector<Integer> v=new Vector<>();
                    v.add(num);v.add(n1);v.add(42-num-n1);
                    Collections.sort(v);
                    if (magicCube.search(v.get(0),v.get(1),v.get(2),magicCube.l2,37))
                    {
                        return n1;
                    }
                    else
                    {
                        return n4;
                    }
                }
                else if (num==n3)
                {
                   // System.out.print("yo");
                    Vector<Integer> v=new Vector<>();
                    v.add(num);v.add(n1);v.add(42-num-n1);
                    Collections.sort(v);
                    if (magicCube.search(v.get(0),v.get(1),v.get(2),magicCube.l2,37))
                    {
                        return n1;
                    }
                    else
                    {
                        return n4;
                    }
                }
                //find a corner location that is not opposite to the one filled in by human
               /* for(int i=0;i<corner_elems.size();i++){
                    int xx = (int) (corner_elems.elementAt(i).x);
                    int yy = (int) (corner_elems.elementAt(i).y);
                    int zz = (int) (corner_elems.elementAt(i).z);
                    Tuple curr_loc = new Tuple(xx,yy,zz);
                    if(!curr_loc.equals(num_loc) && !curr_loc.equals(loc1) && !curr_loc.equals(loc2) && !curr_loc.equals(loc3) && !curr_loc.equals(loc4)){
                        return magicCube.magicCubeMatrix[(int)curr_loc.x][(int)curr_loc.y][(int)curr_loc.z]; 
                    }
                }  */               
            }

            //if human plays on middle of a surface OR on middle of an edge  - same plane as human - corner - for both- (i) and (ii)
            else if(atMid(num) || atEdge(num)){
                for(int i=0; i<corner_elems.size();i++){
                    int plane_index = magicCube.findplane(num, (int) (magicCube.magicCubeMatrix[(int) corner_elems.elementAt(i).x][(int) corner_elems.elementAt(i).y][(int) corner_elems.elementAt(i).z]));
                    if(plane_index!=-1){
                        return magicCube.magicCubeMatrix[(int) corner_elems.elementAt(i).x][(int) corner_elems.elementAt(i).y][(int) corner_elems.elementAt(i).z];
                    }
                }
            }                           
        }

        else if(option==1 && curr_move_number==5){

                int chance=0;
                int lastIndex = comp_val.size() - 2 ;
                int index = lastIndex;
                while(index>=0){
                    int x = 42 - (comp_val.lastElement() + comp_val.elementAt(index));
                    Vector<Integer> vv = new Vector<>();
                    vv.add(comp_val.lastElement()); vv.add(comp_val.elementAt(index)); vv.add(x);
                    Collections.sort(vv);
                    //if x not occupied and forms a collinear line - AWESOME
                    if(occupied(x)==false && magicCube.search(vv.get(0),vv.get(1),vv.get(2),magicCube.l2,magicCube.size_l2)){
                        //System.out.print("kya");
                        chance=x;
                        break;

                    }
                    else{
                        index--;
                    }
                }
            
                //edge centre
                if(chance <=27 && chance >0)
                {
                   // System.out.print("hp");
                    return(chance);
                }
                else
                {
                int a = comp_val.lastElement();
                Tuple loc_a = location(a);
                int b = user_val.lastElement();
                int c = 42 - a - b;
                Vector<Integer> vv = new Vector<>();
                vv.add(c); vv.add(a); vv.add(b);
                Collections.sort(vv);
                    if(magicCube.search(vv.elementAt(0), vv.elementAt(1), vv.elementAt(2), magicCube.l2, magicCube.size_l2) && atCorner(b)){
                        Tuple t_a = location(a);
                        Tuple t_c = location(c);
                        Tuple t_1 = new Tuple( ((int)t_a.x + 1)%2,(int)t_a.y,(int)t_a.z);
                        Tuple t_2 = new Tuple( (int)t_a.x,((int)t_a.y + 1)%2,(int)t_a.z);
                        Tuple t_3 = new Tuple((int)t_a.x,(int)t_a.y, ((int)t_a.z + 1)%2);
                        if(t_1.equals(t_c)){
                            return magicCube.magicCubeMatrix[(int)t_3.x][(int)t_3.y][(int)t_3.z];
                        }
                        else if(t_2.equals(t_c)){
                            return magicCube.magicCubeMatrix[(int)t_1.x][(int)t_1.y][(int)t_1.z];
                        }
                        else if(t_3.equals(t_c)){
                            return magicCube.magicCubeMatrix[(int)t_2.x][(int)t_2.y][(int)t_2.z];
                        }
                    }  
                    else{
                        for(int i=0;i<corner_elems.size();i++){
                        Tuple e = corner_elems.elementAt(i);
                        if(occupied(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z])==false){
                            return magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z];
                            }
                        }
                    }

                }   
                
            }
                
        else {
            //check if comp can win(collinear line adding to 42) -  if yes, play the move
            int lastIndex = comp_val.size() - 2 ;
            int index = lastIndex;
            if(option==1){
                while(index>=0){
                    int x = 42 - (comp_val.lastElement() + comp_val.elementAt(index));
                    //if x not occupied and forms a collinear line - AWESOME
                     Vector<Integer> vv = new Vector<>();
                    vv.add(comp_val.lastElement()); vv.add(comp_val.elementAt(index)); vv.add(x);
                    Collections.sort(vv);
                    if(occupied(x)==false && magicCube.search(vv.get(0),vv.get(1),vv.get(2),magicCube.l2,magicCube.size_l2)){
                        return x;
                    }
                    else{
                        index--;
                    }
                }
            }
            else if(option==2 || option==3){
                while(index>=0){
                    int x = 42 - (comp_val.lastElement() + comp_val.elementAt(index));
                    //if x not occupied and forms a collinear line - AWESOME
                    Vector<Integer> vv = new Vector<>();
                    vv.add(comp_val.lastElement()); vv.add(comp_val.elementAt(index)); vv.add(x);
                    Collections.sort(vv);
                    if(occupied(x)==false && (magicCube.search(vv.get(0),vv.get(1),vv.get(2),magicCube.l2,magicCube.size_l2) 
                            || magicCube.search(vv.get(0),vv.get(1),vv.get(2),magicCube.l4,magicCube.size_l4))){
                        return x;
                    }
                    else{
                        index--;
                    }
                }
            }
            
            //check if human can win - if yes, block
            int lastIndex_hooman = user_val.size()- 2 ;
            int index_hooman = lastIndex_hooman;
            while(index_hooman>=0){
                int x_hooman = 42 - (user_val.lastElement() + user_val.elementAt(index_hooman));
                Vector<Integer> vv = new Vector<>();
                vv.add(user_val.lastElement()); vv.add(user_val.elementAt(index_hooman)); vv.add(x_hooman);
                Collections.sort(vv);
                if(occupied(x_hooman)==false &&  magicCube.search(vv.get(0),vv.get(1),vv.get(2),magicCube.l2, magicCube.size_l2)){
                    return x_hooman; //block 
                }
                else{
                    index_hooman--;
                }
            }    
            
            //human cant win, comp cant win - kuch bhi chal do bc - but acc to priority
            //play corner
            for(int i=0;i<corner_elems.size();i++){
                Tuple e = corner_elems.elementAt(i);
                if(occupied(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z])==false){
                    return magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z];
                }
            }
            
            if(option==2 || option==3){
                //play face centre
                for(int i=0;i<facecentre_elems.size();i++){
                    Tuple e = facecentre_elems.elementAt(i);
                    if(occupied(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z])==false){
                        return magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z];
                    }
                }            
                //play edge centre
                for(int i=0;i<edgecentre_elems.size();i++){
                    Tuple e = edgecentre_elems.elementAt(i);
                    if(occupied(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z])==false){
                        return magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z];
                    }
                }     
            }
            else{          
                //play edge centre
                for(int i=0;i<edgecentre_elems.size();i++){
                    Tuple e = edgecentre_elems.elementAt(i);
                    if(occupied(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z])==false){
                        return magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z];
                    }
                }  
                //play face centre
                for(int i=0;i<facecentre_elems.size();i++){
                    Tuple e = facecentre_elems.elementAt(i);
                    if(occupied(magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z])==false){
                        return magicCube.magicCubeMatrix[(int)e.x][(int)e.y][(int)e.z];
                    }
                }  
            }
        } 
        return 0;
    }
    
    //check if element is at the corner
    public boolean atCorner(int num){
        int index = magicCube.array.indexOf(num);
        int x = (int) Math.floor(index/9); //i
        int y = (index%9)%3; //j
        int z = (int) Math.floor((index%9)/3); //k              
        if ((x==0 || x==2) && (y!=1 && z!=1))return true;
        else return false;
    }
    //check if element is at the mid of any surface
    public boolean atMid(int num){
        int index = magicCube.array.indexOf(num);
        int x = (int) Math.floor(index/9); //i
        int y = (index%9)%3; //j
        int z = (int) Math.floor((index%9)/3); //k    
        if((x==0 || x==2) && ((y==1 && z==0) || (y==0 && z==1)) && ((y==2 && z==1) || (y==1 && z==2))){
            return true;
        }
        else if((x==1) && (y==0 || y==2) && (z==0 || z==2)){
            return true;
        }
        else return false;            
    }
    //check if element is at the edge
    public boolean atEdge(int num){
        int index = magicCube.array.indexOf(num);
        int x = (int) Math.floor(index/9); //i
        int y = (index%9)%3; //j
        int z = (int) Math.floor((index%9)/3); //k    
        if((x==0 || x==2) && (y==z && y==1)){
            return true;
        }
        else if((x==1) && ((y==1 && z==0) || (y==0 && z==1)) && ((y==2 && z==1) || (y==1 && z==2))){
            return true;
        }       
        else return false;
    }
    
    public Tuple location(int num){
        int index = magicCube.array.indexOf(num);
        //System.out.println(index + " :Index");
        int x = (int) Math.floor(index/9); //i 
        int y = (index%9)/3; //j
        int z = (int) Math.floor((index%9)%3); //k 
        
        Tuple loc = new Tuple(x, y, z);
        return loc;
    }
    
    public int coll_line(Vector<Integer> v){
        Collections.sort(v);
        int count=0;
        for (int i=0;i<v.size()-2;i++)
        {
            for (int j=i+1;j<v.size()-1;j++)
            {
                for (int k=j+1;k<v.size();k++)
                {
                    if (magicCube.search(v.get(i),v.get(j),v.get(k),magicCube.l2,37))
                    {
                        count ++;
                    }
                }
            }
        }
        return(count);
    }
    
    public int coll_line2(Vector<Integer> v){
        Collections.sort(v);
        int count=0;
        for (int i=0;i<v.size()-2;i++)
        {
            for (int j=i+1;j<v.size()-1;j++)
            {
                for (int k=j+1;k<v.size();k++)
                {
                    if (magicCube.search(v.get(i),v.get(j),v.get(k),magicCube.l2,37) || magicCube.search(v.get(i),v.get(j),v.get(k),magicCube.l4,12))
                    {
                        count ++;
                    }
                }
            }
        }
        return(count);
    }
    
   
}
