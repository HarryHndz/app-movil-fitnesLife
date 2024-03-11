package com.harry.fitneslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.harry.fitneslife.ApiEjercicios.ExerciseResponseItem
import com.harry.fitneslife.ApiEjercicios.RetroFitHelper
import com.harry.fitneslife.databinding.FragmentEjercicioBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EjercicioFragment : Fragment() {

    private var _bindig : FragmentEjercicioBinding? = null
    private val binding get() = _bindig!!

    val retro= RetroFitHelper.buildRetro()
    val listExercise = mutableListOf<ExerciseResponseItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bindig = FragmentEjercicioBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val request = retro.getEjercicios()
            if (request.isSuccessful){
                activity?.runOnUiThread {
                    request.body()?.forEach { elemet ->
                        listExercise.add(elemet)
                    }
                }
            }
        }

    }




}