package com.example.sleeptracker.sleepdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptracker.R
import com.example.sleeptracker.database.SleepDatabase
import com.example.sleeptracker.databinding.FragmentSleepDetailBinding


class SleepDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSleepDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_detail, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(requireArguments())
        // Create an instance of the ViewModel Factory.
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepDetailViewModelFactory(arguments.sleepNightKey, dataSource)


        val sleepDetailViewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(SleepDetailViewModel::class.java)

        binding.sleepDetailViewModel = sleepDetailViewModel


        binding.lifecycleOwner = this

        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner) {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment()
                )
                sleepDetailViewModel.doneNavigating()
            }
        }



        return binding.root
    }
}