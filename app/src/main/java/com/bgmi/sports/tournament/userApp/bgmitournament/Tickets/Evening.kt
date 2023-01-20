package com.bgmi.sports.tournament.userApp.bgmitournament.Tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgmi.sports.tournament.userApp.bgmitournament.R
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.FragmentEveningBinding

class Evening : Fragment(R.layout.fragment_evening) {


    private lateinit var binding: FragmentEveningBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentEveningBinding.bind(view)


    }

}
