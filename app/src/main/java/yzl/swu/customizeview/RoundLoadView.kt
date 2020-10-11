package yzl.swu.customizeview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class RoundLoadView: View {
    //进度颜色
    var foregoundCircleColor = Color.GREEN
        set(value) {
            field = value
            foregroundPaint.color = value
        }
    //背景颜色
    var backgroundCircleColor = Color.CYAN
        set(value) {
            field = value
            backgroundPaint.color = value
        }
    //字体颜色
    var textColor = Color.BLACK
        set(value) {
            field = value
            textPaint.color = value
        }
    //圆环宽度
    private var cWidth = 30f
    //圆环属性
    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f
    //进度
    var processRate = 0.2f
        set(value) {
            field = value
            invalidate()
        }
    private val backgroundPaint by lazy {
        Paint().apply {
            color = Color.YELLOW
            style = Paint.Style.STROKE
            strokeWidth = cWidth
        }
    }
    private val foregroundPaint by lazy {
        Paint().apply {
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeWidth = cWidth
        }
    }
    private val textPaint by lazy {
        Paint().apply {
            color = Color.GREEN
            style = Paint.Style.FILL
            strokeWidth = 5f
            textAlign = Paint.Align.CENTER
            textSize = 90f
        }
    }
    //代码创建
    constructor(context: Context) : super(context)
    //xml创建 解析属性
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        //解析属性
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.RoundLoadView)
        backgroundCircleColor = typeArray.getColor(R.styleable.RoundLoadView_processBackgroundColor,Color.BLACK)
        foregoundCircleColor = typeArray.getColor(R.styleable.RoundLoadView_processForegroundColor,Color.GREEN)
        textColor = typeArray.getColor(R.styleable.RoundLoadView_android_textColor,Color.BLACK)
        typeArray.recycle()



    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = Math.max(w,h)/2f - cWidth/2f
        centerY = h/2f
        centerX = w/2f
    }
    override fun onDraw(canvas: Canvas?) {
        //背景
        canvas?.drawCircle(centerX,centerY,radius,backgroundPaint)
        //前景
        canvas?.drawArc(centerX-radius,centerY-radius,centerX+radius,centerY+radius,-90f,processRate*360f,false,foregroundPaint)
        //文字
        var fontY = centerY+((textPaint.fontMetrics.descent-textPaint.fontMetrics.ascent)/2f-textPaint.fontMetrics.descent)
        canvas?.drawText(String.format("%.1f",processRate*100)+"%",centerX,fontY,textPaint)
    }
}