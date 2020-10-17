package com.demo.controller;

import com.demo.model.Pokemon;
import com.demo.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

@Controller
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    //    Default home path '/' will return view index with message
    @RequestMapping(method = RequestMethod.GET,value="/")
    public String printHello(ModelMap model){
        model.addAttribute("message","Hello Spring MVC in maven");
        return "index";
    }
    //  When the path is routed to '/home' below method to be called and view returned is home
    @RequestMapping(method = RequestMethod.GET, value="/home")
    public ModelAndView home1(){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("pageTitle","Home Page");
        modelAndView.addObject("message","Hi Welcome to Sprint MVC");
        return modelAndView;
    }
    //  When the path is routed to '/pokemanage' below method to be called and view returned is pokemanage
    @RequestMapping(method = RequestMethod.GET, value = "/pokemanage")
    public ModelAndView home() {
        List<Pokemon> listPokemon = pokemonService.listAll();
        for (Pokemon Pokemon : listPokemon) {
            System.out.println(Pokemon.toString());
        }
        ModelAndView mav = new ModelAndView("pokemanage");
        mav.addObject("listPokemon", listPokemon);
        return mav;
    }
    //  When the path is routed to '/new' below method to be called and view returned is newPokemon
    @RequestMapping(method = RequestMethod.GET, value ="/new")
    public String newPokemonForm(Map<String, Object> model) {
        Pokemon Pokemon = new Pokemon();
        model.put("pokemon", Pokemon);
        return "newPokemon";
    }
    //  When a form is submitted in POST and action method as 'save' below method to be called returned to home '/' i.e. index
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String savePokemon(@ModelAttribute("pokemon") Pokemon pokemon) {
        pokemonService.save(pokemon);
        return "redirect:/";
    }
    //  When the path is routed to '/edit' below method to be called and view returned is editPokemon
    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public ModelAndView editPokemonForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("editPokemon");
        Pokemon pokemon = pokemonService.get(id);
        mav.addObject("pokemon", pokemon);
        return mav;
    }
    //  When the path is routed to '/delete' below method to be called and view returned is pokemanage
    @RequestMapping(method = RequestMethod.GET, value ="/delete")
    public String deletePokemonForm(@RequestParam long id) {
        pokemonService.delete(id);
        return "redirect:/pokemanage";
    }
    //  When the path is routed to '/search' below method to be called and view returned is searchPokemon
    @RequestMapping(method = RequestMethod.GET, value ="/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Pokemon> result = pokemonService.search(keyword);
        ModelAndView mav = new ModelAndView("searchPokemon");
        mav.addObject("result", result);

        return mav;
    }

}
