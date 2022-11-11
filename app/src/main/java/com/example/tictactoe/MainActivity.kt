package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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

        if(checkForVictory(NOUGHT)){
            val winningText = findViewById<TextView>(R.id.turnText)
            winningText.text = "$NOUGHT Wins!"

        }
        if(checkForVictory(CROSS)){
            val winningText = findViewById<TextView>(R.id.turnText)
            winningText.text = "$CROSS Wins!"
        }

        newGameBtn.setOnClickListener{
                resetBoard()
        }
    }

    private fun checkForVictory(s: String): Boolean {
        val match1 = match(binding.topLeft,s) && match(binding.topMid,s) && match(binding.topRight,s)
        val match2 = match(binding.midLeft,s) && match(binding.mid,s) && match(binding.midRight,s)
        val match3 = match(binding.botLeft,s) && match(binding.botMid,s) && match(binding.botRight,s)
        val match4 = match(binding.topLeft,s) && match(binding.midLeft,s) && match(binding.botLeft,s)
        val match5 = match(binding.topMid,s) && match(binding.mid,s) && match(binding.botMid,s)
        val match6 = match(binding.topRight,s) && match(binding.midRight,s) && match(binding.botRight,s)
        val match7 = match(binding.topLeft,s) && match(binding.mid,s) && match(binding.botRight,s)
        val match8 = match(binding.topRight,s) && match(binding.mid,s) && match(binding.botLeft,s)

        // Horizontal victories
        if(match1)
            return true
        if(match2)
            return true
        if(match3)
            return true

        //Vertical victories
        if(match4)
            return true
        if(match5)
            return true
        if(match6)
            return true

        //Diagonal victories
        if(match7)
            return true
        if(match8)
            return true

        return false
    }

    private fun match(button: Button,symbol : String) = button.text == symbol

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

    private fun addToBoard(button: Button) {
        val draw = findViewById<TextView>(R.id.turnText)
        
            if (button.text != "") {
                draw.text = "Draw!"
                return
            }
            if (currTurn == Turn.NOUGHT) {
                button.text = NOUGHT
                currTurn = Turn.CROSS
            } else if (currTurn == Turn.CROSS) {
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