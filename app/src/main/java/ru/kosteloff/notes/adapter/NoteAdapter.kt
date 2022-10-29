package ru.kosteloff.notes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kosteloff.notes.R
import ru.kosteloff.notes.databinding.CardviewLayoutBinding
import ru.kosteloff.notes.fragments.StartFragment
import ru.kosteloff.notes.fragments.StartFragmentDirections
import ru.kosteloff.notes.tablemodel.NoteModel

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var noteList = emptyList<NoteModel>()

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardviewLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.binding.nameTitle.text = currentItem.title

        holder.binding.cardViewId.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToDetailFragment(currentItem)
            holder.binding.cardViewId.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>) {
        noteList = list
        notifyDataSetChanged()
    }
}