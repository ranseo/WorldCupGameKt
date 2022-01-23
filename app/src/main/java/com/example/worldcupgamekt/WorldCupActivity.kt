package com.example.worldcupgamekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.worldcupgamekt.databinding.ActivityWorldCupBinding
import java.util.*
import kotlin.collections.ArrayList

class WorldCupActivity : AppCompatActivity() {

    var wBinding: ActivityWorldCupBinding? = null
    val binding get() = wBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wBinding = ActivityWorldCupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = Handler(Looper.getMainLooper())

        val worldCupList: ArrayList<Int> = arrayListOf<Int>(
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6,
            R.drawable.a7,
            R.drawable.a8,
            R.drawable.a9,
            R.drawable.a10,
            R.drawable.a11,
            R.drawable.a12,
            R.drawable.a13,
            R.drawable.a14,
            R.drawable.a15,
            R.drawable.a16
        )

        worldCupList.shuffle()


        val tmp: ArrayList<Int> = arrayListOf()
        setChoiceFrag(worldCupList[0], worldCupList[1])

        binding.fmLeft.setOnClickListener {
            addAndRemove(worldCupList, tmp, 0)
            if (worldCupList.isEmpty()) ifEmptyDoIt(worldCupList, tmp)
            if (worldCupList.size == 1) {
                disappearFramelayout(0)
                setWinnerFrag(worldCupList[0])
            } else {
                //disappearFramelayout(1)
                setFragLayoutClickListener(1)

                handler.postDelayed(
                    {

                        addMatchScore()
                        setChoiceFrag(worldCupList[0], worldCupList[1])
                        //appearFramelayout(1)
                    }, 850
                )
            }
        }

        binding.fmRight.setOnClickListener {
            addAndRemove(worldCupList, tmp, 1)
            if (worldCupList.isEmpty()) ifEmptyDoIt(worldCupList, tmp)
            if (worldCupList.size == 1) {
                disappearFramelayout(0)
                setWinnerFrag(worldCupList[0])
            } else {
                //disappearFramelayout(2)
                setFragLayoutClickListener(2)

                handler.postDelayed(
                    {

                        addMatchScore()
                        setChoiceFrag(worldCupList[0], worldCupList[1])
                        //appearFramelayout(2)
                    }, 850
                )

            }


        }
    }

    private fun setChoiceFrag(image1: Int, image2: Int) {
        val ft = supportFragmentManager.beginTransaction()
        val ft2 = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fm_left, ChoiceFragment(image1)).commit()
        ft2.replace(R.id.fm_right, ChoiceFragment(image2)).commit()
    }

    private fun setWinnerFrag(image: Int) {
        val ft = supportFragmentManager.beginTransaction()

        ft.replace(R.id.fl_winner, WinnerFrag(image)).commit()
    }


//    private fun disappearBtn() {
//        binding.ivChoice1.visibility= View.GONE
//        binding.ivChoice2.visibility= View.GONE
//    }


    private fun disappearFramelayout(idx: Int) {
        when (idx) {
            //우승선택 - 전부 없애기
            0 -> {
                binding.fmLeft.visibility = View.GONE
                binding.fmRight.visibility = View.GONE
//            }
//            //1일 때 - 1선택, 오른쪽만 없애기
//            1 -> {
//                binding.fmRight.visibility = View.GONE
//            }
//            //2일 때 - 2선택, 왼쪽만 없애기
//            2 -> {
//                binding.fmLeft.visibility = View.GONE
            }
        }
    }


//    private fun appearFramelayout(idx: Int) {
//        when(idx) {
//            1-> {
//                binding.fmRight.visibility = View.VISIBLE
//            }
//            2-> {
//                binding.fmLeft.visibility = View.VISIBLE
//            }
//        }
//    }


    private fun addAndRemove(worldCupList: ArrayList<Int>, tmp: ArrayList<Int>, idx: Int) {
        tmp.add(worldCupList[idx])

        for (i in 0..1) {
            worldCupList.removeAt(0)
        }
    }

    private fun ifEmptyDoIt(worldCupList: ArrayList<Int>, tmp: ArrayList<Int>) {
        worldCupList.addAll(tmp)
        tmp.clear()
        worldCupList.shuffle()

        if (worldCupList.size != 1) {
            val matchNum = (binding.tvMatch.text.toString().split("강")[0].toInt()) / 2

            //2강 부터는 2강이라고 표현하는 대신 결승전이라고 표현.
            if (matchNum == 2) {
                binding.tvMatch.text = "결승전"
            } else {
                binding.tvMatch.text = "${matchNum}강"
            }

            val matchInfo =
                (binding.tvNumber.text.toString().split("/")[1].removeSuffix(")").toInt()) / 2

            binding.tvNumber.text = "(0/${matchInfo})"
        } else {
            binding.tvMatch.visibility = View.GONE
            binding.tvNumber.visibility = View.GONE

            binding.tvMatchName.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

        }
    }

    private fun addMatchScore() {
        var matchInfoMax =
            (binding.tvNumber.text.toString().split("/")[1].removeSuffix(")").toInt())
        var matchInfo = (binding.tvNumber.text.toString().split("/")[0].removePrefix("(").toInt())
        matchInfo++

        binding.tvNumber.text = "(${matchInfo}/${matchInfoMax})"
    }


    private fun setFragLayoutClickListener(idx: Int) {
        when (idx) {
            1 -> {
                //if(binding.mlMotion1.currentState==R.id.left_end) setTransition(binding.mlMotion1.currentState, R.id.left_frag)
                setTransition(binding.mlMotion1.currentState, R.id.left_choice)

            }
            2 -> {
                //if(binding.mlMotion2.currentState==R.id.right_choice_frag) setTransition(binding.mlMotionlayout.currentState, R.id.right_frag)
                setTransition(binding.mlMotion1.currentState, R.id.right_choice)
            }
        }
    }

    private fun setTransition(startState: Int, endState: Int) {
        binding.mlMotion1.setTransition(startState, endState)
        binding.mlMotion1.setTransitionDuration(200)
        binding.mlMotion1.transitionToEnd()
    }
}