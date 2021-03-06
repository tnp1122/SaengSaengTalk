package com.example.saengsaengtalk

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saengsaengtalk.adapterHome.*
import com.example.saengsaengtalk.databinding.FragHomeBinding
import com.example.saengsaengtalk.fragmentAccount.FragmentLogin
import com.example.saengsaengtalk.fragmentBaedal.FragmentBaedalAdd
import com.example.saengsaengtalk.fragmentBaedal.FragmentBaedal
import com.example.saengsaengtalk.fragmentBaedal.FragmentBaedalPost
import com.example.saengsaengtalk.fragmentFreeBoard.FragmentFreeBoard
import com.example.saengsaengtalk.fragmentFreeBoard.FragmentFreeBoardPost
import com.example.saengsaengtalk.fragmentKara.FragmentKara
import com.example.saengsaengtalk.fragmentTaxi.FragmentTaxi
import com.example.saengsaengtalk.fragmentTaxi.FragmentTaxiAdd
import com.example.saengsaengtalk.fragmentTaxi.FragmentTaxiPost
import java.time.LocalDateTime

class FragmentHome :Fragment() {

    private var mBinding: FragHomeBinding? = null
    private val binding get() = mBinding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragHomeBinding.inflate(inflater, container, false)

        refreshView()

        binding.lytHomeBaedallist.setOnClickListener { setFrag(FragmentBaedal(), fragIndex=1) }
        binding.lytHomeTaxilist.setOnClickListener { setFrag(FragmentTaxi(), fragIndex=2) }
        binding.lytHomeKaralist.setOnClickListener { setFrag(FragmentKara(), fragIndex=3) }
        binding.lytHomeFreeboard.setOnClickListener { setFrag(FragmentFreeBoard(), fragIndex=4) }
        //binding.lytHomeClubboard.setOnClickListener { setFrag(FragmentClubBoard()) }

        return binding.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun refreshView() {
        binding.btnOption.setOnClickListener { setFrag(FragmentLogin(), fragIndex=-1) }

        /* ?????? */
        binding.btnBaedalAdd.setOnClickListener { setFrag(FragmentBaedalAdd(), fragIndex=1) }

        val baedalList = arrayListOf(
            BaedalPre(LocalDateTime.now(), "????????????", 2, 10000, 1),
            BaedalPre(LocalDateTime.now(), "BBQ", 3, 10000, 2),
            BaedalPre(LocalDateTime.now(), "?????????", 2, 10000, 3),
            BaedalPre(LocalDateTime.now(), "??????", 3, 9000, 4),
            BaedalPre(LocalDateTime.now(), "??????", 4, 6000, 5)
        )

        binding.rvBaedal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBaedal.setHasFixedSize(true)
        val baedalAdapter = BaedalPreAdapter(baedalList)
        binding.rvBaedal.adapter = baedalAdapter

        baedalAdapter.setItemClickListener(object: BaedalPreAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Toast.makeText(v.context, "${baedalList[position].postNum}???", Toast.LENGTH_SHORT).show()
                Log.d("?????????????????? ?????????", "${baedalList[position].postNum}")
                setFrag(FragmentBaedalPost(), mapOf("postNum" to baedalList[position].postNum.toString()), fragIndex=1)
            }
        })
        //baedalAdapter.notifyDataSetChanged()


        /* ?????? */
        binding.btnTaxiAdd.setOnClickListener { setFrag(FragmentTaxiAdd(), fragIndex=1) }
        val taxiList = arrayListOf(
            TaxiPre(LocalDateTime.now(), "?????????", "?????????", 1, 6600, 1),
            TaxiPre(LocalDateTime.now(), "?????????", "?????????", 2, 6600, 2),
            TaxiPre(LocalDateTime.now(), "?????????", "?????????", 3, 6600, 3),
            TaxiPre(LocalDateTime.now(), "?????????", "?????????", 3, 6600, 4),
            TaxiPre(LocalDateTime.now(), "?????????", "?????????", 1, 6600, 5)
        )
        val taxiAdapter = TaxiPreAdapter(taxiList)
        binding.rvTaxi.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTaxi.setHasFixedSize(true)
        binding.rvTaxi.adapter = taxiAdapter

        taxiAdapter.setItemClickListener(object: TaxiPreAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Toast.makeText(v.context, "${taxiList[position].postNum}???", Toast.LENGTH_SHORT).show()
                Log.d("?????????????????? ?????? ?????????", "${taxiList[position].postNum}")
                setFrag(FragmentTaxiPost(), mapOf("postNum" to taxiList[position].postNum.toString()), fragIndex=2)
            }
        })
        taxiAdapter.notifyDataSetChanged()


        /* ????????? */
        val karaList = arrayListOf(
            KaraPre(1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), true),
            KaraPre(5, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), false),
            KaraPre(2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), true),
            KaraPre(6, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), false),
            KaraPre(3, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), true),
            KaraPre(7, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), true),
            KaraPre(4, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), false)
        )
        binding.rvKara.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvKara.setHasFixedSize(true)
        binding.rvKara.adapter = KaraPreAdapter(karaList)


        /* ??????????????? */
        val freeBoardList = arrayListOf(
            BoardPre("????????????????????????.", LocalDateTime.now()),
            BoardPre("????????????????????????.222", LocalDateTime.now()),
            BoardPre("????????????????????????.33333", LocalDateTime.parse("2022-04-04T15:10:00"))
        )
        binding.rvFreeBoard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvFreeBoard.setHasFixedSize(true)

        val freeBoardAdapter = BoardPreAdapter(freeBoardList)
        binding.rvFreeBoard.adapter = freeBoardAdapter

        freeBoardAdapter.setItemClickListener(object: BoardPreAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                Log.d("?????????????????? ?????????", "${baedalList[position].postNum}")
                setFrag(FragmentFreeBoardPost(), mapOf("postNum" to baedalList[position].postNum.toString()), fragIndex=4)
            }
        })


        /* ??????????????? */
        binding.lytHomeClubboard.setVisibility(View.GONE)
        /*val clubBoardList = arrayListOf(
            ClubBoardPre("??????????????????????????????.", LocalDateTime.now()),
            ClubBoardPre("????????????????????????.222", LocalDateTime.now()),
            ClubBoardPre("????????????????????????.33333", LocalDateTime.parse("2022-04-05T00:00:01")),
            ClubBoardPre("????????????????????????.3333344", LocalDateTime.parse("2022-04-04T23:59:59")),
        )
        binding.rvClubBoard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvClubBoard.setHasFixedSize(true)
        binding.rvClubBoard.adapter = ClubBoardPreAdapter(clubBoardList)*/

    }

    fun setFrag(fragment: Fragment, arguments: Map<String, String>? = null, fragIndex: Int) {
        println("setIndex = ${fragIndex}")
        val mActivity = activity as MainActivity
        mActivity.setFrag(fragment, arguments, fragIndex=fragIndex)
    }
}