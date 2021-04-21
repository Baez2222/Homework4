package com.example.android.homework4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.homework4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var buttonLog: Button
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    private val dreamViewModel: DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.getExtras()
        if (bundle != null){
            dreamViewModel.deleteById(bundle.getInt("id"))
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonLog = binding.buttonAddDream
        recyclerView = binding.recyclerViewMainLog


        val adapter = DreamListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        dreamViewModel.allDreams.observe(this, Observer {
                dreams -> dreams?.let {
            adapter.submitList(it)
        }
        })


        buttonLog.setOnClickListener {
            launchSecondActivity()
        }
    }



    private fun launchSecondActivity(){
        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
}