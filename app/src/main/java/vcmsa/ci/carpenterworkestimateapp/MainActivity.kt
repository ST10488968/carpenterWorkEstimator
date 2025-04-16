package vcmsa.ci.carpenterworkestimateapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val materialCost = mapOf(
        R.id.cbWood to 500,
        R.id.cbTape to 65,
        R.id.cbCuttingTools to 1200,
        R.id.cbHinges to 100,
        R.id.cbNails to 150,
        R.id.cbTools to 700
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val taskRadioGroup = findViewById<RadioGroup>(R.id.taskRadioGroup)
        val etHours = findViewById<EditText>(R.id.etHours)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvTotalCost = findViewById<TextView>(R.id.tvTotalCost)

        btnCalculate.setOnClickListener{
            val selectedTaskId = taskRadioGroup.checkedRadioButtonId
            val hoursText = etHours.text.toString()
            val hours = hoursText.toIntOrNull()

            if (selectedTaskId == 1 || hours == null || hours  <= 0) {
                tvTotalCost.text = "Please select a task and enter valid hours."
                return@setOnClickListener
            }

            var totalMaterialCost = 0
            for ((checkboxId, cost) in materialCost) {
                val checkbox = findViewById<CheckBox>(checkboxId)
                if (checkbox.isChecked) {
                    totalMaterialCost += cost
                }
            }

            if (totalMaterialCost == 0) {
                tvTotalCost.text = "Please select at least one material."
                return@setOnClickListener
            }

            val totalCost = totalMaterialCost * hours
            tvTotalCost.text = "Total Estimated Cost: R$totalCost.00"
        }
    }
}