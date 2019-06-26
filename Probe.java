public class Probe {
    public static void calculate() {
        double g = 9.81;
        double R = 12756 / 2;
        //vE is the orbital velocity of Earth
        double vE=29.8;
        //transfer transfers km to au
        double transfer = 6.6845871226706 * Math.pow(10, -9);
        //d1 is the distance between Saturn and Titan in km
        double d1 = 1016860.465;
        //d2 is the distance between Saturn and Sun in km
        double d2 = 1504376476;
        //d3 is the distance between Earth and Sun in km
        double d3 = 149010862.2;
        //rP is the distance between Earth and Sun
        double rP = d3;
        //rA is the distance between sun and aphelion
        double rA = d2 -d1;
        //a is the semi major axis
        double a = (rP + rA) / 2;
        //e is the eccentricity of probe orbit
        double e = 1 - (rP / a);
        //pP is the period of the probe orbit
        double pP = Math.pow(a * transfer, 1.5);
        double time = pP / 2;
        //pS is the period of Saturn orbit
        double pS = Math.pow(d2 * transfer, 1.5);
        //pT is the period of Titan orbit
        double pT = Math.pow(d1 * transfer, 1.5);
        //aS is the angle that Saturn has changed since probe was launched
        double aS = time / pS * 360;
        //aT is the angle that Titan has changed since probe was launched
        double aT = (time - Math.floor(time / pT) * pT) / pT * 360;
        //vC is the average velocity of probe orbit
        double vC = 2 * Math.PI * a * transfer / pP;
        //vP is the velocity at perihelion
        double vP = vC * Math.pow(((1 + e )/( 1 - e)), 0.5);
        //v is the vP transferred to km/s
        double v = vP / transfer / (3600 * 24 * 365);
        //v1 is the velocity after escaping Earth
        double v1=v-vE;
        //v2 is the initial velocity
        double v2 = Math.pow(2 * 0.5 * Math.pow(v1, 2) + g * R * Math.pow(10, -3), 0.5);


        System.out.println("The velocity after escaping Earth(km/s): "+v1);
        System.out.println("The time from earth to Titan(years): "+time);
        System.out.println("The angle that Saturn changed: "+aS);
        System.out.println("The angle that Titan changed: "+aT);
        System.out.println("The initial launching velocity(km/s): "+v2);


        double c=d2*Math.sin(Math.toRadians(90-aS));
        double b=d2*Math.cos(Math.toRadians(90-aS));
        System.out.println("The possible positions of Saturn and Earth when launching the probe: ");
        System.out.println("Saturn: ("+b+","+c+")  Earth:(0,"+(-d3)+")");
        System.out.println("("+c+","+(-b)+")  Earth:("+(-d3)+",0)");
        System.out.println("("+(-b)+","+(-c)+")  Earth:(0,"+(d3)+")");
        System.out.println("("+(-c)+","+b+")  Earth:("+(d3)+",0)");
    }
    public static void main(String args[]) {
        calculate();
    }

}
