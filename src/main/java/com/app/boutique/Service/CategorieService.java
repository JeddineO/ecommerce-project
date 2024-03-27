package com.app.boutique.Service;

import com.app.boutique.bo.Categorie;
import com.app.boutique.dao.CategorieDAO;

import java.util.List;
import com.app.boutique.dto.CategorieDTO;

public class CategorieService {

    public List<Categorie> getCategories()
    {
        return new CategorieDAO().findAll();
    }


    public boolean insertCategorie(CategorieDTO categorieDTO)
    {
        return new CategorieDAO().save(this.toCategorie(categorieDTO));
    }

    public Categorie finById(int id){
        return  new CategorieDAO().findById(id);
    }

    public boolean update(CategorieDTO categorieDTO,int id)
    {
        Categorie categorie=this.finById(id);
        categorie.setNomCategorie(categorieDTO.getNomCategorie());
        return new CategorieDAO().update(categorie);
    }


    public boolean deleteCategorie(int id)
    {
        return new CategorieDAO().delete(this.finById(id));
    }

    public  Categorie toCategorie(CategorieDTO categorieDTO)
    {
        Categorie categorie=new Categorie();
        categorie.setNomCategorie(categorieDTO.getNomCategorie());
        return  categorie;
    }
}
