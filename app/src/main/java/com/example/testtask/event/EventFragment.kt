package com.example.testtask.event

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.entities.EventState
import com.example.testtask.R
import com.example.testtask.common.BaseFragment
import com.example.testtask.common.ViewModelFactory
import com.example.testtask.databinding.FragmentEventBinding
import com.example.testtask.di.Injector
import java.util.Calendar
import javax.inject.Inject

class EventFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private var _binding: FragmentEventBinding? = null
    private val binding: FragmentEventBinding
        get() = _binding ?: throw RuntimeException("EventBinding is null")

    override val viewModel: EventViewModel by viewModels { factory }

    private val args: EventFragmentArgs by navArgs()

    init {
        Injector.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEventBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observers()
    }

    private fun observers() {
        viewModel.insertedEvent.observe(viewLifecycleOwner) {
            navigateUp()
        }
        viewModel.errorEvent.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(R.string.weather_error, it), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupViews() = with(binding) {
        val item = args.event
         item?.let {
            event = it
            btnRemove.visibility = View.VISIBLE
             when (it.state) {
                 EventState.DEFAULT -> radioDefault.isChecked = true
                 EventState.VISITED -> radioVisited.isChecked = true
                 EventState.MISSED -> radioMissed.isChecked = true
             }
        }
        val id = item?.id ?: 0

        btnSave.setOnClickListener {
            val state = when (radioGroup.checkedRadioButtonId) {
                radioDefault.id -> EventState.DEFAULT
                radioVisited.id -> EventState.VISITED
                radioMissed.id -> EventState.MISSED
                else -> throw IllegalArgumentException("Unknown state")
            }
            viewModel.isFieldsCorrect(
                name = etName.text.toString(),
                description = etDescription.text.toString(),
                date = etDate.text.toString(),
                time = etTime.text.toString(),
                place = etPlace.text.toString(),
                id = id,
                state = state
            )
        }

        btnRemove.setOnClickListener {
            viewModel.removeEvent(id)
            navigateUp()
        }

        val calendar = Calendar.getInstance()
        etDate.setOnClickListener {
            val cYear = calendar.get(Calendar.YEAR)
            val cMonth = calendar.get(Calendar.MONTH)
            val cDay = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    etDate.setText(getString(R.string.date_formatter, year, monthOfYear + 1, dayOfMonth))
                },
                cYear,
                cMonth,
                cDay
            )
            datePickerDialog.show()
        }
        etTime.setOnClickListener {
            val cHour = calendar.get(Calendar.HOUR_OF_DAY)
            val cMinute= calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(requireContext(),
                { _, hour, minute ->
                    etTime.setText(getString(R.string.time_formatter, hour, minute))
                },
                cHour,
                cMinute,
                true)
            timePickerDialog.show()
        }

    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

}