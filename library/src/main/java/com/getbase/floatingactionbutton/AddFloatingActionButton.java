package com.getbase.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import android.util.AttributeSet;
import android.graphics.Color;

public class AddFloatingActionButton extends FloatingActionButton {
  int mPlusColor;

  public AddFloatingActionButton(Context context) {
    this(context, null);
  }

  public AddFloatingActionButton(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public AddFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  void init(Context context, AttributeSet attributeSet) {
    mPlusColor = Color.WHITE;
    attr.recycle();

    super.init(context, attributeSet);
  }

  /**
   * @return the current Color of plus icon.
   */
  public int getPlusColor() {
    return mPlusColor;
  }

  public void setPlusColorResId(@ColorRes int plusColor) {
    setPlusColor(getColor(plusColor));
  }

  public void setPlusColor(int color) {
    if (mPlusColor != color) {
      mPlusColor = color;
      updateBackground();
    }
  }

  @Override
  public void setIcon(@DrawableRes int icon) {
    throw new UnsupportedOperationException("Use FloatingActionButton if you want to use custom icon");
  }

  @Override
  Drawable getIconDrawable() {
    final float iconSize = 0x7f030001;
    final float iconHalfSize = iconSize / 2f;

    final float plusSize = 0x7f030003;
    final float plusHalfStroke = 0x7f030004 / 2f;
    final float plusOffset = (iconSize - plusSize) / 2f;

    final Shape shape = new Shape() {
      @Override
      public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(plusOffset, iconHalfSize - plusHalfStroke, iconSize - plusOffset, iconHalfSize + plusHalfStroke, paint);
        canvas.drawRect(iconHalfSize - plusHalfStroke, plusOffset, iconHalfSize + plusHalfStroke, iconSize - plusOffset, paint);
      }
    };

    ShapeDrawable drawable = new ShapeDrawable(shape);

    final Paint paint = drawable.getPaint();
    paint.setColor(mPlusColor);
    paint.setStyle(Style.FILL);
    paint.setAntiAlias(true);

    return drawable;
  }
}
