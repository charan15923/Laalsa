package com.cronlogy.charan.laalsa.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.AttributeSet;

public class TintableRatingBar extends AppCompatRatingBar {

    private TintableRatingBarProgressDrawable progressDrawable;

    public TintableRatingBar(final Context context) {
        super(context);
        init();
    }

    public TintableRatingBar(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TintableRatingBar(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        progressDrawable = new TintableRatingBarProgressDrawable();
        setProgressDrawable(progressDrawable);
    }

    public void setCustomTintColors(final int _uncheckedColor, final int _pressedColor, final int _checkedColor) {
        progressDrawable.setRatingMaxLevelValue(getMax() * 1000);
        progressDrawable.setUnCheckedColor(_uncheckedColor);
        progressDrawable.setPressedColor(_pressedColor);
        progressDrawable.setCheckedColor(_checkedColor);
        invalidate();
    }

    public class TintableRatingBarProgressDrawable extends Drawable {
        private static final int STAR_COUNT = 5;
        private static final int STAR_BRANCHES_COUNT = 5;

        /** Sets the max level value: if the level is at the max, then all stars are selected. */
        private int ratingMaxLevelValue = 10000;
        /** Color to be painted for unselected stars */
        private int uncheckedColor = Color.GRAY;
        /** Color to be painted for unselected stars when the ratingbar is pressed */
        private int pressedColor = Color.CYAN;
        /** Color to be painted for selected stars */
        private int checkedColor = Color.BLUE;

        @Override
        public void setAlpha(final int _i) {
        }

        @Override
        public void setColorFilter(final ColorFilter _colorFilter) {
        }

        @Override
        public boolean isStateful() {
            return true;
        }

        @Override
        public boolean setState(final int[] stateSet) {
            final boolean res = super.setState(stateSet);
            invalidateSelf();
            return res;
        }

        @Override
        public int getOpacity() {
            return 255;
        }

        public void setRatingMaxLevelValue(final int _ratingMaxLevelValue) {
            ratingMaxLevelValue = _ratingMaxLevelValue;
        }

        public void setUnCheckedColor(final int _uncheckedColor) {
            uncheckedColor = _uncheckedColor;
        }

        public void setPressedColor(final int _pressedColor) {
            pressedColor = _pressedColor;
        }

        public void setCheckedColor(final int _checkedColor) {
            checkedColor = _checkedColor;
        }

        @Override
        public void draw(final Canvas _canvas) {
            boolean pressed = false;
            for (int i : getState()) {
                if (i == android.R.attr.state_pressed) {
                    pressed = true;
                }
            }

            final int level = (int) Math.ceil(getLevel() / (double) ratingMaxLevelValue * STAR_COUNT);
            final int starRadius = Math.min(getBounds().bottom / 2, getBounds().right / STAR_COUNT / 2);

            for (int i = 0; i < STAR_COUNT; i++) {
                final int usedColor;
                if (level >= i + 1) {
                    usedColor = checkedColor;
                } else if (pressed) {
                    usedColor = pressedColor;
                } else {
                    usedColor = uncheckedColor;
                }
                drawStar(_canvas, usedColor, (i * 2 + 1) * starRadius, getBounds().bottom / 2, starRadius,
                        STAR_BRANCHES_COUNT);
            }
        }

        private void drawStar(final Canvas _canvas, final int _color, final float _centerX, final float _centerY,
                              final float _radius, final int _branchesCount) {
            final double rotationAngle = Math.PI * 2 / _branchesCount;
            final double rotationAngleComplement = Math.PI / 2 - rotationAngle;
            //Calculating how much space is left between the bottom of the star and the bottom of the circle
            //In order to be able to center the star visually relatively to the square when drawn
            final float bottomOffset = (float) (_radius - _radius * Math.sin(rotationAngle / 2) / Math.tan(
                    rotationAngle / 2));
            final float actualCenterY = _centerY + (bottomOffset / 2);
            final Paint paint = new Paint();
            paint.setColor(_color);
            paint.setStyle(Paint.Style.FILL);
            final Path path = new Path();
            final float relativeY = (float) (_radius - _radius * (1 - Math.sin(rotationAngleComplement)));
            final float relativeX = (float) (Math.tan(rotationAngle / 2) * relativeY);
            final PointF a = new PointF(-relativeX, -relativeY);
            final PointF b = new PointF(0, -_radius);
            final PointF c = new PointF(relativeX, -relativeY);
            path.moveTo(_centerX + a.x, actualCenterY + a.y);
            _canvas.save();
            for (int i = 0; i < _branchesCount; i++) {
                path.lineTo(_centerX + b.x, actualCenterY + b.y);
                path.lineTo(_centerX + c.x, actualCenterY + c.y);
                rotationToCenter(b, rotationAngle);
                rotationToCenter(c, rotationAngle);
            }
            _canvas.drawPath(path, paint);
            _canvas.restore();
        }

        private void rotationToCenter(final PointF _point, final double _angleRadian) {
            final float x = (float) (_point.x * Math.cos(_angleRadian) - _point.y * Math.sin(_angleRadian));
            final float y = (float) (_point.x * Math.sin(_angleRadian) + _point.y * Math.cos(_angleRadian));
            _point.x = x;
            _point.y = y;
        }
    }

}
