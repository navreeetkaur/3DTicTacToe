import java.util.*;
public class Tuple<X,Y,Z> {
    public final X x;
    public final Y y;
    public final Z z;

    public Tuple(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }    
    
    public void print(){
        System.out.println(x+", "+y+", "+z);
    }

    public boolean equals(Tuple e){
    	return (x==e.x && y==e.y && z==e.z);
    }
}
