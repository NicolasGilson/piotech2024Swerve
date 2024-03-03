package frc.robot.subsystems.swervedrive;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class arm 
{
    private static  CANSparkMax armMotorA;
    private static  CANSparkMax armMotorB;
    private static DutyCycleEncoder armEncoder;

    /**
     * @param motorAID the CAN bus ID
     * @param motorBChanel the CAN bus ID
     * @param encoderPort the DIO Port number
     */
    public arm(int motorAID, int motorBID, int encoderPort)
    {
        armMotorA = new CANSparkMax(motorAID, MotorType.kBrushless);
        armMotorB = new CANSparkMax(motorBID,MotorType.kBrushless);
        armEncoder = new DutyCycleEncoder(encoderPort);
        armEncoder.setDistancePerRotation(4.0);
    }
    //TODO add corect theta values. they are the bore encoder values not angles
    /**
     * set this to button 4 in robotContainer file in the configureBindings method replace if it alredy exist for that button
     */
    public static void presetT() //Intake
    {
        angle(0.2, 1, 0.05);
    }
    /**
     * set this to button 3 in robotContainer file in the configureBindings method replace if it alredy exist for that button
     */
    public static void presetO() //Shooter
    {
        angle(0.1, 0.916, 0.02);
    }
    /**
     * set this to button 2 in robotContainer file in the configureBindings method replace if it alredy exist for that button
     */
    
    public static void presetX() //1 pointer
    {
        angle(0.1, 0.0702, 0.02);
    }
    /**
     * set this to button 1 in robotContainer file in the configureBindings method replace if it alredy exist for that button
     */
    public static void presetS() //
    {
        angle(0.1, 0, 0.2);
    }
    /**
     * 
     * @param speed Speed the motors turn
     * @param theta the bore encoder value at desired angle
     */
    public static void angle(double speed, double theta, double errorbound)
    {
        if (theta - errorbound > armEncoder.getDistance()) {
            armMotorA.set(speed * -1);
            armMotorB.set(speed);
            System.out.println("down");
        } 
        
        if (theta + errorbound < armEncoder.getDistance()) {
            armMotorA.set(speed);
            armMotorB.set(speed * -1);
            System.out.println("up");
        } 
        if(!(theta - errorbound > armEncoder.getDistance()|| theta + errorbound < armEncoder.getDistance()))
        {
            armMotorA.set(0);
            armMotorB.set(0);
        }

        //System.out.println("encoder - errorbound" + (armEncoder.getDistance()));
    }

    public static void manuel(boolean up, boolean down, boolean preSetT, boolean preSetO, boolean preSetX, boolean preSetS){
        if(up){
            armMotorA.set(0.2);
            armMotorB.set(-0.2);
        } else if (down){
            armMotorA.set(-0.2);
            armMotorB.set(0.2);
        } else if (preSetT){
            presetT();
        } else if (preSetO){
            presetO();
        } else if (preSetX){
            presetS();
        } else if (preSetS){
            presetX();
        } else {
            armMotorA.set(0);
            armMotorB.set(0);
        }
    }

    public String encoderVal()
    {
        return ""+armEncoder.getDistance();
    }
}