package admi.newview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.List;

import admi.newview.R;

/**
 * Created by Administrator on 2017/3/10.
 */
public class DashboardView extends View {
    /*圆弧的半径*/
    private int mRadius;
    /*起始角度*/
    private int mStartAngle;
    /*绘制角度*/
    private int mSweepAngle;
    /*大份数*/
    private int mBigSliceCount;
    /*划分一大份有多少小分数的数量*/
    private int mSliceCountInOneBigSlice;
    /*弧度颜色*/
    private int mArcColor;
    /*刻度字体大小*/
    private int mMeasureTextSize;
    /*字体颜色*/
    private int mTextColor;
    /*表头*/
    private String mHeardTitle;
    /*表头字体大小*/
    private int mHeardTextSize;
    /*表头半径*/
    private int mHeardRadius;
    /*指针半径*/
    private int mPointRadius;
    /*中心圆半径*/
    private int mCircleRadius;
    /*最大值*/
    private int mMainValue;
    /*最小值*/
    private int mMaxValue;
    /*实时值*/
    private float mRealTimeValue;
    /*色条宽度*/
    private int mStripWidth;
    private StripeMode mStripeMode = StripeMode.NORMAL;
    /*较长刻度半径*/
    private int mBigSliceRadius;
    /*较短刻度半径*/
    private int mSmallSliceRadius;
    /*数字刻度半径*/
    private int mNumMeaRadius;
    private int mModeType;
    /*高亮范围颜色对象的集合*/
    private List<HighlightCR> mStripeHighlight;
    /*背景色*/
    private int mBgColor;
    /*控件的宽度*/
    private int mViewWidth;
    /*控件的高度*/
    private int mViewHight;
    private float mCenterX;
    private float mCenterY;
    private Paint mPaintArc;
    private Paint mPaintText;
    private Paint mPaintPointer;
    private Paint mPaintValue;
    private Paint mPaintStripe;
    private RectF mRectArc;
    private RectF mRectStripe;
    private Rect mRectMeasures;
    private Rect mRectHeader;
    private Rect mRectRealText;
    private Path mPath;
    /*短刻度个数*/
    private int mSmallSliceCount;
    /*大刻度等分角度*/
    private float mBigSliceAngle;
    /*小刻度等分角度*/
    private float mSmallSliceAngle;
    /*等分的刻度值*/
    private String[] mGraduations;
    private float initAngle;
    /*若不单独设置字体颜色，则文字和圆弧同色*/
    private boolean textColorFlag = true;
    /*是否播放动画*/
    private boolean mAnimEnable;
    /*动画是否播放完毕*/
    private boolean isAnimFinished = true;
    /*动画默认时长*/
    private long mDuration = 500;

    public int getmRadius() {
        return mRadius;
    }

    public void setmRadius(int mRadius) {
        this.mRadius =daToPx(mRadius);
        initSizes();
        invalidate();
    }

    public int getmStartAngle() {
        return mStartAngle;
    }

    public void setmStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        initSizes();
        invalidate();
    }

    public int getmSweepAngle() {
        return mSweepAngle;
    }

    public void setmSweepAngle(int mSweepAngle) {
        this.mSweepAngle = mSweepAngle;
        initSizes();
        invalidate();
    }

    public int getmBigSliceCount() {
        return mBigSliceCount;
    }

    public void setmBigSliceCount(int mBigSliceCount) {
        this.mBigSliceCount = mBigSliceCount;
        initSizes();
        invalidate();
    }

    public int getmSliceCountInOneBigSlice() {
        return mSliceCountInOneBigSlice;
    }

    public void setmSliceCountInOneBigSlice(int mSliceCountInOneBigSlice) {
        this.mSliceCountInOneBigSlice = mSliceCountInOneBigSlice;
        initSizes();
        invalidate();
    }

    public int getmArcColor() {
        return mArcColor;
    }

    public void setmArcColor(int mArcColor) {
        this.mArcColor = mArcColor;
        mPaintArc.setColor(mArcColor);
        if(textColorFlag){
            mTextColor=mArcColor;
            mPaintText.setColor(mArcColor);
        }
        invalidate();
    }

    public int getmMeasureTextSize() {
        return mMeasureTextSize;
    }

    public void setmMeasureTextSize(int mMeasureTextSize) {
        this.mMeasureTextSize = spToPX(mMeasureTextSize);
        invalidate();
    }

    public int getmTextColor() {
        return mTextColor;
    }

    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        textColorFlag=false;
        mPaintText.setColor(mTextColor);
        invalidate();
    }

    public String getmHeardTitle() {
        return mHeardTitle;
    }

    public void setmHeardTitle(String mHeardTitle) {
        this.mHeardTitle = mHeardTitle;
        invalidate();
    }

    public int getmHeardTextSize() {
        return mHeardTextSize;
    }

    public void setmHeardTextSize(int mHeardTextSize) {
        this.mHeardTextSize = spToPX(mHeardTextSize);
        invalidate();
    }

    public int getmHeardRadius() {
        return mHeardRadius;
    }

    public void setmHeardRadius(int mHeardRadius) {
        this.mHeardRadius = daToPx(mHeardRadius);
        invalidate();
    }

    public int getmPointRadius() {
        return mPointRadius;
    }

    public void setmPointRadius(int mPointRadius) {
        this.mPointRadius = daToPx(mPointRadius);
        invalidate();
    }

    public int getmCircleRadius() {
        return mCircleRadius;
    }

    public void setmCircleRadius(int mCircleRadius) {
        this.mCircleRadius =daToPx( mCircleRadius);
        invalidate();
    }

    public int getmMainValue() {
        return mMainValue;
    }

    public void setmMainValue(int mMainValue) {
        this.mMainValue = mMainValue;
        invalidate();
    }

    public int getmMaxValue() {
        return mMaxValue;

    }

    public void setmMaxValue(int mMaxValue) {
        this.mMaxValue = mMaxValue;
        invalidate();
    }

    public  float getmRealTimeValue(){
        return mRealTimeValue;
    }
    public void setmRealTimeValue(float realTimeValue){
        if(!mAnimEnable){
            invalidate();
        }else{
            if(isAnimFinished){
                isAnimFinished=false;
                playAnimation(realTimeValue);
            }
        }
    }
    public void setmRealTimeValue(float realTimeValue,boolean animEnable){
        mAnimEnable=animEnable;
        setmRealTimeValue(realTimeValue);
    }
    public void setmRealTimeValue(float realTimeValue,boolean aniEnable,long duration){
       this.mDuration=duration;
        setmRealTimeValue(realTimeValue,aniEnable);
    }

    public int getmStripWidth() {
        return mStripWidth;
    }

    public void setmStripWidth(int mStripWidth) {
        this.mStripWidth = daToPx(mStripWidth);
        initSizes();
        invalidate();
    }

    public StripeMode getmStripeMode() {
        return mStripeMode;
    }

    public void setmStripeMode(StripeMode mStripeMode) {
        this.mStripeMode = mStripeMode;
        switch (mStripeMode){
            case NORMAL:
                mModeType=0;
                break;
            case INNER:
                mModeType=1;
                break;
            case OUTER:
                mModeType=2;
                break;
        }
        initSizes();
        invalidate();
    }

    public int getmSmallSliceRadius() {
        return mSmallSliceRadius;
    }

    public void setmSmallSliceRadius(int mSmallSliceRadius) {
        this.mSmallSliceRadius =daToPx( mSmallSliceRadius);
        initSizes();
        invalidate();
    }

    public int getmBigSliceRadius() {
        return mBigSliceRadius;
    }

    public void setmBigSliceRadius(int mBigSliceRadius) {
        this.mBigSliceRadius = mBigSliceRadius;
        initSizes();
        invalidate();
    }

    public int getmNumMeaRadius() {
        return mNumMeaRadius;
    }

    public void setmNumMeaRadius(int mNumMeaRadius) {
        this.mNumMeaRadius = daToPx(mNumMeaRadius);
        initSizes();
        invalidate();
    }

    public DashboardView(Context context) {
        super(context, null);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public DashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DashboardView, defStyleAttr, 0);
        mRadius = a.getDimensionPixelOffset(R.styleable.DashboardView_d_radius, daToPx(80));
        mStartAngle = a.getInteger(R.styleable.DashboardView_starttAngle, 180);
        mSweepAngle = a.getInteger(R.styleable.DashboardView_sweepAngle, 180);
        mBigSliceCount = a.getInteger(R.styleable.DashboardView_bigSliceCount, 10);
        mSliceCountInOneBigSlice = a.getInteger(R.styleable.DashboardView_sliceCountInOneBigSlice, 5);
        mArcColor = a.getColor(R.styleable.DashboardView_arcColor, Color.WHITE);
        mMeasureTextSize = a.getDimensionPixelSize(R.styleable.DashboardView_measureTextSize, spToPX(12));
        mTextColor = a.getColor(R.styleable.DashboardView_textColor, mArcColor);
        mHeardTitle = a.getString(R.styleable.DashboardView_headerTitle);
        if (mHeardTitle == null) {
            mHeardTitle = "";
        }
        mHeardTextSize = a.getDimensionPixelSize(R.styleable.DashboardView_headerTextSize, spToPX(14));
        mHeardRadius = a.getDimensionPixelSize(R.styleable.DashboardView_headerRadius, mRadius / 3);
        mPointRadius = a.getDimensionPixelSize(R.styleable.DashboardView_pointerRadius, mRadius / 3 * 2);
        mCircleRadius = a.getDimensionPixelSize(R.styleable.DashboardView_circleRadius, mRadius / 17);
        mMainValue = a.getInteger(R.styleable.DashboardView_mainValue, 0);
        mMaxValue = a.getInteger(R.styleable.DashboardView_maxValue, 100);
        mRealTimeValue = a.getFloat(R.styleable.DashboardView_realTimeValue, 0.0f);
        mStripWidth = a.getDimensionPixelSize(R.styleable.DashboardView_stripeWidth, 0);
        mModeType = a.getInt(R.styleable.DashboardView_stripeMode, 0);
        mBgColor = a.getColor(R.styleable.DashboardView_bgColor, 0);
        a.recycle();
        initObjecte();
        initSizes();
    }

    private void initSizes() {
        if(mSweepAngle>360){
            throw new IllegalArgumentException("sweepAngle must less than 360 degree");
        }

        mSmallSliceRadius=mRadius-daToPx(8);
        mBigSliceRadius=mSmallSliceRadius-daToPx(4);
        mNumMeaRadius=mBigSliceRadius-daToPx(3);

        mSmallSliceCount=mBigSliceCount*mSliceCountInOneBigSlice;
        mBigSliceAngle=mSweepAngle/(float)mBigSliceCount;
        mSmallSliceAngle=mBigSliceAngle/(float) mSliceCountInOneBigSlice;
        mGraduations=getMeasureNumbers();
        switch (mModeType){
            case 0:
                mStripeMode=StripeMode.NORMAL;
                break;
            case 1:
                mStripeMode=StripeMode.INNER;
                break;
            case 2:
                mStripeMode=StripeMode.OUTER;
                break;

        }
        int totalRadius;
        if(mStripeMode==StripeMode.OUTER){
            totalRadius=mRadius+mStripWidth;
        }else{
            totalRadius=mRadius;
        }
        mCenterX=mCenterY=0.0f;
        if(mStartAngle<=180){
            mViewWidth=totalRadius*2+getPaddingLeft()+getPaddingRight()+daToPx(2)*2;
        }else{
            float[] point1=getCoordinatePoint(totalRadius,mStartAngle);
            float[] point2=getCoordinatePoint(totalRadius,mStartAngle+mSweepAngle);
            float max=Math.max(Math.abs(point1[0]),Math.abs(point2[0]));
            mViewWidth=(int)(max*2+getPaddingLeft()+getPaddingRight()+daToPx(2)*2);
        }
        if((mStartAngle<=90&&mStartAngle+mSweepAngle>=90)||
                (mStartAngle<=270&&mStartAngle+mSweepAngle>=270)){
            mViewHight=totalRadius*2+getPaddingTop()+getPaddingBottom()+daToPx(2)*2;
        }else{
            float[] point1=getCoordinatePoint(totalRadius,mStartAngle);
            float[] point2=getCoordinatePoint(totalRadius,mSweepAngle+mStartAngle);
            float max=Math.max(Math.abs(point1[1]),Math.abs(point2[1]));
            mViewHight=(int)(max*2+getPaddingTop()+getPaddingBottom()+daToPx(2)*2);
        }
         mCenterX=mViewWidth/2.0f;
        mCenterY=mViewHight/2.0f;
        mRectArc=new RectF(mCenterX-mRadius,mCenterY-mRadius,mCenterX+mRadius,mCenterY+mRadius);
        int r=0;
        if(mStripWidth>0){
            if(mStripeMode==StripeMode.OUTER){
                r=mRadius+daToPx(1)+mStripWidth/2;
            }else if(mStripeMode==StripeMode.INNER){
                r=mRadius+daToPx(1)-mStripWidth/2;
            }
            mRectStripe=new RectF(mCenterX-r,mCenterY-r,mCenterX+r,mCenterY+r);
        }
        initAngle=getAngleFormResult(mRealTimeValue);
    }
    private void initObjecte() {
        mPaintArc = new Paint();
        mPaintArc.setAntiAlias(true);
        mPaintArc.setColor(mArcColor);
        mPaintArc.setStyle(Paint.Style.STROKE);
        mPaintArc.setStrokeCap(Paint.Cap.ROUND);

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(mTextColor);
        mPaintText.setStyle(Paint.Style.STROKE);

        mPaintPointer = new Paint();
        mPaintPointer.setAntiAlias(true);

        mPaintStripe = new Paint();
        mPaintStripe.setAntiAlias(true);
        mPaintStripe.setStyle(Paint.Style.STROKE);
        mPaintStripe.setStrokeWidth(mStripWidth);

        mRectMeasures = new Rect();
        mRectHeader = new Rect();
        mRectRealText = new Rect();
        mPath = new Path();

        mPaintValue = new Paint();
        mPaintValue.setAntiAlias(true);
        mPaintValue.setColor(mTextColor);
        mPaintValue.setStyle(Paint.Style.STROKE);
        mPaintValue.setTextAlign(Paint.Align.CENTER);
        mPaintValue.setTextSize(Math.max(mHeardTextSize, mMeasureTextSize));
        mPaintValue.getTextBounds(trimFloat(mRealTimeValue), 0, trimFloat(mRealTimeValue).length(), mRectRealText);

    }

    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode==MeasureSpec.EXACTLY){
            mViewWidth=widthSize;
        }else{
            if(widthMode==MeasureSpec.AT_MOST){
                mViewWidth=Math.min(mViewWidth,widthSize);
            }
        }
        if(heightMode==MeasureSpec.EXACTLY){
            mViewHight=heightSize;
        }else{
            int totalRadius;
            if(mStripeMode==StripeMode.OUTER){
                totalRadius=mRadius+mStripWidth;
            }else{
                totalRadius=mRadius;
            }
            if(mStartAngle>=180&&mStartAngle+mSweepAngle<=360){
                mViewHight=totalRadius+mCircleRadius+daToPx(2)+daToPx(25)+getPaddingTop()+getPaddingBottom()+mRectRealText.height();
            }else{
                float[] point1=getCoordinatePoint(totalRadius,mStartAngle);
                float[] point2=getCoordinatePoint(totalRadius,mStartAngle+mSweepAngle);
                float maxY=Math.max(Math.abs(point1[1])-mCenterY,Math.abs(point2[1]-mCenterY));
                float f=mCircleRadius+daToPx(2)+daToPx(25)+mRectRealText.height();
                float max=Math.max(maxY,f);
                mViewHight= (int) (max+totalRadius+getPaddingTop()+getPaddingBottom()+daToPx(2)*2);
            }
            if(widthMode==MeasureSpec.AT_MOST){
                mViewHight=Math.min(mViewHight,widthSize);
            }
        }
        setMeasuredDimension(mViewWidth,mViewHight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBgColor!=0){
            canvas.drawColor(mBgColor);
        }
        drawStripe(canvas);
        drawMeasures(canvas);
        drawArc(canvas);
        drawCircleAndReadingText(canvas);
        drawPointer(canvas);
    }
    /*
    * 绘制指针
    * */
    private void drawPointer(Canvas canvas) {
        mPaintPointer.setStyle(Paint.Style.FILL);
        mPaintPointer.setColor(mTextColor);
        mPath.reset();
        float[] point1=getCoordinatePoint(mCircleRadius/2,initAngle+90);
        mPath.moveTo(point1[0],point1[1]);
        float[] point2=getCoordinatePoint(mCircleRadius/2,initAngle-90);
        mPath.lineTo(point2[0],point2[1]);
        float[] point3=getCoordinatePoint(mPointRadius,initAngle);
        mPath.lineTo(point3[0],point3[1]);
        mPath.close();
        canvas.drawPath(mPath,mPaintPointer);
        /*
        * 绘制三角形指针底部圆弧效果
        * */
        canvas.drawCircle((point1[0]+point2[0])/2,(point1[1]+point2[1])/2,mCircleRadius/2,mPaintPointer);
    }
    /*
    * 绘制圆和文字读数
    * */
    private void drawCircleAndReadingText(Canvas canvas) {
        //表头
        mPaintText.setTextSize(mHeardTextSize);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.getTextBounds(mHeardTitle,0,mHeardTitle.length(),mRectHeader);
        canvas.drawText(mHeardTitle,mCenterX,mCenterY-mHeardRadius+mRectHeader.height(),mPaintText);
        /*
        * 绘制中心点的圆
        * */
        mPaintPointer.setStyle(Paint.Style.FILL);
        mPaintPointer.setColor(Color.parseColor("#e4e9e9"));
        canvas.drawCircle(mCenterX,mCenterY,mCircleRadius,mPaintPointer);

        mPaintPointer.setStyle(Paint.Style.STROKE);
        mPaintPointer.setStrokeWidth(daToPx(4));
        mPaintPointer.setColor(mArcColor);
        canvas.drawCircle(mCenterX,mCenterY,mCircleRadius+daToPx(2),mPaintPointer);
        /*
        * 绘制读数
        * */
        canvas.drawText(trimFloat(mRealTimeValue),mCenterX,mCenterY+mCircleRadius+daToPx(2)+daToPx(25),mPaintValue);
    }
    /*
    * 绘制刻度盘的弧形
    * */
    private void drawArc(Canvas canvas) {
        mPaintArc.setStrokeWidth(daToPx(2));
        if(mStripeMode==StripeMode.NORMAL){
            if(mStripeHighlight!=null){
                for(int i=0;i<mStripeHighlight.size();i++){
                    HighlightCR highlightCR=mStripeHighlight.get(i);
                    if(highlightCR.getmColor()==0||highlightCR.getmSweepAngle()==0){
                        continue;
                    }
                    mPaintArc.setColor(highlightCR.getmColor());
                    if(highlightCR.getmStartAngle()+highlightCR.getmSweepAngle()<=mStartAngle+mSweepAngle){
                        canvas.drawArc(mRectArc,highlightCR.getmStartAngle(),highlightCR.getmSweepAngle(),false,mPaintArc);
                    }else{
                        canvas.drawArc(mRectArc,highlightCR.getmStartAngle(),mStartAngle+mSweepAngle-highlightCR.getmStartAngle(),false,mPaintArc);
                        break;
                    }
                }
            }else{
                mPaintArc.setColor(mArcColor);
                canvas.drawArc(mRectArc,mStartAngle,mSweepAngle,false,mPaintArc);
            }
        }else if(mStripeMode==StripeMode.OUTER){
            mPaintArc.setColor(mArcColor);
            canvas.drawArc(mRectArc,mStartAngle,mSweepAngle,false,mPaintArc);
        }
    }
    /*
    * 绘制刻度盘
    * */
    private void drawMeasures(Canvas canvas) {
        mPaintArc.setStrokeWidth(daToPx(2));
        for(int i=0;i<=mBigSliceCount;i++){
            /*绘制大刻度*/
            float angle=i*mBigSliceAngle+mStartAngle;
            float[] point1=getCoordinatePoint(mRadius,angle);
            float[] point2=getCoordinatePoint(mBigSliceRadius,angle);
            if(mStripeMode==StripeMode.NORMAL&&mStripeHighlight!=null){
                for(int j=0;j<mStripeHighlight.size();j++){
                    HighlightCR highlightCR=mStripeHighlight.get(j);
                    if(highlightCR.getmColor()==0||highlightCR.getmSweepAngle()==0){
                        continue;
                    }
                    if(angle<=highlightCR.getmStartAngle()+highlightCR.getmSweepAngle()){
                        mPaintArc.setColor(highlightCR.getmColor());
                        break;
                    }else{
                        mPaintArc.setColor(mArcColor);
                    }
                }
            }else{
                mPaintArc.setColor(mArcColor);
            }
            canvas.drawLine(point1[0],point1[1],point2[0],point2[1],mPaintArc);
            /*刻度圆盘上的数字*/
            mPaintText.setTextSize(mMeasureTextSize);
            String number=mGraduations[i];
            mPaintText.getTextBounds(number,0,number.length(),mRectMeasures);
            if(angle%360>135&&angle%360<225){
                mPaintText.setTextAlign(Paint.Align.LEFT);
            }else if((angle%360>=0&&angle%360<45)||(angle%360>315&&angle%360<=360)){
                mPaintText.setTextAlign(Paint.Align.RIGHT);
            }else{
                mPaintText.setTextAlign(Paint.Align.CENTER);
            }
            float[] numbrePoint=getCoordinatePoint(mNumMeaRadius,angle);
            if(i==0||i==mBigSliceCount){
                canvas.drawText(number,numbrePoint[0],numbrePoint[1]+(mRectMeasures.height()/2),mPaintText);
            }else{
                canvas.drawText(number,numbrePoint[0],numbrePoint[1]+mRectMeasures.height(),mPaintText);
            }
        }
        /*绘制小的子刻度*/
        mPaintArc.setStrokeWidth(daToPx(1));
        for(int i=0;i<mSmallSliceCount;i++){
            if(i%mSliceCountInOneBigSlice!=0){
                float angle=i*mSmallSliceAngle+mStartAngle;
                float[] point1=getCoordinatePoint(mRadius,angle);
                float[] point2=getCoordinatePoint(mSmallSliceRadius,angle);
                if(mStripeMode==StripeMode.NORMAL&&mStripeHighlight!=null){
                    for(int j=0;j<mStripeHighlight.size();j++){
                        HighlightCR highlightCR=mStripeHighlight.get(j);
                        if(highlightCR.getmColor()==0||highlightCR.getmSweepAngle()==0){
                           continue;
                        }
                        if(angle<=highlightCR.getmStartAngle()+highlightCR.getmSweepAngle()){
                            mPaintArc.setColor(highlightCR.getmColor());
                            break;
                        }else {
                            mPaintArc.setColor(mArcColor);
                        }
                    }
                }else {
                    mPaintArc.setColor(mArcColor);
                }
                mPaintArc.setStrokeWidth(daToPx(1));
                canvas.drawLine(point1[0],point1[1],point2[0],point2[1],mPaintArc);
            }

        }

    }
    /*
    * 绘制色带
    * */
    private void drawStripe(Canvas canvas) {
        if(mStripeMode!=StripeMode.NORMAL&&mStripeHighlight!=null){
            for(int i=0;i<mStripeHighlight.size();i++){
                HighlightCR highlightCR=mStripeHighlight.get(i);
                if(highlightCR.getmColor()==0||highlightCR.getmSweepAngle()==0){
                    continue;
                }
                mPaintStripe.setColor(highlightCR.getmColor());
                if(highlightCR.getmStartAngle()+highlightCR.getmSweepAngle()<=mStartAngle+mSweepAngle){
                  canvas.drawArc(mRectStripe,highlightCR.getmStartAngle(),highlightCR.getmSweepAngle(),false,mPaintStripe);
                }else{
                    canvas.drawArc(mRectStripe,highlightCR.getmStartAngle(),mStartAngle+mSweepAngle-highlightCR.getmStartAngle()
                    ,false,mPaintStripe);
                }
            }
        }
    }

    /**
     * float类型如果小数点后为零则显示整数否则保留
     */
    private static String trimFloat(float mRealTimeValue) {
        if(Math.round(mRealTimeValue)-mRealTimeValue==0){
            return String.valueOf((long)mRealTimeValue);
        }
        return String.valueOf(mRealTimeValue);
    }
    private String[] getMeasureNumbers(){
        String[] string=new String[mBigSliceCount+1];
        for(int i=0;i<=mBigSliceCount;i++){
            if(i==0){
                string[i]=String.valueOf(mMainValue);
            }else if(i==mBigSliceCount){
                string[i]=String.valueOf(mMaxValue);
            }else{
                string[i]=String.valueOf(((mMaxValue-mMainValue)/mBigSliceCount)*i);
            }
        }
        return string;
    }

    private int spToPX(int i) {
      return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,i,getResources().getDisplayMetrics());
    }

    private int daToPx(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,i,getResources().getDisplayMetrics());
    }
    /*
    * 通过数值得到角度位置
    * */
    private float getAngleFormResult(float mRealTimeValue) {
        if(mRealTimeValue>mMaxValue){
            return mMaxValue;
        }
        return mSweepAngle*(mRealTimeValue-mMainValue)/(mMaxValue-mMainValue)+mStartAngle;
    }
    /*
    * 依圆心坐标，半径，扇形角度，计算扇形终射线与圆弧交叉点的坐标
    * */
    public float[] getCoordinatePoint(int totalRadius, float mStartAngle) {
        float[] point=new float[2];
        /*将角度转换为弧度*/
        double mStartAngle1=Math.toRadians(mStartAngle);
        if(mStartAngle<90){
            point[0]= (float) (mCenterX+Math.cos(mStartAngle)*totalRadius);
            point[1]= (float) (mCenterY+Math.sin(mStartAngle)*totalRadius);
        }else if(mStartAngle==90){
            point[0]= (float) mCenterX;
            point[1]= (float) (mCenterY+totalRadius);
        }else if(mStartAngle>90&&mStartAngle<180){
            point[0]= (float) (mCenterX-Math.cos(mStartAngle)*totalRadius);
            point[1]= (float) (mCenterY+Math.sin(mStartAngle)*totalRadius);
        }else if(mStartAngle==180){
            point[0]= (float) (mCenterX-totalRadius);
            point[1]= (float) mCenterY;
        }else if(mStartAngle>180&&mStartAngle<270){
            point[0]= (float) (mCenterX-Math.cos(mStartAngle)*totalRadius);
            point[1]= (float) (mCenterY-Math.sin(mStartAngle)*totalRadius);
        }else if(mStartAngle==270){
            point[0]= (float) (mCenterX);
            point[1]= (float) (mCenterY-totalRadius);
        }else{
            mStartAngle1 = Math.PI * (360 - mStartAngle) / 180.0;
            point[0] = (float) (mCenterX + Math.cos(mStartAngle1) * totalRadius);
            point[1] = (float) (mCenterY - Math.sin(mStartAngle1) * totalRadius);
        }
        return point;
    }

    public enum StripeMode {
        NORMAL,
        INNER,
        OUTER
    }

    public class PointerBounceInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float input) {
            return (float) (Math.pow(2, -10 * input)
                    * Math.sin((input - 0.4f / 4)
                    * (2 * Math.PI) / 0.4f) + 1);
        }
    }

    private void playAnimation(float targetValue){
        final ValueAnimator animator=ValueAnimator.ofFloat(mRealTimeValue,targetValue);
        animator.setDuration(mDuration);
        animator.setInterpolator(new PointerBounceInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mRealTimeValue= (float) Math.ceil((double)animator.getAnimatedValue());
                if(mRealTimeValue<mMainValue){
                    mRealTimeValue=mMainValue;
                }
                if(mRealTimeValue>mMaxValue){
                    mRealTimeValue=mMaxValue;
                }
                initAngle=getAngleFormResult(mRealTimeValue);
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimFinished=true;
            }
        });
        animator.start();
    }

    public void setStripeHighlightColorAndRange(List<HighlightCR>stripeHighlight){
        mStripeHighlight=stripeHighlight;
        mPaintStripe.setStrokeWidth(mStripWidth);
        invalidate();
    }
}
