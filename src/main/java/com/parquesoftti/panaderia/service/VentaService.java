package com.parquesoftti.panaderia.service;

import com.parquesoftti.panaderia.exception.VentaNotFoundException;
import com.parquesoftti.panaderia.model.Venta;
import com.parquesoftti.panaderia.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;

    public List<Venta> getAll() {
        return ventaRepository.findAll();
    }

    public Venta getById(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new VentaNotFoundException("Venta no encontrada con ID: " + id));
    }

    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta update(Long id, Venta nuevaVenta) {
        Venta actual = ventaRepository.findById(id)
                .orElseThrow(() -> new VentaNotFoundException("Venta no encontrada con ID: " + id));
        actual.setProducto(nuevaVenta.getProducto());
        actual.setCantidad(nuevaVenta.getCantidad());
        actual.setTotal(nuevaVenta.getTotal());
        actual.setFecha(nuevaVenta.getFecha());
        return ventaRepository.save(actual);
    }

    public void deleteById(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new VentaNotFoundException("Venta no encontrada con ID: " + id);
        }
        ventaRepository.deleteById(id);
    }
}
