package com.harry.fitneslife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.FragmentBodyBinding
import com.harry.fitneslife.databinding.FragmentResultBodyBinding
import com.harry.fitneslife.fragments.BodyFragment.Companion.IMC_KEY

class ResultBodyFragment : Fragment() {

    private var _binding: FragmentResultBodyBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalcular: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBodyBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val result: Double = bundle?.getDouble("resultado") ?: -1.0
        Log.i("ciclo", result.toString())
        initComponents()
        initUI(result)
        initListener()
    }

    private fun initComponents() {
        tvIMC = binding.tvIMC
        tvResult = binding.tvResult
        tvDescription = binding.tvDescription
        btnRecalcular = binding.btnRecalculate
    }

    private fun initUI(result: Double) {
        val contexto = requireContext()
        tvIMC.text = result.toString()
        Log.i("ciclo", result.toString())
        when(result) {
            in 0.00..18.50 -> { //bajo peso
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(contexto, R.color.peso_bajo))
                tvDescription.text = getString(R.string.descripcion_bajo_peso)
            }
            in 18.51..24.99 -> {//Peso normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(contexto, R.color.peso_normal))
                tvDescription.text = getString(R.string.descripcion_peso_normal)
            }
            in 25.00..29.99 -> {//Sobrepeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(contexto, R.color.sobrepeso))
                tvDescription.text = getString(R.string.descripcion_sobrepeso)
            }
            in 30.00..99.00 -> {//Obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(contexto, R.color.obesidad))
                tvDescription.text = getString(R.string.descripcion_obesidad)
            }
            else -> {//error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(contexto, R.color.obesidad))
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initListener() {
        btnRecalcular.setOnClickListener {
            val fragmentManager = parentFragmentManager

            if (fragmentManager.backStackEntryCount > 0) {
                fragmentManager.popBackStack()
            } else {
                // finish()
            }
        }
    }
}