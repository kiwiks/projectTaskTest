package com.example.projecttask.animation;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.Node;

public class Shake {
    private TranslateTransition translateTransition;

    public Shake(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(100),  node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(5);
        translateTransition.setAutoReverse(true);
    }

    public void playAnimation() {
        translateTransition.playFromStart();
    }
}
