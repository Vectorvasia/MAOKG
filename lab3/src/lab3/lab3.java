package lab3;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.util.Duration;

public class lab3 extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 600);

        // Upper tail
        MoveTo moveTo = new MoveTo(660, 160);
        QuadCurveTo upper_tail1 = new QuadCurveTo(650, 105, 560, 105);
        QuadCurveTo upper_tail2 = new QuadCurveTo(585, 110, 580, 125);
        QuadCurveTo upper_tail3 = new QuadCurveTo(440, 125, 390, 205);
        QuadCurveTo upper_tail4 = new QuadCurveTo(450, 165, 505, 165);
        QuadCurveTo upper_tail5 = new QuadCurveTo(480, 170, 450, 205);
        QuadCurveTo upper_tail6 = new QuadCurveTo(480, 190, 520, 180);
        QuadCurveTo upper_tail7 = new QuadCurveTo(525, 190, 530, 200);
        Path upper_tail = new Path();
        upper_tail.setStroke(Color.BLACK);
        upper_tail.setFill(Color.BLACK);
        upper_tail.getElements().addAll(moveTo, upper_tail1, upper_tail2, upper_tail3, upper_tail4, upper_tail5,
                upper_tail6, upper_tail7);
        root.getChildren().add(upper_tail);

        // Lower tail
        MoveTo moveTo0 = new MoveTo(520, 360);
        QuadCurveTo lower_tail1 = new QuadCurveTo(470, 350, 450, 350);
        QuadCurveTo lower_tail2 = new QuadCurveTo(465, 360, 470, 365);
        QuadCurveTo lower_tail3 = new QuadCurveTo(380, 360, 340, 405);
        QuadCurveTo lower_tail4 = new QuadCurveTo(400, 375, 465, 385);
        QuadCurveTo lower_tail5 = new QuadCurveTo(455, 400, 440, 405);
        QuadCurveTo lower_tail6 = new QuadCurveTo(480, 410, 520, 395);
        Path lower_tail = new Path();
        lower_tail.setStroke(Color.BLACK);
        lower_tail.setFill(Color.BLACK);
        lower_tail.getElements().addAll(moveTo0, lower_tail1, lower_tail2, lower_tail3, lower_tail4, lower_tail5,
                lower_tail6);
        root.getChildren().add(lower_tail);

        // Triangle part
        MoveTo moveTo1 = new MoveTo(590, 155);
        QuadCurveTo triangle_line1 = new QuadCurveTo(510, 290, 470, 465);
        QuadCurveTo triangle_angle1 = new QuadCurveTo(470, 485, 500, 490);
        QuadCurveTo triangle_line2 = new QuadCurveTo(630, 500, 900, 420);
        QuadCurveTo triangle_angle2 = new QuadCurveTo(920, 405, 900, 380);
        QuadCurveTo triangle_line3 = new QuadCurveTo(770, 230, 660, 160);
        QuadCurveTo triangle_angle3 = new QuadCurveTo(610, 125, 590, 155);
        Path triangle = new Path();
        triangle.setStrokeWidth(7);
        triangle.setStroke(Color.BLACK);
        triangle.setFill(Color.YELLOW);
        triangle.getElements().addAll(moveTo1, triangle_line1, triangle_angle1, triangle_line2, triangle_angle2,
                triangle_line3, triangle_angle3);
        root.getChildren().add(triangle);

        // Left eye
        MoveTo moveTo2 = new MoveTo(630, 340);
        QuadCurveTo left_eye1 = new QuadCurveTo(625, 360, 630, 370);
        QuadCurveTo left_eye2 = new QuadCurveTo(650, 395, 700, 373);
        QuadCurveTo left_eye3 = new QuadCurveTo(715, 365, 710, 345);
        Path left_eye = new Path();
        left_eye.setStrokeWidth(4);
        left_eye.setStroke(Color.BLACK);
        left_eye.setFill(Color.WHITE);
        left_eye.getElements().addAll(moveTo2, left_eye1, left_eye2, left_eye3);
        root.getChildren().add(left_eye);

        // Left apple
        Circle left_apple = new Circle(690, 350, 7);
        left_apple.setFill(Color.BLACK);
        root.getChildren().add(left_apple);

        // Left eyebrow
        MoveTo moveTo3 = new MoveTo(575, 335);
        QuadCurveTo left_eyebrow1 = new QuadCurveTo(565, 325, 580, 315);
        LineTo left_eyebrow2 = new LineTo(715, 330);
        QuadCurveTo left_eyebrow3 = new QuadCurveTo(725, 338, 715, 345);
        LineTo left_eyebrow4 = new LineTo(575, 335);
        Path left_eyebrow = new Path();
        left_eyebrow.setStroke(Color.BROWN);
        left_eyebrow.setFill(Color.BROWN);
        left_eyebrow.getElements().addAll(moveTo3, left_eyebrow1, left_eyebrow2, left_eyebrow3, left_eyebrow4);
        root.getChildren().add(left_eyebrow);

        // Right eye
        MoveTo moveTo4 = new MoveTo(760, 330);
        QuadCurveTo right_eye1 = new QuadCurveTo(770, 365, 808, 360); //770 365 805 355
        QuadCurveTo right_eye2 = new QuadCurveTo(835, 340, 825, 305);
        Path right_eye = new Path();
        right_eye.setStrokeWidth(4);
        right_eye.setStroke(Color.BLACK);
        right_eye.setFill(Color.WHITE);
        right_eye.getElements().addAll(moveTo4, right_eye1, right_eye2);
        root.getChildren().add(right_eye);

        // Right eyebrow
        MoveTo moveTo5 = new MoveTo(750, 325);
        LineTo right_eyebrow1 = new LineTo(860, 270);
        QuadCurveTo right_eyebrow2 = new QuadCurveTo(875, 275, 870, 285);
        LineTo right_eyebrow3 = new LineTo(758, 340);
        QuadCurveTo right_eyebrow4 = new QuadCurveTo(740, 330, 750, 325);
        Path right_eyebrow = new Path();
        right_eyebrow.setStroke(Color.BROWN);
        right_eyebrow.setFill(Color.BROWN);
        right_eyebrow.getElements().addAll(moveTo5, right_eyebrow1, right_eyebrow2, right_eyebrow3, right_eyebrow4);
        root.getChildren().add(right_eyebrow);

        // Right apple
        Circle right_apple = new Circle(810, 330, 7);
        right_apple.setFill(Color.BLACK);
        root.getChildren().add(right_apple);

        // White bottom
        MoveTo moveTo5_5 = new MoveTo(630, 478);
        QuadCurveTo white_bottom_up = new QuadCurveTo(750, 350, 875, 421);
        QuadCurveTo white_bottom_down = new QuadCurveTo(750, 460, 630, 478);
        Path white_bottom = new Path();
        white_bottom.setStrokeWidth(4);
        white_bottom.setStroke(Color.WHITE);
        white_bottom.setFill(Color.WHITE);
        white_bottom.getElements().addAll(moveTo5_5, white_bottom_up, white_bottom_down);
        root.getChildren().add(white_bottom);

        // Beak upper
        MoveTo moveTo6 = new MoveTo(740, 345);
        CubicCurveTo beak1 = new CubicCurveTo(780, 345, 790, 370, 810, 360);
        QuadCurveTo beak2 = new QuadCurveTo(820, 355, 835, 365);
        QuadCurveTo beak3 = new QuadCurveTo(840, 375, 820, 375);
        LineTo beak4 = new LineTo(760, 375);
        QuadCurveTo beak5 = new QuadCurveTo(750, 375, 730, 390);
        QuadCurveTo beak6 = new QuadCurveTo(715, 395, 735, 350);
        QuadCurveTo beak7 = new QuadCurveTo(737, 345, 740, 345);
        Path beak_upper = new Path();
        beak_upper.setStrokeWidth(4);
        beak_upper.setStroke(Color.BLACK);
        beak_upper.setFill(Color.rgb(245, 192, 2));
        beak_upper.getElements().addAll(moveTo6, beak1, beak2, beak3, beak4, beak5, beak6, beak7);
        root.getChildren().add(beak_upper);

        // Beak lower
        MoveTo moveTo7 = new MoveTo(720, 397);
        QuadCurveTo beak8 = new QuadCurveTo(755, 395, 760, 375);
        LineTo beak9 = new LineTo(800, 375);
        LineTo beak10 = new LineTo(760, 410);
        QuadCurveTo beak11 = new QuadCurveTo(755, 413, 750, 410);
        QuadCurveTo beak12 = new QuadCurveTo(710, 400, 720, 397);
        Path beak_lower = new Path();
        beak_lower.setStrokeWidth(4);
        beak_lower.setStroke(Color.BLACK);
        beak_lower.setFill(Color.rgb(245, 192, 2));
        beak_lower.getElements().addAll(moveTo7, beak8, beak9, beak10, beak11, beak12);
        root.getChildren().add(beak_lower);

        // Reflection
        MoveTo moveTo8 = new MoveTo(655, 178);
        QuadCurveTo reflection1 = new QuadCurveTo(700, 240, 728, 230);
        QuadCurveTo reflection2 = new QuadCurveTo(680, 180, 655, 178);
        Path reflection = new Path();
        reflection.setStroke(Color.rgb(250, 245, 164));
        reflection.setFill(Color.rgb(250, 245, 164));
        reflection.getElements().addAll(moveTo8, reflection1, reflection2);
        root.getChildren().add(reflection);

        // Animation
        int cycleCount = 2;
        int time = 2000;

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(-25f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(50);
        translateTransition.setToX(1000);
        translateTransition.setFromY(50);
        translateTransition.setToY(-150);
        translateTransition.setCycleCount(cycleCount);
        translateTransition.setAutoReverse(true);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(0.2);
        scaleTransition.setToY(0.2);
        scaleTransition.setCycleCount(cycleCount);
        scaleTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                scaleTransition,
                translateTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();
        // End of animation

        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab 3");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
