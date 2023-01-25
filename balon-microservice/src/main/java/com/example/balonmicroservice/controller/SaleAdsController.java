package com.example.balonmicroservice.controller;

import com.example.balonmicroservice.model.SaleAds;
import com.example.balonmicroservice.repository.SaleAdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/saleads")
public class SaleAdsController {
    @Autowired
    SaleAdsRepository saleAdsRepository;

    @GetMapping(value = "/getAll")
    public List<SaleAds> getAllAnnouncements() {
        System.out.println("Get all sale announcements");
        return saleAdsRepository.findAll();
    }

    @GetMapping(value = "/getActive")
    public List<SaleAds> getAllActive() {
        System.out.println("Get all active sale announcements");
        return saleAdsRepository.findByActive(true);
    }

    /* TODO: Non salva modifiche */
    @GetMapping(value = "/changeActiveStatus/{id}")
    public SaleAds changeStatus(@PathVariable("id") Long id) {
        System.out.println("Dentro cambio stato");
        SaleAds result = null;
        if (saleAdsRepository.findById(id).isPresent()) {
            System.out.println("Ho trovato l'annuncio");
            result = saleAdsRepository.findById(id).get();
            result.setActive(!result.isActive());
        }
        System.out.print("Ho finito");
        return result;
    }

    @PostMapping(value = "/createAds")
    public SaleAds createSaleAds(@RequestBody SaleAds param) {
        System.out.println("Create new sale annuncements..." + param.toString());
        return saleAdsRepository.save(
                new SaleAds(param.getOwneremail(), param.getDescription(), param.getPrice()));
    }

    @DeleteMapping(value = "/deleteAds/{id}")
    public String deleteSaleAds(@PathVariable("id") Long id) {
        System.out.println("Elimina l'annuncio: " + id);
        saleAdsRepository.deleteById(id);
        return "done";
    }
}