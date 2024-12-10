package com.tecsup.demo.controladores;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.tecsup.demo.modelo.daos.*;
import com.tecsup.demo.modelo.entidades.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class FileDownloadController {

    // Repositorios Autowired para cada entidad
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DuenoRestauranteRepository duenoRestauranteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RepartidorRepository repartidorRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private MotivoRepository motivoRepository;

    // ------------------------------
    // Métodos para la entidad Usuario
    // ------------------------------

    // Descargar Usuarios en PDF
    @GetMapping("/download/usuarios/pdf")
    public ResponseEntity<ByteArrayResource> downloadUsuariosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Usuarios").setFontSize(18));

        List<Usuario> usuarios = usuarioRepository.findAll();

        for (Usuario usuario : usuarios) {
            document.add(new Paragraph("ID: " + usuario.getId()));
            document.add(new Paragraph("Nombre: " + usuario.getNombre()));
            document.add(new Paragraph("Correo Electrónico: " + usuario.getCorreoElectronico()));
            document.add(new Paragraph("Teléfono: " + usuario.getTelefono()));
            document.add(new Paragraph("Google ID: " + usuario.getGoogleId()));
            document.add(new Paragraph("Token Acceso: " + (usuario.getTokenAcceso() != null ? usuario.getTokenAcceso() : "N/A")));
            document.add(new Paragraph("Token Refresh: " + (usuario.getTokenRefresh() != null ? usuario.getTokenRefresh() : "N/A")));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_usuarios.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Usuarios en XLS
    @GetMapping("/download/usuarios/xls")
    public ResponseEntity<ByteArrayResource> downloadUsuariosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Usuarios");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Correo Electrónico");
        headerRow.createCell(3).setCellValue("Teléfono");
        headerRow.createCell(4).setCellValue("Google ID");
        headerRow.createCell(5).setCellValue("Token Acceso");
        headerRow.createCell(6).setCellValue("Token Refresh");

        List<Usuario> usuarios = usuarioRepository.findAll();

        int rowCount = 1;
        for (Usuario usuario : usuarios) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(usuario.getId());
            row.createCell(1).setCellValue(usuario.getNombre());
            row.createCell(2).setCellValue(usuario.getCorreoElectronico());
            row.createCell(3).setCellValue(usuario.getTelefono() != null ? usuario.getTelefono() : "");
            row.createCell(4).setCellValue(usuario.getGoogleId());
            row.createCell(5).setCellValue(usuario.getTokenAcceso() != null ? usuario.getTokenAcceso() : "");
            row.createCell(6).setCellValue(usuario.getTokenRefresh() != null ? usuario.getTokenRefresh() : "");
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 7; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_usuarios.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad DuenoRestaurante
    // ------------------------------

    // Descargar Dueños de Restaurantes en PDF
    @GetMapping("/download/duenos/pdf")
    public ResponseEntity<ByteArrayResource> downloadDuenosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Dueños de Restaurantes").setFontSize(18));

        List<DuenoRestaurante> duenos = duenoRestauranteRepository.findAll();

        for (DuenoRestaurante dueno : duenos) {
            document.add(new Paragraph("ID: " + dueno.getId()));
            document.add(new Paragraph("Nombre: " + dueno.getNombre()));
            document.add(new Paragraph("Correo Electrónico: " + dueno.getCorreoElectronico()));
            document.add(new Paragraph("Teléfono: " + dueno.getTelefono()));
            document.add(new Paragraph("Google ID: " + dueno.getGoogleId()));
            document.add(new Paragraph("Token Acceso: " + (dueno.getTokenAcceso() != null ? dueno.getTokenAcceso() : "N/A")));
            document.add(new Paragraph("Token Refresh: " + (dueno.getTokenRefresh() != null ? dueno.getTokenRefresh() : "N/A")));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_duenos_restaurantes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Dueños de Restaurantes en XLS
    @GetMapping("/download/duenos/xls")
    public ResponseEntity<ByteArrayResource> downloadDuenosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dueños Restaurantes");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Correo Electrónico");
        headerRow.createCell(3).setCellValue("Teléfono");
        headerRow.createCell(4).setCellValue("Google ID");
        headerRow.createCell(5).setCellValue("Token Acceso");
        headerRow.createCell(6).setCellValue("Token Refresh");

        List<DuenoRestaurante> duenos = duenoRestauranteRepository.findAll();

        int rowCount = 1;
        for (DuenoRestaurante dueno : duenos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(dueno.getId());
            row.createCell(1).setCellValue(dueno.getNombre());
            row.createCell(2).setCellValue(dueno.getCorreoElectronico());
            row.createCell(3).setCellValue(dueno.getTelefono() != null ? dueno.getTelefono() : "");
            row.createCell(4).setCellValue(dueno.getGoogleId());
            row.createCell(5).setCellValue(dueno.getTokenAcceso() != null ? dueno.getTokenAcceso() : "");
            row.createCell(6).setCellValue(dueno.getTokenRefresh() != null ? dueno.getTokenRefresh() : "");
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 7; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_duenos_restaurantes.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad Restaurante
    // ------------------------------

    // Descargar Restaurantes en PDF
    @GetMapping("/download/restaurantes/pdf")
    public ResponseEntity<ByteArrayResource> downloadRestaurantesPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Restaurantes").setFontSize(18));

        List<Restaurante> restaurantes = restauranteRepository.findAll();

        for (Restaurante restaurante : restaurantes) {
            document.add(new Paragraph("ID: " + restaurante.getId()));
            document.add(new Paragraph("Nombre: " + restaurante.getNombre()));
            document.add(new Paragraph("Dirección: " + restaurante.getDireccion()));
            document.add(new Paragraph("Teléfono: " + restaurante.getTelefono()));
            document.add(new Paragraph("Tipo de Cocina: " + restaurante.getTipoCocina()));
            document.add(new Paragraph("Calificación: " + restaurante.getCalificacion()));
            document.add(new Paragraph("Dueño: " + restaurante.getDueno().getNombre()));
            document.add(new Paragraph("Delivery Dentro: " + (restaurante.getDeliveryDentro() ? "Sí" : "No")));
            document.add(new Paragraph("Imagen URL: " + (restaurante.getImagenUrl() != null ? restaurante.getImagenUrl() : "N/A")));
            document.add(new Paragraph("Estado: " + restaurante.getStatus()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_restaurantes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Restaurantes en XLS
    @GetMapping("/download/restaurantes/xls")
    public ResponseEntity<ByteArrayResource> downloadRestaurantesXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Restaurantes");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Dirección");
        headerRow.createCell(3).setCellValue("Teléfono");
        headerRow.createCell(4).setCellValue("Tipo de Cocina");
        headerRow.createCell(5).setCellValue("Calificación");
        headerRow.createCell(6).setCellValue("Dueño");
        headerRow.createCell(7).setCellValue("Delivery Dentro");
        headerRow.createCell(8).setCellValue("Imagen URL");
        headerRow.createCell(9).setCellValue("Estado");

        List<Restaurante> restaurantes = restauranteRepository.findAll();

        int rowCount = 1;
        for (Restaurante restaurante : restaurantes) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(restaurante.getId());
            row.createCell(1).setCellValue(restaurante.getNombre());
            row.createCell(2).setCellValue(restaurante.getDireccion() != null ? restaurante.getDireccion() : "");
            row.createCell(3).setCellValue(restaurante.getTelefono() != null ? restaurante.getTelefono() : "");
            row.createCell(4).setCellValue(restaurante.getTipoCocina() != null ? restaurante.getTipoCocina() : "");
            row.createCell(5).setCellValue(restaurante.getCalificacion() != null ? restaurante.getCalificacion() : 0);
            row.createCell(6).setCellValue(restaurante.getDueno().getNombre());
            row.createCell(7).setCellValue(restaurante.getDeliveryDentro() ? "Sí" : "No");
            row.createCell(8).setCellValue(restaurante.getImagenUrl() != null ? restaurante.getImagenUrl() : "");
            row.createCell(9).setCellValue(restaurante.getStatus());
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_restaurantes.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad Repartidor
    // ------------------------------

    // Descargar Repartidores en PDF
    @GetMapping("/download/repartidores/pdf")
    public ResponseEntity<ByteArrayResource> downloadRepartidoresPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Repartidores").setFontSize(18));

        List<Repartidor> repartidores = repartidorRepository.findAll();

        for (Repartidor repartidor : repartidores) {
            document.add(new Paragraph("ID: " + repartidor.getId()));
            document.add(new Paragraph("Nombre: " + repartidor.getNombre()));
            document.add(new Paragraph("Teléfono: " + repartidor.getTelefono()));
            document.add(new Paragraph("Vehículo: " + repartidor.getVehiculo()));
            document.add(new Paragraph("Restaurante: " + repartidor.getRestaurante().getNombre()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_repartidores.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Repartidores en XLS
    @GetMapping("/download/repartidores/xls")
    public ResponseEntity<ByteArrayResource> downloadRepartidoresXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Repartidores");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Teléfono");
        headerRow.createCell(3).setCellValue("Vehículo");
        headerRow.createCell(4).setCellValue("Restaurante");

        List<Repartidor> repartidores = repartidorRepository.findAll();

        int rowCount = 1;
        for (Repartidor repartidor : repartidores) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(repartidor.getId());
            row.createCell(1).setCellValue(repartidor.getNombre());
            row.createCell(2).setCellValue(repartidor.getTelefono() != null ? repartidor.getTelefono() : "");
            row.createCell(3).setCellValue(repartidor.getVehiculo() != null ? repartidor.getVehiculo() : "");
            row.createCell(4).setCellValue(repartidor.getRestaurante().getNombre());
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_repartidores.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad Plato
    // ------------------------------

    // Descargar Platos en PDF
    @GetMapping("/download/platos/pdf")
    public ResponseEntity<ByteArrayResource> downloadPlatosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Platos").setFontSize(18));

        List<Plato> platos = platoRepository.findAll();

        for (Plato plato : platos) {
            document.add(new Paragraph("ID: " + plato.getId()));
            document.add(new Paragraph("Nombre: " + plato.getNombre()));
            document.add(new Paragraph("Descripción: " + plato.getDescripcion()));
            document.add(new Paragraph("Precio: $" + plato.getPrecio()));
            document.add(new Paragraph("Restaurante: " + plato.getRestaurante().getNombre()));
            document.add(new Paragraph("Imagen URL: " + (plato.getImagenUrl() != null ? plato.getImagenUrl() : "N/A")));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_platos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Platos en XLS
    @GetMapping("/download/platos/xls")
    public ResponseEntity<ByteArrayResource> downloadPlatosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Platos");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Descripción");
        headerRow.createCell(3).setCellValue("Precio");
        headerRow.createCell(4).setCellValue("Restaurante");
        headerRow.createCell(5).setCellValue("Imagen URL");

        List<Plato> platos = platoRepository.findAll();

        int rowCount = 1;
        for (Plato plato : platos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(plato.getId());
            row.createCell(1).setCellValue(plato.getNombre());
            row.createCell(2).setCellValue(plato.getDescripcion() != null ? plato.getDescripcion() : "");
            row.createCell(3).setCellValue(plato.getPrecio().doubleValue());
            row.createCell(4).setCellValue(plato.getRestaurante().getNombre());
            row.createCell(5).setCellValue(plato.getImagenUrl() != null ? plato.getImagenUrl() : "");
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_platos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad Pedido
    // ------------------------------

    // Descargar Pedidos en PDF
    @GetMapping("/download/pedidos/pdf")
    public ResponseEntity<ByteArrayResource> downloadPedidosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Pedidos").setFontSize(18));

        List<Pedido> pedidos = pedidoRepository.findAll();

        for (Pedido pedido : pedidos) {
            document.add(new Paragraph("ID: " + pedido.getId()));
            document.add(new Paragraph("Fecha y Hora: " + pedido.getFechaHora()));
            document.add(new Paragraph("Estado: " + pedido.getEstado()));
            document.add(new Paragraph("Usuario: " + pedido.getUsuario().getNombre()));
            document.add(new Paragraph("Restaurante: " + pedido.getRestaurante().getNombre()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_pedidos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Pedidos en XLS
    @GetMapping("/download/pedidos/xls")
    public ResponseEntity<ByteArrayResource> downloadPedidosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pedidos");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Fecha y Hora");
        headerRow.createCell(2).setCellValue("Estado");
        headerRow.createCell(3).setCellValue("Usuario");
        headerRow.createCell(4).setCellValue("Restaurante");

        List<Pedido> pedidos = pedidoRepository.findAll();

        int rowCount = 1;
        for (Pedido pedido : pedidos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(pedido.getId());
            row.createCell(1).setCellValue(pedido.getFechaHora().toString());
            row.createCell(2).setCellValue(pedido.getEstado());
            row.createCell(3).setCellValue(pedido.getUsuario().getNombre());
            row.createCell(4).setCellValue(pedido.getRestaurante().getNombre());
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_pedidos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad DetallePedido
    // ------------------------------

    // Descargar Detalles de Pedido en PDF
    @GetMapping("/download/detallepedidos/pdf")
    public ResponseEntity<ByteArrayResource> downloadDetallePedidosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Detalles de Pedido").setFontSize(18));

        List<DetallePedido> detalles = detallePedidoRepository.findAll();

        for (DetallePedido detalle : detalles) {
            document.add(new Paragraph("ID: " + detalle.getId()));
            document.add(new Paragraph("Cantidad: " + detalle.getCantidad()));
            document.add(new Paragraph("Precio Total: $" + detalle.getPrecioTotal()));
            document.add(new Paragraph("Pedido ID: " + detalle.getPedido().getId()));
            document.add(new Paragraph("Plato: " + detalle.getPlato().getNombre()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_detalle_pedidos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Detalles de Pedido en XLS
    @GetMapping("/download/detallepedidos/xls")
    public ResponseEntity<ByteArrayResource> downloadDetallePedidosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("DetallePedidos");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Cantidad");
        headerRow.createCell(2).setCellValue("Precio Total");
        headerRow.createCell(3).setCellValue("Pedido ID");
        headerRow.createCell(4).setCellValue("Plato");

        List<DetallePedido> detalles = detallePedidoRepository.findAll();

        int rowCount = 1;
        for (DetallePedido detalle : detalles) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(detalle.getId());
            row.createCell(1).setCellValue(detalle.getCantidad());
            row.createCell(2).setCellValue(detalle.getPrecioTotal().doubleValue());
            row.createCell(3).setCellValue(detalle.getPedido().getId());
            row.createCell(4).setCellValue(detalle.getPlato().getNombre());
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_detalle_pedidos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad Entrega
    // ------------------------------



    // ------------------------------
    // Métodos para la entidad Administrador
    // ------------------------------

    // Descargar Administradores en PDF
    @GetMapping("/download/administradores/pdf")
    public ResponseEntity<ByteArrayResource> downloadAdministradoresPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Administradores").setFontSize(18));

        List<Administrador> administradores = administradorRepository.findAll();

        for (Administrador admin : administradores) {
            document.add(new Paragraph("ID: " + admin.getId()));
            document.add(new Paragraph("Nombre: " + admin.getNombre()));
            document.add(new Paragraph("Correo Electrónico: " + admin.getCorreoElectronico()));
            document.add(new Paragraph("Teléfono: " + admin.getTelefono()));
            // Omite la contraseña por seguridad
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_administradores.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Administradores en XLS
    @GetMapping("/download/administradores/xls")
    public ResponseEntity<ByteArrayResource> downloadAdministradoresXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Administradores");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Correo Electrónico");
        headerRow.createCell(3).setCellValue("Teléfono");
        // Omite la columna de Contraseña

        List<Administrador> administradores = administradorRepository.findAll();

        int rowCount = 1;
        for (Administrador admin : administradores) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(admin.getId());
            row.createCell(1).setCellValue(admin.getNombre());
            row.createCell(2).setCellValue(admin.getCorreoElectronico());
            row.createCell(3).setCellValue(admin.getTelefono() != null ? admin.getTelefono() : "");
            // Omite la contraseña
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_administradores.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad Motivo
    // ------------------------------

    // Descargar Motivos en PDF
    @GetMapping("/download/motivos/pdf")
    public ResponseEntity<ByteArrayResource> downloadMotivosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Motivos").setFontSize(18));

        List<Motivo> motivos = motivoRepository.findAll();

        for (Motivo motivo : motivos) {
            document.add(new Paragraph("ID: " + motivo.getId()));
            document.add(new Paragraph("Descripción: " + motivo.getDescripcion()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_motivos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Descargar Motivos en XLS
    @GetMapping("/download/motivos/xls")
    public ResponseEntity<ByteArrayResource> downloadMotivosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Motivos");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Descripción");

        List<Motivo> motivos = motivoRepository.findAll();

        int rowCount = 1;
        for (Motivo motivo : motivos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(motivo.getId());
            row.createCell(1).setCellValue(motivo.getDescripcion() != null ? motivo.getDescripcion() : "");
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_motivos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // ------------------------------
    // Métodos para la entidad Entrega
    // ------------------------------



    // ------------------------------
    // Métodos para la entidad Repartidor
    // Ya están incluidos arriba en las secciones correspondientes.
    // ------------------------------

    // ------------------------------
    // Métodos para la entidad Administrador
    // Ya están incluidos arriba en las secciones correspondientes.
    // ------------------------------

    // ------------------------------
    // Métodos para la entidad Motivo
    // Ya están incluidos arriba en las secciones correspondientes.
    // ------------------------------

    // ------------------------------
    // Métodos para la entidad DetallePedido
    // Ya están incluidos arriba en las secciones correspondientes.
    // ------------------------------

    // ------------------------------
    // Métodos para otras entidades
    // Si tienes más entidades, sigue el mismo patrón.
    // ------------------------------
}
