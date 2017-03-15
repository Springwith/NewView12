package admi.newview.view;

/**
 * HighlightColorAndRange
 * 高亮效果的范围和颜色对象
 * Created by Administrator on 2017/3/13.
 */
public class HighlightCR {
    private int mStartAngle;
    private int mSweepAngle;
    private int mColor;

    public HighlightCR(int mStartAngle, int mSweepAngle, int mColor) {
        this.mStartAngle = mStartAngle;
        this.mSweepAngle = mSweepAngle;
        this.mColor = mColor;
    }

    public HighlightCR() {
    }

    public int getmStartAngle() {
        return mStartAngle;
    }

    public void setmStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
    }

    public int getmSweepAngle() {
        return mSweepAngle;
    }

    public void setmSweepAngle(int mSweepAngle) {
        this.mSweepAngle = mSweepAngle;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }
}
