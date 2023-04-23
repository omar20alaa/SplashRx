package app.splash_rx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.splash_rx.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         showProgress()
    }

    private fun showProgress() {
        Observable.range(0, 101)
            .concatMap { value ->
                Observable.timer(50, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                    .map { value }
            }
            .subscribe { value ->
                if (value == 100) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    binding.myProgress.progress = Integer.valueOf(value)
                }
            }
    }


}