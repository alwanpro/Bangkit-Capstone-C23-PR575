package com.example.nutriku.screen.scan

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NavUtils
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.myjetpackapplication.R
import com.example.myjetpackapplication.databinding.ActivityConsumptionEditorBinding
import com.example.nutriku.MainActivity
import com.example.nutriku.data.FoodsDetailData
import com.example.nutriku.viewmodel.ConsumptionScreenViewModel
import com.example.nutriku.viewmodel.ConsumptionViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class ConsumptionEditorActivity : AppCompatActivity() {
    lateinit var binding : ActivityConsumptionEditorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumptionEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Tambah Konsumsi"

        val imagepath = intent?.getStringExtra("imagePath")
        val foodclass = intent?.getStringExtra("foodclass")

        binding.editorimageview.load(File(imagepath!!))

        val factory: ConsumptionViewModelFactory = ConsumptionViewModelFactory.getInstance(foodclass ?: "")
        val viewModel: ConsumptionScreenViewModel by viewModels { factory }

        // set up button onClick listener
        binding.editorminusbutton.setOnClickListener {
            if (viewModel.selectedAmount.value!! > 0) {
                viewModel.selectedAmount.value = viewModel.selectedAmount.value!! - 100
            }
        }
        binding.editorplusbutton.setOnClickListener {
            viewModel.selectedAmount.value = viewModel.selectedAmount.value!! + 100
        }
        binding.editorsimpanbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            val toast = Toast.makeText(this, "Data konsumsi berhasil ditambahkan!", Toast.LENGTH_LONG)
            viewModel.postFoodsConsumption(imagepath, foodclass!!, binding.editorselectedamount.text.toString()) {
                NavUtils.navigateUpTo(this@ConsumptionEditorActivity, intent)
            }
            toast.show()
        }

        // observer
        viewModel.foodsDetail.observe(this) {
            if (it != null) {
                setDetailViews(it)
            }
        }

        viewModel.selectedAmount.observe(this) {
            setNumberSlider(it, viewModel.foodsDetail.value?.calorie ?: 100)
        }

        viewModel.uploadStatus.observe(this) {
//            val intent = Intent(this, MainActivity::class.java)
//            NavUtils.navigateUpTo(this@ConsumptionEditorActivity, intent)
//            Toast.makeText(this, "Data konsumsi berhasil ditambahkan!", Toast.LENGTH_LONG).show()
        }

    }
    @SuppressLint("SetTextI18n")
    private fun setDetailViews(foodsDetailData: FoodsDetailData) {
        binding.editorfoodclass.text = foodsDetailData.name
        binding.editornutrisienergi.text = "Nutriscore : ${foodsDetailData.nutriscore}"
        binding.editornutrisilemak.text = "Lemak : ${foodsDetailData.fat} g"
        binding.editornutrisiprotein.text = "Protein : ${foodsDetailData.protein} g"
        binding.editornutrisikarbo.text = "Karbohidrat : ${foodsDetailData.carb} g"
        binding.editortotalcalorie.text = "${foodsDetailData.calorie} Kkal"

    }

    @SuppressLint("SetTextI18n")
    private fun setNumberSlider(selectedAmount: Int, calorie: Int) {
        binding.editorselectedamount.text = "${selectedAmount}g"
        val totalCalorie = selectedAmount / 100 * calorie
        binding.editortotalcalorie.text = "${totalCalorie.toInt()} Kkal"
    }
}