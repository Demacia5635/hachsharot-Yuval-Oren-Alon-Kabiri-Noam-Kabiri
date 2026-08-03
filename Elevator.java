public class Elevator {
    double minRange;
    double maxRange;
    double maxSpeed;
    double power;
    boolean isup;
    boolean isdown;
    
    public Elevator(double minRange, double maxRange, double maxSpeed) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.maxSpeed = maxSpeed;
    }
    public double getMinRange() {
        return minRange;
    }

    public double getMaxRange() {
        return maxRange;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
    
    public double getPower() {
        return power;
    }
    public boolean isUp() {
        return isup;
    }
    public boolean isDown() {
        return isdown;
    }
}   