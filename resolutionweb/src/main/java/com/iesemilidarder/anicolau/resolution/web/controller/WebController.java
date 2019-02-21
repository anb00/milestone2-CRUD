package com.iesemilidarder.anicolau.resolution.web.controller;

import com.iesemilidarder.anicolau.resolution.core.DataHelper;
import com.iesemilidarder.anicolau.resolution.core.data.Product;
import com.iesemilidarder.anicolau.resolution.core.data.Restaurant;

import com.iesemilidarder.anicolau.resolution.web.services.ActividadesService;
import com.iesemilidarder.anicolau.resolution.web.services.HotelService;
import com.iesemilidarder.anicolau.resolution.web.services.RestaurantService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
public class WebController {

    private Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private RestaurantService service;
    //private HotelService serviceH;
    private ActividadesService serviceA;
    //private AllProducts;


    private Model initModel(Model model) {
        try {
            model.addAttribute("datil", DataHelper.getHotels());
            model.addAttribute("item", DataHelper.getItems());
            model.addAttribute("act", serviceA.getActividades());
            model.addAttribute("rest", service.getRestaurants());
            model.addAttribute("aa", service.getRestaurants());
        } catch (Exception e) {
            System.out.println("Esto initModelController");
        }
        return model;
    }



    /**
     * TODO: Bienvenid@!
     *
     * @param model
     * @param name
     * @param description
     * @param imgUri
     * @param precio
     * @return
     */
 /*   @RequestMapping("/add")
    public String addProduct(Model model,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String imgUri,
                             @RequestParam Double precio
    ) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImgUri(imgUri);
        product.setPrecio(precio);
        DataHelper.addItem(product);

        initModel(model);
        return "hoteles"; //Conseguir que devuelva a la página de index o a una del producto agregado!
    }
*/


    @RequestMapping("/add")
    public String addProduct(Product product,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String imgUri,
                             @RequestParam Double precio
    ) {
        if (
         new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImgUri(imgUri);
        product.setPrecio(precio);
        DataHelper.addItem(product);
        )

       // initModel(model);
        return "hoteles"; //Conseguir que devuelva a la página de index o a una del producto agregado!
    }


    /**
     * Formulario para agregar Exclusivamente Restaurantes
     * @param model
     * @param name
     * @param description
     * @param imgUri
     * @param precio
     * @return
     */
    @RequestMapping("/addrest")
    public String addItemRest(Model model,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String imgUri,
                              @RequestParam Double precio
    ) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setImgUri(imgUri);
        restaurant.setPrecio(precio);
        DataHelper.addItemRest(restaurant);


        model.addAttribute("rest", service.getRestaurants());

        return "restaurants"; //Conseguir que devuelva a la página de index o a una del producto agregado!
    }


    /**
     * Pagina Inicial de Hoteles
     * @param session
     * @param model
     * @return
     */



    @RequestMapping("/hoteles")
    public String addProduct(HttpSession session, Model model)
    {
        initModel(model);
        return "hoteles";
    }


    /**
     * Controlador en Construcción a falta de Conversor String UUID y Viceversa
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("uuid") UUID id, Model model) {
        // Product product = DataHelper.getItemById(UUID.fromString(id));
        //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        //DataHelper.deleteProduct(product);
        //model.addAttribute("aa", DataHelper.getHotels());
        return "hoteles";
    }

    /**
     * Controlador y Buscador Base Para Hoteles
     * @param uuid
     * @param model
     * @return
     */

    @RequestMapping("/find")
    public String getItemById(@RequestParam String uuid, Model model) {
        //TODO Revisar:  DataHelper.getItemById(id).deleteById(id);

        //model.addAttribute("id", DataHelper.getItemById(UUID.fromString(uuid)));
        model.addAttribute("uu", DataHelper.getItemById(UUID.fromString(uuid)));
        //model.addAttribute("idem", DataHelper.getItemById(UUID.fromString(uuid)));

        initModel(model);
        return "hoteles";
    }

    /**
     * Controlador y Buscador para Restaurantes Exclusivamente por UUID
     * @param uuid
     * @param model
     * @return
     */

    /* Buscador de Restaurantes por ID */


    @RequestMapping("/findRest")
    public String getItemByIdRest(@RequestParam String uuid, Model model) {
        //TODO Revisar:  DataHelper.getItemById(id).deleteById(id);
        model.addAttribute("cc", DataHelper.getItemByIdRest(UUID.fromString(uuid)));
        model.addAttribute("rest", service.getRestaurants());
        model.addAttribute("uu", service.getRestaurants());
        return "restaurants";
    }




    /* GET ALL RESTAURANTS */
    @RequestMapping("/restaurants")
    public String rests(HttpSession session, Model model) {
        model.addAttribute("rest", service.getRestaurants());
        model.addAttribute("uu", service.getRestaurants());
        initModel(model);
        return "restaurants";
    }

    /*GET THE HOTELS */

    @RequestMapping("/getAllhotels")
    public String hotels(HttpSession session, Model model) {
        model.addAttribute("hotels", DataHelper.getItems());
        return "hotels";
    }

    @RequestMapping("/activities")
    public String activities(HttpSession session, Model model) {
        model.addAttribute("activities", DataHelper.getActividades());
        return "activities";
    }

    @RequestMapping(value="/Update/{id}", method = RequestMethod.GET)
            public String updateProduct(@PathVariable("id") UUID id, Model model){

                try{
                    Product product = DataHelper.getItemById(UUID.fromString(String.valueOf(id)));
                    if (product != null){
                        model.addAttribute("product", product);
                    }

                } catch (Exception e){
                    log.error("Update Hotel", e);
                }
                return "hoteles";
    }


}
