package yzl.swu.customizeview

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        CustomViewKt(this)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        ValueAnimator.ofFloat(0f,1f).apply {
            duration = 2000
            addUpdateListener {
                mRoundView.processRate = it.animatedValue as Float
                mRoundView.foregoundCircleColor
                Log.v("yzll","${it.animatedValue}")
            }
        }.start()
        return true
    }
}