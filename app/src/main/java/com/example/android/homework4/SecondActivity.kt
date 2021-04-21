package com.example.android.homework4

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.homework4.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextHappened : EditText
    private lateinit var editTextInterpretation: EditText
    private lateinit var spinnerEmotion : Spinner
    private lateinit var buttonSave : Button
    private lateinit var binding: ActivitySecondBinding
    private var currSelect : String? = null

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editTextTitle = binding.editTextTitle
        editTextHappened = binding.editTextHappened
        editTextInterpretation = binding.editTextInterpretation
        spinnerEmotion = binding.spinnerEmotion
        buttonSave = binding.buttonSave


        // add choices to spinner
        ArrayAdapter.createFromResource(
            this, R.array.emotion_choices,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerEmotion.adapter = adapter
        }
        spinnerEmotion.onItemSelectedListener = this

        buttonSave.setOnClickListener{
            if (TextUtils.isEmpty(editTextTitle.text) || TextUtils.isEmpty(editTextHappened.text) || TextUtils.isEmpty(editTextInterpretation.text) || spinnerEmotion == null || spinnerEmotion.selectedItem == ""){
                Toast.makeText(this, "Missing Information", Toast.LENGTH_SHORT).show()
            }
            else{
                val dreamInfo = Dream(editTextTitle.text.toString(), editTextHappened.text.toString(), editTextInterpretation.text.toString(), spinnerEmotion.selectedItem.toString())
                dreamViewModel.insert(dreamInfo)
                finish()
            }
        }

    }

    override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
        currSelect = p0.getItemAtPosition(p2).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        currSelect = null
    }

}