package com.bgmi.sports.tournament.userApp.bgmitournament.Tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgmi.sports.tournament.userApp.bgmitournament.R
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.FragmentMorningBinding

class Morning : Fragment(R.layout.fragment_morning) {

    private lateinit var binding: FragmentMorningBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMorningBinding.bind(view)

    }
}