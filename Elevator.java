public class Elevator {
    private double minRange;
    private double maxRange;
    private double maxSpeed;
    private double power;
    private boolean isup;
    private boolean isdown;
    private double speed;
    private double position;

    // Constructor to initialize the elevator properties
    public Elevator(double minRange, double maxRange, double maxSpeed, double initialPosition) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.maxSpeed = maxSpeed;
        this.position = initialPosition;
        this.power = 0;
        this.speed = 0;
        this.isup = false;
        this.isdown = false;
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

    // Function to move the elevator up with a given power (between 0 and 1)
    public void moveUp(double power) {
        if (power < 0) power = 0;
        if (power > 1) power = 1;
        this.power = power;
        this.isup = true;
        this.isdown = false;
    }

    // Function to move the elevator down with a given power (between 0 and 1)
    public void moveDown(double power) {
        if (power < 0) power = 0;
        if (power > 1) power = 1;
        this.power = power;
        this.isup = false;
        this.isdown = true;
    }

    // Function to stop the elevator
    public void stop() {
        this.power = 0;
        this.speed = 0;
        this.isup = false;
        this.isdown = false;
    }

    // Periodic update function to calculate position and handle limits
    public void periodicUpdate() {
        // Calculate speed and update position based on direction
        if (isup) {
            this.speed = maxSpeed * power;
            this.position += this.speed;
        } else if (isdown) {
            this.speed = maxSpeed * power;
            this.position -= this.speed;
        } else {
            this.speed = 0;
        }

        // Check upper and lower boundaries
        if (this.position >= maxRange) {
            this.position = maxRange;
            stop();
        } else if (this.position <= minRange) {
            this.position = minRange;
            stop();
        }
    }
    
}