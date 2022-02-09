/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pdf_Encrypter;
import com.qoppa.pdfWriter.PDFPrinterJob;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Vector;
import modelo.ArchivoCSV;
import modelo.Alumno;


public class PDFWriter implements Printable {
	private int m_CurrentPage;
	private int m_CurrentPageStartRow;
	private int m_CurrentPageEndRow;
	private final static int DEFAULT_COLUMN_WIDTH = 150;
	private final static int CELL_MARGIN_X = 8;
	private final static int CELL_MARGIN_Y = 8;

	private Vector m_Data;
	private int m_ColumnWidths[];
	private boolean m_DrawGrid;

	public PDFWriter(Vector data, int[] colWidths, boolean drawGrid) {
		super();
		m_Data = data;
		m_ColumnWidths = colWidths;
		m_DrawGrid = drawGrid;
	}
        
	
	public static Vector initData(ArrayList<Alumno> alumnado) {  
	Vector data = new Vector();

	// Initialize data
	for (Alumno alumno: alumnado) {
		Vector rowData = new Vector();
		for (int col = 0; col < 3; ++col) {
                      switch(col){
                          case 0:
                                    String.valueOf(rowData.add(alumno.getMatricula()));
                                    break;
                                case 1:
                                    String.valueOf(rowData.add("Diseño de software"));
                                    break;
                                case 2: 
                                    String.valueOf(rowData.add(String.valueOf(alumno.getCalificacion())));
                            }
			}
			data.addElement(rowData);
		}
		return data;
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		int lineHeight = g.getFontMetrics().getHeight();

		// Reset current pos
		int currentRow = 0;
		if (pageIndex == 0) {
			// Need to do this in case the instance of this class
			// gets used multiple times to print a string
			m_CurrentPage = 0;
			m_CurrentPageStartRow = 0;
		}
		// Need to do this because Java PrinterJob can call this
		// method multiple times for the same page;
		else if (m_CurrentPage == pageIndex) {
			currentRow = m_CurrentPageStartRow;
		} else {
			currentRow = m_CurrentPageEndRow + 1;
			m_CurrentPageStartRow = currentRow;
		}

		// If we're out of lines, tell the PrinterJob we're done
		if (currentRow >= m_Data.size()) {
			return Printable.NO_SUCH_PAGE;
		}

		// Loop through lines until we fill the page
		int currentY = (int) (pf.getImageableY() + lineHeight);
		while (currentRow < m_Data.size() &&
				currentY + lineHeight < pf.getImageableY() + pf.getImageableHeight()) {
			// Draw the next line
			int currentX = (int) pf.getImageableX();
			Vector nextRow = (Vector) m_Data.elementAt(currentRow);
			for (int col = 0; col < nextRow.size(); ++col) {
				String cellString = (String) nextRow.elementAt(col);
				g.drawString(cellString, currentX + CELL_MARGIN_X, currentY + CELL_MARGIN_Y);

				int colWidth = DEFAULT_COLUMN_WIDTH;
				if (m_ColumnWidths != null && m_ColumnWidths.length > col) {
					colWidth = m_ColumnWidths[col];
				}

				// Draw grid if needed
				if (m_DrawGrid) {
					g.drawRect(currentX, currentY - (lineHeight / 2), colWidth, lineHeight);
				}

				// Advance x
				currentX += colWidth;
			}

			// Advance to the next line
			++currentRow;
			currentY += lineHeight;
		}

		// Save the ned line and current page
		// Again, we have to do this because of multiple calls for the same page.
		m_CurrentPageEndRow = currentRow;
		m_CurrentPage = pageIndex;

		return Printable.PAGE_EXISTS;
	}
}

