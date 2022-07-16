package com.example.gitapp.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import io.reactivex.rxjava3.subjects.PublishSubject

class RxButton : AppCompatButton {

    constructor(
        context: Context
    ) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)


    val clicks = PublishSubject.create<View>().also {
        setOnClickListener(null)
    }

    private fun rxClickListenerWrapper(l: OnClickListener?): (View) -> Unit = { view ->
        clicks.onNext(view)
        l?.onClick(view)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(rxClickListenerWrapper(l))
    }

}