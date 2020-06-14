package com.flaringapp.sortvisualiztion.presentation.fragments.create_array.impl

import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.activities.main.MainContract
import com.flaringapp.sortvisualiztion.presentation.activities.main.navigation.Screen
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.impl.ArrayEditDialog
import com.flaringapp.sortvisualiztion.presentation.fragments.create_array.CreateArrayContract
import com.flaringapp.sortvisualiztion.presentation.mvp.BasePresenter
import com.flaringapp.sortvisualiztion.utils.format

class CreateArrayPresenter : BasePresenter<CreateArrayContract.ViewContract>(),
    CreateArrayContract.PresenterContract {

    private var appNavigation: MainContract.AppNavigation? = null

    private var array: ArrayList<Int> = ArrayList()

    override fun onStart() {
        super.onStart()
        view?.updateArrayText(array.format())
    }

    override fun release() {
        appNavigation = null
        super.release()
    }

    override fun init(appNavigation: MainContract.AppNavigation) {
        this.appNavigation = appNavigation
    }

    override fun onEditClicked() {
        if (array.size > MAX_EDITING_ELEMENTS) {
            view?.showWarningToast(
                view!!.viewContext!!.getString(R.string.edit_array_max_editing_count, MAX_EDITING_ELEMENTS)
            )
        } else {
            view?.showArrayEditDialog(
                ArrayEditDialog.newInstance(array.toIntArray())
            )
        }
    }

    override fun onRandomClicked() {
        array = randomArray()
        view?.updateArrayText(array.format())
    }

    override fun onArrayEdited(array: IntArray) {
        this.array = array.toCollection(ArrayList())
        view?.updateArrayText(array.format())
    }

    override fun onContinueClicked() {
        if (array.size < MIN_ELEMENTS) {
            view?.showWarningToast(view!!.viewContext!!.getString(R.string.too_small_array, MIN_ELEMENTS))
            return
        }

        appNavigation?.openScreen(Screen.SORT_METHODS, array.toIntArray())
    }

    private fun randomArray(): ArrayList<Int> {
        val size = (10000..30000).random()
        return IntArray(size) { (1 until 100).random() }.toCollection(ArrayList())
    }

    companion object {
        private const val MIN_ELEMENTS = 2
        private const val MAX_EDITING_ELEMENTS = 500
    }
}
