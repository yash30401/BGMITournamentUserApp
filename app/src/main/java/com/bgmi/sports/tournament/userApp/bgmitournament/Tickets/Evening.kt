package com.bgmi.sports.tournament.userApp.bgmitournament.Tickets

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgmi.sports.tournament.userApp.bgmitournament.R
import com.bgmi.sports.tournament.userApp.bgmitournament.ViewModel.ticketViewModel
import com.bgmi.sports.tournament.userApp.bgmitournament.adapter.purchaseTicket
import com.bgmi.sports.tournament.userApp.bgmitournament.adapter.purchaseTicketAdapter
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.FragmentEveningBinding
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.FragmentMorningBinding
import com.bgmi.sports.tournament.userApp.bgmitournament.model.matchTicketsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Evening : Fragment(R.layout.fragment_evening),purchaseTicket {


    private lateinit var binding: FragmentEveningBinding

    private lateinit var viewModel: ticketViewModel
    lateinit var adapter: purchaseTicketAdapter

    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: FirebaseAuth
    private lateinit var databaseReference2: DatabaseReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentEveningBinding.bind(view)


        binding.progressBar.visibility=View.VISIBLE


        databaseReference= FirebaseDatabase.getInstance().getReference().child("Orders")
        databaseReference2= FirebaseDatabase.getInstance().getReference().child("Total Orders")

        user= FirebaseAuth.getInstance()

        binding.recyclerView.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.setHasFixedSize(true)
        adapter = purchaseTicketAdapter(this)
        binding.recyclerView.adapter=adapter

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(ticketViewModel::class.java)

        viewModel.eveningallMatch.observe(viewLifecycleOwner, Observer {



            adapter.updateMatchList(it)
            binding.progressBar.visibility=View.GONE

            if(adapter.itemCount==0){
                binding.noMatchesTextView.visibility=View.VISIBLE
            }else{
                binding.noMatchesTextView.visibility=View.GONE
            }
        })

    }

    override fun purchaseticket(ticketsModel: matchTicketsModel, context: Context) {
        databaseReference.child(user.uid.toString()).child(ticketsModel.refId.toString()).setValue(ticketsModel).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(context, "Payment Sucessfull", Toast.LENGTH_SHORT).show()

            }
        }
    }

}
