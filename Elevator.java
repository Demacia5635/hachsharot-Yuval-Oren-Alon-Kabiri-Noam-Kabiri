public class Elevator {
    double minRange;
    double maxRange;
    double maxSpeed;
    double power;
    boolean isup;
    boolean isdown;
    double speed;
    double position;

    public Elevator(double minRange, double maxRange, double maxSpeed) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.maxSpeed = maxSpeed;
        this.position = position;
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

    public double getPosition() {
        return position;
    }

    public void periodicUpdate(double speed, double minRange, double maxRange, double position) {
        this.speed = maxSpeed*power;
        
       if (position>maxRange) {
            position = maxRange;
            speed = 0;
            power = 0;
        }
    


       else if (position<minRange) {
            position = minRange;
            speed = 0;
            power = 0;
        }


       else {
            position += speed;
        }
        
      } 
}
