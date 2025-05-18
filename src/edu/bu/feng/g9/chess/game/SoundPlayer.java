/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.bu.feng.g9.chess.game;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



import javax.sound.sampled.*;
import java.io.*;
class SoundPlayer {






   
    public static void playSound(String moveType) {
        String filePath = null;

        // Switch to choose which sound file to use"
        switch (moveType.toLowerCase()) {
            case "move":
                filePath ="D:\\java\\projects\\chessBoard\\src\\edu\\bu\\feng\\g9\\chess\\game\\csoundeffects\\move-self.wav";
                break;
            case "capture":
                filePath = "edu/bu/feng/g9/chess/game/csoundeffects/capture.wav";
                break;
            case "check":
                filePath = "edu/bu/feng/g9/chess/game/csoundeffects/move-check.wav";
                break;
            case "lose":
                filePath = "edu.bu.feng.g9.chess.game/csoundeffects/game-lose.wav";
                break;
            case "win":
                filePath = "edu.bu.feng.g9.chess.game/csoundeffects/game-win-long.wav";
                break;
            case "draw":
                filePath = "edu.bu.feng.g9.chess.game/csoundeffects/game-draw.wav";
                break;
            case "time":
                filePath = "edu.bu.feng.g9.chess.game/csoundeffects/tenseconds.wav";
                break;
            case "end":
                filePath = "edu.bu.feng.g9.chess.game/csoundeffects/game-end.wav";
                break;
            case "promote":
                filePath = "edu.bu.feng.g9.chess.game/csoundeffects/promote.wav";
                break;
            case "illegal":
                filePath = "edu.bu.feng.g9.chess.game/csoundeffects/illegal move.wav";
                break;
            case "premove":
                filePath = "edu.bu.feng.g9.chess.game/csoundeffects/premove.wav";
                break;
            
            
        }

        
        try {
            InputStream input = SoundPlayer.class.getResourceAsStream("/" + filePath);
            if (input == null) {
                System.err.println("Sound file not found: " + filePath);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(input));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            
            clip.start();
            Thread.sleep(1000 );
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
                    }
    }
}
    

