package com.mattfeury.saucillator.dev.android.visuals;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Align;

public class RectButton extends RectF {
  private Paint bg,text,focusedBg;
  private int borderWidth = 5;
  private String name;
  private boolean focused = false;
  public static final int textWidth = -8; //in pixels, i guess? just an estimate.

  public RectButton(String name) {
    this(name, 0, 0, 0, 0);
  }
  public RectButton(String name, int x, int y, int width, int height) {
    super(x, y, x + width, y + height);
    
    this.name = name;

    bg = new Paint();
    focusedBg = new Paint();
    text = new Paint();

    bg.setARGB(200, 12, 81, 4);
    focusedBg.setARGB(255, 28, 171, 11);
    text.setARGB(255, 255,255,255);
    focusedBg.setTextSize(14);
    focusedBg.setTextAlign(Align.CENTER);
    text.setTextSize(14);
    text.setTextAlign(Align.CENTER);
    
    bg.setStrokeWidth(5);

  }
  public String getName() {
    return name;
  }
  public void set(int x, int y, int width, int height) {
    super.set(x, y, x + width, y + height);       
  }
  public boolean contains(int x, int y) {
    return (x > left && x <= right) &&
            (y > top && y <= bottom);
  }
  
  public void draw(Canvas canvas) {
    if (focused) {
      canvas.drawRect(left, top, right, top + borderWidth, focusedBg); //top line
      canvas.drawText(name, (right + left) / 2f, top + (bottom - top)* .5f, focusedBg);
    } else {
      canvas.drawRect(left + borderWidth, top, right - borderWidth, top + borderWidth, bg); //top line
      canvas.drawRect(left, top, left + borderWidth, bottom - borderWidth, bg); //left line
      canvas.drawRect(left, bottom - borderWidth, right, bottom, bg); //bottom line
      canvas.drawRect(right - borderWidth, top, right, bottom - borderWidth, bg); //right line
      
      canvas.drawText(name, (right + left) / 2f, top + (bottom - top)* .5f, text);
    }
  }

  public void setFocus(boolean focus) {
    focused = focus;
    //view.invalidate();
  }
  public void focus() {
    focused = true;
    //view.invalidate();
  }
  public void unfocus() {
    focused = false;
    //view.invalidate();
  }
  public boolean toggleFocus() {
    focused = ! focused;
    //view.invalidate();
    return focused;
  }

}
