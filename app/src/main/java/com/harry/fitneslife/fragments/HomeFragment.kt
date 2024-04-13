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
import com.harry.fitneslife.activities.InicioActivity
import com.harry.fitneslife.adapter.HomeAdaptaer
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentHomeBinding
import com.harry.fitneslife.funAndClass.ListBox
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val listCarru = mutableListOf<CarouselItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavegarBoton()
        initComponent()
        aggImage()
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
    fun aggImage(){

        listCarru.clear()
        listCarru.add(
            CarouselItem(
                imageDrawable = R.drawable.carru1,
                caption = getString(R.string.image1)
            )
        )
        listCarru.add(
            CarouselItem(
                imageDrawable = R.drawable.carru2,
                caption = getString(R.string.image2)
            )
        )
        listCarru.add(
            CarouselItem(
                imageDrawable = R.drawable.carru3,
                caption = getString(R.string.image3)
            )
        )
        binding.carrusel?.setData(listCarru)
    }

}