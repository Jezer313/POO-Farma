package pe.com.farmacia.app.vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pe.com.farmacia.app.dao.Clientedao;
import pe.com.farmacia.app.dao.DetalleFacturadao;
import pe.com.farmacia.app.dao.Facturadao;
import pe.com.farmacia.app.dao.Medicamentodao;
import pe.com.farmacia.app.daoimplement.Clientedaoimpl;
import pe.com.farmacia.app.daoimplement.DetalleFacturadaoimpl;
import pe.com.farmacia.app.daoimplement.Facturadaoimpl;
import pe.com.farmacia.app.daoimplement.Medicamentodaoimpl;
import pe.com.farmacia.app.modelo.Cliente;
import pe.com.farmacia.app.modelo.DetalleFactura;
import pe.com.farmacia.app.modelo.Factura;
import pe.com.farmacia.app.ventanasEmergentes.DialogoFacturaAgregar;

/**
 *
 * @author USER
 */
public class PanelFacturas extends javax.swing.JPanel {
    
    private final Facturadao facturadao = new Facturadaoimpl();
    private final DetalleFacturadao detalleFacturadao = new DetalleFacturadaoimpl();
    private final Clientedao clientedao = new Clientedaoimpl();
    private final Medicamentodao medicamentodao = new Medicamentodaoimpl();
    private List<Factura> facturasActuales = new ArrayList<>();
    
    /**
     * Creates new form PanelFacturas
     */
    
    public PanelFacturas() {
        initComponents();
        cargarFacturas();
        configurarSeleccionFactura();
    }
    
    public void cargarFacturas() {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblFacturas.getModel();
        modelo.setRowCount(0);

        facturasActuales = facturadao.listar();
        java.util.List<pe.com.farmacia.app.modelo.Cliente> clientes = clientedao.listar();

        for (pe.com.farmacia.app.modelo.Factura f : facturasActuales) {
            modelo.addRow(new Object[]{
                f.getCodigo(),
                obtenerNombreCliente(f.getId_cliente(), clientes),
                f.getFecha(),
                String.format("S/ %.2f", f.getCostototal())
            });
        }

        lblTotalFacturas.setText("Total de facturas: " + facturasActuales.size());

        if (!facturasActuales.isEmpty()) {
            tblFacturas.setRowSelectionInterval(0, 0);
            mostrarVistaPrevia(facturasActuales.get(0), clientes);
        } else {
            limpiarVistaPrevia();
        }
    }

    private void configurarSeleccionFactura() {
        tblFacturas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tblFacturas.getSelectedRow();
                if (fila >= 0 && fila < facturasActuales.size()) {
                    mostrarVistaPrevia(facturasActuales.get(fila), clientedao.listar());
                }
            }
        });
    }

    private String obtenerNombreCliente(int idCliente,
            java.util.List<pe.com.farmacia.app.modelo.Cliente> clientes) {
        for (pe.com.farmacia.app.modelo.Cliente c : clientes) {
            if (c.getId_cliente() == idCliente) {
                return c.getNombre() + " " + c.getApellido();
            }
        }
        return "Cliente #" + idCliente;
    }

    private String obtenerNombreMedicamento(int idMedicamento,
            java.util.List<pe.com.farmacia.app.modelo.Medicamento> medicamentos) {
        for (pe.com.farmacia.app.modelo.Medicamento m : medicamentos) {
            if (m.getId_medicamento() == idMedicamento) {
                return m.getNombre_comercial();
            }
        }
        return "Medicamento #" + idMedicamento;
    }

    private void mostrarVistaPrevia(pe.com.farmacia.app.modelo.Factura factura,
            java.util.List<pe.com.farmacia.app.modelo.Cliente> clientes) {
        if (factura == null) {
            limpiarVistaPrevia();
            return;
        }

        lblNumeroFactura.setText("N.º: " + factura.getCodigo());
        lblFechaFactura.setText("Fecha: " + factura.getFecha());
        lblNombreCliente.setText("Cliente: " + obtenerNombreCliente(factura.getId_cliente(), clientes));
        lblTotalFactura.setText(String.format("S/ %.2f", factura.getCostototal()));

        javax.swing.table.DefaultTableModel modeloDetalle =
                (javax.swing.table.DefaultTableModel) tblDetalleFactura.getModel();
        modeloDetalle.setRowCount(0);

        java.util.List<pe.com.farmacia.app.modelo.DetalleFactura> detalles =
                detalleFacturadao.listarPorFactura(factura.getId_factura());
        java.util.List<pe.com.farmacia.app.modelo.Medicamento> medicamentos =
                medicamentodao.listar();

        for (pe.com.farmacia.app.modelo.DetalleFactura d : detalles) {
            double subtotal = d.getCantidad() * d.getPrecio();
            modeloDetalle.addRow(new Object[]{
                obtenerNombreMedicamento(d.getId_medicamento(), medicamentos),
                String.valueOf(d.getCantidad()),
                String.format("S/ %.2f", d.getPrecio()),
                String.format("S/ %.2f", subtotal)
            });
        }
    }

    private void limpiarVistaPrevia() {
        lblNumeroFactura.setText("N.º: ---");
        lblFechaFactura.setText("Fecha: ---");
        lblNombreCliente.setText("Cliente: ---");
        lblTotalFactura.setText("S/ 0.00");
        ((javax.swing.table.DefaultTableModel) tblDetalleFactura.getModel()).setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FondoPanelFacturas = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        pnlListadoFacturas = new javax.swing.JPanel();
        lblTituloListado = new javax.swing.JLabel();
        scrollFacturas = new javax.swing.JScrollPane();
        tblFacturas = new javax.swing.JTable();
        lblTotalFacturas = new javax.swing.JLabel();
        pnlVistaPrevia = new javax.swing.JPanel();
        lblTituloVistaPrevia = new javax.swing.JLabel();
        pnlDocumentoFactura = new javax.swing.JPanel();
        lblNombreFarmacia = new javax.swing.JLabel();
        lblNumeroFactura = new javax.swing.JLabel();
        lblFechaFactura = new javax.swing.JLabel();
        lblEtiquetaCliente = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        scrollDetalleFactura = new javax.swing.JScrollPane();
        tblDetalleFactura = new javax.swing.JTable();
        lblEtiquetaTotal = new javax.swing.JLabel();
        lblTotalFactura = new javax.swing.JLabel();
        btnImprimirFactura = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setLayout(new java.awt.BorderLayout(850, 550));

        FondoPanelFacturas.setBackground(new java.awt.Color(255, 255, 255));
        FondoPanelFacturas.setMinimumSize(new java.awt.Dimension(850, 550));
        FondoPanelFacturas.setPreferredSize(new java.awt.Dimension(850, 550));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(37, 99, 166));
        lblTitulo.setText("Facturas");

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitulo.setText("Panel de administración de facturas");

        btnAgregar.setBackground(new java.awt.Color(8, 122, 69));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("+ Agregar factura");
        btnAgregar.setFocusable(false);
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        pnlListadoFacturas.setBackground(new java.awt.Color(255, 255, 255));
        pnlListadoFacturas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(223, 243, 232)));

        lblTituloListado.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTituloListado.setForeground(new java.awt.Color(37, 99, 166));
        lblTituloListado.setText("Facturas registradas");

        tblFacturas.setBackground(new java.awt.Color(255, 255, 255));
        tblFacturas.setForeground(new java.awt.Color(30, 41, 59));
        tblFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N.º Factura", "Cliente", "Fecha", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFacturas.setGridColor(new java.awt.Color(223, 243, 232));
        tblFacturas.setRowHeight(32);
        tblFacturas.setSelectionBackground(new java.awt.Color(223, 243, 232));
        tblFacturas.setSelectionForeground(new java.awt.Color(6, 78, 54));
        scrollFacturas.setViewportView(tblFacturas);

        lblTotalFacturas.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTotalFacturas.setForeground(new java.awt.Color(24, 38, 53));
        lblTotalFacturas.setText("Total de facturas: 0");

        javax.swing.GroupLayout pnlListadoFacturasLayout = new javax.swing.GroupLayout(pnlListadoFacturas);
        pnlListadoFacturas.setLayout(pnlListadoFacturasLayout);
        pnlListadoFacturasLayout.setHorizontalGroup(
            pnlListadoFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListadoFacturasLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlListadoFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloListado)
                    .addComponent(lblTotalFacturas)
                    .addComponent(scrollFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnlListadoFacturasLayout.setVerticalGroup(
            pnlListadoFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListadoFacturasLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTituloListado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlVistaPrevia.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloVistaPrevia.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTituloVistaPrevia.setForeground(new java.awt.Color(37, 99, 166));
        lblTituloVistaPrevia.setText("Vista previa de factura");

        pnlDocumentoFactura.setBackground(new java.awt.Color(255, 255, 255));
        pnlDocumentoFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(223, 243, 232)));
        pnlDocumentoFactura.setPreferredSize(new java.awt.Dimension(350, 320));

        lblNombreFarmacia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNombreFarmacia.setForeground(new java.awt.Color(30, 58, 86));
        lblNombreFarmacia.setText("FARMACIA");

        lblNumeroFactura.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNumeroFactura.setForeground(new java.awt.Color(30, 41, 59));
        lblNumeroFactura.setText("N.º: F001-000001");

        lblFechaFactura.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFechaFactura.setForeground(new java.awt.Color(100, 116, 139));
        lblFechaFactura.setText("Fecha: 23/11/2026");

        lblEtiquetaCliente.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEtiquetaCliente.setForeground(new java.awt.Color(30, 58, 86));
        lblEtiquetaCliente.setText("DATOS DEL CLIENTE");

        lblNombreCliente.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblNombreCliente.setForeground(new java.awt.Color(30, 41, 59));
        lblNombreCliente.setText("Cliente: Nombre del cliente");

        scrollDetalleFactura.setPreferredSize(new java.awt.Dimension(320, 125));

        tblDetalleFactura.setBackground(new java.awt.Color(255, 255, 255));
        tblDetalleFactura.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        tblDetalleFactura.setForeground(new java.awt.Color(30, 41, 59));
        tblDetalleFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cant.", "Precio", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetalleFactura.setGridColor(new java.awt.Color(223, 243, 232));
        tblDetalleFactura.setRowHeight(30);
        tblDetalleFactura.setSelectionBackground(new java.awt.Color(232, 241, 250));
        tblDetalleFactura.setSelectionForeground(new java.awt.Color(23, 59, 99));
        scrollDetalleFactura.setViewportView(tblDetalleFactura);

        lblEtiquetaTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEtiquetaTotal.setForeground(new java.awt.Color(30, 58, 86));
        lblEtiquetaTotal.setText("TOTAL A PAGAR:");

        lblTotalFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalFactura.setForeground(new java.awt.Color(8, 122, 69));
        lblTotalFactura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalFactura.setText("S/ 0.00");

        javax.swing.GroupLayout pnlDocumentoFacturaLayout = new javax.swing.GroupLayout(pnlDocumentoFactura);
        pnlDocumentoFactura.setLayout(pnlDocumentoFacturaLayout);
        pnlDocumentoFacturaLayout.setHorizontalGroup(
            pnlDocumentoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentoFacturaLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(pnlDocumentoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumeroFactura)
                    .addComponent(lblNombreFarmacia))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDocumentoFacturaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDocumentoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDocumentoFacturaLayout.createSequentialGroup()
                        .addComponent(lblEtiquetaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDocumentoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEtiquetaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollDetalleFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        pnlDocumentoFacturaLayout.setVerticalGroup(
            pnlDocumentoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDocumentoFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreFarmacia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblEtiquetaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollDetalleFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDocumentoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEtiquetaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        btnImprimirFactura.setBackground(new java.awt.Color(8, 122, 69));
        btnImprimirFactura.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnImprimirFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimirFactura.setText("Imprimir factura");
        btnImprimirFactura.setFocusable(false);
        btnImprimirFactura.addActionListener(this::btnImprimirFacturaActionPerformed);

        javax.swing.GroupLayout pnlVistaPreviaLayout = new javax.swing.GroupLayout(pnlVistaPrevia);
        pnlVistaPrevia.setLayout(pnlVistaPreviaLayout);
        pnlVistaPreviaLayout.setHorizontalGroup(
            pnlVistaPreviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVistaPreviaLayout.createSequentialGroup()
                .addGroup(pnlVistaPreviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVistaPreviaLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lblTituloVistaPrevia))
                    .addGroup(pnlVistaPreviaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnlVistaPreviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImprimirFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlDocumentoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );
        pnlVistaPreviaLayout.setVerticalGroup(
            pnlVistaPreviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVistaPreviaLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblTituloVistaPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDocumentoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimirFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pe/com/farmacia/app/media/imagenes/LogoFactura.png"))); // NOI18N
        jLabel1.setMinimumSize(new java.awt.Dimension(1024, 1024));
        jLabel1.setPreferredSize(new java.awt.Dimension(1024, 1024));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout FondoPanelFacturasLayout = new javax.swing.GroupLayout(FondoPanelFacturas);
        FondoPanelFacturas.setLayout(FondoPanelFacturasLayout);
        FondoPanelFacturasLayout.setHorizontalGroup(
            FondoPanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoPanelFacturasLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(FondoPanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FondoPanelFacturasLayout.createSequentialGroup()
                        .addGroup(FondoPanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FondoPanelFacturasLayout.createSequentialGroup()
                                .addComponent(pnlListadoFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(pnlVistaPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(FondoPanelFacturasLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FondoPanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtitulo)
                            .addComponent(lblTitulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))))
        );
        FondoPanelFacturasLayout.setVerticalGroup(
            FondoPanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoPanelFacturasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(FondoPanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FondoPanelFacturasLayout.createSequentialGroup()
                        .addGroup(FondoPanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FondoPanelFacturasLayout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSubtitulo)))
                        .addGap(10, 10, 10))
                    .addComponent(jSeparator2))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FondoPanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlListadoFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlVistaPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(FondoPanelFacturas, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        DialogoFacturaAgregar dialog = new DialogoFacturaAgregar(
                (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (dialog.isFacturaGuardada()) {
            cargarFacturas();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnImprimirFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirFacturaActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tblFacturas.getSelectedRow();

        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Selecciona una factura de la tabla para generar el PDF.",
                    "Sin selección",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            String codigoFactura = tblFacturas.getValueAt(filaSeleccionada, 0).toString();
            String nombreCliente = tblFacturas.getValueAt(filaSeleccionada, 1).toString();
            String fechaFactura = tblFacturas.getValueAt(filaSeleccionada, 2).toString();

            Factura facturaPrincipal = new Factura();
            facturaPrincipal.setCodigo(codigoFactura);
            facturaPrincipal.setFecha(fechaFactura);

            java.util.List<Factura> detalles = new java.util.ArrayList<>();
            javax.swing.table.DefaultTableModel modeloDetalle = (javax.swing.table.DefaultTableModel) tblDetalleFactura.getModel();

            for (int i = 0; i < modeloDetalle.getRowCount(); i++) {
                Factura item = new Factura();
                item.setDescripcion(modeloDetalle.getValueAt(i, 0).toString());
                item.setCantidad(Integer.parseInt(modeloDetalle.getValueAt(i, 1).toString()));

                String precioStr = modeloDetalle.getValueAt(i, 2).toString().replace("S/", "").trim();
                String subtotalStr = modeloDetalle.getValueAt(i, 3).toString().replace("S/", "").trim();

                item.setPreciounitario(Double.parseDouble(precioStr));
                item.setCostototal(Double.parseDouble(subtotalStr));
                detalles.add(item);
            }

            pe.com.farmacia.app.utils.GenerarFactura.generarPDF(
                    facturaPrincipal,
                    detalles,
                    nombreCliente,
                    "Administrador"
            );

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudo generar la factura en PDF: " + e.getMessage(),
                    "Error al exportar PDF",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnImprimirFacturaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FondoPanelFacturas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnImprimirFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblEtiquetaCliente;
    private javax.swing.JLabel lblEtiquetaTotal;
    private javax.swing.JLabel lblFechaFactura;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNombreFarmacia;
    private javax.swing.JLabel lblNumeroFactura;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloListado;
    private javax.swing.JLabel lblTituloVistaPrevia;
    private javax.swing.JLabel lblTotalFactura;
    private javax.swing.JLabel lblTotalFacturas;
    private javax.swing.JPanel pnlDocumentoFactura;
    private javax.swing.JPanel pnlListadoFacturas;
    private javax.swing.JPanel pnlVistaPrevia;
    private javax.swing.JScrollPane scrollDetalleFactura;
    private javax.swing.JScrollPane scrollFacturas;
    private javax.swing.JTable tblDetalleFactura;
    private javax.swing.JTable tblFacturas;
    // End of variables declaration//GEN-END:variables
}
