/*******************************************************************************
 * Copyright 2014 Pawel Pastuszak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package net.indiearmory.evolsim;

import com.badlogic.gdx.graphics.Color;

public class ColorUtils {
    /** Converts HSV color sytem to RGB
     *
     * @return RGB values in Libgdx Color class
     */
    public static Color HSV_to_RGB (float h, float s, float v) {
        int r, g, b;
        int i;
        float f, p, q, t;
        h = (float)Math.max(0.0, Math.min(360.0, h));
        s = (float)Math.max(0.0, Math.min(100.0, s));
        v = (float)Math.max(0.0, Math.min(100.0, v));
        s /= 100;
        v /= 100;

        h /= 60;
        i = (int)Math.floor(h);
        f = h - i;
        p = v * (1 - s);
        q = v * (1 - s * f);
        t = v * (1 - s * (1 - f));
        switch (i) {
            case 0:
                r = Math.round(255 * v);
                g = Math.round(255 * t);
                b = Math.round(255 * p);
                break;
            case 1:
                r = Math.round(255 * q);
                g = Math.round(255 * v);
                b = Math.round(255 * p);
                break;
            case 2:
                r = Math.round(255 * p);
                g = Math.round(255 * v);
                b = Math.round(255 * t);
                break;
            case 3:
                r = Math.round(255 * p);
                g = Math.round(255 * q);
                b = Math.round(255 * v);
                break;
            case 4:
                r = Math.round(255 * t);
                g = Math.round(255 * p);
                b = Math.round(255 * v);
                break;
            default:
                r = Math.round(255 * v);
                g = Math.round(255 * p);
                b = Math.round(255 * q);
        }
        return new Color(r / 255.0f, g / 255.0f, b / 255.0f, 1);
    }

    /**
     * Takes r,g,b in range [0,1].
     * @param r
     * @param g
     * @param b
     * @return hue in range [0,1].
     */
    public static double[] RGB_to_HSV(float r, float g, float b){
        double R = r;
        double G = g;
        double B = b;

        double min = Math.min(Math.min(R, G), B);
        double max = Math.max(Math.max(R, G), B);
        double delta = max - min;

        double H = max;
        double S = max;
        double V = max;

        if(delta == 0){
            H = 0;
            S = 0;
        }else{
            S = delta / max;

            double delR = ( ( ( max - R ) / 6 ) + ( delta / 2 ) ) / delta;
            double delG = ( ( ( max - G ) / 6 ) + ( delta / 2 ) ) / delta;
            double delB = ( ( ( max - B ) / 6 ) + ( delta / 2 ) ) / delta;

            if(R == max){
                H = delB - delG;
            }else if(G == max){
                H = (1/3) + delR - delB;
            }else if(B == max){
                H = (2/3) + delG - delR;
            }

            if(H < 0) H += 1;
            if(H > 1) H -= 1;
        }

        double[] hsv = new double[3];
        hsv[0] = H;
        hsv[1] = S;
        hsv[2] = V;

        return hsv;
    }
}