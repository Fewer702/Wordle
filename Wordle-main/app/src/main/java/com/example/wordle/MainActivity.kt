package com.example.wordle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast



class MainActivity : AppCompatActivity() {

    var wordToGuess = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        val button = findViewById<Button>(R.id.guess_button)
        val reset_button = findViewById<Button>(R.id.reset_button)
        reset_button.visibility = View.INVISIBLE
        val max_guess = 3
        var count = 0

        val guess1 = findViewById<TextView>(R.id.Guess1)
        val check1 = findViewById<TextView>(R.id.Check1)

        val guess2 = findViewById<TextView>(R.id.Guess2)
        val check2 = findViewById<TextView>(R.id.Check2)

        val guess3 = findViewById<TextView>(R.id.Guess3)
        val check3 = findViewById<TextView>(R.id.Check3)

        val guessword = findViewById<TextView>(R.id.wordtoGuess)

        guessword.text = wordToGuess
        guessword.visibility = View.GONE

        button.setOnClickListener {
            //Log.v("Button clicked!")
            // Toast.makeText(this, "Hello to you too!", Toast.LENGTH_SHORT).show()
            it.hideKeyboard()
            val editText = findViewById<EditText>(R.id.editText)
            val guess = editText.text.toString().uppercase()
            val checker = checkGuess(guess)

            count++

            if (guess == wordToGuess) {
                Toast.makeText(it.context, "You won!", Toast.LENGTH_LONG).show()

            }


            if (count == 1) {

                val box1 = "Guess #1     $guess"
                guess1.text = box1
                val box2 = "Check #1     $checker"
                check1.text = box2

            } else if (count == 2) {

                val box1 = "Guess #2     $guess"
                guess2.text = box1

                val box2 = "Check #2     $checker"
                check2.text = box2


            } else if (count == 3) {


                val box1 = "Guess #3     $guess"
                guess3.text = box1

                val box2 = "Check #3     $checker"
                check3.text = box2
                count = 0
                Toast.makeText(it.context, "That's all the guesses, Press reset to try again!", Toast.LENGTH_LONG)
                    .show()

                guessword.visibility = View.VISIBLE
                button.visibility = View.INVISIBLE
                reset_button.visibility = View.VISIBLE
            }

            editText.text.clear()


        }

        reset_button.setOnClickListener {
            button.visibility = View.VISIBLE
            reset_button.visibility = View.INVISIBLE

            guess1.text = ""
            guess2.text = ""
            guess3.text = ""

            check1.text = ""
            check2.text = ""
            check3.text = ""

            wordToGuess = FourLetterWordList.getRandomFourLetterWord()
            guessword.text = wordToGuess
            guessword.visibility = View.GONE

        }



    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
    }









