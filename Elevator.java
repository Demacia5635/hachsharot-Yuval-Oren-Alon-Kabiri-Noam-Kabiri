public class Elevator {
    private double minRange;
    private double maxRange;
    private double maxSpeed;
    private double power;
    private boolean isup;
    private boolean isdown;
    private double speed;
    private double position;
    
    // New variables for target height management
    private double targetHeight;
    private boolean movingToTarget;
    
    // LEDs object
    private ElevetorLeds leds;

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
        
        // Initialize target variables
        this.targetHeight = initialPosition; 
        this.movingToTarget = false;
        
        this.leds = new ElevetorLeds(); // Initialize LEDs
    }

    public double getMinRange() { return minRange; }
    public double getMaxRange() { return maxRange; }
    public double getMaxSpeed() { return maxSpeed; }
    public double getPower() { return power; }
    public boolean isUp() { return isup; }
    public boolean isDown() { return isdown; }
    public double getPosition() { return position; }
    public ElevetorLeds getLeds() { return leds; }

    // New function: Set the target height for the elevator
    public void setTargetHeight(double target) {
        if (target < minRange) target = minRange;
        if (target > maxRange) target = maxRange;
        
        this.targetHeight = target;
        this.movingToTarget = true;
    }

    // New function: Check if the elevator is at the target height
    public boolean isAtTargetHeight() {
        // Check if not moving and the difference is very small (to overcome floating-point inaccuracies)
        return !movingToTarget && Math.abs(this.position - this.targetHeight) <= 0.1;
    }

    // Function to move the elevator up with a given power (between 0 and 1)
    public void moveUp(double power) {
        this.movingToTarget = false; // Disable automatic mode in case of manual control
        if (power < 0) power = 0;
        if (power > 1) power = 1;
        this.power = power;
        this.isup = true;
        this.isdown = false;
    }

    // Function to move the elevator down with a given power (between 0 and 1)
    public void moveDown(double power) {
        this.movingToTarget = false; // Disable automatic mode in case of manual control
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
        this.movingToTarget = false;
    }

    // Periodic update function to calculate position and handle limits
    public void periodicUpdate() {
        
        // 1. Manage automatic movement to target height
        if (movingToTarget) {
            double distance = targetHeight - position;
            
            if (Math.abs(distance) <= 0.1) { 
                // Reached the target
                this.position = targetHeight;
                stop();
            } else {
                // Proportional power based on distance: the closer we are, the slower it gets (max power 1.0)
                double calculatedPower = Math.abs(distance) * 0.1; 
                if (calculatedPower > 1.0) calculatedPower = 1.0;
                if (calculatedPower < 0.1) calculatedPower = 0.1; // Minimum power to prevent the elevator from stopping too early

                if (distance > 0) {
                    this.power = calculatedPower;
                    this.isup = true;
                    this.isdown = false;
                } else {
                    this.power = calculatedPower;
                    this.isup = false;
                    this.isdown = true;
                }
            }
        }

        // 2. Update speed and position based on direction
        if (isup) {
            this.speed = maxSpeed * power;
            this.position += this.speed;
        } else if (isdown) {
            this.speed = maxSpeed * power;
            this.position -= this.speed;
        } else {
            this.speed = 0;
        }

        // 3. Check upper and lower boundaries
        if (this.position >= maxRange) {
            this.position = maxRange;
            stop();
        } else if (this.position <= minRange) {
            this.position = minRange;
            stop();
        }

        // 4. Update LEDs state according to current status
        updateLedsState();

        // 5. Periodic run of LEDs (for blinking)
        leds.updateBlinking();
    }

    // Helper function to manage LED color and blinking
    private void updateLedsState() {
        String desiredColor = "off";
        boolean shouldBlink = false;

        if (this.position <= minRange) {
            desiredColor = "Green";     // Bottom position: Green
        } else if (this.position >= maxRange) {
            desiredColor = "Red";       // Top position: Red
        } else if (isAtTargetHeight()) {
            desiredColor = "Yellow";    // At target height: Yellow
        } else if (isup) {
            desiredColor = "Red";       // Moving up: Blinking Red
            shouldBlink = true;
        } else if (isdown) {
            desiredColor = "Green";     // Moving down: Blinking Green
            shouldBlink = true;
        }

        // Change LED state only if color or blinking state changes to avoid resetting the timer
        if (!desiredColor.equals(leds.getLedColor()) || shouldBlink != leds.isBlinking()) {
            if (shouldBlink) {
                leds.setBlinkingcolor(desiredColor);
            } else {
                leds.setLedColor(desiredColor);
            }
        }
    }
}