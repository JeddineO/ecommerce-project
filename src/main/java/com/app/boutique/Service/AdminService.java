package com.app.boutique.Service;

import com.app.boutique.dao.AdminDAO;
import com.app.boutique.dto.AdminDTO;
import com.app.boutique.bo.Admin;

public class AdminService {

    public  Admin login(AdminDTO admin)
    {
        AdminDAO dao=new AdminDAO();
        return  dao.login(this.toAdmin(admin));
    }

    public Admin toAdmin(AdminDTO dto)
    {
        Admin admin= new Admin();
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        return admin;

    }
}
