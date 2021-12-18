package de.unistuttgart.iste.sopraws20.api.statistic;

import java.util.List;

public final class Calculator {
	// function that returns correlation coefficient.
	public static float simpleCorrelationCoefficient(float xArray[], float yArray[]) {
		// check if array are same length
		if (!(xArray.length == yArray.length)) {
			throw new IllegalArgumentException();
		}

		int length = xArray.length;
		float sum_X = 0;
		float sum_Y = 0;
		float sum_XY = 0;
		float squareSum_X = 0;
		float squareSum_Y = 0;

		for (int i = 0; i < length; i++) {
			// sum of elements of array X.
			sum_X = sum_X + xArray[i];

			// sum of elements of array Y.
			sum_Y = sum_Y + yArray[i];

			// sum of X[i] * Y[i].
			sum_XY = sum_XY + xArray[i] * yArray[i];

			// sum of square of array elements.
			squareSum_X = squareSum_X + xArray[i] * xArray[i];
			squareSum_Y = squareSum_Y + yArray[i] * yArray[i];
		}

		// use formula for calculating correlation coefficient.
		return (float) (length * sum_XY - sum_X * sum_Y)
				/ (float) (Math.sqrt((length * squareSum_X - sum_X * sum_X) * (length * squareSum_Y - sum_Y * sum_Y)));
	}

	// cut longer array off before cumputing corr
	// if spacing or starting off data is different use different method
	public static float cutInputCorrelationCoefficient(float xArray[], float yArray[]) {
		int length = yArray.length;
		if (xArray.length == yArray.length) {
			return simpleCorrelationCoefficient(xArray, yArray);
		} else if (xArray.length > yArray.length) {
			length = yArray.length;
		}

		float[] newXArray = new float[length];
		float[] newYArray = new float[length];
		System.arraycopy(xArray, 0, newXArray, 0, length);
		System.arraycopy(yArray, 0, newYArray, 0, length);
		return simpleCorrelationCoefficient(newXArray, newYArray);
	}

	public static float[] cryptoDataToArray(List<List<String>> coinList) {
		int length = coinList.size();
		float[] coinArray = new float[length];
		for (int i = 0; i < length; i++) {
			coinArray[i] = Float.parseFloat((coinList.get(i).get(1)));
		}
		return coinArray;
	}
}
