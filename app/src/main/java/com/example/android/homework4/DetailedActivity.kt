package com.example.android.homework4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.android.homework4.databinding.ActivityDetailedBinding

class DetailedActivity: AppCompatActivity() {

    private lateinit var textView_dTitle: TextView
    private lateinit var textView_dEmotion : TextView
    private lateinit var textView_dHappened: TextView
    private lateinit var textView_dInterpretation: TextView
    private lateinit var button_delete: Button
    private lateinit var button_update: Button
    private lateinit var binding: ActivityDetailedBinding
    private lateinit var dreamInfo: Array<String>

    private val dreamViewModel: DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textView_dTitle = binding.textViewDTitle
        textView_dEmotion = binding.textViewDEmotionInfo
        textView_dHappened = binding.textViewDHappenedInfo
        textView_dInterpretation = binding.textViewDInterpretationInfo
        button_delete = binding.buttonDDelete
        button_update = binding.buttonDUpdate

        val id = intent.getIntExtra("id", -1)


        dreamViewModel.getDream(id).observe(this, Observer {
            textView_dTitle.text = it.title
            textView_dEmotion.text = it.emotion
            textView_dHappened.text = it.content
            textView_dInterpretation.text = it.reflection

            dreamInfo = arrayOf(it.id.toString(), it.title, it.emotion, it.content, it.reflection)

        })

        button_delete.setOnClickListener {
            dreamViewModel.deleteById(id)
            super.onBackPressed()
        }
        
        button_update.setOnClickListener { 
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("info", dreamInfo)
            startActivity(intent)
        }
    }
}