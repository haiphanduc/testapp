package com.photomyne.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.photomyne.myapplication.databinding.ActivityMainBinding
import com.photomyne.myapplication.model.Api
import com.photomyne.myapplication.model.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var api:Api
    var data: MutableLiveData<Item> = MutableLiveData()

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch(Dispatchers.IO  + CoroutineExceptionHandler { _, throwable ->
            run {
                Log.e("HaiPd", "onCreate: " + throwable.message)
            }
        }) {
            getData()
        }

        data.observe(this) {
            binding.title.text = it.message
        }
    }

    private suspend fun getData() {
        api.getMess().let {
            data.postValue(it)
        }
    }
}