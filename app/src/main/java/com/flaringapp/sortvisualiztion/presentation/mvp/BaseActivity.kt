package com.flaringapp.sortvisualiztion.presentation.mvp

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.flaringapp.sortvisualiztion.presentation.activities.main.BackClickListener

abstract class BaseActivity<T : IBasePresenter<*>> : AppCompatActivity(), IBaseView {

    abstract val presenter: T

    override val viewContext: Context? get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(intent.extras, savedInstanceState)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        presenter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        for (fragment in supportFragmentManager.fragments.reversed()) {
            if (fragment is BackClickListener) {
                if (fragment.onBackClicked()) {
                    return
                }
            }
        }

        super.onBackPressed()
    }

    override fun onDestroy() {
        presenter.release()
        super.onDestroy()
    }
}