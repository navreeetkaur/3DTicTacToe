import java.util.*;

public class magic_cube {
    //int[][][] mc = new int [3][3][3];
    int[][] mc = new int[3][9];
    int[][][] magicCubeMatrix = new int[3][3][3]; 
    int[][] num = new int[][]{{3,1,9},{3,9,1},{1,3,9},{1,9,3},{9,3,1},{9,1,3}};
    List surfaces = new ArrayList();
    int [][] v1 = new int[][]{{0,1,2,1,2,0,2,0,1}, {2,0,1,0,1,2,1,2,0}, {1,2,0,2,0,1,0,1,2}};
    int [][] v2 = new int[][]{{0,1,2,2,0,1,1,2,0}, {1,2,0,0,1,2,2,0,1},{2,0,1,1,2,0,0,1,2}};
    int [][] v3 = new int[][]{{0,2,1,1,0,2,2,1,0}, {1,0,2,2,1,0,0,2,1}, {2,1,0,0,2,1,1,0,2}};
    int[][] p1;
    int[][] p2;
    int[][] p3;
    int[][] p4;
    int[][] p5;
    int[][] p6;
    int[][] p7;
    int[][] p8;
    int[][] p9;
    int[][] p10; //diagonal
    int[][] p11; //diagonal
    Vector<Integer> array = new Vector<Integer>();
    Vector<int[]> s1 = new Vector<int[]>(3); //layer 1
    Vector<int[]> s2 = new Vector<int[]>(3); //layer 2
    Vector<int[]> s3 = new Vector<int[]>(3); //layer 3

    int size_l1;
    int size_l2;
    int size_l3;
    int size_l4;
    int [][] list;
    int[][] l1;             //numbers adding to 42 on different surfaces
    int[][] l2;             //numbers adding to 42 in same row or column or diagonal             
    int[][] l3;             //numbers adding to 42 on same surface but not in line
    int[][] l4;             //numbers in line not adding to 42 on diagonals

    public void sort_list(int[][] ls,int cnt )
    {
        for (int i=0;i<cnt;i++)
        {
            int a= ls[i][0];
            int b= ls[i][1];
            int c= ls[i][2];
            if (a<b)
            {
                if (a<c)
                {
                    ls[i][0]=a;
                    if (b<c)
                    {
                        ls[i][1]=b;
                        ls[i][2]=c;
                    }
                    else 
                    {
                        ls[i][1]=c;
                        ls[i][2]=b;
                    }
                }
                else
                {
                    ls[i][0]=c;
                    ls[i][1]=a;
                    ls[i][2]=b;
                }
            }
            else
            {
                if (a>c)
                {
                    ls[i][2]=a;
                    if (b>c)
                    {
                        ls[i][1]=b;
                        ls[i][0]=c;
                    }
                    else 
                    {
                        ls[i][1]=c;
                        ls[i][0]=b;
                    }
                }
                else
                {
                    ls[i][2]=c;
                    ls[i][1]=a;
                    ls[i][0]=b;
                }
            }

        }
    }

    public void sort_ls(int[][] ls,int cnt)
    {
        for (int i=cnt-1;i>=0;i--)
        {
            for (int j=0;j<i;j++)
            {
                if (ls[j][0]>ls[j+1][0])
                {
                    int temp1 =ls[j][0];
                    ls[j][0]=ls[j+1][0];
                    ls[j+1][0]=temp1;      
                    int temp2 =ls[j][1];
                    ls[j][1]=ls[j+1][1];
                    ls[j+1][1]=temp2;
                    int temp3 =ls[j][2];
                    ls[j][2]=ls[j+1][2];
                    ls[j+1][2]=temp3;              //swap
                }
                else if (ls[j][0]==ls[j+1][0])
                {
                    if (ls[j][1]>ls[j+1][1])
                    {
                        int temp1 =ls[j][0];
                        ls[j][0]=ls[j+1][0];
                        ls[j+1][0]=temp1;      
                        int temp2 =ls[j][1];
                        ls[j][1]=ls[j+1][1];
                        ls[j+1][1]=temp2;
                        int temp3 =ls[j][2];
                        ls[j][2]=ls[j+1][2];
                        ls[j+1][2]=temp3;    //swap
                    }
                    else if (ls[j][1]==ls[j+1][1])
                    {
                        if (ls[j][2]>ls[j+1][2])
                        {
                            int temp1 =ls[j][0];
                            ls[j][0]=ls[j+1][0];
                            ls[j+1][0]=temp1;      
                            int temp2 =ls[j][1];
                            ls[j][1]=ls[j+1][1];
                            ls[j+1][1]=temp2;
                            int temp3 =ls[j][2];
                            ls[j][2]=ls[j+1][2];
                            ls[j+1][2]=temp3;   
                            //swap
                        }
                    }
                }
            }
        }
    }

    //input x,y,z in ascending order
    public boolean is_smaller(int x,int y,int z,int[][] ls,int cnt)
    {
        boolean b;
        if (x<ls[cnt][0])
        {
            b=true;
        }
        else if (x==ls[cnt][0])
        {
            if (y<ls[cnt][1])
            {
                b=true;
            }
            else if (y==ls[cnt][1])
            {
                if (z<ls[cnt][2])
                {
                    b=true;
                }
                else
                {
                    b=false;
                }
            }
            else 
            {
                b=false;
            }
        }
        else 
        {
            b=false;
        }
        return(b);
    }

    public boolean search(int xx ,int yy, int zz, int[][] ls,int cnt)
    {
        //sort x,y,z
        List<Integer> numbers = new ArrayList<>();
        numbers.add(xx);
        numbers.add(yy);
        numbers.add(zz);
        Collections.sort(numbers);
        int x = numbers.get(0);
        int y = numbers.get(1);
        int z = numbers.get(2); 
        
        boolean b=false;
        int lower=0;
        int upper=cnt-1;
        while (upper >=lower)
        {
            int mid=(upper+lower)/2;
            //System.out.println(mid);
            if (ls[mid][0]==x && ls[mid][1]==y && ls[mid][2]==z)
            {
                //System.out.println("hp");
                b=true;
                break;
            }
            else if (is_smaller(x,y,z,ls,mid))
            {
                upper=mid-1;
            }
            else
            {
                lower=mid+1;
            }
        }
       /* if (upper==lower){
            if (ls[upper][0]==x && ls[upper][1]==y && ls[upper][2]==z)
            {
                b=true;
            }
        }*/
        return(b);
    
    }

    public void make_list()
    {
        int count =0;
        for (int i=1;i<14;i++)
        {
            for (int j=i+1;j<27;j++)
            {
                for (int k=j+1;k<=27;k++)
                {
                    if (i+j+k==42)
                    {
                        list[count][0]=i;
                        list[count][1]=j;
                        list[count][2]=k;
                        count++;
                    }
                }
            }
        }
    }

    public void make_list1()
    {
      // int ct = 0;
       for (int times=0;times <85;times++)
       {
            int x=list[times][0];
            int y=list[times][1];
            int z=list[times][2];
            if (!search(x,y,z,l2,size_l2))
            {
                l1[size_l1][0]=x;
                l1[size_l1][1]=y;
                l1[size_l1][2]=z;
                size_l1++;
            }
        }
    }

   public void make_list2()
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                for (int k=0;k<3;k++)
                {l2[size_l2][k]=magicCubeMatrix[i][j][k];}
                size_l2++;
            }
        }
        // System.out.print("size_l2="+ size_l2);
        for (int j=0;j<3;j++)
        {
            for (int k=0;k<3;k++)
            {
                for (int i=0;i<3;i++)
                {l2[size_l2][i]=magicCubeMatrix[i][j][k];}
                size_l2++;
            }
        }
        //  System.out.print("size_l2="+ size_l2);
        for (int k=0;k<3;k++)
        {
            for (int i=0;i<3;i++)
            {
                for (int j=0;j<3;j++)
                {l2[size_l2][j]=magicCubeMatrix[i][j][k];}
                size_l2++;
            }
        }
        //  System.out.print("size_l2="+ size_l2);
        l2[27][0]=magicCubeMatrix[0][0][0];l2[27][1]=magicCubeMatrix[1][1][1];l2[27][2]=magicCubeMatrix[2][2][2];
        l2[28][0]=magicCubeMatrix[0][0][2];l2[28][1]=magicCubeMatrix[1][1][1];l2[28][2]=magicCubeMatrix[2][2][0];
        l2[29][0]=magicCubeMatrix[2][0][0];l2[29][1]=magicCubeMatrix[1][1][1];l2[29][2]=magicCubeMatrix[0][2][2];
        l2[30][0]=magicCubeMatrix[0][2][0];l2[30][1]=magicCubeMatrix[1][1][1];l2[30][2]=magicCubeMatrix[2][0][2];
        l2[31][0]=magicCubeMatrix[1][0][0];l2[31][1]=magicCubeMatrix[1][1][1];l2[31][2]=magicCubeMatrix[1][2][2];
        l2[32][0]=magicCubeMatrix[1][0][2];l2[32][1]=magicCubeMatrix[1][1][1];l2[32][2]=magicCubeMatrix[1][2][0];
        l2[33][0]=magicCubeMatrix[0][0][1];l2[33][1]=magicCubeMatrix[1][1][1];l2[33][2]=magicCubeMatrix[2][2][1];
        l2[34][0]=magicCubeMatrix[0][2][1];l2[34][1]=magicCubeMatrix[1][1][1];l2[34][2]=magicCubeMatrix[2][0][1];
        l2[35][0]=magicCubeMatrix[0][1][0];l2[35][1]=magicCubeMatrix[1][1][1];l2[35][2]=magicCubeMatrix[2][1][2];
        l2[36][0]=magicCubeMatrix[0][1][2];l2[36][1]=magicCubeMatrix[1][1][1];l2[36][2]=magicCubeMatrix[2][1][0];
        size_l2=37;
        sort_list(this.l2,size_l2);
        sort_ls(this.l2,size_l2);
    }

    public void making_list3(int[][] ls)
    {
        if(ls[0][0] + ls[1][2] + ls[2][1] == 42)
        {
            l3[size_l3][0]=ls[0][0];
            l3[size_l3][1]=ls[1][2];
            l3[size_l3][2]=ls[2][1];
            size_l3++;
        } 
        if(ls[0][2] + ls[1][0] + ls[2][1] == 42)
        {
            l3[size_l3][0]=ls[0][2];
            l3[size_l3][1]=ls[1][0];
            l3[size_l3][2]=ls[2][1];
            size_l3++;
        }  
        if(ls[2][2] + ls[0][1] + ls[1][0] == 42)
        {
            l3[size_l3][0]=ls[2][2];
            l3[size_l3][1]=ls[0][1];
            l3[size_l3][2]=ls[1][0];
            size_l3++;
        } 
        if(ls[2][0] + ls[1][2] + ls[0][1] == 42)
        {
            l3[size_l3][0]=ls[2][0];
            l3[size_l3][1]=ls[1][2];
            l3[size_l3][2]=ls[0][1];
            size_l3++;
        } 
    }

    public void make_list3()
    {
        making_list3(p1);
        making_list3(p2);
        making_list3(p3);
        making_list3(p4);
        making_list3(p5);
        making_list3(p6);
        making_list3(p7);
        making_list3(p8);
        making_list3(p9);
        making_list3(p10);
        making_list3(p11);
        sort_list(l3,size_l3);
        sort_ls(l3,size_l3);
    }

    public void making_list4(int[][] ls)
    {
        l4[size_l4][0] = ls[0][0];
        l4[size_l4][1] = ls[1][1];
        l4[size_l4][2] = ls[2][2];
        size_l4++;
        l4[size_l4][0] = ls[2][0];
        l4[size_l4][1] = ls[1][1];
        l4[size_l4][2] = ls[0][2];
        size_l4++;
    }

    public void make_list4()
    {
        making_list4(p1);
        making_list4(p2);
        making_list4(p3);
        making_list4(p4);
        making_list4(p5);
        making_list4(p6);
        sort_list(l4,size_l4);
        sort_ls(l4,size_l4);
    }

    // Constructor
    public magic_cube() {
        Random rand = new Random();
        int n = rand.nextInt(5) + 0;
        int[] multipliers = num[n]; 
        size_l1=0;
        size_l2=0;
        size_l3=0;
        size_l4=0;
        list = new int[85][3];
        l1 = new int[100][3];            //numbers adding to 42 on different surfaces
        l2 = new int[50][3];             //numbers adding to 42 in same row or column or diagonal             
        l3 = new int[20][3];              //numbers adding to 42 on same surface but not in line
        l4 = new int[20][3];              //numbers in line not adding to 42 on diagonals
        // s1*a + s2*b + s3*c + 1 --- (a,b,c) = (9,3,1)
        for(int i =0;i<3;i++){
            for(int j=0;j<9;j++){
                mc[i][j] = v1[i][j]* multipliers[0] + v2[i][j]*multipliers[1] + v3[i][j]*multipliers[2] + 1;   
            }
        }
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    if(j==0){
                        magicCubeMatrix[i][j][k]= mc[i][k];
                        array.add(mc[i][k]);
                    }
                    else if(j==1){
                        magicCubeMatrix[i][j][k]= mc[i][k+3];
                        array.add(mc[i][k+3]);
                    }
                    else if(j==2){
                        magicCubeMatrix[i][j][k]= mc[i][k+6];
                        array.add(mc[i][k+6]);
                    }
                }
            }
        }


        
        p1 = new int[3][3]; p2 = new int[3][3]; p3 = new int[3][3]; p4 = new int[3][3]; 
        p5 = new int[3][3]; p6 = new int[3][3]; p7 = new int[3][3]; p8 = new int[3][3]; 
        p9 = new int[3][3]; p10 = new int[3][3]; p11 = new int[3][3];
        
        
        int x=0, y=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                p1[x][y] = mc[i][j];
                y++;
            }
            y=0;
            x++;
        }
        //surface 2 -bottom
        x=0;
        y=0;
        for(int i=0;i<3;i++){
            for(int j=6;j<9;j++){
                p2[x][y] = mc[i][j];
                y++;
            }
            y=0;
            x++;
        }
        //surface 3 - right 
        x=0;
        y=0;
        for(int i=0;i<3;i++){
            for(int j=2;j<9;j=j+3){
                p3[x][y] = mc[i][j];
                y++;
            }
            y=0;
            x++;
        }
        //surface 4 - left
        x=0;
        y=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<9;j=j+3){
                p4[x][y] = mc[i][j];
                y++;
            }
            y=0;
            x++;
        }
        //surface 5 -front
        x=0;
        y=0;
        for(int j=0;j<9;j++){
            int i=0;
            if(j%3==2){
                p5[x][y] = mc[i][j];
                y=0;
                x++;               
            }
            else{
                p5[x][y] = mc[i][j];
                y++;
            }        
        }
        //surface 6 - back
        x=0;
        y=0;
        for(int j=0;j<9;j++){
            int i=2;
            if(j%3==2){
                p6[x][y] = mc[i][j];
                y=0;
                x++;                  
            }
            else{
                p6[x][y] = mc[i][j];
                y++;
            }        
        }
        //surface 7 - vertical mid
        x=0;
        y=0;
        for(int j=1;j<9;j=j+3){
            for(int i=0;i<3;i++){
                p7[x][y] = mc[i][j];
                y++;
            }
            y=0;
            x++;
        }
        //surface 8 - horizontal mid 
        x=0;
        y=0;
        for(int i=0;i<3;i++){
            for(int j=3;j<6;j++){
                p8[x][y] = mc[i][j];
                y++;
            }
            y=0;
            x++;
        }
        //surface 9 - mid layer
        x=0;
        y=0;
        for(int j=0;j<9;j++){
            int i=1;
            if(j%3==2){
                p9[x][y] = mc[i][j];
                y=0;
                x++;                
            }
            else{
                p9[x][y] = mc[i][j];
                y++;
            }        
        }
        //surface 10- diagonal 1
        x=0;
        y=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<9;j=j+4){
                p10[x][y] = mc[i][j];
                y++;
            }
            y=0;
            x++;
        }
        //surface 11 -  diagonal 2
        x=0;
        y=0;
        for(int i=0;i<3;i++){
            for(int j=2;j<7;j=j+2){
                p11[x][y] = mc[i][j];
                y++;
            }
            y=0;
            x++;
        }
        surfaces.add(p1);
        surfaces.add(p2);
        surfaces.add(p3);
        surfaces.add(p4);
        surfaces.add(p5);
        surfaces.add(p6);
        surfaces.add(p7);
        surfaces.add(p8);
        surfaces.add(p9);
        surfaces.add(p10);
        surfaces.add(p11);

        make_list();
        make_list4();
        make_list3();
        make_list2();
        make_list1();
    }
    
    //display magic cube
    public void display(){
        /*for(int i=0;i<3;i++){
            for(int j=0;j<9;j++){
                if(j%3==2){
                    System.out.println(mc[i][j] + " ");                    
                }
                else{
                    System.out.print(mc[i][j] + " ");
                }     
            }
            System.out.println();
        }*/
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    System.out.print(magicCubeMatrix[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void display2(Vector<Tuple> t1,Vector<Tuple> t2)
    {
        int fill=0;
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                for (int k=0;k<3;k++)
                {
                    fill=0;                    
                    Tuple e = new Tuple(k,i,j);
                    for (int q=0;q<t1.size();q++)
                    {
                        if (t1.get(q).equals(e))
                        {
                            fill=1;
                        }
                    }
                    for (int q=0;q<t2.size();q++)
                    {
                        if (t2.get(q).equals(e))
                        {
                            fill=2;
                        }
                    }
                        if (fill==0)
                        {
                            System.out.print(" "+" | ");
                        }
                        else if (fill ==1)
                        {
                            System.out.print("*"+ " | ");
                        }
                        else 
                        {
                            System.out.print("O"+" | ");
                        }
                }
                System.out.println();
                
               
            }
            System.out.println();
        }
    }
    
    //not efficient
    private boolean num_in_plane(int a, int[][] p){
        for(int i=0;i<p.length;i++){
            for(int j=0;j<p[0].length;j++){
                if(p[i][j]==a) return true;
            }
        }
        return false;
    }
    
    //not efficient because of num_in_plane used - returns index of 'surfaces'
    public int findplane(int a, int b){
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();
        for(int i=0;i<surfaces.size();i++){
            if(num_in_plane(a, (int[][]) surfaces.get(i))){
                list1.add(i);
            }
            if(num_in_plane(b, (int[][]) surfaces.get(i))){
                list2.add(i);
            }
        }
        //find common index
        Collections.sort(list1);
        Collections.sort(list2);        
        int x=0, y=0;
        while(x<list1.size() && y<list2.size()){
            if(list1.get(x)==list2.get(y)){
                //System.out.println("FOUND COMMON PLANE: "+ list1.get(x) +" "+list2.get(y)+"  "+x+"  "+y);
                //display_plane((int[][]) surfaces.get(list1.get(x)));
                return list1.get(x);
            }
            else if(list1.get(x)<list2.get(y)){
                x++;
            }
            else if(list1.get(x)>list2.get(y)){
                y++;
            }
        }
        System.out.println("On different surfaces");
        return -1;        
    }
    
    private void display_plane(int[][] p){
        for(int i=0;i<p.length;i++){
            for(int j=0;j<p[0].length;j++){
                System.out.print(p[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    
     
}
