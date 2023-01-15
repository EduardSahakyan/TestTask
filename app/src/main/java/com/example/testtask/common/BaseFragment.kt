package com.example.testtask.common

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel

}