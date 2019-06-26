package sample;

public class SolarSystemMain {

    private static final double GEOSTATIONARY_HEIGHT = 42164000;//72997653.14;

    public static void main(String[] args) {
        double success = 0;
        double failure = 0;
        long iteration = 0;
        Lander lander = new EarthLander(110000, 0, GEOSTATIONARY_HEIGHT, 0, 0);
        for (int j = 0; j < 10000000; j++) {
            for (int k = 0; k < 10000; k++) {

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
                    if (lander.getPosY() <= 0) {

                        iteration++;
                        if (getResult(newPosition, previousPosition) == 1) {
                            success++;
                        } else {
                            failure++;
                        }
                        if (iteration % 100 == 0) {
                            System.out.println(iteration + " " + (success / (success + failure) * 100));
                        }

                        //lander.addLanding(getResult(newPosition, previousPosition));
                        break;
                    } else if(k >= 9999){
                        iteration++;
                        failure++;
                        if (iteration % 100 == 0) {
                            System.out.println(iteration + " " + (success / (success + failure) * 100));
                        }
                        break;
                    }
                }
            }
            lander = new EarthLander(110000, 0, GEOSTATIONARY_HEIGHT, 0, 0);
        }
    }

    private static int getResult(double[] position, double[] oldPosition) {
        if (Math.abs(position[0]) <= 100 && Math.abs(oldPosition[1] - position[1]) < 3) {
            return 1;
        } else {
            return 0;
        }
    }
}
