package admi.newview.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View implements ViewRefreshUtil.ViewRefreshInterface, View.OnTouchListener {
	private RectF rectF;
	private List<Integer> colors;
	private List<SectorItem> mList;
	// 圆心
	private Point point;
	// 半径
	private int radius = 0;
	// view刷新
	private int a = 0;
	/*刷新弧形的角度*/
	private int refeshAngle = 0;
	/*弧形的个数*/
	private int number = 0;
	private boolean isPause;
	Thread thread = new Thread();
	private ViewRefreshUtil viewRefreshUtil;
	private ViewRefreshUtil.ViewRefreshInterfaceEntity viewRefreshInterface;
	
	private ViewClickListener viewClickListener;
	
	private Context mContext;

	public DrawView(Context context) {
		super(context);
		init(context);
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}


	public void setColors(List<Integer> colors) {
		this.colors = colors;
	}

	@SuppressLint("ClickableViewAccessibility")
	private void init(Context context) {
		this.mContext = context;
		
		colors = new ArrayList<Integer>();
		mList = new ArrayList<SectorItem>();
		if(colors.size()!=mList.size()){
			throw new IllegalArgumentException("sweepAngle must less than 360 degree");
		}
		
		viewRefreshUtil = ViewRefreshUtil.getRefreshUtil();
		viewRefreshInterface = viewRefreshUtil.addViewRefreshInterface(this);
		this.setOnTouchListener(this);
		
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		isPause = false;
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		
		paint.setAntiAlias(true);// 抗锯齿
		paint.setStyle(Paint.Style.STROKE);// Style.FILL: 实心, STROKE:空心, FILL_OR_STROKE:同时实心与空心
//		paint.setStrokeCap(Cap.ROUND);// 画笔样式：圆形 Cap.ROUND, 方形 Cap.SQUARE
//		paint.setStrokeJoin(Join.ROUND);// 平滑效果
		paint.setStrokeWidth(15);
//		paint.setColor(Color.BLUE);

		// canvas.drawLine(0, 50, 450, 50, paint);// 画线
		// canvas.drawRect(0, 0, 20, 60, paint);// 画矩形
		// canvas.drawCircle(100, 50, 50, paint);// 画圆
//		canvas.drawOval(rectF, paint);// 画椭圆或者圆
		
		a += 1;
		refeshAngle += 1;
		int temAngle = 0;
		
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(14);
		
		for (int i = 0; i < number; i++) {
			paint.setColor(colors.get(i));
			temAngle += mList.get(i).getEndAngle() - mList.get(i).getStartAngle();
			
			if (temAngle >= a) {
				drawMyView(canvas, paint, rectF, mList.get(i).getStartAngle(), refeshAngle);
				
				if (refeshAngle == mList.get(i).getEndAngle() - mList.get(i).getStartAngle()) {
					refeshAngle = 0;
					number = (number < mList.size()) ? number + 1 : mList.size();
				}
			} else {
				drawMyView(canvas, paint, rectF, mList.get(i).getStartAngle(), Math.abs(mList.get(i).getEndAngle() - mList.get(i).getStartAngle()));
			}
			Log.d("sss",i+""+a+"        "+temAngle);
		}
	}
	
	private void drawMyView(Canvas canvas, Paint paint, RectF rectF, int startAngle, int endAngle) {
		paint.setStrokeJoin(Paint.Join.MITER);
		paint.setStrokeCap(Paint.Cap.SQUARE);
		canvas.drawArc(rectF, startAngle, endAngle, true, paint);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measureWidth = getMeasureWH(widthMeasureSpec);
		int measureHeigh = getMeasureWH(heightMeasureSpec);
		
		if (measureWidth > measureHeigh) {
			radius = measureHeigh / 2;
		} else {
			radius = measureWidth / 2;
		}
		
		if (getMeasuredWidth() != 0 && getMeasuredHeight() != 0) {
			rectF = new RectF(0, 0, radius * 2, radius * 2);
//			this.start();
		}
		
		if (measureWidth > measureHeigh) {
			setMeasuredDimension(measureHeigh, measureHeigh);
		} else {
			setMeasuredDimension(measureWidth, measureWidth);
		}
	}
	
	private int getMeasureWH(int measureSpec) {
		
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		
		return specSize;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	public void setData(List<SectorItem> list) {
		if (!viewRefreshInterface.isRefresh() && !isPause) {
			this.mList = list;
			this.number = 1;
//			this.start();
		}
	}
	
	public boolean isRefresh() {
		return viewRefreshInterface.isRefresh();
	}
	
	// 开始
	public void start() {
		if (isPause) {
			viewRefreshInterface.setRefresh(true);
			viewRefreshUtil.startViewRefresh(viewRefreshInterface);
			isPause = false;
		}
		
		if (!viewRefreshInterface.isRefresh()) {
			viewRefreshInterface.setRefresh(true);
			viewRefreshUtil.startViewRefresh(viewRefreshInterface);
			isPause = false;
		}
	}
	
	// 暂停
	public void pause() {
		if (viewRefreshInterface.isRefresh() && !isPause) {
			viewRefreshInterface.setRefresh(false);
			isPause = true;
		}
	}
	
	// 停止
	public void stop() {
		viewRefreshInterface.setRefresh(false);
		a = 0;
		refeshAngle = 0;
		number = 0;
		init(mContext);
		postInvalidate();
	}
	
	@Override
	public void refresh() {
		if (a >= 360) {
			viewRefreshInterface.setRefresh(false);
		} else {
			postInvalidate();
		}
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		point = new Point((right - left) / 2 + left, (bottom - top) / 2 + top);
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			double x = Math.pow(point.x - event.getRawX(), 2);
			double y = Math.pow(point.y - event.getRawY(), 2);
			
			if (!viewRefreshInterface.isRefresh() && Math.sqrt(x + y) <= radius) {
				int tanX = (int) Math.abs(event.getRawX() - point.x);
				int tanY = (int) Math.abs(event.getRawY() - point.y);
				
				double clickAngle = getangle(tanX, tanY, (int) event.getRawX() - point.x, point.y - (int) event.getRawY());
				int mClickAngle = (int) clickAngle - 90;
				
				for (int i = 0; i < mList.size(); i++) {
					if (mClickAngle >= mList.get(i).getStartAngle() && mClickAngle <= mList.get(i).getEndAngle()) {
						viewClickListener.clickListener(mList.get(i));
						break;
					}
				}
			}
			break;
		}
		
		return true;
	}
	
	// 根据点击位置获取夹角
	private double getangle(int x, int y, int downx, int downy) {
		double angle = (float) 0.0;
		if (downx > 0 && downy < 0) {
			double c = (double) y / (double) x;
			double d = Math.toDegrees(Math.atan(c));
			angle = d + 90;
		} else if (downx <= 0 && downy <= 0) {
			double c = (double) x / (double) y;
			double d = Math.toDegrees(Math.atan(c));
			angle = d + 180;
		} else if (downx < 0 && downy >= 0) {
			double c = (double) y / (double) x;
			double d = Math.toDegrees(Math.atan(c));
			angle = d + 270;
		} else {
			double c = (double) x / (double) y;
			double d = Math.toDegrees(Math.atan(c));
			angle = d;
		}
		return angle;
	}

	public void setViewClickListenrt(ViewClickListener clickListener) {
		this.viewClickListener = clickListener;
	}
	
	public interface ViewClickListener {
		public void clickListener(SectorItem item);
	}
	
}