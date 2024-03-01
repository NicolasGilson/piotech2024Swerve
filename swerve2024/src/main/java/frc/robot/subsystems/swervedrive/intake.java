package frc.robot.subsystems.swervedrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class intake 
{
    private final CANSparkMax intakeMotor;

    /**
     * @param intakeMotorID the CAN bus ID
     */
    public intake(int intakeMotorID)
    {
       intakeMotor = new CANSparkMax(intakeMotorID, MotorType.kBrushless);
    }
    /**
     * in robot file set this to the disred button like this OBJname.griper(IWKMS.getR2Button());
     * do this in the teleopPeriodic
     * @param gripers the boolean value of the button
     */
    public void griper(boolean froward, boolean backward)
    {
        if(froward)
        {
            double speed =0.3;
            intakeMotor.set(speed);
        } else if (backward)
        {
            intakeMotor.set(-0.3);
        } else {
            intakeMotor.set(0);
        }
    }
}
