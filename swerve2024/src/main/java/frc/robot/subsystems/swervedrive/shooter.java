package frc.robot.subsystems.swervedrive;

import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class shooter 
{
    private static CANSparkMax shooterMotorA = new CANSparkMax(12, MotorType.kBrushless);
    private static WPI_VictorSPX shooterMotorB = new WPI_VictorSPX(13);
    /**
     * 
     * @param shooterAID the CAN bus ID
     * @param shooterBChannel device ID of motor controller
     */
    public shooter(int shooterAID,int shooterBChannel)
    {
        shooterMotorA = new CANSparkMax(shooterAID, MotorType.kBrushless);
        shooterMotorB = new WPI_VictorSPX(shooterBChannel);
    }
    /**
     * in robot file set this to the disred button like this OBJname.griper(IWKMS.getR2Button());
     * do this in the teleopPeriodic
     * @param balls the boolean value of the button
     */
    //TODO the speeds might be diffrent so set the speeds acordingly
    public static void shoot(boolean balls)
    {
        if(balls)
        {
            double speed =0.8;
            shooterMotorA.set(speed);
            shooterMotorB.set(speed + 0.2);
        }
        else 
        {
            shooterMotorA.set(0);
            shooterMotorB.set(0);
        }
    }

    public static void shoot(){
        double speed =0.8;
        shooterMotorA.set(speed);
        shooterMotorB.set(speed + 0.2);

    }
}
