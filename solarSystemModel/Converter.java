/**
 * Convertor class that converts from -- to --.
 */
public class Converter {
 /*   public static void main(String[] args){
        System.out.println(convert(-3.905962989274014E-01));
        System.out.println(convert(-1.433029529978211E-01));
        System.out.println(convert(3.865818553391108E-03));
        System.out.println(convert(-2.519674903359139E-02));
    }
*/
    private static double convert(double x){
        long devider = 149597870700L;
        return x*devider;
    }
}