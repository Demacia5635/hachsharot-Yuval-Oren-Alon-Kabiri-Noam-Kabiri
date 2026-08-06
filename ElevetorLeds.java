public class ElevetorLeds {
    private String ledColor;     // Color of the LED 
    private int loopCount;       // Count the number of loops for blinking
    private boolean isledOn;     // Check if the LED is on or off
    private boolean isblinking;  // Check if the LED is blinking or not

    public ElevetorLeds() {      // Constructor
        this.ledColor = "off";
        this.isblinking = false;
        this.loopCount = 0;
        this.isledOn = false;
    }
    
    public void setLedColor(String color) { // Set the color of the LED statically
        this.ledColor = color;
        this.isledOn = true;
        this.isblinking = false;
        this.loopCount = 0;
    }
    
    public String getLedColor() { // Get the color of the LED
        return this.ledColor;
    }

    // New function allowing the elevator to know if the LED is currently blinking
    public boolean isBlinking() {
        return this.isblinking;
    }
    
    public void setBlinkingcolor(String color) { // Set the color of the LED and make it blink
        this.ledColor = color;
        this.isblinking = true;
        this.isledOn = true;
        this.loopCount = 0;
    }
    
    public void updateBlinking() { // Update the blinking state of the LED
        if (this.isblinking) {
            this.loopCount++;
            if (this.loopCount <= 3) {
                this.isledOn = !this.isledOn;
                this.loopCount = 0;
            }
        } else {
            this.isledOn = true;
        }
        displayled();
    }
    
    public void displayled() { // Display the current state of the LED
        if (!this.isledOn) {
            System.out.println("LED Color: " + this.ledColor + " is OFF(Blinking)");
        } else {
            System.out.println("LED Color is: " + this.ledColor);
        }
    }
}