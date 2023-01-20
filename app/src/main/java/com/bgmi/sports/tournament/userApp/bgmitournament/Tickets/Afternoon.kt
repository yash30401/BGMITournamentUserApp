package com.bgmi.sports.tournament.userApp.bgmitournament.Tickets

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgmi.sports.tournament.userApp.bgmitournament.R
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.FragmentAfternoonBinding

class Afternoon : Fragment(R.layout.fragment_afternoon) {

    private lateinit var binding:FragmentAfternoonBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentAfternoonBinding.bind(view)


    }

}