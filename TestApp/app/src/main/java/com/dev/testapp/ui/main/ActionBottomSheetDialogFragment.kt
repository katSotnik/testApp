package com.dev.testapp.ui.main

import androidx.core.view.children
import com.dev.testapp.data.enums.EActionType
import com.dev.testapp.databinding.FragmentBottomSheetActionBinding
import com.dev.testapp.ui.base.BaseBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActionBottomSheetDialogFragment(
    private val actionClickListener: ActionClickListener
) : BaseBottomSheetDialogFragment<FragmentBottomSheetActionBinding>(FragmentBottomSheetActionBinding::inflate) {

    override fun observeView() {
        super.observeView()
        binding.container.children.forEach {
            it.setOnClickListener { item ->
                actionClickListener.onActionClicked(EActionType.valueOf(item?.tag.toString()))
                dismiss()
            }
        }
    }

    interface ActionClickListener {
        fun onActionClicked(value: EActionType)
    }
}