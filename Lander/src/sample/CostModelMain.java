package sample;

public class CostModelMain {
    static CostModel model;
    public static void main(String[] args){
        model = new CostModel();
        double final_cost = model.getSpaceshipCost() + model.calculate_Landing_Cost();
        System.out.println(final_cost);
    }
}
