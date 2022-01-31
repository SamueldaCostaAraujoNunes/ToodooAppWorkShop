package com.samuelnunes.too_dooapp.presentation.todo_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.samuelnunes.too_dooapp.databinding.FragmentTodoDetailsBinding
import com.samuelnunes.too_dooapp.presentation.MainViewModel
import com.samuelnunes.too_dooapp.presentation.ScreenState


class TodoDetailsFragment : Fragment() {

    private val args: TodoDetailsFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentTodoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.setState(ScreenState.SAVE)
    }
}