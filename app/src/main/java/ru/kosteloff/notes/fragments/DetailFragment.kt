package ru.kosteloff.notes.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.kosteloff.notes.R
import ru.kosteloff.notes.databinding.FragmentDetailBinding
import ru.kosteloff.notes.tablemodel.NoteModel
import ru.kosteloff.notes.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateNote()
        initTitleAndDesc()
        setHasOptionsMenu(true)
    }

    private fun updateNote() {
        binding.buttonUpdate.setOnClickListener {
            val update = NoteModel(
                id = args.currentItem.id,
                title = binding.updateTitleText.text.toString(),
                description = binding.updateDescriptionText.text.toString()
            )
            viewModel.update(update)
            findNavController().navigate(R.id.action_detailFragment_to_startFragment)
        }
    }

    private fun initTitleAndDesc() {
        binding.updateTitleText.setText(args.currentItem.title)
        binding.updateDescriptionText.setText(args.currentItem.description)
    }

    private fun bannerAboutDelete() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("${getText(R.string.Removal)} \"${args.currentItem.title}\"")
        alertDialog.setMessage("${getText(R.string.Warning_removal)}")

        alertDialog.setNegativeButton(getString(R.string.No)) { _, _ -> }

        alertDialog.setPositiveButton(getString(R.string.Yes)) { _, _ ->
            viewModel.delete(args.currentItem)
            Toast.makeText(context, getString(R.string.Deleted), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_detailFragment_to_startFragment)
        }
        alertDialog.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            bannerAboutDelete()
        }
        return super.onOptionsItemSelected(item)
    }
}