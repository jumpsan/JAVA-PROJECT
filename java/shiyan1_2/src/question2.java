public class question2 {
//    for x = 0 to 100
//            for y = 0 to 100
//            for z = 0 to 100
//            if  (x+y+z=100)  and  (5*x + 3*y + z/3 = 100)  then
//           System.out.println("  "+x+"  "+y+"  "+z)
//    end if
    public static void main(String[] args) {
        for(int x=0;x<=100;x++){
            for(int y=0;y<=100;y++){
                if((100-y-x)/3+5*x+3*y==100){
                    System.out.println("  "+x+"  "+y+"  ");
                }
            }
        }
    }
}
