package comp3_code_folder.org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

//@TeleOp(group = "IMU Test")
public class IMUTest extends LinearOpMode {
    private IMU imu;   // או BNO055IMU, תלוי באיזה אינטראקציה אתם משתמשים

    @Override
    public void runOpMode() {
        // אם משתמשים ב־Universal IMU (מודרני)
        imu = hardwareMap.get(IMU.class, "imu");   // שם הקומפוננט בקונפיג

        // אתחול (אם נדרש)
        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);

        telemetry.addData(">", "IMU initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            double yaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            telemetry.addLine("=== IMU  TEST ===");
            telemetry.addData("IMU Yaw [rad]", "%.3f", yaw);
            telemetry.addData("IMU Yaw [deg]", "%.1f", Math.toDegrees(yaw));
            telemetry.update();
            sleep(50);
        }
    }
}
