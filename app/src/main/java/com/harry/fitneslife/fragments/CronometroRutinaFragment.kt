package com.harry.fitneslife.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.FragmentCronometroRutinaBinding

class CronometroRutinaFragment : Fragment() {
    private var _binding: FragmentCronometroRutinaBinding? = null
    private val bindig get() = _binding!!
    private var elapsedTime = 0L
    private lateinit var countDownTimer: CountDownTimer
    private var pausar = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCronometroRutinaBinding.inflate(inflater, container, false)
        return bindig.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        bindig.btnCronometro.setOnClickListener {

            if (!pausar){
                bindig.cronometro.base = SystemClock.elapsedRealtime() - elapsedTime
                bindig.cronometro.start()
                bindig.desCrono.base = SystemClock.elapsedRealtime()
                pausar = true
            }else{
                bindig.cronometro.stop()
                elapsedTime = SystemClock.elapsedRealtime() - bindig.cronometro.base

                countDownTimer = object : CountDownTimer(10000,1000){
                    override fun onTick(millisUntilFinished: Long) {
                        bindig.desCrono.text = (millisUntilFinished / 1000).toString()
                        bindig.btnCronometro.isEnabled = false
                    }

                    override fun onFinish() {
                        bindig.desCrono.text = "¡Ha terminado el descanso!"
                        bindig.cronometro.base = SystemClock.elapsedRealtime() - elapsedTime
                        bindig.cronometro.start()
                        bindig.btnCronometro.isEnabled = true

                    }

                }
                countDownTimer.start()
                pausar = true
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
        _binding = null
    }
}