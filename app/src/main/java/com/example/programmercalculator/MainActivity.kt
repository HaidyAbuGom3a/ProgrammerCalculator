package com.example.programmercalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.programmercalculator.databinding.ActivityMainBinding
import kotlin.properties.Delegates

private lateinit var numberSystem: NumberSystem
private lateinit var binding: ActivityMainBinding
private lateinit var binary: String
private var decimal by Delegates.notNull<Int>()
private lateinit var octal: String
private lateinit var hex: String

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        addCallbacks()
    }



    private fun init(){
        numberSystem = NumberSystem.DECIMAL
        setupForDecimal()
        clearAll()

    }

    @SuppressLint("SetTextI18n")
    private fun addCallbacks() {
        binding.hex.setOnClickListener {
            numberSystem = NumberSystem.HEX
            setupForHex()
        }
        binding.decimal.setOnClickListener {
            numberSystem = NumberSystem.DECIMAL
            setupForDecimal()
        }
        binding.octal.setOnClickListener {
            numberSystem = NumberSystem.OCTAL
            setupForOctal()
        }
        binding.binary.setOnClickListener {
            numberSystem = NumberSystem.BINARY
            setupForBinary()
        }
        binding.zeroBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.zero)
        }
        binding.oneBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.one)
        }
        binding.twoBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.two)
        }
        binding.threeBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.three)
        }
        binding.fourBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.four)
        }
        binding.fiveBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.five)
        }
        binding.sixBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.six)
        }
        binding.sevenBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.seven)
        }
        binding.eightBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.eight)
        }
        binding.nineBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.nine)
        }
        binding.aBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.a)
        }
        binding.bBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.b)
        }
        binding.cBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.c)
        }
        binding.dBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.d)
        }
        binding.eBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.e)
        }
        binding.fBtn.setOnClickListener {
            binding.number.text = binding.number.text.toString() +
                    getString(R.string.f)
        }
        binding.clearBtn.setOnClickListener {
            clearAll()
        }
        binding.resultBtn.setOnClickListener {
            if(binding.number.text.toString().isNotEmpty()){
                try{
                    executeConversions()
                    showAllNumberSystemsValues(binary,hex,octal,decimal)
                }
                catch(e:Exception){
                    Toast.makeText(this,
                        getString(R.string.toastMsg),
                        Toast.LENGTH_LONG)
                        .show()
                }
            }else{
                Toast.makeText(this,
                    getString(R.string.enter_a_number),
                    Toast.LENGTH_SHORT)
                    .show()
            }

        }
        binding.backspaceBtn.setOnClickListener {
            val number = binding.number.text.toString()
            if(number.isNotEmpty()) binding.number.text = number.substring(0,number.length-1)

        }

    }



    private fun clearAll() {
        val blankStr = getString(R.string.blank)
        binding.number.text =  blankStr
        binding.textBinary.text = blankStr
        binding.textDecimal.text = blankStr
        binding.textOctal.text = blankStr
        binding.txtHex.text = blankStr
    }

    private fun executeConversions(){
        val number = binding.number.text.toString()
        when(numberSystem){
            NumberSystem.DECIMAL -> {
                decimal = number.toInt()
                binary = convertToBinary(decimal)
                hex = convertToHex(decimal)
                octal = convertToOctal(decimal)
            }
            NumberSystem.BINARY ->{
                decimal = Integer.parseInt(number,getString(R.string.binary).toInt())
                binary = number
                hex = convertToHex(decimal)
                octal = convertToOctal(decimal)
            }
            NumberSystem.OCTAL -> {
                decimal = Integer.parseInt(number, getString(R.string.oct).toInt())
                binary = convertToBinary(decimal)
                hex = convertToHex(decimal)
                octal = number
            }
            else ->{
                decimal = Integer.parseInt(number,getString(R.string.hex).toInt())
                binary = convertToBinary(decimal)
                hex = number
                octal = convertToOctal(decimal)
            }
        }
    }

    private fun setupForHex() {
        binding.hex.isEnabled = false
        binding.decimal.isEnabled = true
        binding.octal.isEnabled = true
        binding.binary.isEnabled = true
        binding.zeroBtn.isEnabled = true
        binding.oneBtn.isEnabled = true
        binding.twoBtn.isEnabled = true
        binding.threeBtn.isEnabled = true
        binding.fourBtn.isEnabled = true
        binding.fiveBtn.isEnabled = true
        binding.sixBtn.isEnabled = true
        binding.sevenBtn.isEnabled = true
        binding.eightBtn.isEnabled = true
        binding.nineBtn.isEnabled = true
        binding.aBtn.isEnabled = true
        binding.bBtn.isEnabled = true
        binding.cBtn.isEnabled = true
        binding.dBtn.isEnabled = true
        binding.eBtn.isEnabled = true
        binding.fBtn.isEnabled = true
        binding.number.text = getString(R.string.blank)
    }

    private fun setupForDecimal() {
        binding.decimal.isEnabled = false
        binding.hex.isEnabled = true
        binding.octal.isEnabled = true
        binding.binary.isEnabled = true
        binding.zeroBtn.isEnabled = true
        binding.oneBtn.isEnabled = true
        binding.twoBtn.isEnabled = true
        binding.threeBtn.isEnabled = true
        binding.fourBtn.isEnabled = true
        binding.fiveBtn.isEnabled = true
        binding.sixBtn.isEnabled = true
        binding.sevenBtn.isEnabled = true
        binding.eightBtn.isEnabled = true
        binding.nineBtn.isEnabled = true
        binding.aBtn.isEnabled = false
        binding.bBtn.isEnabled = false
        binding.cBtn.isEnabled = false
        binding.dBtn.isEnabled = false
        binding.eBtn.isEnabled = false
        binding.fBtn.isEnabled = false
        binding.number.text = getString(R.string.blank)
    }

    private fun setupForOctal() {
        binding.hex.isEnabled = true
        binding.decimal.isEnabled = true
        binding.octal.isEnabled = false
        binding.binary.isEnabled = true
        binding.zeroBtn.isEnabled = true
        binding.oneBtn.isEnabled = true
        binding.twoBtn.isEnabled = true
        binding.threeBtn.isEnabled = true
        binding.fourBtn.isEnabled = true
        binding.fiveBtn.isEnabled = true
        binding.sixBtn.isEnabled = true
        binding.sevenBtn.isEnabled = true
        binding.eightBtn.isEnabled = false
        binding.nineBtn.isEnabled = false
        binding.aBtn.isEnabled = false
        binding.bBtn.isEnabled = false
        binding.cBtn.isEnabled = false
        binding.dBtn.isEnabled = false
        binding.eBtn.isEnabled = false
        binding.fBtn.isEnabled = false
        binding.number.text = getString(R.string.blank)
    }

    private fun setupForBinary() {
        binding.hex.isEnabled = true
        binding.decimal.isEnabled = true
        binding.octal.isEnabled = true
        binding.binary.isEnabled = false
        binding.zeroBtn.isEnabled = true
        binding.oneBtn.isEnabled = true
        binding.twoBtn.isEnabled = false
        binding.threeBtn.isEnabled = false
        binding.fourBtn.isEnabled = false
        binding.fiveBtn.isEnabled = false
        binding.sixBtn.isEnabled = false
        binding.sevenBtn.isEnabled = false
        binding.eightBtn.isEnabled = false
        binding.nineBtn.isEnabled = false
        binding.aBtn.isEnabled = false
        binding.bBtn.isEnabled = false
        binding.cBtn.isEnabled = false
        binding.dBtn.isEnabled = false
        binding.eBtn.isEnabled = false
        binding.fBtn.isEnabled = false
        binding.number.text = getString(R.string.blank)
    }

    private fun convertToBinary(decimal:Int):String{
        return Integer.toBinaryString(decimal)
    }

    private fun convertToOctal(decimal:Int):String{
        return Integer.toOctalString(decimal)
    }

    private fun convertToHex(decimal:Int):String{
        return Integer.toHexString(decimal)

    }

    private fun showAllNumberSystemsValues(
        binary: String,
        hex: String,
        octal: String,
        decimal: Int
    )
    {
        binding.textBinary.text = binary
        binding.textDecimal.text = decimal.toString()
        binding.textOctal.text = octal
        binding.txtHex.text = hex
    }




}
