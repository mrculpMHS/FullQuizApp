package com.example.extern.quizapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.question_list_item.view.*


class MainAdapter( var answerTitles: Array<String>, private val context: Context?): RecyclerView.Adapter<CustomViewHolder>() {


    private var listener: IMainActivity?=null

    override fun getItemCount(): Int {
        return answerTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.question_list_item, parent, false)
        return CustomViewHolder(cellForRow)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val answerTitle = answerTitles.get(position)

        holder.itemView.answerOptions?.text = answerTitle
        holder.itemView.setOnClickListener {
            listener?.onAnswerClick(answerTitle)


        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (context is IMainActivity) {
            listener = context
        }
    }

    fun update(answer:Array<String>) {
        answerTitles=answer
        notifyDataSetChanged()
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}
