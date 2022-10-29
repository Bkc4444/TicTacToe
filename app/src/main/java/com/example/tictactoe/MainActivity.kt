package com.example.tictactoe

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class  Turn {
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currTurn = Turn.CROSS
    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }


    private var boardList = mutableListOf<Button>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.topLeft)
        boardList.add(binding.topMid)
        boardList.add(binding.topRight)
        boardList.add(binding.midLeft)
        boardList.add(binding.mid)
        boardList.add(binding.midRight)
        boardList.add(binding.botLeft)
        boardList.add(binding.botMid)
        boardList.add(binding.botRight)
    }

    fun boardTapped(view: View){
        val newGameBtn = findViewById<Button>(R.id.newGameBtn)
        if(view !is Button)
            return
        addToBoard(view)

        newGameBtn.setOnClickListener{
            if(fullBoard()){
                resetBoard()
            }
        }
    }


    private fun resetBoard() {
        for(button in boardList){
            button.text = ""
        }

        if(firstTurn == Turn.NOUGHT) {
            firstTurn =Turn.CROSS
        }
        else if(firstTurn == Turn.CROSS){
            firstTurn = Turn.NOUGHT
        }
        currTurn = firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean {
        for(button in boardList){
            if(button.text == "") {
                return false
            }
        }
        return true
    }

    private fun addToBoard(button: Button){
        if(button.text != "") {
            return
        }
        if(currTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            currTurn = Turn.CROSS
        }
        else if(currTurn == Turn.CROSS) {
            button.text = CROSS
            currTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if(currTurn == Turn.CROSS){
            turnText = "Turn $CROSS"
        }
        else if(currTurn == Turn.NOUGHT){
            turnText = "Turn $NOUGHT"
        }

        binding.turnText.text = turnText
    }
}