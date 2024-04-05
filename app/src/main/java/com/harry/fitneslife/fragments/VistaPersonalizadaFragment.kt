package com.harry.fitneslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.FragmentRutinaPersonalizadaBinding
import com.harry.fitneslife.databinding.FragmentVistaPersonalizadaBinding

class VistaPersonalizadaFragment : Fragment() {

    private var _binding: FragmentVistaPersonalizadaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVistaPersonalizadaBinding.inflate(inflater,container,false)
        return binding.root
    }
}