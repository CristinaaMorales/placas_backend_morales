package com.autos.morales.backend.morales_backend.service.impl;

import com.autos.morales.backend.morales_backend.dto.PlacaRequestDTO;
import com.autos.morales.backend.morales_backend.dto.PlacaResponseDTO;
import com.autos.morales.backend.morales_backend.service.PlacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class PlacaServiceImpl implements PlacaService {

    @Autowired
    ResourceLoader resourceLoader;

    private static final String PLACAS_FILE_PATH = "classpath:placas.txt";

    @Override
    public PlacaResponseDTO buscarVehiculoPorPlaca(PlacaRequestDTO placaRequestDTO) throws IOException {
        PlacaResponseDTO placaResponse = null;
        // importa el archivo txt
        Resource resource = resourceLoader.getResource(PLACAS_FILE_PATH);

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                // si la placa coincide con el archivo txt
                if (placaRequestDTO.placa().equals(datos[0])) {
                    // respuesta si encuentra la placa en el txt
                    placaResponse = new PlacaResponseDTO("00", "",
                            datos[1],  // marca
                            datos[2],  // modelo
                            Integer.parseInt(datos[3]),  // asientos
                            Double.parseDouble(datos[4]), // precio
                            datos[5]  // color
                    );
                    break;  // placa encontrada
                }
            }

        } catch (IOException e) {
            throw new IOException("Error al leer el archivo de placas", e);
        }

        // si no se encuentra placa, devolver error
        if (placaResponse == null) {
            placaResponse = new PlacaResponseDTO("01", "No se encontró un vehículo para la placa ingresada", "", "", 0, 0.0, "");
        }

        return placaResponse;
    }
}