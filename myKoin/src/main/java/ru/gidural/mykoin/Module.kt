package ru.gidural.mykoin

import androidx.lifecycle.ViewModel

class Module {

    inline fun <reified T : Any> single(noinline block: () -> T) {
        MyKoin.put(T::class, SingleFabric(block))
    }

    inline fun <reified T : Any> factory(noinline block: () -> T) {
        MyKoin.put(T::class, FactoryFabric(block))
    }

    inline fun <reified T : ViewModel> viewModel(noinline block: () -> T) {
        MyKoin.put(T::class, SingleFabric(block))
    }

}

