package com.leapi.beagletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import br.com.zup.beagle.annotation.BeagleComponent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.utils.toView
import br.com.zup.beagle.view.BeagleActivity
import br.com.zup.beagle.view.ServerDrivenState
import br.com.zup.beagle.widget.core.AlignContent
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.ui.Text
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_app_beagle.*

@BeagleComponent
class activityAppBeagle : BeagleActivity() {
    private val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progress_bar) }
    private val mToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.custom_toolbar) }
    private val mFrameLayout: FrameLayout by lazy { findViewById<FrameLayout>(R.id.server_driven_container)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_beagle)
        mFrameLayout.addView(TestScreen().toView(this))
    }

    override fun getServerDrivenContainerId(): Int = R.id.server_driven_container

    //override fun getToolbar(): Toolbar = custom_toolbar
    override fun getToolbar(): Toolbar = mToolbar

    override fun onServerDrivenContainerStateChanged(state: ServerDrivenState) {
        if (state is ServerDrivenState.Loading) {
            progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE
        } else if (state is ServerDrivenState.Error) {
            Snackbar.make(window.decorView, "Error", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun TestScreen() = Screen(
        child = Container(
            children = listOf(
                Text(
                    text = "Hello Beagle"
                ).applyFlex(
                    Flex(
                        margin = EdgeValue(
                            top = 16.unitReal()
                        ),
                        alignContent = AlignContent.CENTER
                    )
                ),
                Text(
                    text = "Beagle is a cross-platform framework which provides usage of the " +
                            "Server-Driven UI concept, natively in iOS, Android and Web applications. " +
                            "By using Beagle, your team could easily change application's layout and" +
                            " data by just changing backend code."
                ).applyFlex(
                    Flex(
                        margin = EdgeValue(
                            start = 16.unitReal(),
                            end = 16.unitReal(),
                            top = 20.unitReal()
                        )
                    )
                )
            )
        )
    )

}