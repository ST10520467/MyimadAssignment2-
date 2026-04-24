package com.example.myimadassignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.utilities.Score

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }// Declarations and Variables
        val Scoreachieved = findViewById<TextView>(R.id.scoreachieved)
        val Feedback = findViewById<TextView>(R.id.feedback)
        val ReviewButton = findViewById<Button>(R.id.reviewbutton)

        // 2. Get the data sent from QuizActivity
        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)

        // 3. Display the score
        Scoreachieved.text = "Final Score: $score / $total"

        // This depends on the score you get
        when (score) {
            in 0..3 -> {
                Scoreachieved.text = "Score : $score/8"
                Feedback.text = "What were you doing, try again"
            }
            in 4..6 -> {
                Scoreachieved.text = "Score : $score/8"
                Feedback.text = "Its alright, You can definitely do better!"
            }
            in 7..8 -> {
                Scoreachieved.text = "Score : $score/8"
                Feedback.text = "Well Done! You did so good, you should be proud."
            }
            else -> {
                Feedback.text = "Well done on doing the myths and hacks quiz! Your final score is: $score"

            }







        }
        }
}




