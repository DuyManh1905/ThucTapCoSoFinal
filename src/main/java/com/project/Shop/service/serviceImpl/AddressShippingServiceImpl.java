package com.project.Shop.service.serviceImpl;

import com.project.Shop.dto.AddressShipping.AddressShippingDto;
import com.project.Shop.dto.AddressShipping.AddressShippingDtoAdmin;
import com.project.Shop.entity.Account;
import com.project.Shop.entity.AddressShipping;
import com.project.Shop.entity.Customer;
import com.project.Shop.exception.NotFoundException;
import com.project.Shop.exception.ShopApiException;
import com.project.Shop.repository.AddressShippingRepository;
import com.project.Shop.repository.CustomerRepository;
import com.project.Shop.security.CustomUserDetails;
import com.project.Shop.service.AddressShippingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressShippingServiceImpl implements AddressShippingService {

    private final AddressShippingRepository addressShippingRepository;
    private final CustomerRepository customerRepository;

    public AddressShippingServiceImpl(AddressShippingRepository addressShippingRepository, CustomerRepository customerRepository) {
        this.addressShippingRepository = addressShippingRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<AddressShippingDto> getAddressShippingByAccountId() {
        List<AddressShipping> addressShippings = addressShippingRepository.findAllByCustomer_Account_Id(getCurrentLogin().getId());
        List<AddressShippingDto> addressShippingDtos = new ArrayList<>();
        addressShippings.forEach(item -> {
            AddressShippingDto addressShippingDto = new AddressShippingDto();
            addressShippingDto.setId(item.getId());
            addressShippingDto.setAddress(item.getAddress());
            addressShippingDtos.add(addressShippingDto);
        });
        return addressShippingDtos;
    }

    @Override
    public AddressShippingDto saveAddressShippingUser(AddressShippingDto addressShippingDto) {
        List<AddressShipping> addressShippings = addressShippingRepository.findAllByCustomer_Account_Id(getCurrentLogin().getId());
        if(addressShippings.size() > 5) {
            throw new ShopApiException(HttpStatus.BAD_REQUEST, "Bạn chỉ được thêm tối đa 5 địa chỉ");
        }
        AddressShipping addressShipping = new AddressShipping();
        addressShipping.setAddress(addressShippingDto.getAddress());
        Customer customer = new Customer();
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            customer = getCurrentLogin().getCustomer();
            addressShipping.setCustomer(customer);
        }

        AddressShipping addressShippingNew = addressShippingRepository.save(addressShipping);
        return new AddressShippingDto(addressShippingNew.getId(), addressShippingNew.getAddress());
    }

    @Override
    public AddressShippingDto saveAddressShippingAdmin(AddressShippingDtoAdmin addressShippingDto) {
        AddressShipping addressShipping = new AddressShipping();
        addressShipping.setAddress(addressShipping.getAddress());
        Customer customer = customerRepository.findById(addressShippingDto.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer not found"));
        addressShipping.setCustomer(customer);

        AddressShipping addressShippingNew = addressShippingRepository.save(addressShipping);
        return new AddressShippingDto(addressShippingNew.getId(), addressShippingNew.getAddress());
    }

    @Override
    public void deleteAddressShipping(Long id) {
        AddressShipping addressShipping = addressShippingRepository.findById(id).orElseThrow(null);
        addressShippingRepository.delete(addressShipping);
    }

    private Account getCurrentLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return customUserDetails.getAccount();
        }

        // Handle the case where the principal is not a CustomUserDetails
        return null; // or throw an exception, depending on your use case
    }


}
