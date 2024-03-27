package com.app.boutique.Service;

import com.app.boutique.bo.Admin;
import com.app.boutique.bo.Client;
import com.app.boutique.dao.AdminDAO;
import com.app.boutique.dao.ClientDAO;
import com.app.boutique.dto.AdminDTO;
import com.app.boutique.dto.ClientDTO;

public class ClientService {

    public  Client login(ClientDTO client)
    {
        ClientDAO dao=new ClientDAO();
        return  dao.login(this.toClient(client));
    }

    public Client toClient(ClientDTO dto)
    {
        Client client= new Client();
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        return client;

    }
}
