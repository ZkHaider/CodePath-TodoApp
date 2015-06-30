package com.todocodepathapp.custom.ui;

import android.content.Context;
import android.content.res.Resources;

import com.todocodepathapp.R;

import java.util.Random;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class MaterialColorHelper {

    private static final int MATERIAL_COLOR_COUNT = 17;

    public static int[] generateMaterialColors(Context context) {
        Resources resources = context.getResources();

        int[] colors = new int[MATERIAL_COLOR_COUNT];

        colors[0] = resources.getColor(R.color.red_500);
        colors[1] = resources.getColor(R.color.pink_500);
        colors[2] = resources.getColor(R.color.purple_500);
        colors[3] = resources.getColor(R.color.deep_purple_500);
        colors[4] = resources.getColor(R.color.indigo_500);
        colors[5] = resources.getColor(R.color.blue_500);
        colors[6] = resources.getColor(R.color.light_blue_500);
        colors[7] = resources.getColor(R.color.cyan_500);
        colors[8] = resources.getColor(R.color.teal_500);
        colors[9] = resources.getColor(R.color.green_500);
        colors[10] = resources.getColor(R.color.light_green_500);
        colors[11] = resources.getColor(R.color.light_green_500);
        colors[12] = resources.getColor(R.color.amber_500);
        colors[13] = resources.getColor(R.color.orange_500);
        colors[14] = resources.getColor(R.color.deep_orange_500);
        colors[15] = resources.getColor(R.color.brown_500);
        colors[16] = resources.getColor(R.color.blue_grey_500);

        return colors;

    }


}
