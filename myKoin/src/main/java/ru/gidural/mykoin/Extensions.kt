package ru.gidural.mykoin

import androidx.lifecycle.ViewModel

fun module(block: Module.() -> Unit): Module = Module().apply(block)

fun startKoin(block: MyKoin.() -> Unit) = MyKoin.apply(block)

inline fun <reified T : Any> get(): T = MyKoin.get(T::class)

inline fun <reified T : ViewModel> viewModel() = lazy { get<T>() }
