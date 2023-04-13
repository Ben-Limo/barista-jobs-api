package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.address.dto.CreateAddressRequest;
import com.wapanzi.baristajobsapi.domain.address.model.Address;

public interface AddressService {
    Address updateAddressDetails(long id, AddressDto updateAddressReq);
    Address createAddress(CreateAddressRequest request);
    void removeAddress(long id);
}
