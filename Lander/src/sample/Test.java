package sample;

public class Test {

    private static final double GEOSTATIONARY_HEIGHT = 72997653.14;

    public static void main(String[] args){
        long iteration = 0;
        Lander lander = new Lander(4771, 0,GEOSTATIONARY_HEIGHT, 0,0);
        while(true) {
            if (lander.getPosY() > 0) {
                double newPosition[] = new double[2];
                for (int i = 0; i < 10; i++) {
                    newPosition = lander.update(1);
                    lander.setPosX(newPosition[0]);
                    lander.setPosY(newPosition[1]);
                }
                if (lander.getPosY() < 0) {
                    System.out.println(iteration++);
                    lander = new Lander(4771, 0, GEOSTATIONARY_HEIGHT, 0, 0);
                }
            }
        }
    }
}
