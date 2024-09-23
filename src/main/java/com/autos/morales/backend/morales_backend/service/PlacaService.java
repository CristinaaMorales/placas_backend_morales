package com.autos.morales.backend.morales_backend.service;

import com.autos.morales.backend.morales_backend.dto.PlacaRequestDTO;
import com.autos.morales.backend.morales_backend.dto.PlacaResponseDTO;

import java.io.IOException;

public interface PlacaService {
    PlacaResponseDTO buscarVehiculoPorPlaca(PlacaRequestDTO placaRequestDTO) throws IOException;
}
