package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.PerkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PerkManager {

    @Autowired
    private PerkRepository perkRepository;

    public PerkManager()
    {

    }

    public List<Perk> getPerks(){
        return (List<Perk>) perkRepository.findAll();
    }

    public Perk savePerk(Perk perk){
        return perkRepository.save(perk);
    }

    public Perk getPerkByID(int id){
        Optional<Perk> perkOptional = perkRepository.findById(id);
        if (perkOptional.isPresent()) {
            return perkOptional.get();
        } else {
            throw new RuntimeException("com.example.model.Perk[" + id + "] not found");
        }

    }
}
