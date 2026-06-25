package org.firstinspires.ftc.teamcode.robot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DennyLUT {

    private final List<Double> inputs  = new ArrayList<>();
    private final List<Double> outputs = new ArrayList<>();

    public DennyLUT(){}

    /**
     * Adds a new (input, output) point to the table.
     * Points are inserted in sorted order by input value.
     * Duplicate input values overwrite the existing output.
     *
     * @param input  the x value (independent variable)
     * @param output the corresponding y value (dependent variable)
     */

    public void add(double input, double output) {
        // Find the correct sorted position
        for (int i = 0; i < inputs.size(); i++) {
            if (Double.compare(inputs.get(i), input) == 0) {
                // Overwrite duplicate
                outputs.set(i, output);
                return;
            }
            if (inputs.get(i) > input) {
                inputs.add(i, input);
                outputs.add(i, output);
                return;
            }
        }
        // Append at the end (largest input so far)
        inputs.add(input);
        outputs.add(output);
    }

    /**
     * Returns the interpolated output for the given input value.
     *
     * - If the table is empty, throws IllegalStateException.
     * - If only one point exists, returns its output regardless of input.
     * - If input is below the first point, clamps to the first output (no extrapolation).
     * - If input is above the last point, clamps to the last output (no extrapolation).
     * - Otherwise, finds the surrounding segment [x1,x2] and linearly interpolates:
     *     output = s1 + (x - x1) / (x2 - x1) * (s2 - s1)
     *
     * @param x the input value to look up
     * @return  the interpolated output
     */
    public double get(double x) {
        if (inputs.isEmpty()) {
            throw new IllegalStateException("LUT is empty. Add at least one point before calling get().");
        }

        int n = inputs.size();

        // Single point: return its output
        if (n == 1) {
            return outputs.get(0);
        }

        for (int i = 1; i < n; i++) {
            double x1 = inputs.get(i - 1);
            double x2 = inputs.get(i);

            if (x < x2 || i == n - 1) {
                double s1 = outputs.get(i - 1);
                double s2 = outputs.get(i);
                return s1 + (x - x1) / (x2 - x1) * (s2 - s1);
            }
        }

        // Fallback (should not be reached)
        return outputs.get(n - 1);
    }

    /**
     * Returns the number of points currently in the table.
     */
    public int size() {
        return inputs.size();
    }

    /**
     * Removes all points from the table.
     */
    public void clear() {
        inputs.clear();
        outputs.clear();
    }

    /**
     * Returns a readable summary of all stored points.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InterpolationLUT [\n");
        for (int i = 0; i < inputs.size(); i++) {
            sb.append(String.format("  (%.4f -> %.4f)\n", inputs.get(i), outputs.get(i)));
        }
        sb.append("]");
        return sb.toString();
    }
}
