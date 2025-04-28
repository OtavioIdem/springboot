package com.guarani.teste_backend.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guarani.teste_backend.domain.enums.OrderStatus;
import com.guarani.teste_backend.domain.service.ReportService;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/sales-by-date")
    public ResponseEntity<Map<String, Object>> getSalesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(reportService.reportByDateRange(start, end));
    }

    @GetMapping("/sales-by-status")
    public ResponseEntity<Map<String, Object>> getSalesByStatus(
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(reportService.reportByStatus(status));
    }
}