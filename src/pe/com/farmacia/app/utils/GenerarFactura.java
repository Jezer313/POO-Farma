/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.farmacia.app.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import pe.com.farmacia.app.modelo.Factura;
/**
 *
 * @author alum.fial1
 */
public class GenerarFactura {
    public static void generarPDF(Factura facturaPrincipal, List<Factura> detalles, String nombreCliente, String nombreFarmaceutico) throws Exception {
        File carpeta = new File("facturas_pdf");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String codigoFactura = facturaPrincipal.getCodigo();
        File archivoPDF = new File(carpeta, "Factura_" + codigoFactura + ".pdf");

        Document documento = new Document(PageSize.A4, 40, 40, 40, 40);
        PdfWriter.getInstance(documento, new FileOutputStream(archivoPDF));
        documento.open();

        Font fuenteTitulo = new Font(Font.HELVETICA, 18, Font.BOLD, new Color(37, 99, 166));
        Font fuenteSubtitulo = new Font(Font.HELVETICA, 12, Font.BOLD, Color.DARK_GRAY);
        Font fuenteNormal = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.BLACK);
        Font fuenteCabecera = new Font(Font.HELVETICA, 11, Font.BOLD, Color.WHITE);
        Font fuenteTotal = new Font(Font.HELVETICA, 13, Font.BOLD, new Color(8, 122, 69));

        Paragraph titulo = new Paragraph("FARMACIA \"EL TILÍN\"", fuenteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);

        Paragraph subtitulo = new Paragraph("N.°: " + codigoFactura + "\n\n", fuenteSubtitulo);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(subtitulo);

        documento.add(new Paragraph("DATOS DE LA FACTURA", fuenteSubtitulo));
        documento.add(new Paragraph("Cliente: " + nombreCliente, fuenteNormal));
        documento.add(new Paragraph("Atendido por: " + nombreFarmaceutico, fuenteNormal));
        documento.add(new Paragraph("Fecha: " + facturaPrincipal.getFecha() + "\n\n", fuenteNormal));

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{4f, 1.5f, 2f, 2f});

        String[] encabezados = {"Producto", "Cant.", "Precio", "Subtotal"};
        for (String enc : encabezados) {
            PdfPCell celda = new PdfPCell(new Phrase(enc, fuenteCabecera));
            celda.setBackgroundColor(new Color(37, 99, 166));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(8);
            tabla.addCell(celda);
        }

        double totalGeneral = 0.0;

        for (Factura item : detalles) {
            PdfPCell cProducto = new PdfPCell(new Phrase(item.getDescripcion(), fuenteNormal));
            cProducto.setPadding(6);
            cProducto.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cCant = new PdfPCell(new Phrase(String.valueOf(item.getCantidad()), fuenteNormal));
            cCant.setPadding(6);
            cCant.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cPrecio = new PdfPCell(new Phrase(String.format("S/ %.2f", item.getPreciounitario()), fuenteNormal));
            cPrecio.setPadding(6);
            cPrecio.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cSubtotal = new PdfPCell(new Phrase(String.format("S/ %.2f", item.getCostototal()), fuenteNormal));
            cSubtotal.setPadding(6);
            cSubtotal.setHorizontalAlignment(Element.ALIGN_RIGHT);

            tabla.addCell(cProducto);
            tabla.addCell(cCant);
            tabla.addCell(cPrecio);
            tabla.addCell(cSubtotal);

            totalGeneral += item.getCostototal();
        }

        documento.add(tabla);

        Paragraph pTotal = new Paragraph(String.format("\nTOTAL A PAGAR:   S/ %.2f", totalGeneral), fuenteTotal);
        pTotal.setAlignment(Element.ALIGN_RIGHT);
        documento.add(pTotal);

        documento.close();

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(archivoPDF);
        }
    }
}
