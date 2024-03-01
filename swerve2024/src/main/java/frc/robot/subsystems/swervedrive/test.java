/*package frc.robot.subsystems.swervedrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class test 
{
    private final CANSparkMax armMotorA;
    private final CANSparkMax armMotorB;
    //private final CANSparkMax intakeMotor;
    //private final CANSparkMax shooterMotorB;
    //private final CANSparkMax shooterMotorA;
    private DutyCycleEncoder armEncoder;

    public test(int motorAID,int motorBID)
    {
        armMotorA = new CANSparkMax(motorAID, MotorType.kBrushless);
        armMotorB = new CANSparkMax(motorBID, MotorType.kBrushless);
        //shooterMotorA = null;
        //shooterMotorB = null;
        //intakeMotor = null;
        armEncoder = null;
        armEncoder.reset();

    }
    /*public test(int motorAID,int motorBID,int intakeMotorID,int shooterAID,int shooterBID)
    {
        armMotorA = new CANSparkMax(motorAID, MotorType.kBrushless);
        armMotorB = new CANSparkMax(motorBID, MotorType.kBrushless);
        intakeMotor = new CANSparkMax(intakeMotorID, MotorType.kBrushless);
        shooterMotorB = new CANSparkMax(shooterBID, MotorType.kBrushless);
        shooterMotorA = new CANSparkMax(shooterAID, MotorType.kBrushless);
        armEncoder = null;
        armEncoder.reset();
    }
    public test(int encodrChannel)
    {
        armEncoder = new DutyCycleEncoder(encodrChannel);
        armEncoder.setDistancePerRotation(4.0);
        armEncoder.reset(); 
        //shooterMotorA = null;
        //shooterMotorB = null;
        //intakeMotor = null;
        armMotorA = null;
        armMotorB = null;
    }

    //the speed is from -1 - 1
    //postive is down
    public String encoderVal()
    {
        return ""+armEncoder.getDistance();
    }
    /*public void intake()
    {
        intakeMotor.set(0.1);
    }
    public void shoot()
    {
        shooterMotorA.set(0.1);
        shooterMotorB.set(0.1);
    }
    public void testa()
    {
        double speed =0.1;
        armMotorA.set(speed);
        armMotorB.set(-0.1);
    }
    public void testd()
    {
        double speed =-0.1;
        armMotorA.set(speed);
        armMotorB.set(0.1);
    }
    public void stop()
    {
        armMotorA.set(0);
        armMotorB.set(0);
    }
    /*public void testb(boolean balls)
    {
        if(balls)
        {
            double speed =0.01;
            armMotorA.set(speed);
            armMotorB.set(speed*-1);
        }
        else 
        {
            armMotorA.set(0);
            armMotorB.set(0);
        }
    }
    public void testc(boolean balls)
    {
        if(balls)
        {
            double speed =-0.01;
            armMotorA.set(speed);
            //armMotorB.set(speed);
        }
        else 
        {
            armMotorA.set(0);
            //armMotorB.set(0);
        }
    }
}
    /** Square button. 
    kSquare(1),
    /** X button. 
    kCross(2),
    /** Circle button. 
    kCircle(3),
    /** Triangle button. 
    kTriangle(4),
    /** Left Trigger 1 button. 
    kL1(5),
    /** Right Trigger 1 button. 
    kR1(6),
    /** Left Trigger 2 button. 
    kL2(7),
    /** Right Trigger 2 button. 
    kR2(8),
    /** Share button. 
    kShare(9),
    /** Option button. 
    kOptions(10),
    /** Left stick button. 
    kL3(11),
    /** Right stick button. 
    kR3(12),
    /** PlayStation button. 
    kPS(13),
    /** Touchpad click button. 
    kTouchpad(14);*/