package com.example.mathpractice.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.mathpractice.R


class ProblemAdapter(var lessonList : ArrayList<AnswerModel>) : RecyclerView.Adapter<ProblemAdapter.ProblemViewHolder>() {

    private lateinit var mListener: OnItemClickedListener

    interface OnItemClickedListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickedListener(listener: OnItemClickedListener) {
        mListener = listener
    }

    inner class ProblemViewHolder(view: View, listener: OnItemClickedListener) :
        RecyclerView.ViewHolder(view) {
        var questionNum: TextView = view.findViewById(R.id.questionNumberTV)
        val linearLayout: LinearLayout = view.findViewById(R.id.linearLayoutET)
        var answerET: EditText = view.findViewById(R.id.answerET)

        var checkButton: ImageButton = view.findViewById(R.id.imageButton)
        var result: TextView = view.findViewById(R.id.resultTV)
        var attempt: TextView = view.findViewById(R.id.attemptTV)

        init {

            checkButton.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }



            view.setOnClickListener{
                //listener.onItemClick(adapterPosition, isCorrect)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.problemitemlayout, parent, false)
        return ProblemViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.questionNum.text = lessonList[position].number


        if (lessonList[position].attemptList.isNullOrEmpty()) {
            holder.attempt.text = ""
        } else {

            holder.attempt.text = lessonList[position].attemptList.toString()

            holder.result.text = "Retry"
            holder.result.setTextColor(ContextCompat.getColor(holder.result.context, R.color.red))

        }


        val numAnswers = lessonList[position].answer.size

        //Add EditTexts dynamically if there is more than one answer
        if (!lessonList[position].answered) {


            val tempAns: ArrayList<Any> = arrayListOf()

            tempAns.add(holder.answerET.text)

            if (numAnswers > 1) {

                var ansNum = 1

                while (ansNum < numAnswers) {

                    val et = EditText(holder.linearLayout.context)
                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                    )
                    lp.setMargins(24, 0, 0, 0)
                    lp.height = 108
                    et.layoutParams = lp
                    et.tag = "answerET$position$ansNum"
                    et.hint = " Enter answer ${ansNum + 1}"
                    et.setPadding(0, 4, 8, 2)

                    et.setSelection(et.text.length)

                    holder.linearLayout.addView(et)

                    ansNum += 1

                }
            }
        } else {

            holder.linearLayout.removeAllViews()
            holder.result.text = "Pass!"
            holder.result.setTextColor(ContextCompat.getColor(holder.result.context, R.color.passgreen))
            holder.checkButton.isEnabled = false
        }

        if (lessonList[position].secondary?.isNotEmpty() == true) {

            val et = EditText(holder.linearLayout.context)
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )
            lp.setMargins(24, 0, 0, 0)
            lp.height = 108
            et.layoutParams = lp
            et.textSize = 12F
            et.tag = "secondaryET$position"
            et.hint = " Enter answer for parent to check"
            et.setPadding(0, 4, 8, 2)

            et.setSelection(et.text.length)

            holder.linearLayout.addView(et)

            if (lessonList[position].answered) {

                holder.linearLayout.removeAllViews()
            }
        }

    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

}