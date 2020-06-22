package com.opus_bd.lostandfound.Activity.LOGREG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.google.android.gms.vision.face.Face;
import com.opus_bd.lostandfound.Utils.CustomListeners;
import com.opus_bd.lostandfound.Utils.Utilities;

import static com.opus_bd.lostandfound.Utils.Constants.DETECTED_RECT;

//import com.opus_bd.lostandfound.Utils.CustomListeners;

/**
 * Graphic instance for rendering face position, orientation, and landmarks within an associated
 * graphic overlay view.
 */
class FaceGraphic extends GraphicOverlay.Graphic {
    private static final float FACE_POSITION_RADIUS = 10.0f;
    private static final float ID_TEXT_SIZE = 40.0f;
    private static final float ID_Y_OFFSET = 50.0f;
    private static final float ID_X_OFFSET = -50.0f;
    private static final float BOX_STROKE_WIDTH = 5.0f;

    private static final int COLOR_CHOICES[] = {
            Color.BLUE,
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
            Color.WHITE,
            Color.YELLOW
    };
    private static int mCurrentColorIndex = 0;

    private Paint mFacePositionPaint;
    private Paint mIdPaint;
    private Paint mBoxPaint;

    private volatile Face mFace;
    private int mFaceId;
    private float mFaceHappiness;

    FaceGraphic(GraphicOverlay overlay) {
        super(overlay);

        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];

        mFacePositionPaint = new Paint();
        mFacePositionPaint.setColor(selectedColor);

        mIdPaint = new Paint();
        mIdPaint.setColor(selectedColor);
        mIdPaint.setTextSize(ID_TEXT_SIZE);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(selectedColor);
        mBoxPaint.setStyle(Paint.Style.STROKE);
        mBoxPaint.setStrokeWidth(BOX_STROKE_WIDTH);
    }

    void setId(int id) {
        mFaceId = id;
    }


    /**
     * Updates the face instance from the detection of the most recent frame.  Invalidates the
     * relevant portions of the overlay to trigger a redraw.
     */
    void updateFace(Face face) {
        mFace = face;
        postInvalidate();
    }

    /**
     * Draws the face annotations for position on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        Face face = mFace;

        if (face == null) {
            return;
        }


        // Draws a circle at the position of the detected face, with the face's track id below.
        float x = translateX(face.getPosition().x + face.getWidth() / 2);
        float y = translateY(face.getPosition().y + face.getHeight() / 2);
        canvas.drawCircle(x, y, FACE_POSITION_RADIUS, mFacePositionPaint);

        // canvas.drawText("id: " + mFaceId, x + ID_X_OFFSET, y + ID_Y_OFFSET, mIdPaint);
        //  canvas.drawText("happiness: " + String.format("%.2f", face.getIsSmilingProbability()), x - ID_X_OFFSET, y - ID_Y_OFFSET, mIdPaint);
        //  canvas.drawText("right eye: " + String.format("%.2f", face.getIsRightEyeOpenProbability()), x + ID_X_OFFSET * 2, y + ID_Y_OFFSET * 2, mIdPaint);
        //  canvas.drawText("left eye: " + String.format("%.2f", face.getIsLeftEyeOpenProbability()), x - ID_X_OFFSET*2, y - ID_Y_OFFSET*2, mIdPaint);

        // Draws a bounding box around the face.
        float xOffset = scaleX(face.getWidth() / 2.0f);
        float yOffset = scaleY(face.getHeight() / 2.0f);
        float left = x - xOffset;
        float top = y - yOffset;
        float right = x + xOffset;
        float bottom = y + yOffset;
        canvas.drawRect(left, top, right, bottom, mBoxPaint);


        Log.i("mkl", "Face found");
        Log.i("mkl", "Face found");
        Utilities.showLogcatMessage("Left" + left);
        Utilities.showLogcatMessage("top" + top);
        Utilities.showLogcatMessage("Left" + left);
        Utilities.showLogcatMessage("right" + right);
        Utilities.showLogcatMessage("bottom" + bottom);
        Rect rect = new Rect((int) left, (int) top, (int) right, (int) bottom);
        DETECTED_RECT = rect;
        CustomListeners.myFaceDetectedListener.onFaceFound();

//        if ((x > Constants.SCEEEN_HEIGHT/3 && y > Constants.SCEEEN_WIDTH/3)  ){
//            CustomListeners.myFaceDetectedListener.onFaceFound();
//        }else if((x < Constants.SCEEEN_HEIGHT*2/3 && y > Constants.SCEEEN_WIDTH/3)){
//           CustomListeners.myFaceDetectedListener.onFaceFound();
//        }else if(( x > Constants.SCEEEN_HEIGHT/3 && y < Constants.SCEEEN_WIDTH*2/3 )){
//            CustomListeners.myFaceDetectedListener.onFaceFound();
//
//        }else if((x < Constants.SCEEEN_HEIGHT*2/3 && y < Constants.SCEEEN_WIDTH*2/3)){
//           // CustomListeners.myFaceDetectedListener.onFaceFound();


//        } else {
//           // Utilities.showLogcatMessage("Not in centre   x: " + x+" y: " +y);
//        }
    }
}