package com.universidad.pedidos;

import com.universidad.pedidos.modelo.Pedido;
import com.universidad.pedidos.observer.GestorPedidosService;
import com.universidad.pedidos.strategy.CarritoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosApp implements CommandLineRunner {

    private final GestorPedidosService gestor;
    private final CarritoService carrito;

    public PedidosApp(GestorPedidosService gestor, CarritoService carrito) {
        this.gestor = gestor;
        this.carrito = carrito;
    }

    public static void main(String[] args) {
        SpringApplication.run(PedidosApp.class, args);
    }

    @Override
    public void run(String... args) {
        // Probar Observer
        System.out.println("=== OBSERVER: CONFIRMAR PEDIDO ===");
        Pedido p1 = new Pedido("P-001", "PROD-A", 2, 80000.0, true);
        gestor.confirmarPedido(p1);

        System.out.println("\n=== OBSERVER: CANCELAR PEDIDO ===");
        Pedido p2 = new Pedido("P-002", "PROD-B", 1, 30000.0, true);
        gestor.cancelarPedido(p2);

        // Probar Strategy
        System.out.println("\n=== STRATEGY: MOTOR DE DESCUENTOS ===");
        System.out.println("Estrategias disponibles: " + carrito.listarEstrategias());

        carrito.activarDescuento("Temporada");
        carrito.calcularTotal(100000.0);

        carrito.activarDescuento("VIP");
        carrito.calcularTotal(100000.0);

        carrito.activarDescuento("Cupon");
        carrito.calcularTotal(100000.0);
    }
}