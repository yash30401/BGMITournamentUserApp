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
import com.bgmi.sports.tournament.userApp.bgmitournament.adapter.orderAdapter
import com.bgmi.sports.tournament.userApp.bgmitournament.adapter.purchaseTicketAdapter
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.FragmentMyOrdersBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class MyOrders : Fragment(R.layout.fragment_my_orders) {

    private lateinit var binding:FragmentMyOrdersBinding
    private lateinit var viewModel: ticketViewModel
    lateinit var adapter: orderAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMyOrdersBinding.bind(view)

        binding.progressBar.visibility=View.VISIBLE



        binding.recyclerView.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.setHasFixedSize(true)
        adapter = orderAdapter()
        binding.recyclerView.adapter=adapter

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(ticketViewModel::class.java)

        viewModel.ordersallMatch.observe(viewLifecycleOwner, Observer {

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