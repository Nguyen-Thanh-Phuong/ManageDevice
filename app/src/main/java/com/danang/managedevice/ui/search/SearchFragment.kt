package com.danang.managedevice.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.danang.managedevice.Model.QL_TB_CT.QL_TB_CT_Model
import com.danang.managedevice.Object.QL_TBi_CT
import com.danang.managedevice.R
import com.google.zxing.integration.android.IntentIntegrator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var root:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_search, container, false)
        setData()
        return root;
    }

    private fun setData() {
        root.findViewById<Button>(R.id.btnScanCode).setOnClickListener {
            scanCode()
        }
    }

    private fun scanCode() {
        val intent = IntentIntegrator.forSupportFragment(this)

        intent.captureActivity = CaptureAct::class.java
        intent.setOrientationLocked(false)
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            intent.setPrompt("Please Scanning Product Code");
            intent.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var intentIntegrator = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentIntegrator!=null && intentIntegrator.contents!=null)
        {
            Toast.makeText(requireContext(),intentIntegrator.contents,Toast.LENGTH_SHORT).show()
            var action:SearchFragmentDirections.ActionSearchFragmentToResultSearchQRFragment = SearchFragmentDirections.actionSearchFragmentToResultSearchQRFragment(intentIntegrator.contents);
            val nav= Navigation.findNavController(root)
            nav.navigate(action)
        }
        else
            super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}