package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

//@TeleOp(name="Limelight TeleOp", group="Testing")
public class limelight extends OpMode {

    private Limelight3A limelight;
    private IMU imu;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);

        // הגדרת ה-IMU
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start() {
        limelight.start();
    }

    @Override
    public void loop() {
        // קבלת זווית ה-Yaw מה-IMU ועדכון הלימלייט (חשוב ל-MT2)
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw(AngleUnit.DEGREES));

        LLResult llResult = limelight.getLatestResult();

        if (llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2(); // שימוש ב-MegaTag 2

            telemetry.addData("Target", "Detected");
            telemetry.addData("TX", llResult.getTx());
            telemetry.addData("TY", llResult.getTy());
            telemetry.addData("Botpose", botpose.toString());
        } else {
            telemetry.addData("Target", "Not Detected");
        }

        // עדכון הטלמטריה בכל מצב, גם אם אין מטרה
        telemetry.update();
    }
}