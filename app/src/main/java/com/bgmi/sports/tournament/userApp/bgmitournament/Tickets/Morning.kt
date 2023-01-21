package com.bgmi.sports.tournament.userApp.bgmitournament.Tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgmi.sports.tournament.userApp.bgmitournament.R
import com.bgmi.sports.tournament.userApp.bgmitournament.ViewModel.ticketViewModel
import com.bgmi.sports.tournament.userApp.bgmitournament.adapter.purchaseTicketAdapter
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.FragmentMorningBinding

class Morning : Fragment(R.layout.fragment_morning) {

    private lateinit var binding: FragmentMorningBinding
    private lateinit var viewModel: ticketViewModel
    lateinit var adapter:purchaseTicketAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMorningBinding.bind(view)

        binding.progressBar.visibility=View.VISIBLE

        binding.recyclerView.layoutManager=
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.setHasFixedSize(true)
        adapter = purchaseTicketAdapter()
        binding.recyclerView.adapter=adapter

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(ticketViewModel::class.java)

        viewModel.morningallMatch.observe(viewLifecycleOwner, Observer {



            adapter.updateMatchList(it)
            binding.progressBar.visibility=View.GONE
            
            if(adapter.itemCount==0){
                binding.noMatchesTextView.visibility=View.VISIBLE
            }else{
                binding.noMatchesTextView.visibility=View.GONE
            }
        })





    }
}