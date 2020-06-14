package com.flaringapp.sortvisualiztion.app.di

import com.flaringapp.sortvisualiztion.data.managers.sort.SortManager
import com.flaringapp.sortvisualiztion.data.managers.sort.SortManagerImpl
import org.koin.dsl.module

val data_module = module {

    single { SortManagerImpl() as SortManager }

}