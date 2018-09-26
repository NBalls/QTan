package driver.chao.com.qtan.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

import driver.chao.com.qtan.R

/**
 * 简单环形进度条
 */
class SimpleRoundProgress @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {
    private val paint: Paint // 画笔对象的引用
    private val roundColor: Int // 圆环的颜色
    private val roundWidth: Float // 圆环的宽度
    private val progressColor: Int // 圆环进度的颜色
    private val progressWidth: Float // 圆环进度的宽度
    private var max: Int = 0 // 最大进度
    private val style: Int // 进度的风格，实心或者空心
    private val startAngle: Int // 进度条起始角度
    /**
     * 获取进度
     *
     * @return int 当前进度值
     */
    /**
     * 设置进度，此为线程安全控件
     *
     * @param progress 进度值
     */
    @get:Synchronized // 刷新界面调用postInvalidate()能在非UI线程刷新
    var progress: Int = 0
        @Synchronized set(progress) {
            var progress = progress
            if (progress < 0) {
                throw IllegalArgumentException("progress not less than 0")
            }
            if (progress > max) {
                progress = max
            }
            field = progress
            postInvalidate()
        } // 当前进度

    init {

        paint = Paint()

        // 读取自定义属性的值
        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleRoundProgress)

        // 获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.SimpleRoundProgress_srp_roundColor, Color.RED)
        roundWidth = mTypedArray.getDimension(R.styleable.SimpleRoundProgress_srp_roundWidth, 5f)
        progressColor = mTypedArray.getColor(R.styleable.SimpleRoundProgress_srp_progressColor, Color.GREEN)
        progressWidth = mTypedArray.getDimension(R.styleable.SimpleRoundProgress_srp_progressWidth, roundWidth)
        max = mTypedArray.getInteger(R.styleable.SimpleRoundProgress_srp_max, 100)
        style = mTypedArray.getInt(R.styleable.SimpleRoundProgress_srp_style, 0)
        startAngle = mTypedArray.getInt(R.styleable.SimpleRoundProgress_srp_startAngle, 90)

        mTypedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2 // 获取圆心的x坐标
        val radius = (centerX - roundWidth / 2).toInt() // 圆环的半径

        // step1 画最外层的大圆环
        paint.strokeWidth = roundWidth // 设置圆环的宽度
        paint.color = roundColor // 设置圆环的颜色
        paint.isAntiAlias = true // 消除锯齿
        // 设置画笔样式
        when (style) {
            STROKE -> paint.style = Paint.Style.STROKE
            FILL -> paint.style = Paint.Style.FILL_AND_STROKE
        }
        canvas.drawCircle(centerX.toFloat(), centerX.toFloat(), radius.toFloat(), paint) // 画出圆环

        // step2 画圆弧-画圆环的进度
        paint.strokeWidth = progressWidth // 设置画笔的宽度使用进度条的宽度
        paint.color = progressColor // 设置进度的颜色
        val oval = RectF((centerX - radius).toFloat(), (centerX - radius).toFloat(), (centerX + radius).toFloat(), (centerX + radius).toFloat()) // 用于定义的圆弧的形状和大小的界限

        val sweepAngle = 360 * this.progress / max // 计算进度值在圆环所占的角度
        // 根据进度画圆弧
        when (style) {
            STROKE ->
                // 空心
                canvas.drawArc(oval, startAngle.toFloat(), sweepAngle.toFloat(), false, paint)
            FILL ->
                // 实心
                canvas.drawArc(oval, startAngle.toFloat(), sweepAngle.toFloat(), true, paint)
        }
    }

    /**
     * 设置进度的最大值
     *
     * 根据需要，最大值一般设置为100，也可以设置为1000、10000等
     *
     * @param max int最大值
     */
    @Synchronized
    fun setMax(max: Int) {
        if (max < 0) {
            throw IllegalArgumentException("max not less than 0")
        }
        this.max = max
    }

    fun getMax(): Int {
        return this.max
    }

    companion object {
        val STROKE = 0 // 样式：空心
        val FILL = 1 // 样式：实心
    }
}
