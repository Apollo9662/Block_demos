package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//@TeleOp(name = "Servo_Hold_Y", group = "test")
public class ServoHoldY extends LinearOpMode {

    private Servo s_block;

    private final double close_pos = 0.05; // מצב סגור
    private final double open_pos = 0;  // מצב פתוח

    @Override
    public void runOpMode() throws InterruptedException {

        s_block = hardwareMap.get(Servo.class, "s_block");



        // ---- START IN OPEN POSITION ----
        s_block.setPosition(open_pos);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            // ---- MOVE SERVO BASED ON Y PRESS ----
            if (gamepad1.y) {
                s_block.setPosition(close_pos); // Y לחוץ -> סגור
            } else {
                s_block.setPosition(open_pos);  // Y לא לחוץ -> פתוח
            }

            // ---- TELEMETRY ----
            telemetry.addData("X", "Transfer Motor");
            telemetry.addData("Y", "Hold to Close Servo");
            telemetry.update();
        }
    }
}
