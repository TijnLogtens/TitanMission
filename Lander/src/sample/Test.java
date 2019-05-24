package sample;

public class Test {

    private static final double GEOSTATIONARY_HEIGHT = 72997653.14;

    public static void main(String[] args){
        double success = 0;
        double failure = 0;
        long iteration = 0;
        Lander lander = new Lander(4771, 0,GEOSTATIONARY_HEIGHT, 0,0);
        System.out.println("Success Rate: " + lander.successRate());
        while(true) {
            if (lander.getPosY() > 0) {
                double[] newPosition = new double[2];
                double[] previousPosition = new double[2];
                for (int i = 0; i < 10; i++) {
                    newPosition = lander.update(1);
                    previousPosition[0] = lander.getPosX();
                    previousPosition[1] = lander.getPosY();
                    lander.setPosX(newPosition[0]);
                    lander.setPosY(newPosition[1]);
                }
                /*
                if (lander.getPosY() < 0) {
                    iteration++;
                    if(getResult(newPosition,previousPosition) == 1){
                        success++;
                    } else {
                        failure++;
                    }
                    if(iteration % 1000 == 0) {
                        System.out.println(iteration + " " + (success / (success + failure) * 100));
                    }

                    lander = new Lander(4771, 0, GEOSTATIONARY_HEIGHT, 0, 0);
                }
                */
            }
        }
    }

    private static double getResult(double[] position, double[] oldPosition){
        if(Math.abs(position[0]) <= 100 && position[1] - oldPosition[1] < 3){
            return 1;
        } else {
            return 0;
        }
    }
}