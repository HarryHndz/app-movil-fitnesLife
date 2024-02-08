package com.harry.fitneslife.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harry.fitneslife.R
import com.harry.fitneslife.activities.RegistroActivity
import com.harry.fitneslife.adapter.HomeAdaptaer
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentHomeBinding
import com.harry.fitneslife.funAndClass.ListBox

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavegarBoton()
        cerrarSesion()
        initComponent()
    }

    fun initComponent(){
        binding.recicleHome.layoutManager = LinearLayoutManager(
            requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recicleHome.adapter = HomeAdaptaer(ListBox.carrusel)
    }
    fun NavegarBoton(){
        binding.btnMoreExercise.setOnClickListener{
            findNavController().navigate(R.id.action_navInicio_to_navEjercicios)
        }
        binding.btnMoreRutina.setOnClickListener {
            findNavController().navigate(R.id.action_navInicio_to_navRutinas)
        }
    }

    fun cerrarSesion(){
        binding.back.setOnClickListener {
            userData.wipe()
            val intent = Intent(activity,RegistroActivity::class.java)
            startActivity(intent)
        }
        val name = userData.getName()
        binding.tvSaludo.text = name

    }



}