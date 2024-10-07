package org.example

import java.io.File
import java.io.FileOutputStream
import java.util.Scanner
import kotlin.random.Random

fun main() {


    println("Login: \n")
    val player : String = readlnOrNull().toString()
    val p1 : Player = Player(player)
    val path : String = "scoreboard.csv"
    val file : File = File(path)
    var score = 0;
    if (file.exists()) {
        p1.readCSV(path);
    } else {
        file.createNewFile();
        p1.updatePlayer(score);
        p1.save(path);
    }

    val oldScore : Int  = p1.showPlayerScore()

    println("Hi $player, your score on the leaderboard is: $oldScore");
    println("How many rounds do you want to play? \n");
    val rounds : Int  = readln().toIntOrNull() ?: 0
    println("How to play: 1-paper , 2-rock , 3-scissors")
    for (i in 0 until rounds){
        println("Round ${i+1}\n");
        val plAns : Int = readln().toInt();
        val pcAns = Random.nextInt(1,4); // 1 papier 2 kamien 3 nozyce

        if (pcAns == plAns){
            println("Draw!")
        }
        if(plAns == 1){
            if (pcAns == 2){
                println("$pcAns \nYou win!")
                score++
            }
            if (pcAns == 3){
                println("$pcAns \nYou lose!")
                score--
            }
        }
        if(plAns == 2){
            if (pcAns == 3){
                println("$pcAns \nYou win!")
                score++
            }
            if (pcAns == 1){
                println("$pcAns \nYou lose!")
                score--
            }
        }
        if(plAns == 3){
            if (pcAns == 1){
                println("$pcAns \nYou win!")
                score++
            }
            if (pcAns == 2){
                println("$pcAns \nYou lose!")
                score--
            }
        }

        println("Your score for this session: $score\n");
    }
    p1.updatePlayer(score);
    p1.save(path);
    p1.showLeaderBoard(path);

}