package com.example.myimadassignment2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }// declarations and Variables
        var score = findViewById<TextView>(R.id.score)
        val hackButton = findViewById<RadioButton>(R.id.Hack)
        val mythButton = findViewById<RadioButton>(R.id.Myth)
        val nextQuestionButton = findViewById<Button>(R.id.nextquestion)
        val Question = findViewById<TextView>(R.id.showquestion)
        val Answer = findViewById<TextView>(R.id.showanswer)

        val selectionOfHacksandMyths = arrayOf(
            "Using a spring from an old pen you have to prevent your phone charger cable from fraying.",
            "Microwaving your phone charges it without any damage",
            "Freeze grapes to use as ice cubes for wine so they chill your drink without watering it down.",
            "Carrots do not actually give you super night vision",
            "Take a photo of your fridge before grocery shopping so you always know exactly what you're out of.",
            "Shaving your hair does not make it grow back thicker",
            "Put a wooden spoon you have across a boiling pot to stop the water from overflowing.",
            "Goldfish do not have a 3 second memory",

        )

        val Answers = arrayOf(true, false, true, false, true, false, true, false)
        /* Hack = true and Myth = false because the quizzes require yes or no answers.
         */

        val thePlayerAns = BooleanArray(8)
        var IndexofmyQuestions = 0
        var thePlayerScore = 0

        hackButton.setOnClickListener { mythButton.isChecked = false }
        mythButton.setOnClickListener { hackButton.isChecked = false }

        Question.text = selectionOfHacksandMyths[0]

//This is where everything starts

        nextQuestionButton.setOnClickListener {
            if (!hackButton.isChecked && !mythButton.isChecked)
            {
                Toast.makeText(
                    this,
                    "Sorry you have to choose between Hack or Myth.",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            //This is where answers are checked to see if you are wrong or right
            for (i in Answers.indices) {
                if (i == IndexofmyQuestions) {
                    val didTheyPickHack = hackButton.isChecked
                    thePlayerAns[i] = didTheyPickHack

                    if (didTheyPickHack == Answers[i]) {
                        thePlayerScore = thePlayerScore + 1
                        Answer.text = "Well Done,the answer u picked was correct."
                        Answer.setTextColor(Color.GREEN) //The answer will be green
                        score.text = "Are you happy with your current score,which is: $thePlayerScore"
                    } else {
                        Answer.text = "Come on you are better than that"
                        Answer.setTextColor(Color.RED)//The wrong answer will be red
                    }
                }
            }

            Answer.visibility = View.VISIBLE //This means you can see the answer
            //if statement
            if (IndexofmyQuestions < selectionOfHacksandMyths.size - 1) {
                IndexofmyQuestions = IndexofmyQuestions + 1 /*this is going through the questions*/

                //for loop
                for (i in selectionOfHacksandMyths.indices) {
                    if (i == IndexofmyQuestions) {
                        Question.text = selectionOfHacksandMyths[i]

                        //Update the UI again
                        hackButton.postDelayed({
                            hackButton.isChecked = true
                            mythButton.isChecked = false
                            Answer.visibility = View.INVISIBLE
                        }, 1500)
                    }
                }
            } //if statement
            else {
                Question.text = "Well done on doing the myths and hacks quiz! Your final score is: $thePlayerScore"
                val youdidit = Intent(
                    this,
                    MainActivity4::class.java
                )
                //This is needed for the 3rd screen
                youdidit.putExtra("Your final score is ", thePlayerScore)

                //putting info from this screen to the following screen
                youdidit.putExtra("These questions that you sadly got incorrect", thePlayerAns)

                //This is to see the wrong questions
                startActivity(youdidit)
                nextQuestionButton.text = "You are Finished"
                nextQuestionButton.setOnClickListener { finish() }
            }
        }
    }
}