package com.export;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class PixelUtil {
	public static final short EXCEL_COLUMN_WIDTH_FACTOR = 256;
	public static final short EXCEL_ROW_HEIGHT_FACTOR = 20;
	public static final int UNIT_OFFSET_LENGTH = 7;
	public static final int[] UNIT_OFFSET_MAP = new int[] { 0, 36, 73, 109, 146, 182, 219 };

	public static short pixel2WidthUnits(int pxs) {
		short widthUnits = (short) (EXCEL_COLUMN_WIDTH_FACTOR * (pxs / UNIT_OFFSET_LENGTH));
		widthUnits += UNIT_OFFSET_MAP[(pxs % UNIT_OFFSET_LENGTH)];
		return widthUnits;
	}

	public static int widthUnits2Pixel(short widthUnits) {
		int pixels = (widthUnits / EXCEL_COLUMN_WIDTH_FACTOR) * UNIT_OFFSET_LENGTH;
		int offsetWidthUnits = widthUnits % EXCEL_COLUMN_WIDTH_FACTOR;
		pixels += Math.floor((float) offsetWidthUnits / ((float) EXCEL_COLUMN_WIDTH_FACTOR / UNIT_OFFSET_LENGTH));
		return pixels;
	}

	public static int heightUnits2Pixel(short heightUnits) {
		int pixels = (heightUnits / EXCEL_ROW_HEIGHT_FACTOR);
		int offsetWidthUnits = heightUnits % EXCEL_ROW_HEIGHT_FACTOR;
		pixels += Math.floor((float) offsetWidthUnits / ((float) EXCEL_ROW_HEIGHT_FACTOR / UNIT_OFFSET_LENGTH));
		return pixels;
	}

	public static int poiWidthToPixels(final double widthUnits) {
		if (widthUnits <= 256) {
			return (int) Math.round((widthUnits / 28));
		} else {
			return (int) (Math.round(widthUnits * 9 / 256));
		}
	}

	public static void setColumnWidth(HSSFSheet sheet, int colIdx, double widthInTwips) {
		sheet.setColumnWidth(colIdx, (int) (441.3793d + 256d * (widthInTwips - 1d)));
	}

	public static int calculateColWidth(int width) {
		if (width > 254)
			return 65280; // Maximum allowed column width.
		if (width > 1) {
			int floor = (int) (Math.floor(((double) width) / 5));
			int factor = (30 * floor);
			int value = 450 + factor + ((width - 1) * 256);
			return value;
		} else
			return 450;
	}
}