package com.omerfpekgoz.eticaretproject.network;


import com.omerfpekgoz.eticaretproject.service.IUrunlerDao;
import com.omerfpekgoz.eticaretproject.service.IUyelerDao;

public class APIUtils {

    public static final String BASE_URL = "http://www.omerfpekgoz.cf/";

    public static IUyelerDao getUyelerDao() {

        return RetrofitClient.getClient(BASE_URL).create(IUyelerDao.class);

    }
    public static IUrunlerDao getUrunlerDao() {

        return RetrofitClient.getClient(BASE_URL).create(IUrunlerDao.class);

    }

}
