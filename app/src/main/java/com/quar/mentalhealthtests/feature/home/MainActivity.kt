package com.quar.mentalhealthtests.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.quar.mentalhealthtests.R
import com.quar.mentalhealthtests.data.HomeRepository
import com.quar.mentalhealthtests.databinding.ActivityMainBinding
import com.quar.mentalhealthtests.feature.HomeViewModel
import com.quar.mentalhealthtests.feature.ViewModelProviderFactory
import com.quar.mentalhealthtests.feature.about.AboutActivity
import com.quar.mentalhealthtests.feature.quiz.QuizActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelProviderFactory(HomeRepository(this))
        val mainViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        val adapter = MainAdapter(listOf())

        adapter.setOnItemClickListener { i, s ->
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("categoryId", i)
            intent.putExtra("name", s)
            startActivity(intent)
        }

        binding.apply {
            toolbar2.setTitleTextColor(resources.getColor(R.color.white))
            setSupportActionBar(toolbar2)
            rvMain.adapter = adapter
            rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainViewModel.category.observe(this) {
            adapter.update(it)
        }
        mainViewModel.getCategory()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}