public class Probe {
	protected double mass;
	protected double x;
	protected double y;
	protected double vx;
	protected double vy;
	protected double bigG = 6.674E-11;

	Probe(double x, double y, double vx, double vy){
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	public void update(double dt) {
		double dx = calculateX(dt);
		double dy = calculateY(dt);
		this.x += dx;
		this.y += dy;
		System.out.println("X pos = " + this.x + "   Y pos = " + this.y);
	}

	private double calculateX(double dt) {
		calculateVx(dt);
		return (this.vx*dt);
	}

	private double calculateY(double dt) {
		calculateVy(dt);
		return (this.vy*dt);
	}

	private void calculateVx(double dt) {
		double accX = calculateAx();
		//System.out.println(/*"X acc = " + */accX);
		this.vx += (accX*dt);
	}

	private void calculateVy(double dt) {
		double accY = calculateAy();
		//System.out.print("	Y acc = " + accY + "\n");
		this.vy += (accY*dt);
	}

	private double calculateAx() {
		//return bigG * ((Masses.getmSun())/Math.pow(this.x, 2));
		//System.out.println((bigG * Masses.getmSun()*(-this.x))/(Math.pow(Math.pow(this.x, 2) + Math.pow(this.y, 2),(3/2))));
		//return (bigG * this.parent.getMass()*(this.parent.getX()-this.x)) / Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) + ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);

		return (bigG * Masses.getmSun()*(-this.x)) / Math.pow((this.x*this.x + this.y*this.y),1.5);
	}

	private double calculateAy() {
		//return bigG * ((Masses.getmSun())/Math.pow(this.y, 2));
		//System.out.println(Math.pow((this.x*this.x + this.y*this.y),1.5));
		//return (bigG * this.parent.getMass()*(this.parent.getY()-this.y)) / Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) + ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);

		return (bigG * Masses.getmSun()*(-this.y)) / Math.pow((this.x*this.x + this.y*this.y),1.5);
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

}
