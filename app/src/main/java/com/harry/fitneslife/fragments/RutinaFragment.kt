package com.harry.fitneslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.FragmentEjercicioBinding
import com.harry.fitneslife.databinding.FragmentRutinaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RutinaFragment : Fragment() {

    private var _bindig : FragmentRutinaBinding? = null
    private val binding get() = _bindig!!

    /*val retro= RetroFitHelper.buildRetro()
    val listExercise = mutableListOf<ExerciseResponseItem>()
*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindig = FragmentRutinaBinding.inflate(inflater,container,false)
        return binding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        *//*initCorrutina()*//*
    }*/

    /*private fun initCorrutina() {
        lifecycleScope.launch(Dispatchers.IO) {
            val request = retro.getEjercicios()
            if (request.isSuccessful){
                activity?.runOnUiThread {
                    request.body()?.forEach { elemet ->
                        binding.txPrueba?.append(elemet.nombre)
                    }
                    //val fotos: List<String> = request.images[2]
                }
            }
        }
    }*/
}