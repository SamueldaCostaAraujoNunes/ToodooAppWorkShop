package com.samuelnunes.too_dooapp.presentation.todo_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.samuelnunes.too_dooapp.R
import com.samuelnunes.too_dooapp.databinding.FragmentTodoDetailsBinding

class TodoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTodoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}