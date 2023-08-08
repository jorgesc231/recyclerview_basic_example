package com.example.recyclerviewexample

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.RecyclerViewExampleApplication.Companion.wordList
import com.example.recyclerviewexample.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var listAdapter : ListAdapter

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvList = binding.rvList

        listAdapter = ListAdapter(wordList) {itemSelected(it)}
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(this.context)
        rvList.adapter = listAdapter

        if (wordList.isEmpty()) {
            binding.tvEmptyList.visibility = View.VISIBLE
            binding.rvList.visibility = View.GONE
        }
        else {
            binding.tvEmptyList.visibility = View.GONE
            binding.rvList.visibility = View.VISIBLE
        }

        binding.fab.setOnClickListener { view ->
            wordList.add("Word " + wordList.size)
            listAdapter.updateList(wordList)

            binding.rvList.visibility = View.VISIBLE
            binding.tvEmptyList.visibility = View.GONE
        }
    }

    private fun itemSelected(item_index: Int) {
        if (!wordList[item_index].contains("Clicked!")) {
            wordList[item_index] = "Clicked! " + wordList[item_index]
        }
        findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(item_index))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}