package com.springframework.controllers;

import com.springframework.commands.ProductForm;
import com.springframework.converters.ProductToProductForm;
import com.springframework.domain.Product;
import com.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by sbiliaiev on 16/07/17.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private ProductToProductForm productToProductForm;

    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/list")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAll());
        return "product/products";
    }

    @RequestMapping("/{id}")
    public String getProductById(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product/product";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = productService.getById(id);
        ProductForm productForm = productToProductForm.convert(product);
        model.addAttribute("productForm", productForm);
        return "product/productform";
    }

    @RequestMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("productForm", new Product());
        return "product/productform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/" + savedProduct.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}
