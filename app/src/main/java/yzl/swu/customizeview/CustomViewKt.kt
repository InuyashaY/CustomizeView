package yzl.swu.customizeview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.jar.Attributes
import kotlin.math.log

/**
 * Kotlin中如果一个类继承另一个类 并且父类中又多个构造方法 就使用参数最多的构造方法来构建父类
 * 使用参数较少的构造方法 作为 次构造
 *
 * 1.创建一个类继承于View 或者 View 的子类
 * 2、重写构造方法 （主要三个）
 * 3、onMeasure 测量尺寸
 * 4、onDraw     绘制内容
 *
 * 绘制四个基本组成部分：
 *      BitMap  图片
 *      Canvas  绘制类
 *      Paint   画笔
 *      Path    路径
 *
 *
 * 画板：Canvas  绘制类  提供各种绘制方法  draw**（）   线 圆 矩形等
 * 画笔：Paint     画笔粗细 颜色
 * 路径：Path
 * */
class CustomViewKt(context: Context,attrs:AttributeSet?,defStyleAttr:Int) : View(context,attrs,defStyleAttr) {
    //圆的宽高
    private var mWidth = 0
    private var mHeight = 0
    //圆的半径
    private var radius = 0f
    //画笔
    private val mPaint: Paint by lazy {
        Paint().apply {
//            color = Color.GREEN
//            shader = LinearGradient(mWidth/2.0f,0f,mWidth/2f,mHeight.toFloat(),Color.GREEN,Color.MAGENTA,Shader.TileMode.CLAMP)
            /**
             * BitMap 具体图片  图片id对应的真实数据
             * */
//            val img = BitmapFactory.decodeResource(resources,R.drawable.m)
//            shader = BitmapShader(img,Shader.TileMode.REPEAT,Shader.TileMode.MIRROR)
            style = Paint.Style.FILL
            strokeWidth = 10f
        }
    }

    //图片
    private val logo: Bitmap by lazy {
        BitmapFactory.decodeResource(resources,R.drawable.m)
    }
    //Path
    private val mPath:Path by lazy {
        Path().apply {
            moveTo(20f,30f)
//            lineTo(400f,30f)
            //二阶贝塞尔曲线   控制点+终点
            quadTo(210f,500f,400f,30f)
            //三阶    控制点1+控制点2+终点
            moveTo(400f,100f)
            cubicTo(500f,-200f,700f,350f,800f,100f)

            //圆弧
            moveTo(500f,400f)
            arcTo(RectF(100f,0f,900f,800f),0f,90f)
            close()
        }
    }



    //代码创建
    constructor(context: Context):this(context,null,0){
        Log.v("yzll","代码创建")
    }

    //xml创建
    constructor(context: Context,attrs: AttributeSet):this(context,attrs,0){
        Log.v("yzll","xml创建")
    }

    //重写onMeasure方法
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.v("yzll","OnMeasure")
    }

    //布局完毕
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = measuredWidth
        mHeight = measuredHeight
        radius = if (mWidth > mHeight) mHeight/2.0f else mWidth/2.0f
        Log.v("yzll","onSizeChanged")
    }

    //绘制draw()  绘制主体内容
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        setBackgroundColor(Color.DKGRAY)
        //画圆
//        canvas?.drawCircle(mWidth/2f,mHeight/2f,radius,mPaint)
        //绘制图片
//        canvas?.drawBitmap(logo,0f,0f,mPaint)
        //画线条
//        canvas?.drawLine(20f,0f,300f,0f,mPaint)
        //通过Path确定路径  绘制Path

        canvas?.drawPath(mPath,mPaint)
    }

    //dispatchDraw()  viewGroup绘制子视图
    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        Log.v("yzll","dispatchDraw")
    }

    //绘制前景
    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)
        Log.v("yzll","onDrawForeground")
    }
}