package com.rahulm.pomodoro.controllers;

import com.rahulm.pomodoro.model.Attempt;
import com.rahulm.pomodoro.model.AttemptKind;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Home {
    private final AudioClip mApplause;
    private final AudioClip note;
    @FXML
    public VBox container;

    @FXML
    public Label title;

    @FXML
    public TextArea message;

    // ctrl+b shortcut

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    private Timeline mTimeline;

    public Home() {
        mTimerText = new SimpleStringProperty();
        setTimerText(0);
        mApplause = new AudioClip(getClass().getResource("/sounds/Pop.mp3").toExternalForm());
        note = new AudioClip(this.getClass().getResource("/music/ocean.mp3").toString());
    }

    public String getTimerText() {
        return mTimerText.get();
    }

    public StringProperty timerTextProperty() {
        return mTimerText;
    }

    public void setTimerText(String timerText) {
        mTimerText.set(timerText);
    }

    public void setTimerText(int remainingSeconds) {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes, seconds));
    }

    private void prepareAttempt(AttemptKind kind) {
        reset();
        mCurrentAttempt = new Attempt(kind, "");
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getRemainingSeconds());
        mTimeline = new Timeline();
        mTimeline.setCycleCount(kind.getTotalSeconds());
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            mCurrentAttempt.tick(); //lambda
            setTimerText(mCurrentAttempt.getRemainingSeconds());
        }));
        mTimeline.setOnFinished(e -> { //lambda
            saveCurrentAttempt();
            mApplause.play();
            prepareAttempt(mCurrentAttempt.getKind() == AttemptKind.FOCUS ?
                    AttemptKind.BREAK : AttemptKind.FOCUS); //if the currebt time is focus switch to break or else focus
        });
    }

    private void saveCurrentAttempt() {
        mCurrentAttempt.setMessage(message.getText());
        mCurrentAttempt.save();
    }

    private void reset() {
        clearAttemptStyles();
        if (mTimeline != null && mTimeline.getStatus() == Animation.Status.RUNNING) {
            mTimeline.stop();
        }
    }

    public void playTimer() {
        container.getStyleClass().add("playing");
        mTimeline.play();
    }

    public void pauseTimer() {
        container.getStyleClass().remove("playing");
        mTimeline.pause();
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {
        container.getStyleClass().remove("playing");
        for (AttemptKind kind : AttemptKind.values()) {
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

//    public void DEBUG(ActionEvent actionEvent) {
//        System.out.println("TEST");
//    }

    public void handleRestart(ActionEvent actionEvent) {
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
        note.play();
    }

    public void handlePlay(ActionEvent actionEvent) {
        if (mCurrentAttempt == null) {
        reset();
        } else {
            playTimer();
        }
    }
    public void handlePause(ActionEvent actionEvent) {
        pauseTimer();
    }

    public void handleStop(ActionEvent actionEvent) {
        if (mCurrentAttempt == null) {
            reset();
        } else{
            mTimeline.stop();
            reset();
            setTimerText(0);
            saveCurrentAttempt();
            mTimeline = new Timeline();
            note.stop();
        }

    }

    public void handleMusic(ActionEvent actionEvent) {
        if (note.isPlaying())
        {
            note.stop();
        }else{
            note.play();
        }
    }

    public void handleInformation(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://en.wikipedia.org/wiki/Pomodoro_Technique"));
    }

}
