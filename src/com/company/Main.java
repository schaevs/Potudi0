package com.company;
import java.io.*;
import com.sun.media.sound.FFT;
import com.sun.media.sound.WaveFileReader;

import java.io.File;
import java.net.URL;
public class Main {

    public static void main(String[] args) {
        /*double[] samples = new double[16384];
        for (int i = 0; i < 16384; i++){
            samples[i] = Math.sin((double)i);
        }
        */

        WavFile wavFile = WavFile.newWavFile("/Volumes/TOO $HORT/1706/It's Goin Down x Emoji dr edit.wav");


        //String urlAsString = "file:/Volumes/TOO $HORT/1706/It's Goin Down x Emoji dr edit.wav";
        //URL url = new URL(urlAsString);
        //WaveFileReader waveFileReader = new WaveFileReader();

       // double[] result = getMagnitudes(samples);


    }
    static public double[] getMagnitudes(double[] amplitudes) {

        int sampleSize = amplitudes.length;

        // call the fft and transform the complex numbers
        FFT fft = new FFT(sampleSize / 2, -1);
        fft.transform(amplitudes);
        // end call the fft and transform the complex numbers

        double[] complexNumbers = amplitudes;

        // even indexes (0,2,4,6,...) are real parts
        // odd indexes (1,3,5,7,...) are img parts
        int indexSize = sampleSize / 2;

        // FFT produces a transformed pair of arrays where the first half of the
        // values represent positive frequency components and the second half
        // represents negative frequency components.
        // we omit the negative ones
        int positiveSize = indexSize / 2;

        double[] mag = new double[positiveSize];
        for (int i = 0; i < indexSize; i += 2) {
            mag[i / 2] = Math.sqrt(complexNumbers[i] * complexNumbers[i] + complexNumbers[i + 1] * complexNumbers[i + 1]);
            System.out.println(mag[i / 2]);
        }

        return mag;
    }


}
