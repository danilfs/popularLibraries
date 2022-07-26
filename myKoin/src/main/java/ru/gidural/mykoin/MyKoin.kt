package ru.gidural.mykoin

import android.content.Context
import kotlin.reflect.KClass

object MyKoin {

    private val storage = HashMap<KClass<out Any>, Fabric<Any>>()

    fun androidContext(context: Context) {
        put(Context::class, SingleFabric {
            context
        })
    }

    fun modules(vararg list: Module) {
        // does nothing
        // calling for initialization modules
    }

    fun <T : Any> get(clazz: KClass<T>): T {
        val fabric = storage[clazz]
        if (fabric != null) {
            return fabric.get() as T
        } else {
            throw IllegalStateException("Dependency not found")
        }
    }

    fun <T : Any> put(clazz: KClass<out T>, fabric: Fabric<T>) {
        storage[clazz] = fabric
    }

}