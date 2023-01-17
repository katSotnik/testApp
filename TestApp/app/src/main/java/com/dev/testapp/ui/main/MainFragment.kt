package com.dev.testapp.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.dev.testapp.Constants.PUSH_NOTIFICATION_MODEL
import com.dev.testapp.R
import com.dev.testapp.data.enums.EActionType
import com.dev.testapp.data.models.NotificationModel
import com.dev.testapp.databinding.FragmentMainBinding
import com.dev.testapp.extensions.parcelable
import com.dev.testapp.services.CallReceiver
import com.dev.testapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
    ActionBottomSheetDialogFragment.ActionClickListener {

    companion object {
        private const val TAG = "SortBottomSheetDialogFragment"
    }

    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
        initRegisterForActivityResult()
        initNotificationEvent()
    }

    private fun initRegisterForActivityResult() {
        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let {
                        callLogic(it)
                    }
                }
            }
    }

    private fun initNotificationEvent() {
        val notificationModel: NotificationModel? = arguments?.parcelable(PUSH_NOTIFICATION_MODEL)
        if (notificationModel != null) {
            doCall()
        }
    }

    override fun observeView() {
        super.observeView()
        binding.btAction.setOnClickListener {
            ActionBottomSheetDialogFragment(this).show(
                parentFragmentManager,
                TAG
            )
        }
    }

    override fun onActionClicked(value: EActionType) {
        when (value) {
            EActionType.ANIMATION -> doAnimation()
            EActionType.TOAST -> doToast(getString(R.string.toast_message))
            EActionType.CALL -> doCall()
            EActionType.NOTIFICATION -> doNotification()
            else -> {}
        }
    }

    private fun doAnimation() {
        binding.root.transitionToStart()
        binding.root.transitionToEnd()
    }

    private fun doToast(text: String) {
        Toast.makeText(activity, text, LENGTH_SHORT).show()
    }

    @SuppressLint("IntentReset")
    private fun doCall() {
        val pickContact = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        pickContact.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        startForResult.launch(pickContact)
    }

    private fun doNotification() {
        CallReceiver().showNotification(activity, getString(R.string.notification_message))
    }

    @SuppressLint("Recycle")
    private fun callLogic(it: Uri) {
        val contactData: Uri = it
        val cursor: Cursor? =
            activity?.contentResolver?.query(contactData, null, null, null, null)
        cursor?.let { c ->
            if (c.moveToFirst()) {
                val contactDisplayName = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                doToast(c.getString(contactDisplayName))
            }
        }
    }
}