package au.amidja.service;

import java.io.UnsupportedEncodingException;

import au.amidja.persistence.model.SystemUser;

public interface IUserService {

    String generateQRUrl(String userName) throws UnsupportedEncodingException;
    
    boolean registerFor2FA(String userName);
     
}
