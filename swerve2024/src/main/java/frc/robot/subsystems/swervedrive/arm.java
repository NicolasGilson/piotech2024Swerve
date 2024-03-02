package frc.robot.subsystems.swervedrive;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class arm 
{
    private final CANSparkMax armMotorA;
    private final CANSparkMax armMotorB;
    private DutyCycleEncoder armEncoder;

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
    public void presetT()
    {
        angle(0.2, 1, 0.05);
    }
    /**
     * set this to button 3 in robotContainer file in the configureBindings method replace if it alredy exist for that button
     */
    public void presetO()
    {
        angle(0.1, 0.916, 0.02);
    }
    /**
     * set this to button 2 in robotContainer file in the configureBindings method replace if it alredy exist for that button
     */
    
    public void presetX()
    {
        angle(0.1, 0.916, 0.5);
    }
    /**
     * set this to button 1 in robotContainer file in the configureBindings method replace if it alredy exist for that button
     */
    public void presetS()
    {
        angle(0.1, 0, 0.2);
    }
    /**
     * 
     * @param speed Speed the motors turn
     * @param theta the bore encoder value at desired angle
     */
    public void angle(double speed, double theta, double errorbound)
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

    public void manuel(boolean up, boolean down, boolean preSetT, boolean preSetO, boolean preSetX, boolean preSetS){
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