package org.lessons.java.spring_la_mia_pizzeria_relazioni.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

  @Autowired
  private SpecialOfferRepository specialOfferRepository;

  public List<SpecialOffer> findAll() {
    return specialOfferRepository.findAll();
  }

  public SpecialOffer getById(Integer id) {
    Optional<SpecialOffer> specialOfferAttempt = specialOfferRepository.findById(id);

    if (specialOfferAttempt.isEmpty()) {
    }

    return specialOfferAttempt.get();
  }

  public SpecialOffer findById(Integer id) {
    return specialOfferRepository.findById(id).get();
  }

  public SpecialOffer create(SpecialOffer specialOffer) {
    return specialOfferRepository.save(specialOffer);
  }

  public SpecialOffer update(SpecialOffer specialOffer) {
    return specialOfferRepository.save(specialOffer);
  }

  public void deleteById(Integer id) {
    specialOfferRepository.deleteById(id);
  }

  public void delete(SpecialOffer specialOffer) {
    deleteById(specialOffer.getId());
  }

  public Boolean existById(Integer id) {
    return specialOfferRepository.existsById(id);
  }

  public Boolean exist(SpecialOffer specialOffer) {
    return existById(specialOffer.getId());
  }
}
