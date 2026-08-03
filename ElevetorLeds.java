public class ElevetorLeds {
    private String ledColor;
    private boolean isblinking;
    private int loopCount;
    private boolean isledOn;

    public ElevetorLeds() { // Constructor
        this.ledColor = "off";
        this.isblinking = false;
        this.loopCount = 0;
        this.isledOn = false;
    }
    public void setLedColor(String color) {
        this.ledColor = color;
        this.isledOn = true;
        this.isblinking = false;
        this.loopCount = 0;
    }
    public String getLedColor() {
        return this.ledColor;
    }
    public void setBlinkingcolor(String color) {
        this.isblinking = true;
        this.isledOn = true;
        this.loopCount = 0;
    }
    public void updateBlinking() {
        if (this.isblinking) {
            this.loopCount++;
            if (this.loopCount <=3) {
                this.isledOn = !this.isledOn;
                loopCount=0;
            }
        }
        else {
            this.isledOn = true;
        }
        displayled();
    }
    public void displayled() {
        if (!this.isledOn) {
            System.out.println("LED Color: " + this.ledColor + " is OFF(Blinking)");
        } else {
            System.out.println("LED Color is: " + this.ledColor);
        }
    }
}