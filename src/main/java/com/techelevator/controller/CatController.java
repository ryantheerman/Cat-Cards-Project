package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatCardNotFoundException;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpMessageConverterExtractor;

import javax.validation.Valid;
import java.util.List;
@RestController
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping(path = "/api/cards", method = RequestMethod.GET)
    public List<CatCard> get() {
        return catCardDao.list();
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.GET)
    public CatCard get(@PathVariable long id) {
        return catCardDao.get(id);
    }

    @RequestMapping(path = "/api/cards/random", method = RequestMethod.GET)
    public CatCard getRandomCard() {
        CatCard otherCatCard = new CatCard();
        otherCatCard.setCatFact(catFactService.getFact().getText());
        otherCatCard.setImgUrl(catPicService.getPic().getFile());
        return otherCatCard;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/api/cards", method = RequestMethod.POST)
    public boolean saveCatCard(@RequestBody CatCard catCard)
            throws CatCardNotFoundException {
        return catCardDao.save(catCard);
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.PUT)
    public boolean updateCatCard(@Valid @RequestBody CatCard catCard,
                                 @PathVariable long id) throws CatCardNotFoundException {
        return catCardDao.update(id, catCard);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable(name = "id") long id) throws CatCardNotFoundException {
    catCardDao.delete(id);
    }

}
