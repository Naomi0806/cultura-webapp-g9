package com.example;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
class CoInvestorController {

    @Autowired
    private CoInvestorRepository coInvestorRepository;

    public CoInvestorController() {
    }

    @GetMapping("/api/co-investors")
    Iterable<CoInvestor> getCoInvestors(@RequestParam Optional<String> coInvestorType) {
        return coInvestorRepository.findAll();
    }

    @GetMapping("/api/co-investors/{id}")
    Optional<CoInvestor> getCoInvestor(@PathVariable long id) {
        return coInvestorRepository.findById(id);
    }

    @PostMapping("/api/co-investors")
    CoInvestor createCoInvestor(@RequestBody CoInvestor coInvestorType) {
        return coInvestorRepository.save(coInvestor);
    }

    @PutMapping("/api/co-investors/{id}")
    Optional<CoInvestor> updateCoInvestor(@RequestBody CoInvestor coInvestorRequest, @PathVariable long id) {
        Optional<CoInvestor> opt = coInvestorRepo.findById(id);
        if (opt.isPresent()) {
            if (coInvestorRequest.getId() == id) {
                return Optional.of(coInvestorRepository.save(coInvestorRequest));
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do coinvestidor com id " + id);
    }

    @DeleteMapping(value = "/api/co-investors/{id}")
    void deleteCoInvestor(@PathVariable long id) {
        coInvestorRepo.deleteById(id);
    }
}
