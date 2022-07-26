package ru.gidural.mykoin

sealed class Fabric<out T: Any>(protected val block: () -> T) {
    abstract fun get(): T
}

class SingleFabric<out T: Any>(block: () -> T) : Fabric<T>(block) {
    private val obj: T by lazy(block)
    override fun get(): T = obj
}

class FactoryFabric<out T: Any>(block: () -> T) : Fabric<T>(block) {
    override fun get(): T = block()
}