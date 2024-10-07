package org.example

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

class Player(val player: String) {

    var playerList : HashMap<String,Int> = HashMap()


    fun readCSV(path: String) {
        val br: BufferedReader = BufferedReader(FileReader(path))
        var line: String?

        while (br.readLine().also { line = it } != null) {
            line?.let {
                if (it.isNotBlank()) {
                    val parts = it.split(",")
                    if (parts.size >= 2) {
                        playerList[parts[0]] = parts[1].toIntOrNull() ?: 0
                    }
                }
            }
        }

        br.close()
    }

    fun showLeaderBoard(path:String){
        readCSV(path)
        println("Login, Score\n")
        for ((key, value) in playerList) {
            println("$key, $value")
        }

    }

    fun showPlayerScore() : Int{
        if(!playerList.contains(player)){
            updatePlayer(0);
        }
        return playerList.getValue(player)
    }

    fun updatePlayer(score : Int){
        if (!playerList.containsKey(player)){
            playerList.put(player,score);
        }
        else{
            var newScore : Int = playerList.getValue(player) + score;
            playerList.replace(player,playerList.getValue(player),newScore)
        }

    }
    fun save(path : String){
        BufferedWriter(FileWriter(path)).use { bw ->
            val sortedPlayerList = playerList.toList().sortedBy { (_, value) -> value }.toMap(LinkedHashMap())
            for ((key, value) in sortedPlayerList) {
                bw.write("$key,$value")
                bw.newLine()
            }
        }
    }
}