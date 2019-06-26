package sample;


public class CostModel {

    /* Assumptions made for the exhaust velocity:
        1. Assume chamber pressure of 50 atmoshperes
        2. Exhaust pressure of 1 atmosphere
     */

    private static final double EXHAUST_VELOCITY = 2.749;
    //Newton meters
    //Energy per kilogram of kerosene
    private static final double energy_per_kilo =  42.80 * 10E6;
    //Cost of fuel in Saudi Arabia
    private static final double COST_PER_KILO = 0.5;
    private static final double SPACESHIP_COST = 1560000000;

    private static final double GEOSTATIONARY_HEIGHT_TITAN = 72997653.14;
    private static final double GEOSTATIONARY_HEIGHT_EARTH = 42164000;
    EarthLander earth = new EarthLander(110000, 0, GEOSTATIONARY_HEIGHT_EARTH, 0, 0);
    TitanLander titan = new TitanLander(4771, 0,GEOSTATIONARY_HEIGHT_TITAN, 0,0);



   public double calculate_Landing_Cost(){
       while(earth.getPosY() > 0) {
           double newPosition[] = new double[2];
           newPosition = earth.update(1);
           earth.setPosX(newPosition[0]);
           earth.setPosY(newPosition[1]);
       }
       while(titan.getPosY() > 0) {
           double newPosition[] = new double[2];
           newPosition = titan.update(1);
           titan.setPosX(newPosition[0]);
           titan.setPosY(newPosition[1]);
       }
        double average_thrust_Earth = calculateAverageThrust(earth.getSum_Thrust_Earth(), TimeNeeded(1, earth.getCounter()));
        double average_thrust_Titan = calculateAverageThrust(titan.getSum_Thrust_Titan(), TimeNeeded(1, titan.getCounter()));

        double mass_flow_earth = mass_flow_rate(average_thrust_Earth);
        double mass_flow_titan = mass_flow_rate(average_thrust_Titan);

        double totalMassFuelEarth = mass_flow_earth * TimeNeeded(1, earth.getCounter());
        double totalMassFuelTitan = mass_flow_titan * TimeNeeded(1, titan.getCounter());

       return totalMassFuelEarth * COST_PER_KILO + totalMassFuelTitan * COST_PER_KILO;
    }



    /*
    EarthLander lander;
    SolarSystemMain l;




    //Injection velocity
    double injection_velocity = EXHAUST_VELOCITY * Math.log(lander.getTotalMass()/lander.getDryMass());

    //Compute number of seconds of burn for launch

    double resulting_launch_velocity = l.getInitial_vx() + l.getInitial_vy();
    double thrust =

    double launch_thrust =

    int number_of_seconds_for_burning  = (int) ((lander.getTotalMass() * EXHAUST_VELOCITY)/ );
    */




    //Calculate fuel used for landing on Earth

    public double calculateAverageThrust(double sum, double times){
            return sum/times;
    }

    public double TimeNeeded(double initialDt, double finalDt){
        return finalDt - initialDt;
    }

    public double mass_flow_rate(double thrust){
        return (thrust/EXHAUST_VELOCITY);
    }

    public double getSpaceshipCost(){
        return SPACESHIP_COST;
    }

}
