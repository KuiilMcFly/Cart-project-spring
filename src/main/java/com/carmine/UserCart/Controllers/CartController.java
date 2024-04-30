package com.carmine.UserCart.Controllers;

import com.carmine.UserCart.Models.Cart;
import com.carmine.UserCart.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @GetMapping("/getAllCarts")
    public ResponseEntity<List<Cart>> getAllCarts() {
        try {
            List<Cart> cartList = new ArrayList<>();
            cartRepository.findAll().forEach(cartList::add);

            if (cartList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception exception){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
