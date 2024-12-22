package com.example.mydicegame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mydicegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DiceViewModel by viewModels()

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diceImages = listOf(
            binding.dice1,
            binding.dice2,
            binding.dice3,
            binding.dice4,
            binding.dice5
        )

        viewModel.diceValues.observe(this, Observer { diceList ->
            diceList.forEachIndexed { index, dice ->
                val resource = resources.getIdentifier("dice_${dice.value}", "drawable", packageName)
                diceImages[index].setImageResource(resource)
            }
        })

        binding.startButton.setOnClickListener {
            viewModel.startRolling()
        }

        binding.stopButton.setOnClickListener {
            viewModel.stopRolling()
        }
    }
}
