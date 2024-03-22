package com.harry.fitneslife.fragments

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.harry.fitneslife.R
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentBodyBinding
import com.harry.fitneslife.databinding.FragmentHomeBinding
import java.text.DecimalFormat

class BodyFragment : Fragment() {

    private var _binding: FragmentBodyBinding? = null
    private val binding get() = _binding!!

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 70
    private var currentAge: Int = 30
    private var currentHeight: Int = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBodyBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        viewMale = binding.viewMale
        viewFemale = binding.viewFemale
        tvHeight = binding.tvHeight
        rsHeight = binding.rsHeight
        btnSubtractWeight = binding.btnSubtractWeight
        btnPlusWeight = binding.btnPlusWeight
        tvWeight = binding.tvWeight
        btnSubtractAge = binding.btnSubtractAge
        btnPlusAge = binding.btnPlusAge
        tvAge = binding.tvAge
        btnCalculate = binding.btnCalculate
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            Log.i("ciclo", result.toString())
            guardarDatos(result)
            navigateToResult(result)
        }
    }

    private fun guardarDatos(result: Double) {
        var con = SQLite(requireContext(), "fitlife", null, 2)
        var dataBase = con.writableDatabase
        var correo = userData.getEmail()
        val values = ContentValues()
        values.put("imc", result)
        dataBase.update("usuarios", values, "correo =?", arrayOf(correo))

        dataBase.close()
    }

    private fun navigateToResult(result: Double) {
        val fragmentManager = parentFragmentManager
        val bundle = Bundle()
        bundle.putDouble("resultado", result)

        val transaction = fragmentManager.beginTransaction()
        val fragmentDestino = ResultBodyFragment()
        fragmentDestino.arguments = bundle

        transaction.add(R.id.body, fragmentDestino)

        Log.i("ciclo", result.toString())
        transaction.commit()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean): Int{

        val colorReference = if(isSelectedComponent){
            R.color.grey_low
        }else{
            R.color.grey_components
        }

        return ContextCompat.getColor(requireContext(), colorReference)
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight.toDouble() / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        Log.i("ciclo", imc.toString())
        return df.format(imc).toDouble()
    }


}