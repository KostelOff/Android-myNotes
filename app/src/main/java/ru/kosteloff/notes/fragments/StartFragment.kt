package ru.kosteloff.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kosteloff.notes.APP
import ru.kosteloff.notes.R
import ru.kosteloff.notes.adapter.NoteAdapter
import ru.kosteloff.notes.databinding.FragmentStartBinding
import ru.kosteloff.notes.viewmodel.StartViewModel

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatabase()
        addNote()
    }

    private fun initDatabase() {
        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]

        viewModel.initDataBase()

        recyclerView = binding.recyclerviewStartFrag
        noteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter

        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            noteAdapter.setList(it.asReversed())
        }
    }

    private fun addNote() {
        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }
}