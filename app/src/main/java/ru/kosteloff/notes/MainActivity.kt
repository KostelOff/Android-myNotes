package ru.kosteloff.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import ru.kosteloff.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var naVController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP = this
        naVController = Navigation.findNavController(this, R.id.nav_fragment)

        setupActionBarWithNavController(findNavController(R.id.nav_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navigate = findNavController(R.id.nav_fragment)
        return navigate.navigateUp() || super.onSupportNavigateUp()
    }
}