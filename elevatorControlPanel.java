import java.util.Random;

public class elevatorControlPanel {

    public static void main(String[] args) throws InterruptedException {

        Elevator elevator = new Elevator(0,1.5,0.9,0);

        Random random = new Random();

        while(true){

            double target = random.nextDouble()*1.5;

            elevator.setTargetHeight(target);

            while(!elevator.isAtTargetHeight()){

                elevator.periodicUpdate();

                System.out.println("Target: " + target);
                System.out.println("Height: " + elevator.getPosition());

                Thread.sleep(100);
            }

            Thread.sleep(2000);
        }
    }
}