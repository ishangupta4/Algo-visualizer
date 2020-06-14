package com.flaringapp.sortvisualiztion.app.di

import com.flaringapp.sortvisualiztion.presentation.activities.main.MainContract
import com.flaringapp.sortvisualiztion.presentation.activities.main.impl.MainActivity
import com.flaringapp.sortvisualiztion.presentation.activities.main.impl.MainPresenter
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.ArrayEditContract
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.impl.ArrayEditDialog
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.impl.ArrayEditPresenter
import com.flaringapp.sortvisualiztion.presentation.fragments.create_array.CreateArrayContract
import com.flaringapp.sortvisualiztion.presentation.fragments.create_array.impl.CreateArrayFragment
import com.flaringapp.sortvisualiztion.presentation.fragments.create_array.impl.CreateArrayPresenter
import com.flaringapp.sortvisualiztion.presentation.fragments.intro.IntroContract
import com.flaringapp.sortvisualiztion.presentation.fragments.intro.impl.IntroFragment
import com.flaringapp.sortvisualiztion.presentation.fragments.intro.impl.IntroPresenter
import com.flaringapp.sortvisualiztion.presentation.fragments.sort.SortContract
import com.flaringapp.sortvisualiztion.presentation.fragments.sort.impl.SortFragment
import com.flaringapp.sortvisualiztion.presentation.fragments.sort.impl.SortPresenter
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.SortLogsContract
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.impl.SortLogsFragment
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.impl.SortLogsPresenter
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.SortMethodsContract
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.impl.SortMethodsFragment
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.impl.SortMethodsPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentation_module = module {

    scope(named<MainActivity>()) {
        scoped { MainPresenter() as MainContract.PresenterContract }
    }

    scope(named<IntroFragment>()) {
        scoped { IntroPresenter() as IntroContract.PresenterContract }
    }

    scope(named<CreateArrayFragment>()) {
        scoped { CreateArrayPresenter() as CreateArrayContract.PresenterContract }
    }

    scope(named<SortMethodsFragment>()) {
        scoped { SortMethodsPresenter() as SortMethodsContract.PresenterContract }
    }

    scope(named<SortFragment>()) {
        scoped { SortPresenter(get()) as SortContract.PresenterContract }
    }

    scope(named<SortLogsFragment>()) {
        scoped { SortLogsPresenter() as SortLogsContract.PresenterContract }
    }

    scope(named<ArrayEditDialog>()) {
        scoped { ArrayEditPresenter() as ArrayEditContract.PresenterContract }
    }

}