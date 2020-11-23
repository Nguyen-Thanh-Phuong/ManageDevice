package com.danang.managedevice.ui.search

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.danang.managedevice.Controller.CTTB.CTTB_Adapter
import com.danang.managedevice.Model.QL_TB_CT.SendDT_CTTB
import com.danang.managedevice.Controller.QLTB_Adapter
import com.danang.managedevice.Controller.SendDataMdToView
import com.danang.managedevice.Model.QLTB.QLTB_Model
import com.danang.managedevice.Model.QL_TB_CT.QL_TB_CT_Model
import com.danang.managedevice.Object.QLTB
import com.danang.managedevice.Object.QL_TBi_CT
import com.danang.managedevice.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [Result_Search_QRFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Result_Search_QRFragment : Fragment(),SendDataMdToView,
    SendDT_CTTB {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private lateinit var root:View;
    private lateinit var listQLTB:ListView
    private lateinit var listCTTB:ListView
    private lateinit var btnScroll:ImageButton
    private lateinit var maql:String;
    var drop_down = true;
    var rope:YoYo.YoYoString?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root =inflater.inflate(R.layout.fragment_result__search__q_r, container, false)

        listQLTB = root.findViewById(R.id.list_QLTB)
        listCTTB = root.findViewById(R.id.list_CTTB)

        btnScroll = root.findViewById(R.id.btnScroll);
        btnScroll.setOnClickListener {
            //set Property button
            btnScroll.isEnabled = false;
            btnScroll.setImageResource(if(drop_down)R.drawable.up_chevron else R.drawable.arrow_down);
            //stop old animation
            if(rope!=null)
                rope?.stop(true);

            //set animation run now
        rope = YoYo.with(if(drop_down)Techniques.FadeOutLeft else Techniques.BounceInRight)
            .duration(1000)
            .pivot(YoYo.CENTER_PIVOT,YoYo.CENTER_PIVOT)
            .interpolate(AccelerateDecelerateInterpolator())
            .withListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(p0: Animator?) {

                }

                override fun onAnimationEnd(p0: Animator?) {
                    if(drop_down)listQLTB.visibility = View.GONE;
                    btnScroll.isEnabled = true;
                    drop_down = !drop_down;
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {
                    if(!drop_down)listQLTB.visibility = View.VISIBLE;
                }

            }).playOn(listQLTB);
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        try
        {
            if(arguments!=null)
            {
                val args = Result_Search_QRFragmentArgs.fromBundle(requireArguments());
                maql = args.maql;
            }
        }catch (ex:Exception)
        {
            return;
        }
        root.findViewById<TextView>(R.id.textView);
        QLTB_Model.instances.apply {
            sendDataToView = this@Result_Search_QRFragment
            exec(QLTB_Model.State.GET_WITH_CODE, QLTB(maql));
        }
        QL_TB_CT_Model.instances.apply {
            exec(QL_TB_CT_Model.State.GET_WITH_CODE, QL_TBi_CT("","",maql,"","","","","",""),this@Result_Search_QRFragment);
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDataQLTB() {

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            Result_Search_QRFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }

    }

    override fun sendData(qltb: QLTB) {
        val adapterQLTB = QLTB_Adapter.getAdapter(qltb,requireContext())
        listQLTB.adapter = adapterQLTB;
    }

    override fun sendData(any: Any) {
        val list = any as MutableList<QL_TBi_CT>
        val adapter = CTTB_Adapter(requireContext(),list);
        listCTTB.adapter = adapter
    }
}

