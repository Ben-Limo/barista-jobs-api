package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;

public interface AddressService {
    Address updateAddressDetails(long id, Address address);
    Address createAddress(Address address);
    void removeAddress(long id);
}
