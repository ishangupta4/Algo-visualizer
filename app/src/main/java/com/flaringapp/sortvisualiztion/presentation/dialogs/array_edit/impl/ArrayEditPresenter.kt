package com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.impl

import android.os.Bundle
import androidx.core.text.isDigitsOnly
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.ArrayEditContract
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.ArrayEditContract.Companion.DATA_ARRAY_KEY
import com.flaringapp.sortvisualiztion.presentation.mvp.BasePresenter

class ArrayEditPresenter : BasePresenter<ArrayEditContract.ViewContract>(),
    ArrayEditContract.PresenterContract {

    private lateinit var listener: ArrayEditContract.ArrayEditParent

    private val array = ArrayList<Int>()

    override fun onCreate(arguments: Bundle?, savedInstanceState: Bundle?) {
        super.onCreate(arguments, savedInstanceState)
        array.addAll(arguments!!.getIntArray(DATA_ARRAY_KEY)?.toList() ?: emptyList())
    }

    override fun onStart() {
        super.onStart()
        view?.initText(array.format())
    }

    override fun init(listener: ArrayEditContract.ArrayEditParent) {
        this.listener = listener
    }

    override fun onAddPressed(text: String) {
        if (!text.isDigitsOnly() || text.isEmpty()) return

        val number = text.toInt()
        array.add(number)

        view?.addText(
            if (array.size == 1) number.toString()
            else " ; $number"
        )

        view?.clearInput()
    }

    override fun onSavePressed() {
        listener.onArrayEdited(array.toIntArray())
        view?.close()
    }

    override fun onCancelPressed() {
        view?.close()
    }

    private fun ArrayList<Int>.format(): String {
        return this.joinToString(
            separator = " ; "
        )
    }
}