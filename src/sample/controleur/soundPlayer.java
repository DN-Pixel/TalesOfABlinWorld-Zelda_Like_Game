package sample.controleur;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;

public class soundPlayer {

    private static MediaPlayer musiqueDeFond = new MediaPlayer(new Media(Paths.get("src/sample/ressources/sounds/music/village.mp3").toUri().toString()));

    public static void playMusiqueDeFond(String musicName) {
        stopMusiqueDeFond();
        Media h = new Media(Paths.get("src/sample/ressources/sounds/music/"+musicName).toUri().toString());
        musiqueDeFond = new MediaPlayer(h);
        musiqueDeFond.setVolume(0.05);
        musiqueDeFond.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                musiqueDeFond.seek(Duration.ZERO);
                musiqueDeFond.play();
            }
        });
        musiqueDeFond.play();
    }

    public static void stopMusiqueDeFond(){
        musiqueDeFond.stop();
    }

    public static void playMapMusic(String numero){
        switch (numero){
            case "0":
                stopRain();
                playMusiqueDeFond("mystical.mp3");
                break;
            case "6":
                stopRain();
                playMusiqueDeFond("boss.mp3");
                break;
            case "2":
                playRain();
                break;
            case "5":
                playRain();
                break;
            case "1":
                stopRain();
                playMusiqueDeFond("village.mp3");
                break;
        }
}

    private static MediaPlayer rainSound = new MediaPlayer(new Media(Paths.get("src/sample/ressources/sounds/music/rain.mp3").toUri().toString()));

    private static void playRain() {
        stopRain();
        Media h = new Media(Paths.get("src/sample/ressources/sounds/music/rain.mp3").toUri().toString());
        rainSound = new MediaPlayer(h);
        rainSound.setVolume(0.07);
        rainSound.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                rainSound.seek(Duration.ZERO);
                rainSound.play();
            }
        });
        rainSound.play();
    }

    private static void stopRain() {
        rainSound.stop();
    }

    public static void playerGotHit(){
        MediaPlayer sound;
        Media h = new Media(Paths.get("src/sample/ressources/sounds/Hit.wav").toUri().toString());
        sound = new MediaPlayer(h);
        sound.setVolume(0.05);
        sound.play();
    }

    public static void playerDied(){
        MediaPlayer sound;
        Media h = new Media(Paths.get("src/sample/ressources/sounds/GameOver.wav").toUri().toString());
        sound = new MediaPlayer(h);
        sound.setVolume(0.2);
        sound.play();
    }

    public static void playerLevelUp(){
        MediaPlayer sound;
        Media h = new Media(Paths.get("src/sample/ressources/sounds/PowerUp1.wav").toUri().toString());
        sound = new MediaPlayer(h);
        sound.setVolume(0.2);
        sound.play();
    }

    public static void playSpecificSound(String filename){
        MediaPlayer sound;
        Media h = new Media(Paths.get("src/sample/ressources/sounds/"+filename).toUri().toString());
        sound = new MediaPlayer(h);
        sound.setVolume(0.1);
        sound.play();
    }
    public static void playAttackSound(){
        String filename = "";
        double x = Math.random();
        if(x<0.25)
            filename = "hitEnnemy.mp3";
        else if(x<0.5)
            filename = "hitEnnemy2.mp3";
        else if(x<0.75)
            filename = "hitEnnemy3.mp3";
        else
            filename = "hitEnnemy4.mp3";
        MediaPlayer sound;
        Media h = new Media(Paths.get("src/sample/ressources/sounds/"+filename).toUri().toString());
        sound = new MediaPlayer(h);
        sound.setVolume(0.1);
        sound.play();
    }
}
