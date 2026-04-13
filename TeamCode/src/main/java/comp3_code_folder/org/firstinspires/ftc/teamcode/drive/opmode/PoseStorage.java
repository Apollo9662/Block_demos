package comp3_code_folder.org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class PoseStorage {
    // ה-static חשוב כדי שהמידע יישמר במעבר בין OpModes
    public static Pose2d currentPose = new Pose2d();
}