package com.api_ecommerce.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.api_ecommerce.dto.request.SellerRequestDTO;
import com.api_ecommerce.entities.Seller;
import com.api_ecommerce.entities.User;
import com.api_ecommerce.repositories.SellerRepository;
import com.api_ecommerce.repositories.UserRepository;
import com.api_ecommerce.services.SellerService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public Seller saveSeller(SellerRequestDTO sellerRequestDTO) {
        if (sellerRepository.existsByName(sellerRequestDTO.getName())) {
            throw new EntityExistsException("Seller already exists with name: " + sellerRequestDTO.getName());
        }

        User user = userRepository.findById(sellerRequestDTO.getUserId())
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found with ID: " + sellerRequestDTO.getUserId()));

        if (sellerRepository.existsByUserId(user.getUserId())) {
            throw new EntityExistsException("This user is already registered as a seller.");
        }
        user.setRole(2);
        userRepository.save(user);

        Seller seller = modelMapper.map(sellerRequestDTO, Seller.class);

        return sellerRepository.save(seller);
    }

    @Override
    public Seller getSellerById(Integer sellerId) {
        return sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found: " + sellerId));
    }

    @Override
    public Page<Seller> getSellers(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return sellerRepository.findAll(null, pageable);
    }

    @Override
    @Transactional
    public Seller updateSeller(Integer sellerId, SellerRequestDTO sellerRequestDTO) {
        return sellerRepository.findById(sellerId)
                .map(existing -> {
                    modelMapper.map(sellerRequestDTO, existing);
                    return sellerRepository.save(existing);
                })
                .orElseThrow(() -> new EntityNotFoundException("Seller not found: " + sellerId));
    }
}
