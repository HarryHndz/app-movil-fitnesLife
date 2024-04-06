package com.harry.fitneslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.FragmentEjercicioBinding
import com.harry.fitneslife.databinding.FragmentRutinaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RutinaFragment : Fragment() {

    private var _bindig : FragmentRutinaBinding? = null
    private val binding get() = _bindig!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindig = FragmentRutinaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPrin.setOnClickListener {
            findNavController().navigate(R.id.action_navRutinas_to_routinePrincipianteFragment)
        }
        binding.viewInter.setOnClickListener {
            findNavController().navigate(R.id.action_navRutinas_to_routineIntermedioFragment)
        }
        binding.viewAvan.setOnClickListener {
            findNavController().navigate(R.id.action_navRutinas_to_routineAvanzadoFragment)
        }
    }


}