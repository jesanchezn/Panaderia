package com.parquesoftti.panaderia.service;

import com.parquesoftti.panaderia.exception.ProductoNotFoundException;
import com.parquesoftti.panaderia.model.Producto;
import com.parquesoftti.panaderia.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    // Obtener producto por nombre
    public Producto getByName(String nombre) {
        return productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado: " + nombre));
    }

    // Guardar nuevo producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar producto existente
    public Producto update(Long id, Producto producto) {
        Producto actual = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("ID no encontrado: " + id));
        actual.setNombre(producto.getNombre());
        actual.setPrecio(producto.getPrecio());
        actual.setStock(producto.getStock());
        return productoRepository.save(actual);
    }

    // Eliminar producto por ID
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException("ID no encontrado: " + id);
        }
        productoRepository.deleteById(id);
    }
}
