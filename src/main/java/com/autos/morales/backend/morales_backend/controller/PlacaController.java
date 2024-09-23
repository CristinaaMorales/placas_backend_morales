package com.autos.morales.backend.morales_backend.controller;

import com.autos.morales.backend.morales_backend.dto.PlacaRequestDTO;
import com.autos.morales.backend.morales_backend.dto.PlacaResponseDTO;
import com.autos.morales.backend.morales_backend.service.PlacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculo")
public class PlacaController {

    @Autowired
    private PlacaService placaService;

    @PostMapping("/buscar")
    public PlacaResponseDTO buscarVehiculo(@RequestBody PlacaRequestDTO placaRequestDTO){

        try {
            // buscar por placa
            PlacaResponseDTO response = placaService.buscarVehiculoPorPlaca(placaRequestDTO);

            // no fue encontrado
            if (response.codigo().equals("01")) {
                return new PlacaResponseDTO("01", "No se encontró un vehículo para la placa ingresada", "", "", 0, 0.0, "");
            }

            // detalles
            return response;

        } catch (Exception e) {
            // errores
            System.out.println(e.getMessage());
            return new PlacaResponseDTO("99", "Error: Ocurrió un problema al procesar la solicitud", "", "", 0, 0.0, "");
        }
    }
}
