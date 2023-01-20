package com.bgmi.sports.tournament.userApp.bgmitournament.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bgmi.sports.tournament.userApp.bgmitournament.R
import com.bgmi.sports.tournament.userApp.bgmitournament.databinding.MatchLayoutBinding
import com.bgmi.sports.tournament.userApp.bgmitournament.model.matchTicketsModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.channels.TickerMode

class purchaseTicketAdapter:RecyclerView.Adapter<purchaseTicketAdapter.purchaseTicketViewHolder>() {


    private val allMatchData=ArrayList<matchTicketsModel>()

    inner class purchaseTicketViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding=MatchLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): purchaseTicketViewHolder {
        val viewHolder=purchaseTicketViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_layout,parent,false))

        return viewHolder
    }

    override fun getItemCount(): Int {
            return allMatchData.size
    }

    override fun onBindViewHolder(holder: purchaseTicketViewHolder, position: Int) {
        val matchData=allMatchData[position]

        holder.binding.fUploadDate.text=matchData.uploadDate.toString()
        holder.binding.fUploadTime.text=matchData.uploadTime.toString()
        holder.binding.fUploadRefId.text=matchData.refId.toString()
        holder.binding.fUploadSlots.text=matchData.slots.toString()
        holder.binding.fUploadMatchCategory.text=matchData.matchDuration.toString()
        holder.binding.fMatchDate.text=matchData.matchDate.toString()
        holder.binding.fMatchTime.text=matchData.matchTime.toString()
        holder.binding.fRoomId.text=matchData.roomId.toString()
        holder.binding.fRoomPass.text=matchData.roomPass.toString()
        holder.binding.map.text=matchData.matchCategory.toString()

        val prize=matchData.prize.toString()

        val prizeList=prize.split(",")

        if(prize.contains(",")) {
            holder.binding.f1stPrize.text =  "₹"+prizeList.get(0)
            holder.binding.f2ndPrize.text =  "₹"+prizeList.get(1)
            holder.binding.f3rdPrize.text = "₹"+prizeList.get(2)

        }else{
            holder.binding.f1stPrize.text = "₹"+matchData.prize.toString()
            holder.binding.f2ndPrize.text =  "₹"+matchData.prize.toString()
            holder.binding.f3rdPrize.text =  "₹"+matchData.prize.toString()

        }
        holder.binding.fEntryPrice.text = "Entery Price: ₹${matchData.matchCharge.toString()}"

        Picasso.get().load(matchData.imageUrl).into(holder.binding.ftikcetImage)
    }

    fun updateMatchList(allMatchData : List<matchTicketsModel>){
        this.allMatchData.clear()
        this.allMatchData.addAll(allMatchData)
        notifyDataSetChanged()
    }

}