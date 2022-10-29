package ru.kosteloff.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.kosteloff.notes.R
import ru.kosteloff.notes.databinding.FragmentAddNoteBinding
import ru.kosteloff.notes.tablemodel.NoteModel
import ru.kosteloff.notes.viewmodel.AddNoteViewModel

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveNote()
    }

    private fun saveNote() {
        binding.buttonSave.setOnClickListener {
            if (binding.editTitleText.text.toString().isEmpty() &&
                binding.editDescriptionText.text.toString().isEmpty()
            ) {
                Toast.makeText(context, getString(R.string.fill_the_fields), Toast.LENGTH_SHORT)
                    .show()
            } else {
                val title = binding.editTitleText.text.toString()
                val description = binding.editDescriptionText.text.toString()
                val noteModel = NoteModel(title = title, description = description)
                viewModel.insert(noteModel)
                Toast.makeText(context, getString(R.string.Saved), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addNoteFragment_to_startFragment)
            }
        }
    }
}