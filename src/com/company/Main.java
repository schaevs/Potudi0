package com.company;
import com.sun.media.sound.FFT;
import java.io.*;

import com.company.*;

public class Main {

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();

        /*double[] samples = new double[16384];
        for (int i = 0; i < 16384; i++){
            samples[i] = Math.sin((double)i);
        }
        */
        try {
            File file = new File("/Users/alexanderschaevitz/IdeaProjects/Potaudio Java/src/com/company/It's Goin Down x Emoji dr edit.wav");
            WavFile wavFile = WavFile.openWavFile(file);
            wavFile.display();
            int numChannels = wavFile.getNumChannels();
            long numFrames = wavFile.getNumFrames();
            // Create a buffer of 100 frames
            double[] buffer = new double[16384 * numChannels];

            int framesRead;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;

            do
            {
                // Read frames into buffer
                framesRead = wavFile.readFrames(buffer, 1);

                // Loop through frames and look for minimum and maximum value
                for (int s=0 ; s<framesRead * numChannels ; s++)
                {
                    if (buffer[s] > max) max = buffer[s];
                    if (buffer[s] < min) min = buffer[s];
                }
                getMagnitudes(buffer);

            }
            while (framesRead != 0);

            wavFile.close();

            // Output the minimum and maximum value
            System.out.printf("Min: %f, Max: %f\n", min, max);

        }
        catch (Exception e)
        {
            System.err.println(e);
        }

        //String urlAsString = "file:/Volumes/TOO $HORT/1706/It's Goin Down x Emoji dr edit.wav";
        //URL url = new URL(urlAsString);
        //WaveFileReader waveFileReader = new WaveFileReader();

       // double[] result = getMagnitudes(samples);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) );



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
            //System.out.println(mag[i / 2]);
        }

        return mag;
    }


}
