package io.android.voterapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nostra13.universalimageloader.core.ImageLoader
import android.graphics.Bitmap




class CandidateAdaptor(private val cList:List<CandidateModel>) :
    RecyclerView.Adapter<CandidateAdaptor.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.candidate_details, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CandidateAdaptor.ViewHolder, position: Int) {
        val candidateModel = cList[position]

        val imageLoader = ImageLoader.getInstance()
        val bmp = imageLoader.loadImageSync(candidateModel.photoUrl)
        holder.candidateProfilePhoto.setImageBitmap(bmp)
        holder.candidateFullName.text = candidateModel.candidateName
        holder.candidatePartyName.text = candidateModel.candidateParty



    }

    override fun getItemCount(): Int {
        return cList.size
    }



    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val candidateFullName = itemView.findViewById<TextView>(R.id.candidate_name)
        val candidatePartyName = itemView.findViewById<TextView>(R.id.candidate_party_name)
        val candidateProfilePhoto = itemView.findViewById<ImageView>(R.id.candidate_profile_photo)
    }
}


