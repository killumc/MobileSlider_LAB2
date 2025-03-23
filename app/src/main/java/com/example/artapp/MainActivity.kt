package com.example.artapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
    private val texts = listOf("Северное сияние в Лапландии", "Норвегия", "Лофотенские острова, Норвегия")
    private val texts2 = listOf(
        "Северное сияние в Лапландии — это волшебное зрелище, где небо оживает в танце света, создавая незабываемые впечатления.",
        "Норвегия, с её величественными фьордами и живописными пейзажами, манит путешественников своей первозданной красотой.",
        "Лофотенские острова, расположенные в Норвегии, предлагают уникальное сочетание дикой природы и уютных рыбацких деревень, где можно насладиться тишиной и спокойствием."
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)
        val titleText: TextView = findViewById(R.id.title)
        val textView: TextView = findViewById(R.id.textView)
        val buttonPrev: Button = findViewById(R.id.button)
        val buttonNext: Button = findViewById(R.id.button2)

        // Восстановите состояние, если оно есть
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0)
        }

        // Установите начальные значения
        updateUI(imageView, titleText, textView)

        buttonPrev.setOnClickListener {
            currentIndex = when {
                currentIndex > 0 -> currentIndex - 1
                else -> images.size - 1
            }
            updateUI(imageView, titleText, textView)
        }

        buttonNext.setOnClickListener {
            currentIndex = when {
                currentIndex < images.size - 1 -> currentIndex + 1
                else -> 0
            }
            updateUI(imageView, titleText, textView)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateUI(imageView: ImageView, titleText: TextView, textView: TextView) {
        imageView.setImageResource(images[currentIndex])
        textView.text = texts2[currentIndex]
        titleText.text = texts[currentIndex]
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
    }
}
